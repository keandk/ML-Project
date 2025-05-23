/* ====================================================================
 * Copyright (c) 2001-2011 The OpenSSL Project.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. All advertising materials mentioning features or use of this
 *    software must display the following acknowledgment:
 *    "This product includes software developed by the OpenSSL Project
 *    for use in the OpenSSL Toolkit. (http://www.openssl.org/)"
 *
 * 4. The names "OpenSSL Toolkit" and "OpenSSL Project" must not be used to
 *    endorse or promote products derived from this software without
 *    prior written permission. For written permission, please contact
 *    openssl-core@openssl.org.
 *
 * 5. Products derived from this software may not be called "OpenSSL"
 *    nor may "OpenSSL" appear in their names without prior written
 *    permission of the OpenSSL Project.
 *
 * 6. Redistributions of any form whatsoever must retain the following
 *    acknowledgment:
 *    "This product includes software developed by the OpenSSL Project
 *    for use in the OpenSSL Toolkit (http://www.openssl.org/)"
 *
 * THIS SOFTWARE IS PROVIDED BY THE OpenSSL PROJECT ``AS IS'' AND ANY
 * EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE OpenSSL PROJECT OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 * ====================================================================
 *
 */
#include <openssl/opensslconf.h>
#ifndef OPENSSL_NO_AES
#include <openssl/evp.h>
#include <openssl/err.h>
#include <string.h>
#include <assert.h>
#include <openssl/aes.h>
#include "evp_locl.h"
#ifndef OPENSSL_FIPS
#include "modes_lcl.h"
#include <openssl/rand.h>
#include <sys/stat.h> 
#include <stonesoup/stonesoup_trace.h> 
typedef struct {
AES_KEY ks;
block128_f block;
union {
cbc128_f cbc;
ctr128_f ctr;}stream;}EVP_AES_KEY;
typedef struct {
/* AES key schedule to use */
AES_KEY ks;
/* Set if key initialised */
int key_set;
/* Set if an iv is set */
int iv_set;
GCM128_CONTEXT gcm;
/* Temporary IV store */
unsigned char *iv;
/* IV length */
int ivlen;
int taglen;
/* It is OK to generate IVs */
int iv_gen;
/* TLS AAD length */
int tls_aad_len;
ctr128_f ctr;}EVP_AES_GCM_CTX;
typedef struct {
/* AES key schedules to use */
AES_KEY ks1;
AES_KEY ks2;
XTS128_CONTEXT xts;
void (*stream)(const unsigned char *, unsigned char *, size_t , const AES_KEY *, const AES_KEY *, const unsigned char [16]);}EVP_AES_XTS_CTX;
typedef struct {
/* AES key schedule to use */
AES_KEY ks;
/* Set if key initialised */
int key_set;
/* Set if an iv is set */
int iv_set;
/* Set if tag is valid */
int tag_set;
/* Set if message length set */
int len_set;
/* L and M parameters from RFC3610 */
int L;
int M;
CCM128_CONTEXT ccm;
ccm128_f str;}EVP_AES_CCM_CTX;
#define MAXBITCHUNK	((size_t)1<<(sizeof(size_t)*8-4))
#ifdef VPAES_ASM
extern int vpaes_set_encrypt_key(const unsigned char *userKey,int bits,AES_KEY *key);
extern int vpaes_set_decrypt_key(const unsigned char *userKey,int bits,AES_KEY *key);
extern void vpaes_encrypt(const unsigned char *in,unsigned char *out,const AES_KEY *key);
extern void vpaes_decrypt(const unsigned char *in,unsigned char *out,const AES_KEY *key);
extern void vpaes_cbc_encrypt(const unsigned char *in,unsigned char *out,size_t length,const AES_KEY *key,unsigned char *ivec,int enc);
#endif
#ifdef BSAES_ASM
extern void bsaes_cbc_encrypt(const unsigned char *in,unsigned char *out,size_t length,const AES_KEY *key,unsigned char ivec[16],int enc);
extern void bsaes_ctr32_encrypt_blocks(const unsigned char *in,unsigned char *out,size_t len,const AES_KEY *key,const unsigned char ivec[16]);
extern void bsaes_xts_encrypt(const unsigned char *inp,unsigned char *out,size_t len,const AES_KEY *key1,const AES_KEY *key2,const unsigned char iv[16]);
extern void bsaes_xts_decrypt(const unsigned char *inp,unsigned char *out,size_t len,const AES_KEY *key1,const AES_KEY *key2,const unsigned char iv[16]);
#endif
#ifdef AES_CTR_ASM
#endif
#ifdef AES_XTS_ASM
#endif
#if	defined(AES_ASM) && !defined(I386_ONLY) &&	(  \
	((defined(__i386)	|| defined(__i386__)	|| \
	  defined(_M_IX86)) && defined(OPENSSL_IA32_SSE2))|| \
	defined(__x86_64)	|| defined(__x86_64__)	|| \
	defined(_M_AMD64)	|| defined(_M_X64)	|| \
	defined(__INTEL__)				)
extern unsigned int OPENSSL_ia32cap_P[2];
#ifdef VPAES_ASM
#define VPAES_CAPABLE	(OPENSSL_ia32cap_P[1]&(1<<(41-32)))
#endif
#ifdef BSAES_ASM
#define BSAES_CAPABLE	VPAES_CAPABLE
#endif
/*
 * AES-NI section
 */
#define	AESNI_CAPABLE	(OPENSSL_ia32cap_P[1]&(1<<(57-32)))
extern int aesni_set_encrypt_key(const unsigned char *userKey,int bits,AES_KEY *key);
extern int aesni_set_decrypt_key(const unsigned char *userKey,int bits,AES_KEY *key);
extern void aesni_encrypt(const unsigned char *in,unsigned char *out,const AES_KEY *key);
extern void aesni_decrypt(const unsigned char *in,unsigned char *out,const AES_KEY *key);
extern void aesni_ecb_encrypt(const unsigned char *in,unsigned char *out,size_t length,const AES_KEY *key,int enc);
extern void aesni_cbc_encrypt(const unsigned char *in,unsigned char *out,size_t length,const AES_KEY *key,unsigned char *ivec,int enc);
extern void aesni_ctr32_encrypt_blocks(const unsigned char *in,unsigned char *out,size_t blocks,const void *key,const unsigned char *ivec);
extern void aesni_xts_encrypt(const unsigned char *in,unsigned char *out,size_t length,const AES_KEY *key1,const AES_KEY *key2,const unsigned char iv[16]);
extern void aesni_xts_decrypt(const unsigned char *in,unsigned char *out,size_t length,const AES_KEY *key1,const AES_KEY *key2,const unsigned char iv[16]);
extern void aesni_ccm64_encrypt_blocks(const unsigned char *in,unsigned char *out,size_t blocks,const void *key,const unsigned char ivec[16],unsigned char cmac[16]);
extern void aesni_ccm64_decrypt_blocks(const unsigned char *in,unsigned char *out,size_t blocks,const void *key,const unsigned char ivec[16],unsigned char cmac[16]);
int fewterer_ineducabilian = 0;
int stonesoup_global_variable;
typedef char *adventured_supersanction;
void* stonesoup_printf_context = NULL;
void stonesoup_setup_printf_context() {
    struct stat st = {0};
    char * ss_tc_root = NULL;
    char * dirpath = NULL;
    int size_dirpath = 0;
    char * filepath = NULL;
    int size_filepath = 0;
    int retval = 0;
    ss_tc_root = getenv("SS_TC_ROOT");
    if (ss_tc_root != NULL) {
        size_dirpath = strlen(ss_tc_root) + strlen("testData") + 2;
        dirpath = (char*) malloc (size_dirpath * sizeof(char));
        if (dirpath != NULL) {
            sprintf(dirpath, "%s/%s", ss_tc_root, "testData");
            retval = 0;
            if (stat(dirpath, &st) == -1) {
                retval = mkdir(dirpath, 0700);
            }
            if (retval == 0) {
                size_filepath = strlen(dirpath) + strlen("logfile.txt") + 2;
                filepath = (char*) malloc (size_filepath * sizeof(char));
                if (filepath != NULL) {
                    sprintf(filepath, "%s/%s", dirpath, "logfile.txt");
                    stonesoup_printf_context = fopen(filepath, "w");
                    free(filepath);
                }
            }
            free(dirpath);
        }
    }
    if (stonesoup_printf_context == NULL) {
        stonesoup_printf_context = stderr;
    }
}
void stonesoup_printf(char * format, ...) {
    va_list argptr;
    va_start(argptr, format);
    vfprintf(stonesoup_printf_context, format, argptr);
    va_end(argptr);
    fflush(stonesoup_printf_context);
}
void stonesoup_close_printf_context() {
    if (stonesoup_printf_context != NULL &&
        stonesoup_printf_context != stderr) {
        fclose(stonesoup_printf_context);
    }
}
void cohort_electrifiable(int goddammit_nish,adventured_supersanction *psyllium_schlieren);
void clydebank_quadrantile(int chymosins_concentration,adventured_supersanction *noiselessly_lovevine);

