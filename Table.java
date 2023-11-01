// JH, IG 10/26
// Attempt at passing something of type Object (using generics)
import java.util.ArrayList;
import java.util.Random;

// Instantiation of generic Type parameter
// Keep in mind format: Table<Type> name = new Table<>(); 
public abstract class Table <T>{
    ArrayList<T> database;
    Random rand = new Random();
    
    // Adds the passed entry to the database/table that which it belongs
    public void addEntry(T entry) {
        database.add(entry);
        // Update external database here
    }

    // Returns the entry specified by passing its ID
    public T getEntry(String primaryKey) {
        for (int i = 0; i < database.size(); i++) {
            if (getID(i) == primaryKey) {
                return (database.get(i));
            }
        }
        return null;
    }

    // Overloaded method to get an entry with the array's index
    public T getEntry(int index) {
        return database.get(index);
    }

    // NOTE: There is a setEntry method present in each child class
    // Each child has different parameters

    // Permanently removes the entry of a specified ID
    public void removeEntry(String primaryKey) {
        for (int i = 0; i < database.size(); i++) {
            if (getID(i) == primaryKey) {
                database.remove(i);
                // Update external database here
                break;
            }
        }
    }

    // Overloaded method to remove an entry with the array's index
    public void removeEntry(int index) {
        database.remove(index);
    }

    // Fetches the primaryKey of a certain index
    abstract String getID(int index);

    // Returns the size of the database array
    public int getSize() {
        return(database.size());
    }

    // Creates a unique 7 digit primaryKey
    public String generateID() {
        boolean success = false;
        String ID = "";

        // Loop until a successful ID has been created
        while (success == false) {
            ID = ("" + rand.nextInt(9999999));

            // Make sure the ID is 7 digits long
            while (ID.length() <7) {
                ID = "0" + ID;
            }

            // Check the database to ensure a unique ID
            for (int i = 0; i < database.size(); i++) {
                if (getID(i) == ID) {
                    continue;
                }
            }
            success = true;
        }
        return ID;
    }

}
