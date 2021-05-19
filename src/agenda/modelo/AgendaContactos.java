package agenda.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;

/**
 * Clase AgendaContactos
 * 
 * @author Alex Calderón, Irune Arratibel, Daniel Jiménez
 * @version 1.0
 *
 */
public class AgendaContactos {
	private Map<Character, Set<Contacto>> agenda;

	/**
	 * Constructor de AgendaContactos
	 */
	public AgendaContactos() {
		agenda = new TreeMap<Character, Set<Contacto>>();
	}

	/**
	 * Añade un contacto al Map. Si no hay con su inicial se crea una nueva clave,
	 * sino se añade, comprobando siempre si esta ya metido.
	 * 
	 * @param Contacto c (el contacto a añadir)
	 */
	public void añadirContacto(Contacto c) {
		char prim = c.getApellidos().charAt(0);
		prim = Character.toUpperCase(prim);
		if (agenda.containsKey(prim)) {
			if (!agenda.get(prim).contains(c)) {
				agenda.get(prim).add(c);
			}
		} else {
			Set<Contacto> cont = new TreeSet<Contacto>();
			cont.add(c);
			agenda.put(prim, cont);
		}
	}

	/**
	 * Dado una letra devuelve un conjunto(Set) de contactos de esa letra
	 * 
	 * @param char letra
	 * @return Set conjuntos de contacos.
	 */
	public Set<Contacto> contactosEnLetra(char letra) {
		Set<Contacto> contactos = new HashSet<Contacto>();
		for (char clave : agenda.keySet()) {
			for (Contacto lista : agenda.get(clave)) {
				if (lista.getPrimeraLetra() == Character.toUpperCase(letra)) {
					contactos.add(lista);
				}
			}
		}
		return contactos;
	}

	/**
	 * Metodo para comprobar si esta o no una letra en la agenda
	 * 
	 * @param char letra
	 * @return boolean
	 */
	public boolean estaLetra(char letra) {
		return agenda.containsKey(letra);
	}

	/**
	 * Devuelve el número total de contactos en la agenda.
	 * 
	 * @return int
	 */
	public int totalContactos() {
		ArrayList<Contacto> valores = new ArrayList<Contacto>();
		for (char clave : agenda.keySet()) {
			for (Contacto lista : agenda.get(clave)) {
				valores.add(lista);
			}
		}
		return valores.size();
	}

	/**
	 * Representación textual de la agenda de contactos.
	 * 
	 * @return String
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("Agenda de contactos".toUpperCase() + "\n\n");

		Set<Map.Entry<Character, Set<Contacto>>> entradas = agenda.entrySet();
		Iterator<Map.Entry<Character, Set<Contacto>>> it = entradas.iterator();
		while (it.hasNext()) {
			Map.Entry<Character, Set<Contacto>> entrada = it.next();
			Character letra = entrada.getKey();
			Set<Contacto> contactosEnLetra = entrada.getValue();
			sb.append(letra + " (" + contactosEnLetra(letra).size() + " contacto/s)\n---------------\n");
			for (Contacto c : contactosEnLetra) {
				sb.append(c.toString() + "\n");
			}
			sb.append("\n");

		}
		sb.append(" (" + totalContactos() + " contacto/s)\n");
		return sb.toString();
	}

	/**
	 * Devuelve un ArrayList con todos los contactos que incluyen el texto
	 * ,introducido como parámetro, en su nombre o apellidos.
	 * 
	 * @param String texto
	 * @return ArrayList<Contacto>
	 */
	public List<Contacto> buscarContactos(String texto) {

		ArrayList<Contacto> resul = new ArrayList<Contacto>();
		for (char clave : agenda.keySet()) {
			for (Contacto lista : agenda.get(clave)) {
				if (lista.getNombre().toUpperCase().contains(texto.toUpperCase())
						|| lista.getApellidos().toUpperCase().contains(texto.toUpperCase()))
					resul.add(lista);
			}
		}

		return resul;
	}

	/**
	 * Dada una letra devuelve una collecion ArrayList con contactos personales que
	 * contengan esa letra.
	 * 
	 * @param char letra
	 * @return ArrayList
	 */
	public List<Personal> personalesEnLetra(char letra) {
		letra = Character.toUpperCase(letra);
		if (agenda.containsKey(letra)) {
			ArrayList<Personal> contactoP = new ArrayList<Personal>();
			if (agenda.containsKey(letra)) {
				for (Contacto lista : agenda.get(letra)) {
					if (lista instanceof Personal) {
						contactoP.add((Personal) lista);
					}
				}
			}
			return contactoP;
		}
		return null;
	}

