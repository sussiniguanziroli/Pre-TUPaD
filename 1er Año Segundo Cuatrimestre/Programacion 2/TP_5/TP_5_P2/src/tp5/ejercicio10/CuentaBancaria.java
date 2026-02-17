/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp5.ejercicio10;

/**
 *
 * @author paddy
 */
public class CuentaBancaria {
    private String cbu;
    private double saldo;
    private ClaveSeguridad clave;
    private Titular titular;

    public CuentaBancaria(String cbu, double saldo, int codigoClave, String fechaModificacion) {
        this.cbu = cbu;
        this.saldo = saldo;
        this.clave = new ClaveSeguridad(codigoClave, fechaModificacion);
    }

    public String getCbu() {
        return cbu;
    }

    public double getSaldo() {
        return saldo;
    }

    public ClaveSeguridad getClave() {
        return clave;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        if (this.titular != titular) {
            this.titular = titular;
            if (titular != null) {
                titular.setCuenta(this);
            }
        }
    }

    @Override
    public String toString() {
        String nombreTitular = (titular != null) ? titular.getNombre() : "N/A";
        return "CuentaBancaria [cbu=" + cbu + ", saldo=" + saldo + ", titular=" + nombreTitular + 
               ",\n  " + clave.toString() + "]";
    }
}