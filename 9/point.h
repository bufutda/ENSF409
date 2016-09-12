// File: point.h
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#ifndef POINT_H
#define POINT_H

class Point {
public:
    Point (double x, double y);
    ~Point ();

    // PROMISES: Outputs point data to stdout
    void display () const;

    // PROMISES: Gets the x coordinate
    double getX () const;

    // PROMISES: Gets the Y coordinate
    double getY () const;

    // PROMISES: Gets the ID of the object
    double getID () const;

    // PROMISES: Sets the x coordinate to the new value
    void setX (double x);

    // PROMISES: Sets the y coordinate to the new value
    void setY (double y);

    // PROMISES: Calculates the distance between this object and another
    double distance (const Point& other) const;

    // PROMISES: Calculates the distance between two objects
    static double distance (const Point& a, const Point& b);

    // PROMISES: Returns the total number of point objects in memory
    static int counter ();
private:
    double x;
    double y;
    int id;
    static int idIndex;
    static int objectCount;
};

#endif
