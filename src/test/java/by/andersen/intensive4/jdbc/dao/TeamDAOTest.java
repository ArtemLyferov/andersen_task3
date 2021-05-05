package by.andersen.intensive4.jdbc.dao;


import by.andesen.intensive4.entities.Team;
import by.andesen.intensive4.jdbc.connector.ConnectionPool;
import by.andesen.intensive4.jdbc.dao.TeamDAO;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class TeamDAOTest {
    public static TeamDAO teamDAO;
    public static Team lastTeamInList;
    public static int countAdd;

    @BeforeClass
    public static void initDAO() throws SQLException {
        ConnectionPool connectionPool = ConnectionPool.create(
                "jdbc:postgresql://localhost:5432/employee_control_system_db",
                "postgres", "postgres");
        teamDAO = new TeamDAO(connectionPool.getConnection());
        Team team = new Team("Test team 1");
        teamDAO.create(team);
        List<Team> teams = teamDAO.findAll();
        lastTeamInList = teams.get(teams.size() - 1);
    }

    @AfterClass
    public static void clearDAO() {
        teamDAO.delete(lastTeamInList.getId());
        lastTeamInList = null;
        teamDAO = null;
    }

    @Test
    public void createTeamTest() {
        int expected = 1;
        int actual = teamDAO.create(new Team("Team test 2"));
        countAdd++;
        assertEquals(expected, actual);
        teamDAO.delete(lastTeamInList.getId() + countAdd);
    }

    @Test
    public void findAllTeamsTest() {
        assertNotNull(teamDAO.findAll());
    }

    @Test
    public void getTeamByIdTest() {
        Team expected = lastTeamInList;
        Team actual = teamDAO.findEntityById(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void updateTeamTest() {
        lastTeamInList.setTeamName("Test team 3");
        int expected = 1;
        int actual = teamDAO.update(lastTeamInList);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteTeamTest() {
        teamDAO.create(new Team("Test tem 4"));
        countAdd++;
        int expected = 1;
        int actual = teamDAO.delete(lastTeamInList.getId() + countAdd);
        assertEquals(expected, actual);
    }
}