
package system;

import system.book.*;
import system.exception.*;
import system.user.*;

import java.io.IOException;
import java.util.Scanner;


public class SystemManager {
    private static final String CREATE_BOOK_LIST = "Create Book List";
    private static final String SHOW_BOOK_LIST = "Show Book List";
    private static final String SEARCH_IN_BOOK_LIST = "Search in Book List";
    private static final String CREATE_USER = "Create User";
    private static final String SHOW_USER_LIST = "Show User List";
    private static final String SAVE_USER_LIST = "Save User List";
    private static final String LOAD_USER_LIST = "Load User List";
    private static final String LOGIN_USER = "Login User";
    private static final String EXIT = "Exit";

    private static final String SEARCH = "Show all Books";
    private static final String ADD_BOOK_IN_MY_LIST = "Add Book in My List";
    private static final String SHOW_MY_BOOK_LIST = "Show My Book List";
    private static final String READ_BOOK = "Read Book";
    private static final String DOWNLOAD_BOOK = "Download Book";
    private static final String CHANGE_PASSWORD = "Change Password";
    private static final String LOGOFF = "Logoff";
    Scanner input = new Scanner(System.in);
    private BookList bookList = new BookList();
    private UserList userList = new UserList();
    User user = new User();

    String secondMenu = null;
    public static void main(String[] args) {
        SystemManager manager = new SystemManager();
        manager.showMainMenu();
    }

    private void showMainMenu() {
        while (true) {
            if (secondMenu == null) {
                // Admin menu
            	System.out.println("================================");
                System.out.println("|| Menu - Mini-System: OOP/A2 ||");
                System.out.println("================================");
                System.out.println("Select an option:");
                System.out.println("1. " + CREATE_BOOK_LIST);
                System.out.println("2. " + SHOW_BOOK_LIST);
                System.out.println("3. " + SEARCH_IN_BOOK_LIST);
                System.out.println("4. " + CREATE_USER);
                System.out.println("5. " + SHOW_USER_LIST);
                System.out.println("6. " + SAVE_USER_LIST);
                System.out.println("7. " + LOAD_USER_LIST);
                System.out.println("8. " + LOGIN_USER);
                System.out.println("9. " + EXIT);

                String choice = input.nextLine();
                try {
                    switch (choice) {
                        case "1":
                            createBookList();
                            break;
                        case "2":
                            showBookList();
                            break;
                        case "3":
                            searchInBookList();
                            break;
                        case "4":
                            createUser();
                            break;
                        case "5":
                            showUserList();
                            break;
                        case "6":
                            saveUserList();
                            break;
                        case "7":
                            loadUserList();
                            break;
                        case "8":
                            loginUser();
                            secondMenu = "Change menu";
                            break;
                        case "9":
                            exit();
                            System.exit(0);
                            return;
                        default:
                            System.err.println("Invalid option. Please try again.");
                    }
                } catch (BookException | UserException e) {
                    System.err.println(e.getMessage());
                    break;
                }
            } else {
                // User menu
            	System.out.println("================================");
                System.out.println("|| Menu - User ..............||");
                System.out.println("================================");
                System.out.println("Select an option:");
                System.out.println("10. " + SEARCH);
                System.out.println("11. " + ADD_BOOK_IN_MY_LIST);
                System.out.println("12. " + SHOW_MY_BOOK_LIST);
                System.out.println("13. " + READ_BOOK);
                System.out.println("14. " + DOWNLOAD_BOOK);
                System.out.println("15. " + CHANGE_PASSWORD);
                System.out.println("16. " + LOGOFF);

                String choice = input.nextLine();
                try {
                    switch (choice) {
                        case "10":
                            showAllBooks();
                            break;
                        case "11":
                            addBookInMyList();
                            break;
                        case "12":
                            showMyBookList();
                            break;
                        case "13":
                            readBook();
                            break;
                        case "14":
                            downloadBook();
                            break;
                        case "15":
                            changePassword();
                            break;
                        case "16":
                            logoff();
                            secondMenu = null;
                            break;
                        default:
                            System.err.println("Invalid option. Please try again.");
                    }
                } catch (BookException | UserException e) {
                    System.err.println(e.getMessage());
                    
                }
            }
        }
    }

    private void createBookList() throws BookException {
    	System.out.print("Name of file to create booklist: ");
        String fileName = input.nextLine();
        try {
            bookList.createList(fileName);
            bookList.populateGenreAndLang();
            System.out.println("Book list created successfully!");
        } catch (IOException e) {
            throw new BookException("Error creating book list.");
        }
    }

    private void showBookList() throws BookException {
    	 System.out.println("BOOKLIST ..................");
    	 bookList.printList();
    }

    private void searchInBookList() throws BookException {
    	System.out.print("Enter one string for search in the list: ");
        String searchString = input.nextLine();
        System.out.println("Results from search...");
        for (Book book : bookList.search(searchString)) {
            System.out.println(book);
        }
    }
 
    private void createUser() throws UserException {
    	System.out.println("Enter user data:");
    	System.out.println("Email: ");
    	String email = input.nextLine();
    	System.out.println("Password: ");
    	String password = input.nextLine();
    	System.out.println("Plan type: [1]: trial, [2]: standard, [3]: vip ");
		String string1 = input.nextLine();
		System.out.println("Activation: [1]: activated, [2]: deactivated: ");
		String string2 = input.nextLine();
		
    	user = user.createUser(email,password,string1,string2);
		userList.addUser(user);
		System.out.println("User Created Succesfully");
    }

    private void showUserList() throws UserException {
    	System.out.println("USERLIST...............");
    	userList.loadUserList();
    	
    }

    private void saveUserList() throws UserException {
    	 System.out.print("Name of file to save booklist: ");
         String saveFileName = input.nextLine();
         try {
             userList.saveUserList(saveFileName);
             System.out.println("File " + saveFileName + " created.");
         } catch (IOException e) {
             throw new UserException("Error saving user list.");
         }
    }

    private void loadUserList() throws UserException {
    	System.out.println("IDK what this method was supposed to do");
    }

    private void loginUser() throws UserException {
     System.out.println("Enter username: ");
     String username = input.nextLine();
     System.out.println("Enter password: ");
     String password = input.nextLine();
     userList.loginUser(username, password);
    }

    private void exit() {
    	System.out.println("================================");
        System.out.println("|| [Application ended] ||");
        System.out.println("================================");
    }

    private void showAllBooks() throws BookException {
    	System.out.println("All Books...............");
    	bookList.printList();
    }

    private void addBookInMyList() throws BookException, UserException {
      System.out.println("Enter the book index: ");
      int index = input.nextInt();
      user.addToBooklist(index);
      System.out.println("Book added Successfully");
    }

    private void showMyBookList() throws BookException {
    	System.out.println("USER BOOKLIST...............");
    	user.displayBooklist();
    }

    private void readBook() throws BookException, UserException {
    	System.out.println("Enter book index to read: ");
    	int index = input.nextInt();
    	user.readBook(user, index);
    	
    }

    private void downloadBook() throws BookException, UserException {
    	System.out.println("Enter book index to download: ");
    	int index = input.nextInt();
    	user.downloadBook(user, index);
    }

    private void changePassword() throws UserException {
    	System.out.println("Enter your username: ");
       String username = input.nextLine();
       System.out.println("Enter new password: ");
       String newPass= input.nextLine();
       userList.changePass(username, newPass);
    }

    private void logoff() {
    	System.out.println("================================");
        System.out.println("|| [User Logoff ...] ||");
        System.out.println("================================");
    }
}

