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
        JLabel teachers = new JLabel("Teachers");
        JTable teachersList = new JTable();
        JTextField teacherFirstName = new JTextField();
        JTextField teacherLastName = new JTextField();
    JLabel cListName = new JLabel("Course List");
    JTable courseList = new JTable();
    JLabel course = new JLabel("Course Name");
    JTextField courseName = new JTextField("");
        JTable sectionList = new JTable();

        JLabel secTab = new JLabel("Sections Taught");
        JTable sectionsTab = new JTable();
    JTable enrollmentList;
    JScrollPane enrollmentPane = new JScrollPane();
        JTable studentList = new JTable();
        JTextField studentFirstName = new JTextField();
        JTextField studentLastName = new JTextField();

    JButton deleteTable;

    JRadioButton ACA = new JRadioButton("0) Academic");
    JRadioButton KAP = new JRadioButton("1) KAP");
    JRadioButton  AP = new JRadioButton("2) AP");
    ButtonGroup bg = new ButtonGroup();
    JButton addCourse = new JButton("Add course to List");
    JButton removeCourse = new JButton("Remove Course from List");
    JButton editCourse = new JButton("Save Changes to Course");

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


        teachers.setBounds(20, 15, 300, 15);
        add(teachers);
        teachers.setVisible(false);

        teachersList.setBounds(20, 30, 300, 500);
        teachersList.setBorder(oLine);
        add(teachersList);
        teachersList.setVisible(false);

            secTab.setBounds(400, 15,300, 15 );
            add(secTab);
            secTab.setVisible(false);

            sectionsTab.setBounds(400, 30, 300, 200);
            sectionsTab.setBorder(oLine);
            add(sectionsTab);
            sectionsTab.setVisible(false);

            teacherFirstName.setBounds();
            

        studentList.setBounds(20, 20, 300, 500);
        studentList.setBorder(oLine);
        add(studentList);
        studentList.setVisible(false);

            enrollmentPane.setBounds(400, 20, 300, 200);
            enrollmentPane.setBorder(oLine);
            add(enrollmentPane);
            enrollmentPane.setVisible(false);

        cListName.setBounds(20, 15, 300, 15);
        add(cListName);
        cListName.setVisible(false);

        courseList.setBounds(20, 30, 300, 500);
        courseList.setBorder(oLine);
        add(courseList);
        courseList.setVisible(false);

            course.setBounds(400, 15,300, 15 );
            add(course);
            course.setVisible(false);

            courseName.setBounds(400, 30, 300, 50);
            courseName.setBorder(oLine);
            add(courseName);
            courseName.setVisible(false);

            ACA.setBounds(400, 100, 100, 30);
            ACA.setBorder(oLine);
            bg.add(ACA);
            add(ACA);
            ACA.setVisible(false);

            KAP.setBounds(400, 130, 75, 30);
            KAP.setBorder(oLine);
            bg.add(KAP);
            add(KAP);
            KAP.setVisible(false);

            AP.setBounds(400, 160, 75, 30);
            AP.setBorder(oLine);
            bg.add(AP);
            add(AP);
            AP.setVisible(false);

        addCourse.setBounds(400, 220, 300, 30);
        add(addCourse);
        addCourse.addActionListener(e -> {courseAdder();});
        addCourse.setVisible(false);

        removeCourse.setBounds(400, 280, 300, 30);
        add(removeCourse);
        removeCourse.addActionListener(e -> {courseRemover();});
        removeCourse.setVisible(false);

        editCourse.setBounds(400, 340, 300, 30);
        add(editCourse);
        editCourse.addActionListener(e -> {courseEditor();});
        editCourse.setVisible(false);

            sectionList.setBounds(20, 20, 300, 500);
            sectionList.setBorder(oLine);
            add(sectionList);
            sectionList.setVisible(false);



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
        openTeacher();
        System.out.println("aaaaaaaaaaaaaaaaaaaa");
        //setVisible(false);
        teachers.setVisible(true);
        teachersList.setVisible(true);
        secTab.setVisible(true);
        sectionsTab.setVisible(true);
        /*try {File f = new File("TeacherInfo.txt");
            Scanner fromFile = new Scanner(f);
            String a = null;
            while(fromFile.hasNextLine()){String[] parts = fromFile.nextLine().split(",");
                if (parts.equals("")){break;}
                Teacher t = new Teacher( );
                info.add(t);
                stuff.setListData(info.toArray());}
            System.out.println("String is "+ a);}
        catch (Exception b){b.printStackTrace();
            System.out.println("Helo");}*/
        String[] columnNames = {"id", "First Name", "Last Name"};
        //teachersList = new JTable(teacherData, columnNames);
    }
    public void openTeacher(){
        studentList.setVisible(false);
        enrollmentPane.setVisible(false);
        cListName.setVisible(false);
        courseList.setVisible(false);
        course.setVisible(false);
        courseName.setVisible(false);
        ACA.setVisible(false);
        KAP.setVisible(false);
        AP.setVisible(false);
        addCourse.setVisible(false);
        removeCourse.setVisible(false);
        editCourse.setVisible(false);
    }

    public void studentTable(){
        openStudent();
        System.out.println("bbbbbbbbbbbbbbbbbbbb");
        //setVisible(false);
        studentList.setVisible(true);
        enrollmentPane.setVisible(true);
                /*try {File f = new File("StudentInfo.txt");
            Scanner fromFile = new Scanner(f);
            String a = null;
            while(fromFile.hasNextLine()){String[] parts = fromFile.nextLine().split(",");
                if (parts.equals("")){break;}
                Student s = new Student();
                info.add(s);
                stuff.setListData(info.toArray());}
            System.out.println("String is "+ a);}
        catch (Exception b){b.printStackTrace();
            System.out.println("Helo");}*/
        String[] columnNames = {"id", "First Name", "Last Name"};
        //studentList = new JTable(studentData, columnNames);
    }
    public void openStudent(){
        teachersList.setVisible(false);
        sectionsTab.setVisible(false);
        cListName.setVisible(false);
        courseList.setVisible(false);
        course.setVisible(false);
        courseName.setVisible(false);
        ACA.setVisible(false);
        KAP.setVisible(false);
        AP.setVisible(false);
        addCourse.setVisible(false);
        removeCourse.setVisible(false);
        editCourse.setVisible(false);
    }

    public void coursesTable(){
        openCourse();
        System.out.println("cccccccccccccccccccc");
        //setVisible(false);
            cListName.setVisible(true);
            courseList.setVisible(true);
            course.setVisible(true);
            courseName.setVisible(true);
            ACA.setVisible(true);
            KAP.setVisible(true);
            AP.setVisible(true);
            addCourse.setVisible(true);
            removeCourse.setVisible(true);
            editCourse.setVisible(true);
        //enrollmentPane.setVisible(true);
                /*try {File f = new File("CourseInfo.txt");
            Scanner fromFile = new Scanner(f);
            String a = null;
            while(fromFile.hasNextLine()){String[] parts = fromFile.nextLine().split(",");
                if (parts.equals("")){break;}
                Course c = new Course();
                info.add(c);
                stuff.setListData(info.toArray());}
            System.out.println("String is "+ a);}
        catch (Exception b){b.printStackTrace();
            System.out.println("Helo");}*/
        String[] columnNames = {"id", "Title", "Type"};
        //courseList = new JTable(courseData, columnNames);
    }
    public void openCourse(){
        teachersList.setVisible(false);
        sectionsTab.setVisible(false);
        studentList.setVisible(false);
        enrollmentPane.setVisible(false);
    }

    public void courseAdder(){}

    public void courseRemover(){}

    public void courseEditor(){}

    public void sectionsTable(){
        System.out.println("dddddddddddddddddddd");
        //setVisible(false);

    }
    public void openSection(){

    }


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


