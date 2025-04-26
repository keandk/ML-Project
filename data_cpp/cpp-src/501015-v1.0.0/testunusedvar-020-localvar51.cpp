 struct Token { const Token* next() const; }; const Token* nameToken();
void foo() {
  int x = 4;
  x = 15 + x; // Variable 'x' is assigned a value that is never used.
}
