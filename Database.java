import java.util.ArrayList;

public abstract class Database {
    ArrayList<Object> database;

    public Database(Object object) {
        ArrayList<Object> database = new ArrayList<Object>();
    }

    public void addEntry(Object object) {
        database.add(object);
    }

    public Object GetEntry(int entryID) {
        Object object = database.get(entryID);
        return(object);
    }

    abstract void editEntry();

    abstract void removeEntry();
}
