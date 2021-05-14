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

@WebServlet(name = "GetTeamServlet", value = "/teams/show")
public class GetTeamServlet extends HttpServlet {

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
        request.setAttribute("team", teamService.findById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/views/team/showTeam.jsp").forward(request, response);
    }
}
