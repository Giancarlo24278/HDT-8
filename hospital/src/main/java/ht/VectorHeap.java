package ht;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Implementación de una cola de prioridad utilizando un heap.
 * @param <E> Tipo de elementos que deben ser comparables.
 */
public class VectorHeap<E extends Comparable<E>> {
    private List<E> heap;

    /**
     * Constructor que inicializa el heap como una lista vacía.
     */
    public VectorHeap() {
        heap = new ArrayList<>();
    }

    /**
     * Inserta un elemento en la cola de prioridad.
     * @param item Elemento a insertar.
     */
    public void insert(E item) {
        heap.add(item);
        Collections.sort(heap);
    }

    /**
     * Retira y devuelve el elemento con la mayor prioridad.
     * @return Elemento con la mayor prioridad o null si la cola está vacía.
     */
    public E remove() {
        if (heap.isEmpty()) return null;
        return heap.remove(0);
    }

    /**
     * Verifica si la cola de prioridad está vacía.
     * @return true si está vacía, false en caso contrario.
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
