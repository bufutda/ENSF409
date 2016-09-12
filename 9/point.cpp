// File: point.cpp
// Author: Mitchell Sawatzky
// Date: March 27, 2016
// Class: ENSF 409

#include "point.h"
#include <iostream>
#include <cmath>
using namespace std;

int Point::idIndex = 1001;
int Point::objectCount = 0;

Point::Point (double x, double y) {
    this->x = x;
    this->y = y;
    this->id = Point::idIndex;
    Point::objectCount++;
    Point::idIndex++;
}

Point::~Point () {
    Point::objectCount--;
}

void Point::display () const {
    cout << "  X-coordinate: " << this->x << endl;
    cout << "  Y-coordinate: " << this->y << endl;
}

double Point::getX () const {
    return this->x;
}

double Point::getY () const {
    return this->y;
}

double Point::getID () const {
    return this->id;
}

void Point::setX (double x) {
    this->x = x;
}

void Point::setY (double y) {
    this->y = y;
}

int Point::counter () {
    return Point::objectCount;
}

double Point::distance (const Point& other) const {
    return sqrt(pow(other.getX() - getX(), 2) + pow(other.getY() - getY(), 2));
}

double Point::distance (const Point& a, const Point& b) {
    return sqrt(pow(b.getX() - a.getX(), 2) + pow(b.getY() - a.getY(), 2));
}
