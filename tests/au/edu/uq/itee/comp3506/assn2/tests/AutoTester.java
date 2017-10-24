package au.edu.uq.itee.comp3506.assn2.tests;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import au.edu.uq.itee.comp3506.assn2.api.TestAPI;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.*;
import au.edu.uq.itee.comp3506.assn2.entities.ADTs.AbstractBinaryTree.*;


import au.edu.uq.itee.comp3506.assn2.entities.CallRecord;
import au.edu.uq.itee.comp3506.assn2.entities.FileReader;
import au.edu.uq.itee.comp3506.assn2.entities.Switch;

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
	ProbeHashMap<Integer, Integer> switchesMap;
	TreeMultiMap<Long, LocalDateTime, CallRecord> diallerMultiMap;
	TreeMultiMap<Long, LocalDateTime,CallRecord> receiverMultiMap;
	AvlTree<LocalDateTime, Switch> switchesTree;

	FileReader fileReader = new FileReader(FileReader.SWITCHES_FILE, "call-records-short.txt");


	public AutoTester()  {
		/* Read in data from data sets. */
		switchesMap = fileReader.getSwitchesMap();
		switchesTree = fileReader.getSwitchesTree();
		recordsTree = fileReader.getAllCallRecords();
		diallerMultiMap = fileReader.getDialerRecords();
		receiverMultiMap = fileReader.getReceiverRecords();
	}
	
	@Override
	public List<Long> called(long dialler) {

		AvlTree<LocalDateTime, CallRecord> tree = diallerMultiMap.getTree(dialler);
		List<Long> list = new ArrayList<>();
		if (tree == null) {
			return list;
		}

		Node<LocalDateTime, CallRecord> firstNode = tree.getFirst();
		Node<LocalDateTime, CallRecord> lastNode = tree.getLast();

		SinglyLinkedList<CallRecord> linkedList = tree.getList(firstNode.getKey(), lastNode.getKey());

		if (linkedList.length == 0) {
			return list;
		}

		list.add(linkedList.getFirst().getReceiver());
		while (linkedList.hasNext()) {
			list.add(linkedList.getNext().getReceiver());
		}

		return list;
	}


	@Override
	public List<Long> called(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
		AvlTree<LocalDateTime, CallRecord> tree = diallerMultiMap.getTree(dialler);
		List<Long> list = new ArrayList<>();

		SinglyLinkedList<CallRecord> linkedList = tree.getList(startTime, endTime);

		if (linkedList.length == 0) {
			return list;
		}

		list.add(linkedList.getFirst().getReceiver());
		while (linkedList.hasNext()) {
			list.add(linkedList.getNext().getReceiver());
		}


		return list;
	}

	@Override
	public List<Long> callers(long receiver) {
		AvlTree<LocalDateTime, CallRecord> tree = receiverMultiMap.getTree(receiver);
		List<Long> list = new ArrayList<>();

		if (tree == null) {
			return list;
		}

		Node<LocalDateTime, CallRecord> firstNode = tree.getFirst();
		Node<LocalDateTime, CallRecord> lastNode = tree.getLast();

		SinglyLinkedList<CallRecord> linkedList = tree.getList(firstNode.getKey(), lastNode.getKey());


		if (linkedList.length == 0) {
			return list;
		}
		list.add(linkedList.getFirst().getDialler());
		while (linkedList.hasNext()) {
			list.add(linkedList.getNext().getDialler());
		}

		return list;
	}

	@Override
	public List<Long> callers(long receiver, LocalDateTime startTime, LocalDateTime endTime) {
		AvlTree<LocalDateTime, CallRecord> tree = receiverMultiMap.getTree(receiver);
		List<Long> list = new ArrayList<>();

		if (tree == null) {
			return list;
		}

		SinglyLinkedList<CallRecord> linkedList = tree.getList(startTime, endTime);

		if (linkedList.length == 0) {
			return list;
		}
		list.add(linkedList.getFirst().getDialler());
		while (linkedList.hasNext()) {
			list.add(linkedList.getNext().getDialler());
		}
		return list;
	}

	@Override
	public List<Integer> findConnectionFault(long dialler) {
		AvlTree<LocalDateTime, CallRecord> tree = diallerMultiMap.getTree(dialler);
		List<Integer> list = new ArrayList<>();


		if (tree == null) {
			return list;
		}

		Node<LocalDateTime, CallRecord> firstNode = tree.getFirst();
		Node<LocalDateTime, CallRecord> lastNode = tree.getLast();

		SinglyLinkedList<CallRecord> linkedList = tree.getList(firstNode.getKey(), lastNode.getKey());

		if (linkedList.length == 0) {
			return list;
		}

		linkedList.getFirst();
		do {
			int fault = linkedList.getElement().getFault();
			if (fault != -1) {
				list.add(fault);
				fault = 0;
			}
		} while (linkedList.getNext() != null);

		return list;
	}

	@Override
	public List<Integer> findConnectionFault(long dialler, LocalDateTime startTime, LocalDateTime endTime) {
		AvlTree<LocalDateTime, CallRecord> tree = diallerMultiMap.getTree(dialler);
		List<Integer> list = new ArrayList<>();


		if (tree == null) {
			return list;
		}

		Node<LocalDateTime, CallRecord> firstNode = tree.getFrom(startTime);
		Node<LocalDateTime, CallRecord> lastNode = tree.getFrom(endTime);

		SinglyLinkedList<CallRecord> linkedList = tree.getList(firstNode.getKey(), lastNode.getKey());

		if (linkedList.length == 0) {
			return list;
		}

		linkedList.getFirst();
		do {
			int fault = linkedList.getElement().getFault();
			if (fault != -1) {
				list.add(fault);
				fault = 0;
			}
		} while (linkedList.getNext() != null);

		return list;
	}

	@Override
	public List<Integer> findReceivingFault(long reciever) {
		AvlTree<LocalDateTime, CallRecord> tree = receiverMultiMap.getTree(reciever);
		List<Integer> list = new ArrayList<>();


		if (tree == null) {
			return list;
		}

		Node<LocalDateTime, CallRecord> firstNode = tree.getFirst();
		Node<LocalDateTime, CallRecord> lastNode = tree.getLast();

		SinglyLinkedList<CallRecord> linkedList = tree.getList(firstNode.getKey(), lastNode.getKey());

		if (linkedList.length == 0) {
			return list;
		}

		linkedList.getFirst();
		do {
			int fault = linkedList.getElement().getFault();
			if (fault != -1) {
				list.add(fault);
			}
		} while (linkedList.getNext() != null);

		return list;
	}

	@Override
	public List<Integer> findReceivingFault(long reciever, LocalDateTime startTime, LocalDateTime endTime) {

		AvlTree<LocalDateTime, CallRecord> tree = receiverMultiMap.getTree(reciever);
		List<Integer> list = new ArrayList<>();

		if (tree == null) {
			return list;
		}

		SinglyLinkedList<CallRecord> linkedList = tree.getList(startTime, endTime);

		if (linkedList.length == 0) {
			return list;
		}

		linkedList.getFirst();

		do {
			int fault = linkedList.getElement().getFault();
			if (fault != -1) {
				list.add(fault);
			}
		} while (linkedList.hasNext());

		return list;
	}


	@Override
	public int maxConnections() {
		AvlTree<LocalDateTime, CallRecord> tree = recordsTree;
		ProbeHashMap<Integer, Integer> map = switchesMap;
		ProbeHashMap<Integer, Switch> tempMap = new ProbeHashMap<>(map.size());


		/* Temp switch */
		Switch leadingSwitch = null;

		if (tree == null) {
			return 0;
		}

		Node<LocalDateTime, CallRecord> firstNode = tree.getFirst();
		Node<LocalDateTime, CallRecord> lastNode = tree.getLast();

		SinglyLinkedList<CallRecord> linkedList = tree.getList(firstNode.getKey(), lastNode.getKey());

		if (linkedList.length == 0) {
			return 0;
		}

		linkedList.getFirst();

		Switch s;
		CallRecord cr;

		do {
			cr = linkedList.getElement();
			for (int i : cr.getConnectionPath()) {
				if (!tempMap.contains(i)) {
					s = new Switch(i);
					tempMap.put(i, s);
				} else {
					s = tempMap.get(i);
				}

				s.incrementCount();

				if (leadingSwitch == null) {
					leadingSwitch = s;
					continue;
				}

				if (s.getCount() == leadingSwitch.getCount()) {
					leadingSwitch = s.getId() < leadingSwitch.getId() ? s : leadingSwitch;
				} else if (s.getCount() > leadingSwitch.getCount()) {
					leadingSwitch = s;
				}
			}
		} while (linkedList.getNext() != null);

		return leadingSwitch.getId();
	}

	@Override
	public int maxConnections(LocalDateTime startTime, LocalDateTime endTime) {
		AvlTree<LocalDateTime, CallRecord> tree = recordsTree;
		ProbeHashMap<Integer, Integer> map = switchesMap;
		ProbeHashMap<Integer, Switch> tempMap = new ProbeHashMap<>(map.size());


		/* Temp switch */
		Switch leadingSwitch = null;

		if (tree == null) {
			return 0;
		}

		SinglyLinkedList<CallRecord> linkedList = tree.getList(startTime, endTime);

		if (linkedList.length == 0) {
			return 0;
		}

		linkedList.getFirst();

		Switch s;
		CallRecord cr;

		do {
			cr = linkedList.getElement();
			for (int i : cr.getConnectionPath()) {
				if (!tempMap.contains(i)) {
					s = new Switch(i);
					tempMap.put(i, s);
				} else {
					s = tempMap.get(i);
				}

				s.incrementCount();

				if (leadingSwitch == null) {
					leadingSwitch = s;
					continue;
				}

				if (s.getCount() == leadingSwitch.getCount()) {
					leadingSwitch = s.getId() < leadingSwitch.getId() ? s : leadingSwitch;
				} else if (s.getCount() > leadingSwitch.getCount()) {
					leadingSwitch = s;
				}
			}
		} while (linkedList.getNext() != null);

		return leadingSwitch.getId();
	}

	@Override
	public int minConnections() {
		AvlTree<LocalDateTime, CallRecord> tree = recordsTree;
		ProbeHashMap<Integer, Integer> map = switchesMap;
		ProbeHashMap<Integer, Switch> tempMap = new ProbeHashMap<>(map.size());


		/* Temp switch */
		Switch leadingSwitch = null;

		if (tree == null) {
			return 0;
		}

		Node<LocalDateTime, CallRecord> firstNode = tree.getFirst();
		Node<LocalDateTime, CallRecord> lastNode = tree.getLast();

		SinglyLinkedList<CallRecord> linkedList = tree.getList(firstNode.getKey(), lastNode.getKey());

		if (linkedList.length == 0) {
			return 0;
		}

		linkedList.getFirst();

		Switch s;
		CallRecord cr;

		do {
			cr = linkedList.getElement();
			for (int i : cr.getConnectionPath()) {
				if (!tempMap.contains(i)) {
					s = new Switch(i);
					tempMap.put(i, s);
				} else {
					s = tempMap.get(i);
				}

				s.incrementCount();

				if (leadingSwitch == null) {
					leadingSwitch = s;
					continue;
				}

				if (s.getCount() == leadingSwitch.getCount()) {
					leadingSwitch = s.getId() < leadingSwitch.getId() ? s : leadingSwitch;
				} else if (s.getCount() < leadingSwitch.getCount()) {
					leadingSwitch = s;
				}
			}
		} while (linkedList.getNext() != null);

		return leadingSwitch.getId();
	}

	@Override
	public int minConnections(LocalDateTime startTime, LocalDateTime endTime) {
		AvlTree<LocalDateTime, CallRecord> tree = recordsTree;
		ProbeHashMap<Integer, Integer> map = switchesMap;
		ProbeHashMap<Integer, Switch> tempMap = new ProbeHashMap<>(map.size());


		/* Temp switch */
		Switch leadingSwitch = null;

		if (tree == null) {
			return 0;
		}

		SinglyLinkedList<CallRecord> linkedList = tree.getList(startTime, endTime);

		if (linkedList.length == 0) {
			return 0;
		}

		linkedList.getFirst();

		Switch s;
		CallRecord cr;

		do {
			cr = linkedList.getElement();
			for (int i : cr.getConnectionPath()) {
				if (!tempMap.contains(i)) {
					s = new Switch(i);
					tempMap.put(i, s);
				} else {
					s = tempMap.get(i);
				}

				s.incrementCount();

				if (leadingSwitch == null) {
					leadingSwitch = s;
					continue;
				}

				if (s.getCount() == leadingSwitch.getCount()) {
					leadingSwitch = s.getId() < leadingSwitch.getId() ? s : leadingSwitch;
				} else if (s.getCount() < leadingSwitch.getCount()) {
					leadingSwitch = s;
				}
			}
		} while (linkedList.getNext() != null);

		return leadingSwitch.getId();
	}

	@Override
	public List<CallRecord> callsMade(LocalDateTime startTime, LocalDateTime endTime) {

		List<CallRecord> list = new ArrayList<>();

		SinglyLinkedList<CallRecord> linkedList = recordsTree.getList(startTime, endTime);

		if (linkedList.length == 0) {
			return list;
		}

		list.add(linkedList.getFirst());
		while (linkedList.hasNext()) {
			list.add(linkedList.getNext());
		}

		return list;
	}
	
	public static void main(String[] args) {
		AutoTester test = new AutoTester();
	}

}