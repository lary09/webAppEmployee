import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {

    public static void main(String[] args) {
        String fileName = "data.csv";
        int numberOfRecords = 1000000;
        int startId = 50;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < numberOfRecords; i++) {
                int id = startId + i;
                // Generar un nombre aleatorio
                String name = generateRandomName();
                // Generar un apellido aleatorio
                String lastName = generateRandomLastName();
                // Generar un salario aleatorio entre $2000 y $4000
                double salary = 2000 + new Random().nextInt(2001);
                // Formatear el salario
                String formattedSalary = String.format("$%.2f", salary);
                // Generar un ID aleatorio del 1 al 5 para el departamento
                int departmentId = new Random().nextInt(5) + 1;
                // Escribir en el archivo
                writer.write(id + ", " + name + ", " + lastName + ", " + formattedSalary + ", " + departmentId + "\n");
            }
            System.out.println("Archivo generado exitosamente: " + fileName);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    // Método para generar un nombre aleatorio
    private static String generateRandomName() {
        String[] names = {"Antonella", "Sofía", "Daniel", "Carolina", "Alejandro", "Patricia", "Miguel", "Andrea", "Javier", "Paula"};
        return names[new Random().nextInt(names.length)];
    }

    // Método para generar un apellido aleatorio
    private static String generateRandomLastName() {
        String[] lastNames = {"López", "Díaz", "Sánchez", "Ramírez", "Torres", "Flores", "Romero", "Ruiz", "Gómez", "Vásquez"};
        return lastNames[new Random().nextInt(lastNames.length)];
    }
}
