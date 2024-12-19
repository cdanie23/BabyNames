package edu.westga.cs1302.babynamesgui.model;

/**
 * The frequency value class which represents a frequency value used in the hash
 * map with a corresponding key
 * 
 * @version Spring 2024
 * @author Colby
 */

public class FrequencyValue {
	private int frequency;

	/**
	 * Creates an instance of a frequency
	 * 
	 * @precondition (frequency >= 0) && (state != null) && !(state.isEmpty())
	 * @postcondition this.frequency == frequency && this.state.equals(state)
	 * @param frequency the frequency
	 *
	 */
	public FrequencyValue(int frequency) {

		if (frequency < 0) {
			throw new IllegalArgumentException("frequency cannot be negative");
		}
		this.frequency = frequency;

	}

	/**
	 * Gets the frequency
	 * 
	 * @return the frequency
	 */

	public int getFrequency() {
		return this.frequency;
	}

	/**
	 * Sets the frequency
	 * 
	 * @precondition frequency >= 0
	 * @param frequency the frequency to set
	 */

	public void setFrequency(int frequency) {
		if (frequency < 0) {
			throw new IllegalArgumentException("frequency cannot be negative");
		}
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return " " + this.frequency;
	}
}
