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
        for (int i = 0; i < indiceCartas.length; i++) {
            if (indiceCartas[i] <= 13) {
                listaTrebol[TOTAL_DATOS_TREBOL] = indiceCartas[i];
                TOTAL_DATOS_TREBOL++;
            } else if (indiceCartas[i] <= 26) {
                listaPica[TOTAL_DATOS_PICA] = indiceCartas[i];
                TOTAL_DATOS_PICA++;
            } else if (indiceCartas[i] <= 39) {
                listaCorazon[TOTAL_DATOS_CORAZON] = indiceCartas[i];
                TOTAL_DATOS_CORAZON++;
            } else {
                listaDiamante[TOTAL_DATOS_DIAMANTE] = indiceCartas[i];
                TOTAL_DATOS_DIAMANTE++;
            }
        }

        // se quitan duplicados de las listas de pintas
        int[] trebolSinDuplicados = Jugador.quitarDuplicados(listaTrebol);
        int[] picaSinDuplicados = Jugador.quitarDuplicados(listaPica);
        int[] corazonSinDuplicados = Jugador.quitarDuplicados(listaCorazon);
        int[] diamanteSinDuplicados = Jugador.quitarDuplicados(listaDiamante);

        mensaje = "Se encontraron las siguientes escaleras de Trebol:\n";

        int l = 0;
        while (l < trebolSinDuplicados.length - 1) {
            int inicio = l;
            while (l < trebolSinDuplicados.length - 1 && trebolSinDuplicados[l + 1] == trebolSinDuplicados[l] + 1) {
                l++;
            }
            if (l > inicio) {
                for (int j = inicio; j <= l; j++) {
                    int indice1 = trebolSinDuplicados[j] % 13;

                    if (indice1 != 0) {
                        mensaje += NombreCarta.values()[(trebolSinDuplicados[j] % 13) - 1] + "+\n";
                    } else if (indice1 == 0) {
                        mensaje += NombreCarta.values()[12] + " _\n";

                    }

                }
            } else {
                l++;
            }

        }

        mensaje += "Se encontraron las siguientes escaleras de Pica:\n";
        int k = 0;
        while (k < picaSinDuplicados.length - 1) {
            int inicio = k;
            while (k < picaSinDuplicados.length - 1 && picaSinDuplicados[k + 1] == picaSinDuplicados[k] + 1) {
                k++;
            }
            if (k > inicio) {
                for (int j = inicio; j <= k; j++) {
                    int indice1 = picaSinDuplicados[j] % 13;

                    if (indice1 != 0) {
                        mensaje += NombreCarta.values()[(picaSinDuplicados[j] % 13) - 1] + "+\n";
                    } else if (indice1 == 0) {
                        mensaje += NombreCarta.values()[12] + " _\n";

                    }

                }
            } else {
                k++;
            }

        }
        mensaje += "Se encontraron las siguientes escaleras de Corazon:\n";
        int m = 0;
        while (m < corazonSinDuplicados.length - 1) {
            int inicio = m;
            while (m < corazonSinDuplicados.length - 1 && corazonSinDuplicados[m + 1] == corazonSinDuplicados[m] + 1) {
                m++;
            }
            if (m > inicio) {
                for (int j = inicio; j <= m; j++) {
                    int indice1 = corazonSinDuplicados[j] % 13;

                    if (indice1 != 0) {
                        mensaje += NombreCarta.values()[(corazonSinDuplicados[j] % 13) - 1] + "+\n";
                    } else if (indice1 == 0) {
                        mensaje += NombreCarta.values()[12] + " _\n";

                    }

                }
            } else {
                m++;
            }

        }
        mensaje += "Se encontraron las siguientes escaleras de Diamante:\n";
        int n = 0;
        while (n < diamanteSinDuplicados.length - 1) {
            int inicio = n;
            while (n < diamanteSinDuplicados.length - 1
                    && diamanteSinDuplicados[n + 1] == diamanteSinDuplicados[n] + 1) {
                n++;
            }
            if (n > inicio) {
                for (int j = inicio; j <= n; j++) {
                    int indice1 = diamanteSinDuplicados[j] % 13;

                    if (indice1 != 0) {
                        mensaje += NombreCarta.values()[(diamanteSinDuplicados[j] % 13) - 1] + "+\n";
                    } else if (indice1 == 0) {
                        mensaje += NombreCarta.values()[12] + " _\n";

                    }

                }
            } else {
                n++;
            }

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

    public static int[] quitarDuplicados(int[] a) {

        int[] temp = new int[a.length];
        int contador = 0;

        for (int i = 0; i < a.length; i++) {
            boolean yaEsta = false;
            if (a[i] == 0)
                continue;
            for (int j = 0; j < contador; j++) {
                if (a[i] == temp[j]) {
                    yaEsta = true;
                    break;
                }
            }
            if (!yaEsta) {
                temp[contador] = a[i];
                contador++;
            }
        }
        int[] sinDuplicados = new int[contador];
        for (int i = 0; i < contador; i++) {
            sinDuplicados[i] = temp[i];
        }
        return sinDuplicados;
    }

}
