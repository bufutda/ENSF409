// File: shape.cpp
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#include "shape.h"
#include "point.h"
#include <iostream>
using namespace std;

Shape::Shape (double x, double y, const char* name) : origin(x, y) {
    shapeName = new char[strlen(name) + 1];
    strcpy(shapeName, name);
}

Shape::Shape (const Shape& src) : origin(src.getOrigin().getX(), src.getOrigin().getY()) {
    shapeName = new char[strlen(src.getName()) + 1];
    strcpy(shapeName, src.getName());
}

Shape::~Shape () {
    delete [] shapeName;
}

Shape& Shape::operator= (const Shape& rhs) {
    if (this != &rhs) {
        if (shapeName) {
            delete [] shapeName;
        }
        origin = Point(rhs.getOrigin().getX(), rhs.getOrigin().getY());
        shapeName = new char[strlen(rhs.getName()) + 1];
        strcpy(shapeName, rhs.getName());
    }
    return *this;
}

const Point& Shape::getOrigin () const {
    return origin;
}

char* Shape::getName () const {
    return shapeName;
}

void Shape::display () const {
    cout << "  Shape Name: " << shapeName << endl;
    origin.display();
}

double Shape::distance (Shape& other) {
    return Point::distance(origin, other.getOrigin());
}

double Shape::distance (Shape& the_shape, Shape& other) {
    return Point::distance(the_shape.getOrigin(), other.getOrigin());
}

void Shape::move (double dx, double dy) {
    origin.setX(origin.getX() + dx);
    origin.setY(origin.getY() + dy);
}
