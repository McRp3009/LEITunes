package domain.core;

import domain.facade.ISong;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 * Class that represent the event of a song addition to a Music Library
 *
 */
public class SongAddedLibraryEvent extends SongLibraryEvent {

	/**
	 * SongAddedLibraryEvent constructor
	 *
	 * @param song the song related to the event (being added)
	 * @param lib  libarary where the event happended
	 */
	public SongAddedLibraryEvent(ISong song, MusicLibrary lib) {
		super(song, lib);
	}

}
