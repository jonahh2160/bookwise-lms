// JH 10/15
// Class for the Book object that contains its attributes

public class Book {
    // Declaration of variables (attributes)
    public String primaryKey;
    private String title, author, publisher, genre;
    private long isbn;
    private boolean availablity;

    // Constructor
    public Book(String title, String author, String genre, long isbn, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        availablity = true;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }

    public long getIsbn() {
        return isbn;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public boolean getAvailability() {
        return availablity;
    }

    // Setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public void setAvailability(boolean availablity) {
        this.availablity = availablity;
    }
}