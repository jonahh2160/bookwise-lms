// MT 10/23
import java.util.ArrayList;

public class UserDatabase extends Table<User> {

    // Constructor
    public UserDatabase() {
        this.database = new ArrayList<User>();
    }

    // Overriden method to add an entry with the parent method and generates an ID
    @Override
    public void addEntry(User entry) {
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
            System.out.println("User ID " + i + ": " + database.get(i).primaryKey);
        }
    }

}