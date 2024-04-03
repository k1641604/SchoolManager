public class Enrollment implements Comparable{
    private int sectionID;
    private int studentID;

    public Enrollment(int sectionID, int studentID) {
        this.sectionID = sectionID;
        this.studentID = studentID;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
