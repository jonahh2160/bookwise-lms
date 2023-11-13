// JH 10/26

import java.util.ArrayList;

public class BookDatabase extends Table<Book> {

    // Constructor; initializes database ArrayList
    public BookDatabase() {
        this.database = new ArrayList<Book>();
    }

    // Overriden method to add an entry with the parent method and generate an ID
    @Override
    public void addEntry(Book entry) {
        super.addEntry(entry);
        entry.setPrimaryKey(generateID());
    }

    // Implements the ability to edit book entries
    public void setEntry(Book book, String title, String author, String publisher, String genre,
            long isbn, boolean availablity, String primaryKey) {
        if (title != null) {
            book.setTitle(title);
        }
        if (author != null) {
            book.setAuthor(author);
        }
        if (publisher != null) {
            book.setPublisher(publisher);
        }
        if (genre != null) {
            book.setGenre(genre);
        }
        book.setAvailability(availablity);
        if (primaryKey != null) {
            book.setPrimaryKey(primaryKey);
        }
    }

    // Returns the primaryKey at a given index
    // Overriden so that the parent class can access the primaryKey
    @Override
    public String getID(int index) {
        return (database.get(index).primaryKey);
    }

    // Displays each primary key (for testing purposes)
    public void display() {
        for (int i = 0; i < database.size(); i++) {
            System.out.println("Book ID " + i + ": " + database.get(i).primaryKey);
        }
    }
}