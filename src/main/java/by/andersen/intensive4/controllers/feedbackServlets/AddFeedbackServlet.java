package by.andersen.intensive4.controllers.feedbackServlets;

import by.andersen.intensive4.entities.Employee;
import by.andersen.intensive4.entities.Feedback;
import by.andersen.intensive4.service.EntityService;
import by.andersen.intensive4.jdbc.connector.ConnectorDB;
import by.andersen.intensive4.jdbc.dao.EmployeeDAO;
import by.andersen.intensive4.jdbc.dao.FeedbackDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "AddFeedbackServlet", value = "/feedbacks/new")
public class AddFeedbackServlet extends HttpServlet {

    private EntityService<Feedback> feedbackService;
    private EntityService<Employee> employeeService;

    @Override
    public void init() throws ServletException {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            feedbackService = new EntityService<>(new FeedbackDAO(connectorDB));
            employeeService = new EntityService<>(new EmployeeDAO(connectorDB));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employees", employeeService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/feedback/newFeedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Feedback feedback = new Feedback();
        feedback.setDescription(request.getParameter("description"));
        feedback.setFeedbackDate(LocalDate.parse(request.getParameter("feedbackDate")));
        feedback.setEmployee(employeeService.findById(Integer.parseInt(request.getParameter("id"))));
        feedbackService.create(feedback);
        response.sendRedirect(request.getContextPath() + "/feedbacks");
    }
}
