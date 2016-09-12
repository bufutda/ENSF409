// File: circle.h
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#ifndef CIRCLE_H
#define CIRCLE_H
#include "shape.h"

const double M_PI = 3.1416;

class Circle: virtual public Shape {
public:
    Circle (double x, double y, double radius, const char* name);

    // PROMISES: Calculate the area of the shape and return it
    double area ();

    // PROMISES: Calculate the area of the shape and return it
    double perimeter ();

    // PROMISES: Gets the radius and returns it
    double getRadius ();

    // PROMISES: Sets the radius to the new value
    void setRadius (double radius);

    // PROMISES: Outputs shape data to stdout
    void display ();
private:
    double radius;
};

#endif
