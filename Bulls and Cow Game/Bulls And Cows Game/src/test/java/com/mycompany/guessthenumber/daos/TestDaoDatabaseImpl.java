/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.daos;

import com.mycompany.guessthenumber.App;
import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mac
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.TestApplicationConfiguration.class)
@Repository
@ActiveProfiles(profiles = "test")
public class TestDaoDatabaseImpl {

    @Autowired
    GuessTheNumberDao dao;

    public TestDaoDatabaseImpl() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dao.deleteAllGames();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of newGame method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testNewGameGoldenPath() {
        try {
            //combines NewGame() and getGameById().

            int targetNum = 7685;

            Game added = dao.newGame(targetNum);

            int id = added.getGameId();

            Game validatedGame = dao.getGameById(id);

            assertEquals(false, validatedGame.getIsDone());
            assertEquals(7685, validatedGame.getTargetNum());

        } catch (InvalidTargetNumberException | InvalidGameIdException ex) {
            fail();
        }
    }

    /**
     * Test of getGameById method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testGetGameByIdGoldenPath() {
        try {
            int targetNum = 7685;

            Game added = dao.newGame(targetNum);

            int id = added.getGameId();

            Game validatedGame = dao.getGameById(id);

            assertEquals(false, validatedGame.getIsDone());
            assertEquals(7685, validatedGame.getTargetNum());

        } catch (InvalidTargetNumberException | InvalidGameIdException ex) {
            fail();
        }
    }

    @Test
    public void testGetGameByIdInvalidId() {
        try {
            Game toTest = dao.getGameById(9);
            assertNull(toTest);
        } catch (InvalidGameIdException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of newRound method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testNewRoundGoldenPath() {
        try {
            int targetNum = 9716;
            Game getGame = dao.newGame(targetNum);
            int gameId = getGame.getGameId();

            Round toTest = new Round();
            toTest.setGameId(gameId);
            toTest.setGuessedNum(9716);
            toTest.setPartialMatchCount(1);
            toTest.setExactMatchCount(2);
            toTest.setGuessTime(LocalDateTime.of(2019, 10, 01, 12, 01, 30));

            dao.newRound(toTest);

            assertEquals(gameId, toTest.getGameId());
            assertEquals(9716, toTest.getGuessedNum());
            assertEquals(1, toTest.getPartialMatchCount());
            assertEquals(2, toTest.getExactMatchCount());
            assertEquals(LocalDateTime.of(2019, 10, 01, 12, 01, 30), toTest.getGuessTime());

        } catch (RoundDaoException | InvalidTargetNumberException | InvalidGameIdException ex) {
            fail();
        }
    }

    @Test
    public void testNewRoundInvalidGameId() {
        try {
            int invalidGameId = 3000;
            Round toTest = new Round();
            toTest.setGameId(invalidGameId);
            toTest.setGuessedNum(9716);
            toTest.setPartialMatchCount(1);
            toTest.setExactMatchCount(2);
            toTest.setGuessTime(LocalDateTime.of(2019, 10, 01, 12, 01, 31));

            dao.newRound(toTest);
            fail();
        } catch (RoundDaoException ex) {
            fail(ex.getMessage());
        } catch (InvalidGameIdException ex) {
        }
    }

    @Test
    public void testNewRoundNullGuessedTime() {
        try {
            int targetNum = 9182;
            Game getGame = dao.newGame(targetNum);
            int gameId = getGame.getGameId();

            Round toTest = new Round();
            toTest.setGameId(gameId);
            toTest.setGuessedNum(9716);
            toTest.setPartialMatchCount(1);
            toTest.setExactMatchCount(1);
            toTest.setGuessTime(null);

            dao.newRound(toTest);
            fail();
        } catch (RoundDaoException ex) {

        } catch (InvalidGameIdException | InvalidTargetNumberException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of getAllGames method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testGetAllGamesGoldenPath() {
        try {
            int targetNum1 = 4567;
            Game game1 = dao.newGame(targetNum1);

            int targetNum2 = 5678;
            Game game2 = dao.newGame(targetNum2);

            List<Game> allGames = dao.getAllGames();

            assertEquals(2, allGames.size());

        } catch (InvalidTargetNumberException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of getAllRounds method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testGetAllRoundsGoldenPath() {
        try {
            int targetNum = 1234;
            Game toAdd = dao.newGame(targetNum);
            int gameId = toAdd.getGameId();

            Round round1 = new Round();
            round1.setGameId(gameId);
            round1.setGuessedNum(1246);
            round1.setPartialMatchCount(1);
            round1.setExactMatchCount(2);
            round1.setGuessTime(LocalDateTime.of(2019, 10, 01, 12, 00, 01));
            Round oneAdd = dao.newRound(round1);

            Round round2 = new Round();
            round2.setGameId(gameId);
            round2.setGuessedNum(1243);
            round2.setPartialMatchCount(2);
            round2.setExactMatchCount(2);
            round2.setGuessTime(LocalDateTime.of(2019, 10, 01, 12, 05, 30));
            Round twoAdd = dao.newRound(round2);

            List<Round> allRounds = dao.getAllRounds(gameId);

            assertEquals(2, allRounds.size());
            
        } catch (InvalidTargetNumberException | RoundDaoException | InvalidGameIdException ex) {
            fail();
        }
    }

    @Test
    public void testGetAllRoundsInvalidGameId() {
        try {
            int targetNum = 1234;
            Game toAdd = dao.newGame(targetNum);
            int gameId = toAdd.getGameId();
            
            Round round1 = new Round();
            round1.setGameId(gameId);
            round1.setGuessedNum(1246);
            round1.setPartialMatchCount(1);
            round1.setExactMatchCount(2);
            round1.setGuessTime(LocalDateTime.of(2019, 10, 01, 15, 00, 02));
            Round oneAdd = dao.newRound(round1);
            
            Round round2 = new Round();
            round2.setGameId(gameId);
            round2.setGuessedNum(1243);
            round2.setPartialMatchCount(2);
            round2.setExactMatchCount(2);
            round2.setGuessTime(LocalDateTime.of(2019, 10, 01, 12, 05, 30));
            Round twoAdd = dao.newRound(round2);
            
            List<Round> getRounds = dao.getAllRounds(1000);
            fail();
            
        } catch (InvalidTargetNumberException | RoundDaoException ex) {
            fail(ex.getMessage());
        } catch (InvalidGameIdException ex) {

        }   
    }

    /**
     * Test of editGame method, of class GuessTheNumberDaoImpl.
     */
    @Test
    public void testEditGameGoldenPath() {
        try {
            int targetNum = 1234;
            Game toAdd = dao.newGame(targetNum);
            int gameId = toAdd.getGameId();

            Round round = new Round();
            round.setGameId(gameId);
            round.setGuessedNum(1234);
            round.setPartialMatchCount(0);
            round.setExactMatchCount(4);
            round.setGuessTime(LocalDateTime.of(2019, 10, 05, 12, 00, 05));

            Round oneAdd = dao.newRound(round);

            dao.editGame(gameId);

            Game toTest = dao.getGameById(gameId);

            assertEquals(true, toTest.getIsDone());
        } catch (InvalidTargetNumberException | RoundDaoException | InvalidGameIdException ex) {
            fail();
        }
    }

}
