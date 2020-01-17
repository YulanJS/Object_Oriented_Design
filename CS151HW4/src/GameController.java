import java.util.*;
import javax.swing.event.*;

public class GameController{
	private final String PLAYER1 = "player1";
	private final String PLAYER2 = "player2";
	
	
	private Player player1;
	private Player player2;
	private PlayerScreen player1Screen;
	private PlayerScreen player2Screen;
	private State state;
	public GameController()
	{
		player1 = new Player(PLAYER1);
		player2 = new Player(PLAYER2);
		player1Screen = new PlayerScreen(player1, player2, true, this);
		player2Screen = new PlayerScreen(player2, player1, false, this);

		state = new Player1SetUpState();
	}

	public Player getPlayer1()
	{
		return player1;
	}
	
	public Player getPlayer2()
	{
		return player2;
	}
	
	public PlayerScreen getPlayer1Screen()
	{
		return player1Screen;
	}
	
	public PlayerScreen getPlayer2Screen()
	{
		return player2Screen;
	}
	
	public State getState()
	{
		return state;
	}
	
	public void setState(State s)
	{
		state = s;
	}
	
	public void update()
	{
		state.drawBoard(this);
	}
	
	public void nextState()
	{
		state.handle(this);
	}
	
	public void showScreen()
	{
		state.showPlayerScreen(this);
	}
}
