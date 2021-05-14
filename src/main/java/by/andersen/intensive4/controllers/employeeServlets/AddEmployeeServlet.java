package by.andersen.intensive4.controllers.employeeServlets;

import by.andersen.intensive4.entities.Employee;
import by.andersen.intensive4.entities.Team;
import by.andersen.intensive4.service.EntityService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "AddEmployeeServlet", value = "/employees/new")
public class AddEmployeeServlet extends HttpServlet {

    private EntityService<Employee> employeeService;
    private EntityService<Team> teamService;

    @Override
    public void init() throws ServletException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        employeeService = applicationContext.getBean("employeeService", EntityService.class);
        teamService = applicationContext.getBean("teamService", EntityService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("teams", teamService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/employee/newEmployee.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = new Employee();
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
        employee.setTeam(teamService.findById(Integer.parseInt(request.getParameter("id"))));
        employeeService.create(employee);
        response.sendRedirect(request.getContextPath() + "/employees");
    }
}
