// LookupTable.h
// ENEL 409 - WINTER 2004
// Completed by: M. Moussavi

#ifndef LOOKUPTABLE_H
#define LOOKUPTABLE_H
#include <iostream>

using namespace std;


// class LookupTable: GENERAL CONCEPTS
//
//    key/datum pairs are ordered.  The first pair is the pair with
//    the lowest key, the second pair is the pair with the second
//    lowest key, and so on.  This implies that you must be able to
//    compare two keys with the < operator.
//
//    Each LookupTable has an embedded iterator class that allows users
//    of the class to traverse trhough the list and  have acess to each
//    node.

#include "customer.h"

//    In this version of the LookupTable a new struct type called Pair
//    is introduced which represents a key/data pair.

template <class V, class T> class LookupTable;

template <class V, class T> struct Pair {
    //constructor
    Pair (V keyA, T datumA) : key(keyA), datum(datumA) {}

    V key;
    T datum;
};

template <class V, class T> class LT_Node {
    friend class LookupTable<V, T>;
    friend class Iterator;
private:
    Pair<V, T> pairM;
    LT_Node<V, T> *nextM;

    // This ctor should be convenient in insert and copy operations.
    LT_Node (const Pair<V, T>& pairA, LT_Node<V, T> *nextA);
};

template <class V, class T> class LookupTable {
public:
    class Iterator {
        friend class LookupTable;
        LookupTable *LT;
        LT_Node<T, V>* cursor;

    public:
        Iterator () : LT(0) {}
        Iterator (LookupTable & x) : LT(&x) {}
        const T& operator* ();
        const T& operator++ ();
        const T& operator++ (int);
        int operator! ();

        void step_fwd () {
            assert(LT->cursor_ok());
            LT->step_fwd();
        }
    };

    LookupTable ();
    LookupTable (const LookupTable& source);
    LookupTable& operator= (const LookupTable& rhs);
    ~LookupTable();

    LookupTable& begin ();

    int size () const;
    // PROMISES: Returns number of keys in the table.

    int cursor_ok () const;
    // PROMISES:
    //   Returns 1 if the cursor is attached to a key/datum pair,
    //   and 0 if the cursor is in the off-list state.

    const V& cursor_key () const;
    // REQUIRES: cursor_ok()
    // PROMISES: Returns key of key/datum pair to which cursor is attached.

    const T& cursor_datum () const;
    // REQUIRES: cursor_ok()
    // PROMISES: Returns datum of key/datum pair to which cursor is attached.

    void insert (const Pair<V, T>& pariA);
    // PROMISES:
    //   If keyA matches a key in the table, the datum for that
    //   key is set equal to datumA.
    //   If keyA does not match an existing key, keyA and datumM are
    //   used to create a new key/datum pair in the table.
    //   In either case, the cursor goes to the off-list state.

    void remove (const V& keyA);
    // PROMISES:
    //   If keyA matches a key in the table, the corresponding
    //   key/datum pair is removed from the table.
    //   If keyA does not match an existing key, the table is unchanged.
    //   In either case, the cursor goes to the off-list state.

    void find (const V& keyA);
    // PROMISES:
    //   If keyA matches a key in the table, the cursor is attached
    //   to the corresponding key/datum pair.
    //   If keyA does not match an existing key, the cursor is put in
    //   the off-list state.

    void go_to_first ();
    // PROMISES: If size() > 0, cursor is moved to the first key/datum pair
    //   in the table.

    void step_fwd ();
    // REQUIRES: cursor_ok()
    // PROMISES:
    //   If cursor is at the last key/datum pair in the list, cursor
    //   goes to the off-list state.
    //   Otherwise the cursor moves forward from one pair to the next.

    void make_empty ();
    // PROMISES: size() == 0.


    friend  ostream& operator << <V, T> (ostream& os,const LookupTable<V, T>& lt);

private:
    int sizeM;
    LT_Node<V, T> *headM;
    LT_Node<V, T> *cursorM;

    void destroy();
    // Deallocate all nodes, set headM to zero.

    void copy(const LookupTable& source);
    // Establishes *this as a copy of source.  Cursor of *this will
    // point to the twin of whatever the source's cursor points to.
};

#endif

template <class V, class T> LookupTable<V, T>& LookupTable<V, T>::begin () {
    cursorM = headM;
    return *this;
}

template <class V, class T> LT_Node<V, T>::LT_Node (const Pair<V, T>& pairA, LT_Node<V, T> *nextA)
: pairM(pairA), nextM(nextA) {
}

template <class V, class T> LookupTable<V, T>::LookupTable ()
: sizeM(0), headM(0), cursorM(0) {
}

template <class V, class T> LookupTable<V, T>::LookupTable (const LookupTable<V, T>& source) {
    copy(source);
}

template <class V, class T> LookupTable<V, T>& LookupTable<V, T>::operator= (const LookupTable<V, T>& rhs) {
    if (this != &rhs) {
        destroy();
        copy(rhs);
    }
    return *this;
}

