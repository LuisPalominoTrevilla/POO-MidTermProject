
public class Empresa {
	private int numEmpleados;
	private int empleadosActivos;
	private Empleado[] empleados;
	
	public Empresa(int numEmpleados){
		/*
		 * numEmpleados debe ser mayor o igual a uno
		 */
		this.numEmpleados = numEmpleados;
		this.empleados = new Empleado[this.numEmpleados];
		this.empleadosActivos = 0;
	}
	
	public Empresa(){
		this(1);
	}
	
	public int addEmpleado(String nombre, String rfc, Deduccion deducciones, Ingresos ingresos){
		/*
		 * Regresa -1 si no hay espacio disponible
		 */
		try{
			this.empleados[this.empleadosActivos] = new Empleado(nombre, rfc, deducciones, ingresos);
			this.empleadosActivos++;
		}catch(ArrayIndexOutOfBoundsException ex){
			return -1;
		}
		return 0;
	}
}
