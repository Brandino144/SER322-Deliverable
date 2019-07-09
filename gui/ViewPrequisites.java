package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JTextArea;

public class ViewPrequisites {

    private Connection conn;
    public JFrame frame;
    private JTextArea textArea;
    private JComboBox comboBox;
    private ArrayList<String> courseIds2;

    /**
     * Create the application.
     */
    public ViewPrequisites(Connection connec) {
        conn = connec;
        initialize();
        try {
            Statement mystmt = conn.createStatement();
            String sql = "SELECT * "
                    + " FROM courses;";
            ResultSet rs = mystmt.executeQuery(sql);
            
            textArea.setText("Course ID\tName\tTerm\n");
            while (rs.next()) {
            String newline = rs.getString(1) + "\t" + rs.getString(2) + "\t" +rs.getString(3) + "\n";
            textArea.setText(textArea.getText()+newline);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
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
            rs = stmt.executeQuery("SELECT `COURSE ID` FROM COURSES;");
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
        frame = new JFrame("Database Info");
        frame.setBounds(100, 100, 900, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblViewPrerequisites = new JLabel("View Prerequisites");
        lblViewPrerequisites.setBounds(383, 30, 156, 16);
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
        
        
        JLabel lblCourseId = new JLabel("Course ID:");
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

        if (conn == null) {
            return;
        }
        try {
            stmt = conn.prepareStatement("SELECT PREREQUISITES FROM COURSES WHERE `COURSE ID` = ?");
            stmt.setString(1, courseID);
            rs = stmt.executeQuery();
            
            textArea.setText("Course ID\tName\tNumber of Credits\n");
            
            while (rs.next()) {
                getPrerequisites(rs.getString(1));
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
    public ArrayList<String> getPrerequisites(String prereq) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<String> ids = new ArrayList<String>();

        if (conn == null) {
            return null;
        }
        try {
            stmt = conn.prepareStatement("SELECT * FROM COURSES WHERE `COURSE ID` = ?");
            stmt.setString(1, prereq);
            rs = stmt.executeQuery();
            
            textArea.setText("Course ID\tName\t\tNumber of Credits\n");
            
            while (rs.next()) {
                String newline = rs.getString(1) + "\t" 
                                + rs.getString(3) + "\t" 
                                + rs.getString(5) + "\n";
                textArea.setText(textArea.getText()+newline);
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
        return ids;
    }
    
    
    public void submitButtonPressed() {
        GetCourses((String) comboBox.getSelectedItem());
    }

}
