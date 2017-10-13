package au.edu.uq.itee.comp3506.assn2.tests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import au.edu.uq.itee.comp3506.assn2.api.TestAPI;
import au.edu.uq.itee.comp3506.assn2.entities.CallRecord;

/**
 * Hook class used by automated testing tool.
 * The testing tool will instantiate an object of this class to test the functionality of your assignment.
 * You must implement the method and constructor stubs below so that they call the necessary code in your application.
 * 
 * @author 
 */
public final class AutoTester implements TestAPI {
	// TODO Provide any data members required for the methods below to work correctly with your application.

	/* Data sets. */
	List<CallRecord> records;

	public AutoTester()  {
		// TODO Create and initialise any objects required by the methods below.
		records = new ArrayList<>();			// TODO this is to change data type.


		/* Read in data from data sets. */
		List<String> inputLines = new ArrayList<>();
		try {
			inputLines = readFile("call-records-short.txt");
		} catch (IOException e) {
			System.err.println("File failed to read.");
			e.printStackTrace();
		}

		// Pass into Call records.
		for (String line : inputLines) {
			CallRecord cr = new CallRecord(line);
			records.add(cr);
		}

	}
	
	@Override
	public List<Long> called(long dialler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> called(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> callers(long receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> callers(long receiver, LocalDateTime startTime, LocalDateTime endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> findConnectionFault(long dialler) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> findConnectionFault(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> findReceivingFault(long reciever) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> findReceivingFault(long reciever, LocalDateTime startTime, LocalDateTime endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int maxConnections() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int maxConnections(LocalDateTime startTime, LocalDateTime endTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int minConnections() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int minConnections(LocalDateTime startTime, LocalDateTime endTime) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CallRecord> callsMade(LocalDateTime startTime, LocalDateTime endTime) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		AutoTester test = new AutoTester();
		
		System.out.println("AutoTester Stub");
	}


	/**
	 * Reads each line in the given textfile name from data folder into an List.
	 *
	 * Runs in O(n) time.
	 *
	 * @param fileName, The name of the textfile to be read from the data folder.
	 * @return Each line of a file in a list.
	 * @throws IOException,
	 * 		Throws IOException if the file cannot be found.
	 */
	private List<String> readFile(String fileName) throws IOException {
		List<String> lineArray = new ArrayList<>();

		FileReader fr = new FileReader("data/" + fileName);
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