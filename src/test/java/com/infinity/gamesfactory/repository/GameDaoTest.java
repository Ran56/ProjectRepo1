package com.infinity.gamesfactory.repository;

import com.infinity.gamesfactory.ApplicationBootstrap;
import com.infinity.gamesfactory.model.Company;
import com.infinity.gamesfactory.model.Console;
import com.infinity.gamesfactory.model.Game;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class GameDaoTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ConsoleDao consoleDao;
    private Console console = new Console();

    @Autowired
    private CompanyDAO companyDAO;
    private Company company = new Company();

    @Autowired
    private GameDao gameDao = new GameDaoImpl();
    private Game game = new Game();



    @Before
    public void setUp()
    {
        company.setName("Mt");
        company.setIndustry("Software development" + " "+"Computer hardware" + " "+
                "Consumer electronics" + " "+
                "Social networking service" + " "+
                "Cloud computing" + " " +
                "Video games" + " "+
                "Internet");
        company.setDescription("this is mt");
        company.setLocation("Redmond, Washington");
        company.setWebAddress("www.aa112233.com");
        companyDAO.save(company);


        console.setName("Hellow");
        console.setCompany(company);
        consoleDao.save(console);

        game.setName("Red And Blue And Yellow");
        game.setConsole(console);
        gameDao.save(game);
    }

    @After
    public void tearDown()
    {
        gameDao.delete(game);
        consoleDao.delete(console);
        companyDAO.delete(company);
    }

    @Test
    public void getGames()
    {
        int expect = 3;
        Assert.assertEquals(expect,gameDao.getGames().size());
    }

    @Test
    public void getGamesEagerBy()
    {
        Game game1 = gameDao.getGameEagerBy(game.getId());
        Assert.assertEquals(game.getName(),game1.getName());
    }

}
