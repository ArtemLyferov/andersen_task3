package by.andesen.intensive4.controllers.feedbackServlets;

import by.andesen.intensive4.entities.Feedback;
import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.FeedbackDAO;
import by.andesen.intensive4.service.EntityService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetFeedbackServlet", value = "/feedbacks/show")
public class GetFeedbackServlet extends HttpServlet {

    private EntityService<Feedback> feedbackService;

    @Override
    public void init() throws ServletException {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            feedbackService = new EntityService<>(new FeedbackDAO(connectorDB.getConnection()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("feedback", feedbackService.findById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/views/feedback/showFeedback.jsp").forward(request, response);
    }
}
