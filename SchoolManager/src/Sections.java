public class Sections implements Comparable{
    private int sectionID;
    private int courseID;
    private int teacherID;

    public Sections(int sectionID, int courseID, int teacherID) {
        this.sectionID = sectionID;
        this.courseID = courseID;
        this.teacherID = teacherID;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
