public class AverageCalculator {

    public static void main(String[] args) {

        double total = 0;

        System.out.printf("The %d numbers are:", args.length);
        for (String i : args) {
            double val = Double.parseDouble(i);
            System.out.printf(" %.3f", val);
            total += val;
        }
        System.out.println();

        System.out.printf("And their average is: %.3f\n", total / args.length);

    }

}
