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
        JLabel teachers = new JLabel("Teacher Information");
        JTable teachersList = new JTable();
        JLabel tFirst = new JLabel("First Name");
        JTextField teacherFirstName = new JTextField();
        JLabel tLast = new JLabel("Last Name");
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
        JLabel students = new JLabel("Student Information");
        JLabel schedule = new JLabel("Student Schedule");
        JTable studentList = new JTable();
        JLabel sFirst = new JLabel("First Name");
        JLabel sLast = new JLabel("Last Name");
        JTextField studentFirstName = new JTextField();
        JTextField studentLastName = new JTextField();

    JButton deleteTable;

    JRadioButton ACA = new JRadioButton("0) Academic");
    JRadioButton KAP = new JRadioButton("1) KAP");
    JRadioButton  AP = new JRadioButton("2) AP");
    ButtonGroup bg = new ButtonGroup();

    //should fail if textfield / type are left unfilled (pop-up window)
    JButton addCourse = new JButton("Add course to List");

    //should only appear after course has been selected
    JButton removeCourse = new JButton("Remove Course from List");
    JButton editCourse = new JButton("Save Changes to Course");

    //should fail if first / last name are left unfilled (pop-up window)
    JButton addStudent = new JButton("Add Student to List");

    //should only appear after student has been selected
    JButton removeStudent = new JButton("Remove Student from List");
    JButton editStudent = new JButton("Save Changes to Student's Information");

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

            tFirst.setBounds(400, 265,130, 15);
            add(tFirst);
            tFirst.setVisible(false);

            teacherFirstName.setBounds(400, 280, 130, 30);
            teacherFirstName.setBorder(oLine);
            add(teacherFirstName);
            teacherFirstName.setVisible(false);

            tLast.setBounds(600,265, 130, 15);
            add(tLast);
            tLast.setVisible(false);

            teacherLastName.setBounds(600, 280, 130, 30);
            teacherLastName.setBorder(oLine);
            add(teacherLastName);
            teacherLastName.setVisible(false);

        students.setBounds(20, 15, 300, 15);
        add(students);
        students.setVisible(false);

        studentList.setBounds(20, 30, 300, 500);
        studentList.setBorder(oLine);
        add(studentList);
        studentList.setVisible(false);

            schedule.setBounds(400, 15, 300, 15);
            add(schedule);
            schedule.setVisible(false);

            enrollmentPane.setBounds(400, 30, 300, 200);
            enrollmentPane.setBorder(oLine);
            add(enrollmentPane);
            enrollmentPane.setVisible(false);

            sFirst.setBounds(400, 265,130, 15);
            add(tFirst);
            tFirst.setVisible(false);

            studentFirstName.setBounds(400, 280, 130, 30);
            studentFirstName.setBorder(oLine);
            add(studentFirstName);
            studentFirstName.setVisible(false);

            sLast.setBounds(600,265, 130, 15);
            add(tLast);
            tLast.setVisible(false);

            studentLastName.setBounds(600, 280, 130, 30);
            studentLastName.setBorder(oLine);
            add(studentLastName);
            studentLastName.setVisible(false);

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
        //should save data to file? (idk)
        exportData.addActionListener(e -> {exportDataDoer();});
        File.add(importData);
        //pop-up browser allows user to select file to import data from
        importData.addActionListener(e -> {importDataDoer();});
        File.add(purge);
        //deletes all tables (does not affect the SQL), then calls release()
        purge.addActionListener(e -> {purger();});
        File.add(exit);
        //automatically closes the program
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
        tFirst.setVisible(true);
        teacherFirstName.setVisible(true);
        tLast.setVisible(true);
        teacherLastName.setVisible(true);
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
        //all JTextFields should be reset if another menu is opened
        //More things need to be added as more tables, labels, textfields, etc are added to the program
        tFirst.setVisible(false);
        tLast.setVisible(false);
        studentFirstName.setVisible(false);
        studentLastName.setVisible(false);
        students.setVisible(false);
        schedule.setVisible(false);
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
        students.setVisible(true);
        schedule.setVisible(true);
        studentList.setVisible(true);
        enrollmentPane.setVisible(true);
        tFirst.setVisible(true);
        studentFirstName.setVisible(true);
        studentLastName.setVisible(true);
        tLast.setVisible(true);
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
        //all JTextFields should be reset if another menu is opened
        //More things need to be added as more tables, labels, textfields, etc are added to the program
        teachers.setVisible(false);
        secTab.setVisible(false);
        tFirst.setVisible(false);
        teacherFirstName.setVisible(false);
        tLast.setVisible(false);
        teacherLastName.setVisible(false);
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
        //all JTextFields should be reset if another menu is opened
        //More things need to be added as more tables, labels, textfields, etc are added to the program
        tFirst.setVisible(false);
        tLast.setVisible(false);
        studentFirstName.setVisible(false);
        studentLastName.setVisible(false);
        students.setVisible(false);
        schedule.setVisible(false);
        teachers.setVisible(false);
        secTab.setVisible(false);
        tFirst.setVisible(false);
        teacherFirstName.setVisible(false);
        tLast.setVisible(false);
        teacherLastName.setVisible(false);
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

    }
    public void openSection(){
        //all JTextFields should be reset if another menu is opened
        //More things need to be added as more tables, labels, textfields, etc are added to the program
    }


    public void helpTable(){
        //no idea what this does
    }

    public void exportDataDoer(){}
    public void importDataDoer(){}
    public void purger(){}
    public void release(){}
}


