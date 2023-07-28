package vista;

import controlador.EmpleadoService;
import java.util.Scanner;

public class main {
    static EmpleadoService empleadoService = new EmpleadoService();

    private boolean guardado = true;
    private static void getMenu(){
    System.out.println("\n\n=============== MENU ===============\n\n" +
            "1. Ingresar empleado\n" +
            "2. Listar todos los empleados\n" +
            "3. Buscar empleado\n" +
            "4. Eliminar empleado\n" +
            "5. Actualizar empleado\n" +
            "6. Generar reportes\n" +
            "7. Guardar informacion\n" +
            "8. Borrar toda la informacion\n" +
            "9. Salir");
    }
    private static void getMenuReportes() {
        System.out.println(
                "\n\n=============== REPORTES ===============\n\n" +
                "1. Reporte de empleados que tengan un salario en cierto rango\n" +
                "2. Reporte de todos los empleados con el salario de 12 meses y adicionar una columna de la prima (???)\n" +
                "3. Reporte por tipo de usuario.\n" +
                "4. Salir de reportes.");
    }
    private static void getOpcion() {
        Scanner capturaPorTeclado = new Scanner(System.in);
        int opcion=0;
        while (opcion!=9){
            try {
                getMenu();
                System.out.printf("\n\nIngrese la opcion: ");
                String strOpcion = capturaPorTeclado.next();
                opcion= Integer.parseInt(strOpcion);
                accionMenu(opcion, capturaPorTeclado);
            }catch (Exception e){
                imprimirError(e);
                opcion=0;
            }
        };
    }
    private static void getOpcionReportes(Scanner scanner){
        int opcion=0;
        do {
            try {
                getMenuReportes();
                System.out.printf("\n\nIngrese la opcion: ");
                String strOpcion = scanner.nextLine();
                opcion= Integer.parseInt(strOpcion);
                accionMenuReportes(opcion);
            } catch (Exception e){
                imprimirError(e);
                opcion=0;
            }
        } while (opcion!=4);
    }
    private static void accionMenu(int opcion, Scanner scanner) throws Exception {
        switch (opcion){
            case 1:
                if ((preguntarTipoEmpleado(scanner))) {
                    empleadoService.crearEmpleadoTiempoCompleto(scanner);
                } else {
                    empleadoService.crearEmpleadoTiempoMedio(scanner);
                }
                break;
            case 2:
                empleadoService.mostrarTodoEmpleado();
                break;
            case 3:
                if (preguntarTipoEmpleado(scanner)){
                    empleadoService.buscarEmpleadoTiempoCompleto(scanner);
                } else {
                    //empleadoService.buscarEmpleadoTiempoMedio(scanner);
                }
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                getOpcionReportes(scanner);
                break;
            case 7:
                break;
            case 8:
                System.out.println("¿Esta seguro que quiere borrar TODA la informacion? (S/N)");
                if ((scanner.nextLine().toUpperCase().equals("S"))) {
                    empleadoService.borrarTodaInformacion();
                } else {
                    System.out.println("No se borro la información.");
                }
                break;
            case 9:
                System.out.println("Gracias por usar la aplicación.");
                break;
            default:
                System.err.println("Opción invalida.");
        }
    }
    private static void accionMenuReportes(int opcion){
        switch (opcion){
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println("Saliendo de Reportes...");
                break;
        }
    }

    private static void imprimirError(Exception e){
        System.err.println("\nError al ingresar la informacion: "+e.toString()+"\nIntente ingresar la opcion nuevamente.");
    }

    private static boolean preguntarTipoEmpleado(Scanner scanner){
        scanner.nextLine();
        System.out.println("\n¿Que tipo de datos quiere usar?");
        System.out.println("1. Empleado de tiempo completo");
        System.out.println("2. Empleado de medio tiempo");
        System.out.printf("Digite una opción: ");
        return (scanner.nextLine().equals("1"))?true:false;
    }

    public static void main(String[] args) {
        getOpcion();
    }
}
