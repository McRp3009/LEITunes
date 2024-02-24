package domain.core;

import domain.facade.ISong;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 * Class that represent the event of a song changing its rate in a Music Library
 *
 */
public class SongRatedLibraryEvent extends SongLibraryEvent {

	private Rate before;
	private Rate after;

	/**
	 * SongRatedLibraryEvent constructor
	 *
	 * @param song   song related to the event (changing rate)
	 * @param lib    library where the event happened
	 * @param before song rate before the event
	 * @param after  song rate before the event
	 */
	SongRatedLibraryEvent(ISong song, MusicLibrary lib, Rate before, Rate after) {
		super(song, lib);
		this.before = before;
		this.after = after;
	}

	/**
	 * @return the Rate before the current one
	 * @ensures \result != null
	 */
	public Rate getBefore() {
		return this.before;
	}

	/**
	 * @return the rate after the current one
	 * @ensures \result != null
	 */
	public Rate getAfter() {
		return this.after;
	}

}
