package ht;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Clase principal que gestiona la lectura de pacientes desde un archivo y su atención según prioridad.
 */
public class App {
    public static void main(String[] args) {
        VectorHeap<Paciente> hospitalQueue = new VectorHeap<>();

        // Lectura de pacientes desde el archivo
        try (BufferedReader br = new BufferedReader(new FileReader("hospital/src/main/java/ht/pacientes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(", ");
                if (datos.length == 3) { // Verificar que hay exactamente 3 elementos
                    hospitalQueue.insert(new Paciente(datos[0], datos[1], datos[2].charAt(0)));
                } else {
                    System.err.println("Formato incorrecto en la línea: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Procesamiento y atención de pacientes en orden de prioridad
        System.out.println("Orden de atención de pacientes:");
        while (!hospitalQueue.isEmpty()) {
            System.out.println(hospitalQueue.remove());
        }
    }
}
