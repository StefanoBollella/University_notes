package myproject.grid;

/** Signals  an attempt to fill a column that is already full.
  */


public class FullColumnException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a FullColumnException with
     *    
	 * @param message
	 *         As an error message. 
	 */
	
	public FullColumnException(String message) {
		 
		 super(message); 
		 
	 }
}
