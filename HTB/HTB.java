public class HTB {
	int[] table;
	int p, m, c1, c2, collisions = 0;

	public HTB(int p, int m, int c1, int c2) {
		this.p = p;
		this.m = m;
		this.c1 = c1;
		this.c2 = c2;
		this.table = new int[m];
	}

	void insert(int key) {
		if (find(key)) return;
		boolean inserted = false;
		int index = hash(key);
		if (table[index] == 0)
			table[index] = key;
		else {
			for (int i = 1; i < table.length + 1; i++) {
				int index2 = (hash(key) + c1 * i + c2 * i * i) % m;
				collisions++;
				if (table[index2] == 0) {
					table[index2] = key;
					inserted = true;
					break;
				}
			}
			if (!inserted) {
				resize();
				insert(key);
			}
		}
	}

	int hash(int key) {
		return (key * p) % m;
	}

	private void resize() {
		int[] tmp = new int[table.length];
		System.arraycopy(table, 0, tmp, 0, table.length);
		m = 2 * m + 1;
		table = new int[m];
		for (int j : tmp) {
			insert(j);
		}
	}

	boolean find(int key) {
		for (int j : table) {
			if (j == key) return true;
		}
		return false;
	}

	void delete(int key) {
		for (int i = 0; i < table.length; i++) {
			if (table[i] == key) table[i] = 0;
		}
	}

	void printKeys() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != 0)
				System.out.println(i + ": " + table[i]);
		}
	}

	void printCollisions() {
		System.out.println(collisions);
	}
}
