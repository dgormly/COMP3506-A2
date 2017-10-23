package au.edu.uq.itee.comp3506.assn2.tests;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import au.edu.uq.itee.comp3506.assn2.api.TestAPI;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.*;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractBinaryTree.*;


import au.edu.uq.itee.comp3506.assn2.entities.CallRecord;
import au.edu.uq.itee.comp3506.assn2.entities.FileReader;

/**
 * Hook class used by automated testing tool.
 * The testing tool will instantiate an object of this class to test the functionality of your assignment.
 * You must implement the method and constructor stubs below so that they call the necessary code in your application.
 * 
 * @author Daniel Gormly: 43503348
 */
public final class AutoTester implements TestAPI {
	// TODO Provide any data members required for the methods below to work correctly with your application.

	/* Data sets. */
	AvlTree<LocalDateTime, CallRecord> recordsTree;
	AbstractMap<Integer, Integer> switchesMap;
	TreeMultiMap<Long, LocalDateTime, CallRecord> diallerMultiMap;
	TreeMultiMap<Long, LocalDateTime,CallRecord> receiverMultiMap;

	FileReader fileReader = new FileReader(FileReader.SWITCHES_FILE, "call-records-short.txt");


	public AutoTester()  {
		/* Read in data from data sets. */
		switchesMap = fileReader.getSwitchesMap();
		recordsTree = fileReader.getAllCallRecords();
		diallerMultiMap = fileReader.getDialerRecords();
	}
	
	@Override
	public List<Long> called(long dialler) {

		AvlTree<LocalDateTime, CallRecord> tree = diallerMultiMap.getTree(dialler);
		List<Long> list = new ArrayList<>();
		System.out.println(recordsTree.size());
		System.out.println(switchesMap.size());

		if (tree == null) {
			return list;
		}

		Node<LocalDateTime, CallRecord> firstNode = tree.getFirst();
		Node<LocalDateTime, CallRecord> lastNode = tree.getLast();

		while (firstNode != lastNode) {
			list.add(firstNode.getElement().getReceiver());
			firstNode = firstNode.getNext(firstNode);
		}

		list.add(tree.getLast().getElement().getReceiver());

		return list;
	}


	@Override
	public List<Long> called(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
		AvlTree<LocalDateTime, CallRecord> tree = diallerMultiMap.getTree(dialler);
		List<Long> list = new ArrayList<>();

		Node<LocalDateTime, CallRecord> firstNode = tree.getFrom(startTime);
		Node<LocalDateTime, CallRecord> lastNode = tree.getTo(endTime);


		while (firstNode != lastNode) {
			list.add(firstNode.getElement().getReceiver());
			firstNode = firstNode.getNext(firstNode);
		}

		return list;
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

		return null;
	}
	
	public static void main(String[] args) {
		AutoTester test = new AutoTester();
		LocalDateTime start = LocalDateTime.parse("2017-09-07T03:04:55.529");
		LocalDateTime end = LocalDateTime.parse("2017-09-16T16:40:29.461");

		System.out.println(test.recordsTree.size());

		List<Long> receivers = test.called(Long.parseLong("5618941102"));


		System.out.println("List size: " + receivers.size());
		for (Long receiver : receivers) {
			System.out.println(receiver);
		}

		//receivers = test.called(Long.parseLong("5618941102"), start, end);
	}

}