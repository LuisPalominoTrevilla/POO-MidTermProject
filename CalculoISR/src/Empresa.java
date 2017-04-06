/*
 * Autores: 
 * Luis Palomino Trevilla A01228574
 * Emanuel Estrada Larios A01633605 
 * 
 * Porcentaje de participación:
 * Luis Palomino - 55%
 * Emanuel Estrada - 45%
 * 
 * Fecha: 5 de abril de 2017
 * 
 * Comentarios: ninguno
 * 
 */


public class Empresa {
	private int vacantes;			// Vacantes disponibles, no necesariamente van a estar ocupadas
	private int empleadosActivos;	// Numero de empleados registrados en la empresa
	private Empleado[] empleados;	// Lista de empleados
	
	public Empresa(int vacantes){
		/*
		 * vacantes debe ser mayor o igual a uno
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
			//Aniadir al empleado en espera
			this.empleados[this.empleadosActivos++] = empleado;
		}
	}
	
	public void addEmpleado(String nombre, String rfc,double sueldoMensual, double aguinaldo, double primaVacacional, double medicosHospitales, double gastosFunerarios, double sgmm, double gastosHipotecarios, double donativos, double retiro, double transporteEscolar, String nivelEducativo, double colegiatura){
		this.addEmpleado(new Empleado(nombre, rfc, sueldoMensual, aguinaldo, primaVacacional, medicosHospitales, gastosFunerarios, sgmm, gastosHipotecarios, donativos, retiro, transporteEscolar, nivelEducativo, colegiatura));
	}
	
	public void hacerDeclaracionAnual(){
		for(int i=0; i<this.empleadosActivos;i++){
			this.empleados[i].hacerDeclaracionAnual();
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
		
		for(Empleado empleado: this.empleados){
			if(empleado == null){
				break;								// Salirse del loop si ya no hay mas vacantes
			}
			returnString += empleado + "\n";		// Concatenar los empleados
		}
		
		return returnString;
	}
}
