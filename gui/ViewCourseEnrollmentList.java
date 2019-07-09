package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class ViewCourseEnrollmentList {

    private Connection conn;
    public JFrame frame;
    private JTextArea textArea;
    private JComboBox comboBox;
    private ArrayList<String> courseIds2;
    /**
     * Create the application.
     */
    public ViewCourseEnrollmentList(Connection connec) {
        conn = connec;
        initialize();
    }
    public ArrayList<String> getCourseIds() {
        Statement stmt = null;
        ResultSet rs = null;
        
        ArrayList<String> ids = new ArrayList<String>();

        if (conn == null) {
            return null;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT `Course ID`, Section FROM COURSES;");
            while (rs.next()) {
                ids.add(rs.getString(1) + " - " + rs.getString(2));
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
        frame = new JFrame("Database Info");
        frame.setBounds(100, 100, 900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblViewPrerequisites = new JLabel("View Course Enrollment List");
        lblViewPrerequisites.setBounds(383, 30, 300, 16);
        lblViewPrerequisites.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        frame.getContentPane().add(lblViewPrerequisites);
        
        JButton btnGoBack = new JButton("Go Back");
        btnGoBack.setBounds(6, 6, 117, 29);
        frame.getContentPane().add(btnGoBack);
        
        btnGoBack.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
             frame.setVisible(false);
             frame.dispose();
            } 
          } );
        
        
        JLabel lblCourseId = new JLabel("Course List:");
        lblCourseId.setBounds(62, 71, 79, 16);
        frame.getContentPane().add(lblCourseId);
        
        comboBox = new JComboBox();
        comboBox.setBounds(153, 67, 117, 27);
        frame.getContentPane().add(comboBox);
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(392, 100, 117, 29);
        frame.getContentPane().add(btnSubmit);
        
        btnSubmit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
               submitButtonPressed();
            } 
          } );
        
        ArrayList<String> courseIds = getCourseIds();
        
        textArea = new JTextArea();
        textArea.setBounds(49, 155, 784, 568);
        frame.getContentPane().add(textArea);
        for (int i = 0; i < courseIds.size(); i++) {
            comboBox.addItem(courseIds.get(i));
        }
    }
    public void GetCourses(String courseID) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String split[] = courseID.split(" - ");
        
        if (conn == null) {
            return;
        }
        try {
            stmt = conn.prepareStatement("SELECT DISTINCT student.`First Name`, student.`Last Name`,student.`ASU ID`" +
            "FROM student, grades" + 
            "WHERE `Course ID` = ? AND Section = ? AND grades.`ASU ID` = student.`ASU ID`;");
            stmt.setString(1, split[0]);
            stmt.setInt(2, Integer.parseInt(split[1]));
            rs = stmt.executeQuery();
            
            textArea.setText("First Name\tLast Name\tASU ID\n");
            
            while (rs.next()) {
                textArea.setText(textArea.getText());
            }

        } catch (SQLException e) {
            System.out.println("Sql Error");
            e.printStackTrace();
        }
        finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing sql statement");
                e.printStackTrace();
            }
        }
    }
    
    public void submitButtonPressed() {
        GetCourses((String) comboBox.getSelectedItem());
    }

}

}
