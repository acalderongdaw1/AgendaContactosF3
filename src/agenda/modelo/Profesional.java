package agenda.modelo;

/**
 * Clase Profesional, que hereda de Contacto añadiendo el nombre de la empresa
 * 
 * @author Alex Calderón, Irune Arratibel, Daniel Jiménez
 * @version 1.0
 *
 */
public class Profesional extends Contacto {
	private String nombreEmpresa;

	/**
	 * Constructor
	 * 
	 * Crea un objeto Profesional
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param telefono
	 * @param email
	 * @param nombreEmpresa
	 */
	
	public Profesional(String nombre, String apellidos, String telefono, String email, String nombreEmpresa) {
		super(nombre, apellidos, telefono, email);
		this.nombreEmpresa = "";
		String[] temp = nombreEmpresa.split(" ");
		for(String lista: temp) {
			this.nombreEmpresa += lista.substring(0,1).toUpperCase() + lista.substring(1) + " ";
		}
	}

	/**
	 * Devuelve el nombre de la empresa (Getter)
	 * @return String con el nombre de la empresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Cambia el nombre de la empresa al parámetro introducido (Setter)
	 * @param nombreEmpresa
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	
	/**
	 * Saca la firma del email
	 * @return String con la firma
	 */
		@Override
		public String getFirmaEmail() {
			String resul="";
			int aux = (int)(Math.random() * 4 + 1);
			switch(aux) {
				case 1:
					resul = "Atentamente";
				case 2:
					resul = "Saludos";
				case 3:
					resul = "Saludos cordiales";
				case 4:
					resul = "Mis mejores deseos";	
			}
			return resul;
		}
		

	/**
	 * Representación textual del contacto Profesional
	 * @return String con el texto
	 */
	@Override
	public String toString() {
		return (super.toString() + "Empresa: " + nombreEmpresa + "\n");
				
	}
	

}
