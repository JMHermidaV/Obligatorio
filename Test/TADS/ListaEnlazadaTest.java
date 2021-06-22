package TADS;

import org.junit.Test;
import static org.junit.Assert.*;

public class ListaEnlazadaTest {

    @Test
    public void testFlujoCompleto() {
        ListaEnlazada<Integer> MiLista = new ListaEnlazada();

        assertEquals(0,MiLista.size());
        MiLista.add(4);
        assertEquals(1,MiLista.size());
        MiLista.add(23);
        assertEquals(2,MiLista.size());
        MiLista.add(1);
        assertEquals(3,MiLista.size());
        MiLista.add(5);
        assertEquals(4,MiLista.size());

        assertTrue(MiLista.contains(4));
        assertTrue(MiLista.contains(5));
        assertTrue(MiLista.contains(23));
        assertFalse(MiLista.contains(7));

        assertEquals((Integer)23, MiLista.get(2));
        assertEquals((Integer)4, MiLista.get(1));

        MiLista.remove(2);
        assertEquals(3,MiLista.size());
        assertEquals((Integer)1, MiLista.get(2));
        assertFalse(MiLista.contains(23));
    }
}