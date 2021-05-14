package by.andesen.intensive4.controllers.projectServlets;

import by.andesen.intensive4.entities.Employee;
import by.andesen.intensive4.entities.Project;
import by.andesen.intensive4.entities.Team;
import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.EmployeeDAO;
import by.andesen.intensive4.jdbc.dao.ProjectDAO;
import by.andesen.intensive4.jdbc.dao.TeamDAO;
import by.andesen.intensive4.service.EntityService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddProjectServlet", value = "/projects/new")
public class AddProjectServlet extends HttpServlet {

    private EntityService<Project> projectService;
    private EntityService<Employee> employeeService;
    private EntityService<Team> teamService;

    @Override
    public void init() throws ServletException {
        try {
            projectService = new EntityService<>(new ProjectDAO(ConnectorDB.getConnection()));
            employeeService = new EntityService<>(new EmployeeDAO(ConnectorDB.getConnection()));
            teamService = new EntityService<>(new TeamDAO(ConnectorDB.getConnection()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employees", employeeService.findAll());
        request.setAttribute("teams", teamService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/project/newProject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Project project = new Project();
        project.setNameProject(request.getParameter("nameProject"));
        project.setCustomer(request.getParameter("customer"));
        project.setDuration(Integer.parseInt(request.getParameter("duration")));
        project.setMethodology(Project.Methodology.valueOf(request.getParameter("methodology")));
        project.setProjectManager(employeeService.findById(Integer.parseInt(request.getParameter("idEmployee"))));
        project.setTeam(teamService.findById(Integer.parseInt(request.getParameter("idTeam"))));
        projectService.create(project);
        response.sendRedirect(request.getContextPath() + "/projects");
    }
}