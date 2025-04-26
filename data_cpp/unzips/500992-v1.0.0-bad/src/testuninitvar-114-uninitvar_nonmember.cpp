struct Foo {
  int bar;
};

int main() {
  Foo* foo;
  foo->bar = 3; // Uninitialized variable: foo
}
