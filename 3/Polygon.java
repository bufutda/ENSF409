/**
 * ENSF 409 - Lab 3 - Winter 20115
 * Started by: Mahmood Moussavi
 * January 22, 2015
 * Completed by: Mitchell Sawatzky & Connor Newman
 */
import java.util.*;

class Polygon {
	private final LinkedHashSet <Line> polygon;
	private int objID;
	private static int classID;
	Iterator <Line> it;

	public Polygon(LinkedHashSet<Line> polygon) {
		 this.polygon = new LinkedHashSet<Line>();
		 for(Line l: polygon)
			 this.polygon.add (l);
		 objID = ++ classID;
		 it = this.polygon.iterator();
	}

	public Iterator <Line> getLine() {
		it = polygon.iterator();
		return it;
	}

	public static int classID(){
		return classID;
	}

    public String toString() {
        String s = "\nThe lines in polygon " + objID + " are:";
        for (Line l : polygon)
            s += "\n    " + l.toString();

    	return s;
    }
}
