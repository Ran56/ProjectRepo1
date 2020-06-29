package com.infinity.gamesfactory.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class GameJDBCDaoTest {



    private static Logger logger = LoggerFactory.getLogger(GameJDBCDaoTest.class);
    private GameJDBCDao gameJDBCDAO;
    private Game game;
    @Before
    public void setUP()
    {
        gameJDBCDAO = new GameJDBCDao();
        game = new Game();
    }

    @After
    public void tearDown()
    {
        gameJDBCDAO = null;
    }

    @Test
    public void save()
    {


        game.setName("Super Mario Odyssey1");
        game.setPrice(59.99);
        game.setGenre("Platformer, Action");
        game.setPlayers("up to 2 players");
        game.setConsoleId(1);
        game.setSupportedLanguages("Japanese, English, French, German, Italian, Spanish, Dutch, Russian, Chinese");

        assertEquals(1, gameJDBCDAO.save(game));
        logger.info("Insert data succeed");

    }

    @Test
    public void delete()
    {

        game.setName("Super Mario Odyssey1");
        assertEquals(1,gameJDBCDAO.delete(game.getName()));
        logger.info("Delete data succeed");

    }

    @Test
    public void update()
    {

        String oldName = "Super Mario Odyssey1";
        game.setName("Super Mario Odyssey");
        game.setPrice(59.99);
        game.setGenre("Platformer, Action");
        game.setPlayers("up to 2 players");
        game.setConsoleId(1);
        game.setSupportedLanguages("Japanese, English, French, German, Italian, Spanish, Dutch, Russian, Chinese");

        assertEquals(1, gameJDBCDAO.update(oldName,game));
        assertNotSame(oldName,game.getName());
        logger.info("Update data succeed");

    }

    @Test
    public void getConsoles()
    {
        assertEquals(2,gameJDBCDAO.getGames().size());
    }




}
