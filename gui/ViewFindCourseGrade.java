package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class ViewFindCourseGrade {package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ViewFindCourseGrade {

    private Connection conn;
    public JFrame frame;
    private JTable table;
    private JComboBox comboBox;
    private JTextArea table1;

    /**
     * Create the application.
     */
    public ViewFindCourseGrade(Connection connec) {
        conn = connec;
        initialize();
    }
    
    static public ArrayList<String> getCourseIds(Connection conn) {
        Statement stmt = null;
        ResultSet rs = null;
        
        ArrayList<String> ids = new ArrayList<String>();

        if (conn == null) {
            return null;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT `Course ID` FROM COURSES");
            while (rs.next()) {
                ids.add(rs.getString(1));
            }

        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
            return null;
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                System.out.println("Error closing sql statement");
                e.printStackTrace();
                return null;
            }
        }
        return ids;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblFindCourseGrades = new JLabel("Find Course Grades");
        lblFindCourseGrades.setBounds(373, 11, 129, 16);
        frame.getContentPane().add(lblFindCourseGrades);
        
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
        btnSubmit.setBounds(373, 51, 117, 29);
        frame.getContentPane().add(btnSubmit);
        
        table = new JTable();
        table.setBounds(49, 155, 784, 568);
        frame.getContentPane().add(table);
        
        ArrayList<String> courseIds = ViewFindCourseGrade.getCourseIds(conn);
        
        comboBox = new JComboBox();
        comboBox.setBounds(242, 71, 125, 27);
        frame.getContentPane().add(comboBox);
        for (int i = 0; i < courseIds.size(); i++) {
            comboBox.addItem(courseIds.get(i));
        }
    }

    public void getCourseGrade(String courseID) {
    	
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        if (conn == null) {
            return;
        }
        try {
            stmt = conn.prepareStatement("SELECT `ASU ID`, `Grade` FROM `GRADES` WHERE `Course ID` = ?");
            stmt.setString(1, courseID);
            rs = stmt.executeQuery();
            
            table1.setText("ASU ID\tGrade\n");
            while (rs.next()) {
                String newline = rs.getString(1) + "\t" 
                                + rs.getString(2) + "\n";
                table1.setText(table1.getText()+newline);
            }
            
        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing SQL statement");
                e.printStackTrace();
            }
        }
    }
    	
    public void submitButtonPressed() {
        getCourseGrade((String) comboBox.getSelectedItem());
    }

}


    private Connection conn;
    public JFrame frame;
    private JTable table;

    /**
     * Create the application.
     */
    public ViewFindCourseGrade(Connection connec) {
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
        
        JLabel lblFindCourseGrades = new JLabel("Find Course Grades");
        lblFindCourseGrades.setBounds(373, 11, 129, 16);
        frame.getContentPane().add(lblFindCourseGrades);
        
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
        btnSubmit.setBounds(373, 51, 117, 29);
        frame.getContentPane().add(btnSubmit);
        
        table = new JTable();
        table.setBounds(49, 155, 784, 568);
        frame.getContentPane().add(table);
    }

}
