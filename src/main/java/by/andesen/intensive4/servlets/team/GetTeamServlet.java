package by.andesen.intensive4.servlets.team;

import by.andesen.intensive4.jdbc.connector.ConnectionPool;
import by.andesen.intensive4.jdbc.dao.TeamDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetTeamServlet", value = "/teams/show")
public class GetTeamServlet extends HttpServlet {

    private TeamDAO teamDAO;

    @Override
    public void init() throws ServletException {
        ConnectionPool connectionPool = null;
        try {
            connectionPool = ConnectionPool.create(
                    "jdbc:postgresql://localhost:5432/employee_control_system_db",
                    "postgres", "postgres");
            teamDAO = new TeamDAO(connectionPool.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("team", teamDAO.findEntityById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/views/team/showTeam.jsp").forward(request, response);
    }
}
