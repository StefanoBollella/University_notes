package myproject.gamestate;

import myproject.grid.GridManager;
import myproject.player.Tokenable;

  /** The interface declares useful methods 
   * for managing the state of a game.
   */
   
public interface GameStateInformation {
  
  /** Returns the current player. 
	*  
	* @return a reference of type <code>Tokenable</code>
	*/
	Tokenable getCurrentPlayer();
	
   /** Returns the second player.
    * 
    * @return a reference of type <code>Tokenable</code> 
    */
	
	Tokenable getSecondPlayer(); 
	
   /** Returns the game grid. 
    *
    * @return a reference of type <code>GameManager</code>.
    */
	GridManager getGameGrid(); 
	
  /**Allows a player to make their move.
   * 
   * @param column
   *        The <code>column</code> chosen by the player.
   */
	void move(int column);
	
	
  /** Checks if the current player has won.
   * 	
   * @return true if the player has won, otherwise false
   */
	boolean checkWin(); 
	
}
