import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class SchoolManagerFrame extends JFrame{

    JPanel teachersList = new JPanel();
    JPanel courseList = new JPanel();
    JPanel sectionList = new JPanel();
    JPanel studentList = new JPanel();

    public SchoolManagerFrame(){
        super("School Manager");
        setSize(800, 700);
        setLayout(null);

        teachersList.setBounds();
        add(teachersList);

        courseList.setBounds();
        add(courseList);

        sectionList.setBounds();
        add(sectionList);

        studentList.setBounds();
        add(studentList);

        setVisible(true);
    }

}
