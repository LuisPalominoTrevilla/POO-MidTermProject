
public class Empresa {
	private int vacantes;			// Vacantes disponibles, no necesariamente van a estar ocupadas
	private int empleadosActivos;	// Numero de empleados registrados en la empresa
	private Empleado[] empleados;	// Lista de empleados
	
	public Empresa(int vacantes){
		/*
		 * numEmpleados debe ser mayor o igual a uno
		 */
		this.vacantes = vacantes;
		this.empleados = new Empleado[this.vacantes];	// Inicializar el tamano de la lista de empleados
		this.empleadosActivos = 0;
	}
	
	public Empresa(){
		this(10);	// Crea una lista para 10 empleados
	}
	
	public void addEmpleado(Empleado empleado){
		try{
			this.empleados[this.empleadosActivos] = empleado;			// Anadir el empleado a la lista
			this.empleadosActivos++;									// Anadir un empleado nuevo
		}catch(ArrayIndexOutOfBoundsException ex){
			this.vacantes += 10;										// Agregar 10 vacantes extra
			Empleado[] temp = new Empleado[this.vacantes];				
			
			for(int i = 0; i < this.empleadosActivos; i++){
				temp[i] = this.empleados[i];
			}
			this.empleados = temp;
		}
	}
	
	public void addEmpleado(String nombre, String rfc,double sueldoMensual, double aguinaldo, double primaVacacional, double medicosHospitales, double gastosFunerarios, double sgmm, double gastosHipotecarios, double donativos, double retiro, double transporteEscolar, String nivelEducativo, double colegiatura){
		this.addEmpleado(new Empleado(nombre, rfc, sueldoMensual, aguinaldo, primaVacacional, medicosHospitales, gastosFunerarios, sgmm, gastosHipotecarios, donativos, retiro, transporteEscolar, nivelEducativo, colegiatura));
	}
	
	public void hacerDeclaracionAnual(){
		for(int i=0; i<this.vacantes;i++){
			if(empleados[i]!=null){
				this.empleados[i].hacerDeclaracionAnual();
			}
		}
	}
	
	public Empleado[] getEmpleados(){
		return this.empleados;
	}
	
	public int getEmpleadosActivos(){
		return this.empleadosActivos;
	}
	
	public String toString(){
		// Regresar un string en lineas, el nombre del empleado, su rfc y su declaracion
		String returnString = "";
		
		for(int i=0; i<this.vacantes;i++){
			if(empleados[i]!=null){
				returnString += this.empleados[i].getNombre() + "," + this.empleados[i].getRFC() + "," + this.empleados[i].getISR() + "/n";
			}
		}
		
		return returnString;
	}
}
