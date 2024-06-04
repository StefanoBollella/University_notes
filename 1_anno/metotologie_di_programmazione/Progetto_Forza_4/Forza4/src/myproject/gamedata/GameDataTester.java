package myproject.gamedata;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import myproject.gamestate.*;
import myproject.grid.*;
import myproject.player.*;


/* Test methods and the game Data class. */

public class GameDataTester {

	public static void main(String[] args) throws FileNotFoundException{
		
		String nameFile = "Saved_Games_Test.txt";
		GameData dataTest = new GameData(nameFile); 
		
		
		String namePlayer1 = "Bianchi"; 
		String namePlayer2 = "Rossi";
		
		
		Tokenable player1 = new Player(namePlayer1, "R");
	    Tokenable player2 = new Player(namePlayer2, "B");
		
	    Tokenable[][] grid = new Player[6][7];
	    GridManager gridGame = new Grid(grid); 
		
	    GameStateInformation gameStateTest = new GameState(player1,player2,gridGame); 
	    
	    gameStateTest.move(0); 
	    gameStateTest.move(1);
	    
	    /* Test the saving of the game. */
	    
	    dataTest.saveGame("gameTest", gameStateTest);
	    
	    
	   /* Test the Attempt to save a non-existent game, 
          with a reference to an object of type GameState null.
	    */
	    
	    GameStateInformation gameStateNullTest = null; 
	    dataTest.saveGame("gameTestnull", gameStateNullTest);
	    
	    
	    
       /* Tests the search for a saved game by its name. */
	     
	    System.out.println(dataTest.searchGame("gameTest"));
	    System.out.println("Expected : true");
	    
	    /* Test the search attempt for a inexistent match.
	     * For example, one of the two players.
	     */
	    
	    System.out.println(dataTest.searchGame("Bianchi"));
	    System.out.println("Expected : false");
	    
	    
	    /* Test the recovery of a saved game. */
	    
	    ArrayList<String> savedGameData1 = dataTest.readGame("GameTest");
	    
	    System.out.println(savedGameData1.isEmpty());
	    System.out.println("Expected : false");
	 
	    /* Test the attempt to restore a inexistent match. */

	    ArrayList<String> savedGameData2 = dataTest.readGame("inexistentGame");
	    System.out.println(savedGameData2.isEmpty());
	    System.out.println("Expected : true");

	}

}
