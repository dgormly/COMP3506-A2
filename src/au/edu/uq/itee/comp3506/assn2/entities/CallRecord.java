package au.edu.uq.itee.comp3506.assn2.entities;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Represents a single phone call connection attempt.
 * 
 * @author Richard Thomas
 *
 */
public class CallRecord {
	private long dialler;
	private long receiver;
	private int diallerSwitch;
	private int receiverSwitch;
	private List<Integer> connectionPath;
	private LocalDateTime timeStamp;

	/**
	 * @param dialler The phone number that initiated the call.
	 * @param receiver The phone number that should have received the call.
	 * @param diallerSwitch The identifier of the switch to which the dialler was connected.
	 * @param receiverSwitch The identifier of the switch to which the receiver was connected.
	 * @param connectionPath List of all the identifiers of the switches through which the call was routed. 
	 * @param timeStamp The date and time at which the call was initiated.
	 */
	public CallRecord(long dialler, long receiver,
	                  int diallerSwitch, int receiverSwitch,
	                  List<Integer> connectionPath, LocalDateTime timeStamp) {
		this.dialler = dialler;
		this.receiver = receiver;
		this.diallerSwitch = diallerSwitch;
		this.receiverSwitch = receiverSwitch;
		this.connectionPath = connectionPath;
		this.timeStamp = timeStamp;
	}


	public CallRecord(String line) {
		String[] variables = line.split(" ");

		// TODO finish implementing this first!
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

		// Map to local variables.
		this.dialler = dialer;
		this.receiver = receiver;
		this.diallerSwitch = diallerSwitch;
		this.receiverSwitch = receiverSwitch;
		this.connectionPath = path;
		this.timeStamp = stamp;
	}

	public long getDialler() {
		return dialler;
	}

	public long getReceiver() {
		return receiver;
	}

	public int getDiallerSwitch() {
		return diallerSwitch;
	}

	public int getReceiverSwitch() {
		return receiverSwitch;
	}

	public List<Integer> getConnectionPath() {
		return connectionPath;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * 
	 * @param switchID The identifier of a switch.
	 * @return true if switchID is in the connection path of the call route; false otherwise.
	 */
	public boolean hasSwitch(int switchID) {
		return connectionPath.indexOf(switchID) != -1;
	}

	/**
	 * Simple string representation of the call record.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CallRecord [dialler=" + dialler + ", receiver=" + receiver + ", diallerSwitch=" + diallerSwitch
				+ ", receiverSwitch=" + receiverSwitch + ", connectionPath=" + connectionPath + ", timeStamp="
				+ timeStamp + "]";
	}
}