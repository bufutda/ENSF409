#include <iostream>
#include <assert.h>
#include "mystring2.h"

using namespace std;

template <class T> class Vector {
public:
    class VectIter {
        friend class Vector;
        private:
            Vector *v; // points to a vector object of type T
            int index; // represents the subscript number of the vector's
                       // array.
        public:
        VectIter(Vector& x);

        T operator++();
        //PROMISES: increments the iterator's indes and return the
        //          value of the element at the index position. If
        //          index exceeds the size of the array it will
        //          be set to zero. Which means it will be circulated
        //          back to the first element of the vector.

        T  operator++(int);
        // PRIMISES: returns the value of the element at the index
        //           position, then increments the index. If
        //           index exceeds the size of the array it will
        //           be set to zero. Which means it will be circulated
        //           back to the first element of the vector.

        T  operator--();
        // PROMISES: decrements the iterator index, and return the
        //           the value of the element at the index. If
        //           index is less than zero it will be set to the
        //           last element in the aray. Which means it will be
        //           circulated to the last element of the vector.

        T  operator--(int);
        // PRIMISES: returns the value of the element at the index
        //           position, then decrements the index. If
        //           index is less than zero it will be set to the
        //           last element in the aray. Which means it will be
        //           circulated to the last element of the vector.

        T operator *();
        // PRIMISES: returns the value of the element at the current
        //           index position.

    };

    Vector(int sz);
    ~Vector();

    T& operator[](int i);
    // PRIMISES: returns existing value in the ith element of
    //           array or sets a new value to  the ith element in
    //           array.

    void ascending_sort();
    // PRIMISES: sorts the vector values in ascending order.

private:
    T *array;               // points to the first element of an array of T
    int size;                 // size of array
    void swap(T&, T&);   // swaps the values of two elements in array
};


template <class T> void Vector<T>::ascending_sort () {
    for(int i=0; i< size-1; i++)
        for(int j=i+1; j < size; j++)
            if(array[i] > array[j])
                swap(array[i], array[j]);
}
template <> void Vector<Mystring>::ascending_sort () {
    for(int i=0; i< size-1; i++)
        for(int j=i+1; j < size; j++)
            if(array[i].isGreater(array[j]))
                swap(array[i], array[j]);
}
template <> void Vector<char*>::ascending_sort () {
    for(int i=0; i< size-1; i++)
        for(int j=i+1; j < size; j++)
            if(strcmp(array[i], array[j]) > 0)
                swap(array[i], array[j]);
}

template <class T> void Vector<T>::swap (T& a, T& b) {
    T tmp = a;
    a = b;
    b = tmp;
}

template <class T> Vector<T>::VectIter::VectIter (Vector& x) {
    v = &x;
    index = 0;
}

template <class T> Vector<T>::Vector(int sz) {
    size=sz;
    array = new T [sz];
    assert (array != NULL);
}

template <class T> Vector<T>::~Vector() {
    delete [] array;
    array = NULL;
}

template <class T> T& Vector<T>::operator[] (int i) {
  return array[i];
}

template <class T> T Vector<T>::VectIter::operator* () {
    return v -> array[index];
}

template <class T> T Vector<T>::VectIter::operator++ () {
    // cout << "\n(size: " << v->size << ")";
    if (v->size == (index + 1)) {
        // cout << "resetting index\n";
        index = 0;
    } else {
        // cout << "index to " << index + 1 << "\n";
        index++;
    }
    return v->array[index];
}

template <class T> T Vector<T>::VectIter::operator++ (int) {
    // cout << "\n(size: " << v->size << ")";
    T ret = v->array[index];
    if (v->size == (index + 1)) {
        // cout << "resetting index\n";
        index = 0;
    } else {
        // cout << "index to " << index + 1 << "\n";
        index++;
    }
    return ret;
}

template <class T> T Vector<T>::VectIter::operator-- () {
    // cout << "\n(size: " << v->size << ")";
    if (index == 0) {
        index = v->size - 1;
        // cout << "maxing index to " << index << endl;
    } else {
        index--;
        // cout << "setting index to " << index << endl;
    }
    return v->array[index];
}

template <class T> T Vector<T>::VectIter::operator-- (int) {
    // cout << "\n(size: " << v->size << ")";
    T ret = v->array[index];
    if (index == 0) {
        index = v->size - 1;
        // cout << "maxing index to " << index << endl;
    } else {
        index--;
        // cout << "setting index to " << index << endl;
    }
    return ret;
}


int main() {
    Mystring mys(5);
    Vector<int> x(3);
    x[0] = 999;
    x[1] = -77;
    x[2] = 88;
    Vector<int>::VectIter iter(x);
    cout << "\n\nThe first element of vector x contains: " << *iter;
    // the code between the  #if 0 and #endif is ignored by
    // compiler. If you change it to #if 1, it will be compiled
    #if 1
        cout << "\nTesting an <int> Vector: " << endl;;

        cout << "\n\nTesting sort";
        x.ascending_sort();

        for (int i=0; i<3 ; i++)
            cout << endl << iter++;

        cout << "\n\nTesting Prefix --:";
        for (int i=0; i<3 ; i++)
            cout << endl << --iter;

        cout << "\n\nTesting Prefix ++:";
        for (int i=0; i<3 ; i++)
            cout << endl << ++iter;

        cout << "\n\nTesting Postfix --";
        for (int i=0; i<3 ; i++)
            cout << endl << iter--;

        cout << endl;

        cout << "Testing a <String> Vector: " << endl;
        Vector<Mystring> y(3);
        y[0] = "Bar";
        y[1] = "Foo";
        y[2] = "All";;

        Vector<Mystring>::VectIter iters(y);

        cout << "\n\nTesting sort";
        y.ascending_sort();

        for (int i=0; i<3 ; i++)
            cout << endl << iters++;

        cout << "\n\nTesting Prefix --:";
        for (int i=0; i<3 ; i++)
            cout << endl << --iters;

        cout << "\n\nTesting Prefix ++:";
        for (int i=0; i<3 ; i++) {
            cout << endl << ++iters;
        }

        cout << "\n\nTesting Postfix --";
        for (int i=0; i<3 ; i++)
            cout << endl << iters--;

        cout << endl; cout << "Testing a <char *> Vector: " << endl;
        Vector<char*> z(3);
        z[0] = (char*)"Orange";
        z[1] = (char*)"Pear";
        z[2] = (char*)"Apple";

        Vector<char*>::VectIter iterchar(z);

        cout << "\n\nTesting sort";
        z.ascending_sort();

        for (int i=0; i<3 ; i++)
            cout << endl << iterchar++;

    #endif
	cout << "\nPrgram Terminated Successfully." << endl;
	return 0;
}
