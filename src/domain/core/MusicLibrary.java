package domain.core;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import domain.facade.ISong;
import domain.player.Player;
import domain.player.PlayerFactory;
import util.adts.AbsQListWithSelection;
import util.adts.ArrayQListWithSelection;
import util.observer.Listener;
import util.observer.Subject;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 * Objects of this type represent a Music Library
 */
public class MusicLibrary extends AbsQListWithSelection<Song>
		implements Subject<SongLibraryEvent>, PropertyChangeListener {

	private List<Listener<SongLibraryEvent>> listeners;
	private Song playing;
	private Player player;

	/**
	 * Music Library constructor
	 */
	public MusicLibrary() {
		super();
		this.listeners = new ArrayList<>();
		this.player = PlayerFactory.INSTANCE.getPlayer();
		player.addListener(this);
	}

	/**
     * Method that creates a list based in arrays
     * 
     * @return a new ArrayList
     * @ensures \result != null && \result.size() == 0
     */
	@Override
	protected List<Song> createList() {
		return new ArrayList<>();
	}

	/**
	 * Method that gets the player of this library
	 *
	 * @return the player of the library
	 */
	public Player getPlayer() {
		return this.player;
	}

	/**
	 * Method that gets the current playing song
	 *
	 * @return the song currently playing
	 */
	public Song getPlaying() {
		return this.playing;
	}

	/**
	 * Method that gets the list of listeners from the library
	 *
	 * @return the list of listener
	 */
	public List<Listener<SongLibraryEvent>> getListeners() {
		return this.listeners;
	}

	/**
	 * Method that reacts to the events happening in the player
	 * (if a current playing song has ended or if the song was stopped),
	 * only if there is a song playing in the library
	 *
	 * @param evt the player related event
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (isPlaying()) {
			if (evt.getNewValue().equals(Player.PlayingState.ENDED)) {
				this.playing.incTimesPlayed();
				if (getSelected().equals(playing)) {
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
	 * Interrupts the current song and starts playing
	 * the selected song in the library
	 *
	 * @requires someSelected()
	 */
	public void play() {
		if (isPlaying()) {
			this.stop();
		}
		this.player.load(getSelected().getFilename());
		this.playing = getSelected();
		this.player.play();
	}

	/**
	 * Determines if there is a song currently playing
	 * in the library
	 *
	 * @return true if there is a song currently playing
	 *         in the library, otherwise false
	 */
	public boolean isPlaying() {
		return this.playing != null;
	}

	/**
	 * Stops the music that is currently playing
	 * in the library
	 *
	 * @requires isPlaying()
	 */
	public void stop() {
		this.player.stop();

	}

	/**
	 * Emits the given library related event
	 *
	 * @param e the event given
	 */
	@Override
	public void emitEvent(SongLibraryEvent e) {
		for (Listener<SongLibraryEvent> l : listeners) {
			l.processEvent(e);
		}
	}

	/**
	 * Registers the given Listener as a listener for
	 * events that happen in this library
	 *
	 * @param obs the Listener given
	 * @ensures this.listeners.contains(obs)
	 */
	@Override
	public void registerListener(Listener<SongLibraryEvent> obs) {
		if (this.listeners.contains(obs)) {
			return;
		}
		this.listeners.add(obs);
	}

	/**
	 * Unregistes the given Listener
	 *
	 * @param obs the Listener given
	 * @ensures !this.listeners.contains(obs)
	 */
	@Override
	public void unregisterListener(Listener<SongLibraryEvent> obs) {
		this.listeners.remove(obs);
	}

	/**
	 * Adds the given song to the list and
	 * emits a SongAddedLibraryEvent
	 *
	 * @param e the song to add
	 * @ensures getSelected() == e
	 */
	@Override
	public void add(Song e) {
		super.add(e);
		emitEvent(new SongAddedLibraryEvent(e, this));
	}

	/**
	 * Removes the selected song from the list, if possible
	 *
	 * @ensures !someSelected()
	 */
	@Override
	public void remove() {
		if (someSelected()) {
			emitEvent(new SongRemovedLibraryEvent(getSelected(), this));
			super.remove();
		}
	}

	/**
	 * Increases the selected song's rating to the next higher rating based on the
	 * current rating, emitting a SongRatedLibraryEvent if the rate was changed
	 */
	public void incRateSelected() {
		if (someSelected()) {
			Rate before = getSelected().getRating();
			getSelected().incRating();
			emitEvent(new SongRatedLibraryEvent(getSelected(), this, before, getSelected().getRating()));
		}
	}

	/**
	 * Decreses the selected song's rating to the precious lower rating based on the
	 * current rating, emitting a SongRatedLibraryEvent if the rate was changed
	 */
	public void decRateSelected() {
		if (someSelected()) {
			Rate before = getSelected().getRating();
			getSelected().decRating();
			emitEvent(new SongRatedLibraryEvent(getSelected(), this, before, getSelected().getRating()));
		}
	}

	/**
	 * Method that returns an iterable structure with all the elements from the
	 * library that match the given regex expression
	 *
	 * @param reexp regex expression to match
	 *
	 * @requires reexp != null
	 * @return iterable structure with elements form the library that match the
	 *         reexp
	 * @ensures \result != null
	 *
	 */
	public Iterable<ISong> getMatches(String reexp) {
		ArrayQListWithSelection<ISong> matches = new ArrayQListWithSelection<>();
		Iterable<ISong> iterable = getSongs();
		for (ISong iSong : iterable) {
			if (iSong.matches(reexp)) {
				matches.add(iSong);
			}
		}
		return matches;
	}

	/**
	 * Method that returns an iterable structure with all the elements from the
	 * library in the same order
	 *
	 * @return iterable structure with all elements from library
	 * @ensures \result != null
	 *
	 */
	public Iterable<ISong> getSongs() {
		ArrayQListWithSelection<ISong> ret = new ArrayQListWithSelection<>();
		for (ISong song : super.getList()) {
			ret.add(song);
		}
		return ret;
	}

	/**
	 * Creates a String representation of a Music Library
	 *
	 * @return string representing a Music Library
	 * @ensures \result != null
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(super.toString());

		return sb.toString();
	}

	/**
	 * Method that checks if a given object is equal to this one
	 * Two Music Library are equal if they are the same object or
	 * if they have the equal lists of songs and if they have the
	 * equal lists of listeners
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

		MusicLibrary l = (MusicLibrary) other;

		return super.equals(other) && getListeners().equals(l.getListeners());

	}

}
