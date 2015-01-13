
//**********************************************************
// Assignment: Final Project - SE450
//
// Author: 
//
// File: Light.java
// 
// Date: 
//
// Change Log:  None
//
// Notes: SE450 Included Source
//*********************************************************


package project.model;

/**
 * A light has a boolean state.
 */
public class Light implements Agent {
	Light() {
	} // Created only by this package

	private boolean _state;

	public boolean getState() {
		return _state;
	}

	public void run(double time) {
		if (time % 40 == 0) {
			_state = !_state;
		}
	}
}
