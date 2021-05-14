package by.andersen.intensive4.controllers.feedbackServlets;

import by.andersen.intensive4.entities.Feedback;
import by.andersen.intensive4.service.EntityService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GetFeedbackServlet", value = "/feedbacks/show")
public class GetFeedbackServlet extends HttpServlet {

    private EntityService<Feedback> feedbackService;

    @Override
    public void init() throws ServletException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        feedbackService = applicationContext.getBean("feedbackService", EntityService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("feedback", feedbackService.findById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/views/feedback/showFeedback.jsp").forward(request, response);
    }
}
