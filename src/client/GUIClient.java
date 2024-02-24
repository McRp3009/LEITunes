package client;

import domain.facade.LEITunes;
import ui.UI;

/**
 * @author antonialopes
 * Class responsible for starting up the system (GUI version)
 */
public class GUIClient {
	
	/**
	 * The method for the start up of the system 
	 */
	public static void main (String [] args) {
		LEITunes leiTunes = new LEITunes ();
		UI ui = new UI (leiTunes.getPlaylistController(), 
						leiTunes.getMusicLibraryController());
		ui.run ();
	}
}
