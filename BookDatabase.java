// JH 10/26
// TODO: Ensure all methods work and are in the right order
import java.util.ArrayList;

public class BookDatabase extends Table<Book> {

    public BookDatabase() {
        this.database = new ArrayList<Book>();
    }

    @Override
    public void addEntry(Book entry) {
        super.addEntry(entry);
        entry.setPrimaryKey(generateID());
    }

    public void setEntry() {

    }

    @Override
    public String getID(int index) {
        return(database.get(index).primaryKey);
    }

    //this method is for testing purposes
    public void display() {
        for (int i=0;i<database.size();i++) {
            System.out.println("Book ID " + i + ": " + database.get(i).primaryKey);
        }
    }

}