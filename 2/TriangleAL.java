import java.util.ArrayList;

public class TriangleAL {

    private ArrayList <ArrayList <Integer>> triangle;
    private int size;

    TriangleAL(int n) {
        //allocate array and fill it
        size = n;
        triangle = new ArrayList <ArrayList <Integer>>(n+1);
        for (int i = 0; i < n; i++) {
            triangle.add(new ArrayList <Integer>(i+1));
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    triangle.get(i).add(1);
                else
                    triangle.get(i).add(triangle.get(i-1).get(j-1) + triangle.get(i-1).get(j));
            }
        }
    }

    public int size() {
        return size;
    }
    public void printTriangle() {
        //print triangle to stdout
        for (int i = 0; i < size; i++)
            for (int j = 0; j <= i; j++)
                System.out.printf((j == 0 ? "" : " ") + "%d" + (j == i ? "\n" : ""), triangle.get(i).get(j));
    }
    public ArrayList <Integer> sumRows() {
        //array with the sum of each row
        ArrayList <Integer> sum = new ArrayList <Integer>(size);
        for (int i = 0; i < size; i++) {
            int rowSum = 0;
            for (int j = 0; j <= i; j++)
                rowSum += triangle.get(i).get(j);
            sum.add(rowSum);
        }
        return sum;
    }
    public ArrayList <Integer> sumCols() {
        //array with the sum of each column
        ArrayList <Integer> sum = new ArrayList <Integer>();
        for (int i = 0; i < size; i++) {
            sum.add(0);
            for (int j = 0; j <= i; j++)
                sum.set(j, sum.get(j) + triangle.get(i).get(j));
        }
        return sum;
    }


    public static void main(String[] args) {
        if (args.length < 1 ) {
            System.err.println("ERROR: No integer argument.");
            System.exit(1);
        }
        for (int i = 0; i < args[0].length(); i++) {
            if (!Character.isDigit(args[0].charAt(i))) {
                System.err.println("ERROR: Argument is not an integer.");
                System.exit(1);
            }
        }

        TriangleAL pt = new TriangleAL(Integer.parseInt(args[0]));
        pt.printTriangle();

        ArrayList <Integer> sum_rows = pt.sumRows();
        System.out.println("\nHere are the sum of rows:");
        for(int i =0; i < pt.size(); i++)
            System.out.println(sum_rows.get(i));

        ArrayList <Integer> sum_cols = pt.sumCols();
        System.out.println("\nHere are the sum of columns:");
        for(int i =0; i < pt.size(); i++)
            System.out.printf( "%-5d", sum_cols.get(i));
        System.out.println();
    }

}
