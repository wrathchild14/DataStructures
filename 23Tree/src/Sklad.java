import java.util.ArrayList;
import java.util.List;

class Element<Tip> {

    public Tip vrednost;
    public Element<Tip> vezava;

    public Element(Tip e) {
        this.vrednost = e;
    }
}
 
public class Sklad<Tip> implements Seznam<Tip> {

    private Element<Tip> vrh;

    public Sklad() {
    }

    public void push(Tip e) {
        Element<Tip> novVrh = new Element<>(e);
        novVrh.vezava = vrh;
        vrh = novVrh;
    }

    public Tip pop() {
        if (vrh == null) {
            throw new java.util.NoSuchElementException();
        }
        Tip e = vrh.vrednost;
        vrh = vrh.vezava;
        return e;
    }

    @Override
    public boolean isEmpty() {
        return (vrh == null);
    }

    public Tip peek() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return vrh.vrednost;
    }

    public int count() {
        if (isEmpty()) {
            return 0;
        }

        int stElementov = 0;
        Element<Tip> tmp = vrh;
        while (tmp != null) {
            stElementov++;
            tmp = tmp.vezava;
        }
        return stElementov;
    }

    public boolean top(Tip e) {
        if (vrh == null) {
            throw new java.util.NoSuchElementException();
        }
        return vrh.vrednost.equals(e);
    }

    public int search(Tip e) {
        Element<Tip> tmp = vrh;
        int i = 0;
        while (null != tmp) {
            if (tmp.vrednost.equals(e)) {
                return i;
            }
            i++;
            tmp = tmp.vezava;
        }
        return -1;
    }

    @Override
    public void add(Tip e) {
        this.push(e);
    }

    @Override
    public Tip removeFirst() {
        return this.pop();
    }

    @Override
    public Tip getFirst() {
        return peek();
    }

    @Override
    public int size() {
        return this.count();
    }

    @Override
    public int depth() {
        return this.count();
    }

    @Override
    public Tip remove(Tip e) {
        if (this.isEmpty()) throw new java.util.NoSuchElementException();
        if (!this.exists(e)) throw new java.util.NoSuchElementException();

        Sklad<Tip> tmpStack = new Sklad<>();
        while(!this.isEmpty()) {
            Tip tmpElt = this.pop();
            if(!tmpElt.equals(e)) tmpStack.push(tmpElt);
        }

        while(!tmpStack.isEmpty()) this.push(tmpStack.pop());

        return e;
    }

    @Override
    public boolean exists(Tip e) {
        if (isEmpty()) return false;
        int eIndex = this.search(e);
        return eIndex != -1;
    }

    @Override
    public List<Tip> toList() {
        if (this.isEmpty()) throw new java.util.NoSuchElementException();
        ArrayList<Tip> list = new ArrayList<>();
        Sklad<Tip> tmpStack = new Sklad<>();
        while(!this.isEmpty()) {
            Tip tmpElt = this.pop();
            tmpStack.push(tmpElt);
            list.add(tmpElt);
        }
        while(!tmpStack.isEmpty())
            this.push(tmpStack.pop());
        return list;
    }

    // manjka toList
}
