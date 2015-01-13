
//**********************************************************
// Assignment: Final Project - SE450
//
// Author:
//
// File: UIFactory.java
// 
// Date: 
//
// Change Log:  None
//
// Notes: SE450 Included Source
//*********************************************************


package project.ui;

public class UIFactory {
	private UIFactory() {
	}

	static private UI _UI = new PopupUI();

	// static private UI _UI = new TextUI();
	static public UI ui() {
		return _UI;
	}
}
