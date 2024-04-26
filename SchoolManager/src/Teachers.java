public class Teachers implements Comparable<Teachers>{
    private int teacherID;
    private String firstName;
    private String lastName;
    public Teachers(int teacherID, String firstName, String lastName)
    {
        this.teacherID = teacherID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Teachers(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String toStore()
    {
        return teacherID + "," + firstName + "," + lastName;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String toString()
    {
        return "FN:" + this.firstName + " LN:" + this.lastName + " Teacher ID: " + this.teacherID;
    }
    @Override
    public int compareTo(Teachers t) {
        if(t.getTeacherID() == this.teacherID &&  t.getFirstName().equals(this.firstName) && t.getLastName().equals(this.lastName))
        {
            return 0;
        }
        return -1;
    }
}
