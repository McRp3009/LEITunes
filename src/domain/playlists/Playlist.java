package domain.playlists;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import domain.core.SongLibraryEvent;
import domain.facade.ISong;
import util.observer.Listener;

/**
 * @author antonialopes
 *
 * Playlists have a name and consist of a list of distinct musics from a music library.
 *
 * Removing a song from the library leads to automatic removal of the song from the
 * playlist, if it is there.
 *
 * Addition/removal of songs may be completely automatic and, hence, manual addition/removal
 * might not be possible. The same applies to changes of order through moveUpSelected method.
 *
 * At any time, one song in the playlist may be selected and one song of the
 * playlist may be playing (i.e. the play action was performed via the playlist).
 * A playlist keeps track that a song was played (until the end) through the playlist.
 *
 */
public interface Playlist extends Listener<SongLibraryEvent>, PropertyChangeListener, Iterable<ISong>{

	/**
	 * Returns the number of songs in the playlist
	 *
	 * @return the number of songs in the playlist
	 * @ensures \return >= 0
	 */
	int size();

	/**
	 * Returns the selected song
	 *
	 * @return the selected song
	 * @requires someSelected()
	 * @ensures \return != null
	 */
	ISong getSelected();

	/**
	 * Returns true if some element is selected
	 *
	 * @return true if some element is selected, false otherwise
	 */
	boolean someSelected();

	/**
	 * Adds a song to the end of the playlist, if it
	 * does not exist yet and selects it,
	 * if addition is possible
	 *
	 * @param song the element to be added
	 * @requires song != null
	 * @return true if the song was added to the playlist, false otherwise
	 * @ensures \result ==> size() == \old(size()) + 1 &&
	 * 						someSelected() &&
	 * 						getIndexSelected() == size() - 1
	 */
	boolean add(ISong song);

	/**
	 * Removes the selected element from the playlist, if possible
	 *
	 * @return true if the song was removed, false otherwise
	 * @ensures \return && \old someSelected()  ==>
	 * 					!someSelected() && size() == \old(size()) - 1
	 * @ensures !\return ==> \old someSelected() == someSelected()
	 * 							&& size() == \old(size())
	 */
	boolean remove();

	/**
	 * Selects song at position i
	 *
	 * @param i the position denoting the element to be selected
	 * @requires 0 <= i < size()
	 * @ensures someSelected() && getIndexSelected() == i &&
	 * 								size() == \old(size())
	 */
	void select(int i);

	/**
	 * Moves the current selected song up to position i,
	 * shifting down all elements in the playlist from
	 * positions i+1 to \old getIndexSelected()-1,
	 * if movement in the playlist is possible
	 *
	 * @param i the index where this element is going to be moved
	 * @requires someSelected() && 0 <= i < getIndexSelected()
	 * @ensures \return ==> someSelected() &&
	 * 					getIndexSelected() == i  &&
	 * 					size() == \old(size())
	 */
	boolean moveUpSelected(int i);


	/**
	 * Returns the index of the selected element, if any
	 *
	 * @return the index of the selected element, if any
	 * @requires someSelected()
	 * @ensures 0 <= \return < size()
	 */
	int getIndexSelected();

	/**
	 * Selects the next element, if any. Otherwise, no element is selected.
	 *
	 * @requires someSelected()
	 * @ensures if \old getIndexSelected() < size() - 1
	 *          then getIndexSelected() = \old getIndexSelected() + 1
	 *          else !someSelected()
	 * @ensures size() == \old(size())
	 */
	void next();

	/**
	 * Selects the previous element, if any. Otherwise, no element is selected.
	 *
	 * @requires someSelected()
	 * @ensures if \old getIndexSelected() > 0
	 *          then getIndexSelected() = \old getIndexSelected() - 1
	 *          else !someSelected()
	 * @ensures size() == \old(size())
	 */
	void previous();

	/**
	 * Returns the name of the playlist
	 *
	 * @return the name of the playlist
	 * @ensures \result != null
	 */
	String getName();

	/**
	 * Returns if a song is playing and the play action has been performed via the playlist
	 *
	 * @return true if a song is playing and the play action was done through the playlist,
	 *         false otherwise
	 */
	boolean isPlaying();

	/**
	 * Plays the selected song
	 *
	 * @requires someSelected()
	 * @ensures isPlaying()
	 */
	void play();

	/**
	 * Stops the playing song
	 *
	 * @requires isPlaying()
	 */
	void stop();

	/**
	 * Reaction to property change events, namely those emitted by the player
	 * (can affect the selected song and song being played)
	 */
	@Override
	void propertyChange(PropertyChangeEvent evt);

	/**
	 * Reaction to events, namely those emitted by the music library that
	 * backs up this playlist (can affect the content of the playlist)
	 */
	@Override
	void processEvent(SongLibraryEvent e);



}
