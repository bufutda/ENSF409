// File: square.h
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#ifndef SQUARE_H
#define SQUARE_H
#include "shape.h"

class Square: virtual public Shape {
public:
    Square (double x, double y, double side, const char* name);

    // PROMISES: Calculates the area of the shape
    virtual double area ();

    // PROMISES: Calculates the perimeter of the shape
    virtual double perimeter ();

    // PROMISES: Gets the side_a of the shape
    double getSideA ();

    // PROMISES: Sets the side_a of the shape to the new value
    void setSideA (double side);

    // PROMISES: Outputs the shape data to stdout
    virtual void display ();
private:
    double side_a;
};

#endif
