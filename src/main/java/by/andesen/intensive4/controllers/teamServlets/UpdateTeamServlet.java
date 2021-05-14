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

@WebServlet(name = "UpdateTeamServlet", value = "/teams/edit")
public class UpdateTeamServlet extends HttpServlet {

    private EntityService<Team> teamService;

    @Override
    public void init() throws ServletException {
        try {
            teamService = new EntityService<>(new TeamDAO(ConnectorDB.getConnection()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("team", teamService.findById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/views/team/editTeam.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newTeamName = request.getParameter("teamName");
        if (newTeamName != null && !newTeamName.isEmpty()) {
            int id = Integer.parseInt(request.getParameter("id"));
            Team team = teamService.findById(id);
            team.setTeamName(newTeamName);
            teamService.update(team);
        }
        response.sendRedirect(request.getContextPath() + "/teams");
    }
}
