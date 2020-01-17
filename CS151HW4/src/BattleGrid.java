import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public abstract class BattleGrid extends JPanel {
	public BattleGrid() {
		// shipBoard = new ShipBoard("player1");
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JPanel selfLine = new JPanel();
		selfLine.setLayout(new GridLayout(0, 10));
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				JPanel cell = getCell();
				selfLine.add(cell);
			}
			this.add(selfLine);
		}
		// Problem: ActionListener is undefined for the type JPanel.
		// cell.addActionListener(new ActionListener()
		// {
		// public void actionPerformed(ActionEvent event)
		// {
		// cell = (JPanel)event.getSource();
		// cell.setBackground(Color.yellow);
		// }
		// });
	}
	
	protected abstract JPanel getCell();
	
//	protected class Cell extends JPanel
//	{
//		private int rowNum;
//		private int colNum;
//		
//		public Cell(int x, int y)
//		{
//			super();
//			rowNum = x;
//			colNum = y;
//		}
//		
//		public int getRowNum()
//		{
//			return rowNum;
//		}
//		
//		public int getColNum()
//		{
//			return colNum;
//		}
//	}
	

}