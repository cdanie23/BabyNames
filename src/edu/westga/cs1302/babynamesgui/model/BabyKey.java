package edu.westga.cs1302.babynamesgui.model;

import java.util.Objects;

/**
 * The class babyKey which is used to represent a key object for a hash map
 * 
 * @version Spring 2024
 * @author Colby
 */

public class BabyKey implements Comparable<BabyKey> {
	private String name;
	private GenderType gender;
	private int year;

	/**
	 * Instantiates an instance of a baby key to use for the hash map
	 * 
	 * @preconditions !(name.isEmpty()) && (name != null) && (gender != null) &&
	 *                (year >= 1910) && (year <= 2024)
	 * @postconditions this.name.equals(name) && (this.gender == gender) &&
	 *                 (this.year == year)
	 * @param name   - the name of the baby
	 * @param gender - the gender of the baby
	 * @param year   - the year the baby was born
	 */
	public BabyKey(String name, GenderType gender, int year) {
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty");
		}

		if (gender == null) {
			throw new IllegalArgumentException("gender cannot be null");
		}
		if (year < 1910) {
			throw new IllegalArgumentException("year must be after 1910");
		}
		if (year > 2024) {
			throw new IllegalArgumentException("year cannot be past 2024");
		}
		this.name = name;
		this.gender = gender;
		this.year = year;
	}

	/**
	 * Gets the name
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name
	 * 
	 * @preconditions (name != null) && !(name.isEmpty())
	 * @param name the name to set
	 */
	public void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name cannot be empty");
		}
		this.name = name;
	}

	/**
	 * Gets the gender
	 * 
	 * @return the gender
	 */
	public GenderType getGender() {
		return this.gender;
	}

	/**
	 * Sets the gender
	 * 
	 * @precondition gender != null
	 * @param gender the gender to set
	 */
	public void setGender(GenderType gender) {
		if (gender == null) {
			throw new IllegalArgumentException("gender cannot be null");
		}
		this.gender = gender;
	}

	/**
	 * Gets the year
	 * 
	 * @return the year
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * Sets the year
	 * 
	 * @precondition year >= 1910
	 * @param year the year to set
	 */
	public void setYear(int year) {
		if (year < 1910) {
			throw new IllegalArgumentException("year must be after 1910");
		}
		if (year > 2024) {
			throw new IllegalArgumentException("year must be before 2024");
		}
		this.year = year;
	}

	@Override
	public boolean equals(Object other) {
		BabyKey otherKey = (BabyKey) other;
		return (this.name.equals(otherKey.getName()) && this.gender == otherKey.getGender()
				&& this.year == otherKey.getYear());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name, this.gender, this.year);
	}

	@Override
	public String toString() {
		return this.name + ", " + this.gender.name().charAt(0) + ", " + this.year + ":";
	}

	@Override
	public int compareTo(BabyKey object) {
		return this.year - object.year;
	}

}
