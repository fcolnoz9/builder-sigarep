package sigarep.modelos.data.maestros;

import java.io.Serializable;

import javax.persistence.*;

import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.data.transacciones.InstanciaMiembro;

import java.util.List;

/**
 * Clase Persona  (Miembros de la Instancia)
 * 
 * @author BUILDER
 * @version 1.0
 * @since 10/12/2013
 */
@Entity
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;
	// Atributos de la clase
	@Id
	// Clave principal de la clase
	@Column(name = "cedula_persona")
	private String cedulaPersona;

	private String apellido;

	private String correo;

	private Boolean estatus;

	private String nombre;

	private String telefono;

	// Relación bidireccional de muchos a uno, asociada a la clase InstanciaMiembro
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "persona")
	private List<InstanciaMiembro> instanciaMiembros;

	// Relación bidireccional de uno a uno, asociada a la clase Usuario
	@OneToOne
	@JoinColumn(name = "nombre_usuario")
	private Usuario usuario;

	// Constructor por defecto
	public Persona() {
	}

	// Métodos set y get
	public String getCedulaPersona() {
		return this.cedulaPersona;
	}

	public void setCedulaPersona(String cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Boolean getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<InstanciaMiembro> getInstanciaMiembros() {
		return this.instanciaMiembros;
	}

	public void setInstanciaMiembros(List<InstanciaMiembro> instanciaMiembros) {
		this.instanciaMiembros = instanciaMiembros;
	}

	/**
	 * Relación de la clase Persona con la clase InstanciaMiembro, Agregar
	 * InstanciaMiembro
	 * 
	 * @see InstanciaMiembro
	 * @param InstanciaMiembro
	 * @return InstanciaMiembro
	 */
	public InstanciaMiembro addInstanciaMiembro(
			InstanciaMiembro instanciaMiembro) {
		getInstanciaMiembros().add(instanciaMiembro);
		instanciaMiembro.setPersona(this);

		return instanciaMiembro;
	}

	/**
	 * Relación de la clase Persona con la clase InstanciaMiembro, Quitar
	 * InstanciaMiembro
	 * 
	 * @see InstanciaMiembro
	 * @param InstanciaMiembro
	 * @return InstanciaMiembro
	 */
	public InstanciaMiembro removeInstanciaMiembro(
			InstanciaMiembro instanciaMiembro) {
		getInstanciaMiembros().remove(instanciaMiembro);
		instanciaMiembro.setPersona(null);

		return instanciaMiembro;
	}

	// Fin Métodos set y get
}//Fin Clase Persona