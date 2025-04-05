package ht;

/**
 * Clase que representa un paciente en la sala de emergencias.
 * Implementa la interfaz Comparable para ordenar por prioridad.
 */
public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char prioridad;

    /**
     * Constructor para crear un objeto Paciente.
     * @param nombre Nombre del paciente.
     * @param sintoma Descripción del síntoma.
     * @param prioridad Código de emergencia (A-E) donde A es la mayor prioridad.
     */
    public Paciente(String nombre, String sintoma, char prioridad) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.prioridad = prioridad;
    }

    /**
     * Obtiene la prioridad del paciente.
     * @return Código de emergencia del paciente.
     */
    public char getPrioridad() {
        return prioridad;
    }

    /**
     * Compara dos pacientes según su prioridad.
     * @param otro Otro paciente a comparar.
     * @return Valor negativo si este paciente tiene mayor prioridad, positivo si tiene menor.
     */
    @Override
    public int compareTo(Paciente otro) {
        return Character.compare(this.prioridad, otro.prioridad);
    }

    /**
     * Representación en cadena del paciente.
     * @return Cadena con el nombre, síntoma y prioridad del paciente.
     */
    @Override
    public String toString() {
        return nombre + ", " + sintoma + ", " + prioridad;
    }
}