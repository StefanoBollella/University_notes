package myproject.gamemanager;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import myproject.gamedata.GameData;
import myproject.gamestate.*;
import myproject.grid.*;
import myproject.player.*;

/** The class starts a new game or restores a previously saved game.
 *  Includes the main method and <code>runGame</code> method to start a new game or a saved game,
 *  also implements a method to <code>restore</code> the state of a saved game and 
 *  a method <code>display</code>to view the game grid.
 *
 */																				

public class GameManager{ 													
     
	 //game grid size
	 private static final int ROWS = 6; 
	 private static final int COLUMNS = 7; 

	 //game menu settings 
     private static final String  NEW_GAME = "1";
     private static final String  LOAD_GAME = "2"; 
	 private static final int     PAUSE_MENU = -1; 
     private static final String  SAVE_CURRENT_GAME = "2"; 
	 private static final String  RETURN_CURRENT_GAME = "1"; 
     
	 //color of tokens
	 private static final String RED = "Red"; 
	 private static final String BLUE = "Blue"; 
	
	  
	 
	/** Implements the game menu, which allows a new game to be started
	 *  or a saved game to be loaded by giving its name.
	 * 
	 * @param args[0]
	 *        The path to the file where games are saved.
	 *      
	 * 
	 * @throws FileNotFoundException
	 *         Report that the attempt to open the file in which to search for the saved game has failed.
	 */
 
   public static void main(String[] args) throws FileNotFoundException {
	
	  String fileName = args[0]; 
	
	  GameData gameDataFile = new GameData(fileName); 
	  
	  GameStateInformation newGameState = null;
	  
	  Scanner input = new Scanner(System.in);    
    
      boolean option = true; 
      while(option){	    
	    
    	 System.out.println("* * * * * * * * * * * *");
         System.out.println("* * * * FORZA 4 * * * *");
         System.out.println("* * * * * * * * * * * *");
         System.out.println("\n[ 1 ] new game\n"
 				          + "[ 2 ] load a saved game,\n"
         				  + "press any other button to EXIT");
 	
         String choiceMenu = input.next(); 
     
         switch(choiceMenu) {     
      
             case NEW_GAME :    
    	                      System.out.println("\t**** NEW GAME ****\n");  
    	                      System.out.println("Enter the names of the two players : ");      
       	
    	                      input.nextLine(); 
 	   
 	                          System.out.println("name of player 1: ");
 	                          String namePlayer1 = input.next(); 
 	       
 	                          input.nextLine();
 	       
 	                          System.out.println("name of player 2: "); 
 	                          String namePlayer2 =  input.next(); 
 	       
 	                          Tokenable player1 = new Player(namePlayer1,RED); 
 	                          Tokenable player2 =  new Player(namePlayer2,BLUE); 
 	                      
 	                          
 	                          GridManager gridGame = new Grid(new Player[ROWS][COLUMNS]); 

 	                          newGameState = new GameState(player1,player2,gridGame);
 	                     
 	                          runGame(newGameState, gameDataFile ,input);
 	                          
 	                          break; 
  
             case LOAD_GAME : /* Restores the information of a saved game */
            	                
            	              System.out.println("\t*** LOADING A SAVED GAME ****\n"); 
            	              System.out.println("Enter the name of the game:\n");
            	              String nameGame = input.next();  
            	       	   
            	              if(gameDataFile.searchGame(nameGame)) {
            	                 
            	            	/* Invokes the readGame method of the GameData class,
            	            	 * which returns an ArrayList of type String with all
            	            	 * the information required for restoring.
            	                 */
            	            	  
            	            	  ArrayList<String> dataRestored = gameDataFile.readGame(nameGame);

            	                  Player player1Saved = new Player(dataRestored.get(1),dataRestored.get(2));
            	                  Player player2Saved = new Player(dataRestored.get(3),dataRestored.get(4));

            	                  Tokenable[][] grid = new Player[ROWS][COLUMNS];
                                 
            	                 /* Extrapolates the exact position of the tokens 
            	                  * from the ArrayList to reconstruct the game grid.
            	                  */
            	                  int w = 5; 
            	                  for(int i = 0; i < ROWS; i++) {    
            	                   
            	        	          if(i == 0) {
            	           	            w += i * COLUMNS; 
            	                      }
            	                      else{
            	                 	    w += COLUMNS; 
            	                      }
            	       	           
            	        	          for(int j = 0; j < COLUMNS; j++) {
            	       		
            	       		             if(dataRestored.get(w + j).equals("_")) {
            	       			            grid[i][j] = null;
            	       		             }
            	       	                 else if(dataRestored.get(w + j).equals(player1Saved.getColor())) {
            	       	    	            grid[i][j] = player1Saved;
            	       	                 }
            	       	                 else { 
            	       	    	             grid[i][j] = player2Saved;
            	       	                 }
            	                       }
            	                   }

            	                  GridManager gridGameSaved = new Grid(grid);

            	                  newGameState = new GameState(player1Saved,player2Saved,gridGameSaved);
            	             
            	                  runGame(newGameState, gameDataFile, input);
            	              
            	               }
                               else {
            	    	          System.out.println("Game not found\n");
            	               } 
            	                    
                               break; 
 	        
             default : return;     
          }
       input.nextLine();
   }
 input.close();
}
   
   
   
