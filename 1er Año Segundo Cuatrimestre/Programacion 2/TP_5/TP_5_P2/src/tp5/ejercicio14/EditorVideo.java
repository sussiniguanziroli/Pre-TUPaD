/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio14;

/**
 *
 * @author paddy
 */
public class EditorVideo {
    
    public void exportar(String formato, Proyecto proyecto) {
        Render nuevoRender = new Render(formato, proyecto);
        System.out.println("Exportando proyecto...");
        System.out.println("Render finalizado:");
        System.out.println(nuevoRender.toString());
    }
}