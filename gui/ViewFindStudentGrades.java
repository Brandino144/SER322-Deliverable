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
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class ViewFindStudentGrades {

    private Connection conn;
    public JFrame frame;
    private JTextArea table;
    private JComboBox comboBox;

    /**
     * Create the application.
     */
    public ViewFindStudentGrades(Connection connec) {
        conn = connec;
        initialize();
    }
    
    public ArrayList<String> getStudentIds() {
        Statement stmt = null;
        ResultSet rs = null;
        
        ArrayList<String> ids = new ArrayList<String>();

        if (conn == null) {
            return null;
        }
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT `ASU ID` FROM STUDENT");
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
        
        JLabel lblViewFindStudent = new JLabel("View Find Student Grades");
        lblViewFindStudent.setBounds(367, 6, 181, 16);
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
        btnSubmit.setBounds(382, 58, 117, 29);
        frame.getContentPane().add(btnSubmit);
        
        btnSubmit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
               submitButtonPressed();
            } 
          } );
        
        
        table = new JTextArea();
        table.setBounds(49, 155, 784, 568);
        frame.getContentPane().add(table);
        
        ArrayList<String> studentIds = getStudentIds();
        
        comboBox = new JComboBox();
        comboBox.setBounds(242, 71, 125, 27);
        frame.getContentPane().add(comboBox);
        for (int i = 0; i < studentIds.size(); i++) {
            comboBox.addItem(studentIds.get(i));
        }
        
        
        
        
    }
    
    public void GetGrades(String asuid) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        if (conn == null) {
            return;
        }
        try {
            stmt = conn.prepareStatement("SELECT * FROM GRADES WHERE `ASU ID` = ?");
            stmt.setString(1, asuid);
            rs = stmt.executeQuery();
            
            table.setText("Course ID\tSection\tGrade\n");
            while (rs.next()) {
                String newline = rs.getString(3) + "\t" 
                                + rs.getString(4) + "\t" 
                                + rs.getString(5) + "\n";
                table.setText(table.getText()+newline);
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
        GetGrades((String) comboBox.getSelectedItem());
        
        
    }
    


}
