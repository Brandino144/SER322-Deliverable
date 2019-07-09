package gui;

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
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ViewFindStudentGPA {

    private Connection conn;
    public JFrame frame;
    private JTextArea table;
    private JComboBox comboBox;

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
        
        btnSubmit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
               submitButtonPressed();
            } 
          } );
        
        
        table = new JTextArea();
        table.setBounds(49, 155, 784, 568);
        frame.getContentPane().add(table);
        
        
        
        ArrayList<String> studentIds = ViewFindStudentGrades.getStudentIds(conn);
        
        comboBox = new JComboBox();
        comboBox.setBounds(242, 71, 125, 27);
        frame.getContentPane().add(comboBox);
        for (int i = 0; i < studentIds.size(); i++) {
            comboBox.addItem(studentIds.get(i));
        }
    }
    
    public void GetGPA(String asuid) {
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int totalUnits = 0;
        double weightedGrade = 0;

        ArrayList<String> courses = new ArrayList<String>();
        ArrayList<Double> grades = new ArrayList<Double>();
        ArrayList<Integer> units = new ArrayList<Integer>();
        
        if (conn == null) {
            return;
        }
        try {
            stmt = conn.prepareStatement("SELECT * FROM GRADES WHERE `ASU ID` = ?");
            stmt.setString(1, asuid);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                courses.add(rs.getString(3));
                grades.add(new Double(rs.getDouble(5)));
            }
            
            for (int i = 0; i < courses.size(); i++) {
               
                stmt = conn.prepareStatement("SELECT `Number of Credits` FROM COURSES WHERE `Course ID` = ?");
                stmt.setString(1, courses.get(i));
                rs = stmt.executeQuery();
                
                while (rs.next()) {
                    units.add(new Integer(rs.getInt(1)));
                }
                
                totalUnits += units.get(i);
                weightedGrade += units.get(i) * grades.get(i);
            }
            
            double GPA = weightedGrade / totalUnits;
            table.setText("Student GPA = " + GPA);
            
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
        GetGPA((String) comboBox.getSelectedItem());
    }
    

}
