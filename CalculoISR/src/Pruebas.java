
public class Pruebas {

	public static void main(String[] args) {
		Empresa e = new Empresa(3);
		System.out.println(e.addEmpleado("luis", "dsfd", new Deduccion(), new Ingresos()));
		System.out.println(e.addEmpleado("luis", "dsfd", new Deduccion(), new Ingresos()));
		System.out.println(e.addEmpleado("luis", "dsfd", new Deduccion(), new Ingresos()));
		System.out.println(e.addEmpleado("luis", "dsfd", new Deduccion(), new Ingresos()));

	}

}