  /** Manages the execution of the game by alternating the game turn between the two players,
   *  which gives the current player the possibility to pause and save the game.
   *  
   * 
   * @param newGameState
   *        A reference of type <code>GameStateInformation</code> that represents the state of the game.
   * 
   * @param gameDataFile
   *        A reference variable of type <code>GameData</code>, used to save the game data to file
   * 
   * @param input
   *        A reference of type Scanner.
   * 
   * @throws FileNotFoundException
   *         Signals that the attempt to open the file on which to save the game failed.
   */

   public static void runGame(GameStateInformation newGameState, GameData gameDataFile ,Scanner input) throws FileNotFoundException{


	   
	      boolean exitGame = true; 
	          
	      while(exitGame && ! newGameState.getGameGrid().checkFull()){
	   
	               /*The current player's turn is managed at this point */ 
	               
	               boolean turnCompleted = true;      
	            
	               while(turnCompleted){        
	            
	            	  display(newGameState.getGameGrid().getGrid()); 
	            
	            	   System.out.println("\nplayer "+newGameState.getCurrentPlayer().getName()
	          		            +"\ncolor  "+newGameState.getCurrentPlayer().getColor()+"\n");

	            	  
	                   System.out.println("choose a column, otherwise press [-1] to pause the game\n");
	    
	                   try { 
		              
	                       int choiceColumn = input.nextInt(); 
	                     
	                       if( choiceColumn == PAUSE_MENU){ 
		    				
	                    	  boolean pause = true; 
	                    	  
	                    	  while(pause) {
	                    		   
	                    		   System.out.println("\n\t* * * * * * * * *");
	                    		   System.out.println("\t*  Pause Menu   *");
	                    		   System.out.println("\t* * * * * * * * *\n");  
	                    	       System.out.println(" press [ 1 ] to resume the game\n"
	                    	      	                 +" press [ 2 ] to save the current game\n"
	                    	                         +" press any other button to EXIT the game\n");
	                    	    
	                    	        String pauseChoice = input.next();
	                    	      
	                    	        if(pauseChoice.equals(RETURN_CURRENT_GAME)) {
	                    	   	    
	                    	    	   pause = false; 
	                    	   	    }
	                    	        else if(pauseChoice.equals(SAVE_CURRENT_GAME)) {   
	                    	   	        
	                    	       	   System.out.println("*** Save The Current Game ***\n"); 
	                    	   	             
	                    	           System.out.println("Enter the name of the game:\n");
	                    	                     
	                    	           String nameCurrentGame = input.next(); 
	                    	            
	                    	            if(gameDataFile.searchGame(nameCurrentGame)) {
	                    	       	     
	                    	           	     System.out.println("Name already exists\n");
	                    	       	     }
	                    	             else {
	                    	               	 gameDataFile.saveGame(nameCurrentGame, newGameState );
	                    	                       
	                    	                   System.out.println("Game Saved\n"); 
	                    	             }
	                    	          }	    			      
	                    	          else {
	                    	   	          pause = false; 
	                    	        	  turnCompleted = false; 
	                    		          exitGame = false;
	                    	          }
	                    	    }
	                        } 
		    			    else if(choiceColumn >= 0 && choiceColumn < COLUMNS) {
		    				   
		    			    	newGameState.move(choiceColumn);
		    			     
		    				       if(newGameState.checkWin()){
		    				    	 
		    				    	  display(newGameState.getGameGrid().getGrid());
		    				    	  System.out.println("\n"+newGameState.getSecondPlayer().getName()
		    				    			 +" "+newGameState.getSecondPlayer().getColor()+" Wins The Game"); 
		    				         
		    				          exitGame = false; 
		    				       }
		    				      
		    				   turnCompleted = false; 
		    			    }
		    			    else {
		    				    System.out.println("* * * Insertion not allowed, repeat the insertion * * * \n");
		    			
		    			    }
		    		  }
		    		 catch(InputMismatchException e) {
		    			  System.out.println("* * * Insertion not allowed, repeat the insertion * * *\n");
		    		 }
		    		 catch(FullColumnException e) {
		    			  System.out.println(e.getMessage());
		    		 }
	                   input.nextLine(); 
	               }
	    
	      } 
   }


   
 /** Print the game grid.  
  * 
  * @param grid 
  *        Inside is stored the position of the tokens.
  *        
  */


  public static void display(Tokenable[][] grid) {
  	
       System.out.println("   0   1   2   3   4   5   6");
       System.out.println("  --- --- --- --- --- --- ---");
    
  	   for(int i = 0; i < grid.length; i++) {
  	      System.out.print(" ");
  		  for(int j= 0; j < grid[0].length ; j++) {
  		 
  			 if(grid[i][j] == null) {
  				
  		          if(j == grid[0].length -1 ) {
  		        	  
  		        	  System.out.print("|   |");  
  		          }
  		          else {
  				
  				      System.out.print("|   ");  
  		          }
  			 }
  		     else {
  				
  		    	 if(j == grid[0].length - 1 ) {
 		        	  
  		    		if(grid[i][j].getColor().equals(RED)) {
 		        	  System.out.print("| "+"R"+" |");  
  		    		}
  		    		else { 
  		    		   System.out.print("| "+"B"+" |");  
  		    	     }
  		    	  }
  		    	   else {
  		    		    if(grid[i][j].getColor().equals(RED)) {
  		    		   
 		        	       System.out.print("| "+"R"+" ");
  		    		    }
  		    		    else {
  		    		    	 System.out.print("| "+"B"+" ");
  		    		    	
  		    		    }
  		    	   }
  			  }
        }
  		System.out.println("\n  --- --- --- --- --- --- ---");
  	}
 }
  

   
   
      
   
   }
