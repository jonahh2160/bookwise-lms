import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagementPageGUI {
    
    private JButton editButton, currentButton;

//-------------------------------Edit Button Methods------------------------------------
       
//Method to assign action event for the edit button in the case of editing the variables of a book;
    public void changeBookInfo(Book book) {

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String title = " ";
                String author =" ";
                String genre = " ";
                String publisher = " ";
                long isbn;

                currentButton = editButton;
                String[] responses = {"Title","Author","Genre","Publisher","ISBN","Cancel"};
		        int answer = JOptionPane.showOptionDialog(
				null,
				"Which field would you like to edit?", 
				"Book Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);
                if(answer == 0) 
                title = JOptionPane.showInputDialog("What would you like to change the title to? ");
                book.setTitle(title);
                if(answer == 1)
                author = JOptionPane.showInputDialog("What would you like to change the author to? ");
                book.setAuthor(author);
                if(answer == 2)
                genre = JOptionPane.showInputDialog("What would you like to change the genre to? ");
                book.setGenre(genre);
                if(answer == 3)
                publisher = JOptionPane.showInputDialog("What would you like to change the publisher to? ");
                book.setPublisher(publisher);
               // if(answer == 4)
               // isbn = JOptionPane.showInputDialog("What would you like to change the isbn to? ");
                //book.setIsbn(isbn); 
                
            }
          });
        }

//Method to assign action event for the edit button in the case of editing the variables of a user;
    public void changeUserInfo(User user) {

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String fullName = " ";
                String username =" ";
                String password = " ";
                int perms = 0;
                String[] memberLevel = {"Member","Librarian"};

                currentButton = editButton;
                String[] responses = {"Name","Username","Password","Cancel"};
		        int answer = JOptionPane.showOptionDialog(
				null,
				"Which field would you like to edit?", 
				"User Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);
                if(answer == 0) 
                fullName = JOptionPane.showInputDialog("What would you like to change the name to? ");
                user.setFullName(fullName);
                if(answer == 1)
                username = JOptionPane.showInputDialog("What would you like to change the username to? ");
                user.setUsername(username);
                if(answer == 2)
                password = JOptionPane.showInputDialog("What would you like to change the password to? ");
                user.setPassword(password);
                if(answer == 3)
		        perms = JOptionPane.showOptionDialog(
				null,
				"Which permission level would you like to assign?", 
				"Permission Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, memberLevel, memberLevel[0]);
                user.setPerms(perms);
            }
          });
        }

//Method to assign action event for the edit button in the case of editing the variables of a transaction;
    public void changeTransactionInfo(Transaction transaction) {

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String dateBorrowed = " ";
                String dateReturned =" ";
                String dateDue = " ";


                currentButton = editButton;
                String[] responses = {"Date Borrowed","Date Returned","Date Due","Cancel"};
		        int answer = JOptionPane.showOptionDialog(
				null,
				"Which field would you like to edit?", 
				"User Management", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, responses, responses[0]);
                if(answer == 0) 
                dateBorrowed = JOptionPane.showInputDialog("What would you like to change the date borrowed to? ");
                transaction.setDateBorrowed(dateBorrowed);
                if(answer == 1)
                dateReturned = JOptionPane.showInputDialog("What would you like to change the date returned to? ");
                transaction.setDateReturned(dateReturned);
                if(answer == 2)
                dateDue = JOptionPane.showInputDialog("What would you like to change the date due to? ");
                transaction.setDateDue(dateDue);  
            }
          });
    }
        
    }
