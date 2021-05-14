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

@WebServlet(name = "DeleteEmployeeServlet", value = "/employees/delete")
public class DeleteEmployeeServlet extends HttpServlet {

    private EntityService<Employee> employeeService;

    @Override
    public void init() throws ServletException {
        try {
            employeeService = new EntityService<>(new EmployeeDAO(ConnectorDB.getConnection()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeService.delete(id);
        response.sendRedirect(request.getContextPath() + "/employees");
    }
}