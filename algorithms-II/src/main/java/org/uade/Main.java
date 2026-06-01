package org.uade;

import org.uade.entity.Micro;
import org.uade.entity.Ruta;
import org.uade.entity.Tipo;
import org.uade.entity.Viaje;
import org.uade.service.*;
import org.uade.util.ConsoleInput;
import org.uade.view.Menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        System.out.println("----------------PRECARGA DEMO----------------");

        Menu menu = new Menu();
        menu.launch();


    }
}
