public class Tests {
    public static void main(String[] args) {
        //Test 1
        Seznam s = null;
        s = Seznam.insert(s,new Elt(5));
        s = Seznam.insert(s,new Elt(7));
        s = Seznam.insert(s,new Elt(5));
        Seznam.printElementKeys(s);

        //Test 2
        s = null;
        s = Seznam.insert(s,new Elt(1));
        s = Seznam.insert(s,new Elt(3));
        s = Seznam.insert(s,new Elt(4));
        s = Seznam.insert(s,new Elt(2));
        s = Seznam.insert(s,new Elt(5));
        Seznam.printElementKeys(s);
        Seznam.printElementKeyComparisons(s);

        //Test 3
        s = null;
        s = Seznam.insert(s,new Elt(1));
        s = Seznam.insert(s,new Elt(2));
        s = Seznam.insert(s,new Elt(3));
        s = Seznam.insert(s,new Elt(4));
        s = Seznam.insert(s,new Elt(5));
        s = Seznam.delete(s,3);
        s = Seznam.delete(s,4);
        s = Seznam.delete(s,3);
        s = Seznam.delete(s,4);
        s = Seznam.delete(s,10);
        s = Seznam.insert(s,new Elt(10));
        Seznam.printElementKeys(s);

        //Test 4
        s = null;
        s = Seznam.insert(s,new Elt(1));
        s = Seznam.insert(s,new Elt(2));
        s = Seznam.insert(s,new Elt(3));
        s = Seznam.insert(s,new Elt(4));
        s = Seznam.insert(s,new Elt(5));
        Seznam.find(s,4);
        Seznam.find(s,7);
        s = Seznam.insert(s,new Elt(5));
        s = Seznam.insert(s,new Elt(15));
        s = Seznam.insert(s,new Elt(11));
        s = Seznam.insert(s,new Elt(1));
        s = Seznam.delete(s,15);
        s = Seznam.delete(s,15);
        s = Seznam.insert(s,new Elt(23));
        s = Seznam.delete(s,23);
        Seznam.printElementKeyComparisons(s);

        //Test 5
        s = null;
        s = Seznam.insert(s,new Elt(5));
        Seznam.printElementKeys(s);
        Seznam.printElementKeyComparisons(s);
        s = Seznam.delete(s,5);
        Seznam.printElementKeys(s);
        Seznam.printElementKeyComparisons(s);
        Seznam.find(s,5);
        Seznam.printElementKeys(s);
        Seznam.printElementKeyComparisons(s);

        //Test 6
        s = null;
        s = Seznam.insert(s,new Elt(5));
        Seznam.find(s,5);
        s = Seznam.insert(s,new Elt(5));
        s = Seznam.insert(s,new Elt(17));
        s = Seznam.insert(s,new Elt(1));
        s = Seznam.delete(s,15);
        s = Seznam.delete(s,25);
        s = Seznam.insert(s,new Elt(23));
        s = Seznam.delete(s,31);
        s = Seznam.insert(s,new Elt(17));
        s = Seznam.insert(s,new Elt(9));
        s = Seznam.insert(s,new Elt(7));
        s = Seznam.insert(s,new Elt(3));
        Seznam.find(s,5);
        Seznam.printElementKeys(s);
        Seznam.printElementKeyComparisons(s);

    }
}

