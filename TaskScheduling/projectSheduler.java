import java.util.*;

public class ProjectScheduler {

    private static final int MAX_WORKING_DAYS = 5;

    private List<project> missedProjects = new ArrayList<>();
    private List<project> futureProjects = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public List<project> getMissedProjects() {
        return missedProjects;
    }

    public List<project> getFutureProjects() {
        return futureProjects;
    }

    // =========================
    // THIS WEEK SCHEDULE
    // =========================
    public void generateThisWeekSchedule(ProjectDoa dao) throws Exception {

        List<project> projects = dao.getAllProjects();

        if (projects.isEmpty()) {

            System.out.println("No projects available.");
            return;
        }

        System.out.print("Enter Scheduling Day (Sat/Sun): ");
        String schedulingDay = scanner.next().toLowerCase();

        System.out.print("Enter Busy Days (0-5): ");
        int busyTillDay = scanner.nextInt();

        System.out.print("Enter Last Week Avg Profit: ");
        double lastWeekAvg = scanner.nextDouble();

        System.out.print("Enter Last 3 Week Avg Profit: ");
        double lastThreeWeekAvg = scanner.nextDouble();

        List<project> schedule =
                generateOptimalSchedule(
                        projects,
                        schedulingDay,
                        busyTillDay,
                        lastWeekAvg,
                        lastThreeWeekAvg);

        printSchedule(schedule, busyTillDay, "THIS WEEK");
    }

    // =========================
    // NEXT WEEK SCHEDULE
    // =========================
    public void generateNextWeekSchedule() {

        if (futureProjects.isEmpty()) {

            System.out.println("No future projects available.");
            return;
        }

        System.out.print("Enter Last Week Avg Profit: ");
        double lastWeekAvg = scanner.nextDouble();

        System.out.print("Enter Last 3 Week Avg Profit: ");
        double lastThreeWeekAvg = scanner.nextDouble();

        List<project> nextWeekProjects = new ArrayList<>();

        for (project p : futureProjects) {

            int remaining = p.getRemainingDeadline() - MAX_WORKING_DAYS;

            if (remaining > -MAX_WORKING_DAYS) {

                project copy =
                        new project(
                                p.getProjectId(),
                                p.getTitle(),
                                "sunday",
                                remaining,
                                p.getExpectedRevenue());

                copy.setRemainingDeadline(remaining);

                nextWeekProjects.add(copy);
            }
        }

        List<project> schedule =
                generateOptimalSchedule(
                        nextWeekProjects,
                        "sunday",
                        0,
                        lastWeekAvg,
                        lastThreeWeekAvg);

        printSchedule(schedule, 0, "NEXT WEEK");
    }

    // =========================
    // CORE GREEDY LOGIC
    // =========================
    public List<project> generateOptimalSchedule(
            List<project> projects,
            String schedulingDay,
            int busyTillDay,
            double lastWeekAvg,
            double lastThreeWeekAvg) {

        missedProjects.clear();
        futureProjects.clear();

        project[] schedule = new project[MAX_WORKING_DAYS];

        List<project> critical = new ArrayList<>();
        List<project> safe = new ArrayList<>();

        Map<project, Integer> remainingMap = new HashMap<>();

        int planningOffset =
                schedulingDay.equalsIgnoreCase("sunday") ? 1 : 2;

        // STEP 1: Calculate remaining deadlines
        for (project p : projects) {

            int remaining;

            if (p.getRemainingDeadline() == p.getDeadline()) {

                int daysPassed =
                        getDaysBetween(
                                p.getSubmissionDay(),
                                schedulingDay);

                remaining =
                        p.getDeadline()
                                - (daysPassed + planningOffset);

            } else {

                remaining =
                        p.getRemainingDeadline()
                                - MAX_WORKING_DAYS;
            }

            p.setRemainingDeadline(remaining);

            remainingMap.put(p, remaining);

            if (remaining < 0)
                missedProjects.add(p);

            else if (remaining <= MAX_WORKING_DAYS)
                critical.add(p);

            else {
                safe.add(p);
                futureProjects.add(p);
            }
        }

        // STEP 2: Sort critical → earliest deadline first, highest revenue second
        critical.sort((a, b) -> {

            int remA = remainingMap.get(a);
            int remB = remainingMap.get(b);

            if (remA != remB)
                return Integer.compare(remA, remB);

            return Double.compare(
                    b.getExpectedRevenue(),
                    a.getExpectedRevenue());
        });

        // STEP 3: Sort safe → highest revenue first
        safe.sort((a, b) ->
                Double.compare(
                        b.getExpectedRevenue(),
                        a.getExpectedRevenue()));

        // STEP 4: Combine
        List<project> ordered = new ArrayList<>();
        ordered.addAll(critical);
        ordered.addAll(safe);

        // STEP 5: Assign greedily
        int pointer = busyTillDay;

        for (project p : ordered) {

            if (pointer >= MAX_WORKING_DAYS)
                break;

            schedule[pointer] = p;

            futureProjects.remove(p);

            pointer++;
        }

        // STEP 6: Convert to result list
        List<project> result = new ArrayList<>();

        for (int i = busyTillDay; i < MAX_WORKING_DAYS; i++) {

            if (schedule[i] != null)
                result.add(schedule[i]);
        }

        return result;
    }

    // =========================
    // PRINT METHOD (FIXED)
    // =========================
    private void printSchedule(
            List<project> schedule,
            int startDay,
            String week) {

        System.out.println("\n===== Schedule for " + week + " =====");

        int day = startDay + 1;

        double totalProfit = 0;

        for (project p : schedule) {

            System.out.println(
                    "Day "
                            + day++
                            + " → "
                            + p.getTitle()
                            + " | Revenue: ₹"
                            + p.getExpectedRevenue()
                            + " | Remaining Deadline: "
                            + p.getRemainingDeadline()
                            + " days");

            totalProfit += p.getExpectedRevenue();
        }

        System.out.println("\n💰 Total Profit: ₹" + totalProfit);

        // MISSED PROJECTS
        System.out.println("\n===== Missed Projects =====");

        if (missedProjects.isEmpty()) {

            System.out.println("None");

        } else {

            for (project p : missedProjects) {

                System.out.println(
                        p.getTitle()
                                + " | Deadline Missed (expired: "
                                + p.getRemainingDeadline()
                                + " days)");
            }
        }

        // FUTURE PROJECTS
        System.out.println("\n===== Future Projects =====");

        if (futureProjects.isEmpty()) {

            System.out.println("None");

        } else {

            for (project p : futureProjects) {

                System.out.println(
                        p.getTitle()
                                + " | Remaining Deadline: "
                                + p.getRemainingDeadline()
                                + " days");
            }
        }
    }

    // =========================
    // UTILITY FUNCTION
    // =========================
    public static int getDaysBetween(
            String submissionDay,
            String schedulingDay) {

        List<String> days =
                Arrays.asList(
                        "monday",
                        "tuesday",
                        "wednesday",
                        "thursday",
                        "friday",
                        "saturday",
                        "sunday");

        int sub =
                days.indexOf(submissionDay.toLowerCase());

        int sch =
                days.indexOf(schedulingDay.toLowerCase());

        int diff = sch - sub;

        if (diff < 0)
            diff += 7;

        return diff;
    }
}
