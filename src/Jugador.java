import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import javax.swing.JPanel;

public class Jugador {

    private int TOTAL_CARTAS = 10;
    private int MARGEN = 10;
    private int DISTANCIA = 40;

    public Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Random r = new Random(); // la suerte del jugador

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);
            Arrays.sort(cartas);
        }

    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();

        int posicion = MARGEN + (TOTAL_CARTAS - 1) * DISTANCIA;
        for (Carta carta : cartas) {
            carta.mostrar(pnl, posicion, MARGEN);
            posicion -= DISTANCIA;
        }

        pnl.repaint();
    }

    public String getGrupos() {
        String mensaje = "No se encontraron grupos";

        int[] contadores = new int[NombreCarta.values().length];
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;
        }

        boolean hayGrupos = false;
        for (int contador : contadores) {
            if (contador >= 2) {
                hayGrupos = true;
                break;
            }
        }

        if (hayGrupos) {
            mensaje = "Se encontraron los siguientes grupos:\n";
            int fila = 0;
            for (int contador : contadores) {
                if (contador >= 2) {
                    mensaje += Grupo.values()[contador] + " de " + NombreCarta.values()[fila] + "\n";
                }
                fila++;
            }
        }

        return mensaje;
    }

    public String getEscaleras() {
        int TAMAÑO_INICIAL = 15;
        int totalDatos = 0;
        String mensaje = "No se encontraron Escaleras";
        int[] indiceCartas = new int[cartas.length];
        int[] listaEscalera = new int[TAMAÑO_INICIAL];
        int[] gruposEscalera = new int[TAMAÑO_INICIAL];
        int fila = 0;

        for (Carta carta : cartas) {
            indiceCartas[fila] = carta.getIndice();
            fila++;

        }
        Jugador.ordenarIndices(indiceCartas);
        for (int j = 0; j < indiceCartas.length; j++) {
            System.out.println(indiceCartas[j]);

        }
        System.out.println("siguiente");

        // si estan en escalera agregar a listaEscalera
        for (int i = 0; i < indiceCartas.length - 1; i++) {

            if (indiceCartas[i] + 1 == indiceCartas[i + 1]) {
                listaEscalera[totalDatos] = indiceCartas[i - 1];
                totalDatos++;
                listaEscalera[totalDatos + 1] = indiceCartas[i];

            }

        }
        for (int j = 0; j < totalDatos; j++) {
            System.out.println(listaEscalera[j]);

        }

        // se ordena el arreglo de indices

        // para verificar las listas creadas

        // boolean hayGrupos = false;
        // for (int contador : contadores) {
        // if (contador >= 2) {
        // hayGrupos = true;
        // break;
        // }
        // }

        // if (hayGrupos) {
        // mensaje = "Se encontraron los siguientes grupos:\n";
        // int fila = 0;
        // for (int contador : contadores) {
        // if (contador >= 2) {
        // mensaje += Grupo.values()[contador] + " de " + NombreCarta.values()[fila] +
        // "\n";
        // }
        // fila++;
        // }
        // }

        return mensaje;
    }

    public int getPuntaje() {
        int puntaje = 0;
        // aqui se crea la lista de contadores de tamaño 13
        int[] contadores = new int[NombreCarta.values().length];
        int[] contadoresEscalera = new int[NombreCarta.values().length];
        // si la carta aparece en una posición , se incrementa el contador en el nro de
        // posición del nombbre
        for (Carta carta : cartas) {
            contadores[carta.getNombre().ordinal()]++;

        }
        // se recorre las cartas y si la lista contadores en la posición de la carta
        // está menos
        // de 2 veces ,trae el valor de esa carta y la suma en el puntaje.
        for (Carta carta : cartas) {
            if (contadores[carta.getNombre().ordinal()] < 2) {
                puntaje += carta.getValor();
                // System.out.println(puntaje);
            }
        }

        return puntaje;

    }

    public static void ordenarIndices(int[] a) {
        int n = a.length - 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                if (a[j] > a[j + 1]) {
                    int aux = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = aux;
                }
            }
        }
    }

}
