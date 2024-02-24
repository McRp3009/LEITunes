package util.adts;

/**
 * @author malopes
 *
 * Represents objects that can be asked whether they match with a regular expression.
 * 
 */

public interface RegExpMatchable {

	/**
	 * @param regexp a regular expression used to check the matches
	 * @requires regexp != null
	 * @return true if this object matches regexp, false otherwise.
	 */
	boolean matches (String regexp);
}
