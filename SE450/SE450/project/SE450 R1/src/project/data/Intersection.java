package project.data;

/**
 * @author Joshua S Abbott <joshuastevenabbott@gmail.com>
 * 
 */
public interface Intersection {
	/*
	 * returns the light at facing NS
	 */
	Light getNSlight();

	/*
	 * returns the light facing EW
	 */

	Light getEWlight();
}
