package by.andesen.intensive4.servlets.employee;

import by.andesen.intensive4.entities.Employee;
import by.andesen.intensive4.entities.Team;
import by.andesen.intensive4.jdbc.connector.ConnectorDB;
import by.andesen.intensive4.jdbc.dao.EmployeeDAO;
import by.andesen.intensive4.jdbc.dao.TeamDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "UpdateEmployeeServlet", value = "/employees/edit")
public class UpdateEmployeeServlet extends HttpServlet {

    EmployeeDAO employeeDAO;
    TeamDAO teamDAO;

    @Override
    public void init() throws ServletException {
        try {
            employeeDAO = new EmployeeDAO(ConnectorDB.getConnection());
            teamDAO = new TeamDAO(ConnectorDB.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employee", employeeDAO.findEntityById(Integer.parseInt(request.getParameter("id"))));
        request.setAttribute("teams", teamDAO.findAll());
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
        employee.setTeam(teamDAO.findEntityById(Integer.parseInt(request.getParameter("idTeam"))));
        employeeDAO.update(employee);
        response.sendRedirect(request.getContextPath() + "/employees");
    }
}