public class Seznam {
	public static int comparisons = 0;

    public final Elt head;
    public final Seznam tail;

    public Seznam(Elt elt, Seznam tail) {
        this.head = elt;
        this.tail = tail;
    }

    public static void printElementKeys(Seznam s) {
		while (s != null) {
			System.out.println(s.head.toString());
			s = s.tail;
		}
    }

    public static Seznam insert(Seznam s, Elt e) {
		if (find(s, e.key) == null) {
			s = new Seznam(e, s);
		}
		return s;
    }

	public static Elt find(Seznam s, int key) {
		if (s == null) return null;
		comparisons++;
		if (s.head.key == key)
			return s.head;
		else {
			return find(s.tail, key);
		}
	}

	public static Seznam delete(Seznam s, int key) {
		if (s == null) return null;
		comparisons++;
		if (s.head.key == key) return s.tail;
		else return new Seznam(s.head, delete(s.tail, key));
    }

	public static void printElementKeyComparisons(Seznam s) {
		if (s != null)
			System.out.println(comparisons);
	}
}
