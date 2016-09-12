/**
 * Started by M. Moussavi
 * March 2016
 * Completed by: Mitchell Sawatzky
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadRecord {

    private ObjectInputStream input;

    /**
     *  opens an ObjectInputStream using a FileInputStream
     */

    private void readObjectsFromFile(String name)
    {
        MusicRecord record;

        try
        {
            input = new ObjectInputStream(new FileInputStream( name ) );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error opening file." );
        }

        /* The following loop is supposed to use readObject method of of
         * ObjectInputSteam to read a MusicRecord object from a binary file that
         * contains several reords.
         * Loop should terminate when an EOFException is thrown.
         */

        try {
            while (true) {
                record = (MusicRecord)input.readObject();
                System.out.println(record.getYear() + "\t" + record.getSongName() + "\t" + record.getSingerName() + "\t" + record.getPurchasePrice());
            }
        } catch (EOFException e) {
        } catch (ClassNotFoundException e) {
            System.err.println("Could not find the class associated with this serialization");
            System.err.println(e.getStackTrace());
        } catch (IOException e) {
            System.err.println("Could not read object");
            System.err.println(e);
        }

        try {
            input.close();
        } catch (IOException e) {
            System.err.println("Could not close file");
            System.err.println(e.getStackTrace());
        }
    }


    public static void main(String [] args)
    {
        ReadRecord d = new ReadRecord();
        d.readObjectsFromFile("allsongs.ser");
    }
}
