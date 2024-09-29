package coursemanagement;



import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNumber = request.getParameter("studentId");
        int enrollment_id = Integer.parseInt(request.getParameter("enrollment_id"));
        int student_id = Integer.parseInt(request.getParameter("student_id"));
        int course_id = Integer.parseInt(request.getParameter("course_id"));

        String url = "jdbc:mysql://localhost:3306/enrollmentdb";
        String username = "root";
        String password = "yamuna@13";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "UPDATE users SET enrollment_id=?, student_id=?, course_id=? WHERE enrollment_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, enrollment_id);
            ps.setInt(2, student_id);
            ps.setInt(3, course_id);
            ps.setString(4, rollNumber);
           

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("viewAccounts.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

