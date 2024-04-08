public class Enrollment implements Comparable{
    private int sectionID;
    private int studentID;

    public Enrollment(int sectionID, int studentID) {
        this.sectionID = sectionID;
        this.studentID = studentID;
    }

    public int getSectionID() {
        return sectionID;
    }

    public int getStudentID() {
        return studentID;
    }
    public String toStore()
    {
        return sectionID + "," + studentID ;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
