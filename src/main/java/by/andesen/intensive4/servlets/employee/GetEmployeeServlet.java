package by.andesen.intensive4.servlets.employee;

import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.EmployeeDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetEmployeeServlet", value = "/employees/show")
public class GetEmployeeServlet extends HttpServlet {

    EmployeeDAO employeeDAO;

    @Override
    public void init() throws ServletException {
        try {
            employeeDAO = new EmployeeDAO(ConnectorDB.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employee", employeeDAO.findEntityById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/views/employee/showEmployee.jsp").forward(request, response);
    }
}