package edu.westga.cs1302.babynamesgui.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import edu.westga.cs1302.babynamesgui.model.BabyKey;
import edu.westga.cs1302.babynamesgui.model.BabyNameRecord;
import edu.westga.cs1302.babynamesgui.model.GenderType;

/**
 * The class record display this is used to format the key and value of the hash
 * map and display it properly in the list view on the GUI
 * 
 * @version Spring 2024
 * @author Colby
 */

public class RecordDisplay implements List<String> {

	private ArrayList<String> formattedRecords;

	/**
	 * Instantiates a record for display
	 */

	public RecordDisplay() {
		this.formattedRecords = new ArrayList<String>();
	}

	/**
	 * Formats the record for display
	 * 
	 * @param record the specific record to format
	 */
	public void format(BabyNameRecord record) {
		this.formattedRecords.clear();
		String formattedRecord = "";

		ArrayList<BabyKey> girls = new ArrayList<BabyKey>();

		ArrayList<BabyKey> boys = new ArrayList<BabyKey>();

		for (BabyKey key : record.keySet()) {
			if (key.getGender() == GenderType.FEMALE) {
				girls.add(key);
			} else if (key.getGender() == GenderType.MALE) {
				boys.add(key);
			}
		}
		Collections.sort(girls);
		Collections.sort(boys);
		girls.addAll(boys);
		for (BabyKey key : girls) {
			formattedRecord = key.toString() + record.get(key).toString();
			this.formattedRecords.add(formattedRecord);
		}
	}

	/**
	 * Formats the record display by comparing the keys by name and year
	 * 
	 * @param record the record to format
	 */

	public void formatByNameYear(BabyNameRecord record) {
		String formattedRecord = "";
		ArrayList<BabyKey> girls = new ArrayList<BabyKey>();
		ArrayList<BabyKey> boys = new ArrayList<BabyKey>();
		record.keySet().forEach(key -> {
			if (key.getGender() == GenderType.FEMALE) {
				girls.add(key);
			} else if (key.getGender() == GenderType.MALE) {
				boys.add(key);
			}
		});
		Collections.sort(girls, new Comparator<BabyKey>() {

			@Override
			public int compare(BabyKey key1, BabyKey key2) {
				int yearComparison = key1.getYear() - key2.getYear();
				if (key1.getYear() - key2.getYear() != 0) {
					return yearComparison;
				} else {
					return key1.getName().compareTo(key2.getName());
				}
			}

		});
		Collections.sort(boys, new Comparator<BabyKey>() {

			@Override
			public int compare(BabyKey key1, BabyKey key2) {
				int yearComparison = key1.getYear() - key2.getYear();
				if (key1.getYear() - key2.getYear() != 0) {
					return yearComparison;
				} else {
					return key1.getName().compareTo(key2.getName());
				}
			}

		});
		girls.addAll(boys);
		for (BabyKey key : girls) {
			formattedRecord = key.toString() + record.get(key).toString();
			this.formattedRecords.add(formattedRecord);
		}
	}

	/**
	 * Formats the record display by comparing the record by year and frequency
	 * 
	 * @param record the record to format
	 */

	public void formatByYearFrequency(BabyNameRecord record) {
		String formattedRecord = "";
		ArrayList<BabyKey> girls = new ArrayList<BabyKey>();
		ArrayList<BabyKey> boys = new ArrayList<BabyKey>();
		record.keySet().forEach(key -> {
			if (key.getGender() == GenderType.FEMALE) {
				girls.add(key);
			} else if (key.getGender() == GenderType.MALE) {
				boys.add(key);
			}
		});
		Collections.sort(girls, new Comparator<BabyKey>() {

			@Override
			public int compare(BabyKey key1, BabyKey key2) {
				int yearComparison = key1.getYear() - key2.getYear();
				int frequencyComparison = record.get(key1).getFrequency() - record.get(key2).getFrequency();
				if (yearComparison < 0) {
					return 1;
				} else if (yearComparison > 0) {
					return -1;
				} else {
					return -(frequencyComparison);
				}
			}

		});
		Collections.sort(boys, new Comparator<BabyKey>() {

			@Override
			public int compare(BabyKey key1, BabyKey key2) {
				int yearComparison = key1.getYear() - key2.getYear();
				int frequencyComparison = record.get(key1).getFrequency() - record.get(key2).getFrequency();
				if (yearComparison < 0) {
					return 1;
				} else if (yearComparison > 0) {
					return -1;
				} else {
					return -(frequencyComparison);
				}
			}

		});
		girls.addAll(boys);
		for (BabyKey key : girls) {
			formattedRecord = key.toString() + record.get(key).toString();
			this.formattedRecords.add(formattedRecord);
		}
	}

