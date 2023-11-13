// IG 11/13

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.*;

public class CreateInstanceGui {
    private BookDatabase bookDatabase;
    private UserDatabase userDatabase;
    private TransactionDatabase transactionDatabase;
    private SearchPageGui searchPage; 
    private JFrame frame, fr;
    private JPanel panel, pa;
    private JButton bookButton, userButton, transactionButton;
    private JComboBox borrowDateBox, dueDateBox;
    private JLabel label1,label2,label3,label4;
    private JTextField te1,te2,te3,te4;
    private int componentX = 100;
    private int[] componentY = {0,30,60,90,120};
    private int frameWidth = 640;
    private int frameHeight = 480;
    private int buttonWidth = 100;
    private int buttonHeight = 30;
    private int buttonSep = 20;
    private int buttonX = (frameWidth/2) - (buttonWidth/2);
    private int[] buttonY = {(frameHeight/2)-(buttonHeight/2)-buttonSep-buttonHeight,(frameHeight/2)-(buttonHeight/2),(frameHeight/2)+(buttonHeight/2)+buttonSep};

    public CreateInstanceGui(BookDatabase bookDatabase, UserDatabase userDatabase, TransactionDatabase transactionDatabase, SearchPageGui searchPage) {
        this.searchPage = searchPage;
        this.bookDatabase = bookDatabase;
        this.userDatabase = userDatabase;
        this.transactionDatabase = transactionDatabase;

        // Create the frame
        frame = new JFrame("Manage");
        frame.setSize(frameWidth,frameHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr = new JFrame("Manage");
        fr.setSize(frameWidth,frameHeight);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the panel
        panel = new JPanel();
        panel.setLayout(null);
        pa = new JPanel();
        pa.setLayout(null);

        // Book button
        bookButton = new JButton("NEW BOOK");
        bookButton.setSize(buttonWidth,buttonHeight);
        bookButton.setLocation(buttonX,buttonY[0]);

        // User button
        userButton = new JButton("NEW USER");
        userButton.setSize(buttonWidth,buttonHeight);
        userButton.setLocation(buttonX,buttonY[1]);

        // Transaction button
        transactionButton = new JButton("NEW TRANSACTION");
        transactionButton.setSize(buttonWidth,buttonHeight);
        transactionButton.setLocation(buttonX,buttonY[2]);

        // Labels
        label1.setSize(200,40);
        label1.setLocation(componentX,componentY[0]);
        label2.setSize(200,40);
        label2.setLocation(componentX,componentY[0]);
        label3.setSize(200,40);
        label3.setLocation(componentX,componentY[0]);
        label4.setSize(200,40);
        label4.setLocation(componentX,componentY[0]);
    }

    public void createInstancePage() {
        panel.removeAll();

        panel.add(bookButton);
        panel.add(userButton);
        panel.add(transactionButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void createBook() {
        pa.removeAll();
        label1.setText("Title:");
        label2.setText("Author");
        label3.setText("Genre");
        label4.setText("Year");
        pa.add(label1);
        pa.add(label2);
        pa.add(label3);
        pa.add(label4);
    }

}
