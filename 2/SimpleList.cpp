// SimpleList.cpp
//  Created by Mahmood Moussavi on 2014-11-09.

// Note: This SimpleList is designed for the purpose of a simple class
//       exercise for ENSF 409 and doesn't follow the law of Big-3 (causes
//       problems if you try to copy objects of class SimpleList

#include <iostream>
using namespace std;

#include <stdlib.h>
#include "SimpleList.h"



int SimpleList::get(int n)
{
    if(n < 0 || n >= sizeM)
    {
        cout << "\n Illigal Access. program Terminates...";
        exit(1);
    }
    
    Node * p = headM;
    for(int i= 0; i < n; i++)
        p = p -> next;
    
    
    return p -> item;
}

void SimpleList::set(int n, int v)
{
    if(n < 0 || n >= sizeM)
    {
        cout << "\n Illigal Access. program Terminates...";
    }
    
    Node * p = headM;
    for(int i= 0; i < n; i++)
        p = p -> next;

    
    p->item = v;
}


void SimpleList::push_back(const int& item)
{
    Node *new_node = new Node;
    if(new_node == NULL)
    {
        cout << "\nNo memory available to create a node" << endl;
        exit(1);
    }
    
    new_node->item = item;
    
    if (headM == 0) {
        new_node->next = headM;
        headM = new_node;
    }
    else
    {
        Node* p = headM;
        while (p ->next  != NULL)
            p = p ->next;
        
        p -> next = new_node;
        new_node -> next = NULL;
    }
    sizeM++;
}


void SimpleList::push_front(const int& item)
{
    Node *new_node = new Node;
    new_node->item = item;
    new_node->next = headM;
    headM = new_node;
    sizeM++;

}

void SimpleList::insert(const int& itemA, int n)
{
    if(n < 0 || n > sizeM)
        return;
    else if(n == 0)
        push_front(itemA);
    else if(n == sizeM) 
        push_back(itemA);
    else{
        Node *new_node = new Node;
        if (new_node == NULL) {
            cout << "Sorry memory is unavailable to create an new node.\n";
            return;
        }
        new_node->item = itemA;
        
        
        Node *before = headM;      // will point to node in front of new node
        Node *after = headM->next; // will be 0 or point to node after new node
        int i = 1;
        while(i < n)
        {
            before = after;
            after = after->next;
            i++;
        }
        new_node->next = after;
        before->next = new_node;
        sizeM++;
    }

}

void SimpleList::clear()
{
    Node* p = headM;
    
    while(p)
    {
        headM = headM -> next;
        delete p;
        p = headM;
    }

    headM = 0;
    sizeM = 0;;
}

void SimpleList::remove(int n)
{
    if (headM == 0 || n < 0 || n >= sizeM)
        return;
    Node * be_deleted;
    Node* before;
    
    if(n == 0)
    {
        be_deleted = headM;
        headM = headM -> next;
    }
    else
    {
        before = headM;
        be_deleted = before -> next;
        
        int i = 1;
        while (i < n)
        {
            before = be_deleted;
            be_deleted = before -> next;
            i++;
        }
        
        before -> next = be_deleted -> next;

    }
    delete be_deleted;
    sizeM--;
}




