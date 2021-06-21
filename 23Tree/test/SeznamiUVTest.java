
import org.junit.*; 
import static org.junit.Assert.*;

public class SeznamiUVTest {

    private SeznamiUV uv;

    public SeznamiUVTest() {
    }

    @Before
    public void setUp() {
        uv = new SeznamiUV();
    }

    @Test
    public void testUseSklad() {
        assertEquals("OK", uv.processInput("use sk"));
        doTests();
        testSklad(true);

        testToListSklad();
    }

    @Test
    public void testUsePrioritetnaVrsta() {
        assertEquals("OK", uv.processInput("use pv"));
        doTests();
        testPrioritetnaVrsta(true);

        testToListPrioritetnaVrsta();
    }

    @Test
    public void testUseBST() {
        assertEquals("OK", uv.processInput("use bst"));
        doTests();
        testBst(true);

        testToListBST();
    }

    @Test
    public void testUse23() {
        assertEquals("OK", uv.processInput("use 23"));
        testToList23();
    }

    @Test
    public void testUse23add() {
        assertEquals("OK", uv.processInput("use 23"));
        testAdd();
    }

    @Test
    public void testUse23depth() {
        assertEquals("OK", uv.processInput("use 23"));
        testDepthOne();
    }

    @Test
    public void testUseAllMixed() {
        assertEquals("OK", uv.processInput("use sk"));
        testAddTestSequence();
        assertEquals("OK", uv.processInput("use pv"));
        testAddTestSequence();
        assertEquals("OK", uv.processInput("use bst"));
        testAddTestSequence();
        assertEquals("OK", uv.processInput("use pv"));
        testPrioritetnaVrsta(false);
        assertEquals("OK", uv.processInput("use bst"));
        testBst(false);
        assertEquals("OK", uv.processInput("use sk"));
        testSklad(false);
    }


    // *****************
    // POMOZNE METODE
    // *****************

    public void doTests() {
        reset();
        testAdd();
        reset();
        testAddNothing();
        reset();
        testRemoveFirst();
        reset();
        testRemoveFirstNothing();
        reset();
        testGetFirst();
        reset();
        testGetFirstNothing();
        reset();
        testResetOnEmpty();
        reset();
        testResetOnFull();
        reset();
        testSizeOnEmpty();
        reset();
        testSizeOne();
        reset();
        testSizeTwo();
        reset();
        testDepthOnEmpty();
        reset();
        testDepthOne();
        reset();
        testDepthTwo();
        reset();
        testIsEmptyEmpty();
        reset();
        testIsEmptyNotEmpty();
        reset();
        testResetOnEmpty();
        reset();
        testResetOnFull();
        reset();
//        testExists(); // to do
        reset();
        testRemove();  // to do
        reset();
//        testToList();  // to do
        reset();
    }
    
    public void reset() {
        uv.processInput("reset");
    }

