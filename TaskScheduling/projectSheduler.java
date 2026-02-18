import java.util.*;

public class projectSheduler {

    public List<project> generateOptimalSchedule(List<project> projects) {

        int maxDays = 5;
        int n = projects.size();

        // Sort by deadline ascending (important for DP)
        projects.sort(Comparator.comparingInt(project::getDeadline));

        // dp[i][d] = maximum revenue using first i projects within d days
        double[][] dp = new double[n + 1][maxDays + 1];

        for (int i = 1; i <= n; i++) {

            project current = projects.get(i - 1);

            for (int d = 1; d <= maxDays; d++) {

                // Option 1: skip current project
                dp[i][d] = dp[i - 1][d];

                // Option 2: take current project (if deadline allows)
                if (d <= current.getDeadline()) {
                    dp[i][d] = Math.max(
                            dp[i][d],
                            dp[i - 1][d - 1] + current.getExpectedRevenue()
                    );
                }
            }
        }

        // Backtrack to get selected projects
        List<project> result = new ArrayList<>();
        int d = maxDays;

        for (int i = n; i > 0 && d > 0; i--) {

            if (dp[i][d] != dp[i - 1][d]) {
                project selected = projects.get(i - 1);
                result.add(selected);
                d--;
            }
        }

        return result;
    }
}
    

