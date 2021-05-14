package by.andersen.intensive4.controllers.employeeServlets;

import by.andersen.intensive4.entities.Employee;
import by.andersen.intensive4.service.EntityService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "GetAllEmployeesServlet", value = "/employees")
public class GetAllEmployeesServlet extends HttpServlet {

    private EntityService<Employee> employeeService;

    @Override
    public void init() throws ServletException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        employeeService = applicationContext.getBean("employeeService", EntityService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("employees", employeeService.findAll());
        request.getRequestDispatcher("/WEB-INF/views/employee/indexEmployees.jsp").forward(request, response);
    }
}
