public class Students implements Comparable<Students>{
    private int studentID;
    private String firstName;
    private String lastName;

    public Students(int studentID, String firstName, String lastName) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Students(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String toStore()
    {
        return studentID + "," + firstName + "," + lastName;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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
        return "Student ID:" + this.studentID + " FN:" + this.firstName + " LN:" + this.lastName;
    }

    @Override
    public int compareTo(Students s) {
        if(s.getLastName().equals(this.getLastName()))
        {
            if(s.getFirstName().equals(this.getFirstName()))
            {
                if(s.getStudentID()> this.getStudentID())
                {
                    return -1;
                }
                else
                {
                    return 1;
                }
            } else if (s.getFirstName().compareTo(this.getFirstName()) > 0) {
                return -1;
            }
            else
            {
                return 1;
            }
        } else if (s.getLastName().compareTo(this.getLastName()) > 0) {
            return -1;
        }
        else
        {
            return 1;
        }
    }
}
