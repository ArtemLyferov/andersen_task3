package by.andesen.intensive4.servlets.team;

import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.TeamDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetAllTeamsServlet", value = "/teams")
public class GetAllTeamsServlet extends HttpServlet {

    private TeamDAO teamDAO;

    @Override
    public void init() throws ServletException {
        try {
            teamDAO = new TeamDAO(ConnectorDB.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("teams", teamDAO.findAll());
        request.getRequestDispatcher("/WEB-INF/views/team/indexTeams.jsp").forward(request, response);
    }
}
