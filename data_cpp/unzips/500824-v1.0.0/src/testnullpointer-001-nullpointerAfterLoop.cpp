#include <string>
 struct Token { const Token *next() const; std::string str() const; };
void foo(const Token *tok)
{
    while (tok);
    tok = tok->next(); // Either the condition 'tok' is redundant or there is possible null pointer dereference: tok.
}
