package array;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindElementInSortedArrayTest {

    @Test
    public void testExist() {
        int[][] a = new int[3][3];
        a[0] = new int[] { 2, 3, 9 };
        a[1] = new int[] { 4, 8, 10 };
        a[2] = new int[] { 6, 9, 11 };

        assertTrue(FindElementInSortedArray.exist(a, 2));
        assertTrue(FindElementInSortedArray.exist(a, 11));
        assertTrue(FindElementInSortedArray.exist(a, 6));

        assertTrue(!FindElementInSortedArray.exist(a, 1));
        assertTrue(!FindElementInSortedArray.exist(a, 12));
        assertTrue(!FindElementInSortedArray.exist(a, 7));
    }
}
