
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

public class ParcialFinal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] weekDays = {"lunes", "martes", "miercoles", "jueves", "viernes", "sabado", "domingo"};
        String[][] data = new String[7][100];

        for (int i = 0; i < 7; i++) {
            String dia = weekDays[i];
            String fileName = "C:\\Users\\juanp\\Downloads\\" + dia + ".txt";
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line;
                int a = 0;
                while ((line = br.readLine()) != null) {
                    data[i][a] = line;
                    a++;
                }
                br.close();
            } catch (Exception e) {
                System.out.println("Error al leer el archivo de " + dia + ": " + e.getMessage());
            }
        }

        while (true) {
            System.out.println("Bienvenido, ¿qué deseas hacer?");
            System.out.println("1. Día de la semana en que más se movió el dinero");
            System.out.println("2. Hora del día en que más se movió el dinero");
            System.out.println("3. Encontrar información por el ID");
            System.out.println("4. Para ver datos");
            System.out.println("5. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    double maxValor = Double.MIN_VALUE;
                    String diaMaxValor = "";

                    for (int i = 0; i < 7; i++) {
                        for (int j = 0; j < data[i].length; j++) {
                            if (data[i][j] != null) {
                                String[] parts = data[i][j].split(";");
                                if (parts.length >= 2) {
                                    double valor = Double.parseDouble(parts[1]);
                                    if (valor > maxValor) {
                                        maxValor = valor;
                                        diaMaxValor = weekDays[i];
                                    }
                                }
                            }
                        }
                    }

                    if (!diaMaxValor.isEmpty()) {
                        System.out.println("El día de la semana con el mayor valor de transacción es " + diaMaxValor);
                        System.out.println("El valor máximo es: " + maxValor);
                    }
                    break;
                case 2:
                    findMaxColumn2Value(data);
                    break;
                case 3:
                    System.out.print("Ingrese un ID para buscar información: ");
                    String idToFind = scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Datos disponibles:");
                    for (int i = 0; i < 7; i++) {
                        System.out.println(weekDays[i] + ": ");
                        for (int a = 0; a < data[i].length; a++) {
                            if (data[i][a] != null) {
                                System.out.println(data[i][a]);
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("Saliendo del programa.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private static void findMaxColumn2Value(String[][] data) {
        double maxColumn2Value = Double.MIN_VALUE;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != null) {
                    String[] parts = data[i][j].split(";");
                    if (parts.length >= 5) {
                        try {
                            double column2Value = Double.parseDouble(parts[4]);
                            if (column2Value > maxColumn2Value) {
                                maxColumn2Value = column2Value;
                            }
                        } catch (NumberFormatException e) {
                            // Ignore invalid values in column 2
                        }
                    }
                }
            }
        }

        System.out.println("El mayor valor en la columna 2 es: " + maxColumn2Value);
    }
}
