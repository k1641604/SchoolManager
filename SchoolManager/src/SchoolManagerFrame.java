import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class SchoolManagerFrame extends JFrame implements WindowListener{

    JMenuBar menubar = new JMenuBar();
    JTable teachersList = new JTable();
    JTable courseList = new JTable();
    JTable sectionList = new JTable();
    JTable enrollmentList = new JTable();
    JTable studentList = new JTable();

    JTextArea aboutApp = new JTextArea("");
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
        teacher.addActionListener(e ->{showTeacher();});
        JMenuItem student = new JMenuItem ("Student");
        student.addActionListener(e ->{showStudent();});
        JMenuItem course = new JMenuItem ("Course");
        course.addActionListener(e ->{showCourse();});
        JMenuItem section = new JMenuItem ("Section");
        section.addActionListener(e ->{showSection();});
        view.add(teacher);
        view.add(student);
        view.add(course);
        view.add(section);
        aboutApp.setBounds(0, 20, 800, 660);
        add(aboutApp);
        aboutApp.setVisible(false);
        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem ("About");
        about.addActionListener(e ->{clearDisplayAndShowAbout();});
        help.add(about);

        menubar.setBounds(0,0,800,19);
        add(menubar);
        menubar.add(file);
        menubar.add(view);
        menubar.add(help);

        teachersList.setBounds(0, 20, 800, 660);
        add(teachersList);

        studentList.setBounds(0, 20, 800, 660);
        add(studentList);
        studentList.setVisible(false);

        courseList.setBounds(0, 20, 800, 660);
        add(courseList);
        courseList.setVisible(false);

        sectionList.setBounds(0, 20, 800, 660);
        add(sectionList);
        sectionList.setVisible(false);

        enrollmentList.setBounds(0, 20, 800, 660);
        add(enrollmentList);
        enrollmentList.setVisible(false);



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
    public void showTeacher()
    {
        teachersList.setVisible(true);
        studentList.setVisible(false);
        courseList.setVisible(false);
        sectionList.setVisible(false);
        aboutApp.setVisible(false);
    }
    public void showStudent()
    {
        teachersList.setVisible(false);
        studentList.setVisible(true);
        courseList.setVisible(false);
        sectionList.setVisible(false);
        aboutApp.setVisible(false);
    }
    public void showCourse()
    {
        teachersList.setVisible(false);
        studentList.setVisible(false);
        courseList.setVisible(true);
        sectionList.setVisible(false);
        aboutApp.setVisible(false);
    }
    public void showSection()
    {
        teachersList.setVisible(false);
        studentList.setVisible(false);
        courseList.setVisible(false);
        sectionList.setVisible(true);
        aboutApp.setVisible(false);
    }
    public void clearDisplayAndShowAbout()
    {
        teachersList.setVisible(false);
        studentList.setVisible(false);
        courseList.setVisible(false);
        sectionList.setVisible(false);
        aboutApp.setVisible(true);
    }
    public void fileSave()
    {
        try
        {
            File f = new File("Teacher.csv");
            File f1 = new File("Student.csv");
            File f2 = new File("Course.csv");
            File f3 = new File("Selection.csv");
            if(!f.exists())
            {
                f.createNewFile();
            }
            if(!f1.exists())
            {
                f1.createNewFile();
            }
            if(!f2.exists())
            {
                f2.createNewFile();
            }
            if(!f3.exists())
            {
                f3.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(f,false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            /*for(Contact c : Rolodex.n)
            {
                printWriter.println(c.toStore());
            }*/
            fileWriter.close();
            printWriter.close();

            fileWriter = new FileWriter(f,false);
            printWriter = new PrintWriter(fileWriter);
            /*for(Contact c : Rolodex.n)
            {
                printWriter.println(c.toStore());
            }*/
            fileWriter.close();
            printWriter.close();

            fileWriter = new FileWriter(f,false);
            printWriter = new PrintWriter(fileWriter);
            /*for(Contact c : Rolodex.n)
            {
                printWriter.println(c.toStore());
            }*/
            fileWriter.close();
            printWriter.close();

            fileWriter = new FileWriter(f,false);
            printWriter = new PrintWriter(fileWriter);
            /*for(Contact c : Rolodex.n)
            {
                printWriter.println(c.toStore());
            }*/
            fileWriter.close();
            printWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}