package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import com.aulamatriz.programer.clase.resumen.ejercicio.modelo.EnumTipoDocumento;

public class EmpleadoService {

    //Listas
    @SuppressWarnings("final")
    private HashMap<LlaveEmpleado,EmpleadoTiempoCompleto> empleadosTiempoCompleto = new HashMap<>();
    @SuppressWarnings("final")
    private HashMap<LlaveEmpleado,EmpleadoTiempoMedio> empleadosTiempoMedio = new HashMap<>();

    //Metodos para la creacion de empleados

    /**
     * Se encarga de solicitar al usuario todos los datos necesarios para la creacion de un empleado a tiempo completo.
     * La funcion verificara que no existan empleados con el mismo tipo de documento y numero en la lista de empleados
     * a tiempo completo y en la lista de empleados de medio tiempo.
     * @param scanner
     * @throws Exception
     */
    public void crearEmpleadoTiempoCompleto (Scanner scanner) throws Exception{
        String nombre;
        String apellido;
        String tipoDocumento;
        int documento;
        int telefono;
        String nacionalidad;
        char genero;
        boolean activo;
        LocalDate fechaNacimiento;
        List<Ocupacion> ocupacionList = new ArrayList<>();

        tipoDocumento = recibirTipoDocumento(scanner);

        documento = recibirDocumento(scanner);

        //Revisando que no exista un empleado con ese documento y ese tipo de documento
        LlaveEmpleado llaveEmpleado = crearLlaveEmpleado(tipoDocumento,documento);
        verificarEmpleadoTiempoCompletoInexistente(llaveEmpleado);

        System.out.printf(Constantes.MSGSETNOMBRE);
        nombre = scanner.nextLine();

        System.out.printf(Constantes.MSGSETAPELLIDO);
        apellido = scanner.nextLine();

        System.out.printf(Constantes.MSGSETTELEFONO);
        telefono = Integer.parseInt(scanner.nextLine());

        System.out.printf(Constantes.MSGSETNACIONALIDAD);
        nacionalidad = scanner.nextLine();

        System.out.printf(Constantes.MSGSETGENERO);
        String tempGenero= scanner.nextLine().toUpperCase();

        if (tempGenero.equals("M") || tempGenero.equals("F"))
        {
            genero = tempGenero.charAt(0);
        }
        else
        {
            throw new Exception("El genero debe ser M o F. Se ingreso: " + tempGenero+".");
        }

        System.out.printf(Constantes.MSGSETACTIVO);
        activo=(scanner.nextLine().toUpperCase().equals("ACTIVO"))?true:false;

        System.out.printf(Constantes.MSGSETANIONACIMIENTO);
        int tempAnioNacimiento=parseInt((scanner.nextLine()));
        System.out.printf(Constantes.MSGSETMESNACIMIENTO);
        int tempMesNacimiento=parseInt(scanner.nextLine());
        System.out.printf(Constantes.MSGSETDIANACIMIENTO);
        int tempDiaNacimiento=parseInt(scanner.nextLine());
        fechaNacimiento = LocalDate.of(tempAnioNacimiento,tempMesNacimiento,tempDiaNacimiento);

        System.out.printf(Constantes.MSGSETOCUPACIONCODIGO);
        int tempOcupacionCodigo = parseInt(scanner.nextLine());

        System.out.printf(Constantes.MSGSETOCUPACIONNOMBRE);
        String tempOcupacionNombre = scanner.nextLine();

        aniadirOcupacion(tempOcupacionCodigo, tempOcupacionNombre, ocupacionList);

        boolean addOcupaciones = true;
        while (addOcupaciones) {
            System.out.println("¿Desea agregar otra ocupacion? Escriba S/N");
            if (scanner.nextLine().toUpperCase().equals("S")) {
                System.out.println(Constantes.MSGSETOCUPACIONCODIGO);
                tempOcupacionCodigo = parseInt(scanner.nextLine());

                System.out.println(Constantes.MSGSETOCUPACIONNOMBRE);
                tempOcupacionNombre = scanner.nextLine();

                aniadirOcupacion(tempOcupacionCodigo, tempOcupacionNombre, ocupacionList);
            } else {
                addOcupaciones = false;
            }
        }

        empleadosTiempoCompleto.put(llaveEmpleado, new EmpleadoTiempoCompleto(nombre, apellido, tipoDocumento, documento, telefono, nacionalidad, genero, activo, fechaNacimiento, ocupacionList));
        System.out.println("\nEl empleado fue registrado exitosamente.");
        System.out.println("A continuacion se va a mostrar los datos ingresados.\n");
        mostrarEmpleadoInfo(empleadosTiempoCompleto.get(llaveEmpleado));

    }

