/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio14;

/**
 *
 * @author paddy
 */
public class Main {
    public static void main(String[] args) {

        Proyecto miVideo = new Proyecto("Vacaciones 2025", 15);
        
        EditorVideo editor = new EditorVideo();
        
        System.out.println("--- Probando la Dependencia de Creacion ---");
        editor.exportar("MP4", miVideo);
    }
}
