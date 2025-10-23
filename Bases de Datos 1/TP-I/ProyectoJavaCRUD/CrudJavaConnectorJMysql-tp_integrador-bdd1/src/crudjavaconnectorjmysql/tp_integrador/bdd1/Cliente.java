public class Cliente {

    private int idCliente;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private int idLocalidad;

    public Cliente() {
    }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public int getIdLocalidad() { return idLocalidad; }
    public void setIdLocalidad(int idLocalidad) { this.idLocalidad = idLocalidad; }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + idCliente + ", nombre='" + nombre + '\'' + ", email='" + email + '\'' + '}';
    }
}