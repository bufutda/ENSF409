//
//  SimpleList.h
//  Created by Mahmood Moussavi on 2014-11-09.
//
// Note: This SimpleList is designed for the purpose of a simple class
//       exercise for ENSF 409 and doesn't follow the law of Big-3 (causes
//       problems if you try to copy objects of class SimpleList

#ifndef Lab9_Simple_List__SimpleList__
#define Lab9_Simple_List__SimpleList__



class SimpleList {
private:
    
    class Node {
    public:
        int item;
        Node *next;
    };
    
    Node *headM;
    int sizeM;
    
    
public:
    
    SimpleList(): headM(0), sizeM(0) { /* Point one */}
    // PROMISES: creates empty list.

    
    ~SimpleList() { clear(); }   // destructor
    
    int size() const {return sizeM;}
    
    void push_back(const int& item);
    // PROMISES:
    //    Adds a node with an item to the end of the list, and increments sizeM
    
    void push_front(const int& item);
    // PROMISES:
    //    Adds a node with an item to the beginning of the list, and increments
    //    sizeM
    void pop_back(const int& item);
    // PROMISES:
    //    The last node int list is removed
    
    int get(int n);
    // PROMISES:
    //    An item is return at the nth position in the list.
    //    if n is less than 0 or greater than or equal sizeM gives the error
    //    message: "Illegal Access" and terminates the program

    void set(int i, int v);
    // PROMISES:
    //    puts the value of v at the i-th position
    
    
    void insert(const int& theItem, int n);
    // PROMISES:
    //    A node with a copy of theItem is inserted at the nth position, and sizeM
    //    will be incremented if the operation of insert was successfull.
    //    if n == sizeM calles push_back
    //    if n == 0 calls push_front
    //    if n < 0 or n > sizeM returns and does nothing.
    
    void remove(int n);
    //  PROMISES:
    //    Does nothing if n < 0 or n > sizeM-1. Otherwise, if list is not empty
    //    removes the node at the position n.
    
    void clear();
    // Deallocate all nodes, and sets headM to zero, and size to zero
};

#endif /* defined(Lab9_Simple_List__SimpleList__) */
