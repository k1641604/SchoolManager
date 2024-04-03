public class Courses implements Comparable {
    private int courseID;
    private String title;
    private int type;

    public Courses(int courseID, String title, int type) {
        this.courseID = courseID;
        this.title = title;
        this.type = type;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
