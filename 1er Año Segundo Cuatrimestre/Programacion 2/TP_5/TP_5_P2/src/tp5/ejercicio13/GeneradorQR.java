/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio13;

/**
 *
 * @author paddy
 */
public class GeneradorQR {

    public void generar(String valor, Usuario usuario) {
        CodigoQR nuevoCodigo = new CodigoQR(valor, usuario);
        System.out.println("Se ha generado un nuevo Codigo QR:");
        System.out.println(nuevoCodigo.toString());
    }
}