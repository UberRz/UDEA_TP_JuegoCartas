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

        int TOTAL_DATOS_TREBOL = 0;
        int TOTAL_DATOS_PICA = 0;
        int TOTAL_DATOS_CORAZON = 0;
        int TOTAL_DATOS_DIAMANTE = 0;
        String mensaje = "No se encontraron Escaleras";
        int[] indiceCartas = new int[cartas.length];
        int[] listaTrebol = new int[10];
        int[] listaPica = new int[10];
        int[] listaCorazon = new int[10];
        int[] listaDiamante = new int[10];
        int fila = 0;

        // Se llena una lista con índices de las cartas repartidas
        for (Carta carta : cartas) {
            indiceCartas[fila] = carta.getIndice();
            fila++;

        }
        // se ordena la lista de índices
        Jugador.ordenarIndices(indiceCartas);
        for (int j = 0; j < indiceCartas.length; j++) {
            System.out.println(indiceCartas[j]);

        }
        System.out.println("siguiente");

        // Se separan las cartas por pinta
        for (int i = 1; i < indiceCartas.length; i++) {
            if (indiceCartas[i] <= 13) {
                listaTrebol[TOTAL_DATOS_TREBOL] = indiceCartas[i];
                TOTAL_DATOS_TREBOL++;
            } else if (13 < indiceCartas[i] && indiceCartas[i] <= 26) {
                listaPica[TOTAL_DATOS_PICA] = indiceCartas[i];
                TOTAL_DATOS_PICA++;
            } else if (26 < indiceCartas[i] && indiceCartas[i] <= 39) {
                listaCorazon[TOTAL_DATOS_CORAZON] = indiceCartas[i];
                TOTAL_DATOS_CORAZON++;
            } else {
                listaDiamante[TOTAL_DATOS_DIAMANTE] = indiceCartas[i];
                TOTAL_DATOS_DIAMANTE++;
            }
        }
        mensaje = "Se encontraron las siguientes escaleras de Trebol:\n";
        for (int k = 0; k < listaTrebol.length - 1; k++) {

            if (listaTrebol[k] + 1 == listaTrebol[k + 1]) {
                int indice1 = listaTrebol[k] % 13;
                if (indice1 != 0) {
                    mensaje += NombreCarta.values()[(listaTrebol[k] % 13) - 1] + "+"
                            + NombreCarta.values()[(listaTrebol[k + 1] % 13) - 1] + " _";
                } else {
                    mensaje += NombreCarta.values()[12];

                }
            }

        }
        mensaje += "Se encontraron las siguientes escaleras de Pica:\n";
        for (int k = 0; k < listaPica.length - 1; k++) {

            if (listaPica[k] + 1 == listaPica[k + 1]) {
                int indice1 = listaPica[k] % 13;
                if (indice1 != 0) {
                    mensaje += NombreCarta.values()[(listaPica[k] % 13) - 1] + "+"
                            + NombreCarta.values()[(listaPica[k + 1] % 13) - 1] + " _";
                } else {
                    mensaje += NombreCarta.values()[12];

                }
            }

        }
        mensaje += "Se encontraron las siguientes escaleras de Corazon:\n";
        for (int k = 0; k < listaCorazon.length - 1; k++) {

            if (listaCorazon[k] + 1 == listaCorazon[k + 1]) {
                int indice1 = listaCorazon[k] % 13;
                if (indice1 != 0) {
                    mensaje += NombreCarta.values()[(listaCorazon[k] % 13) - 1] + "+"
                            + NombreCarta.values()[(listaCorazon[k + 1] % 13) - 1] + " _";
                } else {
                    mensaje += NombreCarta.values()[12];

                }
            }

        }
        mensaje += "Se encontraron las siguientes escaleras de Diamante:\n";
        for (int k = 0; k < listaDiamante.length - 1; k++) {

            if (listaDiamante[k] + 1 == listaDiamante[k + 1]) {
                int indice1 = listaDiamante[k] % 13;
                if (indice1 != 0) {
                    mensaje += NombreCarta.values()[(listaDiamante[k] % 13) - 1] + "+"
                            + NombreCarta.values()[(listaDiamante[k + 1] % 13) - 1] + " _";
                } else {
                    mensaje += NombreCarta.values()[12];

                }
            }

        }
        for (int i = 0; i < listaCorazon.length; i++) {
            System.out.println(listaCorazon[i]);

        }

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
