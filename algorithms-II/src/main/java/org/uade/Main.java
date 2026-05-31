package org.uade;

import org.uade.service.*;
import org.uade.view.Menu;

public class Main {
    public static void main(String[] args) {
        FlotaService flota = new FlotaService();
        ViajeService prioridad = new ViajeService();

        Menu menu = new Menu();
        menu.launch();


    }
}
