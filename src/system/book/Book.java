
package system.book;

import system.user.User;

public class Book implements BookReadable, BookDownloadable{
	private int index;
    private String name;
    private String author;
    private String originalLanguage;
    private int firstPublished;
    private float milionSales;
    private String genre;
    User user;

    public Book(int index, String name, String author, String originalLanguage, int firstPublished, float milionSales, String genre) {
        this.index = index;
        this.name = name;
        this.author = author;
        this.originalLanguage = originalLanguage;
        this.firstPublished = firstPublished;
        this.milionSales = milionSales;
        this.genre = genre;
    }

    public Book() {}

	// Getters and setters
    public int getIndex() { return index; }
    public void setIndex(int index) { this.index = index; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getOriginalLanguage() { return originalLanguage; }
    public void setOriginalLanguage(String originalLanguage) { this.originalLanguage = originalLanguage; }

    public int getFirstPublished() { return firstPublished; }
    public void setFirstPublished(int firstPublished) { this.firstPublished = firstPublished; }

    public float getMilionSales() { return milionSales; }
    public void setMilionSales(float milionSales) { this.milionSales = milionSales; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }


//    public String toString() {
//        return "Book [" + index + "] {Book='" + name + "', Author(s)='" + author + "', Original language='" + originalLanguage +
//               "', First published=" + firstPublished + ", Approximate sales in millions=" + milionSales + ", Genre=" + genre + "}";
//    }
    public String toString() {
        return "Book [" + index + "], " + name + ", " + author + ", " + originalLanguage +
               ", " + firstPublished + ", " + milionSales + ", " + genre;
    }

	@Override
	public  boolean download(User user) {
		if(user.plan.isActive() && user.plan.isVip()) {
			return true;
		}return false;
	}

	@Override
	public boolean read(User user) {
		if(user.plan.isActive()) {
			return true;
		}
		return false;
		
	}


}
