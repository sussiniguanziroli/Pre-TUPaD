/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio02;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {
        Bateria bateriaExterna = new Bateria("Litio", "5000mAh");
        Usuario usuario = new Usuario("Ana Gomez", "35987654");

        Celular celular = new Celular("123456789012345", "Samsung", "S23", bateriaExterna);
        
        usuario.setCelular(celular);

        System.out.println("--- PRUEBA DE METODOS ---");
        System.out.println(usuario);
        System.out.println(celular);
        System.out.println("La bateria puede existir por separado: " + bateriaExterna.toString());
    }
}
