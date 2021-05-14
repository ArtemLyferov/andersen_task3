package by.andersen.intensive4.controllers.feedbackServlets;

import by.andersen.intensive4.entities.Feedback;
import by.andersen.intensive4.service.EntityService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteFeedbackServlet", value = "/feedbacks/delete")
public class DeleteFeedbackServlet extends HttpServlet {

    private EntityService<Feedback> feedbackService;

    @Override
    public void init() throws ServletException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        feedbackService = applicationContext.getBean("feedbackService", EntityService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        feedbackService.delete(id);
        response.sendRedirect(request.getContextPath() + "/feedbacks");
    }
}
