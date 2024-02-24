package servicos;

import domain.player.Player;
import javazoom.jl.decoder.JavaLayerException;

public class JLPlayer extends javazoom.jl.player.Player {
   
    private boolean isPlaying;
    private boolean stop;
    private boolean end;
    private Thread player;
	private final Player iPlayer;

    public JLPlayer (java.io.InputStream stream, Player iPlay) throws JavaLayerException {
    	super (stream);
    	this.iPlayer = iPlay;
    	
    	player = new Thread() {
            public void run() {
                try { 
                	while (!stop && !end) {
                	  while (isPlaying && !stop && !end) {
                		 if (!decodeFrame ())
                			 end = true;
                	  }
                	}
                }
                catch (Exception e) { 
                	System.out.println(e); 
                }
                if (stop) 
                	iPlayer.hasStopedSong();
                else
                	iPlayer.hasEndedSong();
            }
        };
        player.start();
    }
    
    @Override
    public void play () {
    	isPlaying = true;
    }
    
    public void still () {
    	isPlaying = false;
    }
    
    public void stop () {
    	stop = true;
    	super.close();
    }
}
