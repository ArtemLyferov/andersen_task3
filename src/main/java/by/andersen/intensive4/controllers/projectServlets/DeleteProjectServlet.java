package by.andersen.intensive4.controllers.projectServlets;

import by.andersen.intensive4.entities.Project;
import by.andersen.intensive4.service.EntityService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteProjectServlet", value = "/projects/delete")
public class DeleteProjectServlet extends HttpServlet {

    private EntityService<Project> projectService;

    @Override
    public void init() throws ServletException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        projectService = applicationContext.getBean("projectService", EntityService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        projectService.delete(id);
        response.sendRedirect(request.getContextPath() + "/projects");
    }
}
