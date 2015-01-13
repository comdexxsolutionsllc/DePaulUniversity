
//**********************************************************
// Assignment: Final Project - SE450
//
// Author: 
//
// File: UI.java 
// 
// Date: 
//
// Change Log:  None
//
// Notes: SE450 Included Source
//*********************************************************


package project.ui;

public interface UI {
	public void processMenu(UIMenu menu);

	public String[] processForm(UIForm form);

	public void displayMessage(String message);

	public void displayError(String message);
}
