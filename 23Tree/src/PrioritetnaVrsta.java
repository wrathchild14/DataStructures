import java.util.ArrayList;
import java.util.List;

public class PrioritetnaVrsta<Tip extends Comparable> implements Seznam<Tip> {
 
    private Object[] heap;
    private int end = 0;

    public PrioritetnaVrsta() {
        this(100);
    }

    public PrioritetnaVrsta(int maxSize) {
        heap = new Object[maxSize];
    }

    private void bubbleUp() {
        int eltIdx = end - 1;
        while (eltIdx >= 0) {
            Tip elt = (Tip) heap[eltIdx];
            int childIdx = eltIdx * 2 + 1;
            if (childIdx < end) {
                Tip child = (Tip) heap[childIdx];
                if (childIdx + 1 < end && child.compareTo(heap[childIdx + 1]) < 0) {
                    child = (Tip) heap[++childIdx];
                }
                if (elt.compareTo(child) >= 0) {
                    return;
                }
                swap(eltIdx, childIdx);
            }
            eltIdx = eltIdx % 2 == 1 ? (eltIdx - 1) / 2 : (eltIdx - 2) / 2;
        }
    }

    @Override
    public void add(Tip e) {
        if (end == heap.length) {
            resize();
        }
        heap[end++] = e;
        bubbleUp();
    }

    private void resize() {
        Object[] tmp = heap;
        heap = new Object[2 * heap.length];
        for (int i = 0; i < end; i++) {
            heap[i] = tmp[i];
        }
    }

    private void bubbleDown(int start) {
        int eltIdx = start;
        int childIdx = eltIdx * 2 + 1;
        while (childIdx < end) {
            Tip elt = (Tip) heap[eltIdx];
            Tip child = (Tip) heap[childIdx];
            if (childIdx + 1 < end && child.compareTo(heap[childIdx + 1]) < 0) {
                child = (Tip) heap[++childIdx];
            }
            if (elt.compareTo(child) >= 0) {
                return;
            }
            swap(eltIdx, childIdx);
            eltIdx = childIdx;
            childIdx = eltIdx * 2 + 1;
        }
    }

    @Override
    public Tip removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Tip elt = (Tip) heap[0];
        swap(0, --end);
        bubbleDown(0);
        return elt;
    }

    private void swap(int a, int b) {
        Object tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }

    @Override
    public Tip getFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return (Tip) heap[0];
    }

    @Override
    public int depth() {
        if (end == 0) {
            return 0;
        }
        return (int) (Math.log(end) / Math.log(2)) + 1;
    }

    @Override
    public boolean isEmpty() {
        return end == 0;
    }

    @Override
    public int size() {
        return end;
    }

    @Override
    public Tip remove(Tip e) {
        if (this.isEmpty()) throw new java.util.NoSuchElementException();
        if (!this.exists(e)) throw new java.util.NoSuchElementException();

        for (int i = 0; i < end; i++) {
            Tip elt = (Tip) heap[i];
            if (elt.compareTo(e) == 0) {
                swap(i, --end);
                Tip child = (Tip) heap[i];
                Tip parent = (Tip) heap[(i - 1) / 2];
                if (child.compareTo(parent) < 0) {
                    bubbleUp();
                } else {
                    bubbleDown(i);
                }
            }
        }
        return e;
    }

    @Override
    public boolean exists(Tip e) {
        if (this.isEmpty()) return false;
        for (int i = 0; i < end; i++) {
            Tip elt = (Tip) heap[i];
            if (elt.compareTo(e) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Tip> toList() {
        if (this.isEmpty()) throw new java.util.NoSuchElementException();
        ArrayList<Tip> list = new ArrayList<>();
        Sklad<Tip> tmpPV = new Sklad<>();
        while(!this.isEmpty()) {
            Tip tmpElt = this.removeFirst();
            tmpPV.add(tmpElt);
            list.add(tmpElt);
        }
        while(!tmpPV.isEmpty())
            this.add(tmpPV.removeFirst());
        return list;
    }

    // manjka toList
    
}
