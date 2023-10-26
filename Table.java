// JH 10/26
// Attempt at passing something of type Object (using generics)
// TODO: Solve primaryKey errors
import java.util.ArrayList;
import java.util.Random;

// Instantiation of generic Type parameter
// Keep in mind format: Table<Type> name = new Table<>(); 
public abstract class Table <T>{
    // TODO: Add an ArrayList of type T here to avoid passing one to each method(?)
    Random rand = new Random();
    
    // Adds the passed entry to the database/table that which it belongs
    public void addEntry(ArrayList<T> database, T entry) {
        database.add(entry);
        // Update exteral database here
    }

    // Returns the entry specified by passing its ID
    public T getEntry(ArrayList<T> database, String primaryKey) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).primaryKey == primaryKey) {
                return (database.get(i));
            }
        }
        return null;
    }

    // Overloaded method to get an entry with another query
    public T getEntry(ArrayList<T> database, int index) {
        return database.get(index);
    }

    // TODO: Add a method to edit existing entries
    abstract void setEntry();

    // Permanently removes the entry of a specified ID
    public void removeEntry(ArrayList<T> database, String primaryKey) {
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).primaryKey == primaryKey) {
                database.remove(i);
                // Update external database here
                break;
            }
        }
    }

    public String generateID(ArrayList<T> database) {
        boolean success = false;
        String ID = "";

        // Loop until a successful ID has been created
        while (success == false) {
            ID = ("" + rand.nextInt(999999999));

            // Make sure the ID is 7 digits long
            while (ID.length() <7) {
                ID = "0" + ID;
            }

            // Check the database to ensure a unique ID
            for (int i = 0; i < database.size(); i++) {
                if (database.get(i).getPrimaryKey() == ID) {
                    continue;
                }
            }
            success = true;
        }
        return ID;
    }

}
