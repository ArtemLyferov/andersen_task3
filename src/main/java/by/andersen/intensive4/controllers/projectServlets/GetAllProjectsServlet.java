package by.andersen.intensive4.controllers.projectServlets;

import by.andersen.intensive4.entities.Project;
import by.andersen.intensive4.service.EntityService;
import by.andersen.intensive4.jdbc.connector.ConnectorDB;
import by.andersen.intensive4.jdbc.dao.ProjectDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetAllProjectsServlet", value = "/projects")
public class GetAllProjectsServlet extends HttpServlet {

    private EntityService<Project> projectService;

    @Override
    public void init() throws ServletException {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            projectService = new EntityService<>(new ProjectDAO(connectorDB));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("projects", projectService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/project/indexProjects.jsp").forward(request, response);
    }
}
