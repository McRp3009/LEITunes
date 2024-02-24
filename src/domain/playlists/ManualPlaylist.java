package domain.playlists;
import domain.core.MusicLibrary;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Objects of this type represent playlists wich songs were added manually.
 * This playlists allow manual addition and removals
 *
 */
public class ManualPlaylist extends AbsPlaylist {

	/**
	 * ManualPlaylist constructor
	 * @param name the name of the playlist
	 * @param library the library
	 */
	public ManualPlaylist(String name, MusicLibrary library) {
		super(name, library);
	}

}
