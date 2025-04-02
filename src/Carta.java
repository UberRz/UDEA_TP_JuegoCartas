import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Carta {

    private int indice;

    public Carta(Random r) {
        // el indice de la carta debe ser un numero al azar entre 1 y 52
        indice = r.nextInt(52) + 1;
    }

    public int getIndice() {
        return indice;
    }

    public void mostrar(JPanel pnl, int x, int y) {

        String nombreArchivo = "/imagenes/CARTA" + indice + ".jpg";
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(nombreArchivo));

        JLabel lblCarta = new JLabel();
        lblCarta.setIcon(imgCarta);
        lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight());
        pnl.add(lblCarta);

        lblCarta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, getNombre() + " de " + getPinta() + "y vale" + getValor());
            }
        });
    }

    public Pinta getPinta() {
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta getNombre() {
        int residuo = indice % 13;
        if (residuo == 0) {
            residuo = 13;
        }
        return NombreCarta.values()[residuo - 1];
    }

    public int getValor() {

        int[] valorCarta = new int[] { 10, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

        int residuo = indice % 13;
        if (residuo == 0) {
            residuo = 13;
        }

        return valorCarta[residuo - 1];
    }

    @Override
    public String toString() {
        return "Carta [indice=" + indice + ", getPinta()=" + getPinta() + ", getNombre()=" + getNombre() + "]";
    }

}
