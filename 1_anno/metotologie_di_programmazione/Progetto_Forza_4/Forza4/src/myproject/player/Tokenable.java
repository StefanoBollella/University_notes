package myproject.player;

  /** An object of a type of a class that implements such an interface,
   *  becomes within the logic of the game system a token.
   */

public interface Tokenable {

	   
       /** Restores the name of the Palyer.
	    * 
	    * @return The color of the token. 
	    */
        String getName(); 
        
        /** Restores the color of the token.
 	    * 
 	    * @return The color of the token. 
 	    */
        String getColor(); 
}
