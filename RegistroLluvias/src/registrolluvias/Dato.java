/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registrolluvias;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Dato {
   private final int hora;
   private int medida;

    public Dato(int hora, int medida) {
        this.hora = hora;
        this.medida = medida;
    }

    public int getHora() {
        return hora;
    }

    public int getMedida() {
        return medida;
    }

    public void setMedida(int medida) {
        this.medida = medida;
    }
}
