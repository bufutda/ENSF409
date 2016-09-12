// File: rectangle.cpp
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#include "rectangle.h"
#include <iostream>
using namespace std;

Rectangle::Rectangle (double x, double y, double side_a, double side_b, const char* name)
: Square(x, y, side_a, name), Shape(x, y, name) {
    this->side_b = side_b;
}

double Rectangle::area () {
    return side_b * getSideA();
}

double Rectangle::perimeter () {
    return (2 * side_b) + (2 * getSideA());
}

double Rectangle::getSideB () {
    return side_b;
}

void Rectangle::setSideB (double b) {
    side_b = b;
}

void Rectangle::display () {
    cout << "  Rectangle Name: " << getName() << endl;
    getOrigin().display();
}
