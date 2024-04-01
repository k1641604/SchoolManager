import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class SchoolManagerFrame extends JFrame{

    JMenuBar menubar = new JMenuBar();
    JTable teachersList = new JTable();
    JTable courseList = new JTable();
    JTable sectionList = new JTable();
    JTable enrollmentList = new JTable();
    JTable studentList = new JTable();

    JButton deleteTables = new JButton();


    public SchoolManagerFrame(){
        super("School Manager");
        setSize(800, 700);
        setLayout(null);

        menubar.setBounds(0,0,800,19);
        add(menubar);

        teachersList.setBounds(20, 20, 148, 660);
        add(teachersList);

        courseList.setBounds(178, 20, 148, 660);
        add(courseList);

        sectionList.setBounds(336, 20, 148, 660);
        add(sectionList);

        enrollmentList.setBounds(494, 20, 148, 660);
        add(enrollmentList);

        studentList.setBounds(651, 20, 148, 660);
        add(studentList);

        //deleteTables.setBounds();

        setVisible(true);
    }


}