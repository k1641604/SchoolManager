public class Courses implements Comparable<Courses> {
    private int courseID;
    private String courseName;
    private int type;

    public Courses(int courseID, String title, int type) {
        this.courseID = courseID;
        this.courseName = title;
        this.type = type;
    }

    public Courses(String title, int type) {
        this.courseName = title;
        this.type = type;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getTitle() {
        return courseName;
    }

    public int getType() {
        return type;
    }
    public String toStore()
    {
        return courseID + "," + courseName + "," + type;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setCourseName(String title) {
        this.courseName = title;
    }

    public void setType(int type) {
        this.type = type;
    }
    @Override
    public String toString()
    {
        String t;
        if(this.type == 0)
        {
            t = "ACA";
        }
        else if(this.type == 1)
        {
            t = "KAP";
        }
        else
        {
            t = "AP";
        }
        return this.courseID + "  " + this.courseName + " - " + t;
    }

    @Override
    public int compareTo(Courses t) {
        if(t.getCourseID() == this.courseID &&  t.getType() == this.getType() && t.getTitle().equals(this.courseName))
        {
            return 0;
        }
        return -1;
    }
}
