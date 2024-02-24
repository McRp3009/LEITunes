package domain.core;

import domain.facade.ISong;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 * Class that represents the event of removing a Song in a Music Library
 *
 */
public class SongRemovedLibraryEvent extends SongLibraryEvent{

	/**
	 * SongRemovedLibraryEvent constructor
     *
	 * @param song song related to the event (being removed)
	 * @param lib  library where the event happened
	 */
    SongRemovedLibraryEvent(ISong song, MusicLibrary lib) {
        super(song, lib);
    }

}
