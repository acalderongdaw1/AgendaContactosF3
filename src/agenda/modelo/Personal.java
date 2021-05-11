package agenda.modelo;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Clase Personal, que hereda de Contacto añadiendo la fecha de nacimiento
 * y la relación.
 * 
 * @author Alex Calderón, Irune Arratibel, Daniel Jiménez
 * @version 1.0
 *
 */

public class Personal extends Contacto{

	private LocalDate fecha_nac;
	private Relacion rel;
	
	/**
	 * Contructor de la clase
	 * @param nombre, apellidos, telefono, email, fechan(fecha nacimiento), rel(relación)
	 * 
	 */
	public Personal(String nombre, String apellidos, String telefono, String email, String fechan, Relacion rel) {
		super(nombre, apellidos, telefono, email);
		String[] partes = fechan.split("/");
		fecha_nac = LocalDate.of(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]), Integer.parseInt(partes[0]));
		this.rel = rel;
	}
	
	
	/**
	 * Getter de la fecha de nacimiento
	 * @return LocalDate con la fecha de nacimiento
	 */
	public LocalDate getFecha_nac() {
		return fecha_nac;
	}


	/**
	 * Setter de la fecha de nacimiento
	 * @param LocalDate fecha_nac (nueva fecha de nacimiento)
	 */
	public void setFecha_nac(LocalDate fecha_nac) {
		this.fecha_nac = fecha_nac;
	}


	/**
	 * Getter de la relación
	 * @return Relacion con la relación del contacto personal
	 */
	public Relacion getRel() {
		return rel;
	}


	/**
	 * Setter de la relación
	 * @param Relacion rel (nueva relación del contacto personal)
	 */
	public void setRel(Relacion rel) {
		this.rel = rel;
	}


	/**
	 * Saca la firma del email
	 * @return String con la firma
	 */
	@Override
	public String getFirmaEmail() {
		return "Un abrazo!!";
	}

	/**
	 * Comprueba que la fecha de hoy sea el cumpleaños.
	 * @return Boolean (true es el cumpleaños)
	 */
	public boolean esCumpleaños() {
		LocalDate hoy = LocalDate.now();
		int dianac = fecha_nac.getDayOfMonth();
		Month mesnac = fecha_nac.getMonth();
		int diaact = hoy.getDayOfMonth();
		Month mesact = hoy.getMonth();
		return dianac == diaact && mesnac.equals(mesact);
	}
	
	/**
	 * Representación textual del contacto personal
	 * @return String con el texto
	 */
	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM YYYY");
		
		String resul = super.toString();
		resul += "Fecha nacimiento: " + dtf.format(fecha_nac) + "\nRelacion: " + rel + "\n";
		return resul;
	}

}