package modelo;

import java.time.LocalDate;
import java.util.List;

public abstract class BaseEmpleado implements IEmpleado {
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private int documento;
    private int telefono;
    private String nacionalidad;
    private char genero;
    private boolean activo;
    private LocalDate fechaNacimiento;
    private List<Ocupacion> ocupacionList;

    public BaseEmpleado(String nombre, String apellido, String tipoDocumento, int documento, int telefono, String nacionalidad, char genero, boolean activo,
                        LocalDate fechaNacimiento, List<Ocupacion> ocupacionList) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.telefono = telefono;
        this.nacionalidad = nacionalidad;
        this.genero = genero;
        this.activo = activo;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacionList = ocupacionList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public final double getEdad(){
        int resultado;
        resultado = LocalDate.now().getYear() - getFechaNacimiento().getYear();
        return resultado;
    }

    public List<Ocupacion> getOcupacionList() {
        return ocupacionList;
    }

    public void setOcupacionList(List<Ocupacion> ocupacionList) {
        this.ocupacionList = ocupacionList;
    }
}
