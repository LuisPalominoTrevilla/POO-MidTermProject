
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
			this.empleados[this.empleadosActivos++] = new Empleado(nombre, rfc, deducciones, ingresos);
		}catch(ArrayIndexOutOfBoundsException ex){
			return -1;
		}
		return 0;
	}
	
	public void hacerDeclaracionAnual(){
		// For para que cada empleado haga sus declaraciones
	}
	
	public Empleado[] getEmpleados(){
		return this.empleados;
	}
	
	public String toString(){
		// Regresar en strings en lineas, el nombre del empleado, su rfc y su declaracion (RFC to string)
		return "";
	}
}