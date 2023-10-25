import java.util.UUID;
import java.util.Random;

public class IDTesting {

public static void main(String args[]) {

    String ID;

    //UUID route
    ID = UUID.randomUUID().toString();
    System.out.println("ID: " + ID);

    //custom route
    Random rand = new Random();
    ID = ("" + rand.nextInt(999999999));
    while (ID.length() < 9) {
        ID = "0" + ID;
    }
    System.out.println("ID: " + ID);

}

/*mock up method for database abstract class
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

*/

}