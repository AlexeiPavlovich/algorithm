
package com.example;

import java.util.Iterator;

import org.junit.Test;

public class HashTableTest {

	@Test
	public void TestTable() {
		Table<String, Integer> table = new Table<>();

		table.put("1", 1);
		table.put("2", 2);
		table.put("3", 3);
		table.put("4", 4);
		table.put("5", 5);
		table.put("6", 6);
		table.put("7", 7);
		table.put("8", 8);
		table.put("9", 9);

		Table.MapIterator<String, Integer> iterator = table.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().value);
		}
		System.out.println("********");
		table.put("3", 30);
		table.put("4", 40);
		table.put("5", 50);

		iterator = table.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().value);
		}
		iterator = table.iterator();
		System.out.println("********");
		while (iterator.hasNext()) {
			String key=iterator.next().key;
			if(key.equals("5")) {
				iterator.remove();
			}
		}
		System.out.println("********");
		iterator = table.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().value);
		}
		System.out.println("********");
		table.put("1", 1);
		table.put("2", 2);
		table.put("3", 3);
		table.put("4", 4);
		table.put("5", 5);
		table.put("6", 6);
		table.put("7", 7);
		table.put("8", 8);
		table.put("9", 9);

		iterator = table.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().value);
		}

	}

	public static class Table<K, V> {

		private int size = 0;

		private double maxLoadFactor = 0.75;

		private Entry<K, V>[] table;
		private int capacity;

		public Table() {
			this(3);
		}

		public Table(int initialCapacity) {
			initTable(initialCapacity);
		}

		public boolean delete(K key) {
			Entry<K, V> entry = getEntry(key);
			if (entry == null) {
				return false;
			}
			while (entry != null) {
				if (entry.key.equals(key)) {
					if (entry.previous == null) {
						table[hash(key)] = null;
					}
					size--;
					return true;
				}
				entry = entry.next;
			}

			return false;
		}

		public void put(K key, V value) {

			if (size / capacity > maxLoadFactor) {
				resizeTable();
			}
			int index = hash(key);
			Entry<K, V> entry = table[index];
			if (entry == null) {
				table[index] = createEntry(key, value, null);
				size++;
				return;
			}
			entry = getEntry(key);
			if (entry != null) {
				entry.value = value;
				return;
			}

			Entry<K, V> previosEntry = entry;
			while (entry != null) {
				entry = entry.next;
				previosEntry = entry;
			}
			entry = createEntry(key, value, previosEntry);
			size++;
		}

		private void resizeTable() {
			Entry<K, V>[] temp = table;
			size = 0;
			initTable(capacity * 2);
			for (Entry<K, V> entry : temp) {
				put(entry.key, entry.value);
			}

		}

		public V get(K key) {
			Entry<K, V> entry = getEntry(key);
			return entry.value;
		}

		private Entry<K, V> createEntry(K key, V value, Entry<K, V> entryParent) {
			Entry<K, V> entry = new Entry<>();
			entry.key = key;
			entry.value = value;
			entry.previous = entryParent;
			return entry;
		}

		private Entry<K, V> getEntry(K key) {
			Entry<K, V> entry = table[hash(key)];
			while (entry != null) {
				if (entry.key.equals(key)) {
					return entry;
				}
				entry = entry.next;
			}
			return null;
		}

		public boolean containsKey(K key) {
			Entry<K, V> entry = table[hash(key)];
			while (entry != null) {
				if (entry.key.equals(key)) {
					return true;
				}
				entry = entry.next;
			}
			return false;
		}

		private int hash(K key) {
			return key.hashCode() % capacity;

		}

		private void initTable(int capacity) {
			this.capacity = capacity;
			table = new Entry[capacity];

		}

		public MapIterator<K, V> iterator() {
			MapIterator<K, V> it = new MapIterator<>();
			it.table = this;
			return it;
		}

		public static class MapIterator<K, V> implements Iterator<Entry<K, V>> {

			private int tablePos;
			private Entry<K, V> entry;
			Table<K, V> table;

			@Override
			public boolean hasNext() {
				Entry<K, V> entryTemp = entry;
				int tablePosTemp = tablePos;
				Entry<K, V> next = next();
				entry = entryTemp;
				tablePos = tablePosTemp;
				return next != null;
			}

			@Override
			public Entry<K, V> next() {
				while (tablePos < table.capacity) {
					if (table.table[tablePos] != null) {
						if (entry == null) {
							entry = table.table[tablePos];
						} else {
							entry = entry.next;
						}
						if (entry != null) {
							return entry;
						}
					}
					tablePos++;
					entry = null;

				}

				return null;
			}

			@Override
			public void remove() {
				if (table.table[tablePos] == entry) {
					table.table[tablePos] = null;
					tablePos++;
					entry = null;
				} else {
					entry = entry.previous = entry.next;
				}
				table.size--;
			}

		}

		public static class Entry<K, V> {
			K key;
			V value;
			Entry<K, V> previous;
			Entry<K, V> next;
		}

	}

}
