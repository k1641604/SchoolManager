import javax.swing.*;

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

        add(menubar);
        ViewMenuCreator();
        menubar.add(View);
        HelpMenuCreator();
        menubar.add(Help);
        FileMenuCreator();
        menubar.add(File);
        setJMenuBar(menubar);

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

    public void teacherTable(){}
    public void studentTable(){}
    public void coursesTable(){}
    public void sectionsTable(){}

    public void helpTable(){}

    public void exportDataDoer(){}
    public void importDataDoer(){}
    public void purger(){}
    public void release(){}


}
