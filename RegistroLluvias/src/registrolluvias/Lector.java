/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registrolluvias;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Lector extends Thread{
    private final Estadistica estadistica;
    private final Pluviometro pluviometro;
    private final int idLector;

    public Lector(Estadistica estadistica, Pluviometro pluviometro, int idLector) {
        this.estadistica = estadistica;
        this.pluviometro = pluviometro;
        this.idLector = idLector;
    }

    @Override
    public void run() {
        while(true){
            try {
                Dato dato = pluviometro.leer();
                System.out.println(String.format("Lector %d: leidos %d mm/m2 a las %d horas", idLector, dato.getMedida(), dato.getHora()));
                estadistica.acumular(dato);
            } catch (InterruptedException ex) {
                Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
}
