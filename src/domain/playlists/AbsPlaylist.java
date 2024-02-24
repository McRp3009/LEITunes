package domain.playlists;

import java.beans.PropertyChangeEvent;
import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.adts.ArrayQListWithSelection;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Objects of this type represent Playlists in a certain Music Library
 */
public abstract class AbsPlaylist implements Playlist {

	private String name;
	private ArrayQListWithSelection<ISong> songs;
	private ISong playing;
	private MusicLibrary library;
	private Player player;

	/**
	 * Constructor of an AbsPlaylist
	 * 
	 * @param name name of the playlist
	 * @param lib  Music Library where the playlist is located
	 */
	protected AbsPlaylist(String name, MusicLibrary lib) {
		this.songs = new ArrayQListWithSelection<>();
		this.name = name;
		this.playing = null;
		this.library = lib;
		this.player = PlayerFactory.INSTANCE.getPlayer();
		this.player.addListener(this);
	}

	/**
	 * Method that gets the music Library where the playlist is
	 *
	 * @return the music library of the playlist
	 * @ensures \result != null
	 */
	public MusicLibrary getLibrary() {
		return this.library;
	}

	/**
	 * Method that gets te current playing song
	 */
	public ISong getPlaying() {
		return this.playing;
	}

	/**
	 * Method that gets the songs of the playlist
	 *
	 * @return songs in the playlist
	 * @ensures \result != null
	 */
	public ArrayQListWithSelection<ISong> getSongs() {
		return this.songs;
	}

	/**
	 * Returns the number of songs in the playlist
	 *
	 * @return the number of songs in the playlist
	 * @ensures \return >= 0
	 */
	@Override
	public int size() {
		return this.songs.size();
	}

	/**
	 * Returns the selected song
	 *
	 * @requires someSelected()
	 * @return the selected song
	 * @ensures \return != null
	 */
	@Override
	public ISong getSelected() {
		return this.songs.getSelected();
	}

	/**
	 * Returns true if some element is selected
	 *
	 * @return true if some element is selected, false otherwise
	 * @ensures \result == true || \result == false
	 */
	@Override
	public boolean someSelected() {
		return this.songs.someSelected();
	}

	/**
	 * Adds a song to the end of the playlist, if it
	 * does not exist yet and selects it,
	 * if addition is possible
	 *
	 * @param song the element to be added
	 * @requires song != null
	 * @return true if the song was added to the playlist, false otherwise
	 * @ensures \result ==> size() == \old(size()) + 1 &&
	 *          someSelected() &&
	 *          getIndexSelected() == size() - 1
	 */
	@Override
	public boolean add(ISong song) {
		if (contains(song)) {
			return false;
		}
		this.songs.add(song);
		return true;
	}

