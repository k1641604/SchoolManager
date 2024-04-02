public class Students implements Comparable{
    private int studentID;
    private String firstName;
    private String lastName;

    public Students(int studentID, String firstName, String lastName) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
