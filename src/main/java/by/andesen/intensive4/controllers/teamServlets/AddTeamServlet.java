package by.andesen.intensive4.controllers.teamServlets;

import by.andesen.intensive4.entities.Team;
import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.TeamDAO;
import by.andesen.intensive4.service.EntityService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddTeamServlet", value = "/teams/new")
public class AddTeamServlet extends HttpServlet {

    private EntityService<Team> teamService;

    @Override
    public void init() throws ServletException {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            teamService = new EntityService<>(new TeamDAO(connectorDB.getConnection()));
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
        String teamName = request.getParameter("teamName");
        if (teamName != null && !teamName.isEmpty()) {
            Team team = new Team(teamName);
            teamService.create(team);
        }
        response.sendRedirect(request.getContextPath() + "/teams");
    }
}
