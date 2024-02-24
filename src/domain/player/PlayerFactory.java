package domain.player;

/**
 * @author antonialopes
 * A factory that provides the player that can be used for playing digital songs
 */
public enum PlayerFactory {
	
	INSTANCE;
	
	private Player player;
	
	/**
	 * @return the player that can be used for playing digital songs
	 */
	public Player getPlayer(){	
		return INSTANCE.player;
	}
	
	private PlayerFactory() {
		player = JLPlayerAdapter.INSTANCE;
	}
}
