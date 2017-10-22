package au.edu.uq.itee.comp3506.assn2.entities;

import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractBinaryTree;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractMap;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AvlTree;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.ProbeHashMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    /* TODO this needs to be changed to not use an ArrayList and should be implemented in the AutoTester.
     */

    private final static String RECORD_FILE = "call-records.txt";
    private final static String SWITCHES_FILE = "switches.txt";



    /**
     * Reads in all records and converts them to a CallRecord Object.
     *
     * Runtime: O(n)
     *
     * @return List of Call Records.
     */
    public static AbstractBinaryTree<LocalDateTime, CallRecord> readRecords() {
        /* Read in data from data sets. */
        List<String> inputLines = new ArrayList<>();
        AbstractBinaryTree<LocalDateTime, CallRecord> records = new AvlTree<>();

        try {
            inputLines = readFile(RECORD_FILE);
        } catch (IOException e) {
            System.err.println("File failed to read.");
            e.printStackTrace();
        }

        // Pass into Call records.
        for (String line : inputLines) {
            String[] variables = line.split(" ");

            long dialer = Long.parseLong(variables[0]);
            long receiver = Long.parseLong(variables[variables.length - 2]);
            int diallerSwitch = Integer.parseInt(variables[1]);
            int receiverSwitch = Integer.parseInt(variables[variables.length - 3]);

            List<Integer> path = new ArrayList<>();
            for (int i = 2; i < variables.length - 2; i++) {
                if (variables[i].equals("")) {
                    continue;
                }

                int switchId = Integer.parseInt(variables[i]);
                path.add(switchId);
            }

            LocalDateTime stamp = LocalDateTime.parse(variables[variables.length - 1]);

            CallRecord cr = new CallRecord(dialer, receiver, diallerSwitch, receiverSwitch, path, stamp);
        }
        return records;
    }


    /**
     * Reads in the switches.txt file.
     *
     * Runtime: O(n)
     *
     * @return List of switch identifiers.
     */
    public static AbstractMap<Integer, Integer> readSwitches() {
        /* Read in data from data sets. */
        List<String> inputLines = new ArrayList<>();
        AbstractMap<Integer, Integer> switches = new ProbeHashMap<>();

        try {
            inputLines = readFile(SWITCHES_FILE);
        } catch (IOException e) {
            System.err.println("File failed to read.");
            e.printStackTrace();
        }

        for (String line : inputLines) {
            switches.put(Integer.parseInt(line), Integer.parseInt(line));
        }

        switches.remove(0);

        return switches;
    }


    /**
     * Reads each line in the given textfile name from data folder into an List.
     *
     * Runtime: O(n) time.
     *
     * @param fileName, The name of the textfile to be read from the data folder.
     * @return Each line of a file in a list.
     * @throws IOException,
     * 		Throws IOException if the file cannot be found.
     */
    private static List<String> readFile(String fileName) throws IOException {
        List<String> lineArray = new ArrayList<>();

        java.io.FileReader fr = new java.io.FileReader("data/" + fileName);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            lineArray.add(line);
        }
        br.close();
        fr.close();
        return lineArray;
    }

}
