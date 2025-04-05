package ht8;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ht.VectorHeap;
import ht.Paciente;

/**
 * Clase de pruebas unitarias para la implementación de VectorHeap<Paciente>.
 * Utiliza JUnit 5 para validar la funcionalidad del sistema de prioridad basado en heap.
 * 
 * @author 
 */
public class VectorHeapTest {

    private VectorHeap<Paciente> heap;

    /**
     * Método que se ejecuta antes de cada prueba para inicializar una nueva instancia de VectorHeap.
     */
    @BeforeEach
    public void setup() {
        heap = new VectorHeap<>();
    }

    /**
     * Verifica que un objeto Paciente se cree correctamente con sus datos.
     */
    @Test
    public void testPacienteCreacion() {
        Paciente p = new Paciente("Juan Perez", "fractura de pierna", 'C');
        assertEquals('C', p.getPrioridad());
    }

    /**
     * Verifica que compareTo funcione correctamente cuando un paciente tiene mayor prioridad que otro.
     */
    @Test
    public void testCompareToMayorPrioridad() {
        Paciente p1 = new Paciente("Maria", "apendicitis", 'A');
        Paciente p2 = new Paciente("Lorenzo", "chikunguya", 'E');
        assertTrue(p1.compareTo(p2) < 0);
    }

    /**
     * Verifica que compareTo retorne 0 cuando ambos pacientes tienen la misma prioridad.
     */
    @Test
    public void testCompareToIgualPrioridad() {
        Paciente p1 = new Paciente("Maria", "apendicitis", 'A');
        Paciente p2 = new Paciente("Andrea", "infarto", 'A');
        assertEquals(0, p1.compareTo(p2));
    }

    /**
     * Verifica que un paciente puede ser insertado correctamente en el heap.
     */
    @Test
    public void testInsertarUnElemento() {
        Paciente p = new Paciente("Luis", "dolor", 'D');
        heap.insert(p);
        assertFalse(heap.isEmpty());
    }

    /**
     * Verifica que un paciente insertado puede ser extraído correctamente y en el orden adecuado.
     */
    @Test
    public void testInsertarYExtraerElemento() {
        Paciente p = new Paciente("Luis", "dolor", 'D');
        heap.insert(p);
        Paciente extraido = heap.remove();
        assertEquals('D', extraido.getPrioridad());
    }

    /**
     * Verifica que el orden de atención de los pacientes sea según su prioridad (A > B > C).
     */
    @Test
    public void testOrdenDeAtencionCorrecto() {
        Paciente p1 = new Paciente("Juan", "pierna", 'C');
        Paciente p2 = new Paciente("Maria", "apendicitis", 'A');
        Paciente p3 = new Paciente("Carmen", "parto", 'B');

        heap.insert(p1);
        heap.insert(p2);
        heap.insert(p3);

        assertEquals('A', heap.remove().getPrioridad()); // A
        assertEquals('B', heap.remove().getPrioridad()); // B
        assertEquals('C', heap.remove().getPrioridad()); // C
    }

    /**
     * Verifica que el heap quede vacío después de remover el único elemento insertado.
     */
    @Test
    public void testHeapVacioDespuesDeRemoverTodo() {
        heap.insert(new Paciente("Pedro", "dolor abdominal", 'D'));
        heap.remove();
        assertTrue(heap.isEmpty());
    }
}

