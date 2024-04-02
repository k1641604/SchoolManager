public class Teachers implements Comparable{
    private int teacherID;
    private String firstName;
    private String lastName;
    public Teachers(int teacherID, String firstName, String lastName)
    {
        this.teacherID = teacherID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
