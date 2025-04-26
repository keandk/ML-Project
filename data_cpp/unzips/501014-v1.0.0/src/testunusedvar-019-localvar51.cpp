 struct Token { const Token* next() const; }; const Token* nameToken();
void foo(const Token *var) {
  const Token *tok = nameToken();
  tok = tok->next(); // Variable 'tok' is assigned a value that is never used.
}
