
package system.user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import system.book.Book;
import system.book.BookList;
import system.exception.BookException;
import system.exception.UserException;

public class UserList {
	Scanner input = new Scanner(System.in);
	ArrayList <User>users = new ArrayList<User>();

	public void addUser(User user) {
		users.add(user);
	}

	public void loadUserList(){
		for(User user : users) {
			System.out.println(user.toString());
		}
	}

	public void saveUserList (String file) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (User user : users) {
				bw.write(user.toString() + "\n");
			}
		} catch (IOException e) {
			throw new IOException("Error writing to file: " + file);
		}

	}
	public void loginUser(String username, String pass) throws UserException {
		boolean userFound = false;

		for (User user : users) {
			if (user.getEmail().equals(username)) {
				userFound = true;
				if (user.getPass().equals(pass)) {
					System.out.println("User " + username + " logged in successfully.");
					return;
				} else {
					throw new UserException("Wrong password");
				}
			}
		}

		if (!userFound) {
			throw new UserException("Username unrecognized");
		}
	}

	public void changePass(String username, String newPass) throws UserException {
		boolean userFound = false;

		for (User user : users) {
			if (user.getEmail().equals(username)) {
				userFound = true;
				if(userFound) {
					user.setPassword(newPass);
				}else {
					throw new UserException("Failed to change password");
				}
				}
				return;
			} 
		if (!userFound) {
			throw new UserException("Username unrecognized");
		}
	}
	
	

}
