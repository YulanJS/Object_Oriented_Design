import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class PlayerScreen extends JFrame {
	private SelfGrid selfGrid;
	private AttackGrid attackGrid;
	private JButton next;
	//for a screen for player1, it has attackGrid, needs to check the info of player2 
	//to see whether it hits or not.
	//View needs the int[][] positions from Model to draw...
	
	//on player1's screen, only player1 info goes onto selfGrid.
	public PlayerScreen(Player self, Player other, boolean show, GameController gameController) {
		super(self.getName());
		this.setLayout(new BorderLayout());
		selfGrid = new SelfGrid(self, gameController);//What is name doing in self Grid?
		// selfGrid.redraw();
		this.add(selfGrid, BorderLayout.EAST);
		attackGrid = new AttackGrid(other,gameController);
		this.add(attackGrid, BorderLayout.WEST);
		this.add(new JLabel(self.getName()), BorderLayout.NORTH);
		next = new JButton("next");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideScreen();
				gameController.nextState();
				//At first, inject gameController to let the next button have the function of
				//switching screens... Each state should know which screen to display.
				//Later, found that selfGrid and attackGrid should have gameController as injection 
				//instead of having playerScreen as injection... To see specific reasons, please move 
				//to selfGrid.java.
			}
		});
		
		this.add(next, BorderLayout.CENTER);
		
		//add the south JPanel here
		
		
		this.pack();
		this.setVisible(show);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void hideScreen() {
		this.setVisible(false);
	}
	
	public void showScreen() {
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public SelfGrid getSelfGrid()
	{
		return selfGrid;
	}
	
	public AttackGrid getAttackGrid()
	{
		return attackGrid;
	}
}
