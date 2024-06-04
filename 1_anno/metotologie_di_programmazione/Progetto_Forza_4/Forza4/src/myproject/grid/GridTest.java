package myproject.grid;

import myproject.player.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GridTest {
    
	
	   private Tokenable player1;
	   private Tokenable player2;
	   private  Grid gameGrid; 

	
	   public GridTest() {
		  
		}
     
	  @Before
	  public void setUp() {
		  
    	  player1 = new Player("p1","red");
          player2 = new Player("p2","blue");
    	  gameGrid= new Grid(new Player[6][7]);
    
	   }
	  
      
     @Test
     public void testCheckisFull() {
    	  
       /* Test the CheckFull method without parameter list,
        * verifying that the entire gameGrid1 is empty 
        */
    	
        assertFalse(gameGrid.checkFull()); 
      
       /* Test the CheckFull method with the parameter list,
        * check if the selected column is not full 
        */
    	  
        assertFalse(gameGrid.checkFull(0));
     
        /*Test the CheckFull method without a list of parameters,
          checks that the entire game grid is full */
     
        //fills the entire game grid
       Tokenable[][] grid = gameGrid.getGrid();
       for(int i= 0; i< 6; i++) {
    	   for(int j= 0; j< 7; j++) {
    		    grid[i][j] = player1; 
    	   }
       }
       assertTrue(gameGrid.checkFull());
     
     
     }
      
      
	  
	 @Test
	 public void testAddToken() {
		  
		  /* Adds a token to a column in the grid to test that it works correctly.*/
		 
		  gameGrid.addToken(player1, 0);
		  Tokenable[][] grid = gameGrid.getGrid(); 
		  
		  assertEquals(player1,grid[5][0]); 
 
		 /* Fills an entire column with tokens to test if the FullColumnException is thrown. */
		  gameGrid.addToken(player1, 0);
		  gameGrid.addToken(player1, 0);
		  gameGrid.addToken(player1, 0);
		  gameGrid.addToken(player1, 0);
		  gameGrid.addToken(player1, 0);
		  
		  try {
			   gameGrid.addToken(player1, 0); 
			   fail("should throw the FullColumnException");
		   }
		  catch(FullColumnException e){
		  }
	 }
	  
	 
	  @Test
	  public void testIsDiagonal() {
	   
	   /* Test if four player1 tokens (player1) are aligned on a diagonal parallel to 
		* the secondary diagonal. 
		*/
		  
		  gameGrid.addToken(player1, 0);
		  gameGrid.addToken(player2, 1); 
		  gameGrid.addToken(player2, 2);
		  gameGrid.addToken(player2, 3);
		  
		  gameGrid.addToken(player1, 1);
		  gameGrid.addToken(player2, 2); 
		  gameGrid.addToken(player2, 3);
		  
		  gameGrid.addToken(player1, 2);
		  gameGrid.addToken(player2, 3); 
		  
		  gameGrid.addToken(player1, 3);
		  
		  assertTrue(gameGrid.isDiagonal(player1));
		 

		 /* Test if four tokens (player1) are aligned on a diagonal parallel to 
		  * the main diagonal. 
		  */
		  
		  gameGrid.addToken(player1, 6);
		  gameGrid.addToken(player2, 5); 
		  gameGrid.addToken(player2, 4);
		  
		  gameGrid.addToken(player1, 5); 
		  gameGrid.addToken(player2, 4);
		  
		  gameGrid.addToken(player1, 4);
		  
		  assertTrue(gameGrid.isDiagonal(player1));
      }
	  
	  
	   @Test
	   public void testIsVertical() {
		   
		   gameGrid.addToken(player1, 0);
		   gameGrid.addToken(player1, 0);
		   gameGrid.addToken(player1, 0);
		   gameGrid.addToken(player1, 0);  
		   
		   assertTrue(gameGrid.isVertical(player1));
	  }
	  
	  
	   @Test
	   public void testIsHorizontal() {
		   
		   gameGrid.addToken(player1, 0);
		   gameGrid.addToken(player1, 1);
		   gameGrid.addToken(player1, 2);
		   gameGrid.addToken(player1, 3);  
	  
		   assertTrue(gameGrid.isHorizontal(player1));
	  
	   }
	  
	  
	  @After
	  public void tearDown() {
		  gameGrid =null; 
	  }
	  
	  
}
