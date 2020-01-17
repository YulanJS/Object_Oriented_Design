import java.util.ArrayList;
import java.util.Iterator;
public class Ship implements Iterable<Coordinate>{
	private final int SHIP_SIZE = 3;
	private final int HIT = 1;
	
	private int size;
	private Coordinate startCoordi;
	private int[] cellHealth;
	private int life;
	private ArrayList<Coordinate> body;
	
	public Ship(Coordinate c, int size) {
		startCoordi = c;
		this.size = SHIP_SIZE;
		body = new ArrayList<Coordinate>(size);
		for(int i = 0; i < size; i++)
		{
			body.add(new Coordinate(c.getX(), c.getY() + i));
		}
		life = this.size;
	}
	
	//This is setting shooting result
//	public boolean hitCell(Coordinate c) {
//		if (isInShip(c)) 
//		{
//			for(int i = 0; i < size; i++)
//			{
//				if(cellHealth[i] != HIT)
//				{
//					life--;
//					return true;
//				}
//			}
//		}
//		else
//		{
//			return false; //not in the ship or the position is already hit
//		}
//		
//	}
	
	//This is getting shooting result
//	public int getCell(Coordinate c) {
//		if (isInShip(c)) {
//			return cellHealth[c.getY() - startCoordi.getY()]; // 0 for not hit yet or 1 for hit
//		}
//		return -1;// for not in this ship
//	}
	
	public void setLife()
	{
		life--;
	}
	
	public boolean isSunk() {
		return life == 0;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setStartCoordinate(Coordinate c) {
		startCoordi = c;
	}
	
	public Coordinate getStartCoordinate() {
		return startCoordi;
	}
	
	public boolean isInShip(Coordinate c) {
		for(int i = 0; i < size; i++)
		{
			if(body.get(i).equals(c))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isOverlapping(Ship other) {
		for(int i = 0; i < other.size; i++)
		{
			if(isInShip(other.body.get(i)))
			{
				return true;
			}
		}
		return false;
	}
	
	public Iterator<Coordinate> iterator()
	{
		return body.iterator();
	}
	
	public String toString() {
		String str = "";
		int num;
		for (int i = 0; i < size; i++) {
			num = i + startCoordi.getY();
			str += "(" + startCoordi.getX() + ", " + num + ")";
		}
		return "The ship is at position " + str;
	}
}
