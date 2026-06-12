package org.uade.util;

import java.util.Scanner;

public class ConsoleInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(String mensaje) {
        System.out.print(mensaje + " ");
        return scanner.nextLine().trim();
    }

    public static int readInt(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje + " ");
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (Exception e) {
                System.out.println("❌ Error: Por favor, ingrese un número entero válido.");
                scanner.nextLine();
            }
        }
    }

    // NUEVO: Validación de enteros estrictamente positivos (> 0)
    public static int readPositiveInt(String mensaje) {
        while (true) {
            int valor = readInt(mensaje);
            if (valor > 0) {
                return valor;
            } else {
                System.out.println("❌ Error: El número debe ser mayor a 0.");
            }
        }
    }

    public static int readOption(String mensaje) {
        return readInt(mensaje);
    }

    public static void waitEnter() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }

    // NUEVO: Validación con Regex que respeta el formato original (Ej: Nombres de ciudades)
    public static String readRegexString(String mensaje, String regex, String mensajeError) {
        while (true) {
            System.out.print(mensaje + " ");
            String input = scanner.nextLine().trim();
            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println("❌ Error: " + mensajeError);
            }
        }
    }

    // NUEVO: Validación con Regex que fuerza MAYÚSCULAS (Ej: Patentes, Códigos de Terminal)
    public static String readRegexStringUpper(String mensaje, String regex, String mensajeError) {
        while (true) {
            System.out.print(mensaje + " ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (input.matches(regex)) {
                return input;
            } else {
                System.out.println("❌ Error: " + mensajeError);
            }
        }
    }
}
