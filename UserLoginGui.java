// JH 11/11
// Page that will handle login related matters

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserLoginGui {
    // Objects of other classes
    private UserDatabase userDatabase;
    private UserLogin userLogin;

    // Objects required for creating the log in page
    private JFrame fr = new JFrame("Library Management System - Log In");
    private JPanel pa;
    private JTextField teUser;
    private JPasswordField tePass;
    private JLabel laUser, laPass, laError;
    private JButton buLogin, buCancel;

    // Final values to store sizes
    final int frWidth = 640;
    final int frHeight = 480;
    final int teWidth = 200;
    final int teHeight = 25;
    final int buWidth = 95;
    final int buHeight = 30;

    // Final values to store positions
    final int x = frWidth / 2 - (teWidth / 2);
    final int teUserY = (frHeight / 2) - (2 * teHeight) - (buHeight);
    final int tePassY = teUserY + teHeight + 25;
    final int laUserY = teUserY - 30;
    final int laPassY = tePassY - 30;
    final int buY = tePassY + teHeight + 20;
    final int buCancelX = x + buWidth + 10;
    final int laErrorY = buY + 20;

    // Contructor to setup window and objects
    public UserLoginGui(UserDatabase userDatabase, SearchPageGui searchPageGui) {
        this.userDatabase = userDatabase;
        this.userLogin = new UserLogin(this.userDatabase);

        // Setting up JFrame
        fr.setSize(frWidth, frHeight);
        fr.setLocationRelativeTo(null);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Setting up JPanel
        pa = new JPanel();
        pa.setLayout(null);

        // Creating text field parameters
        teUser = new JTextField();
        teUser.setSize(teWidth, teHeight);
        teUser.setLocation(x, teUserY);
        tePass = new JPasswordField();
        tePass.setSize(teWidth, teHeight);
        tePass.setLocation(x, tePassY);

        // Creating label parameters
        laUser = new JLabel("Username:");
        laUser.setSize(200, 40);
        laUser.setLocation(x, laUserY);
        laPass = new JLabel("Password:");
        laPass.setSize(200, 40);
        laPass.setLocation(x, laPassY);

        // Creating button parameters
        buLogin = new JButton("Login");
        buLogin.setSize(buWidth, buHeight);
        buLogin.setLocation(x, buY);
        buCancel = new JButton("Cancel");
        buCancel.setSize(buWidth, buHeight);
        buCancel.setLocation(buCancelX, buY);

        // Doing logic for the Login button
        buLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = teUser.getText();
                String password = tePass.getText();
                int loginValue = userLogin.login(username, password);

                if (loginValue == -1) {
                    laError.setForeground(Color.RED);
                    laError.setText("User does not exist!");
                } else if (loginValue == 0) {
                    laError.setForeground(Color.RED);
                    laError.setText("Incorrect password!");
                } else if (loginValue == 1) {
                    laError.setForeground(Color.GREEN);
                    laError.setText("Login successful!");
                    searchPageGui.setUser(userDatabase.getUserByName(username));
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    fr.dispose();
                }
            }
        });

        // Doing logic for the Cancel/exit button
        buCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
            }
        });

        // Creating parameters for new error label
        laError = new JLabel("");
        laError.setSize(200, 40);
        laError.setLocation(x, laErrorY);
    }

    // Draws the full log in page when called by adding to the panel
    public void logIn() {
        // Clearing fields
        laError.setText("");
        teUser.setText("");
        tePass.setText("");

        // Adding the objects to the panel
        pa.add(teUser);
        pa.add(tePass);
        pa.add(laUser);
        pa.add(laPass);
        pa.add(buLogin);
        pa.add(buCancel);
        pa.add(laError);

        // Adding the panel to the frame
        fr.add(pa);
        fr.setVisible(true);
    }
}