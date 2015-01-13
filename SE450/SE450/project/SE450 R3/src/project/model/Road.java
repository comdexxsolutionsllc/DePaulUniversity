
//**********************************************************
// Assignment: Final Project - SE450
//
// Author: 
//
// File: Road.java
// 
// Date: 
//
// Change Log:  None
//
// Notes: SE450 Included Source
//*********************************************************


package project.model;
import java.util.List;
import java.util.ArrayList;

/**
 * A road holds cars.
 */
public class Road {
	Road() {
	} // Created only by this package

	private List<Car> _cars = new ArrayList<Car>();

	public void accept(Car d) {
		if (d == null) {
			throw new IllegalArgumentException();
		}
		_cars.add(d);
	}

	public List<Car> getCars() {
		return _cars;
	}
}
