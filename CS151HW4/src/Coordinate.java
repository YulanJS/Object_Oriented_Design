
public class Coordinate {
	private int xCoordi; //This is the coordinate for the grid in Model. It is not for screen position.
	private int yCoordi; 
	public Coordinate(int x, int y)
	{
		xCoordi = x;
		yCoordi = y;
	}
	
	public int getX()
	{
		return xCoordi;
	}
	
	public int getY()
	{
		return yCoordi;
	}
	
	public void setX(int x)
	{
		xCoordi = x;
	}
	
	public void setY(int y)
	{
		yCoordi = y;
	}
	
	public boolean equals(Object other)
	{
		Coordinate that = (Coordinate)other;
		return this.xCoordi == that.xCoordi && this.yCoordi == that.yCoordi;
	}
	
}