    public void testAdd() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
    }

    public void testAddNothing() {
        assertEquals("Error: please specify a string", uv.processInput("add"));
    }

    public void testRemoveFirst() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Test", uv.processInput("remove_first"));
    }

    public void testRemoveFirstNothing() {
        assertEquals("Error: data structure is empty", uv.processInput("remove_first"));
        assertEquals("Error: please specify a string", uv.processInput("add"));
        assertEquals("Error: data structure is empty", uv.processInput("remove_first"));
    }

    public void testGetFirst() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("Test", uv.processInput("get_first"));
    }

    public void testGetFirstNothing() {
        assertEquals("Error: data structure is empty", uv.processInput("get_first"));
        assertEquals("Error: please specify a string", uv.processInput("add"));
        assertEquals("Error: data structure is empty", uv.processInput("get_first"));
    }

    public void testSizeOnEmpty() {
        assertEquals("0", uv.processInput("size"));
    }

    public void testSizeOne() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("1", uv.processInput("size"));
    }

    public void testSizeTwo() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("2", uv.processInput("size"));
    }

    public void testDepthOnEmpty() {
        assertEquals("0", uv.processInput("depth"));
    }

    public void testDepthOne() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("1", uv.processInput("depth"));
    }

    public void testDepthTwo() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("2", uv.processInput("depth"));
    }

    public void testIsEmptyEmpty() {
        assertEquals("Data structure is empty.", uv.processInput("is_empty"));
        assertEquals("Error: please specify a string", uv.processInput("add"));
        assertEquals("Data structure is empty.", uv.processInput("is_empty"));
    }

    public void testIsEmptyNotEmpty() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("Data structure is not empty.", uv.processInput("is_empty"));
    }

    public void testResetOnEmpty() {
        assertEquals("OK", uv.processInput("reset"));
    }

    public void testResetOnFull() {
        assertEquals("OK", uv.processInput("add Test"));
        assertEquals("OK", uv.processInput("reset"));
        assertEquals("Error: data structure is empty", uv.processInput("remove_first"));
        assertEquals("0", uv.processInput("size"));
    }

    // TO DO
    public void testExists() {
        testAddTestSequence();
        assertEquals("Element doesn't exist in data structure.", uv.processInput("exists bla"));
        assertEquals("Element exists in data structure.", uv.processInput("exists Test1"));
        assertEquals("Error: please specify a string", uv.processInput("exists"));
    }

    // TO DO
    public void testRemove() {
        testAddTestSequence();
        assertEquals("Test1", uv.processInput("remove Test1"));
        assertEquals("4", uv.processInput("size"));
        assertEquals("Test3", uv.processInput("remove Test3"));
        assertEquals("Test2", uv.processInput("remove Test2"));
        assertEquals("Test5", uv.processInput("remove Test5"));
        assertEquals("1", uv.processInput("size"));
        assertEquals("Test4", uv.processInput("remove Test4"));
        assertEquals("Error: please specify a string", uv.processInput("remove"));
    }
    
     // TO DO
    public void testToListSklad() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));

        assertEquals("Test2 Test1", uv.processInput("asList"));
    }

    public void testToListPrioritetnaVrsta() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));

        assertEquals("Test2 Test1", uv.processInput("asList"));
    }

    public void testToListBST() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));

        assertEquals("Test1 Test2", uv.processInput("asList"));
    }

    public void testToList23() {
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test2"));

        assertEquals("Test1 Test2", uv.processInput("asList"));
    }

    public void testAddTestSequence() {
        assertEquals("OK", uv.processInput("add Test4"));
        assertEquals("OK", uv.processInput("add Test2"));
        assertEquals("OK", uv.processInput("add Test3"));
        assertEquals("OK", uv.processInput("add Test1"));
        assertEquals("OK", uv.processInput("add Test5"));
    }

    public void testSklad(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("Test5", uv.processInput("remove_first"));
        assertEquals("Test1", uv.processInput("remove_first"));
        assertEquals("Test3", uv.processInput("remove_first"));
        assertEquals("Test2", uv.processInput("remove_first"));
        assertEquals("Test4", uv.processInput("remove_first"));
    }

    public void testPrioritetnaVrsta(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("Test5", uv.processInput("remove_first"));
        assertEquals("Test4", uv.processInput("remove_first"));
        assertEquals("Test3", uv.processInput("remove_first"));
        assertEquals("Test2", uv.processInput("remove_first"));
        assertEquals("Test1", uv.processInput("remove_first"));
    }

    public void testBst(boolean add) {
        if (add) {
            testAddTestSequence();
        }
        assertEquals("Test4", uv.processInput("remove_first"));
        assertEquals("Test5", uv.processInput("remove_first"));
        assertEquals("Test2", uv.processInput("remove_first"));
        assertEquals("Test3", uv.processInput("remove_first"));
        assertEquals("Test1", uv.processInput("remove_first"));
    }

}
