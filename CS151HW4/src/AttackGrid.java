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
public class AttackGrid extends BattleGrid {
	private final int MISS = 1;
	private final int HIT = 2;
	private final int DESTROY = 3;
	
	private final int SHIP_SIZE = 3;
	
	private Player player;
//	private ChangeListener listener;
	
	public AttackGrid(Player player, GameController gameController) {
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
	
	// attackGrid only shows hit or not, change one small cell. All ships are placed
	// in selfGrid.
	// public void redraw(Coordinate c)
	// {
	// this.removeAll();
	// this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	// JPanel self = new JPanel();
	// self.setLayout(new GridLayout(0, 10));
	// int playerInfoCell;
	// self.setLayout(new GridLayout(0, 10));
	// Cell cell;
	// for (int i = 0; i < 10; i++) {
	// for (int j = 0; j < 10; j++) {
	// playerInfoCell = player.getPlayerInfoCell(i, j);
	// if(playerInfoCell == MISS)
	// {
	// //1 for miss
	// cell = new Cell(i, j);
	// cell.setBackground(Color.RED);
	// cell.setBorder(BorderFactory.createLineBorder(Color.white, 1));
	// cell.setPreferredSize(new Dimension(20, 20));
	// self.add(cell);
	// }
	// else if(playerInfoCell == HIT || playerInfoCell == DESTROY)
	// {
	// //2 for hit // 3 for destroy
	// cell = new Cell(i, j);
	// cell.setBackground(Color.GREEN);
	// cell.setBorder(BorderFactory.createLineBorder(Color.white, 1));
	// cell.setPreferredSize(new Dimension(20, 20));
	// self.add(cell);
	// }
	// else {
	// cell = getCell(i, j);
	// self.add(cell);
	// }
	// }
	// this.add(self);
	// }
	// }
	
	@Override
	protected JPanel getCell() {
		// getCell()...gets new JPanel...
		JPanel cell = new JPanel();
		cell.setBackground(Color.white);
		cell.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		cell.setPreferredSize(new Dimension(20, 20)); // for demo purposes onl
		return cell;
	}
	
	// attack and show the result of attack
	public void attackHere() {
		addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent event) {
				Object source = event.getSource();
				// if the MouseListener is for the whole selfGrid...it will return the relative
				// position on the grid
				// if(source instanceof SelfGrid) //can detect whether selfGrid or attackGrid is
				// clicked.
				// {
				int x = event.getX() / 20; // 20 is the size of the small cell in selfGrid
				int y = event.getY() / 20;
				// System.out.println(x);
				// System.out.println(y);
				Coordinate c = new Coordinate(y, x); // screen position and ship board position need to be switched.
				player.fireAt(c);
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
	
	public void redraw(GameController gameController) {
		this.removeAll();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel selfLine = new JPanel();
		selfLine.setLayout(new GridLayout(0, 10));
		int position;
		selfLine.setLayout(new GridLayout(0, 10));
		JPanel cell;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (player.getPlayerInfoCell(i, j) == MISS) {
					// 1 for miss
					cell = new JPanel();
					cell.setBackground(Color.RED);
					cell.setBorder(BorderFactory.createLineBorder(Color.white, 1));
					cell.setPreferredSize(new Dimension(20, 20));
					selfLine.add(cell);
				} else if (player.getPlayerInfoCell(i, j) == HIT || player.getPlayerInfoCell(i, j) == DESTROY) {
					// 2 for hit // 3 for destroy
					cell = new JPanel();
					cell.setBackground(Color.GREEN);
					cell.setBorder(BorderFactory.createLineBorder(Color.white, 1));
					cell.setPreferredSize(new Dimension(20, 20));
					selfLine.add(cell);
				} else {
					cell = new JPanel();
					cell = getCell();
					selfLine.add(cell);
				}
			}
			this.add(selfLine);
		}
		gameController.showScreen();
	}
	
	// this.showScreen();
	// this.addMouseListener(new MouseListener() {
	// public void mouseClicked(MouseEvent event) {
	// Object source = event.getSource();
	// // if the MouseListener is for the whole selfGrid...it will return the
	// relative
	// // position on the grid
	//
	// int x = ((JPanel)source).getX() / 20;
	// int y = ((JPanel)source).getY() / 20;
	// System.out.println(x);
	// System.out.println(y);
	// Coordinate c = new Coordinate(y, x); //screen position and ship board
	// position need to be switched.
	// int fireResult = player.fireAt(c);
	//
	// //Maybe should wait for player to notify the view class about their changes
	// and redraw as below...
	// int rowNum = c.getX();
	// int colNum = c.getY();
	//// redraw(c);
	// for(int i = 0; i < 10; i++)
	// {
	// for(int j = 0; j < 10; j++)
	// {
	// if(i == rowNum && j == colNum)
	// {
	// if(fireResult == MISS)
	// {
	// //1 for miss
	// JPanel cell = new JPanel();
	// cell.setBackground(Color.RED);
	// cell.setBorder(BorderFactory.createLineBorder(Color.white, 1));
	// cell.setPreferredSize(new Dimension(20, 20));
	// }
	// else if(fireResult == HIT || fireResult == DESTROY)
	// {
	// //2 for hit // 3 for destroy
	// JPanel cell = new JPanel();
	// cell.setBackground(Color.GREEN);
	// cell.setBorder(BorderFactory.createLineBorder(Color.white, 1));
	// cell.setPreferredSize(new Dimension(20, 20));
	// }
	// }
	// else
	// {
	// JPanel cell = getCell();
	// }
	//
	// }
	// }
	// }
	// public void mouseEntered(MouseEvent arg0) {}
	// public void mouseExited(MouseEvent arg0) {}
	// public void mousePressed(MouseEvent arg0) {}
	// public void mouseReleased(MouseEvent arg0) {}
	// });
	// }
}