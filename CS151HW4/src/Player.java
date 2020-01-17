import java.util.*;
import java.util.Iterator;

import javax.swing.event.*;
import java.awt.event.*;

public class Player {
	private final int TOTAL_NUM_OF_SHIPS = 5;
	private final int NUM_OF_ROWS = 10;
	private final int NUM_OF_COLS = 10;
	
	
	public final int MISS = 1;
	public final int HIT = 2;
	public final int DESTROY = 3;
	public final int DID_BEFORE = 4;
	public final int INVALID_HIT = 5;
	public final int OCCUPIED = 6;//a cell from a ship occupies this position.

	
	private String playerName;
	private int[][] playerGrid;	
	private int numOfHealthyShips;
	private ArrayList<Ship> ships; // need to add listeners
	private ArrayList<ChangeListener> listeners;
	private int shipCount;
	
	public Player(String name) {
		playerName = name;
		playerGrid = new int[NUM_OF_ROWS][NUM_OF_COLS];
		ships = new ArrayList<>(TOTAL_NUM_OF_SHIPS);
		listeners = new ArrayList<ChangeListener>();
	}
	
	private boolean canPlaceShip(Coordinate c, int size) {
		// this function makes sure that the startPosition of a ship is inside the grid.
		if(shipCount >= 5)
		{
			return false;
		}
		Ship ship = new Ship(c, size);
		Iterator<Coordinate> cellIterator = ship.iterator();
		while(cellIterator.hasNext()) {
			Coordinate next = cellIterator.next();
			int i = next.getX();
			int j = next.getY();
			if (i < 0 || j < 0 || i >= NUM_OF_ROWS || j >= NUM_OF_COLS || playerGrid[i][j] == OCCUPIED)
				// Not checking overlapping with other ships or not...Checking the availability
				// of cells on board.
				return false;
		}
		return true;
	}
	
	// need a method to place all ships.
	public boolean placeAShip(Coordinate c, int size) {
		if (canPlaceShip(c, size)) {
			Ship ship = new Ship(c, size);
			Iterator<Coordinate> cellIterator = ship.iterator();
			shipCount++;
			ships.add(ship);
			//traverse it twice because the first time is to check whether can place it or not.
			//if set board info at that time, then if a cell is OK, it sets to OK to place, but the
			//info is wrong.
			while(cellIterator.hasNext()) {
//				System.out.println("has next");
				//record ship positions on this ship board
				Coordinate next = cellIterator.next();
//				System.out.println(next.getX());
//				System.out.println(next.getY());
				playerGrid[next.getX()][next.getY()] = OCCUPIED;
			}
			
			ChangeEvent event = new ChangeEvent(this);
			for (ChangeListener listener : listeners) {
				System.out.println("Please place a ship according to model");
				listener.stateChanged(event);
			}
			
			display();
			System.out.println("Ship Added");
			numOfHealthyShips++;
			
			
			return true;
		}
		System.out.println("Ship Cannot be Added");
		return false;
	}
	
	// set the value of a small cell
	public int fireAt(Coordinate c) {
		// I used this position x, y as 2D array index.
		// if all sunk, should not fire...
		int x = c.getX();
		int y = c.getY();
		if (x < 0 || x >= NUM_OF_ROWS || y < 0 || y >= NUM_OF_COLS) {
			return INVALID_HIT; //out of shipBoard.
		}
		//use shipBoard to record hitting information...
		if(playerGrid[x][y] == HIT || playerGrid[x][y] == MISS)
		{
			//already hit before, no matter it his the goal or misses the goal, it is an invalid hit.
			return DID_BEFORE;//This is an invalid hit, so it doesn't count.
		}
		//when the c position is not hit before: it can be hit or miss
		int i = 0;
		do
		{
			Ship ship = ships.get(i);
			if(!(ship.isSunk()))
			{
				if(ship.isInShip(c))
				{
					ship.setLife();//decrease this ship's life.
					playerGrid[x][y] = HIT; //update shipBoard info.
					if(ship.isSunk())
					{
						//this hit makes the ship sink
						numOfHealthyShips--;
						ChangeEvent event = new ChangeEvent(this);
						for (ChangeListener listener : listeners) {
							System.out.println("Please show attack according to model");
							listener.stateChanged(event);
						}
						return DESTROY;
					}
					ChangeEvent event = new ChangeEvent(this);
					for (ChangeListener listener : listeners) {
						System.out.println("Please show attack according to model");
						listener.stateChanged(event);
					}
					return HIT;
				}
			}
			i++;
		}while(i < shipCount);
		playerGrid[x][y] = MISS;
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			System.out.println("Please show attack according to model");
			listener.stateChanged(event);
		}
		return MISS;
	}
	
	public String getName()
	{
		return playerName;
	}
	
	public int getNumOfHealthyShips() {
		return numOfHealthyShips;
	}
	
	public int getNumOfSunkShips() {
		return TOTAL_NUM_OF_SHIPS - numOfHealthyShips;
	}
	
	public boolean allDestroyed() {
		return numOfHealthyShips == 0;
	}
	
	public void display() {
		for (int i = 0; i < NUM_OF_ROWS; i++) {
			for (int j = 0; j < NUM_OF_COLS; j++) {
				System.out.printf(playerGrid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Number of healthy ships: " + numOfHealthyShips);
		System.out.println("Number of ships sunk: " + getNumOfSunkShips());
	}
	
	public int getPlayerInfoCell(int i, int j) {
		return playerGrid[i][j];
	}
	
	public boolean changeTurnToPlace()
	{
		if(shipCount == 5)
			return true;
		return false;
	}
	
	public void addChangeListener(ChangeListener listener) {
		listeners.add(listener);
	}
}
