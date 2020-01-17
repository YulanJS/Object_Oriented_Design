
public interface State {
	public abstract void drawBoard(GameController gameController);

	public abstract void handle(GameController gameController);
	
	public abstract void showPlayerScreen(GameController gameController);
}
