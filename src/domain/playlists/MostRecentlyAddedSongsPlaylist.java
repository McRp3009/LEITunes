package domain.playlists;

import domain.core.MusicLibrary;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 */
public class MostRecentlyAddedSongsPlaylist extends SmartPlaylist {

    private static final int N = 5;

    /**
     * MostRecentlyAddedSongsPlaylist constructor
     * @param library the MusicLibrary
     */
    public MostRecentlyAddedSongsPlaylist(MusicLibrary library) {
        super("Most Recently Added", library);
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

    @Override
    protected void addAutomatic(ISong song) {
        if(super.add(song) && (this.size() == N+1)) {
            removeAutomatic(0);
        }

    }

    /**
	 * Processes a SongLibraryEvent, especifically SongRemovedLibraryEvent e SongAddedLibraryEvent
	 *
	 * @param e the SongLibraryEvent given
	 * @ensures if (e instanceof SongRemovedLibraryEvent) then !\this.contains(e.getSong)
     *          if (e instanceof SongAddedLibraryEvent) then contains(e.getSong()) &&
     *                                                       !contains(\old(this.getSongs().get(0)))
	 */
    @Override
	public void processEvent(SongLibraryEvent e) {
		if(e instanceof SongRemovedLibraryEvent) {
            if(this.size() > 0) {
                super.processEvent(e);
                searchForReplacement();
            }
		} else if(e instanceof SongAddedLibraryEvent) {
            addAutomatic(e.getSong());
        }
    }

    /**
	 * Method that searches for the most recent song in the library that isnt already in the playlist
	 */
    private void searchForReplacement() {
        MusicLibrary lib = getLibrary();
        if(lib.size() > getSongs().size()) {
            int initSize = getSongs().size();
            for(int i = lib.size()-1; i >= 0 && initSize == getSongs().size(); i--) {
                addAutomatic(lib.get(i));   
            }
        }
    }

}
