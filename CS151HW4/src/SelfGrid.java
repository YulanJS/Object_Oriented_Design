import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.event.*;;

/**
 * Represents the player's own grid
 */
// extends BattleGrid
public class SelfGrid extends BattleGrid{
	private final int OCCUPIED = 6;// a cell from a ship occupies this position.
	private final int SHIP_SIZE = 3;
	public final int HIT = 2;
	public final int DESTROY = 3;
	
	private Player player;
	private ChangeListener listener;
	
	// View can have Model to get info from Model.
	public SelfGrid(Player player, GameController gameController) {
		super();
		this.player = player;
		
		ChangeListener listener = new
				ChangeListener()
				{
					public void stateChanged(ChangeEvent event)
					{
						redraw(gameController);
					}
				};
		player.addChangeListener(listener);
		}
	
	public void redraw(GameController gameController) {
		
		this.removeAll();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel selfLine = new JPanel();
		selfLine.setLayout(new GridLayout(0, 10));
		int position;
		JPanel cell;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (player.getPlayerInfoCell(i, j) == OCCUPIED) {
					// 6 to represent ship there.
					cell = new JPanel();
					cell.setBackground(Color.MAGENTA);
					cell.setBorder(BorderFactory.createLineBorder(Color.white, 1));
					cell.setPreferredSize(new Dimension(20, 20));
				} 
				else if(player.getPlayerInfoCell(i, j) == HIT || player.getPlayerInfoCell(i, j) == DESTROY)
				{
					cell = new JPanel();
					cell.setBackground(Color.GREEN);
					cell.setBorder(BorderFactory.createLineBorder(Color.white, 1));
					cell.setPreferredSize(new Dimension(20, 20));
				}
				else {
					cell = getCell();
				}
				selfLine.add(cell);
			}
			this.add(selfLine);
		}
		gameController.showScreen();
		//Why not inject playerScreen? 
		//Player1Screen: player1(SelfGrid) player2(AttackGrid)
		//Player2Screen: player2(SelfGrid) player1(AttackGrid)
		//When player1 places a ship, player1 model is changed, all listeners will be notified.
		//player1Screen's selfGrid will be notified, player2Screen's attackGrid will be notified.
		//If they both use playerScreen.showScreen, two screens will show at the same time.
		//Which screen to show should depend on current state. So give control to gameController.
	}
	
	@Override
	protected JPanel getCell() {
		// getCell()...gets new JPanel...
		JPanel cell = new JPanel();
		cell.setBackground(Color.black);
		cell.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		cell.setPreferredSize(new Dimension(20, 20)); // for demo purposes onl
		return cell;
	}
	
	public void placeShipHere() {
		addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent event) {
				Object source = event.getSource();
				// if(source instanceof SelfGrid) //can detect whether selfGrid or attackGrid is
				// clicked.
	
				int x = event.getX() / 20; // 20 is the size of the small cell in selfGrid
				int y = event.getY() / 20;
				System.out.println(x);
				System.out.println(y);
				Coordinate c = new Coordinate(y, x);
				// screen position and ship board position need to be switched.
				player.placeAShip(c,SHIP_SIZE);//control tells model
			}
			
			public void mouseEntered(MouseEvent arg0) {
			}
			
			public void mouseExited(MouseEvent arg0) {
			}
			
			public void mousePressed(MouseEvent arg0) {
			}
			
			public void mouseReleased(MouseEvent arg0) {
			}
		});	
	}
}