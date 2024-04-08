public class Sections implements Comparable{
    private int sectionID;
    private int courseID;
    private int teacherID;

    public Sections(int sectionID, int courseID, int teacherID) {
        this.sectionID = sectionID;
        this.courseID = courseID;
        this.teacherID = teacherID;
    }

    public int getSectionID() {
        return sectionID;
    }

    public int getCourseID() {
        return courseID;
    }

    public int getTeacherID() {
        return teacherID;
    }
    public String toStore()
    {
        return sectionID + "," + courseID + "," + teacherID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
