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

public class Deduccion {
	private final int MAXIMO_PRIMARIA = 12900;
	private final int MAXIMO_SECUNDARIA = 19900;
	private final int MAXIMO_PREPARATORIA = 24500;
	
	private Empleado empleado;
	private double medicosHospitales;
	private double gastosFunerarios;
	private double sgmm;
	private double gastosHipotecarios;
	private double donativos;
	private double retiro;
	private double transporteEscolar;
	private String nivelEducativo;
	private double colegiatura;
	
	
	public Deduccion(Empleado empleado, double medicosHospitales, double gastosFunerarios, double sgmm, double gastosHipotecarios, double donativos, double retiro, double transporteEscolar, String nivelEducativo, double colegiatura){
		this.empleado = empleado;
		this.medicosHospitales = medicosHospitales;
		this.gastosFunerarios = gastosFunerarios;
		this.sgmm = sgmm;
		this.gastosHipotecarios = gastosHipotecarios;
		this.donativos = donativos;
		this.retiro = retiro;
		this.transporteEscolar = transporteEscolar;
		this.nivelEducativo = nivelEducativo;
		this.colegiatura = colegiatura;
	}
	
	public Deduccion(Empleado empleado){
		this.empleado = empleado;
	}

	public double getMaximoDeducirColegiatura(){
		/*
		 *  Regresa el maximo a deducir de colegiatura en base al nivel educativo
		 */
		this.nivelEducativo = this.nivelEducativo.replaceAll("\\s+",""); // Quitarle espacios, tomado de http://stackoverflow.com/questions/5455794/removing-whitespace-from-strings-in-java
		switch(this.nivelEducativo.toLowerCase()){
		case "primaria":
			return this.MAXIMO_PRIMARIA;
		case "secundaria":
			return this.MAXIMO_SECUNDARIA;
		case "preparatoria":
			return this.MAXIMO_PREPARATORIA;
		default:
			return 0.0;
		}
	}

	public double getTotalDeducciones(){
		/*
		 * Regresa el total de deducciones SIN incluir el retiro
		 */
		double deduccionColegiatura = (this.getMaximoDeducirColegiatura() < this.colegiatura)? this.getMaximoDeducirColegiatura(): this.colegiatura;				// Calcular el monto maximo a deducir de colegiatura
		return this.medicosHospitales + this.gastosFunerarios + this.sgmm + this.gastosHipotecarios + this.donativos + this.transporteEscolar + deduccionColegiatura;
	}
	
	public double getDeduccionPermitida(){
		double deduccionRetiro = 0;
		double deduccionNormal = 0;
		final double MAXIMO_DEDUCIR = this.empleado.getIngresos().getTotalIngresos()*.10;							// El monto a deducir no puede sobrepasar este monto
		
		deduccionRetiro = (this.retiro > MAXIMO_DEDUCIR)? MAXIMO_DEDUCIR:this.retiro;
		deduccionNormal = (this.getTotalDeducciones() > MAXIMO_DEDUCIR)? MAXIMO_DEDUCIR: this.getTotalDeducciones();
		return deduccionRetiro + deduccionNormal;
	}

	public String toString(){
		return String.format("%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%s,%.2f,%.2f,%.2f", this.medicosHospitales, this.gastosFunerarios, this.sgmm, this.gastosHipotecarios, this.donativos, this.retiro, this.transporteEscolar, this.nivelEducativo, this.getMaximoDeducirColegiatura(), this.colegiatura, this.getTotalDeducciones());
	}
	
	public String desglosar(){
		/*
		 *  Regresa un toString de Deducciones con formato para ser exportado a un archivo html
		 */
		 return "<html>-----------------------<br><b>DEDUCCIONES</b><br>-----------------------<br>" +
		 		"<b>Medicos y Hospitales:</b>            $" + Double.toString(this.medicosHospitales) + "<br>" +
				"<b>Gastos Funerarios:</b>       		 $" + Double.toString(this.gastosFunerarios) + "<br>" +
		 		"<b>S. Gastos Medicos Mayores:</b>       $" + Double.toString(this.sgmm) + "<br>" +
		 		"<b>Hipotecarios:</b>         			 $" + Double.toString(this.gastosHipotecarios) + "<br>" +
		 		"<b>Donativos:</b>         				 $" + Double.toString(this.donativos) + "<br>" +
		 		"<b>Subcuenta Retiro:</b>          		 $" + Double.toString(this.retiro) + "<br>" +
		 		"<b>Transporte Escolar:</b>  			 $" + Double.toString(this.transporteEscolar) + "<br>" +
		 		"<b>Nivel educativo:</b>  					 " + this.nivelEducativo + "<br>" +
		 		"<b>Maximo a deducir colegiatura:</b>  					 $" + Double.toString(this.getMaximoDeducirColegiatura()) + "<br>" +
		 		"<b>Colegiatura pagada:</b>  					 $" + Double.toString(this.colegiatura) + "<br>" +
		 		"<b>Total Deducciones (sin retiro):</b>            	 $" + Double.toString(this.getTotalDeducciones()) + "<br></html>";
	 }
	
	
}
