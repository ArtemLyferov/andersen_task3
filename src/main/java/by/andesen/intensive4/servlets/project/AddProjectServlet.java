package by.andesen.intensive4.servlets.project;

import by.andesen.intensive4.entities.Project;
import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.EmployeeDAO;
import by.andesen.intensive4.jdbc.dao.ProjectDAO;
import by.andesen.intensive4.jdbc.dao.TeamDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddProjectServlet", value = "/projects/new")
public class AddProjectServlet extends HttpServlet {

    private ProjectDAO projectDAO;
    private EmployeeDAO employeeDAO;
    private TeamDAO teamDAO;

    @Override
    public void init() throws ServletException {
        try {
            projectDAO = new ProjectDAO(ConnectorDB.getConnection());
            employeeDAO = new EmployeeDAO(ConnectorDB.getConnection());
            teamDAO = new TeamDAO(ConnectorDB.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employees", employeeDAO.findAll());
        request.setAttribute("teams", teamDAO.findAll());
        request.getRequestDispatcher("/WEB-INF/views/project/newProject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Project project = new Project();
        project.setNameProject(request.getParameter("nameProject"));
        project.setCustomer(request.getParameter("customer"));
        project.setDuration(Integer.parseInt(request.getParameter("duration")));
        project.setMethodology(Project.Methodology.valueOf(request.getParameter("methodology")));
        project.setProjectManager(employeeDAO.findEntityById(Integer.parseInt(request.getParameter("idEmployee"))));
        project.setTeam(teamDAO.findEntityById(Integer.parseInt(request.getParameter("idTeam"))));
        projectDAO.create(project);
        response.sendRedirect(request.getContextPath() + "/projects");
    }
}
