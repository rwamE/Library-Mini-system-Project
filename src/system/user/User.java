
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
import system.util.SystemUtil;

public class User {
	private String email;
	private String password;
	ArrayList<Book> userBookList= new ArrayList<>();
	BookList bookList = new BookList();
	public UserPlan plan = new UserPlan();
	Scanner input = new Scanner(System.in);

	public User(String email, String password, UserPlan plan) {
		this.email=email;
		this.password=password;
		this.plan= plan;

	}

	public User() {}

	public User createUser(String ...strings)throws UserException{
		//Check String validity
		for(String s : strings) {
			if(!SystemUtil.isValid(s)) {
				throw new UserException("Invalid strings at Create User. Expected 2 args");
			}
		}
		email = strings[0];
		password = strings[1];
		String string1 = strings[2];
		String string2 = strings[3];
		
		plan = plan.createPlan(string1,string2);
		
		return new User(email,password,plan);
	}

	public void addToBooklist(int index) throws UserException, BookException {
		  if (plan.isActive) {
			  boolean bookFound = false;
		        for (Book book : bookList.getBestsellers()) {
		            if (book.getIndex() == index) {
		                userBookList.add(book);
		                bookFound = true;
		                break; 
		            }
		        }
		        if (!bookFound) {
		            throw new BookException("User can't add book: Book not found");
		        }
		    } else {
		        throw new UserException("User is not active: Can't add book");
		    }
	}

	public String findBookByIndex(int index) {
		for(Book book : userBookList) {
			if(book.getIndex() == index) {
				return book.toString();
			}
		}return null;
	}

	public void displayBooklist() {
		for (Book book : userBookList) {
			System.out.println(book.toString());
		}
	}

	public void saveList() throws BookException, IOException {
		System.out.println("Enter where to save: ");
		String file=input.nextLine();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (Book book : userBookList) {
				bw.write(book.toString() + "\n");
			}
		} catch (IOException e) {
			throw new IOException("Error writing to file: " + file);
		}
	}

	protected String getEmail() {
		return email;
	}
	protected String getPass() {
		return password;
	}
	@Override
	public String toString() {
		return "Email: " + email + plan ;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void readBook(User user, int index) throws UserException {
		if(bookList.returnBook().read(user)) {
			for(Book book : userBookList) {
				if(book.getIndex() == index) {
					System.out.println("Book "+ book.getName()+" read!");
					return;
				}
			}
		}else {
			throw new UserException("User needs to be active to read!");
		}
	}
	public void downloadBook(User user, int index) throws UserException {
		if(bookList.returnBook().download(user)) {
			for(Book book : userBookList) {
				if(book.getIndex() == index) {
					System.out.println("Book "+ book.getName()+" downloaded!");
					return;
				}
			}
		}else {
			throw new UserException("User needs to be active and Vip to download!");
		}
	}

}
