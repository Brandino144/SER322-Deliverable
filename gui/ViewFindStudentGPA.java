package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class ViewFindStudentGPA {

    private Connection conn;
    public JFrame frame;
    private JTable table;

    /**
     * Create the application.
     */
    public ViewFindStudentGPA(Connection connec) {
        conn = connec;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Database Info");
        frame.setBounds(100, 100, 900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblViewFindStudent = new JLabel("Find Student GPA");
        lblViewFindStudent.setBounds(383, 11, 117, 16);
        frame.getContentPane().add(lblViewFindStudent);
        
        JButton btnGoBack = new JButton("Go Back");
        btnGoBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frame.dispose();
            }
        });
        btnGoBack.setBounds(6, 6, 117, 29);
        frame.getContentPane().add(btnGoBack);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(383, 39, 117, 29);
        frame.getContentPane().add(btnSubmit);
        
        table = new JTable();
        table.setBounds(49, 155, 784, 568);
        frame.getContentPane().add(table);
    }

}
