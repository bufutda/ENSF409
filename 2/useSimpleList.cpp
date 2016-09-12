// lab2_EXE_C.cpp
// Started by: M. Moussavi"

#include <iostream>
#include <iomanip>
using namespace std;
#include "SimpleList.h"
#define EXERCISE_B

void print(SimpleList& list);
// PROMISES: prints values in the list from first node (node number 0) to
//           the last node.

int main()
{
    SimpleList list;
    
    cout << "\nList just after creation -- is empty.";
    
    list.push_front(50);
    cout << "\nAfter calling push_front. list must have: 50\n";
    print(list);
    
    list.push_back(440);
    
    list.set(0,770);
    cout << "\nAfter calling push_back and set function list must have: 770  440\n";
    print(list);
    
    list.push_back(330);
    list.push_back(220);
    list.push_back(110);
    
    cout << "\nAfter three more calls to push_back, list must have:"
    "770, 440, 330, 220, 110\n";
    print(list);
    
    list.remove(0);
    list.remove(2);
    cout << "\nAfter removing two nodes. list must have: 440, 330, 110\n";
    print(list);
    list.insert(40, 3); //insert node with the value of 40 at the 4th position
    list.insert(20, -1); // do nothing
    list.insert(30, 30000); // do nothing
    list.insert(10, 0); //insert node with the value of 10 at the 1st position
    list.insert(33, 2); // insert node with the value 33 at the 3rd position
    
    cout << "\nTwo  more nodes inserted, must have: 10, 440, 33, 330, 110, 40\n";
    print(list);
    
    list.remove(0);
    list.remove(1);
    list.remove(2);
    list.remove(3);
    list.remove(4);
    list.remove(5);
    cout << "\nAfter 6 removes, list must have: 440, 330, 40: \n";
    print(list);
    
    list.clear();
    cout << "\nAfter call to clear, list must be empty:\n";
    print(list);
    
    list.push_back(331);
    list.push_back(221);
    list.push_back(111);
    
    cout << "\nAfter three calls to push_back, list must have:"
    "331, 221, 111\n";
    print(list);
    
    
    
    return 0;
    
}


void print(SimpleList& list)
{
    for(int i = 0; i < list.size(); i++)
        cout << list.get(i) << "  ";
}