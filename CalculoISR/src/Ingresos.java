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

import java.lang.Math;

public class Ingresos {
	private double sueldoMensual,
	 							 aguinaldo,
								 primaVacacional,
								 salarioMinimo;

	// Constructor
	public Ingresos(double sueldoMensual, double aguinaldo, double primaVacacional){
		this.sueldoMensual = sueldoMensual;
		this.aguinaldo = aguinaldo;
		this.primaVacacional = primaVacacional;
		this.salarioMinimo = 80.04;	// Salario minimo debe ajustarse al respectivo anio
	}


	 public double getSueldoAnual(){
		 return this.sueldoMensual * 12.0;
	 }

	 public double getAguinaldoGravado(){

		 if(this.aguinaldo > (this.sueldoMensual/30)*15){
			 return this.aguinaldo - (this.sueldoMensual/30)*15;	// Checa si aguinaldo excede limite de deduccion
		 }

		 return 0.0;
	 }

	 public double getAguinaldoExcento(){
		 return this.aguinaldo - this.getAguinaldoGravado();
	 }

	 public double getPrimaVacacionalGravada(){
		 if(this.primaVacacional > this.salarioMinimo*15){	// Checa si prima vacacional excede limite de deduccion
			 return Math.round((this.primaVacacional - this.salarioMinimo*15.0) * 1000.0)/1000.0;		// Redondear para evitar impresicion
		 }
		 return 0.0;
	 }

	 public double getPrimaVacacionalExcenta(){
		 return this.primaVacacional - this.getPrimaVacacionalGravada();
	 }

	 public double getTotalIngresos(){
		 return this.getSueldoAnual() + this.aguinaldo + this.primaVacacional;
	 }

	 public double getIngresosGravados(){
		 return this.getSueldoAnual() + getAguinaldoGravado() + getPrimaVacacionalGravada();
	 }
	 
	 public String toString(){
		 return String.format("%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f", this.sueldoMensual, this.getSueldoAnual(), this.aguinaldo, this.getAguinaldoExcento(), this.getAguinaldoGravado(), this.primaVacacional, this.getPrimaVacacionalExcenta(), this.getPrimaVacacionalGravada(), this.getIngresosGravados());
	 }
	 
	 public String desglosar(){
		 return "<html>-----------------------<br><b>INGRESOS</b><br>-----------------------<br>" +
		 		"<b>Sueldo Mensual:</b>            $" + Double.toString(this.sueldoMensual) + "<br>" +
				"<b>Total Sueldo Anual:</b>        $" + Double.toString(this.getSueldoAnual()) + "<br>" +
		 		"<b>Aguinaldo:</b>                 $" + Double.toString(this.aguinaldo) + "<br>" +
		 		"<b>Aguinaldo Excento:</b>         $" + Double.toString(this.getAguinaldoExcento()) + "<br>" +
		 		"<b>Aguinaldo Gravado:</b>         $" + Double.toString(this.getAguinaldoGravado()) + "<br>" +
		 		"<b>Prima Vacacional:</b>          $" + Double.toString(this.primaVacacional) + "<br>" +
		 		"<b>Prima Vacacional Excenta:</b>  $" + Double.toString(this.getPrimaVacacionalExcenta()) + "<br>" +
		 		"<b>Prima Vacacional Gravada:</b>  $" + Double.toString(this.getPrimaVacacionalGravada()) + "<br>" +
		 		"<b>Total Ingresos:</b>            $" + Double.toString(this.getTotalIngresos()) + "<br>" +
		 		"<b>Total Ingresos Gravados:</b>   $" + Double.toString(this.getIngresosGravados()) + "<br></html>";
	 }

}
