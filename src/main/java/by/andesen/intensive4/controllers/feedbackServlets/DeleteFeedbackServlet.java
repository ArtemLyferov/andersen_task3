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

@WebServlet(name = "DeleteFeedbackServlet", value = "/feedbacks/delete")
public class DeleteFeedbackServlet extends HttpServlet {

    private EntityService<Feedback> feedbackService;

    @Override
    public void init() throws ServletException {
        try {
            feedbackService = new EntityService<>(new FeedbackDAO(ConnectorDB.getConnection()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        feedbackService.delete(id);
        response.sendRedirect(request.getContextPath() + "/feedbacks");
    }
}
