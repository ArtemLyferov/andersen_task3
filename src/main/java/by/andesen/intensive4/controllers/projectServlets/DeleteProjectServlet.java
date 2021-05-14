package by.andesen.intensive4.controllers.projectServlets;

import by.andesen.intensive4.entities.Project;
import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.ProjectDAO;
import by.andesen.intensive4.service.EntityService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteProjectServlet", value = "/projects/delete")
public class DeleteProjectServlet extends HttpServlet {

    private EntityService<Project> projectService;

    @Override
    public void init() throws ServletException {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            projectService = new EntityService<>(new ProjectDAO(connectorDB.getConnection()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        projectService.delete(id);
        response.sendRedirect(request.getContextPath() + "/projects");
    }
}
