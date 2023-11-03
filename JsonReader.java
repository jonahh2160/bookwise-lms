// JH 11/3
// Thanks to GeeksforGeeks for their "How to parse JSON in Java" article

import java.io.FileReader; 
import java.util.Iterator; 
import java.util.Map; 
  
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

import java.io.IOException;

// This is a lot of chaos right now
// Note: may need to turn 325BroSquad into a Maven project for json-simple-1.1.jar to work
public class JsonReader {

    BookDatabase bookDatabase;
    
    // Constructor that passes a bookDatabase to here
    public JsonReader(BookDatabase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    public void readJson() throws IOException, ParseException { 
        try {
            // parsing file "SampleJSON_BookResponse.json"
            Object obj = new JSONParser().parse(new FileReader("SampleJSON_BookResponse.json")); 
            
            /*
            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            String title;
            String author;
            String genre;
            long isbn;
            int year;

            // TODO: Parse the entire JSON

            // TODO: If a book in the JSON isn't in the database, add it

            // In "volumeInfo":
            title = (String) jo.get("title");
            author = (String) jo.get("authors");
            // TODO: Year isn't in the right format, only need first 4 digits
            year = (int) jo.get("publishedDate");
            // In "industryIdentifiers" under "ISBN_13"
            // TODO: ISBN likely doesn't format properly in Book
            isbn = (long) jo.get("identifier");
            // TODO: It comes with two genres, we only need the second
            genre = (String) jo.get("title");

            Book jsonBook = new Book(title, author, genre, isbn, year);
            bookDatabase.addEntry(jsonBook);

            // TODO: If the method of parsing the JSON is through a loop, stop looping after iterating through the whole JSON
            */
            
        } catch (IOException e) {
            System.out.println("Big Bad Eren :(");
        }
    }

}
