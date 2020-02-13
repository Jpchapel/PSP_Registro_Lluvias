/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registrolluvias;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Estadistica {
    private int idEstadistica;
    private Dato[] datos;
    private Semaphore[] semaforos;

    public Estadistica(int idEstadistica) {
        this.idEstadistica = idEstadistica;
        datos = new Dato[24];
        semaforos = new Semaphore[24];
        
        for (int i = 0; i < 24; i++) {
            datos[i] = new Dato(i, 0);
            semaforos[i] = new Semaphore(1);
        }
    }
    
    public void acumular(Dato dato) throws InterruptedException {
        
        semaforos[dato.getHora()].acquire();
        Dato cambio = datos[dato.getHora()];
        cambio.setMedida(cambio.getMedida() + dato.getMedida());
        semaforos[dato.getHora()].release();
    }
    
}
