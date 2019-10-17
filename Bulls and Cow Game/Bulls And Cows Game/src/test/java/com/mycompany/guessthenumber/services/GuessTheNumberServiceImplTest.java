/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.guessthenumber.services;

import com.mycompany.guessthenumber.App;
import com.mycompany.guessthenumber.daos.GuessTheNumberDao;
import com.mycompany.guessthenumber.daos.TestDaoInMemo;
import com.mycompany.guessthenumber.models.Game;
import com.mycompany.guessthenumber.models.Round;
import java.time.LocalDateTime;
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
@ActiveProfiles(profiles = "Service Test")
public class GuessTheNumberServiceImplTest {

//  @Autowired
    GuessTheNumberService service;

    public GuessTheNumberServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //This will wipe out the data in your inMemo Dao so you start fresh.
        service = new GuessTheNumberServiceImpl(new TestDaoInMemo());
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of newGame method, of class GuessTheNumberServiceImpl.
     */
    @Test
    public void testNewGameGoldenPath() {
        try {
            //1. Arrange
            //2. Act
            int toTest = service.newGame();
            //3.Assert
            assertEquals(3, toTest);

        } catch (InvalidNumberException ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Test of guess method, of class GuessTheNumberServiceImpl.
     */
    @Test
    public void testGuessGoldenPath() {
        try {
            //1.Arrange
            Round toTest = new Round();
            toTest.setGuessedNum(4675); //targetNum for game #2 = 5678.
            toTest.setGameId(2);
            toTest.setPartialMatchCount(2);
            toTest.setExactMatchCount(2);

            //2.Act
            service.guess(toTest);

            //3.Assert
            assertEquals(4675, toTest.getGuessedNum());
            assertEquals(2, toTest.getGameId());
            assertEquals(1, toTest.getPartialMatchCount());
            assertEquals(2, toTest.getExactMatchCount());
            assertEquals(3, toTest.getRoundId());

            //1.Arrange
            Round testAgain = new Round();
            testAgain.setGuessedNum(5678); //targetNum for game #2 = 5678.
            testAgain.setGameId(2);
            testAgain.setPartialMatchCount(0);
            testAgain.setExactMatchCount(4);

            //2.Act
            service.guess(testAgain);
            //3.Assert
            assertEquals(5678, testAgain.getGuessedNum());
            assertEquals(2, testAgain.getGameId());
            assertEquals(0, testAgain.getPartialMatchCount());
            assertEquals(4, testAgain.getExactMatchCount());
            assertEquals(4, testAgain.getRoundId());

            Game testGame = service.getGameById(2);
            assertEquals(true, testGame.getIsDone());
        } catch (InvalidNumberException | InvalidIdException | InvalidRoundException ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testGuessInvalidId() {
        try {
            //1. Arrange
            Round toTest = new Round();
            toTest.setGuessedNum(4675); //targetNum for game #2 = 5678.
            toTest.setGameId(9);

            //2. Act
            service.guess(toTest);
            fail();
            //3. Assert
        } catch (InvalidNumberException | InvalidRoundException ex) {
            fail(ex.getMessage());
        } catch (InvalidIdException ex) {
        
        }
    }

    @Test
    public void testGuessDuplicateNumber() {
        try {
            //1. Arrange
            Round toTest = new Round();
            toTest.setGuessedNum(1123);
            toTest.setGameId(2);
            //2. Act
            service.guess(toTest);
            fail();
            //3. Assert
        } catch (InvalidIdException | InvalidRoundException ex) {
            fail();
        } catch (InvalidNumberException ex) {
        
        } 
    }

    /**
     * Test of getGameById method, of class GuessTheNumberServiceImpl.
     */
    @Test
    public void testGetGameByIdGoldenPath(){
        try {
            //1. Arrange
            //2. Act
            Game toTest = service.getGameById(1);
            //3. Assert
            assertEquals(1, toTest.getGameId());
            assertEquals(true, toTest.getIsDone());
            assertEquals(1234, toTest.getTargetNum());
        } catch (InvalidIdException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testGetGameByIdInvalidId() {
        try {
            //1. Arrange
            //2. Act
            
            Game toTest = service.getGameById(7);
            fail();
            //3. Assert
            
        } catch (InvalidIdException ex) {
            
        }
    }

    /**
     * Test of getAllGames method, of class GuessTheNumberServiceImpl.
     */
    @Test
    public void testGetAllGamesGoldenPath() {
        //GuessTheNumberService service = new GuessTheNumberServiceImpl(new TestDaoInMemo());
        //1. Arrange
        //2. Act
        List<Game> toTest = service.getAllGames();
        //3. Assert
        assertEquals(2, toTest.size());
        assertEquals(true, toTest.get(0).getIsDone());
        assertEquals(1234, toTest.get(0).getTargetNum());
        assertEquals(false, toTest.get(1).getIsDone());
        assertEquals(0, toTest.get(1).getTargetNum());
    }
    
    /**
     * Test of getAllRounds method, of class GuessTheNumberServiceImpl.
     */
    @Test
    public void testGetAllRoundsGoldenPath() {
        try {
            //1. Arrange
            //2. Act
            List<Round> toTest = service.getAllRounds(1);
            //3. Assert
            assertEquals(2, toTest.size());
        } catch (InvalidIdException | InvalidRoundException ex) {
            fail(ex.getMessage());
        }
    }
    
    @Test
    public void testGetAllRoundsInvalidId() {
        try {
            List<Round> toTest = service.getAllRounds(8);
            fail();   
            
        } catch (InvalidRoundException ex) {
            fail();
        } catch (InvalidIdException ex) {

        }
    }

}
