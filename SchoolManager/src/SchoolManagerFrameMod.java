import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.border.Border;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;

import static javax.swing.JOptionPane.showMessageDialog;

public class SchoolManagerFrameMod extends JFrame implements WindowListener {

    DefaultTableModel tableModelTeacher = new DefaultTableModel();
    DefaultTableModel tableModelStudent = new DefaultTableModel();
    DefaultTableModel tableModelSection = new DefaultTableModel();
    DefaultTableModel tableModelCourse = new DefaultTableModel();
    DefaultTableModel tableModelRoster = new DefaultTableModel();
    DefaultTableModel tableModelTaught = new DefaultTableModel();
    DefaultTableModel tableModelSchedule = new DefaultTableModel();
    JMenuBar menubar = new JMenuBar();
    JMenu View = new JMenu("View");
    JMenu Help = new JMenu("Help");
    JMenu File = new JMenu("File");
        JLabel teachers = new JLabel("Teacher Information");
        JTable teachersList = new JTable(tableModelTeacher);
        JScrollPane teacherArea;

    JTable Roster = new JTable(tableModelRoster);
    JScrollPane rosterArea;

        JLabel tFirst = new JLabel("First Name");
        JTextField teacherFirstName = new JTextField();
        JLabel tLast = new JLabel("Last Name");
        JTextField teacherLastName = new JTextField();
    JLabel cListName = new JLabel("Course List");
    JTable courseList = new JTable(tableModelCourse);
    JScrollPane courseArea;
    JLabel course = new JLabel("Course Name");
    JTextField courseName = new JTextField("");
        JTable sectionList = new JTable(tableModelSection);
        JScrollPane sectionArea;
        JLabel secTab = new JLabel("Sections Taught");
        JTable sectionsTaughtList = new JTable(tableModelTaught);
        JScrollPane sectionsTaughtArea = new JScrollPane();
    JTable enrollmentList;
    JScrollPane enrollmentPane = new JScrollPane();
        JLabel students = new JLabel("Student Information");
        JLabel schedule = new JLabel("Student Schedule");
        JTable studentList = new JTable(tableModelStudent);
        JScrollPane studentArea;
        JLabel sFirst = new JLabel("First Name");
        JLabel sLast = new JLabel("Last Name");
        JList studentStuff = new JList();
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
    JButton addTeacher = new JButton("Add Teacher to List");
    JButton addSection = new JButton("Add Section to List");

    //should only appear after student has been selected
    JButton removeStudent = new JButton("Remove Student from List");
    JButton editStudent = new JButton("Save Changes to Student's Information");
    JButton removeTeacher = new JButton("Remove Teacher from List");
    JButton editTeacher  = new JButton("Save Changes to Teacher's Information");
    JButton removeSection = new JButton("Remove Section from List");
    JButton editSection  = new JButton("Save Changes to Section Information");
    JTable courseBox = new JTable(tableModelSchedule);
    JScrollPane courseBoxArea;
    JLabel secInfo = new JLabel("Section Information");
    JLabel availableCourseLabel = new JLabel("Available courses and teachers");
    JLabel cidData = new JLabel("");
    JLabel tid = new JLabel("Teacher id");
    JLabel tidData = new JLabel("");
    JComboBox<Courses> available = new JComboBox<Courses>();
    ArrayList<Courses> availableList = new ArrayList<Courses>();
    JComboBox<Teachers> teacherbox = new JComboBox<Teachers>();
    ArrayList<Teachers> teacherboxList = new ArrayList<Teachers>();
    JComboBox<Students> studentBox = new JComboBox<Students>();
    ArrayList<Students> studentboxList = new ArrayList<Students>();
    JButton add2Sec = new JButton("Add Student");
    JButton remFmSec = new JButton("Remove Student");
    ArrayList<Students> studentInfo = new ArrayList<Students>();
    ArrayList<Teachers> teacherInfo = new ArrayList<Teachers>();
    ArrayList<Courses> courseInfo = new ArrayList<Courses>();
    ArrayList<Sections> sectionInfo = new ArrayList<Sections>();
    ArrayList<Enrollment> enrollmentInfo = new ArrayList<Enrollment>();


    Connection con;

    Statement s;

    int rank;

