import javax.swing.*;
import javax.swing.table.*;
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
    JMenuBar menubar = new JMenuBar();
    JMenu View = new JMenu("View");
    JMenu Help = new JMenu("Help");
    JMenu File = new JMenu("File");
        JLabel teachers = new JLabel("Teacher Information");
        JTable teachersList = new JTable(tableModelTeacher);
        JScrollPane teacherArea;

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
        JTable sectionsTab = new JTable();
    JTable enrollmentList;
    JScrollPane enrollmentPane = new JScrollPane();
        JLabel students = new JLabel("Student Information");
        JLabel schedule = new JLabel("Student Schedule");
        JLabel nuCourses = new JLabel("Available Courses");
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

    //should only appear after student has been selected
    JButton removeStudent = new JButton("Remove Student from List");
    JButton editStudent = new JButton("Save Changes to Student's Information");
    JButton removeTeacher = new JButton("Remove Teacher from List");
    JButton editTeacher  = new JButton("Save Changes to Teacher's Information");

    JTable courseBox = new JTable();
    JComboBox<Courses> available = new JComboBox<Courses>();
    JComboBox<Teachers> teacherbox = new JComboBox<Teachers>();

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
        teacherArea.setBounds(20, 30, 300, 500);
        tableModelTeacher.addColumn("Teacher id");
        tableModelTeacher.addColumn("First Name");
        tableModelTeacher.addColumn("Last Name");
        teacherArea.setBorder(oLine);
        add(teacherArea);
        teacherArea.setVisible(false);

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

            addTeacher.setBounds(400, 340, 300, 30);
            addTeacher.addActionListener(e -> {addTeacherButton();});
            add(addTeacher);
            addTeacher.setVisible(false);

            removeTeacher.setBounds(400, 400, 300, 30);
            removeTeacher.addActionListener(e -> {removeItemTeacher();});
            add(removeTeacher);
            removeTeacher.setVisible(false);

            editTeacher.setBounds(400, 460, 300, 30);
            editTeacher.addActionListener(e -> {teacherEditor();});
            add(editTeacher);
            editTeacher.setVisible(false);

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

            courseBox.setBounds(400, 30, 300, 50);
            courseBox.setBorder(oLine);
            add(courseBox);
            courseBox.setVisible(false);

            nuCourses.setBounds(400, 135, 300, 15);
            add(nuCourses);
            nuCourses.setVisible(false);


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

            editStudent.setBounds(400, 460, 300, 30);
            editStudent.addActionListener(e -> {studentEditor();});
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

        editCourse.setBounds(400, 340, 300, 30);
        add(editCourse);
        editCourse.addActionListener(e -> {courseEditor();});
        editCourse.setVisible(false);

            sectionList.setBounds(20, 20, 300, 500);
            sectionList.setBorder(oLine);
            add(sectionList);
            sectionList.setVisible(false);
        try {
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/schoolmanager","root","password");
            Statement n = connect.createStatement();
            String s = "DROP TABLE IF EXISTS enrollment;";
            n.execute(s);
            s = "DROP TABLE IF EXISTS section;";
            n.execute(s);
            s = "DROP TABLE IF EXISTS teacher;";
            n.execute(s);
            s = "DROP TABLE IF EXISTS student;";
            n.execute(s);
            s = "DROP TABLE IF EXISTS course;";
            n.execute(s);
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
            s = "INSERT INTO teacher (teacher_id, first_name, last_name) VALUES (-1, 'Teacher', 'No');";
            n.execute(s);

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            readFile();
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
        addToJTableDataTeachers();
        System.out.println("aaaaaaaaaaaaaaaaaaaa");
        //setVisible(false);
        teachers.setVisible(true);
        teacherArea.setVisible(true);
        secTab.setVisible(true);
        sectionsTab.setVisible(true);
        tFirst.setVisible(true);
        teacherFirstName.setVisible(true);
        tLast.setVisible(true);
        teacherLastName.setVisible(true);
        addTeacher.setVisible(true);
        removeTeacher.setVisible(true);
        editTeacher.setVisible(true);
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
        nuCourses.setVisible(false);
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
        editCourse.setVisible(false);
    }

    public void teacherAdder(){}
    public void teacherRemover(){}
    public void teacherEditor(){}

    public void studentTable(){
        openStudent();
        addToJTableDataStudent();
        System.out.println("bbbbbbbbbbbbbbbbbbbb");
        //setVisible(false);
        students.setVisible(true);
        schedule.setVisible(true);
        studentArea.setVisible(true);
        courseBox.setVisible(true);
        nuCourses.setVisible(true);
        tFirst.setVisible(true);
        studentFirstName.setVisible(true);
        studentLastName.setVisible(true);
        tLast.setVisible(true);
        addStudent.setVisible(true);
        removeStudent.setVisible(true);
        editStudent.setVisible(true);
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
        sectionsTab.setVisible(false);
        cListName.setVisible(false);
        courseArea.setVisible(false);
        course.setVisible(false);
        courseName.setVisible(false);
        ACA.setVisible(false);
        KAP.setVisible(false);
        AP.setVisible(false);
        addCourse.setVisible(false);
        removeCourse.setVisible(false);
        editCourse.setVisible(false);
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
        sectionsTab.setVisible(false);
        studentArea.setVisible(false);
        courseBox.setVisible(false);
        nuCourses.setVisible(false);
    }

    public void courseAdder(){
        if (courseName.getText().isEmpty() || (rank != 0 && rank != 1 && rank != 2)){
            int value = JOptionPane.showConfirmDialog(null, "Courses require a name and a proper rank (ie. ACA, KAP, AP)");
        }
        else {
            System.out.println("Course name is "+courseName.getText()+" with a rank of "+rank);
        }
    }
    public void courseRemover(){}
    public void courseEditor(){}

    public void sectionsTable(){
        System.out.println("dddddddddddddddddddd");
        openSection();
        //addToJTableDataSections();

    }
    public void openSection(){
        //all JTextFields should be reset if another menu is opened
        //More things need to be added as more tables, labels, textfields, etc are added to the program
        teachers.setVisible(false);
        teachersList.setVisible(false);
        teacherArea.setVisible(false);
        secTab.setVisible(false);
        sectionsTab.setVisible(false);
        tFirst.setVisible(false);
        teacherFirstName.setVisible(false);
        tLast.setVisible(false);
        teacherLastName.setVisible(false);
        addTeacher.setVisible(false);
        removeTeacher.setVisible(false);
        editTeacher.setVisible(false);
        students.setVisible(false);
        schedule.setVisible(false);
        studentList.setVisible(false);
        nuCourses.setVisible(false);
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
    }


    public void helpTable(){
        //no idea what this does
    }

    public void exportDataDoer(){}
    public void importDataDoer(){}
    public void purger(){}
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
                System.out.println();
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
        String state = "select * from sections";
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
                tableMod.addRow(toAdd);
                repaint();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToJTableDataEnrollment()
    {
        //referenced code from Knowledge to Share's YouTube video;
        String state = "select * from enrollment";
        try {
            ResultSet rs = this.s.executeQuery(state);
            DefaultTableModel tableMod =  (DefaultTableModel) enrollmentList.getModel();
            tableMod.setRowCount(0);
            while(rs.next())
            {
                String sec = String.valueOf(rs.getInt("section_id"));
                String stu = String.valueOf(rs.getInt("student_id"));
                String[] toAdd = {sec, stu};
                tableMod.addRow(toAdd);

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
            System.out.println("executed statement");

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
            System.out.println("executed statement");
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
                state = "INSERT INTO section (section_id, course_id, teacher_id) VALUES (" + se.getSectionID()+ se.getCourseID() +", " + se.getTeacherID() + "');";
            }
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
                System.out.println(c.toStore());
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
                //addToSqlEnrollment(c, true);
                addToJTableDataEnrollment();
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
                    System.out.println(id +"," + first + "," + last);
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
            JOptionPane.showMessageDialog(this, "Please complete all fields!");
            return ;
        }
        Teachers t = new Teachers(teacherFirstName.getText(), teacherLastName.getText());
        System.out.println(t);
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
            JOptionPane.showMessageDialog(this, "Please complete all fields!");
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
            JOptionPane.showMessageDialog(this, "Please complete all fields!");
            return ;
        }
        if(courseName.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please complete all fields!");
            return ;
        }
        Courses t = new Courses(courseName.getText(), a);
        courseInfo.add(t);
        addToSqlCourse(t, false);
        addToJTableDataCourses();
        courseName.setText("");
        bg.clearSelection();
    }
    /*public void addEnrollmentButton()
        {
            if(studentFirstName.getText().equals("") || studentLastName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Please complete all fields!");
                return ;
            }
            Students t = new Students(studentFirstName.getText(), studentLastName.getText());
            studentInfo.add(t);
            addToSqlStudent(t);
            addToJTableDataStudent();
        }*/
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("trying to save file");
        fileSave();
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
            JOptionPane.showMessageDialog(this, "Please select one row to delete.");
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
            tableMod.removeRow(row);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
    public void removeItemStudent()
    {
        if(studentList.getSelectionModel().isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please select one row to delete.");
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
    }
    public void removeItemCourse()
    {
        if(courseList.getSelectionModel().isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please select one row to delete.");
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

    }
    public void removeItemSection()
    {
        if(sectionList.getSelectionModel().isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please select one row to delete.");
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
        if(enrollmentList.getSelectionModel().isSelectionEmpty())
        {
            JOptionPane.showMessageDialog(this, "Please select one row to delete.");
            return;
        }
        DefaultTableModel tableMod = (DefaultTableModel) enrollmentList.getModel();
    }
    public void updateSelectionTeacher()
    {
        DefaultTableModel tbModel = (DefaultTableModel)  teachersList.getModel();
        if(teachersList.getSelectedRowCount() == 1 )
        {
            int row = teachersList.getSelectedRow();
            teacherFirstName.setText(tbModel.getValueAt(row,1).toString());
            teacherLastName.setText(tbModel.getValueAt(row,2).toString());
        }
    }
    public void updateSelectionStudent()
    {
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
}