    public void crearEmpleadoTiempoMedio (Scanner scanner) throws Exception{

        String nombre;
        String apellido;
        String tipoDocumento = null;
        int documento;
        int telefono;
        String nacionalidad;
        char genero;
        boolean activo;
        LocalDate fechaNacimiento;
        List<Ocupacion> ocupacionList = new ArrayList<>();

        scanner.nextLine();


        System.out.printf(Constantes.MSGSETTIPODOCUMENTO);
        EnumTipoDocumento tempTipoDocumento = EnumTipoDocumento.valueOf(scanner.nextLine().toUpperCase());
        switch (tempTipoDocumento)
        {
            case CC:
                tipoDocumento = "CC";
                break;
            case CE:
                tipoDocumento = "CE";
                break;
            case PS:
                tipoDocumento = "PS";
                break;
            case TI:
                tipoDocumento = "TI";
                break;
            default:
                throw new Exception("Se ha ingresado un tipo de documento no valido: "+tempTipoDocumento);
        }

        System.out.printf(Constantes.MSGSETDOCUMENTO);
        documento = Integer.parseInt(scanner.nextLine());

        //Revisando que no exista un empleado con ese documento y ese tipo de documento
        LlaveEmpleado llaveEmpleado = new LlaveEmpleado(tipoDocumento, documento);

        if (empleadosTiempoCompleto.containsKey(llaveEmpleado)){
            throw new Exception("Ya existe un empleado de tiempo completo con "+tipoDocumento+" "+documento+" registrados");
        }

        //Revisando que no exista un empleado con ese documento y ese tipo de documento
        if (empleadosTiempoMedio.containsKey(llaveEmpleado)){
            throw new Exception("Ya existe un empleado de medio tiempo con "+tipoDocumento+" "+documento+" registrados.");
        }

        System.out.printf(Constantes.MSGSETNOMBRE);
        nombre = scanner.nextLine();

        System.out.printf(Constantes.MSGSETAPELLIDO);
        apellido = scanner.nextLine();

        System.out.printf(Constantes.MSGSETTELEFONO);
        telefono = Integer.parseInt(scanner.nextLine());

        System.out.printf(Constantes.MSGSETNACIONALIDAD);
        nacionalidad = scanner.nextLine();

        System.out.printf(Constantes.MSGSETGENERO);
        String tempGenero= scanner.nextLine().toUpperCase();

        if (tempGenero.equals("M") || tempGenero.equals("F"))
        {
            genero = tempGenero.charAt(0);
        }
        else
        {
            throw new Exception("El genero debe ser M o F. Se ingreso: " + tempGenero+".");
        }

        System.out.printf(Constantes.MSGSETACTIVO);
        activo=(scanner.nextLine().toUpperCase().equals("ACTIVO"))?true:false;

        System.out.printf(Constantes.MSGSETANIONACIMIENTO);
        int tempAnioNacimiento=parseInt((scanner.nextLine()));
        System.out.printf(Constantes.MSGSETMESNACIMIENTO);
        int tempMesNacimiento=parseInt(scanner.nextLine());
        System.out.printf(Constantes.MSGSETDIANACIMIENTO);
        int tempDiaNacimiento=parseInt(scanner.nextLine());
        fechaNacimiento = LocalDate.of(tempAnioNacimiento,tempMesNacimiento,tempDiaNacimiento);

        System.out.printf(Constantes.MSGSETOCUPACIONCODIGO);
        int tempOcupacionCodigo = parseInt(scanner.nextLine());

        System.out.printf(Constantes.MSGSETOCUPACIONNOMBRE);
        String tempOcupacionNombre = scanner.nextLine();

        aniadirOcupacion(tempOcupacionCodigo, tempOcupacionNombre, ocupacionList);

        boolean addOcupaciones = true;
        while (addOcupaciones) {
            System.out.println("¿Desea agregar otra ocupacion? Escriba S/N");
            if (scanner.nextLine().toUpperCase().equals("S")) {
                System.out.println(Constantes.MSGSETOCUPACIONCODIGO);
                tempOcupacionCodigo = parseInt(scanner.nextLine());

                System.out.println(Constantes.MSGSETOCUPACIONNOMBRE);
                tempOcupacionNombre = scanner.nextLine();

                aniadirOcupacion(tempOcupacionCodigo, tempOcupacionNombre, ocupacionList);
            } else {
                addOcupaciones = false;
            }
        }

        empleadosTiempoCompleto.put(llaveEmpleado, new EmpleadoTiempoCompleto(nombre, apellido, tipoDocumento, documento, telefono, nacionalidad, genero, activo, fechaNacimiento, ocupacionList));
        System.out.println("El empleado fue registrado exitosamente.");
    }

