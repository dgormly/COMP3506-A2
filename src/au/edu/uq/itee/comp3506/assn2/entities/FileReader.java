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
import java.util.Map;

public class FileReader {

    /* TODO this needs to be changed to not use an ArrayList and should be implemented in the AutoTester.
     */

    private final static String RECORD_FILE = "call-records.txt";
    private final static String SWITCHES_FILE = "switches.txt";

    private AbstractBinaryTree<LocalDateTime, CallRecord> recordsTree;
    private AbstractBinaryTree<LocalDateTime, CallRecord> invalidRecords;
    private AbstractBinaryTree<LocalDateTime, CallRecord> validRecords;

    private AbstractMap<Integer, CallRecord> dialerRecords;
    private AbstractMap<Integer, CallRecord> recieverRecords;

    private ProbeHashMap<Integer, Integer>  switchesMap;

    public FileReader() {

        importSwitches();
        importRecords();
    }

    /**
     * Reads in all records and converts them to a CallRecord Object.
     *
     * Runtime: O(n)
     *
     * @return List of Call Records.
     */
    public void importRecords() {
        /* Read in data from data sets. */
        recordsTree = new AvlTree<>();

        dialerRecords = new ProbeHashMap<>();
        recieverRecords = new ProbeHashMap<>();

        int lineNum = 0;
        try {
            java.io.FileReader fr = new java.io.FileReader("data/" + RECORD_FILE);
            BufferedReader br = new BufferedReader(fr);


            String line;
            /* Parse Records. */
            while ((line = br.readLine()) != null) {
                // TODO validate records.
                lineNum++;
                String[] variables = line.split("\\s+");

                long dialer = Long.parseLong(variables[0]);
                long receiver = Long.parseLong(variables[variables.length - 2]);
                int diallerSwitch = Integer.parseInt(variables[1]);
                int receiverSwitch = Integer.parseInt(variables[variables.length - 3]);
                LocalDateTime stamp = LocalDateTime.parse(variables[variables.length - 1]);

                List<Integer> path = new ArrayList<>();
                for (int i = 2; i < variables.length - 2; i++) {
                    if (variables[i].equals("")) {
                        continue;
                    }

                    int switchId = Integer.parseInt(variables[i]);
                    path.add(switchId);
                }

                CallRecord cr = new CallRecord(dialer, receiver, diallerSwitch, receiverSwitch, path, stamp);
                recordsTree.add(stamp, cr);
            }

            br.close();
            fr.close();
        } catch (Exception e) {
            System.err.println("File failed to read.");
            System.out.println(lineNum);
            e.printStackTrace();
        }
    }


    /**
     * Reads in the switches.txt file.
     *
     * Runtime: O(n)
     *
     * @return List of switch identifiers.
     */
    public void importSwitches() {
        /* Read in data from data sets. */
        List<String> inputLines = new ArrayList<>();
        AbstractMap<Integer, Integer> switches = new ProbeHashMap<>();

        try {
            java.io.FileReader fr = new java.io.FileReader("data/" + SWITCHES_FILE);
            BufferedReader br = new BufferedReader(fr);

            String line;
            // First line == number of switches. Prevents resize.
            line = br.readLine();
            switchesMap = new ProbeHashMap<>();
            int mapSize = (int) Math.floor(Integer.parseInt(line) * 1.25);
            switchesMap = new ProbeHashMap<>(mapSize);
            while ((line = br.readLine()) != null) {
                switchesMap.put(Integer.parseInt(line), Integer.parseInt(line));
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println("File failed to read.");
            e.printStackTrace();
        }

        for (String line : inputLines) {
            switches.put(Integer.parseInt(line), Integer.parseInt(line));
        }
    }


    public AbstractMap<Integer, Integer> getSwitchesMap() {
        return switchesMap;
    }


    public AbstractBinaryTree<LocalDateTime, CallRecord> getAllCallRecords() {
        return recordsTree;
    }


    /* TODO remove this as it is no longer needed. */
//    /**
//     * Reads each line in the given textfile name from data folder into an List.
//     *
//     * Runtime: O(n) time.
//     *
//     * @param fileName, The name of the textfile to be read from the data folder.
//     * @return Each line of a file in a list.
//     * @throws IOException,
//     * 		Throws IOException if the file cannot be found.
//     */
//    private static List<String> readFile(String fileName) throws IOException {
//        List<String> lineArray = new ArrayList<>();
//
//        java.io.FileReader fr = new java.io.FileReader("data/" + fileName);
//        BufferedReader br = new BufferedReader(fr);
//
//        String line;
//        while ((line = br.readLine()) != null) {
//            lineArray.add(line);
//        }
//        br.close();
//        fr.close();
//        return lineArray;
//    }

}
