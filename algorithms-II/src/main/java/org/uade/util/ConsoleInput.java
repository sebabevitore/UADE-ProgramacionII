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

    public static int readOption(String mensaje) {
        return readInt(mensaje);
    }

    public static void waitEnter() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
