package by.andersen.intensive4.controllers.feedbackServlets;

import by.andersen.intensive4.entities.Feedback;
import by.andersen.intensive4.service.EntityService;
import by.andersen.intensive4.jdbc.connector.ConnectorDB;
import by.andersen.intensive4.jdbc.dao.FeedbackDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetAllFeedbackServlet", value = "/feedbacks")
public class GetAllFeedbacksServlet extends HttpServlet {

    private EntityService<Feedback> feedbackService;

    @Override
    public void init() throws ServletException {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            feedbackService = new EntityService<>(new FeedbackDAO(connectorDB));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("feedbacks", feedbackService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/feedback/indexFeedbacks.jsp").forward(request, response);
    }
}
