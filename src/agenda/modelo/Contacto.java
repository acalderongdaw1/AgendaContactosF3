package agenda.modelo;

/**
 * Clase Contacto, representa a un contacto de la agenda. No se crean instacias
 * en esta clase.
 * 
 * @author Alex Calderón, Irune Arratibel, Daniel Jiménez
 * @version 1.0
 *
 */

public abstract class Contacto implements Comparable<Contacto> {
	// Atributos
	// Constantes
	// Variables
	private String nombre;
	private String apellidos;
	private String telefono;
	private String email;

	/**
	 * Contructor de la clase
	 * 
	 * @param nombre, apellidos, telefono, email
	 * 
	 */
	public Contacto(String nombre, String apellidos, String telefono, String email) {
		this.nombre = nombre.toUpperCase();
		this.apellidos = apellidos.toUpperCase();
		this.telefono = telefono;
		this.email = email.toLowerCase();
	}

	/**
	 * Getter del Nombre
	 * 
	 * @return String con el nombre del contacto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Setter del nombre del contacto
	 * 
	 * @param String nombre (nuevo nombre)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Getter de los apellidos
	 * 
	 * @return String con los apellidos del contacto
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * Setter de los apellidos del contacto
	 * 
	 * @param String apellidos (nuevos apellidos)
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	/**
	 * Getter del Telefono
	 * 
	 * @return String con el Telefono del contacto
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Setter del Telefono del contacto
	 * 
	 * @param String telfono (nuevo telefono)
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * Getter del Email
	 * 
	 * @return String con el email del contacto
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter del Email del contacto
	 * 
	 * @param String email (nuevo email)
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Representación textual del contacto.
	 * 
	 * @return String con texto
	 */
	@Override
	public String toString() {
		return getApellidos() + ", " + getNombre() + " (" + getClass().getSimpleName().toUpperCase() + ")" + "\n"
				+ "Tfno: " + getTelefono() + " |  email: " + getEmail() + "\n";
	}

	/**
	 * Método abstracto que genera la firma del email de un contacto.
	 * 
	 * @return String con firma
	 */
	public abstract String getFirmaEmail();

	/**
	 * Método que devuelve la primera letra del apellido de un contacto.
	 * 
	 * @return char con la primera letra.
	 */
	public char getPrimeraLetra() {
		char primeraL = this.apellidos.charAt(0);
		return primeraL;
	}

	/**
	 * Método CompareTo para compara los apellidos de un contacto (si coinciden se
	 * compara por el nombre), para luego ordenar.
	 * 
	 * @return int
	 */
	@Override
	public int compareTo(Contacto o) {
		if (this.apellidos.compareToIgnoreCase(o.getApellidos()) == 0) {
			if (this.nombre.compareToIgnoreCase(o.getNombre()) > 0) {
				return 1;
			}
			if (this.nombre.compareToIgnoreCase(o.getNombre()) < 0) {
				return -1;
			}
		} else {
			if (this.apellidos.compareToIgnoreCase(o.getApellidos()) > 0) {
				return 1;
			}
			if (this.apellidos.compareToIgnoreCase(o.getApellidos()) < 0) {
				return -1;
			}
		}
		return 0;
	}

	/**
	 * Método Equals para saber si dos objetos son iguales
	 * 
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Contacto)) {
			return false;
		}
		Contacto p = (Contacto) obj;
		return p.getApellidos().equalsIgnoreCase(apellidos) && p.getNombre().equalsIgnoreCase(nombre)
				&& p.getEmail().equalsIgnoreCase(email);
	}

	/**
	 * Método HashCode sobreescrito.
	 * 
	 * @return int
	 */
	@Override
	public int hashCode() {
		return nombre.hashCode() + apellidos.hashCode() + telefono.hashCode() + email.hashCode() * 11;

	}

}
