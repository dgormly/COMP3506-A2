package au.edu.uq.itee.comp3506.assn2.tests;

import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractMap;
import au.edu.uq.itee.comp3506.assn2.entities.FileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileReaderTest {
    FileReader fr;

    @Before
    public void setup() {
        fr = new FileReader();
    }


    /**
     * Tests if all switches are being successfully inserted into the map.
     */
    @Test
    public void readSwitchesTest() {
        fr.importSwitches(FileReader.SWITCHES_FILE);
        assertEquals("Wrong map size found.", fr.getSwitchesMap().size(), 1000);
    }

    @Test
    public void parseRecordsTest() {
        try {
            fr.importSwitches(FileReader.SWITCHES_FILE);
            List<String> records = FileReader.readFile("corruptRecords.txt");
            for (String line : records) {
                assertEquals("Expected null", fr.isValidRecord(line), null);
            }

        } catch (IOException e) {
            System.out.println("Failed to load test file.");
            e.printStackTrace();
        }
    }

    @Test
    public void allRecordsTest() {

    }


}