    /*
        Metodos para buscar los empleados
     */

    public void buscarEmpleadoTiempoCompleto(Scanner scanner) throws Exception {
        String tipoDocumento;
        int documento;

        tipoDocumento = recibirTipoDocumento(scanner);
        documento = recibirDocumento(scanner);

        LlaveEmpleado tempLlave = crearLlaveEmpleado(tipoDocumento, documento);
        System.out.println(tempLlave.getTipoDocumento()+tempLlave.getNumeroDocumento());
        EmpleadoTiempoCompleto empleado = empleadosTiempoCompleto.get(tempLlave);
        System.out.println(empleado.getNombre());

        if (empleado.equals(null))
        {
            throw new Exception("No existe un empleado a tiempo completo de "+tipoDocumento+" "+documento);
        }
        mostrarEmpleadoInfo(empleado);
    }

    //Metodos para recibir datos

    /**
     * Verificara que el input del usuario sea valido dentro de los posibles tipos de documento.
     * Arrojara Exception si no coincide con los tipos de documentos permitidos.
     * @param scanner
     * @return String del tipo de documento CC, CE, PS o TI
     * @throws Exception
     */
    public String recibirTipoDocumento(Scanner scanner) throws Exception {
        String resultado;
        System.out.printf(Constantes.MSGSETTIPODOCUMENTO);
        EnumTipoDocumento tempTipoDocumento = EnumTipoDocumento.valueOf(scanner.nextLine().toUpperCase());
        switch (tempTipoDocumento)
        {
            case CC:
                resultado = "CC";
                break;
            case CE:
                resultado = "CE";
                break;
            case PS:
                resultado = "PS";
                break;
            case TI:
                resultado = "TI";
                break;
            default:
                throw new Exception("Se ha ingresado un tipo de documento no valido: "+tempTipoDocumento);
        }
        return resultado;
    }

