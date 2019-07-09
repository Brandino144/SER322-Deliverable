package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DataPage {

    public JFrame frame;
    private JTextArea table;

    private Connection conn;
    
    private JRadioButton rdbtnStudents;
    private JRadioButton rdbtnCourses;
    private JRadioButton rdbtnInstructors;
    private JRadioButton rdbtnDepartments;
    private JRadioButton rdbtnMajors;
    private JRadioButton rdbtnAdministrators;
    
    
    /**
     * Create the application.
     */
    public DataPage(String data, Connection connec) {
        conn = connec;
        initialize(data);
        
        if (data.equals("student")) {
            displayStudents();
        } else if(data.equals("courses")) {
            displayCourses();
        }
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(String data) {
        frame = new JFrame("Database Info");
        frame.setBounds(100, 100, 900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblWhichDataWould = new JLabel("Which Data Would You Like to View?");
        lblWhichDataWould.setBounds(328, 17, 236, 16);
        frame.getContentPane().add(lblWhichDataWould);
        
        rdbtnStudents = new JRadioButton("Students");
        rdbtnStudents.setBounds(49, 45, 141, 23);
        frame.getContentPane().add(rdbtnStudents);
        
        rdbtnCourses = new JRadioButton("Courses");
        rdbtnCourses.setBounds(176, 45, 98, 23);
        frame.getContentPane().add(rdbtnCourses);
        
        rdbtnInstructors = new JRadioButton("Instructors");
        rdbtnInstructors.setBounds(299, 45, 141, 23);
        frame.getContentPane().add(rdbtnInstructors);
        
        rdbtnDepartments = new JRadioButton("Departments");
        rdbtnDepartments.setBounds(437, 45, 141, 23);
        frame.getContentPane().add(rdbtnDepartments);
        
        rdbtnMajors = new JRadioButton("Majors");
        rdbtnMajors.setBounds(590, 45, 98, 23);
        frame.getContentPane().add(rdbtnMajors);
        
        rdbtnAdministrators = new JRadioButton("Administrators");
        rdbtnAdministrators.setBounds(692, 45, 141, 23);
        frame.getContentPane().add(rdbtnAdministrators);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(380, 80, 117, 29);
        frame.getContentPane().add(btnSubmit);
        
        btnSubmit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
               submitButtonPressed();
            } 
          } );
        
        JButton btnGoBack = new JButton("Go Back");
        btnGoBack.setBounds(6, 6, 117, 29);
        frame.getContentPane().add(btnGoBack);
        
        btnGoBack.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
             frame.setVisible(false);
             frame.dispose();
            } 
          } );
        
        table = new JTextArea();
        table.setBounds(49, 155, 784, 568);
        frame.getContentPane().add(table);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rdbtnStudents);
        bg.add(rdbtnCourses);
        bg.add(rdbtnInstructors);
        bg.add(rdbtnDepartments);
        bg.add(rdbtnMajors);
        bg.add(rdbtnAdministrators);
        bg.add(btnSubmit);
        bg.add(btnGoBack);
    }
    
    void displayStudents() {
        Statement stmt = null;
        ResultSet rs = null;

        if (conn == null) {
            return;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM STUDENT");

            table.setText("First Name\tLastName\tASU ID\n");
            while (rs.next()) {
                String newline = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(5) + "\n";
                table.setText(table.getText()+newline);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
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
            }
        }
    }
    
    void displayCourses() {
        Statement stmt = null;
        ResultSet rs = null;

        if (conn == null) {
            return;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM COURSES");

            table.setText("Course ID\tCredits\tInstructor\tName\n");
            while (rs.next()) {
                String newline = rs.getString(1) + "\t" 
                                + rs.getString(5) + "\t" 
                                + rs.getString(7) + "\t" 
                                + rs.getString(3) + "\n";
                table.setText(table.getText()+newline);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
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
            }
        }
    }
    
    void displayInstructors() {
        Statement stmt = null;
        ResultSet rs = null;

        if (conn == null) {
            return;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM INSTRUCTOR");

            table.setText("FirstName\tLastName\tEmployeeID\tEmail\n");
            while (rs.next()) {
                String newline = rs.getString(1) + "\t" 
                                + rs.getString(2) + "\t" 
                                + rs.getString(5) + "\t"
                                + rs.getString(6) + "\n";
                table.setText(table.getText()+newline);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
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
            }
        }
    }
    
    void displayDepartments() {
        Statement stmt = null;
        ResultSet rs = null;

        if (conn == null) {
            return;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `ACADEMIC DEPARTMENTS`");

            table.setText("Name\t\tAddress\n");
            while (rs.next()) {
                String newline = rs.getString(1) + "\t\t" 
                                + rs.getString(2) + "\n";
                table.setText(table.getText()+newline);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
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
            }
        }
    }
    
    void displayMajors() {
        Statement stmt = null;
        ResultSet rs = null;

        if (conn == null) {
            return;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM MAJORS");

            table.setText("Name\tOnline\tDepartment\n");
            while (rs.next()) {
                String newline = rs.getString(1) + "\t" 
                                + rs.getString(2) + "\t"
                                + rs.getString(3) + "\n";
                table.setText(table.getText()+newline);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
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
            }
        }
    }
    
    void displayAdmin() {
        Statement stmt = null;
        ResultSet rs = null;

        if (conn == null) {
            return;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ADMINISTRATOR");

            table.setText("FirstName\tLastName\tEmployeeID\temail\n");
            while (rs.next()) {
                String newline = rs.getString(3) + "\t" 
                                + rs.getString(4) + "\t"
                                + rs.getString(2) + "\t"
                                + rs.getString(1) + "\n";
                table.setText(table.getText()+newline);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error");
            e.printStackTrace();
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
            }
        }
    }
    
    void submitButtonPressed() {
        if(rdbtnStudents.isSelected()) {
            displayStudents();
        }
        else if(rdbtnCourses.isSelected()) {
            displayCourses();
        }
        else if(rdbtnInstructors.isSelected()) {
            displayInstructors();
        }
        else if (rdbtnDepartments.isSelected()) {
            displayDepartments();
        }
        else if (rdbtnMajors.isSelected()) {
            displayMajors();
        }
        else if (rdbtnAdministrators.isSelected()) {
            displayAdmin();
        }
    }
}
