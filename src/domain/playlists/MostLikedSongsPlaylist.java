package domain.playlists;

import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.core.SongLibraryEvent;
import domain.core.SongRatedLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Objects of this type represent smartplaylists where there are
 * the top N highest rated songs from a certain library
 *
 */
public class MostLikedSongsPlaylist extends SmartPlaylist {

	//constant that representes the limit of songs in this playlist
	private static final int N = 5;

	/**
	 * MostLikedSongsPlaylist Constructor 
	 * @param library Music Library where the playlist is located
	 */
	public MostLikedSongsPlaylist(MusicLibrary library) {
		super("Most Liked", library);
	}

	/**
	 * This type of playlists doesnt allow manual additions
	 * 
	 * @return always false
	 * @ensures \result == false
	 */
	@Override
	public boolean add(ISong song) {
		return false;
	}

	/**
	 * This type of playlists doesnt allow manual removals
	 * 
	 * @return always false
	 * @ensures \result == false
	 */
	@Override
	public boolean remove() {
		return false;
	}

	/**
	 * This type of playlists doesnt allow its elements to change
	 * positions
	 * 
	 * @return always false
	 * @ensures \result == false
	 */
	@Override
	public boolean moveUpSelected(int i) {
		return false;
	}

	/**
	 * Processes a SongLibraryEvent, especifically SongRemovedLibraryEvent e SongRatedLibraryEvent
	 *
	 * @param e the SongLibraryEvent given
	 * @ensures if (e instanceof SongRemovedLibraryEvent) then !\this.contains(e.getSong)
	 */
	@Override
	public void processEvent(SongLibraryEvent e) {
		if (e instanceof SongRemovedLibraryEvent) {
			if(this.size() > 0) {
				super.processEvent(e);
				searchForReplacement(e.getSong());
			}
		} else if (e instanceof SongRatedLibraryEvent) {
			SongRatedLibraryEvent evt = (SongRatedLibraryEvent) e;
			if(evt.getBefore().equals(evt.getAfter())) {
				return;
			}
			addAutomatic(evt.getSong());
		}
	}

	/**
     * Method that automatically adds a given song to the smartplaylist, if possible
     * @param song song to add
	 * 
     */
	@Override
	protected void addAutomatic(ISong song) {
		if (super.add(song)) {
			if (this.size() == N + 1) {
				int toRemove = lowestRankingIndex();
				removeAutomatic(toRemove);
			}
		} else {
			//existe pelo menos uma song q e a que nao conseguimos adicionar
            ISong lowest = getSongs().get(lowestRankingIndex());
			if (lowest.equals(song)) {
				searchForReplacement(song);
			}

		}
	}

	/**
	 * Method that, given a song, searches for a higher rated song in the library
	 * that isnt already in the playlist and adds it, if it exists
	 * @param song song to try and replace
	 */
	private void searchForReplacement(ISong song) {
		MusicLibrary lib = getLibrary();
		if (lib.size() > getSongs().size()) {
			int index = -1;
			for (int i = 0; i < lib.size(); i++) {
				if (!contains(lib.get(i)) && song.getRating().compareTo(lib.get(i).getRating()) < 0) {
					index = i;
				}
			}
			if (index != -1) {
				addAutomatic(lib.get(index));
			}

		}

	}

	/**
	 * Method that searches for the lowest rated song and returns its index
	 * @return index of the lowest rated song in the playlist
	 * @ensures 0 <= \result && \result < size() 
	 */
	private int lowestRankingIndex() {

		ISong lowest = getSongs().get(0);
		Iterator<ISong> it = iterator();
		int counter = 0;
		int index = 0;
		while (it.hasNext()) {
			ISong current = it.next();
			if (current.getRating().compareTo(lowest.getRating()) < 0) {
				lowest = current;
				index = counter;
			}
			counter++;
		}

		return index;

	}

}
