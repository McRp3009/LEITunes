package domain.core;

import java.util.List;

import domain.facade.ISong;
import util.adts.RegExpMatchable;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 * Objects of this type represent songs
 *
 */
public class Song implements ISong, RegExpMatchable {

	private String fileName;
	private Rate rate;
	private SongMetaInfo info;
	private int timesPlayed;

	/**
	 * Song constructor
	 *
	 * @param fileName filename corresponding to the actual song
	 * @param info     information about the song such as title, genre, artists and
	 *                 album
	 */
	public Song(String fileName, SongMetaInfo info) {
		this.fileName = fileName;
		this.info = info;
		this.timesPlayed = 0;
		this.rate = Rate.UNRATED;

	}

	/**
	 * Increments the number of times the song was played
	 */
	@Override
	public void incTimesPlayed() {
		this.timesPlayed++;
	}

	/**
	 * Returns the number of times the song was played
	 *
	 * @return number of times the song was played
	 */
	@Override
	public int getTimesPlayed() {
		return this.timesPlayed;
	}

	/**
	 * Returns the rating of the song
	 *
	 * @return the song's rating
	 * @ensures \result != null
	 */
	@Override
	public Rate getRating() {
		return this.rate;
	}

	/**
	 * Increments the song's rating
	 * @ensures getRating().equals(\old(getRating().inc())
	 */
	@Override
	public void incRating() {
		this.rate = rate.incRate();
	}

	/**
	 * Decrements the song's rating
	 * @ensures getRating().equals(\old(getRating().dec())
	 */
	@Override
	public void decRating() {
		this.rate = rate.decRate();
	}

	/**
	 * Returns the title of the song
	 *
	 * @return the song's title
	 * @ensures \result != null
	 */
	@Override
	public String getSongTitle() {
		return this.info.titulo();
	}

	/**
	 * Returns the genre of the song
	 *
	 * @return the song's genre
	 * @ensures \result != null
	 */
	@Override
	public String getGenre() {
		return this.info.genero();
	}

	/**
	 * Returns the artist list of the song
	 *
	 * @return the song's artists list
	 * @ensures \result != null
	 */
	@Override
	public List<String> getArtists() {
		return this.info.artistas();
	}

	/**
	 * Returns the album name of the song
	 *
	 * @return the song's album name
	 * @ensures \result != null
	 */
	@Override
	public String getAlbum() {
		return this.info.album();
	}

	/**
	 * Return the filename  of the song
	 *
	 * @return the song's filename
	 * @ensures \result != null
	 */
	@Override
	public String getFilename() {
		return this.fileName;
	}

	/**
	 * Checks if any song data matches the given regular expression
	 *
	 * @param regexp the regular expression to be used
	 * @requires regexp != null
	 * @return whether some data of the song matches with the given regexp
	 */
	@Override
	public boolean matches(String regexp) {
		return info.matches(regexp);
	}

	/**
	 * Creates a String representation of the song
	 *
	 * @return the string representation of the song
	 * @ensures \result != null
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(this.info.toString() + " --- " + this.rate.toString() + " --- " + this.timesPlayed);

		return sb.toString();
	}

	/**
	 * returns the SongMetaInfo of the song
	 *
	 * @return the SongMetaInfo of the song
	 * @ensures \result != null
	 */
	public SongMetaInfo getInfo() {
		return this.info;
	}

	/**
	 * Method that checks if an object is equal to this one
	 * Two songs are equal if they are the same object or
	 * if they have the same attributes
	 *
	 * @param other object to check
	 *
	 * @return if other is equal to this
	 * @ensures \return == true || \return == false
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other == null) {
			return false;
		}
		if (other.getClass() != this.getClass()) {
			return false;
		}

		Song s = (Song) other;
		return s.getFilename().equals(this.getFilename()) &&
			   s.getTimesPlayed() == this.getTimesPlayed() &&
			   s.getRating().equals(this.getRating()) &&
			   s.getInfo().equals(this.getInfo());

	}
}
