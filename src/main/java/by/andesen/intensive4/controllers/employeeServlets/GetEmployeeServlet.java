package by.andesen.intensive4.controllers.employeeServlets;

import by.andesen.intensive4.entities.Employee;
import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.EmployeeDAO;
import by.andesen.intensive4.service.EntityService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetEmployeeServlet", value = "/employees/show")
public class GetEmployeeServlet extends HttpServlet {

    private EntityService<Employee> employeeService;

    @Override
    public void init() throws ServletException {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            employeeService = new EntityService<>(new EmployeeDAO(connectorDB.getConnection()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employee", employeeService.findById(Integer.parseInt(request.getParameter("id"))));
        request.getRequestDispatcher("/WEB-INF/views/employee/showEmployee.jsp").forward(request, response);
    }
}
