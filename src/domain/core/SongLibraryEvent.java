package domain.core;

import domain.facade.ISong;
import util.observer.Event;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 *         Class that represent Song related events that happened in a Music
 *         Library
 *
 */
public abstract class SongLibraryEvent implements Event {

	private ISong song;
	private MusicLibrary lib;

	/**
	 * SongLibraryEvent constructor
	 *
	 * @param song the song that is triggering the event
	 * @param lib  the musiclibrary of the song that is triggering the event
	 */
	SongLibraryEvent(ISong song, MusicLibrary lib) {
		this.song = song;
		this.lib = lib;
	}

	/**
	 * Fetches the song that triggered the event
	 *
	 * @return the song that triggered the event
	 * @ensures \result != null
	 */
	public ISong getSong() {
		return this.song;
	}

	/**
	 * Fetches the musiclibrary of the event
	 *
	 * @return the musiclibrary of the event
	 * @ensures \result != null
	 */
	public MusicLibrary getLib() {
		return this.lib;
	}
}
