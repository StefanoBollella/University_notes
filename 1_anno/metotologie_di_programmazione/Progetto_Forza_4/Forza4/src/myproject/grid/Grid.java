package myproject.grid;

import myproject.player.Tokenable;

/** The class represents the concept of a game grid. 
 *  Includes methods that manipulate the game grid of size 6 X 7, can add tokens
 *  to the grid or check the alignment of the four tokens to get the win,
 *  the game tokens in this case are represented by
 *  references to objects of class types that implement the <code>Tokenable</code> interface.
 */

public class Grid implements GridManager{
  
    public final int FOUR = 4; 
  
   /** The value is used for storing the position of the tokens. */
   private  Tokenable[][] tokenGrid; 

    
   /** Initializes a newly created <code>Grid</code> object.
    * 	
    * @param tokenGrid 
    *        A two-dimensional array of the <code>Tokenable</code> interface type.    
    */
   
    public Grid(Tokenable[][] tokenGrid) {
		
		this.tokenGrid = tokenGrid;
	}

	
	/** Returns the current state of the <code>tokenGrid</code>
	 * 
	 * @return grid
	 *        A two-dimensional Array of the <code>Tokenable</code>
	 *        Interface type that stores the position of the tokens
	 */
	
	 public Tokenable[][] getGrid(){
		
		return this.tokenGrid; 
	}
	
	 
	/** Check if the game <code>tokenGrid</code> is full.
	 *  
	 * @return <code>true</code> if the <code>tokenGrid</code> is full, <code>false</code> otherwise 
	 */
	
     public boolean checkFull() {  
	
	    boolean full = true; 
	  
	    for(int i = 0; i < tokenGrid.length; i++) {
		 
		   for(int j = 0; j < tokenGrid[0].length; j++) {
			  
			  if(this.tokenGrid[i][j] == null)
				  full = false; 
			}
		 }
    return full; 
    }


   /** Check that the selected <code>column</code> of the <code>tokenGrid</code> is not full. 
    * 
    *  @param  column
    *          The <code>column</code> selected by the current player
    * 
    *  @return <code>true</code> if the column is full, <code>false</code> otherwise
    */
     
    public boolean checkFull(int column) {
		
		boolean full = true; 
		
		for(int i = 0; i < this.tokenGrid.length; i++) {
			
			if(this.tokenGrid[i][column] == null) {
				
				full = false; 
			}
		}	
		return full;  
	 }
  
  
	/** Adds a token to one of the columns of the game <code>tokenGrid</code>. 
	 * 
	 *  @param token 
	 *         The piece to add to the chosen <code>column</code>.
	 *   
	 *  @param column 
	 *         The <code>column</code> chosen by the <code>currentPlayer</code> where to drop their token
	 *  
	 *  @throws FullColumnException 
	 *          If the <code>currentPlayer</code> attempts to insert a token into a filled <code>column</code>
	 */

     public void addToken(Tokenable token, int column) {
	
	    if(!this.checkFull(column)) { 
		 
		   for( int i = tokenGrid.length - 1 ; i >= 0 ; i -- ) {
		 
		       if(this.tokenGrid[i][column] == null ) {
			  
			      this.tokenGrid[i][column] = token;  
	           
			      return; 
		       }
	        }
	     }
         else {
	     
    	    throw new FullColumnException("the selected column is full"); 
        }
     }

    /** Search inside the game <code>tokenGrid</code> for four tokens aligned diagonally.
     * A search is made in both directions, line by line 
     * from bottom to top and considering only a specific part of the game grid,
     * where the alignment of 4 pieces can occur
     *  
     * 
     * @param token
     *        The type of the <code>token</code> to be researched
     * 
     * @return <code>true</code> if the four aligned tokens are found, <code>false</code> otherwise 
     */
    
