struct pc_data {
    struct {
        char   * strefa;
    } wampiryzm;
};
void f() {
    struct pc_data *pcdata;
    if ( *pcdata->wampiryzm.strefa == '\0' ) { } // Uninitialized variable: pcdata
}
