package by.andesen.intensive4.servlets.feedback;

import by.andesen.intensive4.entities.Feedback;
import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.EmployeeDAO;
import by.andesen.intensive4.jdbc.dao.FeedbackDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "AddFeedbackServlet", value = "/feedbacks/new")
public class AddFeedbackServlet extends HttpServlet {

    private FeedbackDAO feedbackDAO;
    private EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        try {
            feedbackDAO = new FeedbackDAO(ConnectorDB.getConnection());
            employeeDAO = new EmployeeDAO(ConnectorDB.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employees", employeeDAO.findAll());
        request.getRequestDispatcher("/WEB-INF/views/feedback/newFeedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Feedback feedback = new Feedback();
        feedback.setDescription(request.getParameter("description"));
        feedback.setFeedbackDate(LocalDate.parse(request.getParameter("feedbackDate")));
        feedback.setEmployee(employeeDAO.findEntityById(Integer.parseInt(request.getParameter("id"))));
        feedbackDAO.create(feedback);
        response.sendRedirect(request.getContextPath() + "/feedbacks");
    }
}
