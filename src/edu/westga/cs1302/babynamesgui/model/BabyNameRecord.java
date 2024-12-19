package edu.westga.cs1302.babynamesgui.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The baby name records class which uses a hash map to represent the frequency
 * of baby names given the baby key object
 * 
 * @version Spring 2024
 * @author Colby
 */

public class BabyNameRecord {
	private Map<BabyKey, FrequencyValue> records;
	private String state;

	/**
	 * Instantiates a baby name record
	 */

	public BabyNameRecord() {
		this.records = new HashMap<BabyKey, FrequencyValue>();
	}

	/**
	 * Gets the state
	 * 
	 * @return the state
	 */

	public String getState() {
		return this.state;
	}

	/**
	 * Sets the name of the state
	 * 
	 * @precondition (state != null) && !(state.isEmpty())
	 * @param state the state to set the name to
	 */

	public void setState(String state) {
		if (state == null) {
			throw new IllegalArgumentException("state cannot be null");
		}
		if (state.isEmpty()) {
			throw new IllegalArgumentException("state cannot be blank");
		}
		this.state = state;
	}

	/**
	 * Gets the frequency Value
	 * 
	 * @param key the key associated with the value
	 * @return the value associated with the key
	 */

	public FrequencyValue get(BabyKey key) {
		return this.records.get(key);
	}

	/**
	 * Checks if the hash map contains a specific key
	 * 
	 * @param key the key to check for
	 * @return true or false based on the specified key
	 */

	public boolean containsKey(BabyKey key) {
		if (key == null) {
			throw new NullPointerException("key cannot be null");
		}
		return this.records.containsKey(key);
	}

	/**
	 * Checks if the hash map contains a specific value
	 * 
	 * @param value the value to check for
	 * @return true or false based on the specified value
	 */

	public boolean containsValue(FrequencyValue value) {
		if (value == null) {
			throw new NullPointerException("value cannot be null");
		}
		return this.records.containsValue(value);

	}

	/**
	 * Clears the hash map of all data
	 */

	public void clear() {
		this.records.clear();

	}

	/**
	 * Gets a set of all keys in the hash map
	 * 
	 * @return a set of all keys
	 */

	public Set<BabyKey> keySet() {
		return this.records.keySet();
	}

	/**
	 * Gets the collection of values from the hash map
	 * 
	 * @return the collection of values
	 */

	public Collection<FrequencyValue> values() {
		return this.records.values();

	}

	/**
	 * Puts a key and value in the hash map
	 * 
	 * @param key   the key to put into the hash map
	 * @param value the value to put into the hash map
	 * @return the value associated with the key or null if there was no previous
	 *         associated value
	 */

	public FrequencyValue put(BabyKey key, FrequencyValue value) {
		if (value == null) {
			throw new NullPointerException("value cannot be null");
		}
		if (key == null) {
			throw new NullPointerException("key cannot be null");
		}
		return this.records.put(key, value);

	}

	/**
	 * Puts all keys and associated values in the map
	 * 
	 * @precondition map != null
	 * @postcondition this.records.equals(map)
	 * @param map the map to copy all keys and values from
	 */

	public void putAll(Map<BabyKey, FrequencyValue> map) {
		if (map == null) {
			throw new NullPointerException("map cannot be null");
		}
		this.records.putAll(map);

	}

	/**
	 * Removes the value associated with the key
	 * 
	 * @param key - the key associated with the value you want to remove
	 * @return the previous value associated with the key before removal
	 */

	public FrequencyValue remove(BabyKey key) {
		if (key == null) {
			throw new NullPointerException("key cannot be null");
		}
		return this.records.remove(key);

	}

	/**
	 * Replace the value of a key
	 * 
	 * @param key   the specified key to change the value
	 * @param value the value associated with the specified key
	 * @return the previous value associated with the key before change
	 */

	public FrequencyValue replace(BabyKey key, FrequencyValue value) {
		if (value == null) {
			throw new NullPointerException("value cannot be null");
		}
		if (key == null) {
			throw new NullPointerException("key cannot be null");
		}
		return this.records.replace(key, value);
	}

}
