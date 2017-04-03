import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Pruebas {

	public static void main(String[] args) {
		Empresa e = new Empresa();
		
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		e.addEmpleado(new Empleado("luis", "oeffperg", 320000, 180000, 15000, 420000, 78000, 156045, 70000, 30000, 516000, 15000, "primaria", 25000));
		

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
