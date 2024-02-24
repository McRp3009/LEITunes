package domain.player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import servicos.JLPlayer;

/**
 * @author fmartins
 * @author malopes 
 * Adapter for the external mp3 player. This is a singleton class.
 * 
 */
public enum JLPlayerAdapter implements Player {

	/**
	 * The singleton player
	 */
	 INSTANCE;
	
	/**
	 * The reference for the external mp3Player
	 */
	private JLPlayer mp3Player;
	
	/**
	 * The support for the listeners
	 */	
	private PropertyChangeSupport listenerManager = new PropertyChangeSupport(this); 

	
	@Override
	public boolean load(String filename) {
    	try {
    		stop();
			mp3Player = new JLPlayer(new BufferedInputStream(new FileInputStream(filename)), this);
		} catch (Exception e) {
			System.out.println("Somethig went wrong. Did not load song in" + filename);
			return false;
		} 
		return true;
	}

	@Override
	public void play() {
		if (mp3Player != null)
			mp3Player.play();
	}

	@Override
	public void still() {
		if (mp3Player != null)
			mp3Player.still();
	}

	@Override
	public void stop() {
		if (mp3Player != null)
			mp3Player.stop();
	}

	@Override
	public void hasEndedSong() {
		PropertyChangeEvent event = new PropertyChangeEvent(this, "playingState", Player.PlayingState.ON, Player.PlayingState.ENDED);
		listenerManager.firePropertyChange(event);
	}

	@Override
	public void hasStopedSong() {
		PropertyChangeEvent event = new PropertyChangeEvent(this, "playingState", Player.PlayingState.ON, Player.PlayingState.STOPED);
		listenerManager.firePropertyChange(event);
	}

	@Override
	public void addListener(PropertyChangeListener listener) {
		listenerManager.addPropertyChangeListener(listener); 
	}
	
	@Override
	public void removeListener(PropertyChangeListener listener) {
		listenerManager.removePropertyChangeListener(listener); 
	}

}
