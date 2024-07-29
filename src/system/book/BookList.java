
package system.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import system.exception.BookException;
import system.util.SystemUtil;


public class BookList {
	private static final int NUMCOLS = 6;
    public static ArrayList<Book> bestsellers = new ArrayList<>();
    private String[] title= {"Index" , "Book", "Author(s)", "Original Language", "First published", "Approximate sales in millions", "Genre"} ;


    public void createList(String csvFile) throws BookException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line = br.readLine();
            int index = 0;
            while ((line = br.readLine()) != null) {
                String[] fields = SystemUtil.lineReader(line);
                Book book = new Book(index++, fields[0], fields[1], fields[2], Integer.parseInt(fields[3]), Float.parseFloat(fields[4]), fields[5]);
                bestsellers.add(book);
            }
        } catch (FileNotFoundException e) {
            throw new BookException("File not found: " + csvFile);
        } catch (IOException e) {
            throw new IOException("Error reading file: " + csvFile);
        }
    }

    public void saveList(String csvFile) throws BookException, IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            bw.write(String.join(",", title) + "\n");
            for (Book book : bestsellers) {
                bw.write(book.toString() + "\n");
            }
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + csvFile);
        }
    }

    public void printList() throws BookException{
        for (Book book : bestsellers) {
		System.out.println(book);
		}
    }

    public void add(Book book) {
        bestsellers.add(book);
    }

    public List<Book> search(String data) {
        List<Book> results = new ArrayList<>();
        for (Book book : bestsellers) {
            if (book.toString().contains(data)) {
                results.add(book);
            }
        }
        return results;
    }

    public void populateGenreAndLang() {
    	for(Book book: bestsellers) {
    		String genre = book.getGenre();
    		String lang = book.getOriginalLanguage();
    		BookGenreSet.genreSet.add(genre);
    		BookLanguageSet.langSet.add(lang);
    	}
    }

    public int getSize() {
        return bestsellers.size();
    }

    public List<Book> getBestsellers() {
        return bestsellers;
    }
    
public Book returnBook() {
	for(Book book : bestsellers) {
		return book;
	}return null;
}
}
