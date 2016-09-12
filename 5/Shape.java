/*
 * started by: M. Moussavi
 * Date: Feb 2015
 * Modified by: Mitchell Sawatzky
 */
abstract class Shape implements Cloneable, Resizeable, Accessible
{
	protected Point origin;
	protected Text name;
	abstract protected Double area();
	abstract protected Double perimeter();
	abstract protected Double volume();

	protected Shape(Double x_origin, Double y_origin, String name, Colour colour){

		origin = new Point(x_origin,y_origin, colour);
		this.name = new Text(name);
	}

	protected Point  getOrigin()
	{
		return origin;
	}

    public Object clone() throws CloneNotSupportedException {
        Shape obj = (Shape)super.clone();
        obj.origin = (Point)origin.clone();
        obj.name = (Text)name.clone();

        return obj;
    }

	protected  Double distance(   Shape  other)
	{
		return origin.distance(other.origin);
	}

	protected Double  distance(   Shape  a,    Shape  b)
	{
		return Point.distance(a.origin, b.origin);
	}


	protected void  move(Double dx, Double dy)
	{
		origin.setx(origin.getx()+dx);
		origin.sety(origin.gety()+dy);
	}

	@Override
	public String toString(){
		String s = "\nShape name: " + name + "\nOrigin: " + origin;
		return s;
	}

	public String getName() {
		return name.getText();
	}

	public void setName(String newName) {
		name.setText(newName);
	}
}
