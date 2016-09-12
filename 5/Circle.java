class Circle extends Shape
{
	private Double radius;

	Circle(Double x_origin, Double y_origin, Double newradius,  String name, Colour colour){
		super(x_origin, y_origin, name, colour);
		radius = newradius;
	}

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

	public void set_radius(Double newradius){
		radius = newradius;
	}

	public Double get_radius() {
		return radius;
	}

	public Double area() {
		return Math.PI * Math.pow(radius, 2);
	}

	public Double perimeter() {
		return 2 * Math.PI * radius;
	}

	public Double  volume(){
		return 0.0;
	}


	public String toString(){
		String s = super.toString()+ "\nRadius: " + radius;
		return s;
	}

	public void enlarge(double multiplier) throws SizeFactorException {
		if (multiplier < LIMIT) {
			throw new SizeFactorException(multiplier);
		}
		radius *= multiplier;
	}

	public void shrink(double divisor) throws SizeFactorException {
		if (divisor < LIMIT) {
			throw new SizeFactorException(divisor);
		}
		radius /= divisor;
	}
}
