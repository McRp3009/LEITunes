package domain.player;

import util.observer.PropertyObservable;

/**
 * @author antonialopes
 *
 */
public interface Player extends PropertyObservable{
	
	/**
	 * The type of the observable property
	 */
	enum PlayingState {ON, STILL, STOPED, ENDED}
	
	/**
	 * Loads the digital song stored in the  file with the filename
	 * @param filename
	 * @return if load has succedded
	 * @requires filename has a readable music file
	 */
	public boolean load (String filename);
	
	/**
	 * Plays the loaded music
	 */
	public void play ();
	
    /**
     * Pauses the music that is currently playing
     */
    public void still ();
  
    /**
     * Stopes the music that is currently playing
     */
    public void stop ();
    
    /**
     * Notifies listeners that playing song was stopped  
     */
    public void hasStopedSong();
    
    /**
     * Notifies listeners that playing song ended 
     */
    public void hasEndedSong();
     
}
 
