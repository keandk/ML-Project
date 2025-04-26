#include <cstddef>
 struct Entry { Entry *next; }; Entry *buckets[10];
void foo() {
  Entry *entry, *nextEntry;
  for(int i = 0; i < 10; i++) {
    for(entry = buckets[i]; entry != NULL; entry = nextEntry) { // Uninitialized variable: nextEntry
    }
  }
}

