package TADS;

import TADS.EmptyHeapException;
import TADS.MyHeap;
import TADS.MyHeapImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyHeapImplTest {

    @Test
    public void testFlujoCompleto() throws EmptyHeapException {
        MyHeap<Integer> heapMax = new MyHeapImpl<>(5,1);
        MyHeap<Integer> heapMin = new MyHeapImpl<>(50,-1);

        try{
            heapMax.get();
            fail();
        }catch (EmptyHeapException e){
            assertTrue(true);
        }

        heapMax.insert(35);
        heapMax.insert(15);
        heapMax.insert(38);
        heapMax.insert(74);
        heapMax.insert(10);
        heapMax.insert(123);

        heapMin.insert(35);
        heapMin.insert(15);
        heapMin.insert(38);
        heapMin.insert(74);
        heapMin.insert(10);
        heapMin.insert(123);

        assertEquals(6,heapMax.size());
        assertEquals(6,heapMin.size());

        assertEquals(new Integer(123),heapMax.get());
        assertEquals(new Integer(10),heapMin.get());

        heapMax.delete();
        heapMin.delete();

        assertEquals(5,heapMax.size());
        assertEquals(5,heapMin.size());

        assertEquals(heapMax.get(), heapMax.delete());
        assertEquals(heapMin.get(), heapMin.delete());

        assertTrue(true);

    }
}