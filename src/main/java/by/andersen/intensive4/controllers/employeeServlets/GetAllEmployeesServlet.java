package by.andersen.intensive4.controllers.employeeServlets;

import by.andersen.intensive4.entities.Employee;
import by.andersen.intensive4.service.EntityService;
import by.andersen.intensive4.jdbc.connector.ConnectorDB;
import by.andersen.intensive4.jdbc.dao.EmployeeDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetAllEmployeesServlet", value = "/employees")
public class GetAllEmployeesServlet extends HttpServlet {

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
        request.setAttribute("employees", employeeService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/employee/indexEmployees.jsp").forward(request, response);
    }
}
