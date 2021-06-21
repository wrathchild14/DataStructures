
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SkladTest {

    static Sklad<String> instance;

    public SkladTest() {
    }

    @BeforeClass
    public static void setUpOnce() {
        instance = new Sklad<>();
    }

    @Before
    public void setUp() {
        while (!instance.isEmpty()) {
            instance.pop();
        }
    }

    @Test
    public void testPush() {
        String a = "Test";
        instance.push(a);
    }

    @Test
    public void testPop() {
        String a = "Test";
        instance.push(a);
        String b = instance.pop();
        assertEquals("Test", b);
    }

    @Test
    public void testWithTwoElements() {
        String a = "Prvi element";
        String b = "Drugi element";
        instance.push(a);
        instance.push(b);
        assertEquals(b, instance.pop());
        assertEquals(a, instance.pop());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testPopOnEmptyStack() {
        String a = instance.pop();
    }

    @Test
    public void testIsEmptyOnEmpty() {
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testIsEmptyOnFull() {
        instance.push("Test");
        assertFalse(instance.isEmpty());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testPeekOnEmptyStack() {
        String a = instance.peek();
    }

    @Test
    public void testPeekOnFullStack() {
        String a = "Vrednost 1";
        String b = "Vrednost 2";
        instance.push(a);
        instance.push(b);
        String c = instance.peek();
        assertEquals(c, b);
    }

    @Test
    public void testPeekSame() {
        instance.push("Test1");
        instance.push("Test2");
        instance.push("Test3");
        assertEquals("Test3", instance.peek());
        assertEquals("Test3", instance.pop());
        assertEquals("Test2", instance.pop());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testCountEmpty() {
        assertEquals(0, instance.count());
    }

    @Test(timeout = 100)
    public void testCountNonEmpty() {
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(3, instance.count());
    }

    @Test
    public void testCountSame() {
        instance.push("Test1");
        instance.push("Test2");
        instance.push("Test3");
        assertEquals(3, instance.count());
        assertEquals("Test3", instance.pop());
        assertEquals("Test2", instance.pop());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testTopTrue() {
        instance.push("Test");
        assertTrue(instance.top("Test"));
    }

    @Test
    public void testTopFalse() {
        instance.push("Test1");
        assertFalse(instance.top("Test2"));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testTopEmpty() {
        assertFalse(instance.top("Test"));
    }

    @Test
    public void testTopSame() {
        instance.push("Test1");
        instance.push("Test2");
        instance.push("Test3");
        assertTrue(instance.top("Test3"));
        assertEquals("Test3", instance.pop());
        assertEquals("Test2", instance.pop());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

    @Test
    public void testSearchEmpty() {
        assertEquals(-1, instance.search("Test"));
    }

    @Test(timeout = 100)
    public void testSearchFoundTop() {
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(0, instance.search("Vrednost 3"));
    }

    @Test(timeout = 100)
    public void testSearchFoundNonTop() {
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(2, instance.search("Vrednost 1"));
    }

    @Test(timeout = 100)
    public void testSearchNotFound() {
        instance.push("Vrednost 1");
        instance.push("Vrednost 2");
        instance.push("Vrednost 3");
        assertEquals(-1, instance.search("Vrednost"));
    }

    @Test
    public void testSearchSame() {
        instance.push("Test1");
        instance.push("Test2");
        instance.push("Test3");
        assertEquals(1, instance.search("Test2"));
        assertEquals("Test3", instance.pop());
        assertEquals("Test2", instance.pop());
        assertEquals("Test1", instance.pop());
        assertTrue(instance.isEmpty());
    }

    // Testi za add, removeFirst, getFirst, size in depth
    // so zelo poenostavljeni, ker gre le za klice že testiranih metod! 
    @Test
    public void testAdd() {
        String a = "Test";
        instance.add(a);
    }

    @Test
    public void testRemoveFirst() {
        String a = "Test";
        instance.add(a);
        String b = instance.removeFirst();
        assertEquals("Test", b);
    }

    @Test
    public void testGetFirst() {
        String a = "Test";
        instance.add(a);
        String b = instance.getFirst();
        assertEquals("Test", b);
    }

    @Test
    public void testSize() {
        String a = "Test";
        assertEquals(0, instance.size());
        instance.add(a);
        assertEquals(1, instance.size());
    }

    @Test
    public void testDepth() {
        String a = "Test";
        assertEquals(0, instance.depth());
        instance.add(a);
        assertEquals(1, instance.depth());
    }

    // Testi za remove() in exists() ...
    @Test
    public void testRemoveOK() {
        String a = "Test";
        instance.add(a);
        assertEquals(a, instance.remove(a));
        instance.add("Test1");
        instance.add("Test2");
        instance.add(a);
        instance.add("Test3");
        instance.add("Test4");
        assertEquals(a, instance.remove(a));
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveOnEmptyStack() {
        instance.add("Test1");
        instance.add("Test2");
        instance.add("Test3");
        instance.add("Test4");
        String a = instance.remove("Test");
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void testRemoveNOK() {
        String a = instance.remove("Test");
    }

    @Test
    public void testExists() {
        String a = "Test";
        assertFalse(instance.exists(a));
        instance.add(a);
        assertTrue(instance.exists(a));
    }

    @Test
    public void testToList() {
        instance.add("Test1");
        instance.add("Test2");
        ArrayList<String> list = new ArrayList<>();
        list.add("Test2");
        list.add("Test1");
        assertEquals(list, instance.toList());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void toListOnEmptyStack() {
        List<String> list = instance.toList();
    }

}