	/**
	 * Formats the record display to a given year in a way that the records with the
	 * highest frequencies are at the top of the list
	 * 
	 * @param record the record to format
	 * @param year   the specified year to display
	 */

	public void formatByYear(BabyNameRecord record, int year) {
		this.formattedRecords.clear();
		String formattedRecord = "";
		ArrayList<BabyKey> recordsList = new ArrayList<BabyKey>();
		record.keySet().forEach(key -> {
			if (key.getYear() == year) {
				recordsList.add(key);
			}

		});

		Collections.sort(recordsList, new Comparator<BabyKey>() {

			@Override
			public int compare(BabyKey o1, BabyKey o2) {

				return record.get(o2).getFrequency() - record.get(o1).getFrequency();
			}

		});
		for (BabyKey key : recordsList) {
			formattedRecord = key.toString() + record.get(key).toString();
			this.formattedRecords.add(formattedRecord);

		}

	}

	@Override
	public int size() {
		return this.formattedRecords.size();
	}

	@Override
	public boolean isEmpty() {
		return this.formattedRecords.isEmpty();
	}

	@Override
	public boolean contains(Object object) {
		return this.formattedRecords.contains(object);
	}

	@Override
	public Iterator<String> iterator() {
		return this.formattedRecords.iterator();
	}

	@Override
	public Object[] toArray() {
		return this.formattedRecords.toArray();
	}

	@Override
	public <T> T[] toArray(T[] array) {
		return this.formattedRecords.toArray(array);
	}

	@Override
	public boolean add(String line) {
		return this.formattedRecords.add(line);
	}

	@Override
	public boolean remove(Object object) {
		return this.formattedRecords.remove(object);
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		return this.formattedRecords.containsAll(collection);
	}

	@Override
	public boolean addAll(Collection<? extends String> collection) {
		return this.formattedRecords.addAll(collection);
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		return this.formattedRecords.removeAll(collection);
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		return this.formattedRecords.retainAll(collection);
	}

	@Override
	public void clear() {
		this.formattedRecords.clear();

	}

	@Override
	public boolean addAll(int index, Collection<? extends String> collection) {
		return this.formattedRecords.addAll(index, collection);
	}

	@Override
	public String get(int index) {
		return this.formattedRecords.get(index);
	}

	@Override
	public String set(int index, String element) {
		return this.formattedRecords.set(index, element);
	}

	@Override
	public void add(int index, String element) {
		this.formattedRecords.add(index, element);

	}

	@Override
	public String remove(int index) {
		return this.formattedRecords.remove(index);
	}

	@Override
	public int indexOf(Object object) {
		return this.formattedRecords.indexOf(object);
	}

	@Override
	public int lastIndexOf(Object object) {
		return this.formattedRecords.lastIndexOf(object);
	}

	@Override
	public ListIterator<String> listIterator() {
		return this.formattedRecords.listIterator();
	}

	@Override
	public ListIterator<String> listIterator(int index) {
		return this.formattedRecords.listIterator(index);
	}

	@Override
	public List<String> subList(int fromIndex, int toIndex) {
		return this.formattedRecords.subList(fromIndex, toIndex);
	}

}
