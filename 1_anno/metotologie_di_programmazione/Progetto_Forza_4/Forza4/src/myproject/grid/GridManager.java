package myproject.grid;

import myproject.player.Tokenable;

 /** The interface declares methods for changing the state of a grid 
  * and methods that query it for its current state.
  */

public interface GridManager{

	/** Returns a reference to the game grid. 
	 *  
	 * @return reference to the game grid.
	 */
	
	 Tokenable[][] getGrid(); 

	 
	/** Check that the entire grid is full.
	 * 
	 * @return <code>true</code> if the grid is full, <code>false</code> otherwise
	 */
	 
	 boolean checkFull();
    
    
	 /** Checks if a column is full.
      * 
      * @param column
      *        The <code>column</code> to be checked.
      *        
      * @return <code>true</code> if the column is full, <code>false</code> otherwise
	  */
    
	  boolean checkFull(int column);
    
	  
	 /** Adds a token to a grid column.
      * 
      * @param token
      *        The <code>token</code> to be added.
      *        
      * @param column
      *       The selected <code>column</code>.
      */
	 
	  void addToken(Tokenable token, int column);

     
	  
	  /** Checks if four tokens of the same colour are aligned diagonally.
	   * 
	   * @param token
	   *        The type of the <code>token</code> to be researched.
	   *        
	   * @return <code>true</code> if the tokens are aligned <code>false</code> otherwise.
	   */
	  
	  boolean isDiagonal(Tokenable token);
	
      
	  /** Check if four tokens of the same colour are lined up vertical.
       * 
       * @param token
       *      The type of the <code>token</code> to be researched.
       *      
       * @return <code>true</code> if the tokens are aligned <code>false</code> otherwise.
       */
	  boolean isVertical(Tokenable token); 
	  
	  
	  /** Check if four pawns of the same colour are aligned horizontally.
	   * 
	   * @param token
	   *        The type of the <code>token</code> to be researched.
	   *        
	   * @return <code>true</code> if the tokens are aligned <code>false</code> otherwise.
	   */
     
	  boolean isHorizontal(Tokenable token); 
    
}
