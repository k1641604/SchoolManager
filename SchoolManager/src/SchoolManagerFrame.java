import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class SchoolManagerFrame extends JFrame implements WindowListener{

    JMenuBar menubar = new JMenuBar();
    JTable teachersList = new JTable();
    ArrayList<Teachers> teacher = new ArrayList<Teachers>();
    JTable studentList = new JTable();
    ArrayList<Students> student = new ArrayList<Students>();
    JTable courseList = new JTable();
    ArrayList<Courses> course = new ArrayList<Courses>();
    JTable sectionList = new JTable();
    ArrayList<Sections> section = new ArrayList<Sections>();
    JTable enrollmentList = new JTable();
    ArrayList<Enrollment> enrollment = new ArrayList<Enrollment>();
    JTextArea aboutApp = new JTextArea("");
    JButton deleteTables = new JButton();


    public SchoolManagerFrame(Connection c){
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

        teachersList.setBounds(5, 20, 450, 660);
        add(teachersList);

        studentList.setBounds(5, 20, 450, 660);
        add(studentList);
        studentList.setVisible(false);

        courseList.setBounds(5, 20, 450, 660);
        add(courseList);
        courseList.setVisible(false);

        sectionList.setBounds(5, 20, 450, 660);
        add(sectionList);
        sectionList.setVisible(false);

        enrollmentList.setBounds(5, 20, 450, 660);
        add(enrollmentList);
        enrollmentList.setVisible(false);
        try {
            Statement statement = c.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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
        enrollmentList.setVisible(false);
        aboutApp.setVisible(false);
    }
    public void showStudent()
    {
        teachersList.setVisible(false);
        studentList.setVisible(true);
        courseList.setVisible(false);
        sectionList.setVisible(false);
        enrollmentList.setVisible(false);
        aboutApp.setVisible(false);
    }
    public void showCourse()
    {
        teachersList.setVisible(false);
        studentList.setVisible(false);
        courseList.setVisible(true);
        sectionList.setVisible(false);
        enrollmentList.setVisible(false);
        aboutApp.setVisible(false);
    }
    public void showSection()
    {
        teachersList.setVisible(false);
        studentList.setVisible(false);
        courseList.setVisible(false);
        sectionList.setVisible(true);
        enrollmentList.setVisible(false);
        aboutApp.setVisible(false);
    }
    public void clearDisplayAndShowAbout()
    {
        teachersList.setVisible(false);
        studentList.setVisible(false);
        courseList.setVisible(false);
        sectionList.setVisible(false);
        enrollmentList.setVisible(false);
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
            File f4 = new File("Enrollment.csv");
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
            if(!f4.exists())
            {
                f4.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(f,false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for(Teachers c : teacher)
            {
                printWriter.println(c.toStore());
            }
            fileWriter.close();
            printWriter.close();

            fileWriter = new FileWriter(f1,false);
            printWriter = new PrintWriter(fileWriter);
            for(Students c : student)
            {
                printWriter.println(c.toStore());
            }
            fileWriter.close();
            printWriter.close();

            fileWriter = new FileWriter(f2,false);
            printWriter = new PrintWriter(fileWriter);
            for(Courses c : course)
            {
                printWriter.println(c.toStore());
            }
            fileWriter.close();
            printWriter.close();

            fileWriter = new FileWriter(f3,false);
            printWriter = new PrintWriter(fileWriter);
            for(Sections c : section)
            {
                printWriter.println(c.toStore());
            }
            fileWriter.close();
            printWriter.close();
            fileWriter = new FileWriter(f4,false);
            printWriter = new PrintWriter(fileWriter);
            for(Enrollment c : enrollment)
            {
                printWriter.println(c.toStore());
            }
            fileWriter.close();
            printWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void readFile()
    {
        try {
            File f = new File("Teacher.csv");
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner from = new Scanner(f);
            while (from.hasNext()) {
                String[] parts = from.nextLine().split(",");
                Teachers c;
                c = new Teachers(Integer.parseInt(parts[0]), parts[1], parts[2]);
                teacher.add(c);
            }
            from.close();
            File f1 = new File("Student.csv");
            if (!f1.exists()) {
                f1.createNewFile();
            }
            from = new Scanner(f1);
            while (from.hasNext()) {
                String[] parts = from.nextLine().split(",");
                Students c;
                c = new Students(Integer.parseInt(parts[0]), parts[1], parts[2]);
                student.add(c);
            }
            from.close();
            File f2 = new File("Course.csv");
            if (!f2.exists()) {
                f2.createNewFile();
            }
            from = new Scanner(f2);
            while (from.hasNext()) {
                String[] parts = from.nextLine().split(",");
                Courses c;
                c = new Courses(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]));
                course.add(c);
            }
            from.close();
            File f3 = new File("Section.csv");
            if (!f3.exists()) {
                f3.createNewFile();
            }
            from = new Scanner(f3);
            while (from.hasNext()) {
                String[] parts = from.nextLine().split(",");
                Sections c;
                c = new Sections(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                section.add(c);
            }
            from.close();
            File f4 = new File("Enrollment.csv");
            if (!f4.exists()) {
                f4.createNewFile();
            }
            from = new Scanner(f4);
            while (from.hasNext()) {
                String[] parts = from.nextLine().split(",");
                Enrollment c;
                c = new Enrollment(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                enrollment.add(c);
            }
            from.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}