    public SchoolManagerFrameMod(Connection c) {
                super("School Manager");
                setSize(800, 700);
                setLayout(null);
                addWindowListener(this);
                Border oLine = BorderFactory.createLineBorder(Color.black);
                this.con = c;
                try {
                    this.s = this.con.createStatement();
                }
                catch (SQLException e){
                    throw new RuntimeException(e);
                }

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

        teacherArea = new JScrollPane(teachersList);
        teachersList.getSelectionModel().addListSelectionListener(e ->{
            if(!e.getValueIsAdjusting())
            {updateSelectionTeacher();}
            else
            {return ;}});

        tableModelRoster.addColumn("Last Name");
        tableModelRoster.addColumn("First Name");
        tableModelRoster.addColumn("Student id");
        rosterArea = new JScrollPane(Roster);

        teacherArea.setBounds(20, 30, 300, 500);
        tableModelTeacher.addColumn("Teacher id");
        tableModelTeacher.addColumn("First Name");
        tableModelTeacher.addColumn("Last Name");
        teacherArea.setBorder(oLine);
        add(teacherArea);
        teacherArea.setVisible(false);

        tableModelSchedule.addColumn("Section id");
        tableModelSchedule.addColumn("Course Name");
        tableModelSchedule.addColumn("Teacher id");
        tableModelSchedule.addColumn("Teacher FN");
        tableModelSchedule.addColumn("Teacher LN");


            secTab.setBounds(400, 15,300, 15 );
            add(secTab);
            secTab.setVisible(false);



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

            addTeacher.setBounds(400, 340, 300, 30);
            addTeacher.addActionListener(e -> {addTeacherButton();});
            add(addTeacher);
            addTeacher.setVisible(false);

            removeTeacher.setBounds(400, 400, 300, 30);
            removeTeacher.addActionListener(e -> {removeItemTeacher();});
            add(removeTeacher);
            removeTeacher.setVisible(false);

            editTeacher.setBounds(400, 340, 300, 30);
            editTeacher.addActionListener(e -> {editSelectionTeacher();});
            add(editTeacher);
            editTeacher.setVisible(false);

        addSection.setBounds(400, 110, 300, 30);
        addSection.addActionListener(e -> {addSectionButton();});
        add(addSection);
        addSection.setVisible(false);

        removeSection.setBounds(400, 160, 300, 30);
        removeSection.addActionListener(e -> {removeItemSection();});
        add(removeSection);
        removeSection.setVisible(false);

        editSection.setBounds(400, 110, 300, 30);
        editSection.addActionListener(e -> {editSelectionSection();});
        add(editSection);
        editSection.setVisible(false);

        students.setBounds(20, 15, 300, 15);
        add(students);
        students.setVisible(false);

        studentList.getSelectionModel().addListSelectionListener(e ->{
            if(!e.getValueIsAdjusting())
            {updateSelectionStudent();}
            else
            {return ;}});
        studentArea = new JScrollPane(studentList);
        studentArea.setBounds(20, 30, 300, 500);
        tableModelStudent.addColumn("Student id");
        tableModelStudent.addColumn("First Name");
        tableModelStudent.addColumn("Last Name");
        studentArea.setBorder(oLine);
        add(studentArea);
        studentArea.setVisible(false);

            schedule.setBounds(400, 15, 300, 15);
            add(schedule);
            schedule.setVisible(false);

            courseBoxArea = new JScrollPane(courseBox, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        courseBoxArea.setBounds(350, 30, 410, 230);
        courseBoxArea.setBorder(oLine);
            add(courseBoxArea);
        courseBoxArea.setVisible(false);



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

            addStudent.setBounds(400, 340, 300, 30);
            addStudent.addActionListener(e -> {addStudentButton();});
            add(addStudent);
            addStudent.setVisible(false);

            removeStudent.setBounds(400, 400, 300, 30);
            removeStudent.addActionListener(e -> {removeItemStudent();});
            add(removeStudent);
            removeStudent.setVisible(false);

            editStudent.setBounds(400, 340, 300, 30);
            editStudent.addActionListener(e -> {editSelectionStudent();});
            add(editStudent);
            editStudent.setVisible(false);

        cListName.setBounds(20, 15, 300, 15);
        add(cListName);
        cListName.setVisible(false);
        courseList.getSelectionModel().addListSelectionListener(e ->{
            if(!e.getValueIsAdjusting())
            {updateSelectionCourse();}
            else
            {return ;}});
        sectionList.getSelectionModel().addListSelectionListener(e ->{
            if(!e.getValueIsAdjusting())
            {updateSelectionSection();}
            else
            {return ;}});
        courseArea = new JScrollPane(courseList);
        courseArea.setBounds(20, 30, 300, 500);
        tableModelCourse.addColumn("Course id");
        tableModelCourse.addColumn("Course Name");
        tableModelCourse.addColumn("Type");
        courseArea.setBorder(oLine);
        add(courseArea);
        courseArea.setVisible(false);

            course.setBounds(400, 15,300, 15 );
            add(course);
            course.setVisible(false);

            courseName.setBounds(400, 30, 300, 50);
            courseName.setBorder(oLine);
            add(courseName);
            courseName.setVisible(false);

            ACA.setBounds(400, 100, 100, 30);
            ACA.setBorder(oLine);
            ACA.addActionListener(e -> {setACA();});
            bg.add(ACA);
            add(ACA);
            ACA.setVisible(false);

            KAP.setBounds(400, 130, 75, 30);
            KAP.setBorder(oLine);
            KAP.addActionListener(e -> {setKAP();});
            bg.add(KAP);
            add(KAP);
            KAP.setVisible(false);

            AP.setBounds(400, 160, 75, 30);
            AP.setBorder(oLine);
            AP.addActionListener(e -> {setAP();});
            bg.add(AP);
            add(AP);
            AP.setVisible(false);

        addCourse.setBounds(400, 220, 300, 30);
        add(addCourse);
        addCourse.addActionListener(e -> {addCourseButton();});
        addCourse.setVisible(false);

        removeCourse.setBounds(400, 280, 300, 30);
        add(removeCourse);
        removeCourse.addActionListener(e -> {removeItemCourse();});
        removeCourse.setVisible(false);

        editCourse.setBounds(400, 220, 300, 30);
        add(editCourse);
        editCourse.addActionListener(e -> {editSelectionCourse();});
        editCourse.setVisible(false);
        tableModelSection.addColumn("Section id");
        tableModelSection.addColumn("Course id");
        tableModelSection.addColumn("Teacher id");
            sectionArea = new JScrollPane(sectionList);
            sectionArea.setBounds(20, 30, 300, 500);

        tableModelTaught.addColumn("Section id");
        tableModelTaught.addColumn("Course title");
        sectionsTaughtArea = new JScrollPane(sectionsTaughtList);
        sectionsTaughtArea.setBounds(400, 30, 300, 200);
        sectionsTaughtArea.setBorder(oLine);
        add(sectionsTaughtArea);
        sectionsTaughtArea.setVisible(false);

        sectionArea.setBorder(oLine);
        add(sectionArea);
        secInfo.setBounds(20, 15, 300, 15);
        add(secInfo);
        secInfo.setVisible(false);
        //available.setBounds();
        sectionArea.setVisible(false);
        availableCourseLabel.setBounds(400, 15, 300, 15);
        add(availableCourseLabel);
        availableCourseLabel.setVisible(false);
        available.setBounds(400, 35, 300,25);
        add(available);
        available.setVisible(false);
        teacherbox.setBounds(400, 65, 300,25);
        add(teacherbox);
        teacherbox.setVisible(false);
        cidData.setBounds(400, 35, 300, 15);
        add(cidData);
        cidData.setVisible(false);
        studentBox.setBounds(400, 500, 300, 25);
        add(studentBox);
        studentBox.setVisible(false);
        rosterArea.setBounds(400, 250,300,200);
        add(rosterArea);
        rosterArea.setVisible(false);
        add2Sec.setBounds(375, 550, 150,50);
        add2Sec.addActionListener(e -> {});
        add(add2Sec);
        add2Sec.setVisible(false);
        remFmSec.setBounds(575, 550, 150, 50);
        remFmSec.addActionListener(e -> {});
        add(remFmSec);
        remFmSec.setVisible(false);
        try {
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement n = connect.createStatement();
            String s;
            s = "CREATE TABLE IF NOT EXISTS teacher (teacher_id INTEGER Not Null AUTO_INCREMENT, first_name Text, last_name Text, PRIMARY KEY(teacher_id));";
            n.execute(s);
            s = "CREATE TABLE IF NOT EXISTS student (student_id INTEGER Not Null AUTO_INCREMENT, first_name Text, last_name Text, PRIMARY KEY(student_id));";
            n.execute(s);
            s = "CREATE TABLE IF NOT EXISTS course (course_id INTEGER Not Null AUTO_INCREMENT, course_name Text Not Null, type INTEGER Not Null, PRIMARY KEY(course_id));";
            n.execute(s);
            s = "CREATE TABLE IF NOT EXISTS section (section_id INTEGER Not Null AUTO_INCREMENT, course_id INTEGER Not Null,  teacher_id INTEGER Not Null, PRIMARY KEY(section_id), FOREIGN KEY(course_id) references course(course_id) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(teacher_id) references teacher(teacher_id) ON DELETE CASCADE ON UPDATE CASCADE);";
            n.execute(s);
            s = "CREATE TABLE IF NOT EXISTS enrollment (section_id INTEGER Not Null, student_id INTEGER Not Null , PRIMARY KEY(section_id,student_id), FOREIGN KEY(section_id) references section(section_id) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(student_id) references student(student_id) ON DELETE CASCADE ON UPDATE CASCADE);";
            n.execute(s);
            ResultSet rs = n.executeQuery("SELECT first_name FROM teacher WHERE teacher_id=-1;");
            if (!rs.isBeforeFirst() ) {
                s = "INSERT INTO teacher (teacher_id, first_name, last_name) VALUES (-1, 'Teacher', 'No');";
                n.execute(s);
            }
            addToJTableDataTeachers();
            addToJTableDataStudent();
            addToJTableDataCourses();
            addToJTableDataSections();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshCourseSelection();
        refreshTeacherSelection();
        refreshStudentSelection();

        /*try {
            readFile();
        }
        catch (Exception b){
            b.printStackTrace();
            System.out.println("Helo");
        }*/
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
        addToJTableDataTeachers();
        System.out.println("aaaaaaaaaaaaaaaaaaaa");
        //setVisible(false);
        teachers.setVisible(true);
        teacherArea.setVisible(true);
        secTab.setVisible(true);
        sectionsTaughtArea.setVisible(true);
        tFirst.setVisible(true);
        teacherFirstName.setVisible(true);
        tLast.setVisible(true);
        teacherLastName.setVisible(true);
        addTeacher.setVisible(true);
        removeTeacher.setVisible(true);
        sectionsTaughtArea.setVisible(true);
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
        courseBox.setVisible(false);
        studentArea.setVisible(false);
        addStudent.setVisible(false);
        removeStudent.setVisible(false);
        editStudent.setVisible(false);
        enrollmentPane.setVisible(false);
        cListName.setVisible(false);
        courseArea.setVisible(false);
        course.setVisible(false);
        courseName.setVisible(false);
        ACA.setVisible(false);
        KAP.setVisible(false);
        AP.setVisible(false);
        addCourse.setVisible(false);
        removeCourse.setVisible(false);
        secInfo.setVisible(false);
        availableCourseLabel.setVisible(false);
        available.setVisible(false);
        teacherbox.setVisible(false);
        studentBox.setVisible(false);
        rosterArea.setVisible(false);
        editSection.setVisible(false);
        removeSection.setVisible(false);
        addSection.setVisible(false);
        courseBoxArea.setVisible(false);
        remFmSec.setVisible(false);
        add2Sec.setVisible(false);
    }

    public void teacherAdder(){}
    public void teacherRemover(){}
    public void teacherEditor(){}

    public void studentTable(){
        openStudent();
        addToJTableDataStudent();
        System.out.println("bbbbbbbbbbbbbbbbbbbb");
        //setVisible(false);
        editStudent.setVisible(true);
        students.setVisible(true);
        schedule.setVisible(true);
        studentArea.setVisible(true);
        courseBox.setVisible(true);
        tFirst.setVisible(true);
        studentFirstName.setVisible(true);
        studentLastName.setVisible(true);
        tLast.setVisible(true);
        addStudent.setVisible(true);
        removeStudent.setVisible(true);
        courseBoxArea.setVisible(true);
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
        teacherArea.setVisible(false);
        addTeacher.setVisible(false);
        removeTeacher.setVisible(false);
        editTeacher.setVisible(false);
        sectionsTaughtArea.setVisible(false);
        cListName.setVisible(false);
        courseArea.setVisible(false);
        course.setVisible(false);
        courseName.setVisible(false);
        editCourse.setVisible(false);
        ACA.setVisible(false);
        KAP.setVisible(false);
        AP.setVisible(false);
        addCourse.setVisible(false);
        removeCourse.setVisible(false);
        secInfo.setVisible(false);
        availableCourseLabel.setVisible(false);
        available.setVisible(false);
        teacherbox.setVisible(false);
        studentBox.setVisible(false);
        rosterArea.setVisible(false);
        sectionsTaughtArea.setVisible(false);
        editSection.setVisible(false);
        removeSection.setVisible(false);
        addSection.setVisible(false);
        remFmSec.setVisible(false);
        add2Sec.setVisible(false);
    }

    public void studentAdder(){
        if (studentFirstName.getText().isEmpty() || studentLastName.getText().isEmpty()){
            int value = JOptionPane.showConfirmDialog(null, "Students require a First AND a Last name");
        }
        else{
            System.out.println("Student "+studentFirstName.getText()+" "+studentLastName.getText()+" was added to the list");
        }
    }
    public void studentRemover(){
        //if (/*should check if the student that is being removed actually exists*/){
        //    int value = JOptionPane.showConfirmDialog(null, "A student which exists in the system must be selected");
        //}
       // else {
       //     System.out.println("Student "+studentFirstName.getText()+" "+studentLastName.getText()+" has been removed from the list");
       // }
    }
    public void studentEditor(){}

    public void coursesTable(){
        openCourse();
        //addToJTableDataCourses();
        System.out.println("cccccccccccccccccccc");
        //setVisible(false);
            cListName.setVisible(true);
            courseArea.setVisible(true);
            course.setVisible(true);
            courseName.setVisible(true);
            ACA.setVisible(true);
            KAP.setVisible(true);
            AP.setVisible(true);
            addCourse.setVisible(true);
            removeCourse.setVisible(true);
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
        if (ACA.isSelected()){
            rank = 0;
            System.out.println("Course rank is Academic");
        }
        if (KAP.isSelected()){
            rank = 1;
            System.out.println("Course rank is KAP");
        }
        if (AP.isSelected()) {
            rank = 2;
            System.out.println("Course rank is AP");
        }

    }
    public void setACA(){rank = 0;
        System.out.println("Course rank is Academic");
    }
    public void setKAP(){rank = 1;
        System.out.println("Course rank is KAP");
    }
    public void setAP(){rank = 2;
        System.out.println("Course rank is AP");
    }

    public void openCourse(){
        //all JTextFields should be reset if another menu is opened
        //More things need to be added as more tables, labels, textfields, etc are added to the program
        tFirst.setVisible(false);
        tLast.setVisible(false);
        addTeacher.setVisible(false);
        removeTeacher.setVisible(false);
        editTeacher.setVisible(false);
        studentFirstName.setVisible(false);
        studentLastName.setVisible(false);
        addStudent.setVisible(false);
        removeStudent.setVisible(false);
        editStudent.setVisible(false);
        students.setVisible(false);
        schedule.setVisible(false);
        teachers.setVisible(false);
        secTab.setVisible(false);
        tFirst.setVisible(false);
        teacherFirstName.setVisible(false);
        tLast.setVisible(false);
        teacherLastName.setVisible(false);
        teacherArea.setVisible(false);
        sectionsTaughtArea.setVisible(false);
        studentArea.setVisible(false);
        courseBox.setVisible(false);
        secInfo.setVisible(false);
        availableCourseLabel.setVisible(false);
        available.setVisible(false);
        teacherbox.setVisible(false);
        studentBox.setVisible(false);
        rosterArea.setVisible(false);
        sectionsTaughtArea.setVisible(false);
        editSection.setVisible(false);
        removeSection.setVisible(false);
        addSection.setVisible(false);
        courseBoxArea.setVisible(false);
        remFmSec.setVisible(false);
        add2Sec.setVisible(false);
    }
    public void courseEditor(){

    }

    public void sectionsTable(){
        System.out.println("dddddddddddddddddddd");
        sectionArea.setVisible(true);
        secInfo.setVisible(true);
        availableCourseLabel.setVisible(true);
        available.setVisible(true);
        teacherbox.setVisible(true);
        studentBox.setVisible(true);
        rosterArea.setVisible(true);
        addSection.setVisible(true);
        //editSection.setVisible(true);
        removeSection.setVisible(true);
        remFmSec.setVisible(true);
        add2Sec.setVisible(true);
        refreshCourseSelection();
        refreshTeacherSelection();
        refreshStudentSelection();
        openSection();
        //addToJTableDataSections();
    }
    public void openSection(){
        //all JTextFields should be reset if another menu is opened
        //More things need to be added as more tables, labels, textfields, etc are added to the program
        teachers.setVisible(false);
        teacherArea.setVisible(false);
        secTab.setVisible(false);
        sectionsTaughtArea.setVisible(false);
        tFirst.setVisible(false);
        teacherFirstName.setVisible(false);
        tLast.setVisible(false);
        teacherLastName.setVisible(false);
        addTeacher.setVisible(false);
        removeTeacher.setVisible(false);
        editTeacher.setVisible(false);
        students.setVisible(false);
        schedule.setVisible(false);
        tFirst.setVisible(false);
        studentFirstName.setVisible(false);
        studentLastName.setVisible(false);
        studentArea.setVisible(false);
        tLast.setVisible(false);
        addStudent.setVisible(false);
        removeStudent.setVisible(false);
        editStudent.setVisible(false);
        cListName.setVisible(false);
        courseArea.setVisible(false);
        courseBox.setVisible(false);
        course.setVisible(false);
        courseName.setVisible(false);
        ACA.setVisible(false);
        KAP.setVisible(false);
        AP.setVisible(false);
        addCourse.setVisible(false);
        removeCourse.setVisible(false);
        editCourse.setVisible(false);
        sectionsTaughtArea.setVisible(false);
        courseBoxArea.setVisible(false);
    }


    public void helpTable(){
        showMessageDialog(this, "Creator: Maximo San Juan+, Fanghua liang \n Version: 0.0.1");
    }

    public void exportDataDoer(){
        fileSave();
    }
    public void importDataDoer(){
        purger();
        readFile();
    }
    public void purger(){
        try
        {
            Connection connectForPurge = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement np = connectForPurge.createStatement();
            String sp = "DROP TABLE IF EXISTS enrollment;";
            np.execute(sp);
            sp = "DROP TABLE IF EXISTS section;";
            np.execute(sp);
            sp = "DROP TABLE IF EXISTS teacher;";
            np.execute(sp);
            sp = "DROP TABLE IF EXISTS student;";
            np.execute(sp);
            sp = "DROP TABLE IF EXISTS course;";
            np.execute(sp);
            sp = "CREATE TABLE IF NOT EXISTS teacher (teacher_id INTEGER Not Null AUTO_INCREMENT, first_name Text, last_name Text, PRIMARY KEY(teacher_id));";
            np.execute(sp);
            sp = "CREATE TABLE IF NOT EXISTS student (student_id INTEGER Not Null AUTO_INCREMENT, first_name Text, last_name Text, PRIMARY KEY(student_id));";
            np.execute(sp);
            sp = "CREATE TABLE IF NOT EXISTS course (course_id INTEGER Not Null AUTO_INCREMENT, course_name Text Not Null, type INTEGER Not Null, PRIMARY KEY(course_id));";
            np.execute(sp);
            sp = "CREATE TABLE IF NOT EXISTS section (section_id INTEGER Not Null AUTO_INCREMENT, course_id INTEGER Not Null,  teacher_id INTEGER Not Null, PRIMARY KEY(section_id), FOREIGN KEY(course_id) references course(course_id) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(teacher_id) references teacher(teacher_id) ON DELETE CASCADE ON UPDATE CASCADE);";
            np.execute(sp);
            sp = "CREATE TABLE IF NOT EXISTS enrollment (section_id INTEGER Not Null, student_id INTEGER Not Null , PRIMARY KEY(section_id,student_id), FOREIGN KEY(section_id) references section(section_id) ON DELETE CASCADE ON UPDATE CASCADE, FOREIGN KEY(student_id) references student(student_id) ON DELETE CASCADE ON UPDATE CASCADE);";
            np.execute(sp);
            sp = "INSERT INTO teacher (teacher_id, first_name, last_name) VALUES (-1, 'Teacher', 'No');";
            np.execute(sp);
            addToJTableDataTeachers();
            addToJTableDataStudent();
            addToJTableDataCourses();
            addToJTableDataSections();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void release(){
        this.dispose();
    }

    public void addToJTableDataTeachers()
    {
        //referenced code from Knowledge to Share's YouTube video;
        String state = "select * from teacher where teacher_id>-1";
        try {Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement jt = con.createStatement();
            ResultSet rst = jt.executeQuery(state);
            DefaultTableModel tableMod =  (DefaultTableModel) teachersList.getModel();
            tableMod.setRowCount(0);
            while(rst.next())
            {
                String id = String.valueOf(rst.getInt("teacher_id"));
                String first = rst.getString("first_name");
                String last = rst.getString("last_name");
                String[] toAdd = {id, first, last};
                tableMod.addRow(toAdd);
                repaint();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addToJTableDataStudent()
    {
        //referenced code from Knowledge to Share's YouTube video;
        String state = "select * from student";
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement js = con.createStatement();
            ResultSet rss = js.executeQuery(state);
            DefaultTableModel tableMod =  (DefaultTableModel) studentList.getModel();
            tableMod.setRowCount(0);
            while(rss.next())
            {
                String id = String.valueOf(rss.getInt("student_id"));
                String first = rss.getString("first_name");
                String last = rss.getString("last_name");
                String[] toAdd = {id, first, last};
                tableMod.addRow(toAdd);
                repaint();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToJTableDataCourses()
    {
        //referenced code from Knowledge to Share's YouTube video;
        String state = "select * from course";
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement jc = con.createStatement();
            ResultSet rsc = jc.executeQuery(state);
            DefaultTableModel tableMod =  (DefaultTableModel) courseList.getModel();
            tableMod.setRowCount(0);
            while(rsc.next())
            {
                String id = String.valueOf(rsc.getInt("course_id"));
                String title = rsc.getString("course_name");
                int t = rsc.getInt("type");
                String type;
                if(t == 0)
                {
                    type = "ACA";
                } else if (t == 1) {
                    type = "KAP";
                }
                else
                {
                    type = "AP";
                }
                String[] toAdd = {id, title, type};
                tableMod.addRow(toAdd);
                repaint();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToJTableDataSections()
    {
        //referenced code from Knowledge to Share's YouTube video;
        String state = "select * from section";
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement jsc = con.createStatement();
            ResultSet rsse = jsc.executeQuery(state);
            DefaultTableModel tableMod =  (DefaultTableModel) sectionList.getModel();
            tableMod.setRowCount(0);
            while(rsse.next())
            {
                String sec = rsse.getString("section_id");
                String cou = String.valueOf(rsse.getInt("course_id"));
                String teach = String.valueOf(rsse.getInt("teacher_id"));
                String[] toAdd = {sec, cou, teach};
                System.out.println(sec + " " + cou + " " + teach);
                tableMod.addRow(toAdd);
                repaint();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToSqlTeacher(Teachers t, boolean fromFile)
    {
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            String state;
            if(!fromFile)
            {
                state = "INSERT INTO teacher (first_name, last_name) VALUES ('" + t.getFirstName() +"', '" + t.getLastName() + "');";
            }
            else
            {
                state = "INSERT INTO teacher (teacher_id, first_name, last_name) VALUES (" + t.getTeacherID() + ", '" + t.getFirstName() +"', '" + t.getLastName() + "');";
            }
            Statement te = con.createStatement();
            boolean value = te.execute(state);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addToSqlStudent(Students st, boolean fromFile)
    {
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            String state;
            if(!fromFile)
            {
                state = "INSERT INTO student (first_name, last_name) VALUES ('" + st.getFirstName() +"', '" + st.getLastName() + "');";
            }
            else
            {
                state = "INSERT INTO student (student_id, first_name, last_name) VALUES (" + st.getStudentID() + ", '" + st.getFirstName() +"', '" + st.getLastName() + "');";
            }
            Statement stu = con.createStatement();
            boolean value = stu.execute(state);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addToSqlCourse(Courses c, boolean fromFile)
    {
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            String state;
            if(!fromFile)
            {
                state  = "INSERT INTO course (course_name, type) VALUES ('" + c.getTitle() +"', " + c.getType() + ");";
            }
            else
            {
                state = "INSERT INTO course (course_id, course_name, type) VALUES (" + c.getCourseID() + ", '"+ c.getTitle() + "', " + c.getType() + ");";
            }
            Statement co = con.createStatement();
            boolean value = co.execute(state);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addToSqlSection(Sections se, boolean fromFile)
    {
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            String state;
            if(!fromFile)
            {
                state  = "INSERT INTO section (course_id, teacher_id) VALUES (" + se.getCourseID() +", " + se.getTeacherID() + ");";
            }
            else
            {
                state = "INSERT INTO section (section_id, course_id, teacher_id) VALUES (" + se.getSectionID()+", " + se.getCourseID() +", " + se.getTeacherID() + "');";
            }
            Statement sec = this.con.createStatement();
            boolean value = sec.execute(state);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addToSqlEnrollment(Enrollment en)
    {
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            String state;
                state  = "INSERT INTO enrollment (section_id, student_id) VALUES (" + en.getSectionID() +", " + en.getStudentID() + ");";
            Statement sec = this.con.createStatement();
            boolean value = sec.execute(state);
        } catch (SQLException e) {
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
                teacherInfo.add(c);
                addToSqlTeacher(c, true);
                addToJTableDataTeachers();
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
                studentInfo.add(c);
                addToSqlStudent(c, true);
                addToJTableDataStudent();
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
                courseInfo.add(c);
                addToSqlCourse(c, true);
                addToJTableDataCourses();
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
                sectionInfo.add(c);
                addToSqlSection(c, true);
                //addToJTableDataSections();
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
                enrollmentInfo.add(c);
                addToSqlEnrollment(c);
            }
            from.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void fileSave()
    {
        try
        {
            File f = new File("Teacher.csv");
            if(!f.exists())
            {
                f.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(f,false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement fs = connect.createStatement();
            String state =  "select * from teacher where teacher_id>-1";
            try {
                ResultSet rs = fs.executeQuery(state);
                while(rs.next())
                {
                    String id = String.valueOf(rs.getInt("teacher_id"));
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    printWriter.println(id +"," + first + "," + last);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            fileWriter.close();
            printWriter.close();

            File f1 = new File("Student.csv");
            if(!f1.exists())
            {
                f1.createNewFile();
            }
            Connection connects = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement fss = connects.createStatement();
            fileWriter = new FileWriter(f1,false);
            printWriter = new PrintWriter(fileWriter);
            state = "select * from student";
            try {
                ResultSet rs = fss.executeQuery(state);
                while(rs.next())
                {
                    String id = String.valueOf(rs.getInt("student_id"));
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    printWriter.println(id +"," + first + "," + last);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            fileWriter.close();
            printWriter.close();

            File f2 = new File("Course.csv");
            if(!f2.exists())
            {
                f2.createNewFile();
            }
            Connection connectss = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement fsss = connectss.createStatement();
            fileWriter = new FileWriter(f2,false);
            printWriter = new PrintWriter(fileWriter);
            state = "select * from course";
            try {
                ResultSet rs = fsss.executeQuery(state);
                while(rs.next())
                {
                    String id = String.valueOf(rs.getInt("course_id"));
                    String title = rs.getString("course_name");
                    String t = String.valueOf(rs.getInt("type"));
                    printWriter.println(id + "," + title + "," + t);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            fileWriter.close();
            printWriter.close();
            File f3 = new File("section.csv");
            if(!f3.exists())
            {
                f3.createNewFile();
            }
            Connection connectsss = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement fssss = connectsss.createStatement();
            fileWriter = new FileWriter(f3,false);
            printWriter = new PrintWriter(fileWriter);
            state = "select * from section";
            try {
                ResultSet rs = fssss.executeQuery(state);
                while(rs.next())
                {
                    String sec = rs.getString("section_id");
                    String cou = String.valueOf(rs.getInt("course_id"));
                    String teach = String.valueOf(rs.getInt("teacher_id"));
                    printWriter.println(sec + "," + cou + "," + teach);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            fileWriter.close();
            printWriter.close();

            File f4 = new File("Enrollment.csv");
            if(!f4.exists())
            {
                f4.createNewFile();
            }
            Connection connectssss = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement fsssss = connectssss.createStatement();
            fileWriter = new FileWriter(f4,false);
            printWriter = new PrintWriter(fileWriter);
            state = "select * from enrollment";
            try {
                ResultSet rs = fsssss.executeQuery(state);
                while(rs.next())
                {
                    String sec = String.valueOf(rs.getInt("section_id"));
                    String stu = String.valueOf(rs.getInt("student_id"));
                    printWriter.println(sec + "," + stu);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            fileWriter.close();
            printWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void addTeacherButton()
    {
        if(teacherFirstName.getText().equals("") || teacherLastName.getText().equals(""))
        {
            showMessageDialog(this, "Please complete all fields! B");
            return ;
        }
        Teachers t = new Teachers(teacherFirstName.getText(), teacherLastName.getText());
        teacherInfo.add(t);
        addToSqlTeacher(t, false);
        addToJTableDataTeachers();
        teacherFirstName.setText("");
        teacherLastName.setText("");
    }
    public void addStudentButton()
    {
        if(studentFirstName.getText().equals("") || studentLastName.getText().equals(""))
        {
            showMessageDialog(this, "Please complete all fields! C");
            return ;
        }
        Students t = new Students(studentFirstName.getText(), studentLastName.getText());
        studentInfo.add(t);
        addToSqlStudent(t, false);
        addToJTableDataStudent();
        studentFirstName.setText("");
        studentLastName.setText("");
    }
    public void addCourseButton()
    {
        int a;
        if(ACA.isSelected())
        {
            a = 0;
        } else if (KAP.isSelected()) {
            a = 1;
        }
        else if(AP.isSelected())
        {
            a = 2;
        }
        else
        {
            showMessageDialog(this, "Please complete all fields! D");
            return ;
        }
        if(courseName.getText().equals(""))
        {
            showMessageDialog(this, "Please complete all fields! E");
            return ;
        }
        Courses t = new Courses(courseName.getText(), a);
        courseInfo.add(t);
        addToSqlCourse(t, false);
        addToJTableDataCourses();
        courseName.setText("");
        bg.clearSelection();
    }
    public void addSectionButton()
        {
            System.out.println("adding");
            Courses c = (Courses) available.getSelectedItem();
            System.out.println(c + "this is course");
            Teachers t = (Teachers) teacherbox.getSelectedItem();
            System.out.println(t+ "this is teacher");
            Sections s = new Sections(c.getCourseID(), t.getTeacherID());
            sectionInfo.add(s);
            addToSqlSection(s, false);
            addToJTableDataSections();
        }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        //works
        try {
            this.con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.exit(0);
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
    public void removeItemTeacher()
    {
        if(teachersList.getSelectionModel().isSelectionEmpty())
        {
            showMessageDialog(this, "Please select one row to delete. E");
            return;
        }
        DefaultTableModel tableMod = (DefaultTableModel) teachersList.getModel();
        int row = teachersList.getSelectedRow();
        int id = Integer.parseInt(tableMod.getValueAt(row,0).toString());
        String s = "UPDATE section SET "+ "teacher_id=-1" +" WHERE " + "teacher_id="+ id + ";";
        Connection rtc = null;
        try {
            rtc = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement rts = rtc.createStatement();
            rts.execute(s);
            s = "DELETE FROM teacher WHERE teacher_id=" + id +";";
            rts.execute(s);
            s = "UPDATE section SET "+ "teacher_id=-1" +" WHERE " + "teacher_id="+ id + ";";
            rts.execute(s);
            tableMod.removeRow(row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        teacherFirstName.setText("");
        teacherLastName.setText("");
        addToJTableDataSections();

    }
    public void removeItemStudent()
    {
        if(studentList.getSelectionModel().isSelectionEmpty())
        {
            showMessageDialog(this, "Please select one row to delete. F");
            return;
        }
        DefaultTableModel tableMod = (DefaultTableModel) studentList.getModel();
        int row = studentList.getSelectedRow();
        int id = Integer.parseInt(tableMod.getValueAt(row,0).toString());
        Connection rtcs = null;
        try {
            rtcs = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement rtss = rtcs.createStatement();
            String s = "DELETE FROM student WHERE student_id=" + id +";";
            rtss.execute(s);
            tableMod.removeRow(row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        studentFirstName.setText("");
        studentLastName.setText("");
    }
    public void removeItemCourse()
    {
        if(courseList.getSelectionModel().isSelectionEmpty())
        {
            showMessageDialog(this, "Please select one row to delete. G");
            return;
        }
        DefaultTableModel tableMod = (DefaultTableModel) courseList.getModel();
        int row = courseList.getSelectedRow();
        int id = Integer.parseInt(tableMod.getValueAt(row,0).toString());
        Connection rtcss = null;
        try {
            rtcss = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement rtsss = rtcss.createStatement();
            String s = "DELETE FROM course WHERE course_id=" + id +";";
            rtsss.execute(s);
            tableMod.removeRow(row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        courseName.setText("");
        bg.clearSelection();
    }
    public void removeItemSection()
    {
        if(sectionList.getSelectionModel().isSelectionEmpty())
        {
            showMessageDialog(this, "Please select one row to delete. H");
            return;
        }
        DefaultTableModel tableMod = (DefaultTableModel) sectionList.getModel();
        int row = sectionList.getSelectedRow();
        int id = Integer.parseInt(tableMod.getValueAt(row,0).toString());
        Connection rtcsss = null;
        try {
            rtcsss = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement rtssss = rtcsss.createStatement();
            String s = "DELETE FROM section WHERE section_id=" + id +";";
            rtssss.execute(s);
            tableMod.removeRow(row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void removeItemEnrollment()
    {
    }
    public void updateSelectionTeacher()
    {
        addTeacher.setVisible(false);
        editTeacher.setVisible(true);
        DefaultTableModel tbModel = (DefaultTableModel)  teachersList.getModel();
        if(teachersList.getSelectedRowCount() == 1 )
        {
            int row = teachersList.getSelectedRow();
            teacherFirstName.setText(tbModel.getValueAt(row,1).toString());
            teacherLastName.setText(tbModel.getValueAt(row,2).toString());
            int id = Integer.parseInt(tbModel.getValueAt(row,0).toString());
            try
            {
                Connection rtcu = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/schoolmanager","root","password");
                Statement rtu = rtcu.createStatement();
                ResultSet rs = rtu.executeQuery("SELECT section_id,course_id FROM section WHERE teacher_id=" + id +";");
                DefaultTableModel d = (DefaultTableModel) sectionsTaughtList.getModel();
                d.setRowCount(0);
                while(rs.next())
                {
                    String sid = String.valueOf(rs.getInt("section_id"));
                    String courseName = courseNameFromID(rs.getInt("course_id"));
                    String[] toAdd = {sid, courseName};
                    d.addRow(toAdd);
                    repaint();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public void updateSelectionStudent()
    {
        addStudent.setVisible(false);
        editStudent.setVisible(true);
        DefaultTableModel tbModel = (DefaultTableModel)  studentList.getModel();
        if(studentList.getSelectedRowCount() == 1 )
        {
            int row = studentList.getSelectedRow();
            studentFirstName.setText(tbModel.getValueAt(row,1).toString());
            studentLastName.setText(tbModel.getValueAt(row,2).toString());
        }
    }
    public void updateSelectionCourse()
    {
        addCourse.setVisible(false);
        editCourse.setVisible(true);
        DefaultTableModel tbModel = (DefaultTableModel)  courseList.getModel();
        if(courseList.getSelectedRowCount() == 1 )
        {
            int row = courseList.getSelectedRow();
            courseName.setText(tbModel.getValueAt(row,1).toString());
            if(tbModel.getValueAt(row,2).toString().equals("ACA"))
            {
                ACA.setSelected(true);
            } else if (tbModel.getValueAt(row,2).toString().equals("KAP")) {
                KAP.setSelected(true);
            }
            else
            {
                AP.setSelected(true);
            }
        }
    }
    public void updateSelectionSection()
    {
        Teachers t = null;
        Courses c = null;
        addSection.setVisible(false);
        editSection.setVisible(true);
        DefaultTableModel tbModel = (DefaultTableModel)  sectionList.getModel();
        if(sectionList.getSelectedRowCount() == 1 )
        {
            int row = sectionList.getSelectedRow();
            int sid = Integer.parseInt(tbModel.getValueAt(row,2).toString());
            int cid = Integer.parseInt(tbModel.getValueAt(row,1).toString());
            int tid = Integer.parseInt(tbModel.getValueAt(row,0).toString());
            int stun = 0;
            try {
                Connection rtcu = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/schoolmanager","root","password");
                Statement rtu = rtcu.createStatement();
                ResultSet rs = rtu.executeQuery("SELECT first_name,last_name FROM teacher WHERE teacher_id=" + tid +";");
                String sec = null;
                String stu = null;
                while(rs.next())
                {
                    sec = rs.getString("first_name");
                    stu = rs.getString("last_name");
                }
                t = new Teachers(sid,sec,stu);
                int tl = -1;
                for(int i = 0; i < teacherboxList.size(); i++)
                {
                    System.out.println(teacherboxList.get(i) + " " + t);
                    if(teacherboxList.get(i).compareTo(t) == 0)
                    {
                        tl = i;
                        System.out.println(tl);
                    }
                }
                teacherbox.setSelectedItem(tl);
                Connection rtcus = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/schoolmanager","root","password");
                Statement rtus = rtcus.createStatement();
                ResultSet rss = rtus.executeQuery("SELECT course_name,type FROM course WHERE course_id=" + cid +";");


                while(rss.next())
                {
                    sec = rss.getString("course_name");
                    stun = rss.getInt("type");
                }
                c = new Courses(sid,sec,stun);
                available.setSelectedItem(c);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    public void editSelectionTeacher()
    {
        if(teacherFirstName.getText().equals("") || teacherLastName.getText().equals(""))
        {
            showMessageDialog(this, "Both fields needs to be completed! J");
            return ;
        }
        DefaultTableModel tbmd = (DefaultTableModel) teachersList.getModel();
        int row = teachersList.getSelectedRow();
        int id = Integer.parseInt(tbmd.getValueAt(row,0).toString());
        Teachers t = new Teachers(id, teacherFirstName.getText(), teacherLastName.getText());
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            String state = "UPDATE teacher SET first_name='" +teacherFirstName.getText()+"' WHERE teacher_id=" + id + ";";
            Statement te = con.createStatement();
            boolean value = te.execute(state);
            state = "UPDATE teacher SET last_name='" +teacherLastName.getText()+"' WHERE teacher_id=" + id + ";";
            value = te.execute(state);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        addToJTableDataTeachers();
        teacherFirstName.setText("");
        teacherLastName.setText("");
    }
    public void editSelectionStudent()
    {
        if(studentFirstName.getText().equals("") || studentLastName.getText().equals(""))
        {
            showMessageDialog(this, "Please complete all fields! A");
            return ;
        }
        DefaultTableModel tbmd = (DefaultTableModel) studentList.getModel();
        int row = studentList.getSelectedRow();
        int id = Integer.parseInt(tbmd.getValueAt(row,0).toString());
        Students t = new Students(id, studentFirstName.getText(), studentLastName.getText());
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            String state = "UPDATE student SET first_name='" +studentFirstName.getText()+"' WHERE student_id=" + id + ";";
            Statement te = con.createStatement();
            boolean value = te.execute(state);
            state = "UPDATE student SET last_name='" +studentLastName.getText()+"' WHERE student_id=" + id + ";";
            value = te.execute(state);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        addToJTableDataStudent();
        studentFirstName.setText("");
        studentLastName.setText("");
    }
    public void editSelectionCourse()
    {
        if(courseName.getText().equals("") || (!ACA.isSelected() && !KAP.isSelected() && !AP.isSelected()))
        {
            showMessageDialog(this, "Please complete all fields! A");
            return ;
        }
        DefaultTableModel tbmd = (DefaultTableModel) courseList.getModel();
        int row = courseList.getSelectedRow();
        int id = Integer.parseInt(tbmd.getValueAt(row,0).toString());
        int type;
        if(ACA.isSelected())
        {
            type = 0;
        }
        else if (KAP.isSelected())
        {
            type = 1;
        }
        else
        {
            type = 2;
        }
        Courses t = new Courses(id, courseName.getText(), type);
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            String state = "UPDATE course SET course_name='" +courseName.getText()+"' WHERE course_id=" + id + ";";
            Statement te = con.createStatement();
            boolean value = te.execute(state);
            state = "UPDATE course SET type=" +type+" WHERE course_id=" + id + ";";
            value = te.execute(state);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        addToJTableDataCourses();
        courseName.setText("");
        bg.clearSelection();
    }
    public void editSelectionSection()
    {
        DefaultTableModel tbmd = (DefaultTableModel) sectionList.getModel();
        int row = sectionList.getSelectedRow();
        int id = Integer.parseInt(tbmd.getValueAt(row,0).toString());
        Courses c = (Courses) available.getSelectedItem();
        System.out.println(c);
        Teachers t = (Teachers) teacherbox.getSelectedItem();
        System.out.println(t);
        Sections ta = new Sections(c.getCourseID(), t.getTeacherID());
        try {
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            String state = "UPDATE section SET course_id=" + c.getCourseID()+" WHERE section_id=" + id + ";";
            Statement te = con.createStatement();
            te.execute(state);
            state = "UPDATE section SET teacher_id=" + t.getTeacherID() +" WHERE section_id=" + id + ";";
            te.execute(state);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        addToJTableDataSections();
        addSection.setVisible(true);
        editSection.setVisible(false);
    }
    public void refreshCourseSelection()
    {
        try {
            String state = "select * from course";
            Connection cons= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement jcs = cons.createStatement();
            ResultSet rscs = jcs.executeQuery(state);
            available.removeAllItems();
            availableList.clear();
            while(rscs.next())
            {
                int id = rscs.getInt("course_id");
                String title = rscs.getString("course_name");
                int t = rscs.getInt("type");
                Courses cs = new Courses(id, title, t);
                available.addItem(cs);
                availableList.add(cs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void refreshTeacherSelection()
    {
        try {
            String state = "select * from teacher WHERE teacher_id>-1";
            Connection cont= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement jct = cont.createStatement();
            ResultSet rsct = jct.executeQuery(state);
            teacherboxList.clear();
            teacherbox.removeAllItems();
            while(rsct.next())
            {
                int id = rsct.getInt("teacher_id");
                String title = rsct.getString("first_name");
                String t = rsct.getString("last_name");
                Teachers tt = new Teachers(id, title, t);
                teacherbox.addItem(tt);
                teacherboxList.add(tt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void refreshStudentSelection(){
        try{
            String state = "select * from student";
            Connection cont= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement jct = cont.createStatement();
            ResultSet rsct = jct.executeQuery(state);
            studentBox.removeAllItems();
            studentboxList.clear();
            while(rsct.next())
            {
                int id = rsct.getInt("student_id");
                String title = rsct.getString("first_name");
                String s = rsct.getString("last_name");
                Students st = new Students(id, title, s);
                studentboxList.add(st);
            }
            Collections.sort(studentboxList);
            for (Students s : studentboxList) {
                studentBox.addItem(s);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    public String courseNameFromID(int cid)
    {
        String name = "";
        try
        {
            String state = "select course_name from course where course_id=" + cid + ";";
            Connection contc= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement jctc = contc.createStatement();
            ResultSet rsctc = jctc.executeQuery(state);
            while(rsctc.next())
            {
                name = rsctc.getString("course_name");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return name;
    }
}


