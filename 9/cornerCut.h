// File: cornerCut.h
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#ifndef CORNERCUT_H
#define CORNERCUT_H

#include "circle.h"
#include "rectangle.h"

class CornerCut : public Circle, public Rectangle {
public:
    CornerCut (double x, double y, double sideA, double sideB, double radius, const char* name);

    // PROMISES: Calculates the area of the shape
    double area ();

    // PROMISES: Calculates the perimeter of the shape
    double perimeter ();

    // PROMISES: Outputs the shape data to stdout
    void display ();
};

#endif
