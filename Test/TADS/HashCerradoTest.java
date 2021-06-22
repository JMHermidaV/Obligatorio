package TADS;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashCerradoTest {
    @Test
    public void testFlujoCompleto(){
        HashTable<Integer, String> miHash = new HashCerrado<>(11);

        miHash.put(1, "valor1");
        miHash.put(2, "valor2");
        miHash.put(3, "valor3");
        miHash.put(4, "valor4");
        miHash.put(5, "valor5");
        miHash.put(12, "valor12");


        assertEquals("valor1", miHash.get(1).getValue());
        assertEquals((Integer)1, miHash.get(1).getKey());
        assertEquals("valor2", miHash.get(2).getValue());
        assertEquals((Integer)2, miHash.get(2).getKey());
        assertEquals("valor3", miHash.get(3).getValue());
        assertEquals((Integer)3, miHash.get(3).getKey());
        assertEquals("valor4", miHash.get(4).getValue());
        assertEquals((Integer)4, miHash.get(4).getKey());
        assertEquals("valor12", miHash.get(12).getValue());
        assertEquals((Integer)12, miHash.get(12).getKey());

        miHash.remove(1);
        assertNull(miHash.get(1));

        miHash.remove(2);
        assertNull(miHash.get(2));

        assertEquals("valor12", miHash.get(12).getValue());
        assertEquals((Integer)12, miHash.get(12).getKey());

        miHash.put(22,"valor22");
        assertEquals((Integer)22, miHash.get(22).getKey());

        //NUESTRA FUNCIÓN NO CREA OTRA TABLA SI SE ALCANZA EL LF = 0.75, SIMPLEMENTE NO AGREGA NADA
    }
}