	/**
	 * Method that checks ifthe playlist already contains a certainf song
	 *
	 * @param song song to check if already exists
	 * @return true if the song already exists in the playlist, false otherwise
	 * @ensures \result == true || \result == false
	 */
	protected boolean contains(ISong song) {
		for (ISong iSong : this.songs) {
			if (iSong.equals(song)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the selected element from the playlist, if possible
	 *
	 * @return true if the song was removed, false otherwise
	 * @ensures \return && \old someSelected() ==>
	 *          !someSelected() && size() == \old(size()) - 1
	 * @ensures !\return ==> \old someSelected() == someSelected()
	 *          && size() == \old(size())
	 */
	@Override
	public boolean remove() {
		if (someSelected()) {
			this.songs.remove();
			return true;
		}
		return false;

	}

	/**
	 * Selects song at position i
	 *
	 * @param i the position denoting the element to be selected
	 * @requires 0 <= i < size()
	 * @ensures someSelected() && getIndexSelected() == i &&
	 *          size() == \old(size())
	 */
	@Override
	public void select(int i) {
		this.songs.select(i);
	}

	/**
	 * Moves the current selected song up to position i,
	 * shifting down all elements in the playlist from
	 * positions i+1 to \old getIndexSelected()-1,
	 * if movement in the playlist is possible
	 *
	 * @param i the index where this element is going to be moved
	 * @requires someSelected() && 0 <= i < getIndexSelected()
	 * @ensures \return ==> someSelected() &&
	 *          getIndexSelected() == i &&
	 *          size() == \old(size())
	 */
	@Override
	public boolean moveUpSelected(int i) {
		ArrayQListWithSelection<ISong> novo = new ArrayQListWithSelection<>();
		int indexSelected = getIndexSelected();
		boolean done = false;
		int counter = 0;
		while (counter < size()) {
			if (counter == i && !done) {
				novo.add(this.songs.get(indexSelected));
				done = true;
			} else {
				if (counter != indexSelected) {
					novo.add(this.songs.get(counter));
				}
				counter++;
			}
		}
		this.songs = novo;
		return true;
	}

	/**
	 * Returns the index of the selected element, if any
	 *
	 * @return the index of the selected element, if any
	 * @requires someSelected()
	 * @ensures 0 <= \return < size()
	 */
	@Override
	public int getIndexSelected() {
		return this.songs.getIndexSelected();
	}

	/**
	 * Selects the next element, if any. Otherwise, no element is selected.
	 *
	 * @requires someSelected()
	 * @ensures if \old getIndexSelected() < size() - 1
	 *          then getIndexSelected() = \old getIndexSelected() + 1
	 *          else !someSelected()
	 * @ensures size() == \old(size())
	 */
	@Override
	public void next() {
		this.songs.next();
	}

	/**
	 * Selects the previous element, if any. Otherwise, no element is selected.
	 *
	 * @requires someSelected()
	 * @ensures if \old getIndexSelected() > 0
	 *          then getIndexSelected() = \old getIndexSelected() - 1
	 *          else !someSelected()
	 * @ensures size() == \old(size())
	 */
	@Override
	public void previous() {
		this.songs.previous();
	}

	/**
	 * Returns the name of the playlist
	 *
	 * @return the name of the playlist
	 * @ensures \result != null
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/**
	 * Returns if a song is playing and the play action has been performed via the
	 * playlist
	 *
	 * @return true if a song is playing and the play action was done through the
	 *         playlist,
	 *         false otherwise
	 */
	@Override
	public boolean isPlaying() {
		return this.playing != null;
	}

	/**
	 * Plays the selected song
	 *
	 * @requires someSelected()
	 * @ensures isPlaying()
	 */
	@Override
	public void play() {
		if (isPlaying()) {
			this.stop();
		}
		this.player.load(getSelected().getFilename());
		this.playing = getSelected();
		this.player.play();
	}

	/**
	 * Stops the playing song
	 *
	 * @requires isPlaying()
	 */
	@Override
	public void stop() {
		this.playing = null; // needed?
		this.player.stop();
	}

	/**
	 * Reaction to property change events, namely those emitted by the player
	 * (can affect the selected song and song being played)
	 *
	 * @param evt player event
	 *
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (isPlaying()) {
			if (evt.getNewValue().equals(Player.PlayingState.ENDED)) {
				this.playing.incTimesPlayed();
				if (someSelected() && getSelected().equals(playing)) {
					next();
				}
				if (someSelected()) {
					play();
				} else {
					this.playing = null;
				}
			} else if (evt.getNewValue().equals(Player.PlayingState.STOPED)) {
				this.playing = null;
			}
		}
	}

	/**
	 * Processes a SongLibraryEvent
	 *
	 * @param e the SongLibraryEvent given
	 * @ensures if (e instanceof SongRemovedLibraryEvent) then !\this.contains(e.getSong)
	 */
	@Override
	public void processEvent(SongLibraryEvent e) {
		if (e instanceof SongRemovedLibraryEvent) {
			int counter = 0;
			ISong selected = getSelected();
			Iterator<ISong> it = iterator();
			while (it.hasNext()) {
				ISong song = it.next();
				if (song.equals(e.getSong())) {
					select(counter);
					remove();
					break;
				}
				counter++;
			}

			for (int i = 0; i < size(); i++) {
				if (selected.equals(this.songs.get(i))) {
					select(i);
				}

			}
		}
	}

	/**
	 * Method that returns an iterable structure with all the elements from the
	 * playlist
	 *
	 * @return iterable structure with all elements from the playlist
	 * @ensures \result != null
	 */
	@Override
	public Iterator<ISong> iterator() {
		return this.songs.iterator();
	}

	/**
	 * Creates a String representation of the current AbsPlaylist
	 *
	 * @return String representing the current AbsPlaylist
	 * @ensures \result != null
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n*-- Playlist " + name + "--*");
		sb.append(this.songs.toString());
		return sb.toString();
	}

	/**
	 * Method that checks if a given object is equal to this one
	 * Two AbsPlaylist are equal if they are in the same MusicLibrary,
	 * have the same songs and the same name
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

		AbsPlaylist p = (AbsPlaylist) other;

		return getName().equals(p.getName()) &&
				getLibrary().equals(p.getLibrary()) &&
				getSongs().equals(p.getSongs());
	}
}
