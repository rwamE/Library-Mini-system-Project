/*
* Student Name: Rwambibi Emmanuella
* Lab Professor: Leanne Seaward
* Due Date: no due date
* Modified: January , 2024.
* Description:
*/
package system.exception;

public class BookException extends Exception{
	public BookException(String errorMessage) {
        super(errorMessage);
        System.err.println("BookException: " + errorMessage);
	}
}
