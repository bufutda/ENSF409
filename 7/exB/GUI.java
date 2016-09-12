import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;

/**
 * Container class for the the GUI
 * @author Mitchell Sawatzky
 * @version 1.0
 * @since Mar 2016
 */
public class GUI {
    /**
     * True if the a BST has been generated from a file
     */
    static boolean generated = false;
    /**
     * The path to the file the BST was generated from
     */
    static String path;

    /**
     * Program entry point
     * @param argv - cli arguments
     */
    public static void main (String[] argv) {
        // Declare all top-level frame elements
        JFrame f = new JFrame("Exercise B");
        JPanel p = new JPanel();
        JPanel buttons = new JPanel();
        JButton insert = new JButton("Insert");
        JButton query = new JButton("Query");
        JButton generate = new JButton("Generate New Tree");
        JTextArea t = new JTextArea();
        JScrollPane wrapper;
        // Declare BST
        BinSearchTree bst = new BinSearchTree();

        // register listener for the insert button
        insert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (generated) {
                    // declate input fields
                    JTextField id = new JTextField();
                    JTextField faculty = new JTextField();
                    JTextField major = new JTextField();
                    JTextField year = new JTextField();
                    Object[] inp = {
                        "ID:", id,
                        "Faculty:", faculty,
                        "Major:", major,
                        "Year:", year
                    };

                    // ask for inputs
                    int o = JOptionPane.showConfirmDialog(null, inp, "Add Record", JOptionPane.OK_CANCEL_OPTION);

                    // check if the user hit ok or cancel
                    if (o == JOptionPane.OK_OPTION) {
                        // make sure ID is valid
                        try {
                            int t = Integer.parseInt(id.getText());
                        } catch (NumberFormatException err) {
                            JOptionPane.showMessageDialog(null, "Field 'ID' must be a number", "Cannot insert into tree", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                        // make sure YEAR is valid
                        try {
                            int t = Integer.parseInt(year.getText());
                        } catch (NumberFormatException err) {
                            JOptionPane.showMessageDialog(null, "Field 'Year' must be a number", "Cannot insert into tree", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                        // write changes to the file
                        try {
                            PrintWriter writer = new PrintWriter(new FileOutputStream(path, true));
                            writer.append("    " + id.getText() + "   " + faculty.getText() + "      " + major.getText() + "    " + year.getText() + "\n");
                            writer.close();
                        } catch (IOException err) {
                            JOptionPane.showMessageDialog(null, "Could not save new record to file", "Internal Error", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                        // update BST
                        bst.insert(id.getText(), faculty.getText(), major.getText(), year.getText());
                        //update the text area
                        try {
                            updateText(bst, t);
                        } catch (IOException err) {
                            JOptionPane.showMessageDialog(null, "Could not turn BST into text", "Internal Error", JOptionPane.PLAIN_MESSAGE);
                            System.err.println(e);
                            return;
                        }
                    }
                } else {
                    // the user has not generated a tree yet
                    JOptionPane.showMessageDialog(null, "Please generate a tree first", "Cannot Insert Into Tree", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        // register listener for the query button
        query.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (generated) {
                    // search for the id
                    Node res = bst.find(bst.root, JOptionPane.showInputDialog(null, "Student ID:", "Input Student ID", JOptionPane.PLAIN_MESSAGE));
                    // display results
                    if (res != null) {
                        JOptionPane.showMessageDialog(null, res.toString(), "Query Result", JOptionPane.PLAIN_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Could not find a student with that ID", "Query Result", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please generate a tree first", "Cannot Query Tree", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        // register listener for for the generate button
        generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ask for path
                path = JOptionPane.showInputDialog(null, "File Path", "Input File Name", JOptionPane.PLAIN_MESSAGE);
                // declare line counter
                int line = 1;
                try {
                    // open file and iterate over each line
                    Scanner in = new Scanner(new FileInputStream(path));
                    while (in.hasNextLine()) {
                        String record = in.nextLine();
                        // trim the line of whitespace and split it every time there is one or more spaces
                        String[] rec = record.trim().split(" +");
                        // check that there are a correct number of fields in the line
                        if (rec.length != 4) {
                            JOptionPane.showMessageDialog(null, "Incomplete record at " + path + " (line " + line + ")", "Cannot Generate Tree", JOptionPane.PLAIN_MESSAGE);
                            return;
                        }
                        // update the tree
                        bst.insert(rec[0], rec[1], rec[2], rec[3]);
                        line++;
                    }
                    // close the file
                    in.close();
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(null, "Could not file file: " + path, "Cannot Generate Tree", JOptionPane.PLAIN_MESSAGE);
                    return;
                }

                // write bst data to the textarea
                try {
                    updateText(bst, t);
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(null, "Could not turn BST into text", "Internal Error", JOptionPane.PLAIN_MESSAGE);
                    System.err.println(e);
                    return;
                }
                // enable use of other buttons
                generated = true;
            }
        });

        // set the display area as non-editable
        t.setEditable(false);
        // allow the display area to scroll if there is overflow
        wrapper = new JScrollPane(t);

        // add buttons to the button panel
        buttons.add(insert);
        buttons.add(query);
        buttons.add(generate);

        // add content into the main panel
        p.setLayout(new BorderLayout());
        p.add("North", buttons);
        p.add("Center", wrapper);

        // add the main panel to the main frame
        f.add(p);
        // show the main frame
        f.setVisible(true);
        // set the size of the main frame
        f.setSize(500,500);
        // configure the behaviour of the exit button
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Update the TextArea with the content of the bst
     * @throws IOException
     * @param bst - the BinSearchTree to grab data from
     * @param t - the JTextArea to write the data to
     */
    public static void updateText (BinSearchTree bst, JTextArea t) throws IOException {
        // a buffer for the printwriter to write to
        StringWriter buf = new StringWriter();
        // the printwriter required by BinSearchTree.print_tree
        PrintWriter writer = new PrintWriter(buf);
        bst.print_tree(bst.root, writer);
        // write the contents of the buffer to the TextArea
        t.setText(buf.toString());
    }
}
