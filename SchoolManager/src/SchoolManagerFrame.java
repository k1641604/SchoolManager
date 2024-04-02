import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class SchoolManagerFrame extends JFrame implements WindowListener{

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
        JMenu file = new JMenu("File");
        JMenuItem exportData = new JMenuItem ("Export Data");
        JMenuItem importData = new JMenuItem ("Import Data");
        JMenuItem purge = new JMenuItem ("Purge");
        JMenuItem exit = new JMenuItem ("Exit");
        file.add(exportData);
        file.add(importData);
        file.add(purge);
        file.add(exit);
        JMenu view = new JMenu("View");
        JMenuItem teacher = new JMenuItem ("Teacher");
        JMenuItem student = new JMenuItem ("Student");
        JMenuItem course = new JMenuItem ("Course");
        JMenuItem section = new JMenuItem ("Section");
        view.add(teacher);
        view.add(student);
        view.add(course);
        view.add(section);
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem ("About");
        help.add(about);

        menubar.setBounds(0,0,800,19);
        add(menubar);
        menubar.add(file);
        menubar.add(view);
        menubar.add(help);

        teachersList.setBounds(0, 20, 800, 660);
        add(teachersList);

        courseList.setBounds(0, 20, 800, 660);
        //add(courseList);

        sectionList.setBounds(0, 20, 800, 660);
        //add(sectionList);

        enrollmentList.setBounds(0, 20, 800, 660);
        //add(enrollmentList);

        studentList.setBounds(0, 20, 800, 660);
        //add(studentList);

        //deleteTables.setBounds();

        setVisible(true);
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}