static int aesni_init_key(EVP_CIPHER_CTX *ctx,const unsigned char *key,const unsigned char *iv,int enc)
{
  int ret;
  int mode;
  EVP_AES_KEY *dat = (EVP_AES_KEY *)(ctx -> cipher_data);
  mode = (ctx -> cipher -> flags & 0xF0007);
  if ((mode == 0x1 || mode == 0x2) && !enc) {
    ret = aesni_set_decrypt_key(key,ctx -> key_len * 8,(ctx -> cipher_data));
    dat -> block = ((block128_f )aesni_decrypt);
    dat -> stream . cbc = (mode == 0x2?((cbc128_f )aesni_cbc_encrypt) : ((void *)0));
  }
  else {
    ret = aesni_set_encrypt_key(key,ctx -> key_len * 8,(ctx -> cipher_data));
    dat -> block = ((block128_f )aesni_encrypt);
    if (mode == 0x2) {
      dat -> stream . cbc = ((cbc128_f )aesni_cbc_encrypt);
    }
    else {
      if (mode == 0x5) {
        dat -> stream . ctr = ((ctr128_f )aesni_ctr32_encrypt_blocks);
      }
      else {
        dat -> stream . cbc = ((void *)0);
      }
    }
  }
  if (ret < 0) {
    ERR_put_error(6,165,143,"e_aes.c",258);
    return 0;
  }
  return 1;
}

static int aesni_cbc_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  aesni_cbc_encrypt(in,out,len,(ctx -> cipher_data),ctx -> iv,ctx -> encrypt);
  return 1;
}

static int aesni_ecb_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  size_t bl = (ctx -> cipher -> block_size);
  if (len < bl) {
    return 1;
  }
  aesni_ecb_encrypt(in,out,len,(ctx -> cipher_data),ctx -> encrypt);
  return 1;
}
#define aesni_ofb_cipher aes_ofb_cipher
static int aes_ofb_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len);
#define aesni_cfb_cipher aes_cfb_cipher
static int aes_cfb_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len);
#define aesni_cfb8_cipher aes_cfb8_cipher
static int aes_cfb8_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len);
#define aesni_cfb1_cipher aes_cfb1_cipher
static int aes_cfb1_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len);
#define aesni_ctr_cipher aes_ctr_cipher
static int aes_ctr_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len);

static int aesni_gcm_init_key(EVP_CIPHER_CTX *ctx,const unsigned char *key,const unsigned char *iv,int enc)
{
  EVP_AES_GCM_CTX *gctx = (ctx -> cipher_data);
  if (!iv && !key) {
    return 1;
  }
  if (key) {
    aesni_set_encrypt_key(key,ctx -> key_len * 8,&gctx -> ks);
    CRYPTO_gcm128_init(&gctx -> gcm,(&gctx -> ks),((block128_f )aesni_encrypt));
    gctx -> ctr = ((ctr128_f )aesni_ctr32_encrypt_blocks);
/* If we have an iv can set it directly, otherwise use
		 * saved IV.
		 */
    if (iv == ((void *)0) && gctx -> iv_set) {
      iv = (gctx -> iv);
    }
    if (iv) {
      CRYPTO_gcm128_setiv(&gctx -> gcm,iv,(gctx -> ivlen));
      gctx -> iv_set = 1;
    }
    gctx -> key_set = 1;
  }
  else {
/* If key set use IV, otherwise copy */
    if (gctx -> key_set) {
      CRYPTO_gcm128_setiv(&gctx -> gcm,iv,(gctx -> ivlen));
    }
    else {
      memcpy((gctx -> iv),iv,(gctx -> ivlen));
    }
    gctx -> iv_set = 1;
    gctx -> iv_gen = 0;
  }
  return 1;
}
#define aesni_gcm_cipher aes_gcm_cipher
static int aes_gcm_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len);

static int aesni_xts_init_key(EVP_CIPHER_CTX *ctx,const unsigned char *key,const unsigned char *iv,int enc)
{
  EVP_AES_XTS_CTX *xctx = (ctx -> cipher_data);
  if (!iv && !key) {
    return 1;
  }
  if (key) {
/* key_len is two AES keys */
    if (enc) {
      aesni_set_encrypt_key(key,ctx -> key_len * 4,&xctx -> ks1);
      xctx -> xts . block1 = ((block128_f )aesni_encrypt);
      xctx -> stream = aesni_xts_encrypt;
    }
    else {
      aesni_set_decrypt_key(key,ctx -> key_len * 4,&xctx -> ks1);
      xctx -> xts . block1 = ((block128_f )aesni_decrypt);
      xctx -> stream = aesni_xts_decrypt;
    }
    aesni_set_encrypt_key(key + ctx -> key_len / 2,ctx -> key_len * 4,&xctx -> ks2);
    xctx -> xts . block2 = ((block128_f )aesni_encrypt);
    xctx -> xts . key1 = (&xctx -> ks1);
  }
  if (iv) {
    xctx -> xts . key2 = (&xctx -> ks2);
    memcpy((ctx -> iv),iv,16);
  }
  return 1;
}
#define aesni_xts_cipher aes_xts_cipher
static int aes_xts_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len);

static int aesni_ccm_init_key(EVP_CIPHER_CTX *ctx,const unsigned char *key,const unsigned char *iv,int enc)
{
  EVP_AES_CCM_CTX *cctx = (ctx -> cipher_data);
  if (!iv && !key) {
    return 1;
  }
  if (key) {
    aesni_set_encrypt_key(key,ctx -> key_len * 8,&cctx -> ks);
    CRYPTO_ccm128_init(&cctx -> ccm,(cctx -> M),(cctx -> L),(&cctx -> ks),((block128_f )aesni_encrypt));
    cctx -> str = (enc?((ccm128_f )aesni_ccm64_encrypt_blocks) : ((ccm128_f )aesni_ccm64_decrypt_blocks));
    cctx -> key_set = 1;
  }
  if (iv) {
    memcpy((ctx -> iv),iv,(15 - cctx -> L));
    cctx -> iv_set = 1;
  }
  return 1;
}
#define aesni_ccm_cipher aes_ccm_cipher
static int aes_ccm_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len);
#define BLOCK_CIPHER_generic(nid,keylen,blocksize,ivlen,nmode,mode,MODE,flags) \
static const EVP_CIPHER aesni_##keylen##_##mode = { \
	nid##_##keylen##_##nmode,blocksize,keylen/8,ivlen, \
	flags|EVP_CIPH_##MODE##_MODE,	\
	aesni_init_key,			\
	aesni_##mode##_cipher,		\
	NULL,				\
	sizeof(EVP_AES_KEY),		\
	NULL,NULL,NULL,NULL }; \
static const EVP_CIPHER aes_##keylen##_##mode = { \
	nid##_##keylen##_##nmode,blocksize,	\
	keylen/8,ivlen, \
	flags|EVP_CIPH_##MODE##_MODE,	\
	aes_init_key,			\
	aes_##mode##_cipher,		\
	NULL,				\
	sizeof(EVP_AES_KEY),		\
	NULL,NULL,NULL,NULL }; \
