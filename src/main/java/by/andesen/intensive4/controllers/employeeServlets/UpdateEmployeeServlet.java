package by.andesen.intensive4.controllers.employeeServlets;

import by.andesen.intensive4.entities.Employee;
import by.andesen.intensive4.entities.Team;
import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.EmployeeDAO;
import by.andesen.intensive4.jdbc.dao.TeamDAO;
import by.andesen.intensive4.service.EntityService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "UpdateEmployeeServlet", value = "/employees/edit")
public class UpdateEmployeeServlet extends HttpServlet {

    private EntityService<Employee> employeeService;
    private EntityService<Team> teamService;

    @Override
    public void init() throws ServletException {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            employeeService = new EntityService<>(new EmployeeDAO(connectorDB.getConnection()));
            teamService = new EntityService<>(new TeamDAO(connectorDB.getConnection()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employee", employeeService.findById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("teams", teamService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/employee/editEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(request.getParameter("id")));
        employee.setSurname(request.getParameter("surname"));
        employee.setName(request.getParameter("name"));
        employee.setPatronymic(request.getParameter("patronymic"));
        employee.setDOB(LocalDate.parse(request.getParameter("DOB")));
        employee.setEmail(request.getParameter("email"));
        employee.setSkype(request.getParameter("skype"));
        employee.setPhoneNumber(request.getParameter("phoneNumber"));
        employee.setEmploymentDate(LocalDate.parse(request.getParameter("employmentDate")));
        employee.setExperience(Integer.parseInt(request.getParameter("experience")));
        employee.setDeveloperLevel(Employee.DeveloperLevel.valueOf(request.getParameter("developerLevel")));
        employee.setEnglishLevel(Employee.EnglishLevel.valueOf(request.getParameter("englishLevel")));
        employee.setTeam(teamService.findById(Integer.parseInt(request.getParameter("idTeam"))));
        employeeService.update(employee);
        response.sendRedirect(request.getContextPath() + "/employees");
    }
}
