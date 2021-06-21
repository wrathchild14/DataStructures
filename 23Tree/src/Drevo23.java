import java.util.ArrayList;
import java.util.List;

public class Drevo23<Tip extends Comparable> implements Seznam<Tip> {

	Element23 root;
	int size;
	boolean addition;

	class Element23 {
		Element23 left, right, middle;
		Tip leftElement, rightElement;

		Element23() {
			left = null;
			right = null;
			middle = null;
			leftElement = null;
			rightElement = null;
		}

		Element23(Tip leftElement, Tip rightElement) {
			this.leftElement = leftElement;
			this.rightElement = rightElement;
			left = null;
			middle = null;
			right = null;
		}

		Element23(Tip leftElement, Tip rightElement, Element23 left, Element23 middle) {
			this.leftElement = leftElement;
			this.rightElement = rightElement;
			this.left = left;
			this.middle = middle;
		}

		boolean isLeaf() {
			return left == null && middle == null && right == null;
		}
	}

	public Drevo23() {
		root = null;
		size = 0;
	}

	@Override
	public void add(Tip e) {
		size++;
		addition = false;
		if (root == null || root.leftElement == null) {
			if (root == null) root = new Element23();
			root.leftElement = e;
			addition = true;
		} else {
			Element23 newRoot = addRec(root, e);
			if (newRoot != null) root = newRoot;
		}
		if (!addition) {
			size--;
			throw new IllegalArgumentException();
		}
	}

	private Element23 addRec(Element23 curr, Tip e) {
		Element23 newParent = null;

		if (!curr.isLeaf()) {
			Element23 son = null;

			// new elem is smaller than the left elem
			if (curr.leftElement.compareTo(e) > 0) { // bigger
				son = addRec(curr.left, e);
				if (son != null) {
					if (curr.rightElement == null) { // 2node
						curr.rightElement = curr.leftElement;
						curr.leftElement = son.leftElement;
						curr.right = curr.middle;
						curr.middle = son.middle;
						curr.left = son.left;
					}
				}
			} // bigger than left and less that right
			else if (curr.rightElement == null || (curr.rightElement != null && curr.rightElement.compareTo(e) > 0)) {
				son = addRec(curr.middle, e);
				if (son != null) { // split
					// right is empty, we set the son elem in the left and the existing to right
					if (curr.rightElement == null) {

						curr.rightElement = son.leftElement;
						curr.right = son.middle;
						curr.middle = son.left;
					} else {
						Element23 left = new Element23(curr.leftElement, null, curr.left, son.left);
						Element23 middle = new Element23(curr.rightElement, null, son.middle, curr.right);
						newParent = new Element23(son.leftElement, null, left, middle);
					}
				}
			} // bigger than right
			else if (curr.rightElement != null && curr.rightElement.compareTo(e) < 0) {
				son = addRec(curr.right, e);

				if (son != null) { // right goes up
					Element23 leftCopy = new Element23(curr.leftElement, null, curr.left, curr.middle);
					newParent = new Element23(curr.rightElement, null, leftCopy, son);
				}
			} // deepest level
		} else {
			addition = true;

			// elem already exists
			if (curr.leftElement.compareTo(e) == 0 || (curr.rightElement != null && curr.rightElement.compareTo(e) == 0)) {
				addition = false;
			} else if (curr.rightElement == null) { // no right elem
				if (curr.leftElement.compareTo(e) > 0) {
					curr.rightElement = curr.leftElement;
					curr.leftElement = e;
				} // if new elemen is bigger
				else if (curr.leftElement.compareTo(e) < 0) curr.rightElement = e;

			}
			// 3 node, split the node
			else {

				// left elem is bigger, new elem on the left
				if (curr.leftElement.compareTo(e) > 0) {
					Element23 left = new Element23(e, null);
					Element23 right = new Element23(curr.rightElement, null);
					newParent = new Element23(curr.leftElement, null, left, right);
				} else if (curr.leftElement.compareTo(e) < 0) {
					// new elem is bigger than the curr on the right and less than the right, elem goes up
					if (curr.rightElement.compareTo(e) > 0) {
						Element23 left = new Element23(curr.leftElement, null);
						Element23 right = new Element23(curr.rightElement, null);
						newParent = new Element23(e, null, left, right);
					} // new elem is the biggset one, so the current right elem goes up
					else {
						Element23 left = new Element23(curr.leftElement, null);
						Element23 right = new Element23(e, null);
						newParent = new Element23(curr.rightElement, null, left, right);
					}
				}
			}
		}
		return newParent;
	}

	@Override
	public Tip removeFirst() {
		return null;
	}

	@Override
	public Tip getFirst() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		return root.leftElement;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int depth() {
		Element23 tmp = root;
		int level = 0;
		while (tmp != null) {
			tmp = tmp.left;
			level++;
		}
		return level;
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public Tip remove(Tip e) {
		return null;
	}

	@Override
	public boolean exists(Tip e) {
		return find(e) != null;
	}

	private Tip find(Tip e) {
		return findRec(root, e);
	}

	@Override
	public List<Tip> toList() {
		if (isEmpty()) throw new java.util.NoSuchElementException();
		ArrayList<Tip> list = new ArrayList<>();
		preOrderAdd(root, list);
		return list;
	}

	private void preOrderAdd(Element23 curr, ArrayList<Tip> list) {
		if (curr != null) {
			list.add(curr.leftElement);
			preOrderAdd(curr.left, list);
			preOrderAdd(curr.middle, list);

			if (curr.rightElement != null) {
				list.add(curr.rightElement);
				preOrderAdd(curr.right, list);
			}
		}
	}

	private Tip findRec(Element23 current, Tip e) {
		Tip found = null;
		if (current != null) {
			if (current.leftElement != null && current.leftElement.equals(e)) found = current.leftElement;
			else {
				if (current.rightElement != null && current.rightElement.equals(e))
					found = current.rightElement;
				else {
					if (current.leftElement.compareTo(e) > 0) {
						found = findRec(current.left, e);
					} else if (current.right == null || current.rightElement.compareTo(e) > 0) {
						found = findRec(current.middle, e);
					} else if (current.rightElement.compareTo(e) < 0) {
						found = findRec(current.right, e);
					}
				}
			}
		}
		return found;
	}
}
