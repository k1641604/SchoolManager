/*import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
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
    Connection con;
    Statement s;


    public SchoolManagerFrame(Connection c){
        super("School Manager");
        setSize(800, 700);
        setLayout(null);
        this.con = c;
        try {
            this.s = this.con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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
        JButton editTeacher  = new JButton("Delete Teacher from List");

        JComboBox<Courses> courseBox = new JComboBox<Courses>();
        JComboBox<Teachers> teacherbox = new JComboBox<Teachers>();
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
            String state =  "select * from teacher where teacher_id>-1";
            try {
                ResultSet rs = this.s.executeQuery(state);
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

            fileWriter = new FileWriter(f1,false);
            printWriter = new PrintWriter(fileWriter);
            state = "select * from student";
            try {
                ResultSet rs = this.s.executeQuery(state);
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

            fileWriter = new FileWriter(f2,false);
            printWriter = new PrintWriter(fileWriter);
            state = "select * from courses";
            try {
                ResultSet rs = this.s.executeQuery(state);
                while(rs.next())
                {
                    String id = String.valueOf(rs.getInt("course_id"));
                    String title = rs.getString("title");
                    String t = String.valueOf(rs.getInt("type"));
                    printWriter.println(id + "," + title + "," + t);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            fileWriter.close();
            printWriter.close();

            fileWriter = new FileWriter(f3,false);
            printWriter = new PrintWriter(fileWriter);
            state = "select * from sections";
            try {
                ResultSet rs = this.s.executeQuery(state);
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
            fileWriter = new FileWriter(f4,false);
            printWriter = new PrintWriter(fileWriter);
            state = "select * from enrollment";
            try {
                ResultSet rs = this.s.executeQuery(state);
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
    public void addToJTableDataTeachers()
    {
        //referenced code from Knowledge to Share's YouTube video;
        String state = "select * from teacher where teacher_id>-1";
        try {
            ResultSet rs = this.s.executeQuery(state);
            while(rs.next())
            {
                String id = String.valueOf(rs.getInt("teacher_id"));
                String first = rs.getString("first_name");
                String last = rs.getString("last_name");
                String[] toAdd = {id, first, last};
                DefaultTableModel tableMod =  (DefaultTableModel) courseList.getModel();
                tableMod.addRow(toAdd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToJTableDataStudent()
    {
        //referenced code from Knowledge to Share's YouTube video;
        String state = "select * from student";
        try {
            ResultSet rs = this.s.executeQuery(state);
            while(rs.next())
            {
                String id = String.valueOf(rs.getInt("student_id"));
                String first = rs.getString("first_name");
                String last = rs.getString("last_name");
                String[] toAdd = {id, first, last};
                DefaultTableModel tableMod =  (DefaultTableModel) courseList.getModel();
                tableMod.addRow(toAdd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToJTableDataCourses()
    {
        //referenced code from Knowledge to Share's YouTube video;
        String state = "select * from courses";
        try {
            ResultSet rs = this.s.executeQuery(state);
            while(rs.next())
            {
                String id = String.valueOf(rs.getInt("course_id"));
                String title = rs.getString("title");
                int t = rs.getInt("type");
                String type;
                if(t == 0)
                {
                    type = "academic";
                }
                else if(t == 1)
                {
                    type = "KAP";
                }
                else
                {
                    type = "AP";
                }
                String[] toAdd = {id, title, type};
                DefaultTableModel tableMod =  (DefaultTableModel) courseList.getModel();
                tableMod.addRow(toAdd);
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
            ResultSet rs = this.s.executeQuery(state);
            while(rs.next())
            {
                String sec = rs.getString("section_id");
                String cou = String.valueOf(rs.getInt("course_id"));
                String teach = String.valueOf(rs.getInt("teacher_id"));
                String[] toAdd = {sec, cou, teach};
                DefaultTableModel tableMod =  (DefaultTableModel) courseList.getModel();
                tableMod.addRow(toAdd);
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
            while(rs.next())
            {
                String sec = String.valueOf(rs.getInt("section_id"));
                String stu = String.valueOf(rs.getInt("student_id"));
                String[] toAdd = {sec, stu};
                DefaultTableModel tableMod =  (DefaultTableModel) courseList.getModel();
                tableMod.addRow(toAdd);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToSqlTeacher(Teachers t)
    {
        String state = "INSERT INTO teacher (first_name, last_name) VALUES ('" + t.getFirstName() +"', '" + t.getLastName() + "');";
        try {
            this.s.execute(state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToSqlStudent(Students st)
    {
        String state = "INSERT INTO student (first_name, last_name) VALUES ('" + st.getFirstName() +"', '" + st.getLastName() + "');";
        try {
            this.s.execute(state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToSqlCourse(Courses c)
    {
        String state = "INSERT INTO course (title, type) VALUES ('" + c.getTitle() +"', " + c.getType() + ");";
        try {
            this.s.execute(state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addToSqlSection(Sections se)
    {
        String state = "INSERT INTO section (course, teacher) VALUES (" + se.getCourseID() +", " + se.getTeacherID() + ");";
        try {
            s.execute(state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteFromSqlTeacher(int id)
    {
        String state = "DELETE FROM teacher WHERE teacher_id=" + id + ";";
        try {
            s.execute(state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteFromSqlStudent(int id)
    {
        String state = "DELETE FROM teacher WHERE student_id=" + id + ";";
        try {
            s.execute(state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteFromSqlCourse(int id)
    {
        String state = "DELETE FROM teacher WHERE course_id=" + id + ";";
        try {
            s.execute(state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteFromSqlSection(int id)
    {
        String state = "DELETE FROM teacher WHERE section_id=" + id + ";";
        try {
            s.execute(state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteFromSqlEnrollment(int id1, int id2)
    {
        String state = "DELETE FROM teacher WHERE section_id=" + id1 + " AND student_id=" + id2 + ";";
        try {
            s.execute(state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void purge()
    {
        try {
        String state = "DROP TABLE IF EXISTS teacher;";
        s.execute(state);
        state = "DROP TABLE IF EXISTS student;";
        s.execute(state);
        state = "DROP TABLE IF EXISTS course;";
        s.execute(state);
        state = "DROP TABLE IF EXISTS section;";
        s.execute(state);
        state = "DROP TABLE IF EXISTS enrollment;";
        s.execute(state);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.setVisible(false);
        this.dispose();
    }
    public void newTables(Statement stat)
    {
        try {
            String state = "DROP TABLE IF EXISTS teacher;";
            s.execute(state);
            state = "DROP TABLE IF EXISTS student;";
            s.execute(state);
            state = "DROP TABLE IF EXISTS course;";
            s.execute(state);
            state = "DROP TABLE IF EXISTS section;";
            s.execute(state);
            state = "DROP TABLE IF EXISTS enrollment;";
            s.execute(state);
            state = "CREATE TABLE IF NOT EXISTS teacher (teacher_id INTEGER Not Null Primary Key AUTO_INCREMENT, " +
                    "first_name Text, " +
                    "last_name Text" +
                    ");";
            state = "CREATE TABLE IF NOT EXISTS student (student_id INTEGER Not Null Primary Key AUTO_INCREMENT, " +
                    "first_name Text, " +
                    "last_name Text" +
                    ");";
            state = "CREATE TABLE IF NOT EXISTS course (course_id INTEGER Not Null Primary Key AUTO_INCREMENT, " +
                    "title Text Not Null, " +
                    "type INTEGER Not Null" +
                    ");";
            state = "CREATE TABLE IF NOT EXISTS section   (course_id INTEGER Not Null Primary Key AUTO_INCREMENT, " +
                    "title Text Not Null, " +
                    "type INTEGER Not Null" +
                    ");";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
*/