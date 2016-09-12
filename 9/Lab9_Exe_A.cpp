//  Lab9_Exe_A.cpp
//  Exercise A - Lab 9
//  Created by M. Moussavi on 2015-03-17.
//

#include <iostream>
using namespace std;

class Location{
private:
    const char* Loc;
public:
    Location (const char* L);
    void print();
};

Location::Location(const char* L): Loc(L){}

void Location::print(){
    cout << "The value of location is: " << Loc;
}


class CelestiaBody{
private:
    double r;
    Location L;
public:
    CelestiaBody(double a, const char* m );
    double getRadius()const;
    const char* getName() const;
    void print();
};

CelestiaBody::CelestiaBody(double x, const char* m ): L(m){
    r = x;
}

void CelestiaBody::print(){
    cout << "\nThe value of r is: "<< r << endl;
    L.print();
}


class Planet{
private:
    char& type;
public:
    Planet(char& c);
    void print();
};

void Planet::print(){
    cout << "The planet type is: " << type;
    
    // POINT TWO
}

Planet::Planet(char& c): type(c) { /* POINT ONE */ }


class Sun: public CelestiaBody{
private:
    static int n;
    Planet plt;
    double pi;
public:
    Sun(double x, char& ch):CelestiaBody(x, "SKY"), plt(ch){ pi = 3.14;}
    static int getN() { return n;}
    void print();
};

void Sun::print(){
    CelestiaBody::print();
    cout << "\nThe value of pi is: " << pi << endl;
    cout  << "The value of n is: " << n << endl;
    plt.print();
}

int main()
{
    char c = 'D';
    Sun sun(39, c);
    
    sun.print();
    
    return 0;
}


int Sun::n = 2;