     public boolean isDiagonal(Tokenable token) {
     	
       /* Search for the alignment of tokens in one direction. */
         for(int i = tokenGrid.length -1; i > tokenGrid.length - FOUR; i--) {
            for(int j = tokenGrid[0].length -1; j >=  tokenGrid[0].length - FOUR; j-- ) {
    			
    			int w = i; 
    			int q = j; 
    			int count = 0; 
    			
    			/* From here begins the check on the diagonal of the selected element.*/
    			
    			while( w >= 0 && q >= 0) {
    			
    			     if(tokenGrid[w][q] != null && tokenGrid[w][q].getColor().equals(token.getColor())){
    				  
    			    	count++; 
    				    w--;
    				    q--; 
    			   }
    			   else break;
    	        }
    	        if(count >= FOUR) {

    	        	return true;
    	        } 
    	     }
    	}
       /* Search for the alignment of the tokens on the diagonals that are
        * in the opposite direction to those of the first search 
        */ 
     	for(int i= tokenGrid.length -1; i > tokenGrid.length - FOUR; i--) {
    	   for(int j= tokenGrid[0].length - FOUR; j >= 0; j--) {
    		
    			int w = i; 
    			int q = j; 
    			int count =0; 
    			
    			/* From here begins the check on the diagonal of the selected element.*/
    			while( w >= 0 && q < tokenGrid[0].length ) {
    				
    				  if(tokenGrid[w][q] != null && tokenGrid[w][q].getColor().equals(token.getColor())){
    					  
    					  count++; 
    					  w--;
    					  q++; 
    				  }
    				  else break; 
    			  }
    			 if(count >= FOUR) {
    				 return true; 
    			 }
              }
            }
      return false;
    }	
    	
    /** Search inside the game <code>tokenGrid</code> for four tokens aligned on vertically.
     * The search for the pieces aligned is made column by column, starting from the bottom.
     * 
     * @param token
     *        The type of the <code>token</code> to be researched.
     * 
     * @return <code>true</code> if the four aligned tokens are found, <code>false</code> otherwise	
     */
     
     public boolean isVertical(Tokenable token) {
	   
	     int j = 0; 
	   
	     while(j < tokenGrid[0].length){
		   
		      for(int i = tokenGrid.length -1 ; i > tokenGrid.length - FOUR; i--) {
		    	 
		    	 int w = i; 
		    	 int count = 0; 
		    
		    	 while(w >= 0) {
		    	 
		               if(this.tokenGrid[w][j] != null && tokenGrid[w][j].getColor().equals(token.getColor())) {
		    	          
		            	   count++; 
		    	           w--; 
		                }
		                else {
		            	 
		            	  break; 	
		                }
		          }
		     
		         if(count >= FOUR) {  
		 
		    		 return true;  
		    	  }
		      }   
	       j++; 
	     }
	   return false; 
     }
    
   
    /** Search inside the game <code>grid</code> for four tokens aligned on horizontal.
     * 
     * @param  token
     *         The type of the <code>token</code> to be researched.
     * 
     * @return <code>true</code> if the four aligned tokens are found, <code>false</code> otherwise	
     */
    
     public  boolean isHorizontal(Tokenable token) {

	 /* Search on the left side of the game grid. */
	   
	     for(int i = tokenGrid.length - 1; i >= 0; i--) {
		   
		    for(int j = 0; j <= tokenGrid[0].length - FOUR; j++) {
			   
			   int q = j; 
			   int count = 0; 
			   
			    while( q < tokenGrid[0].length) {
				  
				     if(this.tokenGrid[i][q] != null &&  tokenGrid[i][q].getColor().equals(token.getColor())) {
				       
				       count++; 
				       q++; 
				     }
				     else {
				    	 
				    	 break; 
				     }
			   }   
			   if(count >= FOUR) {

			       return true; 
			   }
		    }
         }
	 
   /* Search on the right side of the game grid. */
	   
	     for(int i = tokenGrid.length - 1; i >= 0; i--) {
		   
		    for(int j = tokenGrid[0].length - 1; j >= tokenGrid[0].length - FOUR; j--) {
	   
	            int q = j; 
	            int count=0; 
	          
	              while(q >= 0) {
	        	 
	        	     if(this.tokenGrid[i][q] != null &&  tokenGrid[i][q].getColor().equals(token.getColor())) {
					   
				         count++; 
				         q--; 
				     }
				     else {
				    	
				    	 break; 
				     }
	              }   
			     if(count >= FOUR) {
		
			       return true; 
			     }
		     }
          } 
	    return false; 
     }
    
}
