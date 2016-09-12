// File: cornerCut.cpp
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#include "cornerCut.h"
#include <iostream>
using namespace std;

CornerCut::CornerCut (double x, double y, double sideA, double sideB, double radius, const char* name)
: Circle(x, y, radius, name), Rectangle(x, y, sideA, sideB, name), Shape(x, y, name) {
    if (radius > sideA || radius > sideB) {
        cerr << "Error: Cannot create a CornerCut object with radius larger than width" << endl;
        exit(1);
    }
}

double CornerCut::area () {
    return Rectangle::area() - (0.25 * Circle::area());
}

double CornerCut::perimeter () {
    return Rectangle::perimeter() - (2 * getRadius()) + (0.25 * Circle::perimeter());
}

void CornerCut::display () {
    cout << "  CornerCut Name: " << Circle::getName() << endl;
    Circle::getOrigin().display();
    if (getSideA() >= getSideB()) {
        cout << "  Width: " << getSideB() << endl;
        cout << "  Length: " << getSideA() << endl;
    } else {
        cout << "  Width: " << getSideA() << endl;
        cout << "  Length: " << getSideB() << endl;
    }
    cout << "  Radius of the cut: " << getRadius() << endl;
}
