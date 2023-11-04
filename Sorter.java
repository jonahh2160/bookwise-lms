// MT 11/1/2023

import java.util.ArrayList;
import java.util.Comparator;

public class Sorter{

//Book comparator objects using lambda expressions to compare properties of the object
Comparator<Book> titleComparator = (t1, t2) -> t1.getTitle().compareTo(t2.getTitle());
Comparator<Book> authorComparator = (a1, a2) -> a1.getAuthor().compareTo(a2.getAuthor());
Comparator<Book> genreComparator = (g1, g2) -> g1.getGenre().compareTo(g2.getGenre());
Comparator<Book> primaryKeyBookComparator = (k1, k2) -> k1.getPrimaryKey().compareTo(k2.getPrimaryKey());

//User comparator objects using lambda expressions to compare properties of the object
Comparator<User> fullNameComparator =(f1, f2) -> f1.getFullName().compareTo(f2.getFullName());
Comparator<User> usernameComparator = (n1, n2) -> n1.getUsername().compareTo(n2.getUsername());
Comparator<User> primaryKeyUserComparator = (p1, p2) -> p1.getPrimaryKey().compareTo(p2.getPrimaryKey());

//Transaction comparator objects using lambda expression to compare properties of the object
Comparator<Transaction> dateDueComparator = (d1, d2) -> d1.getDateDue().compareTo(d2.getDateDue());
Comparator<Transaction> primaryKeyTransactionComparator = (v1, v2) -> v1.getPrimaryKey().compareTo(v2.getPrimaryKey());

//Constructor
public Sorter() {}

//Method to order a list of books alphabetically by Title
public ArrayList<Book> titleSorter(ArrayList<Book> listOfBooks){
   
   listOfBooks.sort(titleComparator);

   return listOfBooks;
}

//Method to order a list of books alphabetically by Author
public ArrayList<Book> authorSorter(ArrayList<Book> listOfBooks) {
   
   listOfBooks.sort(authorComparator);

   return listOfBooks;
}

//Method to order a list of books alphabetically by Genre
public ArrayList<Book> genreSorter(ArrayList<Book> listOfBooks) {

   listOfBooks.sort(genreComparator);

   return listOfBooks;
}

//Method to order a list of books by Primary Key
public ArrayList<Book> bookPrimaryKeySorter(ArrayList<Book> listOfBooks) {

   listOfBooks.sort(primaryKeyBookComparator);

   return listOfBooks;
}

//Method to order a list of users alphabetically by their Full Name
public ArrayList<User> fullNameSorter(ArrayList<User> listOfBooks) {
   
   listOfBooks.sort(fullNameComparator);

   return listOfBooks;
}

//Method to order a list of users alphabetically by their Username
public ArrayList<User> usernameSorter(ArrayList<User> listOfBooks) {

   listOfBooks.sort(usernameComparator);

   return listOfBooks;
}

//Method to order a list of users alphabetically by their Primary Key
public ArrayList<User> primaryKeyUserSorter(ArrayList<User> listOfBooks) {
    
   listOfBooks.sort(primaryKeyUserComparator);

   return listOfBooks;
}

//Method to order a list of Transactions numerically by their Date Due
public ArrayList<Transaction> dateDueSorter(ArrayList<Transaction> listOfBooks) {

   listOfBooks.sort(dateDueComparator);

   return listOfBooks;
}

//Method to order a list of Transactions numberically by their Primary Key
public ArrayList<Transaction> primaryKeyTransactionSorter(ArrayList<Transaction> listOfBooks) {
   
   listOfBooks.sort(primaryKeyTransactionComparator);

   return listOfBooks;
}

}