package client;
import java.util.Arrays;
import java.util.List;

import domain.facade.ISong;
import domain.facade.LEITunes;
import domain.facade.MusicLibraryController;
import domain.facade.PlaylistListController;

/**
 * A simple application client that uses both application controllers.
 *	
 * @author malopes
 */
public class SimpleClient {

	/**
	 * An utility class should not have public constructors
	 */
	private SimpleClient() {
	}

	public static void main (String [] args) throws InterruptedException {
		
		LEITunes tunes = new  LEITunes();
		
		PlaylistListController plc = tunes.getPlaylistController();
		MusicLibraryController slc = tunes.getMusicLibraryController();

		addToLibraryTenSongs(slc); 
		//showState(plc, slc);
		
		createPlaylist("On the Go", plc); 
		createPlaylist("Relax", plc); 
		//showState(plc, slc);
		
		addToPlaylist(2, Arrays.asList(0,2,5,7), plc, slc);
		//showState(plc, slc);

		//-----------------------------------

		slc.addSong("songs/Imagine Dragons - Demons (Official).mp3");	

		//-----------------------------------
		
		addToPlaylist(3, Arrays.asList(9,2,8,10), plc, slc); 
		showState(plc, slc);
		
		search(".*VO.*", slc);
	
		removeOneSongFromLibrary(2, slc);
		showState(plc, slc);

		playingSomeSongsFromLibrary(slc);
		showState(plc, slc);
		
		playingSomeSongsInPlaylist(3, plc);
		showState(plc, slc);	
		
	}



	private static void showState(PlaylistListController plc, MusicLibraryController slc) {
		System.out.println(slc);
		System.out.println(plc);	
	}


	/**
	 * Add songs to the Music Library 
	 * 
	 * @param slc the music library controller
	 */
	private static void addToLibraryTenSongs(MusicLibraryController slc) {
		System.out.println("\n----------------------------------------------- ");		
		System.out.println("           Adding songs to library                ");
		System.out.println("-----------------------------------------------\n ");

		slc.addSong("songs/Goldberg Variations.mp3");
		slc.addSong("songs/MeuFado.mp3");
		slc.addSong("songs/Fado Tordo.mp3");
		slc.addSong("songs/08 Odeio.mp3");
		slc.addSong("songs/09 Homem.mp3");
		slc.addSong("songs/Exsultate.mp3");		
		slc.addSong("songs/mundo.mp3");
		slc.addSong("songs/02 Dormir.mp3");
		slc.addSong("songs/5 The Dance At The Gym.mp3");
		slc.addSong("songs/5 V. remembering before all this.mp3");		
	}


	/**
	 * Create a  playlist 
	 * 
	 * @param name the playlist name
	 * @param plc  the list of playlists contoller's
	 */
	private static void createPlaylist(String name, PlaylistListController plc) {		
		plc.createPlaylist(name);
		System.out.println("\n----------------------------------------------- ");		
		System.out.println("             Playlist " + name + " created        ");
		System.out.println("-------------------------------------------------\n");
	}

	/**
	 * Add songs to playlist
	 * 
	 * @param plc the list of playlists contoller's
	 * @param slc the music library controller
	 */
	private static void addToPlaylist(int index, List<Integer> asList, PlaylistListController plc, MusicLibraryController slc) {		
		plc.selectPlaylist(index);
		String playlist = plc.getSelectedPlaylist().getName();
		for(int i: asList) {
			slc.selectSong(i);
			plc.addSong();	
		}
		System.out.println("\n----------------------------------------------- ");
		System.out.println("        Songs "+ asList + " added to " + playlist);
		System.out.println("----------------------------------------------- ");
	}

	/**
	 * Search for songs in the music library 
	 * @param slc the music library controller
	 */
	private static void search(String regExp, MusicLibraryController slc) {
		System.out.println("\n----------------------------------------------- ");
		System.out.println("            Search songs in library by "+ regExp);
		System.out.println("----------------------------------------------- ");

		Iterable<ISong> matches = slc.getMatches(regExp);
		for (ISong s: matches) {
			System.out.println(s);
		}
	}

	/**
	 * Test the removal of songs from the music library
	 * 
	 * @param slc the music library controller
	 * @param index song to be removed 
	 */
	private static void removeOneSongFromLibrary(int index, MusicLibraryController slc) {
		System.out.println("\n----------------------------------------------- ");
		System.out.println("         Select song "+ index + " from library and remove it ");
		System.out.println("----------------------------------------------- ");

		slc.selectSong(index);
		slc.removeSelectedSong();
	}


	/**
	 * Test the play functionality of the library
	 * @param slc the music library controller
	 * 
	 * @throws InterruptedException
	 */
	private static void playingSomeSongsFromLibrary(MusicLibraryController slc) throws InterruptedException {
		System.out.println("\n----------------------------------------------- ");
		System.out.println("                     Playing  from library        ");
		System.out.println("----------------------------------------------- ");

		int index = 0;
		int seconds = 5;
		
		System.out.println("Select and play "+ index);
		slc.selectSong(index);
		System.out.println("Selected "+ slc.getSelectedSong());
		slc.play();
		
		index = 2;
		System.out.println("Wait " + seconds + " seconds and select " + index);
		Thread.sleep(seconds*1000); 
		slc.selectSong(index);
		System.out.println("Selected "+ slc.getSelectedSong()+" and wait "+ seconds + "seconds");
		
		Thread.sleep(seconds*1000); 
		System.out.println("Let's change the mood and play selected");	
		slc.play();
		Thread.sleep(seconds*1000); 	
		System.out.println("I like this music! Let's increase its rank twice");		
		slc.incRateSelected();
		slc.incRateSelected();
		System.out.println("Lets wait a bit and stop it");			
		Thread.sleep(30*1000);
		slc.stop();
	}

	/**
	 * Test the play functionality of the playlist
	 * @param plc  the list of playlists contoller's
	 * @param index playlist to use 
	 * 
	 * @throws InterruptedException
	 */
	private static void playingSomeSongsInPlaylist(int index, PlaylistListController plc) throws InterruptedException {
		System.out.println("\n----------------------------------------------- ");
		System.out.println("                     Playing from a playlist      ");
		System.out.println("----------------------------------------------- ");

		plc.selectPlaylist(index);
		System.out.println("Library selected "+ plc.getSelectedPlaylist());

		System.out.println("Select first and play all in playlist");
		plc.selectSong(0);
		plc.play();
		Thread.sleep(3*2*60*1000*4);
	}


}
