public class Courses implements Comparable {
    private int courseID;
    private String title;
    private int type;

    public Courses(int courseID, String title, int type) {
        this.courseID = courseID;
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
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
