package domain.facade;

import java.util.List;

import domain.core.Rate;

/**
 * @author antonialopes
 * 
 * Objects of this type represent songs.
 * 
 */
public interface ISong {

	/**
	 * Increments the number of times the song was played
	 */
	void incTimesPlayed();

	/**
	 * Returns the number of times the song was played
	 * 
	 * @return number of times the song was played
	 */
	int getTimesPlayed();

	/**
	 * Returns the rating of the song
	 * 
	 * @return the song's rating
	 * @ensures \result != null
	 */
	Rate getRating();

	/**
	 * Increments the song's rating
	 * @ensures getRating().equals(\old(getRating().inc())
	 */
	void incRating();

	/**
	 * Decrements the song's rating
	 * @ensures getRating().equals(\old(getRating().dec())
	 */
	void decRating();

	/**
	 * Returns the title of the song
	 * 
	 * @return the song's title
	 * @ensures \result != null
	 */
	String getSongTitle();

	/**
	 * Returns the genre of the song
	 * 
	 * @return the song's genre
	 * @ensures \result != null
	 */
	String getGenre();

	/**
	 * Returns the artist list of the song
	 * 
	 * @return the song's artists list
	 * @ensures \result != null
	 */
	List<String> getArtists();

	/**
	 * Returns the album name of the song
	 * 
	 * @return the song's album name
	 * @ensures \result != null
	 */
	String getAlbum();

	/**
	 * Return the filename  of the song
	 * 
	 * @return the song's filename
	 * @ensures \result != null 
	 */
	String getFilename();

	/**
	 * Checks if any song data matches the given regular expression
	 *  
	 * @param regexp the regular expression to be used
	 * @requires regexp != null
	 * @return whether some data of the song matches with the given regexp
	 */
	boolean matches(String regexp);
}