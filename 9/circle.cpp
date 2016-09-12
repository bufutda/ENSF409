// File: circle.cpp
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#include "circle.h"
#include <iostream>
using namespace std;

Circle::Circle (double x, double y, double radius, const char* name)
: Shape(x, y, name) {
    this->radius = radius;
}

double Circle::area () {
    return M_PI * radius * radius;
}

double Circle::perimeter () {
    return 2 * M_PI * radius;
}

double Circle::getRadius () {
    return radius;
}

void Circle::setRadius (double radius) {
    this->radius = radius;
}

void Circle::display () {
    cout << "  Circle Name: " << getName() << endl;
    getOrigin().display();
}
