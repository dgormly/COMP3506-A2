package au.edu.uq.itee.comp3506.assn2.entities;

import au.edu.uq.itee.comp3506.assn2.entities.ADTs.*;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractMap;
import com.sun.tools.javac.comp.Check;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class FileReader {

    /* TODO this needs to be changed to not use an ArrayList and should be implemented in the AutoTester.
     */

    public final static String RECORD_FILE = "call-records.txt";
    public final static String SWITCHES_FILE = "switches.txt";

    private AvlTree<LocalDateTime, CallRecord> recordsTree = new AvlTree<>();
    private AvlTree<LocalDateTime, CallRecord> invalidRecords = new AvlTree<>();

    private TreeMultiMap<Long, LocalDateTime, CallRecord> dialerRecords = new TreeMultiMap<>();
    private TreeMultiMap<Long, LocalDateTime, CallRecord> receiverRecords = new TreeMultiMap<>();
    private ProbeHashMap<Integer, Integer>  switchesMap = new ProbeHashMap<>();

    private int crErrors = 0;

    /**
     * Constructor:
     *
     * Reads files in and build data structures.
     */
    public FileReader(String switchesPath, String recordsPath) {
        importSwitches(switchesPath);
        importRecords(recordsPath);
        System.out.println("DONE");
    }


    public FileReader() {

    }


    public int getCrErrors() {
        return crErrors;
    }

    /**
     * Reads in all records and converts them to a CallRecord Object.
     *
     * Runtime: O(n)
     *
     * @return List of Call Records.
     */
    public void importRecords(String recordsPath) {

        try {
            java.io.FileReader fr = new java.io.FileReader("data/" + recordsPath);
            BufferedReader br = new BufferedReader(fr);

            String line;
            /* Parse Records. */
            while ((line = br.readLine()) != null) {
                CallRecord cr = isValidRecord(line);

                if (cr != null) {
                    recordsTree.add(cr.getTimeStamp(), cr);
                    dialerRecords.put(cr.getReceiver(), cr.getTimeStamp(), cr);
                    receiverRecords.put(cr.getReceiver(), cr.getTimeStamp(), cr);
                } else {
                    crErrors++;
                }
            }

            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            System.err.println("File failed to read.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    /**
     * Checks if the record is valid.
     * If it is faulty the record is added to a faulty tree.
     *
     * @param line
     *      Record to parse.
     *
     * @return
     *      Call record if String is valid.
     *      Null if record is invalid.
     *
     */
    public CallRecord isValidRecord(String line) {
        String[] variables = line.split("\\s+");

        try {
            long dialer = Long.parseLong(variables[0]);
            long receiver = Long.parseLong(variables[variables.length - 2]);
            int diallerSwitch = Integer.parseInt(variables[1]);
            int receiverSwitch = Integer.parseInt(variables[variables.length - 3]);
            LocalDateTime stamp = LocalDateTime.parse(variables[variables.length - 1]);
            List<Integer> path = new ArrayList<>();
            int length = variables.length;

//            // Corrupt
//            if (!variables[1].equals(variables[2]) || !variables[length - 3].equals(variables[length - 4])) {
//                return null;
//            }
//
//            if (variables[0].length() != 10 || variables[length - 2].length() != 10) {
//                return null;
//            }
//
//            if (variables.length < 6) {
//                return null;
//            }
//
//
//            for (int i = 2; i < variables.length - 2; i++) {
////                if (i > 2 && i < variables.length - 4) {
////                    // Corrupt
////                    if (variables[i].equals(variables[i + 1])) {
////                        return null;
////                    }
////                }
//
//                int switchId = Integer.parseInt(variables[i]);
//                // Corrupt: Check if in switches list.
//                if (switchesMap.get(switchId) == null) {
//                    return null;
//                }
//
//                path.add(switchId);
//            }


            CallRecord cr = new CallRecord(dialer, receiver, diallerSwitch, receiverSwitch, path, stamp);

            List<Integer> connectionPath = cr.getConnectionPath();
            int size = connectionPath.size();

            if (size < 2) {
                return null;
            }

            if (connectionPath.get(0) == cr.getDiallerSwitch()) {
                return null;
            }

            if (connectionPath.get(size - 1) == cr.getDiallerSwitch()) {
                return null;
            }

            for (int i = 1; i < size - 2; i++) {
                if (connectionPath.get(i) == connectionPath.get(i + 1)) {
                    return null;
                }
            }


            return cr;
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * Reads in the switches.txt file.
     *
     * Runtime: O(n)
     *
     * @return List of switch identifiers.
     */
    public void importSwitches(String switchPath) {
        /* Read in data from data sets. */
        List<String> inputLines = new ArrayList<>();
        AbstractMap<Integer, Integer> switches = new ProbeHashMap<>();

        try {
            java.io.FileReader fr = new java.io.FileReader("data/" + switchPath);
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


    public TreeMultiMap<Long, LocalDateTime, CallRecord> getDialerRecords() {
        return dialerRecords;
    }


    public TreeMultiMap<Long, LocalDateTime, CallRecord> getReceiverRecords() {
        return receiverRecords;
    }


    public AvlTree<LocalDateTime, CallRecord> getAllCallRecords() {
        return recordsTree;
    }


    /**
     * Reads each line in the given textfile name from data folder into an List.
     *
     * This is used for testing.
     *
     * Runtime: O(n) time.
     *
     * @param fileName, The name of the textfile to be read from the data folder.
     *
     * @return Each line of a file in a list.
     *
     * @throws IOException,
     * 		Throws IOException if the file cannot be found.
     */
    public static List<String> readFile(String fileName) throws IOException {
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
