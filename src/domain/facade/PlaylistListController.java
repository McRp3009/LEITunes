package domain.facade;

import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.playlists.ManualPlaylist;
import domain.playlists.Playlist;
import domain.playlists.PlaylistList;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Objects of this type represent a controller for a PlaylistList
 */
public class PlaylistListController {

	private MusicLibrary library;
	private PlaylistList playlists;

	/**
	 * PlaylistListController constructor
	 * @param playlists  the PlaylistList to control
	 * @param library    the MusicLibrary where the PlaylistList is located
	 * 
	 * @requires playlists != null && library != null
	 */
	public PlaylistListController(PlaylistList playlists, MusicLibrary library) {
		this.library = library;
		this.playlists = playlists;
	}

	/**
	 * Method that returns the library where the PlaylistList is located
	 * @return the MusicLibrary
	 * @ensures \result != null
	 */
	public MusicLibrary getLibrary(){
		return this.library;
	}

	/**
	 * Method that returns the PlaylistList that is being controled
	 * @return the PlaylistList
	 * @ensures \result != null
	 */
	public PlaylistList getPlaylistList(){
		return this.playlists;
	}

	/**
	 * Creates a new manual playlist and adds it to the PlaylistList
	 * @param name the manual playlist's name to add
	 * 
	 * @requires name != null
	 * @ensures \old(this.playlists.size()) + 1 == this.playlists.size()
	 */
	public void createPlaylist(String name) {
		this.playlists.add(new ManualPlaylist(name, this.library));
	}

	/**
	 * Selects a Playlist corresponding to a certain index, if possible
	 * @param i the index of the desired playlist to select
	 * 
	 * @requires i != null
	 */
	public void selectPlaylist(int i) {
		if (0 <= i && i < this.playlists.size()) {
			this.playlists.select(i);
		}
	}

	/**
	 * Verifies if there is a playlist selected
	 * @return true if there is a playlist selected and false otherwise
	 * @ensures \result == true !! \result == false
	 */
	public boolean somePlaylistSelected() {
		return this.playlists.someSelected();
	}

	/**
	 * Method that returns the selected playlist from the PlaylistList
	 * @requires somePlaylistSelected()
	 * @return the selected playlist
	 * @ensures \result != null
	 */
	public Playlist getSelectedPlaylist() {
		return this.playlists.getSelected();
	}

	/**
	 * Removes the selected playlist
	 */
	public void removePlaylist() {
		if(somePlaylistSelected()) {
			this.playlists.remove();
		}
	}

	/**
	 * @return a playlist iterator
	 */
	public Iterator<Playlist> iterator() {
		return this.playlists.iterator();
	}

	/**
	 * @return the number of songs in the selected playlist
	 * @requires somePlaylistSelected()
	 */
	public int numberOfSongs() {
		return getSelectedPlaylist().size();
	}

	/**
	 * adds the selected song to the selected playlist
	 * @requires somePlaylistListSelected()
	 */
	public void addSong() {
		if (this.library.someSelected()) {
			ISong song = this.library.getSelected();
			getSelectedPlaylist().add(song);
		}

	}

	/**
	 * Selects a song of the selected playlist given a certain index, if possible
	 * @param i the index of the wanted song
	 * @requires somePlaylistListSelected()
	 */
	public void selectSong(int i) {
        if (0 <= i && i < numberOfSongs()) {
			getSelectedPlaylist().select(i);
        }
	}

	/**
	 * Determines if there is a selected song in a selected playlist
	 * @return true if there is a playlist selected and a selcted song in it
	 * 		   false otherwise
	 * @ensures \result == true !! \result == false
	 */
	public boolean someSongSelected() {
		return somePlaylistSelected() && getSelectedPlaylist().someSelected();
	}

	/**
	 * Removes the selected song
	 * @requires someSongSelected()
	 */
	public void removeSelectedSong() {
        getSelectedPlaylist().remove();
	}

	/**
	 * Selects the song after the one that is currently selected
	 * @requires somePlaylistSelected()
	 */
	public void nextSong() {
		if(getSelectedPlaylist().someSelected()) {
			getSelectedPlaylist().next();
		}
	}

	/**
	 * Selects the song before the one that is currently selected
	 * @requires somePlaylistSelected()
	 */
	public void previousSong() {
		if(getSelectedPlaylist().someSelected()) {
			getSelectedPlaylist().previous();
		}
	}

	/**
	 * Plays the selected song in the selected playlist if someSongSelected()
	 */
	public void play() {
		if(someSongSelected()) {
			this.playlists.play();
		}
	}

	/**
	 * Stops all the songs playing
	 */
	public void stop() {
		this.playlists.stop();
	}

	/**
	 * Creates a String representation of a PlaylistListController
	 * @return the string representation of a PlaylistListController
	 * @ensures \result != null
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("***** PLAYLISTS *****");
		sb.append(this.playlists.toString());

		return sb.toString();
	}

	/**
	 * Method that checks if a given object is equal to this one
	 * Two PlaylistListControllers are equal if their librarys are
	 * equal and their PlaylistLists are equal
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

		PlaylistListController plc = (PlaylistListController) other;

		return this.getLibrary().equals(plc.getLibrary()) &&
		       this.getPlaylistList().equals(plc.getPlaylistList());

	}
}
