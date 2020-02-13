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
public class RegistroLluvias {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Estadistica estadistica = new Estadistica(0);
        Pluviometro pluviometro = new Pluviometro(0);
        Lector lector = new Lector(estadistica, pluviometro, 0);
        
        pluviometro.start();
        lector.start();
    }
    
}
