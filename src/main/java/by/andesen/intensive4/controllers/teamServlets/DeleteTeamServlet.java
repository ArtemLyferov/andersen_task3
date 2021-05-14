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

@WebServlet(name = "DeleteTeamServlet", value = "/teams/delete")
public class DeleteTeamServlet extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        teamService.delete(id);
        response.sendRedirect(request.getContextPath() + "/teams");
    }
}
