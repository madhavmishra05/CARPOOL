import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDoa {

    public void addProject(project project) throws SQLException {

        String sql =
                "INSERT INTO projects (title, deadline, revenue, submission_day) VALUES (?, ?, ?, ?)";

        Connection conn = DBconnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, project.getTitle());
        stmt.setInt(2, project.getDeadline());
        stmt.setDouble(3, project.getExpectedRevenue());
        stmt.setString(4, project.getSubmissionDay());

        stmt.executeUpdate();

        System.out.println("Project added successfully!");

        conn.close();
    }

    public List<project> getAllProjects() throws SQLException {

        List<project> list = new ArrayList<>();

        String sql = "SELECT * FROM projects";

        Connection conn = DBconnection.getConnection();

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {

            project p = new project(
                    rs.getString("title"),
                    rs.getInt("deadline"),
                    rs.getDouble("revenue"),
                    rs.getString("submission_day")
            );

            p.setProjectId(rs.getInt("id"));

            list.add(p);
        }

        conn.close();

        return list;
    }
}
