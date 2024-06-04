package myproject.player;

/**
 * The class represents the concept of a single player.
 */
public class Player  implements Tokenable{
 
	/** The value is used to represent the player's name. */
	 private String name; 
	 /** The value is used to represent the color of the player's token. */ 
	 private String color; 
	
	/**
	 * A class constructor that initializes an object,
	 * representing a player with a <code>name</code> and <code>color</code>.
	 *
	 * @param name 
	 *        The player's name
	 *
	 * @param color 
	 *        The player's color
	 */
	 public Player(String name, String color) {
		 this.name = name; 
	     this.color = color; 
	 }
	
	/** 
	  * Returns the player's name.
	  *  
	  * @return  name 
	  *          The player's name
	  */

	 public String getName() {
		return name;
	}

	 /** 
	  * Returns the player's color.
	  *  
	  * @return color 
	  *         The player's color
	  */
	  
	  public String getColor() {
	
			return color;
		}	 
	
}
