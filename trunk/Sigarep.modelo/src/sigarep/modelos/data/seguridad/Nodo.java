package sigarep.modelos.data.seguridad;
import java.util.Comparator;

import javax.persistence.Column;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/** Clase Nodo
 * Registra y Modifica el Menu tipo arbol de los grupos de usuarios del sistema.
 * @author BUILDER
 * @version 1
 * @since 04/02/2014 
 */

@org.hibernate.annotations.Table(
		appliesTo = "menu_arbol",
		indexes = {
				@Index(name="menu_arbol_index", columnNames={"id","estatus","tipo","nombre_funcion","padre","vinculo","ruta_modal"}),
		}
		)

@Entity
@Cacheable(true)
@Table(name = "menu_arbol")
public class Nodo implements Comparator<Nodo>{
	// Atributos de la clase
	@Id
	// Clave primaria de la clase
	@Column(name="id", unique = true ,length=10, nullable=false)
	private Integer id;

	@Column(name="estatus")
	private boolean estatus;

	@Column(name="tipo")
	private String tipo;

	@Column(name="nombre_funcion")
	private String nombreFuncion;

	@Column(name="padre")
	private Integer padre;

	@Column(name="vinculo")
	private String vinculo;

	@Column(name="ruta_modal")
	private String rutaModal;

	// Constructor por defecto
	public Nodo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor Sancionados
	 * @param id, tipo, nombrefuncion, vinculo, estatus, padre, rutaModal
	 * @return Constructor lleno
	 */
	public Nodo(Integer id, String tipo, String nombrefuncion,
			String vinculo, boolean estatus, Integer padre, String rutaModal) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nombreFuncion = nombrefuncion;
		this.vinculo = vinculo;
		this.estatus = estatus;
		this.padre = padre;
		this.rutaModal = rutaModal;
	}

	// Métodos Set y Get
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getEstatus() {
		return estatus;
	}

	public void setEstado(boolean estatus) {
		this.estatus = estatus;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombreFuncion() {
		return nombreFuncion;
	}
	public void setNombreFuncion(String nombreFuncion) {
		this.nombreFuncion = nombreFuncion;
	}

	public Integer getPadre() {
		return padre;
	}

	public void setPadre(Integer padre) {
		this.padre = padre;
	}

	public String getVinculo() {
		return vinculo;
	}
	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	public String getRutaModal() {
		return rutaModal;
	}

	public void setRutaModal(String rutaModal) {
		this.rutaModal = rutaModal;
	}

	public boolean esFuncion()
	{
		if(this.tipo.equals("F"))
			return true;
		return false;
	}

	@Override
	public int compare(Nodo nodo1, Nodo nodo2) {
		return nodo1.getId().compareTo(nodo2.getId());
	}// Fin Métodos Set y Get

}//Fin Clase Nodo

