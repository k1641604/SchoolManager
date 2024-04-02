import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.border.Border;

public class SchoolManagerFrame extends JFrame{

    JMenuBar menubar = new JMenuBar();
    JMenu View = new JMenu("View");
    JMenu Help = new JMenu("Help");
    JMenu File = new JMenu("File");
    JTable teachersList = new JTable();
    JTable courseList = new JTable();
    JTable sectionList = new JTable();
    JTable enrollmentList = new JTable();
    JTable studentList = new JTable();

    JButton deleteTables = new JButton();


    public SchoolManagerFrame() {
        super("School Manager");
        setSize(800, 700);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Border oLine = BorderFactory.createLineBorder(Color.black);

        add(menubar);
        ViewMenuCreator();
        menubar.add(View);
        HelpMenuCreator();
        menubar.add(Help);
        FileMenuCreator();
        menubar.add(File);
        setJMenuBar(menubar);

        try {
            File f = new File("SchoolManager.txt");
            Scanner fromFile = new Scanner(f);
            String a = null;
            while(fromFile.hasNextLine()){

                String[] parts = fromFile.nextLine().split(",");
                if (parts.equals("")){
                    break;
                }
            }
            System.out.println("String is "+ a);
        }
        catch (Exception b){
            b.printStackTrace();
            System.out.println("Helo");
        }

        setVisible(true);
    }


    public void ViewMenuCreator(){
        JMenuItem teacher = new JMenuItem("Teachers");
        JMenuItem student = new JMenuItem("Students");
        JMenuItem courses = new JMenuItem("Courses");
        JMenuItem section = new JMenuItem("Sections");
        View.add(teacher);
        teacher.addActionListener(e -> {teacherTable();});
        View.add(student);
        student.addActionListener(e -> {studentTable();});
        View.add(courses);
        courses.addActionListener(e -> {coursesTable();});
        View.add(section);
        section.addActionListener(e -> {sectionsTable();});
    }
    public void HelpMenuCreator(){
        JMenuItem about = new JMenuItem("About");
        Help.add(about);
        about.addActionListener(e -> {helpTable();});
    }
    public void FileMenuCreator(){
        JMenuItem exportData = new JMenuItem("Export Data");
        JMenuItem importData = new JMenuItem("Import Data");
        JMenuItem purge = new JMenuItem("Purge");
        JMenuItem exit = new JMenuItem("Exit");
        File.add(exportData);
        exportData.addActionListener(e -> {exportDataDoer();});
        File.add(importData);
        importData.addActionListener(e -> {importDataDoer();});
        File.add(purge);
        purge.addActionListener(e -> {purger();});
        File.add(exit);
        exit.addActionListener(e -> {release();});
    }

    public void teacherTable(){
        try {
            File f = new File("TeacherInfo.txt");
            Scanner fromFile = new Scanner(f);
            String a = null;
            while(fromFile.hasNextLine()){

                String[] parts = fromFile.nextLine().split(",");
                if (parts.equals("")){
                    break;
                }
                Teacher t = new Teacher(parts[0],parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
                info.add(t);
                stuff.setListData(info.toArray());
            }
            System.out.println("String is "+ a);
        }
        catch (Exception b){
            b.printStackTrace();
            System.out.println("Helo");
        }
        String[] columnNames = {"id", "First Name", "Last Name"};
        teachersList = new JTable(teacherData, columnNames);
        teachersList.setBounds(300, 500, 20, 20);
        Border oLine  = BorderFactory.createLineBorder(Color.black);;
        teachersList.setBorder(oLine);
        setVisible(true);
    }
    public void studentTable(){}
    public void coursesTable(){}
    public void sectionsTable(){}

    public void helpTable(){}

    public void exportDataDoer(){}
    public void importDataDoer(){}
    public void purger(){}
    public void release(){}
}
class Teacher implements Comparable {
    private int teacherID;
    private String teacherFirstName;
    private String teacherLastName;
    //private Section secInfo;

    public Teacher(int id, String firstName, String lastName){
        this.teacherID = id;
        this.teacherFirstName = firstName;
        this.teacherLastName = lastName;
    }

    public int getId(){
        return teacherID;
    }

    public String getFirstName(){
        return teacherFirstName;
    }

    public String getLastName(){
        return teacherLastName;
    }

    /*public Section getSecInfo(){
        return secInfo;
    }*/

    public void getId(int id){
        this.teacherID = id;
    }

    public void getFirstName(String firstName){
        this.teacherFirstName = firstName;
    }

    public void getLastname(String lastName){
        this.teacherLastName = lastName;
    }

    /*public void getSecInfo(Section secInfo){
        this.secInfo = secInfo;
    }*/

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

class Student implements Comparable {
    private int studentID;
    private String studentFirstName;
    private String studentLastName;

    //private Course schedule;

    public Student(){
        this.studentID = studentID;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        //this.schedule = schedule;
    }

    public int getId(){
        return studentID;
    }

    public String getFirstName(){
        return studentFirstName;
    }

    public String getLastName(){
        return studentLastName;
    }

    /*public Section getSchedule(){
        return schedule;
    }*/

    public void getId(int id){
        this.studentID = id;
    }

    public void getFirstName(String firstName){
        this.studentFirstName = firstName;
    }

    public void getLastname(String lastName){
        this.studentLastName = lastName;
    }

    /*public void getSchedule(Section secInfo){
        this.schedule = schedule;
    }*/

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}

class Course implements Comparable{
    private int courseID;

    private String courseName;

    private int courseType;

    

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}


