// File: rectangle.h
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#ifndef RECTANGLE_H
#define RECTANGLE_H
#include "square.h"

class Rectangle: public Square {
public:
    Rectangle (double x, double y, double side_a, double side_b, const char* name);

    // PROMISES: Calculates the area of the shape
    double area ();

    // PROMISES: Calculates the perimeter of the shape
    double perimeter ();

    // PROMISES: Gets the side_b of the shaep
    double getSideB ();

    // PROMISES: Sets side_b to the new value
    void setSideB (double b);

    // PROMISES: Outputs the shape data to stdout
    void display ();
private:
    double side_b;
};

#endif
