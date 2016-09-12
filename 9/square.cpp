// File: square.cpp
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#include "square.h"
#include <iostream>
using namespace std;

Square::Square (double x, double y, double side, const char* name)
: Shape(x, y, name) {
    side_a = side;
}

double Square::area () {
    return side_a * side_a;
}

double Square::perimeter () {
    return 4 * side_a;
}

double Square::getSideA () {
    return side_a;
}

void Square::setSideA (double side) {
    side_a = side;
}

void Square::display () {
    cout << "  Square Name: " << getName() << endl;
    getOrigin().display();
}
