
public class Empleado extends Persona{
	private String rfc;
	private Deduccion deducciones;
	private Ingresos ingresos;
	private ISR impuestoISR;
	
	public Empleado(String nombre, String rfc, Deduccion deducciones, Ingresos ingresos){
		super(nombre);
		this.rfc = rfc;
		this.deducciones = deducciones;
		this.ingresos = ingresos;
	}

}
