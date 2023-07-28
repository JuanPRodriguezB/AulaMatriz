package modelo;

import java.time.LocalDate;
import java.util.List;

public class EmpleadoTiempoCompleto extends BaseEmpleado {


    private  double salario;

    private  boolean primaExtralegal;

    public EmpleadoTiempoCompleto(String nombre, String apellido, String tipoDocumento, int documento, int telefono, String nacionalidad, char genero, boolean activo, LocalDate fechaNacimiento, List<Ocupacion> ocupacionList) {
        super(nombre, apellido, tipoDocumento, documento, telefono, nacionalidad, genero, activo, fechaNacimiento, ocupacionList);
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isPrimaExtralegal() {
        return primaExtralegal;
    }

    public void setPrimaExtralegal(boolean primaExtralegal) {
        this.primaExtralegal = primaExtralegal;
    }
}
