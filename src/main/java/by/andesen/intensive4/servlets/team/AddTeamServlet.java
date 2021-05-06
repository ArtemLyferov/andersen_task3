package by.andesen.intensive4.servlets.team;

import by.andesen.intensive4.entities.Team;
import by.andesen.intensive4.jdbc.connector.ConnectionPool;
import by.andesen.intensive4.jdbc.dao.TeamDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddTeamServlet", value = "/teams/new")
public class AddTeamServlet extends HttpServlet {

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
        request.getRequestDispatcher("/WEB-INF/views/team/newTeam.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Team team = new Team(request.getParameter("teamName"));
        teamDAO.create(team);
        response.sendRedirect(request.getContextPath() + "/teams");
    }
}
