import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Pruebas {

	public static void main(String[] args) {
		Empresa e = new Empresa(3);
		e.addEmpleado(new Empleado("luis", "oeffperg", 6000, 3000, 1000, 5600, 50000, 25000, 40500, 1000, 25000, 100, "primaria", 4000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 6000, 3000, 1000, 5600, 50000, 25000, 40500, 1000, 25000, 100, "primaria", 4000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 6000, 3000, 1000, 5600, 50000, 25000, 40500, 1000, 25000, 100, "primaria", 4000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 6000, 3000, 1000, 5600, 50000, 25000, 40500, 1000, 25000, 100, "primaria", 4000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 6000, 3000, 1000, 5600, 50000, 25000, 40500, 1000, 25000, 100, "primaria", 4000));
		
		e.hacerDeclaracionAnual();
		System.out.println(e);
/*		try{
			PrintWriter pw = new PrintWriter(new FileWriter("test.csv"));
			pw.println(e.getEmpleados()[0].getISR());
			pw.close();
			System.out.println("No error");
		}catch(IOException ex){
			System.out.println("woaaah error");
		}*/
		
		
		
	}
}
