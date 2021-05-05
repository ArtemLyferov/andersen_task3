package by.andersen.intensive4.jdbc.dao;

import by.andesen.intensive4.entities.Employee;
import by.andesen.intensive4.entities.Team;
import by.andesen.intensive4.jdbc.connector.ConnectionPool;
import by.andesen.intensive4.jdbc.dao.EmployeeDAO;
import by.andesen.intensive4.jdbc.dao.TeamDAO;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeDAOTest {
    public static TeamDAO teamDAO;
    public static EmployeeDAO employeeDAO;
    public static Team lastTeamInList;
    public static Employee lastEmployeeInList;
    public static int countAdd;

    @BeforeClass
    public static void initDAO() throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.create(
                "jdbc:postgresql://localhost:5432/employee_control_system_db",
                "postgres", "postgres");
        teamDAO = new TeamDAO(connectionPool.getConnection());
        employeeDAO = new EmployeeDAO(connectionPool.getConnection());

        Team team = new Team("Test team");
        teamDAO.create(team);
        List<Team> teams = teamDAO.findAll();
        lastTeamInList = teams.get(teams.size() - 1);

        Employee employee = new Employee("Petrov", "Anton", "Semenovich",
                LocalDate.of(1990, 3, 12), "petrov@gmail.com", "live:petrov",
                "+375291112233", LocalDate.of(2018, 3, 2), 4,
                Employee.DeveloperLevel.J3, Employee.EnglishLevel.A2, lastTeamInList);
        employeeDAO.create(employee);
        List<Employee> employees = employeeDAO.findAll();
        lastEmployeeInList = employees.get(employees.size() - 1);
    }

    @AfterClass
    public static void clearDAO() {
        employeeDAO.delete(lastEmployeeInList.getId());
        teamDAO.delete(lastTeamInList.getId());
        lastEmployeeInList = null;
        lastTeamInList = null;
        employeeDAO = null;
        teamDAO = null;
    }

    @Test
    public void createEmployeeTest() {
        Employee employee = new Employee("Ivanov", "Anton", "Semenovich",
                LocalDate.of(1990, 3, 12), "petrov@gmail.com", "live:petrov",
                "+375291112233", LocalDate.of(2018, 3, 2), 4,
                Employee.DeveloperLevel.J3, Employee.EnglishLevel.A2, lastTeamInList);
        int expected = 1;
        int actual = employeeDAO.create(employee);
        countAdd++;
        assertEquals(expected, actual);
        employeeDAO.delete(lastEmployeeInList.getId() + countAdd);
    }

    @Test
    public void findAllEmployeesTest() {
        assertNotNull(employeeDAO.findAll());
    }

    @Test
    public void getEmployeeByIdTest() {
        Employee expected = lastEmployeeInList;
        Employee actual = employeeDAO.findEntityById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void updateEmployeeTest() {
        lastEmployeeInList.setSurname("Kotov");
        int expected = 1;
        int actual = employeeDAO.update(lastEmployeeInList);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteEmployeeTest() {
        Employee employee = new Employee("Popov", "Anton", "Semenovich",
                LocalDate.of(1990, 3, 12), "petrov@gmail.com", "live:petrov",
                "+375291112233", LocalDate.of(2018, 3, 2), 4,
                Employee.DeveloperLevel.J3, Employee.EnglishLevel.A2, lastTeamInList);
        employeeDAO.create(employee);
        countAdd++;
        int expected = 1;
        int actual = employeeDAO.delete(lastEmployeeInList.getId() + countAdd);
        assertEquals(expected, actual);
    }
}