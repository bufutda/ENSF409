import java.util.Scanner;


public class SinValidator {

private int[] SIN;

private int sumDigit(int x)
{
	int result =0;

	while(x > 0) {
		result += x % 10;
		x = x /10;
	}

	return result;
}

	public SinValidator(String sin) {

		SIN = new int[9];
		int i =0;
		int counter =0;
		while(i < sin.length())  {


			if (Character.isDigit(sin.charAt(i))) {
				if (counter < 9)
					SIN[counter] =(int) sin.charAt(i) - 48;
				counter++;
			}
			else{
				System.err.println("Error: Invalid input by the user");
				return;
			}
			i++;
		}

		if(counter != 9){
			System.err.println("Error: SIN must be 9 digits...");
			return;
		}
	}

	public boolean validateSin() {
        //note that since we're always adding steps, we can have a running total
        int runningTotal = SIN[0] + SIN[2] + SIN[4] + SIN[6]; // Add first, third, fifth, and seventh digits

        //iterate through second, fourth, sixth, and eighth digits
        for (int i = 1; i < 8; i += 2) {
            int intermediateMulti = SIN[i] * 2; //multiply digit by two
            while (intermediateMulti > 0) { //while there are still digits left
                runningTotal += intermediateMulti % 10; //add least significant digit
                intermediateMulti /= 10; //remove least significant digit
            }
            System.out.print("\033[33m" + runningTotal + "\033[0m\n");
        }

        if (SIN[8] == 10 - (runningTotal % 10)) //check equality of 9th digit and 10 - least significant digit of the running total
            return true;
        else
            return false;
	}

	public static void main(String[] args) {
		// Read user input

	    String sin;
		Scanner scan = new Scanner(System.in);
		while (true)
		{
			System.out.println("Please enter your 9 digit social insurance number"
					+ " or enter quit to terminate the program: ");
			sin = scan.nextLine();
			if(sin.toUpperCase().equals("QUIT"))
				break;
			SinValidator sv = new SinValidator(sin);
			if(sv.validateSin())
				System.out.println("Yes this is a valid SIN\n");
			else
				System.out.println("No this is NOT a valid SIN\n");

		}
	}

}
