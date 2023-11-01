// JH 11/1
// Temporary(?) class that handles JSON parsing. Takes the JSON and inputs it into our database

import org.json.*;

public class TableParser {
    BookDatabase bookDatabase;

    // Constructor that passes a bookDatabase to here
    public TableParser(BookDatabase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }
    
}