const EVP_CIPHER *EVP_aes_##keylen##_##mode(void) \
{ return AESNI_CAPABLE?&aesni_##keylen##_##mode:&aes_##keylen##_##mode; }
#define BLOCK_CIPHER_custom(nid,keylen,blocksize,ivlen,mode,MODE,flags) \
static const EVP_CIPHER aesni_##keylen##_##mode = { \
	nid##_##keylen##_##mode,blocksize, \
	(EVP_CIPH_##MODE##_MODE==EVP_CIPH_XTS_MODE?2:1)*keylen/8, ivlen, \
	flags|EVP_CIPH_##MODE##_MODE,	\
	aesni_##mode##_init_key,	\
	aesni_##mode##_cipher,		\
	aes_##mode##_cleanup,		\
	sizeof(EVP_AES_##MODE##_CTX),	\
	NULL,NULL,aes_##mode##_ctrl,NULL }; \
static const EVP_CIPHER aes_##keylen##_##mode = { \
	nid##_##keylen##_##mode,blocksize, \
	(EVP_CIPH_##MODE##_MODE==EVP_CIPH_XTS_MODE?2:1)*keylen/8, ivlen, \
	flags|EVP_CIPH_##MODE##_MODE,	\
	aes_##mode##_init_key,		\
	aes_##mode##_cipher,		\
	aes_##mode##_cleanup,		\
	sizeof(EVP_AES_##MODE##_CTX),	\
	NULL,NULL,aes_##mode##_ctrl,NULL }; \
const EVP_CIPHER *EVP_aes_##keylen##_##mode(void) \
{ return AESNI_CAPABLE?&aesni_##keylen##_##mode:&aes_##keylen##_##mode; }
#else
#define BLOCK_CIPHER_generic(nid,keylen,blocksize,ivlen,nmode,mode,MODE,flags) \
static const EVP_CIPHER aes_##keylen##_##mode = { \
	nid##_##keylen##_##nmode,blocksize,keylen/8,ivlen, \
	flags|EVP_CIPH_##MODE##_MODE,	\
	aes_init_key,			\
	aes_##mode##_cipher,		\
	NULL,				\
	sizeof(EVP_AES_KEY),		\
	NULL,NULL,NULL,NULL }; \
const EVP_CIPHER *EVP_aes_##keylen##_##mode(void) \
{ return &aes_##keylen##_##mode; }
#define BLOCK_CIPHER_custom(nid,keylen,blocksize,ivlen,mode,MODE,flags) \
static const EVP_CIPHER aes_##keylen##_##mode = { \
	nid##_##keylen##_##mode,blocksize, \
	(EVP_CIPH_##MODE##_MODE==EVP_CIPH_XTS_MODE?2:1)*keylen/8, ivlen, \
	flags|EVP_CIPH_##MODE##_MODE,	\
	aes_##mode##_init_key,		\
	aes_##mode##_cipher,		\
	aes_##mode##_cleanup,		\
	sizeof(EVP_AES_##MODE##_CTX),	\
	NULL,NULL,aes_##mode##_ctrl,NULL }; \
const EVP_CIPHER *EVP_aes_##keylen##_##mode(void) \
{ return &aes_##keylen##_##mode; }
#endif
#define BLOCK_CIPHER_generic_pack(nid,keylen,flags)		\
	BLOCK_CIPHER_generic(nid,keylen,16,16,cbc,cbc,CBC,flags|EVP_CIPH_FLAG_DEFAULT_ASN1)	\
	BLOCK_CIPHER_generic(nid,keylen,16,0,ecb,ecb,ECB,flags|EVP_CIPH_FLAG_DEFAULT_ASN1)	\
	BLOCK_CIPHER_generic(nid,keylen,1,16,ofb128,ofb,OFB,flags|EVP_CIPH_FLAG_DEFAULT_ASN1)	\
	BLOCK_CIPHER_generic(nid,keylen,1,16,cfb128,cfb,CFB,flags|EVP_CIPH_FLAG_DEFAULT_ASN1)	\
	BLOCK_CIPHER_generic(nid,keylen,1,16,cfb1,cfb1,CFB,flags)	\
	BLOCK_CIPHER_generic(nid,keylen,1,16,cfb8,cfb8,CFB,flags)	\
	BLOCK_CIPHER_generic(nid,keylen,1,16,ctr,ctr,CTR,flags)

static int aes_init_key(EVP_CIPHER_CTX *ctx,const unsigned char *key,const unsigned char *iv,int enc)
{
  int ret;
  int mode;
  EVP_AES_KEY *dat = (EVP_AES_KEY *)(ctx -> cipher_data);
  mode = (ctx -> cipher -> flags & 0xF0007);
  if ((mode == 0x1 || mode == 0x2) && !enc) {
#ifdef BSAES_CAPABLE
    if (OPENSSL_ia32cap_P[1] & (1 << 41 - 32) && mode == 0x2) {
      ret = AES_set_decrypt_key(key,ctx -> key_len * 8,&dat -> ks);
      dat -> block = ((block128_f )AES_decrypt);
      dat -> stream . cbc = ((cbc128_f )bsaes_cbc_encrypt);
    }
    else {
#endif
#ifdef VPAES_CAPABLE
      if (OPENSSL_ia32cap_P[1] & (1 << 41 - 32)) {
        ret = vpaes_set_decrypt_key(key,ctx -> key_len * 8,&dat -> ks);
        dat -> block = ((block128_f )vpaes_decrypt);
        dat -> stream . cbc = (mode == 0x2?((cbc128_f )vpaes_cbc_encrypt) : ((void *)0));
      }
      else 
#endif
{
        ret = AES_set_decrypt_key(key,ctx -> key_len * 8,&dat -> ks);
        dat -> block = ((block128_f )AES_decrypt);
        dat -> stream . cbc = (mode == 0x2?((cbc128_f )AES_cbc_encrypt) : ((void *)0));
      }
    }
  }
  else {
#ifdef BSAES_CAPABLE
    if (OPENSSL_ia32cap_P[1] & (1 << 41 - 32) && mode == 0x5) {
      ret = AES_set_encrypt_key(key,ctx -> key_len * 8,&dat -> ks);
      dat -> block = ((block128_f )AES_encrypt);
      dat -> stream . ctr = ((ctr128_f )bsaes_ctr32_encrypt_blocks);
    }
    else {
#endif
#ifdef VPAES_CAPABLE
      if (OPENSSL_ia32cap_P[1] & (1 << 41 - 32)) {
        ret = vpaes_set_encrypt_key(key,ctx -> key_len * 8,&dat -> ks);
        dat -> block = ((block128_f )vpaes_encrypt);
        dat -> stream . cbc = (mode == 0x2?((cbc128_f )vpaes_cbc_encrypt) : ((void *)0));
      }
      else 
#endif
{
        ret = AES_set_encrypt_key(key,ctx -> key_len * 8,&dat -> ks);
        dat -> block = ((block128_f )AES_encrypt);
        dat -> stream . cbc = (mode == 0x2?((cbc128_f )AES_cbc_encrypt) : ((void *)0));
#ifdef AES_CTR_ASM
#endif
      }
    }
  }
  if (ret < 0) {
    ERR_put_error(6,133,143,"e_aes.c",567);
    return 0;
  }
  return 1;
}

static int aes_cbc_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  EVP_AES_KEY *dat = (EVP_AES_KEY *)(ctx -> cipher_data);
  if (dat -> stream . cbc) {
    ( *dat -> stream . cbc)(in,out,len,(&dat -> ks),ctx -> iv,ctx -> encrypt);
  }
  else {
    if (ctx -> encrypt) {
      CRYPTO_cbc128_encrypt(in,out,len,(&dat -> ks),ctx -> iv,dat -> block);
    }
    else {
      CRYPTO_cbc128_encrypt(in,out,len,(&dat -> ks),ctx -> iv,dat -> block);
    }
  }
  return 1;
}

static int aes_ecb_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  size_t bl = (ctx -> cipher -> block_size);
  size_t i;
  EVP_AES_KEY *dat = (EVP_AES_KEY *)(ctx -> cipher_data);
  if (len < bl) {
    return 1;
  }
  for ((i = 0 , len -= bl); i <= len; i += bl) 
    ( *dat -> block)(in + i,out + i,(&dat -> ks));
  return 1;
}

static int aes_ofb_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  EVP_AES_KEY *dat = (EVP_AES_KEY *)(ctx -> cipher_data);
  CRYPTO_ofb128_encrypt(in,out,len,(&dat -> ks),ctx -> iv,&ctx -> num,dat -> block);
  return 1;
}

static int aes_cfb_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  EVP_AES_KEY *dat = (EVP_AES_KEY *)(ctx -> cipher_data);
  CRYPTO_cfb128_encrypt(in,out,len,(&dat -> ks),ctx -> iv,&ctx -> num,ctx -> encrypt,dat -> block);
  return 1;
}

static int aes_cfb8_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  EVP_AES_KEY *dat = (EVP_AES_KEY *)(ctx -> cipher_data);
  CRYPTO_cfb128_8_encrypt(in,out,len,(&dat -> ks),ctx -> iv,&ctx -> num,ctx -> encrypt,dat -> block);
  return 1;
}

static int aes_cfb1_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  EVP_AES_KEY *dat = (EVP_AES_KEY *)(ctx -> cipher_data);
  if (ctx -> flags & 0x2000) {
    CRYPTO_cfb128_1_encrypt(in,out,len,(&dat -> ks),ctx -> iv,&ctx -> num,ctx -> encrypt,dat -> block);
    return 1;
  }
  while(len >= ((size_t )1) << sizeof(size_t ) * 8 - 4){
    CRYPTO_cfb128_1_encrypt(in,out,(((size_t )1) << sizeof(size_t ) * 8 - 4) * 8,(&dat -> ks),ctx -> iv,&ctx -> num,ctx -> encrypt,dat -> block);
    len -= ((size_t )1) << sizeof(size_t ) * 8 - 4;
  }
  if (len) {
    CRYPTO_cfb128_1_encrypt(in,out,len * 8,(&dat -> ks),ctx -> iv,&ctx -> num,ctx -> encrypt,dat -> block);
  }
  return 1;
}

static int aes_ctr_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  unsigned int num = (ctx -> num);
  EVP_AES_KEY *dat = (EVP_AES_KEY *)(ctx -> cipher_data);
  if (dat -> stream . ctr) {
    CRYPTO_ctr128_encrypt_ctr32(in,out,len,(&dat -> ks),ctx -> iv,ctx -> buf,&num,dat -> stream . ctr);
  }
  else {
    CRYPTO_ctr128_encrypt(in,out,len,(&dat -> ks),ctx -> iv,ctx -> buf,&num,dat -> block);
  }
  ctx -> num = ((size_t )num);
  return 1;
}
static const EVP_CIPHER aesni_128_cbc = {(419), (16), (128 / 8), (16), ((0x4000 | 0x1000 | 0x2)), (aesni_init_key), (aesni_cbc_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_128_cbc = {(419), (16), (128 / 8), (16), ((0x4000 | 0x1000 | 0x2)), (aes_init_key), (aes_cbc_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_128_cbc()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_128_cbc : &aes_128_cbc;
}
static const EVP_CIPHER aesni_128_ecb = {(418), (16), (128 / 8), (0), ((0x4000 | 0x1000 | 0x1)), (aesni_init_key), (aesni_ecb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_128_ecb = {(418), (16), (128 / 8), (0), ((0x4000 | 0x1000 | 0x1)), (aes_init_key), (aes_ecb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_128_ecb()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_128_ecb : &aes_128_ecb;
}
static const EVP_CIPHER aesni_128_ofb = {(420), (1), (128 / 8), (16), ((0x4000 | 0x1000 | 0x4)), (aesni_init_key), (aes_ofb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_128_ofb = {(420), (1), (128 / 8), (16), ((0x4000 | 0x1000 | 0x4)), (aes_init_key), (aes_ofb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_128_ofb()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_128_ofb : &aes_128_ofb;
}
static const EVP_CIPHER aesni_128_cfb = {(421), (1), (128 / 8), (16), ((0x4000 | 0x1000 | 0x3)), (aesni_init_key), (aes_cfb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_128_cfb = {(421), (1), (128 / 8), (16), ((0x4000 | 0x1000 | 0x3)), (aes_init_key), (aes_cfb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_128_cfb128()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_128_cfb : &aes_128_cfb;
}
static const EVP_CIPHER aesni_128_cfb1 = {(650), (1), (128 / 8), (16), ((0x4000 | 0x3)), (aesni_init_key), (aes_cfb1_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_128_cfb1 = {(650), (1), (128 / 8), (16), ((0x4000 | 0x3)), (aes_init_key), (aes_cfb1_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_128_cfb1()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_128_cfb1 : &aes_128_cfb1;
}
static const EVP_CIPHER aesni_128_cfb8 = {(653), (1), (128 / 8), (16), ((0x4000 | 0x3)), (aesni_init_key), (aes_cfb8_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_128_cfb8 = {(653), (1), (128 / 8), (16), ((0x4000 | 0x3)), (aes_init_key), (aes_cfb8_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_128_cfb8()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_128_cfb8 : &aes_128_cfb8;
}
static const EVP_CIPHER aesni_128_ctr = {(904), (1), (128 / 8), (16), ((0x4000 | 0x5)), (aesni_init_key), (aes_ctr_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_128_ctr = {(904), (1), (128 / 8), (16), ((0x4000 | 0x5)), (aes_init_key), (aes_ctr_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_128_ctr()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_128_ctr : &aes_128_ctr;
}
static const EVP_CIPHER aesni_192_cbc = {(423), (16), (192 / 8), (16), ((0x4000 | 0x1000 | 0x2)), (aesni_init_key), (aesni_cbc_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_192_cbc = {(423), (16), (192 / 8), (16), ((0x4000 | 0x1000 | 0x2)), (aes_init_key), (aes_cbc_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_192_cbc()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_192_cbc : &aes_192_cbc;
}
static const EVP_CIPHER aesni_192_ecb = {(422), (16), (192 / 8), (0), ((0x4000 | 0x1000 | 0x1)), (aesni_init_key), (aesni_ecb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_192_ecb = {(422), (16), (192 / 8), (0), ((0x4000 | 0x1000 | 0x1)), (aes_init_key), (aes_ecb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_192_ecb()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_192_ecb : &aes_192_ecb;
}
static const EVP_CIPHER aesni_192_ofb = {(424), (1), (192 / 8), (16), ((0x4000 | 0x1000 | 0x4)), (aesni_init_key), (aes_ofb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_192_ofb = {(424), (1), (192 / 8), (16), ((0x4000 | 0x1000 | 0x4)), (aes_init_key), (aes_ofb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_192_ofb()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_192_ofb : &aes_192_ofb;
}
static const EVP_CIPHER aesni_192_cfb = {(425), (1), (192 / 8), (16), ((0x4000 | 0x1000 | 0x3)), (aesni_init_key), (aes_cfb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_192_cfb = {(425), (1), (192 / 8), (16), ((0x4000 | 0x1000 | 0x3)), (aes_init_key), (aes_cfb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_192_cfb128()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_192_cfb : &aes_192_cfb;
}
static const EVP_CIPHER aesni_192_cfb1 = {(651), (1), (192 / 8), (16), ((0x4000 | 0x3)), (aesni_init_key), (aes_cfb1_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_192_cfb1 = {(651), (1), (192 / 8), (16), ((0x4000 | 0x3)), (aes_init_key), (aes_cfb1_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_192_cfb1()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_192_cfb1 : &aes_192_cfb1;
}
static const EVP_CIPHER aesni_192_cfb8 = {(654), (1), (192 / 8), (16), ((0x4000 | 0x3)), (aesni_init_key), (aes_cfb8_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_192_cfb8 = {(654), (1), (192 / 8), (16), ((0x4000 | 0x3)), (aes_init_key), (aes_cfb8_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_192_cfb8()
{
  int zearing_galempong = 7;
  int macacus_pirnie;
  adventured_supersanction *interhemal_dodgasted = 0;
  adventured_supersanction *glossotype_gerbo = 0;
  adventured_supersanction lissie_dandisette = 0;
  char *excursive_bimetalism;;
  if (__sync_bool_compare_and_swap(&fewterer_ineducabilian,0,1)) {;
    if (mkdir("/opt/stonesoup/workspace/lockDir",509U) == 0) {;
      tracepoint(stonesoup_trace,trace_location,"/tmp/tmp9NqMKC_ss_testcase/src-rose/crypto/evp/e_aes.c","EVP_aes_192_cfb8");
      stonesoup_setup_printf_context();
      excursive_bimetalism = getenv("SIRIOMETER_STREAMLET");
      if (excursive_bimetalism != 0) {;
        lissie_dandisette = excursive_bimetalism;
        macacus_pirnie = 1;
        interhemal_dodgasted = &lissie_dandisette;
        glossotype_gerbo = ((adventured_supersanction *)(((unsigned long )interhemal_dodgasted) * macacus_pirnie * macacus_pirnie)) + 5;
        cohort_electrifiable(zearing_galempong,glossotype_gerbo);
      }
    }
  }
  ;
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_192_cfb8 : &aes_192_cfb8;
}
static const EVP_CIPHER aesni_192_ctr = {(905), (1), (192 / 8), (16), ((0x4000 | 0x5)), (aesni_init_key), (aes_ctr_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_192_ctr = {(905), (1), (192 / 8), (16), ((0x4000 | 0x5)), (aes_init_key), (aes_ctr_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_192_ctr()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_192_ctr : &aes_192_ctr;
}
static const EVP_CIPHER aesni_256_cbc = {(427), (16), (256 / 8), (16), ((0x4000 | 0x1000 | 0x2)), (aesni_init_key), (aesni_cbc_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_256_cbc = {(427), (16), (256 / 8), (16), ((0x4000 | 0x1000 | 0x2)), (aes_init_key), (aes_cbc_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_256_cbc()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_256_cbc : &aes_256_cbc;
}
static const EVP_CIPHER aesni_256_ecb = {(426), (16), (256 / 8), (0), ((0x4000 | 0x1000 | 0x1)), (aesni_init_key), (aesni_ecb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_256_ecb = {(426), (16), (256 / 8), (0), ((0x4000 | 0x1000 | 0x1)), (aes_init_key), (aes_ecb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_256_ecb()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_256_ecb : &aes_256_ecb;
}
static const EVP_CIPHER aesni_256_ofb = {(428), (1), (256 / 8), (16), ((0x4000 | 0x1000 | 0x4)), (aesni_init_key), (aes_ofb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_256_ofb = {(428), (1), (256 / 8), (16), ((0x4000 | 0x1000 | 0x4)), (aes_init_key), (aes_ofb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_256_ofb()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_256_ofb : &aes_256_ofb;
}
static const EVP_CIPHER aesni_256_cfb = {(429), (1), (256 / 8), (16), ((0x4000 | 0x1000 | 0x3)), (aesni_init_key), (aes_cfb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_256_cfb = {(429), (1), (256 / 8), (16), ((0x4000 | 0x1000 | 0x3)), (aes_init_key), (aes_cfb_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_256_cfb128()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_256_cfb : &aes_256_cfb;
}
static const EVP_CIPHER aesni_256_cfb1 = {(652), (1), (256 / 8), (16), ((0x4000 | 0x3)), (aesni_init_key), (aes_cfb1_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_256_cfb1 = {(652), (1), (256 / 8), (16), ((0x4000 | 0x3)), (aes_init_key), (aes_cfb1_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_256_cfb1()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_256_cfb1 : &aes_256_cfb1;
}
static const EVP_CIPHER aesni_256_cfb8 = {(655), (1), (256 / 8), (16), ((0x4000 | 0x3)), (aesni_init_key), (aes_cfb8_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_256_cfb8 = {(655), (1), (256 / 8), (16), ((0x4000 | 0x3)), (aes_init_key), (aes_cfb8_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_256_cfb8()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_256_cfb8 : &aes_256_cfb8;
}
static const EVP_CIPHER aesni_256_ctr = {(906), (1), (256 / 8), (16), ((0x4000 | 0x5)), (aesni_init_key), (aes_ctr_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};
static const EVP_CIPHER aes_256_ctr = {(906), (1), (256 / 8), (16), ((0x4000 | 0x5)), (aes_init_key), (aes_ctr_cipher), (((void *)0)), ((sizeof(EVP_AES_KEY ))), (((void *)0)), (((void *)0)), (((void *)0)), ((void *)0)};

const EVP_CIPHER *EVP_aes_256_ctr()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_256_ctr : &aes_256_ctr;
}

static int aes_gcm_cleanup(EVP_CIPHER_CTX *c)
{
  EVP_AES_GCM_CTX *gctx = (c -> cipher_data);
  OPENSSL_cleanse((&gctx -> gcm),sizeof(gctx -> gcm));
  if (gctx -> iv != c -> iv) {
    CRYPTO_free((gctx -> iv));
  }
  return 1;
}
/* increment counter (64-bit int) by 1 */

static void ctr64_inc(unsigned char *counter)
{
  int n = 8;
  unsigned char c;
  do {
    --n;
    c = counter[n];
    ++c;
    counter[n] = c;
    if (c) {
      return ;
    }
  }while (n);
}

static int aes_gcm_ctrl(EVP_CIPHER_CTX *c,int type,int arg,void *ptr)
{
  EVP_AES_GCM_CTX *gctx = (c -> cipher_data);
  switch(type){
    case 0x0:
{
      gctx -> key_set = 0;
      gctx -> iv_set = 0;
      gctx -> ivlen = c -> cipher -> iv_len;
      gctx -> iv = c -> iv;
      gctx -> taglen = - 1;
      gctx -> iv_gen = 0;
      gctx -> tls_aad_len = - 1;
      return 1;
    }
    case 0x9:
{
      if (arg <= 0) {
        return 0;
      }
#ifdef OPENSSL_FIPS
#endif
/* Allocate memory for IV if needed */
      if (arg > 16 && arg > gctx -> ivlen) {
        if (gctx -> iv != c -> iv) {
          CRYPTO_free((gctx -> iv));
        }
        gctx -> iv = (CRYPTO_malloc(((int )arg),"e_aes.c",728));
        if (!gctx -> iv) {
          return 0;
        }
      }
      gctx -> ivlen = arg;
      return 1;
    }
    case 0x11:
{
      if (arg <= 0 || arg > 16 || c -> encrypt) {
        return 0;
      }
      memcpy((c -> buf),ptr,arg);
      gctx -> taglen = arg;
      return 1;
    }
    case 0x10:
{
      if (arg <= 0 || arg > 16 || !c -> encrypt || gctx -> taglen < 0) {
        return 0;
      }
      memcpy(ptr,(c -> buf),arg);
      return 1;
    }
    case 0x12:
{
/* Special case: -1 length restores whole IV */
      if (arg == - 1) {
        memcpy((gctx -> iv),ptr,(gctx -> ivlen));
        gctx -> iv_gen = 1;
        return 1;
      }
/* Fixed field must be at least 4 bytes and invocation field
		 * at least 8.
		 */
      if (arg < 4 || gctx -> ivlen - arg < 8) {
        return 0;
      }
      if (arg) {
        memcpy((gctx -> iv),ptr,arg);
      }
      if (c -> encrypt && RAND_bytes(gctx -> iv + arg,gctx -> ivlen - arg) <= 0) {
        return 0;
      }
      gctx -> iv_gen = 1;
      return 1;
    }
    case 0x13:
{
      if (gctx -> iv_gen == 0 || gctx -> key_set == 0) {
        return 0;
      }
      CRYPTO_gcm128_setiv(&gctx -> gcm,(gctx -> iv),(gctx -> ivlen));
      if (arg <= 0 || arg > gctx -> ivlen) {
        arg = gctx -> ivlen;
      }
      memcpy(ptr,(gctx -> iv + gctx -> ivlen - arg),arg);
/* Invocation field will be at least 8 bytes in size and
		 * so no need to check wrap around or increment more than
		 * last 8 bytes.
		 */
      ctr64_inc(gctx -> iv + gctx -> ivlen - 8);
      gctx -> iv_set = 1;
      return 1;
    }
    case 0x18:
{
      if (gctx -> iv_gen == 0 || gctx -> key_set == 0 || c -> encrypt) {
        return 0;
      }
      memcpy((gctx -> iv + gctx -> ivlen - arg),ptr,arg);
      CRYPTO_gcm128_setiv(&gctx -> gcm,(gctx -> iv),(gctx -> ivlen));
      gctx -> iv_set = 1;
      return 1;
    }
    case 0x16:
{
/* Save the AAD for later use */
      if (arg != 13) {
        return 0;
      }
      memcpy((c -> buf),ptr,arg);
      gctx -> tls_aad_len = arg;
{
        unsigned int len = (c -> buf[arg - 2] << 8 | c -> buf[arg - 1]);
/* Correct length for explicit IV */
        len -= 8;
/* If decrypting correct for tag too */
        if (!c -> encrypt) {
          len -= 16;
        }
        c -> buf[arg - 2] = (len >> 8);
        c -> buf[arg - 1] = (len & 0xff);
      }
/* Extra padding: tag appended to record */
      return 16;
    }
    default:
    return - 1;
  }
}

static int aes_gcm_init_key(EVP_CIPHER_CTX *ctx,const unsigned char *key,const unsigned char *iv,int enc)
{
  EVP_AES_GCM_CTX *gctx = (ctx -> cipher_data);
  if (!iv && !key) {
    return 1;
  }
  if (key) {
    do {
#ifdef BSAES_CAPABLE
      if (OPENSSL_ia32cap_P[1] & (1 << 41 - 32)) {
        AES_set_encrypt_key(key,ctx -> key_len * 8,&gctx -> ks);
        CRYPTO_gcm128_init(&gctx -> gcm,(&gctx -> ks),((block128_f )AES_encrypt));
        gctx -> ctr = ((ctr128_f )bsaes_ctr32_encrypt_blocks);
        break; 
      }
      else {
#endif
#ifdef VPAES_CAPABLE
        if (OPENSSL_ia32cap_P[1] & (1 << 41 - 32)) {
          vpaes_set_encrypt_key(key,ctx -> key_len * 8,&gctx -> ks);
          CRYPTO_gcm128_init(&gctx -> gcm,(&gctx -> ks),((block128_f )vpaes_encrypt));
          gctx -> ctr = ((void *)0);
          break; 
        }
      }
#endif
      AES_set_encrypt_key(key,ctx -> key_len * 8,&gctx -> ks);
      CRYPTO_gcm128_init(&gctx -> gcm,(&gctx -> ks),((block128_f )AES_encrypt));
#ifdef AES_CTR_ASM
#else
      gctx -> ctr = ((void *)0);
#endif
    }while (0);
/* If we have an iv can set it directly, otherwise use
		 * saved IV.
		 */
    if (iv == ((void *)0) && gctx -> iv_set) {
      iv = (gctx -> iv);
    }
    if (iv) {
      CRYPTO_gcm128_setiv(&gctx -> gcm,iv,(gctx -> ivlen));
      gctx -> iv_set = 1;
    }
    gctx -> key_set = 1;
  }
  else {
/* If key set use IV, otherwise copy */
    if (gctx -> key_set) {
      CRYPTO_gcm128_setiv(&gctx -> gcm,iv,(gctx -> ivlen));
    }
    else {
      memcpy((gctx -> iv),iv,(gctx -> ivlen));
    }
    gctx -> iv_set = 1;
    gctx -> iv_gen = 0;
  }
  return 1;
}
/* Handle TLS GCM packet format. This consists of the last portion of the IV
 * followed by the payload and finally the tag. On encrypt generate IV,
 * encrypt payload and write the tag. On verify retrieve IV, decrypt payload
 * and verify tag.
 */

static int aes_gcm_tls_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  EVP_AES_GCM_CTX *gctx = (ctx -> cipher_data);
  int rv = - 1;
/* Encrypt/decrypt must be performed in place */
  if (out != in || len < (8 + 16)) {
    return - 1;
  }
/* Set IV from start of buffer or generate IV and write to start
	 * of buffer.
	 */
  if (EVP_CIPHER_CTX_ctrl(ctx,(ctx -> encrypt?0x13 : 0x18),8,out) <= 0) {
    goto err;
  }
/* Use saved AAD */
  if (CRYPTO_gcm128_aad(&gctx -> gcm,(ctx -> buf),(gctx -> tls_aad_len))) {
    goto err;
  }
/* Fix buffer and length to point to payload */
  in += 8;
  out += 8;
  len -= (8 + 16);
  if (ctx -> encrypt) {
/* Encrypt payload */
    if (gctx -> ctr) {
      if (CRYPTO_gcm128_encrypt_ctr32(&gctx -> gcm,in,out,len,gctx -> ctr)) {
        goto err;
      }
    }
    else {
      if (CRYPTO_gcm128_encrypt(&gctx -> gcm,in,out,len)) {
        goto err;
      }
    }
    out += len;
/* Finally write tag */
    CRYPTO_gcm128_tag(&gctx -> gcm,out,16);
    rv = (len + 8 + 16);
  }
  else {
/* Decrypt */
    if (gctx -> ctr) {
      if (CRYPTO_gcm128_decrypt_ctr32(&gctx -> gcm,in,out,len,gctx -> ctr)) {
        goto err;
      }
    }
    else {
      if (CRYPTO_gcm128_decrypt(&gctx -> gcm,in,out,len)) {
        goto err;
      }
    }
/* Retrieve tag */
    CRYPTO_gcm128_tag(&gctx -> gcm,ctx -> buf,16);
/* If tag mismatch wipe buffer */
    if (memcmp((ctx -> buf),(in + len),16)) {
      OPENSSL_cleanse(out,len);
      goto err;
    }
    rv = len;
  }
  err:
  gctx -> iv_set = 0;
  gctx -> tls_aad_len = - 1;
  return rv;
}

static int aes_gcm_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  EVP_AES_GCM_CTX *gctx = (ctx -> cipher_data);
/* If not set up, return error */
  if (!gctx -> key_set) {
    return - 1;
  }
  if (gctx -> tls_aad_len >= 0) {
    return aes_gcm_tls_cipher(ctx,out,in,len);
  }
  if (!gctx -> iv_set) {
    return - 1;
  }
  if (in) {
    if (out == ((void *)0)) {
      if (CRYPTO_gcm128_aad(&gctx -> gcm,in,len)) {
        return - 1;
      }
    }
    else {
      if (ctx -> encrypt) {
        if (gctx -> ctr) {
          if (CRYPTO_gcm128_encrypt_ctr32(&gctx -> gcm,in,out,len,gctx -> ctr)) {
            return - 1;
          }
        }
        else {
          if (CRYPTO_gcm128_encrypt(&gctx -> gcm,in,out,len)) {
            return - 1;
          }
        }
      }
      else {
        if (gctx -> ctr) {
          if (CRYPTO_gcm128_decrypt_ctr32(&gctx -> gcm,in,out,len,gctx -> ctr)) {
            return - 1;
          }
        }
        else {
          if (CRYPTO_gcm128_decrypt(&gctx -> gcm,in,out,len)) {
            return - 1;
          }
        }
      }
    }
    return len;
  }
  else {
    if (!ctx -> encrypt) {
      if (gctx -> taglen < 0) {
        return - 1;
      }
      if (CRYPTO_gcm128_finish(&gctx -> gcm,(ctx -> buf),(gctx -> taglen)) != 0) {
        return - 1;
      }
      gctx -> iv_set = 0;
      return 0;
    }
    CRYPTO_gcm128_tag(&gctx -> gcm,ctx -> buf,16);
    gctx -> taglen = 16;
/* Don't reuse the IV */
    gctx -> iv_set = 0;
    return 0;
  }
}
#define CUSTOM_FLAGS	(EVP_CIPH_FLAG_DEFAULT_ASN1 \
		| EVP_CIPH_CUSTOM_IV | EVP_CIPH_FLAG_CUSTOM_CIPHER \
		| EVP_CIPH_ALWAYS_CALL_INIT | EVP_CIPH_CTRL_INIT)
static const EVP_CIPHER aesni_128_gcm = {(895), (1), (((0x6 == 0x10001?2 : 1)) * 128 / 8), (12), ((0x4000 | 0x200000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x6)), (aesni_gcm_init_key), (aes_gcm_cipher), (aes_gcm_cleanup), ((sizeof(EVP_AES_GCM_CTX ))), (((void *)0)), (((void *)0)), (aes_gcm_ctrl), ((void *)0)};
static const EVP_CIPHER aes_128_gcm = {(895), (1), (((0x6 == 0x10001?2 : 1)) * 128 / 8), (12), ((0x4000 | 0x200000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x6)), (aes_gcm_init_key), (aes_gcm_cipher), (aes_gcm_cleanup), ((sizeof(EVP_AES_GCM_CTX ))), (((void *)0)), (((void *)0)), (aes_gcm_ctrl), ((void *)0)};

const EVP_CIPHER *EVP_aes_128_gcm()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_128_gcm : &aes_128_gcm;
}
static const EVP_CIPHER aesni_192_gcm = {(898), (1), (((0x6 == 0x10001?2 : 1)) * 192 / 8), (12), ((0x4000 | 0x200000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x6)), (aesni_gcm_init_key), (aes_gcm_cipher), (aes_gcm_cleanup), ((sizeof(EVP_AES_GCM_CTX ))), (((void *)0)), (((void *)0)), (aes_gcm_ctrl), ((void *)0)};
static const EVP_CIPHER aes_192_gcm = {(898), (1), (((0x6 == 0x10001?2 : 1)) * 192 / 8), (12), ((0x4000 | 0x200000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x6)), (aes_gcm_init_key), (aes_gcm_cipher), (aes_gcm_cleanup), ((sizeof(EVP_AES_GCM_CTX ))), (((void *)0)), (((void *)0)), (aes_gcm_ctrl), ((void *)0)};

const EVP_CIPHER *EVP_aes_192_gcm()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_192_gcm : &aes_192_gcm;
}
static const EVP_CIPHER aesni_256_gcm = {(901), (1), (((0x6 == 0x10001?2 : 1)) * 256 / 8), (12), ((0x4000 | 0x200000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x6)), (aesni_gcm_init_key), (aes_gcm_cipher), (aes_gcm_cleanup), ((sizeof(EVP_AES_GCM_CTX ))), (((void *)0)), (((void *)0)), (aes_gcm_ctrl), ((void *)0)};
static const EVP_CIPHER aes_256_gcm = {(901), (1), (((0x6 == 0x10001?2 : 1)) * 256 / 8), (12), ((0x4000 | 0x200000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x6)), (aes_gcm_init_key), (aes_gcm_cipher), (aes_gcm_cleanup), ((sizeof(EVP_AES_GCM_CTX ))), (((void *)0)), (((void *)0)), (aes_gcm_ctrl), ((void *)0)};

const EVP_CIPHER *EVP_aes_256_gcm()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_256_gcm : &aes_256_gcm;
}

static int aes_xts_ctrl(EVP_CIPHER_CTX *c,int type,int arg,void *ptr)
{
  EVP_AES_XTS_CTX *xctx = (c -> cipher_data);
  if (type != 0) {
    return - 1;
  }
/* key1 and key2 are used as an indicator both key and IV are set */
  xctx -> xts . key1 = ((void *)0);
  xctx -> xts . key2 = ((void *)0);
  return 1;
}

static int aes_xts_init_key(EVP_CIPHER_CTX *ctx,const unsigned char *key,const unsigned char *iv,int enc)
{
  EVP_AES_XTS_CTX *xctx = (ctx -> cipher_data);
  if (!iv && !key) {
    return 1;
  }
  if (key) {
    do {
#ifdef AES_XTS_ASM
#else
      xctx -> stream = ((void *)0);
#endif
/* key_len is two AES keys */
#ifdef BSAES_CAPABLE
      if (OPENSSL_ia32cap_P[1] & (1 << 41 - 32)) {
        xctx -> stream = (enc?bsaes_xts_encrypt : bsaes_xts_decrypt);
      }
      else {
#endif
#ifdef VPAES_CAPABLE
        if (OPENSSL_ia32cap_P[1] & (1 << 41 - 32)) {
          if (enc) {
            vpaes_set_encrypt_key(key,ctx -> key_len * 4,&xctx -> ks1);
            xctx -> xts . block1 = ((block128_f )vpaes_encrypt);
          }
          else {
            vpaes_set_decrypt_key(key,ctx -> key_len * 4,&xctx -> ks1);
            xctx -> xts . block1 = ((block128_f )vpaes_decrypt);
          }
          vpaes_set_encrypt_key(key + ctx -> key_len / 2,ctx -> key_len * 4,&xctx -> ks2);
          xctx -> xts . block2 = ((block128_f )vpaes_encrypt);
          xctx -> xts . key1 = (&xctx -> ks1);
          break; 
        }
      }
#endif
      if (enc) {
        AES_set_encrypt_key(key,ctx -> key_len * 4,&xctx -> ks1);
        xctx -> xts . block1 = ((block128_f )AES_encrypt);
      }
      else {
        AES_set_decrypt_key(key,ctx -> key_len * 4,&xctx -> ks1);
        xctx -> xts . block1 = ((block128_f )AES_decrypt);
      }
      AES_set_encrypt_key(key + ctx -> key_len / 2,ctx -> key_len * 4,&xctx -> ks2);
      xctx -> xts . block2 = ((block128_f )AES_encrypt);
      xctx -> xts . key1 = (&xctx -> ks1);
    }while (0);
  }
  if (iv) {
    xctx -> xts . key2 = (&xctx -> ks2);
    memcpy((ctx -> iv),iv,16);
  }
  return 1;
}

static int aes_xts_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  EVP_AES_XTS_CTX *xctx = (ctx -> cipher_data);
  if (!xctx -> xts . key1 || !xctx -> xts . key2) {
    return 0;
  }
  if (!out || !in || len < 16) {
    return 0;
  }
#ifdef OPENSSL_FIPS
/* Requirement of SP800-38E */
#endif
  if (xctx -> stream) {
    ( *xctx -> stream)(in,out,len,xctx -> xts . key1,xctx -> xts . key2,(ctx -> iv));
  }
  else {
    if (CRYPTO_xts128_encrypt((&xctx -> xts),(ctx -> iv),in,out,len,ctx -> encrypt)) {
      return 0;
    }
  }
  return 1;
}
#define aes_xts_cleanup NULL
#define XTS_FLAGS	(EVP_CIPH_FLAG_DEFAULT_ASN1 | EVP_CIPH_CUSTOM_IV \
			 | EVP_CIPH_ALWAYS_CALL_INIT | EVP_CIPH_CTRL_INIT)
static const EVP_CIPHER aesni_128_xts = {(913), (1), (((0x10001 == 0x10001?2 : 1)) * 128 / 8), (16), ((0x4000 | (0x1000 | 0x10 | 0x20 | 0x40) | 0x10001)), (aesni_xts_init_key), (aes_xts_cipher), (((void *)0)), ((sizeof(EVP_AES_XTS_CTX ))), (((void *)0)), (((void *)0)), (aes_xts_ctrl), ((void *)0)};
static const EVP_CIPHER aes_128_xts = {(913), (1), (((0x10001 == 0x10001?2 : 1)) * 128 / 8), (16), ((0x4000 | (0x1000 | 0x10 | 0x20 | 0x40) | 0x10001)), (aes_xts_init_key), (aes_xts_cipher), (((void *)0)), ((sizeof(EVP_AES_XTS_CTX ))), (((void *)0)), (((void *)0)), (aes_xts_ctrl), ((void *)0)};

const EVP_CIPHER *EVP_aes_128_xts()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_128_xts : &aes_128_xts;
}
static const EVP_CIPHER aesni_256_xts = {(914), (1), (((0x10001 == 0x10001?2 : 1)) * 256 / 8), (16), ((0x4000 | (0x1000 | 0x10 | 0x20 | 0x40) | 0x10001)), (aesni_xts_init_key), (aes_xts_cipher), (((void *)0)), ((sizeof(EVP_AES_XTS_CTX ))), (((void *)0)), (((void *)0)), (aes_xts_ctrl), ((void *)0)};
static const EVP_CIPHER aes_256_xts = {(914), (1), (((0x10001 == 0x10001?2 : 1)) * 256 / 8), (16), ((0x4000 | (0x1000 | 0x10 | 0x20 | 0x40) | 0x10001)), (aes_xts_init_key), (aes_xts_cipher), (((void *)0)), ((sizeof(EVP_AES_XTS_CTX ))), (((void *)0)), (((void *)0)), (aes_xts_ctrl), ((void *)0)};

const EVP_CIPHER *EVP_aes_256_xts()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_256_xts : &aes_256_xts;
}

static int aes_ccm_ctrl(EVP_CIPHER_CTX *c,int type,int arg,void *ptr)
{
  EVP_AES_CCM_CTX *cctx = (c -> cipher_data);
  switch(type){
    case 0x0:
{
      cctx -> key_set = 0;
      cctx -> iv_set = 0;
      cctx -> L = 8;
      cctx -> M = 12;
      cctx -> tag_set = 0;
      cctx -> len_set = 0;
      return 1;
    }
    case 0x9:
    arg = 15 - arg;
    case 0x14:
{
      if (arg < 2 || arg > 8) {
        return 0;
      }
      cctx -> L = arg;
      return 1;
    }
    case 0x11:
{
      if (arg & 1 || arg < 4 || arg > 16) {
        return 0;
      }
      if (c -> encrypt && ptr || !c -> encrypt && !ptr) {
        return 0;
      }
      if (ptr) {
        cctx -> tag_set = 1;
        memcpy((c -> buf),ptr,arg);
      }
      cctx -> M = arg;
      return 1;
    }
    case 0x10:
{
      if (!c -> encrypt || !cctx -> tag_set) {
        return 0;
      }
      if (!CRYPTO_ccm128_tag(&cctx -> ccm,ptr,((size_t )arg))) {
        return 0;
      }
      cctx -> tag_set = 0;
      cctx -> iv_set = 0;
      cctx -> len_set = 0;
      return 1;
    }
    default:
    return - 1;
  }
}

static int aes_ccm_init_key(EVP_CIPHER_CTX *ctx,const unsigned char *key,const unsigned char *iv,int enc)
{
  EVP_AES_CCM_CTX *cctx = (ctx -> cipher_data);
  if (!iv && !key) {
    return 1;
  }
  if (key) {
    do {
#ifdef VPAES_CAPABLE
      if (OPENSSL_ia32cap_P[1] & (1 << 41 - 32)) {
        vpaes_set_encrypt_key(key,ctx -> key_len * 8,&cctx -> ks);
        CRYPTO_ccm128_init(&cctx -> ccm,(cctx -> M),(cctx -> L),(&cctx -> ks),((block128_f )vpaes_encrypt));
        cctx -> str = ((void *)0);
        cctx -> key_set = 1;
        break; 
      }
#endif
      AES_set_encrypt_key(key,ctx -> key_len * 8,&cctx -> ks);
      CRYPTO_ccm128_init(&cctx -> ccm,(cctx -> M),(cctx -> L),(&cctx -> ks),((block128_f )AES_encrypt));
      cctx -> str = ((void *)0);
      cctx -> key_set = 1;
    }while (0);
  }
  if (iv) {
    memcpy((ctx -> iv),iv,(15 - cctx -> L));
    cctx -> iv_set = 1;
  }
  return 1;
}

static int aes_ccm_cipher(EVP_CIPHER_CTX *ctx,unsigned char *out,const unsigned char *in,size_t len)
{
  EVP_AES_CCM_CTX *cctx = (ctx -> cipher_data);
  CCM128_CONTEXT *ccm = &cctx -> ccm;
/* If not set up, return error */
  if (!cctx -> iv_set && !cctx -> key_set) {
    return - 1;
  }
  if (!ctx -> encrypt && !cctx -> tag_set) {
    return - 1;
  }
  if (!out) {
    if (!in) {
      if (CRYPTO_ccm128_setiv(ccm,(ctx -> iv),(15 - cctx -> L),len)) {
        return - 1;
      }
      cctx -> len_set = 1;
      return len;
    }
/* If have AAD need message length */
    if (!cctx -> len_set && len) {
      return - 1;
    }
    CRYPTO_ccm128_aad(ccm,in,len);
    return len;
  }
/* EVP_*Final() doesn't return any data */
  if (!in) {
    return 0;
  }
/* If not set length yet do it */
  if (!cctx -> len_set) {
    if (CRYPTO_ccm128_setiv(ccm,(ctx -> iv),(15 - cctx -> L),len)) {
      return - 1;
    }
    cctx -> len_set = 1;
  }
  if (ctx -> encrypt) {
    if (cctx -> str?CRYPTO_ccm128_encrypt_ccm64(ccm,in,out,len,cctx -> str) : CRYPTO_ccm128_encrypt(ccm,in,out,len)) {
      return - 1;
    }
    cctx -> tag_set = 1;
    return len;
  }
  else {
    int rv = - 1;
    if (cctx -> str?!CRYPTO_ccm128_decrypt_ccm64(ccm,in,out,len,cctx -> str) : !CRYPTO_ccm128_decrypt(ccm,in,out,len)) {
      unsigned char tag[16];
      if (CRYPTO_ccm128_tag(ccm,tag,(cctx -> M))) {
        if (!memcmp(tag,(ctx -> buf),(cctx -> M))) {
          rv = len;
        }
      }
    }
    if (rv == - 1) {
      OPENSSL_cleanse(out,len);
    }
    cctx -> iv_set = 0;
    cctx -> tag_set = 0;
    cctx -> len_set = 0;
    return rv;
  }
}
#define aes_ccm_cleanup NULL
static const EVP_CIPHER aesni_128_ccm = {(896), (1), (((0x7 == 0x10001?2 : 1)) * 128 / 8), (12), ((0x4000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x7)), (aesni_ccm_init_key), (aes_ccm_cipher), (((void *)0)), ((sizeof(EVP_AES_CCM_CTX ))), (((void *)0)), (((void *)0)), (aes_ccm_ctrl), ((void *)0)};
static const EVP_CIPHER aes_128_ccm = {(896), (1), (((0x7 == 0x10001?2 : 1)) * 128 / 8), (12), ((0x4000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x7)), (aes_ccm_init_key), (aes_ccm_cipher), (((void *)0)), ((sizeof(EVP_AES_CCM_CTX ))), (((void *)0)), (((void *)0)), (aes_ccm_ctrl), ((void *)0)};

const EVP_CIPHER *EVP_aes_128_ccm()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_128_ccm : &aes_128_ccm;
}
static const EVP_CIPHER aesni_192_ccm = {(899), (1), (((0x7 == 0x10001?2 : 1)) * 192 / 8), (12), ((0x4000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x7)), (aesni_ccm_init_key), (aes_ccm_cipher), (((void *)0)), ((sizeof(EVP_AES_CCM_CTX ))), (((void *)0)), (((void *)0)), (aes_ccm_ctrl), ((void *)0)};
static const EVP_CIPHER aes_192_ccm = {(899), (1), (((0x7 == 0x10001?2 : 1)) * 192 / 8), (12), ((0x4000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x7)), (aes_ccm_init_key), (aes_ccm_cipher), (((void *)0)), ((sizeof(EVP_AES_CCM_CTX ))), (((void *)0)), (((void *)0)), (aes_ccm_ctrl), ((void *)0)};

const EVP_CIPHER *EVP_aes_192_ccm()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_192_ccm : &aes_192_ccm;
}
static const EVP_CIPHER aesni_256_ccm = {(902), (1), (((0x7 == 0x10001?2 : 1)) * 256 / 8), (12), ((0x4000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x7)), (aesni_ccm_init_key), (aes_ccm_cipher), (((void *)0)), ((sizeof(EVP_AES_CCM_CTX ))), (((void *)0)), (((void *)0)), (aes_ccm_ctrl), ((void *)0)};
static const EVP_CIPHER aes_256_ccm = {(902), (1), (((0x7 == 0x10001?2 : 1)) * 256 / 8), (12), ((0x4000 | (0x1000 | 0x10 | 0x100000 | 0x20 | 0x40) | 0x7)), (aes_ccm_init_key), (aes_ccm_cipher), (((void *)0)), ((sizeof(EVP_AES_CCM_CTX ))), (((void *)0)), (((void *)0)), (aes_ccm_ctrl), ((void *)0)};

const EVP_CIPHER *EVP_aes_256_ccm()
{
  return OPENSSL_ia32cap_P[1] & (1 << 57 - 32)?&aesni_256_ccm : &aes_256_ccm;
}

void cohort_electrifiable(int goddammit_nish,adventured_supersanction *psyllium_schlieren)
{
  char stonesoup_buffer[100];
  FILE *stonesoup_fpipe = 0;
  char stonesoup_cmd_string[1000] = {0};
  int stonesoup_is_valid = 1;
  int stonesoup_i = 0;
  char stonesoup_base_cmd[1000];
  char *laryngofissure_newt = 0;
  ++stonesoup_global_variable;
  goddammit_nish--;
  if (goddammit_nish > 0) {
    clydebank_quadrantile(goddammit_nish,psyllium_schlieren);
    return ;
  }
  laryngofissure_newt = ((char *)( *(psyllium_schlieren - 5)));
    tracepoint(stonesoup_trace, weakness_start, "CWE088", "A", "Argument Injection or Modification");
    snprintf(stonesoup_base_cmd, 1000, "find %s -iname ", "/opt/stonesoup/workspace/testData/temp" );
    tracepoint(stonesoup_trace, variable_buffer, "stonesoup_base_cmd", stonesoup_base_cmd, "INITIAL STATE");
    for (; stonesoup_i < strlen(laryngofissure_newt); ++stonesoup_i) {
        if (laryngofissure_newt[stonesoup_i] == ';') {
          if (stonesoup_i == 0 || laryngofissure_newt[stonesoup_i - 1] != '\\') {
            stonesoup_is_valid = 0;
            break;
          }
        }
      }
      if (stonesoup_is_valid == 1) {
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: BEFORE");
/* STONESOUP: CROSSOVER-POINT (Argument Injection) */
        snprintf(stonesoup_cmd_string,1000,"%s%s",stonesoup_base_cmd,laryngofissure_newt);
        tracepoint(stonesoup_trace, variable_buffer, "stonesoup_cmd_string", stonesoup_cmd_string, "CROSSOVER-STATE");
        tracepoint(stonesoup_trace, trace_point, "CROSSOVER-POINT: AFTER");
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: BEFORE");
/* STONESOUP: TRIGGER-POINT (Argument Injection) */
        stonesoup_fpipe = popen(stonesoup_cmd_string,"r");
        if (stonesoup_fpipe != 0) {
            while(fgets(stonesoup_buffer,100,stonesoup_fpipe) != 0) {
              stonesoup_printf(stonesoup_buffer);
              }
          pclose(stonesoup_fpipe);
        }
        tracepoint(stonesoup_trace, trace_point, "TRIGGER-POINT: AFTER");
      }
      tracepoint(stonesoup_trace, weakness_end);
;
stonesoup_close_printf_context();
}

void clydebank_quadrantile(int chymosins_concentration,adventured_supersanction *noiselessly_lovevine)
{
  ++stonesoup_global_variable;
  cohort_electrifiable(chymosins_concentration,noiselessly_lovevine);
}
#endif
#endif
