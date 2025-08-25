/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_2_p2;

import java.util.Scanner;

/**
 *
 * @author paddy
 */
public class Ejercicio7 {
    public static void ValidacionNotaCeroYDiez() {
        Scanner input = new Scanner(System.in);
        
        int nota;
        
        do {
            System.out.println("Ingrese una nota entre 0 y 10: ");
            nota = input.nextInt();
            if (nota <0 || nota > 10) {
                System.out.println("Error!. Nota invalida. Ingrese una nota valida.");
            }
        } while (nota < 0 || nota > 10);
        
        System.out.println("Nota guardada exitosamente. ");
    }
}
