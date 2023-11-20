// JH 10/15
// Class for the Book object that contains its attributes

public class Book {
    // Declaration of variables (attributes)
    public String primaryKey;
    private String title, author, genre;
    private boolean availablity;
    private int year;

    // Constructor
    public Book(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        availablity = true;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public boolean getAvailability() {
        return availablity;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setAvailability(boolean availablity) {
        this.availablity = availablity;
    }

    public void setYear(int year) {
        this.year = year;
    }
}