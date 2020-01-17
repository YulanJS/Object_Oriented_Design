
public class Player1ToPlay implements State {
	public void drawBoard(GameController gameController)
	{
		System.out.println("Should show player1 screen again");
		this.showPlayerScreen(gameController);
		gameController.getPlayer1Screen().getAttackGrid().attackHere();
	}
	
	public void handle(GameController gameController)
	{
		gameController.setState(new Player2ToPlay());
		gameController.update();
	}
	
	public void showPlayerScreen(GameController gameController)
	{
		gameController.getPlayer1Screen().showScreen();
	}
}
