package domain.facade;

import domain.core.MusicLibrary;
import domain.playlists.PlaylistList;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Objects of this type represent a system that allows to play songs from a library or playlists
 */
public class LEITunes {

    private MusicLibrary library;
    private PlaylistList playlistList;
    private PlaylistListController playlistListControler;
    private MusicLibraryController libraryController;

    /**
     * LEITunes constructor
     */
	public LEITunes(){
        this.library = new MusicLibrary();
        this.playlistList = new PlaylistList(library);
        this.playlistListControler = new PlaylistListController(playlistList, library);
        this.libraryController = new MusicLibraryController(library);
	}

    /**
     * Method that returns the PlaylisListController
     * @return the PlaylistListController
     * @ensures \result != null
     */
    public PlaylistListController getPlaylistController() {
        return this.playlistListControler;
    }

    /**
     * Method that returns the MusicLibraryController
     * @return the MusicLibraryController
     * @ensures \result != null
     */
    public MusicLibraryController getMusicLibraryController() {
        return this.libraryController;
    }
}
