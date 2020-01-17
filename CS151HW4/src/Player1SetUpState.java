
public class Player1SetUpState implements State{
	
	public void drawBoard(GameController gameController)
	{
		gameController.getPlayer1Screen().getSelfGrid().placeShipHere();
	}
	
	public void handle(GameController gameController)
	{
		gameController.setState(new Player2SetUpState());
		gameController.update();
	}
	
	public void showPlayerScreen(GameController gameController)
	{
		gameController.getPlayer1Screen().showScreen();
	}
}
