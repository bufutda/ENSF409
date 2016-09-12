/**
 * ENSF 409 - Lab 3 - Winter 2015
 * Started by: Mahmood Moussavi
 * January 22, 2015
 * Completed by: Mitchell Sawatzky & Connor Newman
 */

class Line {

	Point start, end;
	private static int classID = 0;
	private int objID;

	public Line(Point a, Point b) {
	start = a;
	end = b;
	 objID = ++ classID;
	}


	public double distance(){
	    return Point.distance(start, end);
    }

    public String toString()
    {
    	String s = "Line " + objID + ": starts at " + start.toString() + ", and ends at " + end.toString();
    	return s;
    }
}
