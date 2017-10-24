package au.edu.uq.itee.comp3506.assn2.entities;

import au.edu.uq.itee.comp3506.assn2.entities.ADTs.*;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class FileReader {

    public final static String RECORD_FILE = "call-records.txt";
    public final static String SWITCHES_FILE = "switches.txt";

    private AvlTree<LocalDateTime, CallRecord> recordsTree = new AvlTree<>();

    private TreeMultiMap<Long, LocalDateTime, CallRecord> dialerRecords = new TreeMultiMap<>();
    private TreeMultiMap<Long, LocalDateTime, CallRecord> receiverRecords = new TreeMultiMap<>();
    private ProbeHashMap<Integer, Integer> switchesMap = new ProbeHashMap<>();
    private AvlTree<LocalDateTime, Switch> switchesTree = new AvlTree<>();


    private int crErrors = 0;

    /**
     * Constructor:
     *
     * Reads files in and build data structures.
     */
    public FileReader(String switchesPath, String recordsPath) {
        importSwitches(switchesPath);
        importRecords(recordsPath);
    }


    /**
     * Empty constructor used for testing.
     */
    public FileReader() {

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
                    dialerRecords.put(cr.getDialler(), cr.getTimeStamp(), cr);
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
     * Runtime Efficiency:
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


            if (variables[0].length() != 10) {
                return null;
            }

            if (variables[variables.length - 2].length() != 10) {
                return null;
            }


            for (int i = 2; i < variables.length - 3; i++) {
                path.add(Integer.parseInt(variables[i]));
            }

            CallRecord cr = new CallRecord(dialer, receiver, diallerSwitch, receiverSwitch, path, stamp);

            // Count switches
            for (int i: cr.getConnectionPath()) {
                if (!switchesMap.contains(i)) {
                    return null;
                }
            }

            List<Integer> connectionPath = cr.getConnectionPath();
            int size = connectionPath.size();

            if (size == 1) {
                // Fault
                //System.out.println("1");
                return cr;
            }


            for (int i = 0; i < size - 1; i++) {
                if (connectionPath.get(i).equals(connectionPath.get(i + 1))) {
                    //System.out.println("4");
                    return null;
                }
            }


            return cr;

        } catch (Exception e) {
            //System.err.println("Error found: ");
            e.printStackTrace();
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
            int mapSize = (int) Math.floor(Integer.parseInt(line) * 1.25);
            switchesMap = new ProbeHashMap<>(mapSize);

            while ((line = br.readLine()) != null) {
                int switchId = Integer.parseInt(line);
                if (!switchesMap.contains(switchId)) {
                    switchesMap.put(switchId, switchId);
                }
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


    /**
     * Returns a Map containing all switches loaded in from given switch file.
     *
     * Key: Switch's uniquie ID
     *
     * Value: Custom call record containing switch information.
     *
     * @return
     *      Linearly probing hash map of switches.
     */
    public ProbeHashMap<Integer, Integer> getSwitchesMap() {
        return switchesMap;
    }


    /**
     * Returns a Linearly probing multi-map containing a tree holding the Timestamp
     * call record information sorted by receiver.
     *
     *
     * @return
     *      MultiMap:
     *      Key 1 =  Dialler number
     *      Key 2 = Time Stamp
     *      Value = Corresponding call record.
     *
     */
    public TreeMultiMap<Long, LocalDateTime, CallRecord> getDialerRecords() {
        return dialerRecords;
    }


    /**
     * Returns a Linearly probing multi-map containing a tree holding the Timestamp
     * call record information sorted by receiver.
     *
     *
     * @return
     *      MultiMap:
     *      Key 1 =  Dialler number
     *      Key 2 = Time Stamp
     *      Value = Corresponding call record.
     *
     */
    public TreeMultiMap<Long, LocalDateTime, CallRecord> getReceiverRecords() {
        return receiverRecords;
    }


    /**
     * AVL tree containing all records found in the given records file sorted by Time Stamp.
     * @return
     *      Data tree,
     *      Null if no records found.
     */
    public AvlTree<LocalDateTime, CallRecord> getAllCallRecords() {
        return recordsTree;
    }


    /**
     * AVL tree containing all switches found in the given records file sorted by time stamp.
     *
     * @return
     *  Data tree,
     *  Null if no switch found.
     */
    public AvlTree<LocalDateTime, Switch> getSwitchesTree() {
        return switchesTree;
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
