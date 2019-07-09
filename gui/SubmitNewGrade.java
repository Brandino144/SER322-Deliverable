package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;

public class SubmitNewGrade {

    public JFrame frame;
    private JComboBox comboBox;
    private JComboBox comboBox_1;
    private Connection conn;
    private JTextField txtEnterGpa;
    private JTextField textEnterSection;

    /**
     * Create the application.
     */
    public SubmitNewGrade(Connection connec) {
        conn = connec;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Database Info");
        frame.setBounds(100, 100, 900, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Submit New Grade");
        lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblNewLabel.setBounds(351, 18, 215, 16);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblAsuId = new JLabel("ASU ID:");
        lblAsuId.setBounds(69, 75, 61, 16);
        frame.getContentPane().add(lblAsuId);
        
        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setBounds(69, 132, 61, 16);
        frame.getContentPane().add(lblCourse);
        
        JLabel lblGrade = new JLabel("Grade (0.00-4.00)");
        lblGrade.setBounds(69, 190, 125, 16);
        frame.getContentPane().add(lblGrade);
        
        JLabel lblSection = new JLabel("Section #");
        lblSection.setBounds(69, 240, 155, 16);
        frame.getContentPane().add(lblSection);
        
        comboBox = new JComboBox();
        comboBox.setBounds(142, 71, 125, 27);
        frame.getContentPane().add(comboBox);
        ArrayList<String> ASUIDs = ViewFindStudentGrades.getStudentIds(conn);
        
        for(int i = 0; i < ASUIDs.size(); i++) {
            comboBox.addItem(ASUIDs.get(i));
        }
        
        comboBox_1 = new JComboBox();
        comboBox_1.setBounds(142, 128, 171, 27);
        frame.getContentPane().add(comboBox_1);
        
        ArrayList<String> CourseIDs = fillComboBox2();
        
        for(int i = 0; i < CourseIDs.size(); i++) {
            comboBox_1.addItem(CourseIDs.get(i));
        }
        
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitButton();
            }
        });
        btnSubmit.setBounds(378, 283, 117, 29);
        frame.getContentPane().add(btnSubmit);
        
        JButton btnGoBack = new JButton("Go Back");
        btnGoBack.setBounds(6, 6, 117, 29);
        frame.getContentPane().add(btnGoBack);
        
        txtEnterGpa = new JTextField();
        txtEnterGpa.setBounds(206, 185, 130, 26);
        frame.getContentPane().add(txtEnterGpa);
        txtEnterGpa.setColumns(10);
        
        textEnterSection = new JTextField();
        textEnterSection.setBounds(206, 240, 130, 26);
        frame.getContentPane().add(textEnterSection);
        textEnterSection.setColumns(10);
        
        btnGoBack.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
             frame.setVisible(false);
             frame.dispose();
            } 
          } );
        
    }
    
    public ArrayList<String> fillComboBox2() {
        Statement stmt = null;
        ResultSet rs = null;
        
        ArrayList<String> ids = new ArrayList<String>();
        
        try {
            //String query = "SELECT * FROM GRADES";
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT DISTINCT `COURSE ID` FROM GRADES");
            
            while (rs.next()) {
                ids.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ids;
    }
    
    public void submitButton() {
        PreparedStatement stmt = null;
        try {
            String asuid = (String)comboBox.getSelectedItem();
            String grade = txtEnterGpa.getText();
            String section = textEnterSection.getText();
            String course =  (String) comboBox_1.getSelectedItem();
            
            
            
            String query = "INSERT INTO `Grade_Keeper_2` . `GRADES` (`ASU ID`, `Course ID`, `Section`, `Grade`)"
                    + " VALUES (? , ? , ?,  ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,  asuid);
            stmt.setString(2,  course);
            stmt.setInt(3, Integer.parseInt(section));
            stmt.setDouble(4, Double.parseDouble(grade));
            stmt.execute();
            JOptionPane.showMessageDialog(null, "New grade submitted!");
        }
        catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

