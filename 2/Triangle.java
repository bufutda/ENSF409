public class Triangle {

    private int [][] triangle;
    private int size;

    Triangle(int n) {
        //allocate array and fill it
        size = n;
        triangle = new int [n + 1][];
        for (int i = 0; i < n; i++) {
            triangle[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i)
                    triangle[i][j] = 1;
                else
                    triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
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
                System.out.printf((j == 0 ? "" : " ") + "%d" + (j == i ? "\n" : ""), triangle[i][j]);
    }
    public int[] sumRows() {
        //array with the sum of each row
        int[] sum = new int[size];
        for (int i = 0; i < size; i++) {
            int rowSum = 0;
            for (int j = 0; j <= i; j++)
                rowSum += triangle[i][j];
            sum[i] = rowSum;
        }
        return sum;
    }
    public int[] sumCols() {
        //array with the sum of each column
        int[] sum = new int[size];
        for (int i : sum)
            sum[i] = 0;
        for (int i = 0; i < size; i++)
            for (int j = 0; j <= i; j++)
                sum[j] += triangle[i][j];
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

        Triangle pt = new Triangle(Integer.parseInt(args[0]));
        pt.printTriangle();

        int [] sum_rows = pt.sumRows();
        System.out.println("\nHere are the sum of rows:");
        for(int i =0; i < pt.size(); i++)
            System.out.println(sum_rows[i]);

        int [] sum_cols = pt.sumCols();
        System.out.println("\nHere are the sum of columns:");
        for(int i =0; i < pt.size(); i++)
            System.out.printf( "%-5d", sum_cols[i]);
        System.out.println();
    }

}
