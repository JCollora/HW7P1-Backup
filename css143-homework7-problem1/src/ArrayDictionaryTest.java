import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDictionaryTest {
    @Test
    public void demo() {
        int testSize = 5;
        ArrayDictionary dict = new ArrayDictionary(testSize);
        assertTrue(dict.add(2, 82));
        assertTrue(dict.add(4, 84));
        assertTrue(dict.add(7, 87));
    }

    @Test
    public void remove() {
        //Empty Dictionary Case | SET 1
    	ArrayDictionary d1 = new ArrayDictionary(0);
    	assertFalse(d1.remove(1));
    	
    	//No Collision Case | SET 2
    	ArrayDictionary d2 = new ArrayDictionary(2); d2.add(0, 103); d2.add(1, 105);
    	assertFalse(d2.remove(3));
    	assertTrue(d2.remove(0));
    	
    	//Collision Included CASE | SET 3
    	ArrayDictionary d3 = new ArrayDictionary(3); d3.add(0, 103); d3.add(1, 105); d3.add(2, 206); d3.add(4, 407);
    	System.out.println(d3);
    	assertTrue(d3.remove(1)); 
    	assertTrue(d3.remove(0));
    	assertFalse(d3.remove(9));
    	System.out.println(d3);
    }

    @Test
    public void contains() {
    	//Empty Dictionary Test | SET 1
        ArrayDictionary d1 = new ArrayDictionary(0);
        assertFalse(d1.contains(-1)); assertFalse(d1.contains(0)); assertFalse(d1.contains(1));
        
        //Capacity 1 Dictionary | SET 2
        ArrayDictionary d2 = new ArrayDictionary(1); d2.add(0, 103);
        assertFalse(d2.contains(2)); assertTrue(d2.contains(0));
        
        //Capacity 2 Dictionary | SET 3
        ArrayDictionary d3 = new ArrayDictionary(2); d3.add(0, 103); d3.add(1, 105);
        assertTrue(d3.contains(0)); assertTrue(d3.contains(1));
        assertFalse(d3.contains(3)); assertFalse(d3.contains(4)); 
        
        //Capacity 3 Dictionary | SET 4
        //using previous set for first assertion as that is how it is stated in spec
        assertFalse(d3.contains(3));
        ArrayDictionary d4 = new ArrayDictionary(3); d4.add(0, 103); d4.add(1, 105); d4.add(2, 206);
        assertTrue(d4.contains(2));
        
        //Capacity 4 Dictionary (INCLUDES COLLISION CASE) | SET 5
        ArrayDictionary d5 = new ArrayDictionary(3); d5.add(0, 103); d5.add(1, 105); d5.add(2, 206); d5.add(4, 407);
        assertTrue(d5.contains(1)); assertTrue(d5.contains(4));
        assertFalse(d5.contains(7)); assertFalse(d5.contains(8));
    }
}