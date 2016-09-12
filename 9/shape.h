// File: shape.h
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#ifndef SHAPE_H
#define SHAPE_H
#include "point.h"

class Shape {
public:
    Shape (double x, double y, const char* name);
    Shape (const Shape& src);
    ~Shape ();
    Shape& operator= (const Shape& rhs);

    // PROMISES: Gets the origin point
    const Point& getOrigin () const;

    // PROMISES: Gets the name of the shape
    char* getName () const;

    // PROMISES: Outputs shape data to stdout
    virtual void display () const;

    // PROMISES: Calculates the distance between the shape and another shape
    double distance (Shape& other);

    // PROMISES: Calculates the distance between the shape and another shape
    static double distance (Shape& the_shape, Shape& other);

    // PROMISES: Moves the shape by dx and dy
    void move (double dx, double dy);
private:
    Point origin;
    char* shapeName;
};

#endif
