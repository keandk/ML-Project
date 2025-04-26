 struct PoolItem { bool operator!=(const PoolItem&) const; };
void f(int x, const PoolItem& rPool) {
    const PoolItem* pItem;
    while (x > 0) {
        if (*pItem != rPool) // Uninitialized variable: pItem
        { }
        x--;
    }
}
