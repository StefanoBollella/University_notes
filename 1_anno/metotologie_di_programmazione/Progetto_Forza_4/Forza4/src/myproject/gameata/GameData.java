package myproject.gamedata;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import myproject.gamestate.GameStateInformation;
import myproject.player.Tokenable;

  /** The class implements methods for managing game data on file.
   * Saves the entire game state to file or recovers the game data
   * of a previously saved game.
   * Search for a match in the file by its name.
   */

public class GameData {
    
	/** The name of the file on which the class methods operate. */
    
	 private String fileName;
     
    
	/** Initialises a newly created object of type GameData.
     * 
     * @param fileName
     *        The name of the file to operate on.
     */
     
     public GameData(String fileName) {
    	 
    	 this.fileName = fileName; 
     }
	 
  
     /** Write the status of the game to file.
      * Stores the <code>nameGame</code> and the current state of the game, <code>gameStateToSave</code>
      * The text is formatted to appear on a single column within the file.
      * A sentinel character '#' is added when the writing of the status is completed.
      * A sentinel character '*' is added to locate the name of the game within the file
      *
      * @param nameGame
      *        The name of the game chosen by the current player.
      *
      * @param gameStateToSave
      *        The current state of the game to be saved. 
      * 
      * @throws FileNotFoundException
      *         Reports that the attempt to open the file in which to save the game failed.
      */
     
      public void saveGame(String nameGame, GameStateInformation gameStateToSave) throws FileNotFoundException{
		
    	  if(gameStateToSave != null) {
    	
    	    try(FileWriter out = new FileWriter(this.fileName, true)){
		        
    	    	
			    out.write("*\n");	
			    out.write(nameGame+"\n");
			    out.write(gameStateToSave.getCurrentPlayer().getName()+"\n");
			    out.write(gameStateToSave.getCurrentPlayer().getColor()+"\n");
			    out.write(gameStateToSave.getSecondPlayer().getName()+"\n");
			    out.write(gameStateToSave.getSecondPlayer().getColor()+"\n");
			
			    Tokenable[][] grid = gameStateToSave.getGameGrid().getGrid(); 
			  
			    for(int i = 0; i < grid.length; i++ ) {
 					for(int j = 0; j< grid[0].length; j++) {
 					  
 						   if(grid[i][j] != null) {
 							   out.write(grid[i][j].getColor()+"\n");
 						   }
 						   else {
 							   out.write("_\n");
 						   }
 						 }
 			           }
                      out.write("#\n");
		    }
    	    catch(IOException e) {
				
				System.out.print(e.getMessage());
		    }
    	  }
    	  else return; 
      }		   
 
      
      /** Reads from file the status of a previously saved game.
       * First search the name of the match within the file, 
       * if the name of the game is found, it starts loading 
       * of the remaining data such as the name of the two players
       * and the game grid until the sentinel character '#',excluded.
       * The '*' character is excluded from loading.
       * 
       * @param nameGame
       *        The name of the game to be loaded.
       *    
       * @return A vector containing information on matches recovered from the file.
       * 
       * @throws FileNotFoundException
       *         Report that the attempt to open the file from which to restore a saved game has failed.
       * 
       */
		   
		   
       public ArrayList<String> readGame(String nameGame) throws FileNotFoundException{
			   
	       ArrayList<String> savedGame = new ArrayList<>();
	 
	       try(Scanner in = new Scanner(new FileReader(this.fileName))){
				 
		       while(in.hasNextLine()) {
					
			       String line = in.nextLine(); 
				 	
				   if(line.trim().equalsIgnoreCase(nameGame)) {
						
					  savedGame.add(line);
						
				      while(in.hasNextLine() && !line.equals("#")) {
							
						  line = in.nextLine(); 
						
					   	  if(!line.equals("#") && !line.equals("*")) {
							 
					   		  savedGame.add(line.trim()); 
						   }
					   }
				    }
					 
				}
			}	
			catch(FileNotFoundException e) {
				 System.out.println(e.getMessage());
			}
	    return savedGame;   
	  }
	   

     /** Search for a game in a file by name.
      * The search stops at the first occurrence 
      * of the name of the match to be searched within the file.
      *   
      * @param nameGame
      *        The name of the game to be searched.
      *            
      * @return <code>true</code> if the match was found <code>false</code> otherwise
      * 
      * @throws FileNotFoundException
      *         Report that the attempt to open the file in which to search for the saved game has failed.
      */
       
   
      public boolean searchGame(String nameGame) throws FileNotFoundException{
    	
    	  try(Scanner in = new Scanner(new FileReader(this.fileName))){
			 
			  while(in.hasNextLine()) {
					  
			      String line = in.nextLine(); 
					
			      if(line.trim().equals("*")) {
						
			    	 String nameGameLine = in.nextLine(); 
			    	
			    	 if(nameGameLine.trim().equalsIgnoreCase(nameGame)) {  
			    	
    	               return true; 
                     }
			      }   
			   }
		  }
    	   catch(FileNotFoundException e) {
			
			 System.out.println(e.getMessage());
		   }
        return false; 
       }	
    	
      
}
