// MT 11/1/2023

import java.util.ArrayList;

public class Sorter {

ArrayList<Book> bookList = new ArrayList<Book>();
ArrayList<User> userList = new ArrayList<User>();
ArrayList<Transaction> transactionList = new ArrayList<Transaction>();


void quicksort(int[] numbers, int startIndex, int endIndex) {
   // Only attempt to sort the array segment if there are
   // at least 2 elements
   if (endIndex <= startIndex) {
      return;
   }
          
   // Partition the array segment
   int high = partition(numbers, startIndex, endIndex);

   // Recursively sort the left segment
   quicksort(numbers, startIndex, high);

   // Recursively sort the right segment
   quicksort(numbers, high + 1, endIndex);
}

}