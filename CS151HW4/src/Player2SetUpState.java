
public class Player2SetUpState implements State{
	public void drawBoard(GameController gameController)
	{
		this.showPlayerScreen(gameController);
		gameController.getPlayer2Screen().getSelfGrid().placeShipHere();
	}
	
	public void handle(GameController gameController)
	{
		gameController.setState(new Player1ToPlay());
		gameController.update();
	}
	
	public void showPlayerScreen(GameController gameController)
	{
		gameController.getPlayer2Screen().showScreen();
	}
}
