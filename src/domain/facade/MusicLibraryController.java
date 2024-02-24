package domain.facade;

import java.util.Optional;


import domain.core.MusicLibrary;
import domain.core.Song;
import servicos.SongCreator;


/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Objects of this type represent controllers for Music Librarys
 *
 */
public class MusicLibraryController {

	private MusicLibrary library;

	/**
	 * MusicLibraryController constructor
	 * @param library library to control
	 * 
	 * @requires library != null
	 */
	public MusicLibraryController(MusicLibrary library) {
		this.library = library;
	}

	/**
	 * Method that returns the amount of songs in the library
	 * @return the number of songs the library has
	 * @ensures \result >= 0
	 */
	public int numberOfSongs() {
		return this.library.size();

	}

	/**
	 * Method that returns the library
	 * @return the library being controlled
	 * @ensures \result != null
	 */
	public MusicLibrary getLibrary(){
		return this.library;
	}

	/**
	 * Creates and adds a new song to the library if possible
	 * @param filename the name of the mp3 file of the song
	 */
	public void addSong(String filename) {

		SongCreator creator = new SongCreator();

		Song toAdd = creator.create(filename);

		if(toAdd != null) {
			this.library.add(toAdd);
		}

	}

	/**
	 * selects the songs corresponding to the given index
	 * @param i the index of the wanted song
	 */
	public void selectSong(int i) {
		if (0 <= i && i < numberOfSongs()) {
			this.library.select(i);
		}
	}

	/**
	 * Method that returns the selected song from the library, if possible
	 * 
	 * @return the selected song in the library if possible, otherwise returns Optional.empty()
	 * @ensures this.library.someSelected() ==> \result == Optional.of(this.library.getSelected())
	 * @ensures !this.library.someSelected() == > \result == Optional.empty()
	 */
	public Optional<ISong> getSelectedSong() {
		if(this.library.someSelected()) {
			return Optional.of(this.library.getSelected());			
		}
		return Optional.empty();

	}

	/**
	 * Removes the selected song if possible
	 * 
	 * @ensures !this.library.someSelected()
	 */
	public void removeSelectedSong() {
		this.library.remove();
	}

	/**
	 * Plays the selected song if possible
	 * 
	 * @ensures this.library.isPlaying()
	 */
	public void play() {
		if(this.library.someSelected()) {
			this.library.play();
		}

	}

	/**
	 * Stops all playing songs
	 * 
	 * @ensures !this.library.isPlaying()
	 */
	public void stop() {
		if(this.library.isPlaying()) {
			this.library.stop();
		}

	}

	/**
	 * Increases the rate of the selected song to the next rating, if possible
	 * 
	 * @ensures if (this.library.someSelected())
	 * 			then this.library.getSelected().getRating().compareTo(\old(this.library.getSelected().getRating())) >= 0
	 */
	public void incRateSelected() {
		this.library.incRateSelected();
	}

	/**
	 * Increases the rate of the selected song to the previous rating, if possible
	 * 
	 * @ensures if (this.library.someSelected())
	 * 			then this.library.getSelected().getRating().compareTo(\old(this.library.getSelected().getRating())) <= 0
	 */
	public void decRateSelected() {
        this.library.decRateSelected();
	}

	/**
	 * Method that returns an iterable with all the songs from the library that match
	 * the given expression
	 * @param reexp given expression to match
	 * @return iterable structure with library songs that match reexp
	 * @ensures \result != null
	 */
	public Iterable<ISong> getMatches(String reexp) {
		return this.library.getMatches(reexp);

	}

	/**
	 * Method that returns an iterable with all the songs from the library
	 * @return iterable structure with all library songs
	 * @ensures \result != null
	 */
	public Iterable<ISong> getSongs() {
		return this.library.getSongs();

	}

	/**
	 * Creates a String Representation of a MusicLibraryController
	 * @return the string representation of MusicLibraryController
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("*****MUSIC LIBRARY****");
		sb.append(this.library.toString());

		return sb.toString();
	}

	/**
	 * Method that checks if a given object is equal to this one
	 * Two MusicLIbraryControllers are equal if their libraries are
	 * equal as well
	 *
	 * @param other object to check
	 * @return if other is equal to this
	 * @ensures \result == true || \result == false
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

		MusicLibraryController mlc = (MusicLibraryController) other;

		return this.getLibrary().equals(mlc.getLibrary());

	}

}
