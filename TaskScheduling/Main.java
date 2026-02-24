import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ProjectDoa dao = new ProjectDoa();

    static ProjectScheduler scheduler = new ProjectScheduler();

    public static void main(String[] args) throws Exception {

        while (true) {

            System.out.println("\n===== Intelligent Project Scheduler =====");

            System.out.println("1. Add Project");
            System.out.println("2. View Projects");
            System.out.println("3. Generate Schedule (This Week)");
            System.out.println("4. Generate Schedule (Next Week)");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addProject();
                    break;

                case 2:
                    viewProjects();
                    break;

                case 3:
                    scheduler.generateThisWeekSchedule(dao);
                    break;

                case 4:
                    scheduler.generateNextWeekSchedule();
                    break;

                case 5:
                    System.exit(0);
            }
        }
    }

    static void addProject() throws Exception {

        scanner.nextLine();

        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Submission Day: ");
        String submissionDay = scanner.nextLine();

        System.out.print("Enter Deadline: ");
        int deadline = scanner.nextInt();

        System.out.print("Enter Revenue: ");
        double revenue = scanner.nextDouble();

        project p =
                new project(title, deadline, revenue, submissionDay);

        dao.addProject(p);

        System.out.println("Project added successfully.");
    }

    static void viewProjects() throws Exception {

        List<project> list = dao.getAllProjects();

        if (list.isEmpty()) {

            System.out.println("No projects found.");
            return;
        }

        for (project p : list) {

            System.out.println(
                    p.getProjectId()
                            + " | "
                            + p.getTitle()
                            + " | "
                            + p.getSubmissionDay()
                            + " | Deadline: "
                            + p.getDeadline()
                            + " | Revenue: "
                            + p.getExpectedRevenue());
        }
    }
}
