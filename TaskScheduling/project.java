public class project {

    private int projectId;
    private String title;
    private String submissionDay;
    private int deadline;
    private double expectedRevenue;

    private int remainingDeadline;

    // Constructor for adding new project
    public project(String title,
                   int deadline,
                   double expectedRevenue,
                   String submissionDay) {

        this.title = title;
        this.deadline = deadline;
        this.expectedRevenue = expectedRevenue;
        this.submissionDay = submissionDay;

        this.remainingDeadline = deadline;
    }

    // Constructor for database fetch
    public project(int projectId,
                   String title,
                   String submissionDay,
                   int deadline,
                   double expectedRevenue) {

        this.projectId = projectId;
        this.title = title;
        this.submissionDay = submissionDay;
        this.deadline = deadline;
        this.expectedRevenue = expectedRevenue;

        this.remainingDeadline = deadline;
    }

    // GETTERS

    public int getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubmissionDay() {
        return submissionDay;
    }

    public int getDeadline() {
        return deadline;
    }

    public double getExpectedRevenue() {
        return expectedRevenue;
    }

    public int getRemainingDeadline() {
        return remainingDeadline;
    }

    // SETTERS

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setRemainingDeadline(int remainingDeadline) {
        this.remainingDeadline = remainingDeadline;
    }
}
