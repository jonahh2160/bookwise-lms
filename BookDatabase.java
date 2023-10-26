// JH 10/26
import java.util.ArrayList;

public class BookDatabase extends Table<Book> {

    // Constructor
    public BookDatabase() {
        this.database = new ArrayList<Book>();
    }

    // Overriden method to add an entry with the parent method and generates an ID
    @Override
    public void addEntry(Book entry) {
        super.addEntry(entry);
        entry.setPrimaryKey(generateID());
    }

    // Edit an entry in the book array
    public void setEntry() {

    }

    // Overriden such that the parent class can use the method; returns the primaryKey for a given index
    @Override
    public String getID(int index) {
        return(database.get(index).primaryKey);
    }

    // TEST METHOD
    public void display() {
        for (int i=0;i<database.size();i++) {
            System.out.println("Book ID " + i + ": " + database.get(i).primaryKey);
        }
    }
    
}