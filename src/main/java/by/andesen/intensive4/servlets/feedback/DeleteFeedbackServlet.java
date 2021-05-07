package by.andesen.intensive4.servlets.feedback;

import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.FeedbackDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteFeedbackServlet", value = "/feedbacks/delete")
public class DeleteFeedbackServlet extends HttpServlet {

    private FeedbackDAO feedbackDAO;

    @Override
    public void init() throws ServletException {
        try {
            feedbackDAO = new FeedbackDAO(ConnectorDB.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        feedbackDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/feedbacks");
    }
}
