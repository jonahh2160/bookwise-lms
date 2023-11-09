// IG 11/7
// Used for testing the GUI components

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TestGui {

    public void MainPage() {
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960,540);
        frame.setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel notLoggedIn = new JLabel("Not Logged In!");
        notLoggedIn.setSize(100,20);
        notLoggedIn.setLocation(840,10);
        notLoggedIn.setForeground(Color.red);
        Font italicFont = new Font(notLoggedIn.getFont().getName(),Font.ITALIC,notLoggedIn.getFont().getSize());
        notLoggedIn.setFont(italicFont);
        panel.add(notLoggedIn);

        
        JButton searchButton = new JButton("Search as [Placeholder]");
        searchButton.setSize(620,80);
        searchButton.setLocation(170,175);
        panel.add(searchButton);

        JButton loginButton = new JButton("Login");
        loginButton.setSize(300,80);
        loginButton.setLocation(170,285);
        panel.add(loginButton);
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginButton.setText("Leave");
            }
        });
        
        
        JButton manageButton = new JButton("Manage");
        manageButton.setSize(300,80);
        manageButton.setLocation(490,285);
        panel.add(manageButton);
        
         frame.add(panel);
        frame.setVisible(true);

    }

}
