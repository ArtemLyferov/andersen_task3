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

@WebServlet(name = "UpdateProjectServlet", value = "/projects/edit")
public class UpdateProjectServlet extends HttpServlet {

    private EntityService<Project> projectService;
    private EntityService<Employee> employeeService;
    private EntityService<Team> teamService;

    @Override
    public void init() throws ServletException {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            projectService = new EntityService<>(new ProjectDAO(connectorDB.getConnection()));
            employeeService = new EntityService<>(new EmployeeDAO(connectorDB.getConnection()));
            teamService = new EntityService<>(new TeamDAO(connectorDB.getConnection()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("project", projectService.findById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("employees", employeeService.findAll());
        request.setAttribute("teams", teamService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/project/editProject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Project project = new Project();
        project.setId(Integer.parseInt(request.getParameter("id")));
        project.setNameProject(request.getParameter("nameProject"));
        project.setCustomer(request.getParameter("customer"));
        project.setDuration(Integer.parseInt(request.getParameter("duration")));
        project.setMethodology(Project.Methodology.valueOf(request.getParameter("methodology")));
        project.setProjectManager(employeeService.findById(Integer.parseInt(request.getParameter("idEmployee"))));
        project.setTeam(teamService.findById(Integer.parseInt(request.getParameter("idTeam"))));
        projectService.update(project);
        response.sendRedirect(request.getContextPath() + "/projects");
    }
}
