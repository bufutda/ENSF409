public interface Resizeable {
    static final double LIMIT = 1.0;
    public void shrink(double divisor) throws SizeFactorException;
    public void enlarge (double multiplier) throws SizeFactorException;
}