	/**
	 * Devuelve un ArrayList con los contactos que hay que felicitar
	 * 
	 * @return ArrayList <Personal>
	 */
	public List<Personal> felicitar() {
		ArrayList<Personal> resul = new ArrayList<Personal>();
		for (char clave : agenda.keySet()) {
			for (Contacto lista : agenda.get(clave)) {
				if (lista instanceof Personal && ((Personal) lista).esCumpleaños())
					resul.add((Personal) lista);
			}
		}
		return resul;
	}

	/**
	 * Devuelve TreeMap en el que aparecen los contactos personales organizados de
	 * forma que la clave en el nuevo map es la relación y el valor asociado, un
	 * ArrayList de cadenas con los apellidos y nombre de todos los contactos
	 * personales que hay en la agenda
	 * 
	 * @return
	 */
	public Map<Relacion, List<String>> personalesPorRelacion() {

		TreeMap<Relacion, List<String>> resul = new TreeMap<Relacion, List<String>>();

		for (char clave : agenda.keySet()) {
			for (Contacto lista : agenda.get(clave)) {
				if (lista instanceof Personal) {
					String nomb = "";
					nomb = lista.getApellidos() + " " + lista.getNombre();
					switch (((Personal) lista).getRel()) {
					case PADRE:
						if (resul.containsKey(Relacion.PADRE)) {
							resul.get(Relacion.PADRE).add(nomb);
						} else {
							ArrayList<String> cont = new ArrayList<String>();
							cont.add(nomb);
							resul.put(Relacion.PADRE, cont);
						}
						break;
					case MADRE:
						if (resul.containsKey(Relacion.MADRE)) {
							resul.get(Relacion.MADRE).add(nomb);
						} else {
							ArrayList<String> cont = new ArrayList<String>();
							cont.add(nomb);
							resul.put(Relacion.MADRE, cont);
						}
						break;
					case AMIGOS:
						if (resul.containsKey(Relacion.AMIGOS)) {
							resul.get(Relacion.AMIGOS).add(nomb);
						} else {
							ArrayList<String> cont = new ArrayList<String>();
							cont.add(nomb);
							resul.put(Relacion.AMIGOS, cont);
						}
						break;
					case PAREJA:
						if (resul.containsKey(Relacion.PAREJA)) {
							resul.get(Relacion.PAREJA).add(nomb);
						} else {
							ArrayList<String> cont = new ArrayList<String>();
							cont.add(nomb);
							resul.put(Relacion.PAREJA, cont);
						}
						break;
					case HIJO:
						if (resul.containsKey(Relacion.HIJO)) {
							resul.get(Relacion.HIJO).add(nomb);
						} else {
							ArrayList<String> cont = new ArrayList<String>();
							cont.add(nomb);
							resul.put(Relacion.HIJO, cont);
						}
						break;
					case HIJA:
						if (resul.containsKey(Relacion.HIJA)) {
							resul.get(Relacion.HIJA).add(nomb);
						} else {
							ArrayList<String> cont = new ArrayList<String>();
							cont.add(nomb);
							resul.put(Relacion.HIJA, cont);
						}
						break;
					}
				}
			}
		}
		return resul;
	}

	/**
	 * Ordena una ArrayList por fecha de nacimiento, pero solo los que empiezan con
	 * una letra determinada. Estos se guardan en otra ArrayList y se trabaja sobre
	 * ella.
	 * 
	 * @param char letra
	 * @return List<Personal> con los contactos personales ordenados
	 */
	public List<Personal> personalesOrdenadosPorFechaNacimiento(char letra) {
		List<Personal> ord = personalesEnLetra(letra);

		Collections.sort(ord, new Comparator<Personal>() {
			public int compare(Personal p1, Personal p2) {
				if (p1.getFecha_nac().compareTo(p2.getFecha_nac()) > 0) {
					return 1;
				}
				if (p1.getFecha_nac().compareTo(p2.getFecha_nac()) < 0) {
					return -1;
				}
				return 0;
			}

		});
		return ord;
	}

}
