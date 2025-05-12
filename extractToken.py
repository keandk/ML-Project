import re

def extract_and_replace_tokens(code):
    """
    Tokenizes code by replacing identifiers, function names, and string literals with placeholders.
    
    This function handles several categories of string literals differently:
    
    1. Special content that is preserved as-is:
       - HTML/XML content (starting with '<' or containing tags)
       - MIME types (starting with 'text/' or 'application/')
       - URLs (containing 'http://' or 'https://' or 'ftp://' or standard domain patterns)
       - SQL queries (containing keywords like SELECT, INSERT, etc.)
       - JSON-like structures (with proper brackets and key-value pairs)
       
    2. Regular string literals:
       - Tokenized as STR1, STR2, etc.
       
    3. String arrays:
       - In the subgraph_building_&_embedding.py script, additional post-processing
         ensures string literals in array initializers are properly tokenized as STR1, STR2, etc.
    
    Args:
        code (str): The input code string to tokenize
        
    Returns:
        str: The tokenized code with identifiers, functions, and strings replaced with placeholders
    """
    # Reserved keywords in C/C++/Java
    keywords = {
        # C keywords
        "auto", "break", "case", "char", "const", "continue", "default", "do", "double",
        "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register",
        "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef",
        "union", "unsigned", "void", "volatile", "while",
        
        # C++ additional keywords
        "asm", "bool", "catch", "class", "const_cast", "delete", "dynamic_cast",
        "explicit", "export", "false", "friend", "inline", "mutable", "namespace",
        "new", "operator", "private", "protected", "public", "reinterpret_cast",
        "static_cast", "template", "this", "throw", "true", "try", "typeid",
        "typename", "using", "virtual", "wchar_t",
        
        # Java keywords
        "abstract", "assert", "boolean", "byte", "extends", "final", "finally",
        "implements", "import", "instanceof", "interface", "native", "null", "package",
        "strictfp", "super", "synchronized", "throws", "transient",
        "Object", "module", "open", "opens", "permits", "provides", "record", 
        "requires", "sealed", "to", "transitive", "uses", "var", "with", "yield",
        
        # Java built-in methods
        "equals", "toString", "hashCode", "getClass", "clone", "finalize", "wait",
        "notify", "notifyAll", "length", "charAt", "substring", "indexOf", "lastIndexOf",
        "startsWith", "endsWith", "trim", "toLowerCase", "toUpperCase", "compareTo",
        "compareToIgnoreCase", "contains", "isEmpty", "replace", "split", "join",
        "getParameter", "setAttribute", "getAttribute", "removeAttribute", "printStackTrace",
        "getMessage", "append", "delete", "insert", "reverse", "setLength",
        
        # C standard library functions
        "printf", "scanf", "malloc", "free", "calloc", "realloc", "memcpy", "memmove",
        "strcpy", "strncpy", "strcat", "strncat", "strcmp", "strncmp", "strlen",
        "fopen", "fclose", "fread", "fwrite", "fprintf", "fscanf", "fgets", "fputs",
        "qsort", "bsearch", "exit", "abort", "system",
        
        # C++ standard library functions/methods
        "cout", "cin", "endl", "push_back", "pop_back", "begin", "end", "size",
        "empty", "clear", "find", "erase", "insert", "at", "front", "back",
        "make_pair", "first", "second", "resize", "reserve", "capacity",
        "substr", "c_str", "data", "assign", "swap", "sort", "reverse",
        "max_size", "shrink_to_fit", "emplace", "emplace_back"
    }

    # Control-flow keywords: don't treat as functions
    control_flow_keywords = {
        "if", "for", "while", "switch", "catch", "throw", "return", 
        "synchronized", "instanceof", "try", "else"
    }

    variable_map = {}
    function_map = {}
    string_map = {}
    variable_counter = 1
    function_counter = 1
    string_counter = 1

    # Special handling for dots in URLs
    # Save the original code before we add spaces around symbols
    original_code = code
    
    # 1. Add spaces around symbols including square brackets
    code = re.sub(r'([{}()\[\];,\.])', r' \1 ', code)
    code = re.sub(r'\s+', ' ', code).strip()

    # 2. Split tokens while preserving string literals
    tokens = []
    current_pos = 0
    while current_pos < len(code):
        if code[current_pos] == '"':
            # Find the end of the string
            end_pos = current_pos + 1
            while end_pos < len(code) and code[end_pos] != '"':
                if code[end_pos] == '\\':
                    end_pos += 2
                else:
                    end_pos += 1
            if end_pos < len(code):
                string_content = code[current_pos:end_pos+1]
                string_value = string_content.strip('"')
                
                # For special content detection, we need to use the original string
                # without the extra spaces inserted around punctuation
                orig_start = original_code.find(string_value)
                if orig_start >= 0:
                    orig_string_value = string_value
                else:
                    # If we can't find the exact string, it might have dots and other punctuation
                    # Try to find it by matching the string literal boundaries
                    quote_pattern = re.escape(string_content[0]) + r'(.*?)' + re.escape(string_content[-1])
                    orig_match = re.search(quote_pattern, original_code)
                    if orig_match:
                        orig_string_value = orig_match.group(1)
                    else:
                        orig_string_value = string_value  # fallback
                
                # Patterns indicating special content that should be preserved as-is
                is_special_content = False
                
                # 1. Markup/template content (HTML, XML, JSP)
                if (orig_string_value.startswith('<') or 
                    orig_string_value.endswith('>') or
                    re.search(r'<\/?[a-zA-Z][^>]*>', orig_string_value)):
                    is_special_content = True
                
                # 2. MIME types
                elif orig_string_value.startswith('text/') or orig_string_value.startswith('application/'):
                    is_special_content = True
                
                # 3. URLs
                elif (re.search(r'^https?://', orig_string_value) or 
                      re.search(r'^ftp://', orig_string_value) or
                      re.search(r'www\.[a-zA-Z0-9-]+\.[a-zA-Z]{2,}', orig_string_value)):
                    # For URLs, use the original string from the code instead of the tokenized version
                    is_special_content = True
                    # Reconstruct the proper URL string with quotes
                    string_content = '"' + orig_string_value + '"'
                
                # 4. SQL queries
                elif re.search(r'\b(SELECT|INSERT|UPDATE|DELETE|CREATE|DROP)\b', orig_string_value, re.IGNORECASE):
                    is_special_content = True
                    # Use original SQL string
                    string_content = '"' + orig_string_value + '"'
                
                # 5. JSON-like structures
                elif (orig_string_value.startswith('{') and orig_string_value.endswith('}')) or \
                     (orig_string_value.startswith('[') and orig_string_value.endswith(']')) or \
                     re.search(r'["\']\s*:\s*["\']', orig_string_value):
                    is_special_content = True
                    # Use original JSON string
                    string_content = '"' + orig_string_value + '"'
                
                # If it's special content, preserve it as-is
                if is_special_content:
                    tokens.append(string_content)
                else:
                    # Regular string literal - tokenize as STR
                    if string_content not in string_map:
                        string_map[string_content] = f"STR{string_counter}"
                        string_counter += 1
                    tokens.append(string_map[string_content])
                current_pos = end_pos + 1
            else:
                tokens.append(code[current_pos])
                current_pos += 1
        else:
            match = re.match(r'\s*([^\s"]+)', code[current_pos:])
            if match:
                tokens.append(match.group(1))
                current_pos += len(match.group(0))
            else:
                current_pos += 1

    # 3. Process tokens
    processed_tokens = []
    i = 0
    while i < len(tokens):
        token = tokens[i]

        # If it's already a mapped string token (STRx), keep it
        if re.match(r'^STR\d+$', token):
            processed_tokens.append(token)
        # If it's a quoted string, keep it as is
        elif token.startswith('"') and token.endswith('"'):
            processed_tokens.append(token)
        # If it looks like an identifier
        elif re.match(r'^[a-zA-Z_][a-zA-Z0-9_]*$', token):
            if (i + 1 < len(tokens)) and (tokens[i + 1] == '('):
                # Check if it's NOT a control-flow keyword
                if token not in control_flow_keywords:
                    if token not in keywords and token not in function_map:
                        function_map[token] = f"FUN{function_counter}"
                        function_counter += 1
                    processed_tokens.append(function_map.get(token, token))
                else:
                    processed_tokens.append(token)
            else:
                if token not in keywords and token not in variable_map and token not in function_map:
                    variable_map[token] = f"VAR{variable_counter}"
                    variable_counter += 1
                processed_tokens.append(variable_map.get(token, token))
        else:
            processed_tokens.append(token)

        i += 1

    return ' '.join(processed_tokens)

# Example usage
code = """
ChannelBuffer buf;
        try {
            if (response instanceof XContentRestResponse) {
                XContentBuilder builder = ((XContentRestResponse) response).builder();
                if (response.contentThreadSafe()) {
                    buf = builder.bytes().toChannelBuffer();
                } else {
                    buf = builder.bytes().copyBytesArray().toChannelBuffer();
                }
            } else {
                if (response.contentThreadSafe()) {
                    buf = ChannelBuffers.wrappedBuffer(response.content(), response.contentOffset(), response.contentLength());
                } else {
                    buf = ChannelBuffers.copiedBuffer(response.content(), response.contentOffset(), response.contentLength());
                }
            }
        }
"""
