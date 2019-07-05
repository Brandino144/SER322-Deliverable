package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.TextField;
import java.awt.SystemColor;
import javax.swing.JList;

public class HomePage {

    public JFrame frame;
    private Connection conn;
    
    private JButton btnSubmit;
    private JRadioButton rdbtnAllData;
    private JRadioButton rdbtnStudent;
    private JRadioButton rdbtnCourses;
    private JRadioButton rdbtnPrereq;
    private JRadioButton rdbtnCourseEnrollmentList;
    private JRadioButton rdbtnFindStudentGrades;
    private JRadioButton rdbtnGrades;
    private JRadioButton rdbtnFindStudentGpas;
    private JRadioButton rdbtnFindCourseGrades;

    /**
     * Create the application.
     */
    public HomePage(Connection connec) {
        conn = connec;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 900, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("What do you want to do?");
        lblNewLabel.setBounds(372, 33, 154, 16);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblViewDatabase = new JLabel("View Database");
        lblViewDatabase.setBounds(6, 74, 103, 16);
        frame.getContentPane().add(lblViewDatabase);
        
        JLabel lblSelectData = new JLabel("Select Data");
        lblSelectData.setBounds(6, 120, 89, 16);
        frame.getContentPane().add(lblSelectData);
        
        JLabel lblInsert = new JLabel("Insert");
        lblInsert.setBounds(6, 174, 61, 16);
        frame.getContentPane().add(lblInsert);
        
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(393, 229, 117, 29);
        frame.getContentPane().add(btnSubmit);
        
        btnSubmit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
              submitButtonPressed();
            } 
          } );
        
        rdbtnAllData = new JRadioButton("All Data");
        rdbtnAllData.setBounds(16, 96, 141, 23);
        frame.getContentPane().add(rdbtnAllData);
        
        rdbtnStudent = new JRadioButton("Student");
        rdbtnStudent.setBounds(113, 96, 141, 23);
        frame.getContentPane().add(rdbtnStudent);
        
        rdbtnCourses = new JRadioButton("Courses");
        rdbtnCourses.setBounds(208, 96, 141, 23);
        frame.getContentPane().add(rdbtnCourses);
        
        rdbtnPrereq = new JRadioButton("Prerequisites");
        rdbtnPrereq.setBounds(16, 148, 117, 23);
        frame.getContentPane().add(rdbtnPrereq);
        
        rdbtnCourseEnrollmentList = new JRadioButton("Course Enrollment List");
        rdbtnCourseEnrollmentList.setBounds(135, 148, 192, 23);
        frame.getContentPane().add(rdbtnCourseEnrollmentList);
        
        rdbtnFindStudentGrades = new JRadioButton("Find Student Grades");
        rdbtnFindStudentGrades.setBounds(333, 148, 158, 23);
        frame.getContentPane().add(rdbtnFindStudentGrades);
        
        rdbtnGrades = new JRadioButton("Grades");
        rdbtnGrades.setBounds(16, 202, 141, 23);
        frame.getContentPane().add(rdbtnGrades);
        
        rdbtnFindStudentGpas = new JRadioButton("Find Student GPAs");
        rdbtnFindStudentGpas.setBounds(515, 148, 158, 23);
        frame.getContentPane().add(rdbtnFindStudentGpas);
        
        rdbtnFindCourseGrades = new JRadioButton("Find Course Grades");
        rdbtnFindCourseGrades.setBounds(695, 148, 164, 23);
        frame.getContentPane().add(rdbtnFindCourseGrades);
        
        // Add buttons to group so only one can be selected.
        ButtonGroup bg = new ButtonGroup();
        bg.add(btnSubmit);
        bg.add(rdbtnAllData);
        bg.add(rdbtnStudent);
        bg.add(rdbtnPrereq);
        bg.add(rdbtnCourses);
        bg.add(rdbtnCourseEnrollmentList);
        bg.add(rdbtnFindStudentGrades);
        bg.add(rdbtnGrades);
        bg.add(rdbtnFindStudentGpas);
        bg.add(rdbtnFindCourseGrades);
        
        
        
        
    }
    
    public void ViewDataPage(String data) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DataPage window = new DataPage(data, conn);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void ViewPrereqs() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewPrequisites window = new ViewPrequisites(conn);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void InsertGrade() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SubmitNewGrade window = new SubmitNewGrade(conn);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void ViewCourse() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewCourseEnrollmentList window = new ViewCourseEnrollmentList(conn);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void ViewCourseGrades() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewFindCourseGrade window = new ViewFindCourseGrade(conn);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void ViewStudentGPA() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewFindStudentGPA window = new ViewFindStudentGPA(conn);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void ViewStudentGrade() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewFindStudentGrades window = new ViewFindStudentGrades(conn);
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
    
    public void submitButtonPressed() {
        if (rdbtnAllData.isSelected()) {
            ViewDataPage("all");
        } else if (rdbtnStudent.isSelected()) {
            ViewDataPage("student");
        } else if (rdbtnPrereq.isSelected()) {
            ViewPrereqs();
        } else if (rdbtnCourses.isSelected()) {
            ViewDataPage("courses");
        } else if (rdbtnCourseEnrollmentList.isSelected()) {
            ViewDataPage("enrollment");
        } else if (rdbtnFindStudentGrades.isSelected()) {
            ViewDataPage("studentgrades");
        } else if (rdbtnGrades.isSelected()) {
            InsertGrade();
        } else if (rdbtnFindStudentGpas.isSelected()) {
            ViewDataPage("gpa");
        } else if (rdbtnFindCourseGrades.isSelected()) {
            ViewDataPage("coursegrades");
            
        } 
    }
}
