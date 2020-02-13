/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package registrolluvias;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joaquin Pereira Chapel
 */
public class Pluviometro extends Thread{
    private final int idPluviometro;
    private static final Dato[] REGISTROS = new Dato[24];
    private static final Semaphore PERMISOS_ESCRITURA = new Semaphore(24);
    private static final Semaphore PERMISOS_LECTURA = new Semaphore(0);
    private static int horaProximaEscritura = 0;
    private static int horaProximaLectura = 0;
    

    public Pluviometro(int idPluviometro) {
        this.idPluviometro = idPluviometro;
    }

    private void escribir(int medida) throws InterruptedException {
        PERMISOS_ESCRITURA.acquire();
        
        System.out.println(String.format("Pluviómetro %d: registrados %d mm/m2 a las %d horas", 
                idPluviometro, medida, horaProximaEscritura));
        REGISTROS[horaProximaEscritura] = new Dato(horaProximaEscritura, medida);
        horaProximaEscritura = (horaProximaEscritura + 1) % 24;
        
        PERMISOS_LECTURA.release();
    }

    public Dato leer() throws InterruptedException {
        PERMISOS_LECTURA.acquire();
        
        Dato dato = REGISTROS[horaProximaLectura];
        horaProximaLectura = (horaProximaLectura + 1) % 24;
        
        PERMISOS_ESCRITURA.release();
        return dato;
    }

    @Override
    public void run() {
        Random random = new Random();
        
        while (true) {
            try {
                escribir(random.nextInt(100));
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Pluviometro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
