package myproject.gamestate; 

import myproject.grid.GridManager;
import myproject.player.Tokenable;

/**
 *  The class represents the current game state. 
 *  An instance of the class stores within itself the information necessary
 *  for the continuation of the game, every time the current player makes a move,
 *  the state of the <code>gameGrid</code> is updated and the references of the <code>currentPlayer</code> and
 *  <code>secondPlayer</code> variables are exchanged.
 *  Includes methods for making a <code>move</code> or verifying the victory,<code>checkWin</code>
 *  by the current player.
 */

public class GameState implements GameStateInformation {
    
       /** The value is used to store the <code>currentPlayer</code>,
        *  the player of the current turn. 
        */
	   private Tokenable currentPlayer; 
    
	   /** The value is used to store the <code>secondPlayer</code>,
	    *  the player of the next turn.
	    */ 
       private Tokenable secondPlayer; 
       
       /** The value is used to store the <code>gameGrid</code>. */
       private GridManager gameGrid; 

	  
       /** Initializes a newly created GameState object.
        *
	    * @param player1
	    *        A <code>Tokenable</code> type reference representing the <code>currentPlayer</code>
	    *  
	    * @param player2
	    *        A <code>Tokenable</code> type reference representing the <code>secondPlayer</code>
	    * 
	    * @param gameGrid
	    *        A <code>GridController</code> type reference representing the <code>gameGrid</code>
	    */
	   
 	   public GameState(Tokenable player1, Tokenable player2, GridManager gameGrid) {
 		   
 		   this.currentPlayer = player1; 
	       this.secondPlayer = player2;  
 		   this.gameGrid = gameGrid; 
 	   }
 	   

 	   
 	   /** Returns the current player.
 	    * 
 	    * @return currentPlayer
 	    *         The current player in the game.
 	    *         
 	    */
 
        public Tokenable getCurrentPlayer() {
   		
        	return currentPlayer;
   	    }

       
       
      /** Returns the second player.
        * 
        * @return secondPlayer
        *         The second player waiting for his turn.
        */
      
        public Tokenable getSecondPlayer() {
   		
    		return secondPlayer;
        }
       
      
       
      /** Returns the game grid.
        * 
        * @return gameGrid
        */       
   	
   	    public GridManager getGameGrid() {
   	   	
           return gameGrid;
        }
   	  
   	   
   	   
   	  /** Makes a move based on the current player and the chosen column.
   	    *  If the current player's move is successful, it becomes the secondary player,
   	    *  and the player of the next turn becomes the current player.
   	    *  
   	    * @param  column
        *         The <code>column</code> selected by the <code>currentPlayer</code>.
   	    */

 	    public void move(int column) {
		   
		   gameGrid.addToken(this.currentPlayer,column);
	      
		   
		   Tokenable swapVariablePlayer = currentPlayer; 
	       currentPlayer = secondPlayer; 
	       secondPlayer =  swapVariablePlayer ; 
	    }
	   

 	 
 	  /** Checks if the <code>currentPlayer</code> has won.
 	    * 
 	    * @return <code>true</code> if the current player won, <code>false</code> otherwise. 
 	    */
 	   
 	    public boolean checkWin() {
		   
    	    if(gameGrid.isVertical(secondPlayer) || 
		    	gameGrid.isHorizontal(secondPlayer)|| gameGrid.isDiagonal(secondPlayer)) {
			 
			  return true; 
		    }
		    else{
			   
		      return false; 
		    }
        }
        
}






