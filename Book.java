// JH 10/15

public class Book {
    // Declaration of variables (attributes)
    // The ISBN of the book will double as its primary key
    private String title, author, publisher, genre;
    private long isbn;
    private int year, length;
    private boolean availablity = true;

    // Constructor
    public Book(String title, String author, String publisher, 
    String genre, long isbn, int year, int length) {
        // TODO: make this constructor look less like vomit
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.isbn = isbn;
        this.year = year;
        this.length = length;
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

    public int getYear() {
        return year;
    }

    public int getLength() {
        return length;
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

    public void setYear(int year) {
        this.year = year;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setAvailability(boolean availablity) {
        this.availablity = availablity;
    }
}
