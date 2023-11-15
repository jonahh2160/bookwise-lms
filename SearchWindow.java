//IG 11/15

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.*;

public class SearchWindow {
    private CreateInstanceGui createPage;
    private JFrame frame;
    private JPanel panel;
    private JTextField searchField;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;
    private int frameWidth = 216;
    private int frameHeight = 216;
    private int searchHeight = 30;
    private String[] bookColumnNames = {"Title","Author","ID"};
    private String[] userColumnNames = {"Name","Username","ID"};
    private String[] columnNames = bookColumnNames;

    public SearchWindow(CreateInstanceGui createPage) {
        this.createPage = createPage;
        frame = new JFrame("Search Book");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(frameWidth,frameHeight);
        frame.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);

        // Creating the table
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return(false);
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        tableModel.setColumnCount(columnNames.length);
        for (int i=0;i<columnNames.length;i++) {
            table.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
        }
        table.getTableHeader().repaint();
        //table.getTableHeader().setBackground(darkNavyColor);
        table.getTableHeader().setForeground(Color.WHITE);
        // Creating the scroll pane to be able to scroll through table
        scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(10,10,frameWidth-20,frameHeight-20);
        scrollPane.setVisible(true);
        //scrollPane.getViewport().setBackground(darkNavyColor);
        
    }

    //public createSearchWindow(int type) {

    //}

}
