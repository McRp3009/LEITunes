package domain.playlists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import domain.core.MusicLibrary;
import util.adts.AbsQListWithSelection;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Objects of this type represent lists of Playlists in a certain Music Library
 *
 */
public class PlaylistList extends AbsQListWithSelection<Playlist> {

	private MusicLibrary library;

	/**
	 * PlaylistList constructor
	 * This constructor automatically adds both types of SmartPlaylist
	 * @param library Music Library where the PlaylistList is
	 */
	public PlaylistList(MusicLibrary library) {
		super();
		this.library = library;
		MostLikedSongsPlaylist liked = new MostLikedSongsPlaylist(this.library);
		this.add(liked);
		MostRecentlyAddedSongsPlaylist rated = new MostRecentlyAddedSongsPlaylist(this.library);
		this.add(rated);
	}

	/**
     * Method that creates a list based in arrays
     * 
     * @return a new ArrayList
     * @ensures \result != null && \result.size() == 0
     */
	@Override
	protected List<Playlist> createList() {
		return new ArrayList<>();	
	}
	

	/**
	 * Method that returns the library where the PlaylistList is located
	 * @return library where the PlaylistList is located
	 * @ensures \result != null
	 */
	public MusicLibrary getLibrary() {
		return this.library;
	}

	/**
	 * Method that plays the selected song, if exists, in the selected playlist
	 * stopping the song that the selected playlist is playing, if its playing any
	 * @requires someSelected()
	 * @ensures if (getSelected().someSelected()) then getSelected().getPlaying().equals(getSelected().getSelected) 
	 */
	public void play() {
		if(getSelected().someSelected()) {
			if(isPlaying()) {
				this.stop();
			}
			getSelected().play();

		}
	}

	/**
	 * Determines if there is a song playing in any of its playlists
	 * @return if there is a song playing in the playlists
	 * @ensures \result == true || \result == false
	 */
	public boolean isPlaying() {
		boolean isPlaying = false;

		Iterator<Playlist> it = iterator();

		while (it.hasNext() && !isPlaying) {
			isPlaying = it.next().isPlaying();
		}

		return isPlaying;

	}

	/**
	 * Stops the all songs playing in the playlists, if theres any
	 * 
	 * @requires isPlaying()
	 */
	public void stop() {
		Iterator<Playlist> it = iterator();

		while (it.hasNext() && isPlaying()) {
			Playlist current = it.next();
			if(current.isPlaying()) {
				current.stop();
			}
		}
		
	}

	/**
	 * Adds a playlist to the playlist list
	 * @param p the playlist to add
	 * 
	 * @ensures \old(size())+1 == size() && getSelected().equals(p)
	 */
	@Override
	public void add(Playlist p) {
		super.add(p);
		this.library.registerListener(getSelected());
	}


	/**
	 * Remove the selected playlist if someSelected()
	 */
	@Override
	public void remove() {
		if(someSelected()) {
			this.library.unregisterListener(getSelected());
			super.remove();
		}
	}

	/**
	 * Creates a String representation of the current PlaylistList
	 * @return the string representation of the current playlistList
	 * @ensures \result != null
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Iterator<Playlist> playlists = iterator();
		while(playlists.hasNext()) {
			sb.append(playlists.next().toString());
		}
		return sb.toString();
	}

	/**
	 * Method that checks if a given object is equal to this one
	 * Two PlaylistLists are equal to each other if their librarys
	 * are the same and also have the same playlists
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

		PlaylistList pl = (PlaylistList) other;

		return this.getLibrary().equals(pl.getLibrary()) &&
			   super.equals(other);
	}
}