    /**
     * @param scanner
     * @return
     */
    public int recibirDocumento(Scanner scanner){
        System.out.printf(Constantes.MSGSETDOCUMENTO);
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * @param codigo
     * @param nombre
     * @param lista
     */
    public void aniadirOcupacion(int codigo, String nombre, List<Ocupacion> lista) {
        lista.add(new Ocupacion(codigo,nombre));
    }

    //Otros

    /**
     * Creara y retornata un LlaveEmpleado
     * @param tipoDocumento
     * @param documento
     * @return Una nueva LlaveEmpleado
     */
    public LlaveEmpleado crearLlaveEmpleado(String tipoDocumento, int documento){
        return new LlaveEmpleado(tipoDocumento, documento);
    }

    /**
     * Arrojara un Exception si la llave es encontrada en los hashMaps de empleados a tiempo completo
     * o de empleados a medio tiempo.
     */
    public void verificarEmpleadoTiempoCompletoInexistente(LlaveEmpleado llaveEmpleado) throws Exception {
        //Verifica que no exista un empleado con ese documento y tipo de documento
        if (empleadosTiempoCompleto.containsKey(llaveEmpleado)){
            throw new Exception("Ya existe un empleado de tiempo completo con "+llaveEmpleado.getTipoDocumento()+" "+llaveEmpleado.getNumeroDocumento()+" registrados.");
        }

        //Revisando que no exista un empleado a medio tiempo con esos datos
        if (empleadosTiempoMedio.containsKey(llaveEmpleado)){
            throw new Exception("Ya existe un empleado de medio tiempo con "+llaveEmpleado.getTipoDocumento()+" "+llaveEmpleado.getNumeroDocumento()+" registrados.");
        }
    }


    //Metodos de impresion
    /**
     * Este metodo se encargara de mostrar toda la informacion de un empleado a tiempo completo
     * @param empleado
     */
    public void mostrarEmpleadoInfo(EmpleadoTiempoCompleto empleado){
        System.out.println("\nNombre: "+empleado.getNombre());
        System.out.println("Apellido: "+empleado.getApellido());
        System.out.println("Tipo de documento: "+empleado.getTipoDocumento());
        System.out.println("Documento: "+empleado.getDocumento());
        System.out.println("Telefono: "+empleado.getTelefono());
        System.out.println("Nacionalidad: "+empleado.getNacionalidad());
        System.out.println("Genero: "+empleado.getNacionalidad());
        System.out.println("Activo: "+((empleado.isActivo())?"Si":"No"));
        System.out.println("Edad: "+empleado.getEdad());
        System.out.println("Ocupaciones:");
        List<Ocupacion> ocupaciones = empleado.getOcupacionList();

        for(int i=1;i<=ocupaciones.size();i++)
        {
            Ocupacion ocupacion = ocupaciones.get(i-1);
            System.out.println(i+". "+ocupacion.getNombre()+" ("+ocupacion.getCodigo()+").");
        }
    }

    /**
     * Este metodo se encargara de mostrar toda la informacion de un empleado a medio tiempo
     * @param empleado
     */
    public void mostrarEmpleadoInfo(EmpleadoTiempoMedio empleado){
        System.out.println("\nNombre: "+empleado.getNombre());
        System.out.println("Apellido: "+empleado.getApellido());
        System.out.println("Tipo de documento: "+empleado.getTipoDocumento());
        System.out.println("Documento: "+empleado.getDocumento());
        System.out.println("Telefono: "+empleado.getTelefono());
        System.out.println("Nacionalidad: "+empleado.getNacionalidad());
        System.out.println("Genero: "+empleado.getNacionalidad());
        System.out.println("Activo: "+((empleado.isActivo())?"Si":"No"));
        System.out.println("Edad: "+empleado.getEdad());
        System.out.println("Ocupaciones:");
        List<Ocupacion> ocupaciones = empleado.getOcupacionList();

        for(int i=1;i<=ocupaciones.size();i++)
        {
            Ocupacion ocupacion = ocupaciones.get(i-1);
            System.out.println(i+". "+ocupacion.getNombre()+" ("+ocupacion.getCodigo()+").");
        }
    }

    public void mostrarTodoEmpleadoTiempoCompleto() {
        Stream<EmpleadoTiempoCompleto> empleadoTiempoCompletoStream = empleadosTiempoCompleto.values().stream();
        empleadoTiempoCompletoStream.forEach(empleado -> mostrarEmpleadoInfo (empleado));
    }
    public void mostrarTodoEmpleadoTiempoMedio() {
        Stream<EmpleadoTiempoMedio> empleadoTiempoMedioStream = empleadosTiempoMedio.values().stream();
        empleadoTiempoMedioStream.forEach(empleado -> mostrarEmpleadoInfo (empleado));
    }
    public void mostrarTodoEmpleado(){
        mostrarTodoEmpleadoTiempoCompleto();
        //mostrarTodoEmpleadoTiempoMedio();
    }
}
