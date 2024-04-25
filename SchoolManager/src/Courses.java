public class Courses implements Comparable {
    private int courseID;
    private String title;
    private int type;

    public Courses(int courseID, String title, int type) {
        this.courseID = courseID;
        this.title = title;
        this.type = type;
    }

    public Courses(String title, int type) {
        this.title = title;
        this.type = type;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }
    public String toStore()
    {
        return courseID + "," + title + "," + type;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return this.courseID + "  " + this.title + " - " + t;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
