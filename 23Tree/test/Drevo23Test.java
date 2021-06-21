import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Drevo23Test {

	private Drevo23<String> t23;

	public Drevo23Test() {
	}

	@BeforeClass
	public static void setUpClass() throws Exception {
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
	}

	@Before
	public void setUp() {
		t23 = new Drevo23<>();
	}

	@After
	public void tearDown() {
	}

	@Test
	public void IsEmptyTree() {
		assertTrue(t23.isEmpty());
		t23.add("1");
		assertFalse(t23.isEmpty());
	}

	@Test
	public void testGetFirstOnEmpty() {
		assertThrows(java.util.NoSuchElementException.class, () -> {
			t23.getFirst();
		});
	}

	@Test
	public void testGetFirstOn4Elemnts() {
		t23.add("7");
		t23.add("1");
		t23.add("0");
		t23.add("4");
		assertEquals("1", t23.getFirst());
	}

	@Test
	public void testAdd4Elemnts() {
		t23.add("7");
		t23.add("1");
		t23.add("0");
		t23.add("4");
	}

	@Test
	public void testSizeOnEmpty() {
		assertEquals(0, t23.size());
	}

	@Test
	public void testAddOne() {
		assertFalse(t23.exists("1"));
		t23.add("1");
		assertTrue(t23.exists("1"));
		assertEquals(1, t23.size());
		assertEquals(1, t23.depth());
	}

	@Test
	public void testAddDuplicate() {
		t23.add("1");
		assertThrows(java.lang.IllegalArgumentException.class, () -> {
			t23.add("1");
		});
	}

	@Test
	public void testAddTwo() {
		assertFalse(t23.exists("7"));
		assertFalse(t23.exists("1"));
		t23.add("7");
		t23.add("1");
		assertTrue(t23.exists("7"));
		assertTrue(t23.exists("1"));
		assertEquals(2, t23.size());
		assertEquals(1, t23.depth());
	}

	@Test
	public void testAddThree() {
		assertFalse(t23.exists("7"));
		assertFalse(t23.exists("1"));
		assertFalse(t23.exists("4"));
		t23.add("7");
		t23.add("1");
		t23.add("4");
		assertTrue(t23.exists("7"));
		assertTrue(t23.exists("1"));
		assertTrue(t23.exists("4"));
		assertEquals(3, t23.size());
		assertEquals(2, t23.depth());
	}

	@Test
	public void testAddFour() {
		assertFalse(t23.exists("7"));
        assertFalse(t23.exists("1"));
        assertFalse(t23.exists("4"));
        assertFalse(t23.exists("0"));
		t23.add("7");
		t23.add("1");
		t23.add("4");
		t23.add("0");
        assertTrue(t23.exists("7"));
        assertTrue(t23.exists("1"));
        assertTrue(t23.exists("4"));
        assertTrue(t23.exists("0"));
		assertEquals(4, t23.size());
		assertEquals(2, t23.depth());
	}

	@Test
	public void testAddFiveThreeTree() {
        assertFalse(t23.exists("7"));
        assertFalse(t23.exists("1"));
        assertFalse(t23.exists("4"));
        assertFalse(t23.exists("0"));
        assertFalse(t23.exists("3"));
		t23.add("7");
		t23.add("1");
		t23.add("4");
		t23.add("0");
		t23.add("3");
        assertTrue(t23.exists("7"));
        assertTrue(t23.exists("1"));
        assertTrue(t23.exists("4"));
        assertTrue(t23.exists("0"));
        assertTrue(t23.exists("3"));
		assertEquals(5, t23.size());
		assertEquals(2, t23.depth());
	}

	@Test
	public void testFixInsertCoverage() {
        assertFalse(t23.exists("7"));
        assertFalse(t23.exists("8"));
        assertFalse(t23.exists("2"));
        assertFalse(t23.exists("1"));
        assertFalse(t23.exists("9"));
        assertFalse(t23.exists("10"));
        assertFalse(t23.exists("11"));
        assertFalse(t23.exists("12"));

		t23.add("7");
		t23.add("8");

		t23.add("2");
		t23.add("1");
		t23.add("9");
		t23.add("10");
		t23.add("11");
		t23.add("12");

		t23.add("15");
		t23.add("17");
		t23.add("13");
		t23.add("19");
		t23.add("20");
		t23.add("23");

		t23.add("4");
		t23.add("3");
		t23.add("6");
		t23.add("5");

        assertTrue(t23.exists("7"));
        assertTrue(t23.exists("8"));
        assertTrue(t23.exists("2"));
        assertTrue(t23.exists("1"));
        assertTrue(t23.exists("9"));
        assertTrue(t23.exists("10"));
        assertTrue(t23.exists("11"));
        assertTrue(t23.exists("12"));

        assertTrue(t23.exists("15"));
        assertTrue(t23.exists("17"));
        assertTrue(t23.exists("13"));
        assertTrue(t23.exists("19"));
        assertTrue(t23.exists("20"));
        assertTrue(t23.exists("23"));

        assertTrue(t23.exists("4"));
        assertTrue(t23.exists("3"));
        assertTrue(t23.exists("6"));
        assertTrue(t23.exists("5"));

		assertEquals(18, t23.size());
		assertEquals(4, t23.depth());
	}

	@Test
	public void testToList() {
		ArrayList<String> list = new ArrayList<>();
		t23.add("7");
		t23.add("1");
		t23.add("4");
		t23.add("0");
		t23.add("3");
		list.add("1");
		list.add("0");
		list.add("3");
		list.add("4");
		list.add("7");
		assertEquals(list, t23.toList());
	}

	@Test
	public void testToList2() {
		ArrayList<String> list = new ArrayList<>();
		t23.add("1");
		t23.add("5");
		t23.add("7");
		t23.add("4");
		t23.add("8");
		list.add("5");
		list.add("1");
		list.add("4");
		list.add("7");
		list.add("8");
		assertEquals(list, t23.toList());
	}

	@Test(expected = java.util.NoSuchElementException.class)
	public void toListOnEmptyStack() {
		List<String> list = t23.toList();
	}
}