template <class V, class T> LookupTable<V, T>::~LookupTable () {
    destroy();
}

template <class V, class T> int LookupTable<V, T>::size () const {
    return sizeM;
}

template <class V, class T> int LookupTable<V, T>::cursor_ok () const {
    return cursorM != 0;
}

template <class V, class T> const V& LookupTable<V, T>::cursor_key () const {
    assert(cursor_ok());
    return cursorM->pairM.key;
}

template <class V, class T> const T& LookupTable<V, T>::cursor_datum () const {
    assert(cursor_ok());
    return cursorM->pairM.datum;
}

template <class V, class T> void LookupTable<V, T>::insert (const Pair<V, T>& pairA) {
    // Add new node at head?
    if (headM == 0 || pairA.key < headM->pairM.key) {
        headM = new LT_Node<V, T>(pairA, headM);
        sizeM++;
    } else if (pairA.key == headM->pairM.key) {
        headM->pairM.datum = pairA.datum;
    } else {
        LT_Node<V, T>* before= headM;
        LT_Node<V, T>* after=headM->nextM;

        while(after!=NULL && (pairA.key > after->pairM.key)) {
            before=after;
            after=after->nextM;
        }

        if(after!=NULL && pairA.key == after->pairM.key) {
            after->pairM.datum = pairA.datum;
        } else {
            before->nextM = new LT_Node<V, T>(pairA, before->nextM);
            sizeM++;
        }
    }
}

template <class V, class T> void LookupTable<V, T>::remove (const V& keyA) {
    if (headM == 0 || keyA < headM->pairM.key)
        return;

    LT_Node<V, T>* doomed_node = 0;
    if (keyA == headM->pairM.key) {
        doomed_node = headM;
        headM = headM->nextM;
        sizeM--;
    } else {
        LT_Node<V, T> *before = headM;
        LT_Node<V, T> *maybe_doomed = headM->nextM;
        while(maybe_doomed != 0 && keyA > maybe_doomed->pairM.key) {
            before = maybe_doomed;
            maybe_doomed = maybe_doomed->nextM;
        }

        if (maybe_doomed != 0 && maybe_doomed->pairM.key == keyA) {
            doomed_node = maybe_doomed;
            before->nextM = maybe_doomed->nextM;
            sizeM--;
        }
    }
    delete doomed_node;           // Does nothing if doomed_node == 0.
}

// This place-holder for find was added in order to
// allow successful linking when you're testing insert and remove.
// Replace it with a definition that works.

template <class V, class T> void LookupTable<V, T>::find (const V& keyA) {
    LT_Node<V, T> *ptr=headM;
    while (ptr!=NULL && ptr->pairM.key != keyA) {
        ptr=ptr->nextM;
    }
    cursorM = ptr;
}

template <class V, class T> void LookupTable<V, T>::go_to_first () {
    cursorM = headM;
}

template <class V, class T> void LookupTable<V, T>::step_fwd () {
    assert(cursor_ok());
    cursorM = cursorM->nextM;
}

template <class V, class T> void LookupTable<V, T>::make_empty () {
    destroy();
    sizeM = 0;
    cursorM = 0;
}

template <class V, class T> void LookupTable<V, T>::destroy () {
    LT_Node<V, T> *ptr = headM;
    while (ptr != NULL) {
        headM=headM->nextM;
        delete ptr;
        ptr=headM;
    }
    cursorM = NULL;
    sizeM=0;
}

template <class V, class T> void LookupTable<V, T>::copy(const LookupTable<V, T>& source) {
    headM=0;
    cursorM =0;

    if(source.headM ==0)
        return;


    for(LT_Node<V, T> *p = source.headM; p != 0; p=p->nextM) {
        insert(Pair<V, T>(p->pairM.key, p->pairM.datum));
        if(source.cursorM == p)
        find(p->pairM.key);
    }
}

template <class V, class T> ostream& operator << (ostream& os, const LookupTable<V, T>& lt) {
    if (lt.cursor_ok())
        os << lt.cursor_key() << "  " << lt.cursor_datum();
    else
        os<<"Not Found.";

    return os;
}

template <class V, class T> const T& LookupTable<V, T>::Iterator::operator* () {
    assert(LT ->cursor_ok());
    return LT->cursor_datum();
}

template <class V, class T> const T& LookupTable<V, T>::Iterator::operator++ () {
    assert(LT->cursor_ok());
    const T & x = LT->cursor_datum();
    LT->step_fwd();
    return x;
}

template <class V, class T> const T& LookupTable<V, T>::Iterator::operator++ (int) {
    assert(LT->cursor_ok());

    LT->step_fwd();
    return LT->cursor_datum();
}


template <class V, class T> int LookupTable<V, T>::Iterator::operator! () {
    return (LT->cursor_ok());
}