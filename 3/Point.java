/**
 * ENSF 409 - Lab 3 - Winter 2015
 * Started by: Mahmood Moussavi
 * January 22, 2015
 * Completed by: Mitchell Sawatzky & Connor Newman
 */

class Point {
	private int x, y;

	public Point(int x, int y) {
	this.x = x;
	this.y = y;
	}

	static public double distance(Point a, Point b){
		double diffx = a.x - b.x;
		double diffy = a.y - b.y;
		return Math.sqrt(diffx * diffx + diffy * diffy);
	}

	public String toString(){
        String s = "(" + x + ", " + y + ")";
		return  s;
	}
}
