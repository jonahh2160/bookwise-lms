import java.util.ArrayList;
import java.util.Random;

abstract class Database {
    Random rand = new Random();

    abstract void addEntry(E entry);

    abstract void removeEntry();

    //This method will create a new ID
    public String createID(ArrayList<E> database) {
        boolean success = false;
        String ID = "";
        //loop until a successful ID has been created
        while (success == false) {
            ID = ("" + rand.nextInt(999999999));
            //make sure the ID is 7 digits long
            while (ID.length() < 7) {
                ID = "0" + ID;
            }
            //check the database to make sure the ID has not been used yet
            for (int i = 0;i < database.size();i ++) {
                if (database.get(i).getPrimaryKey() == ID) {
                    continue;
                }
            }
            success = true;
        }
        return(ID);
    }



}
