package modelo;

import java.time.LocalDate;
import java.util.List;

public class EmpleadoTiempoMedio extends BaseEmpleado {

    private double valorHora;
    private  double horasTrabajas;

    public EmpleadoTiempoMedio(String nombre, String apellido, String tipoDocumento, int documento, int telefono, String nacionalidad, char genero, boolean activo, LocalDate fechaNacimiento, List<Ocupacion> ocupacionList, double valorHora, double horasTrabajas) {
        super(nombre, apellido, tipoDocumento, documento, telefono, nacionalidad, genero, activo, fechaNacimiento, ocupacionList);
        this.valorHora = valorHora;
        this.horasTrabajas = horasTrabajas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public double getHorasTrabajas() {
        return horasTrabajas;
    }

    public void setHorasTrabajas(double horasTrabajas) {
        this.horasTrabajas = horasTrabajas;
    }
}
