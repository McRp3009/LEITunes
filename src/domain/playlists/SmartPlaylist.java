package domain.playlists;
import java.util.Iterator;

import domain.core.MusicLibrary;
import domain.core.SongLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Objects of this type represent playlists wich songs were added manually.
 * This playlists dont allow manual addition, removals and changes in element positions
 *
 */
public abstract class SmartPlaylist extends AbsPlaylist {

    /**
     * SmartPlaylist constructor
     * @param name the playlist's name
     * @param library the MusicLibrary
     */
    protected SmartPlaylist(String name, MusicLibrary library) {
        super(name, library);
    }
    
    /**
     * Method that automatically adds a given song to the smartplaylist, if possible
     * @param song song to add
     */
    protected abstract void addAutomatic(ISong song);

    /**
     * Method that automatically removes the song in the given index, if possible
     * (its only possible to remove song from index if 0 <= index < size())
     * @param index index of the song to remove
     */
    protected void removeAutomatic(int index) {

        if(0 <= index && index < size()) {
            ISong selected = getSelected();
            select(index);
            super.remove();

            for(int i = 0; i < size(); i++) {
                if(selected.equals(getSongs().get(i))){
                    select(i);
                }
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
		if(e instanceof SongRemovedLibraryEvent) {
			int counter = 0;
			Iterator<ISong> it = iterator();
			while(it.hasNext()) {
				ISong song = it.next();
				if(song.equals(e.getSong())) {
					removeAutomatic(counter);
					break;
				}
				counter++;
			}

		}
    }



}
