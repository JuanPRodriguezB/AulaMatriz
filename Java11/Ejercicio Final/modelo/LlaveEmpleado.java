package modelo;

public class LlaveEmpleado {
    //Esta clase pretende ser usada en el hashmap de los empleados como una llave compuesta
    private String tipoDocumento;
    private int numeroDocumento;

    public LlaveEmpleado(String tipoDocumento, int numeroDocumento) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }
}
