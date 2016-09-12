public class SizeFactorException extends Exception {
    private static final long serialVersionUID = 9137726330394461024L;
    public SizeFactorException(double n) {
        super("Error: SizeFactorException: Resize factor " + n + " is less than 1.0");
    }
}
