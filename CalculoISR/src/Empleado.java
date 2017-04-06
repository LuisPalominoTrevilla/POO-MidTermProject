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

public class Empleado extends Persona{
	private String rfc;
	private Deduccion deducciones;
	private Ingresos ingresos;
	private ISR impuestoISR;
	
	public Empleado(String nombre, String rfc,double sueldoMensual, double aguinaldo, double primaVacacional, double medicosHospitales, double gastosFunerarios, double sgmm, double gastosHipotecarios, double donativos, double retiro, double transporteEscolar, String nivelEducativo, double colegiatura){
		super(nombre);
		this.rfc = rfc;
		this.deducciones = new Deduccion(this, medicosHospitales, gastosFunerarios, sgmm, gastosHipotecarios, donativos, retiro, transporteEscolar, nivelEducativo, colegiatura);
		this.ingresos = new Ingresos(sueldoMensual, aguinaldo, primaVacacional);
	}
	
	public Ingresos getIngresos(){
		return this.ingresos;
	}
	
	public Deduccion getDeduccion(){
		return this.deducciones;
	}
	
	public String getRFC(){
		return this.rfc;
	}
	
	public ISR getISR(){
		return this.impuestoISR;
	}
	
	public void hacerDeclaracionAnual(){
		this.impuestoISR = new ISR(this.getDeduccion().getDeduccionPermitida(), this.ingresos.getIngresosGravados());
	}
	
	public String toString(){
		if(this.impuestoISR != null){
			return String.format("%s,%s,%s,%s,%s", this.getNombre(), this.rfc, this.ingresos, this.deducciones, this.impuestoISR);
		}else{
			return String.format("%s,%s,%s,%s", this.getNombre(), this.rfc, this.ingresos, this.deducciones);
		}
	}
}
