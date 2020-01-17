
public class EndGameState implements State{
	public void drawBoard(GameController gameController)
	{
		//check which player wins here.
		System.out.println("do nothing");
	}
	
	public void handle(GameController gameController)
	{
		System.out.println("done state");
	}
	
	public void showPlayerScreen(GameController gameController)
	{
		System.out.println("Already done, no screen to show");
	}
}
