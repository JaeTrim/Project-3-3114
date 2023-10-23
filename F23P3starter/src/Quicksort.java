/**
 * {Project Description Here}
 */

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The class containing the main method.
 *
 * @author {Your Name Here}
 * @version {Put Something Here}
 */

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class Quicksort {

    /**
     * This method is used to generate a file of a certain size, containing a
     * specified number of records.
     *
     * @param filename the name of the file to create/write to
     * @param blockSize the size of the file to generate
     * @param format the format of file to create
     * @throws IOException throw if the file is not open and proper
     */
    public static void generateFile(String filename, String blockSize,
        char format) throws IOException {
        FileGenerator generator = new FileGenerator();
        String[] inputs = new String[3];
        inputs[0] = "-" + format;
        inputs[1] = filename;
        inputs[2] = blockSize;
        generator.generateFile(inputs);
    }


    /**
     * @param args
     *      Command line parameters.
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // This is the main file for the program.
        generateFile("input.txt", "10", 'a');
        //BufferPool pool = new BufferPool(new RandomAccessFile(args[0], "rw"), Integer.parseInt(args[1]));
        new Sort(new RandomAccessFile(args[0], "rw"), Integer.parseInt(args[1]));
        //sorter.quicksort(pool.getArr(), 0, pool.getArr().length / 4 - 1);
        //pool.write(pool.getArr());
        CheckFile check = new CheckFile();
        Boolean res = check.checkFile("input.txt");
        System.out.println(res);
        
    }
}
