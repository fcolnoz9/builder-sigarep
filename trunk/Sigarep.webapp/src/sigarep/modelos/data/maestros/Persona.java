package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.data.transacciones.InstanciaMiembro;

import java.util.List;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_persona")
	private Integer cedulaPersona;

	private String apellido;

	private String correo;

	private Boolean estatus;

	private String nombre;

	private String telefono;

	//bi-directional many-to-one association to InstanciaMiembro
	@OneToMany(mappedBy="persona")
	private List<InstanciaMiembro> instanciaMiembros;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="nombreusuario")
	private Usuario nombreUsuario;

	public Persona() {
	}

	public Integer getCedulaPersona() {
		return this.cedulaPersona;
	}

	public void setCedulaPersona(Integer cedulaPersona) {
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

	public List<InstanciaMiembro> getInstanciaMiembros() {
		return this.instanciaMiembros;
	}

	public void setInstanciaMiembros(List<InstanciaMiembro> instanciaMiembros) {
		this.instanciaMiembros = instanciaMiembros;
	}

	public InstanciaMiembro addInstanciaMiembro(InstanciaMiembro instanciaMiembro) {
		getInstanciaMiembros().add(instanciaMiembro);
		instanciaMiembro.setPersona(this);

		return instanciaMiembro;
	}

	public InstanciaMiembro removeInstanciaMiembro(InstanciaMiembro instanciaMiembro) {
		getInstanciaMiembros().remove(instanciaMiembro);
		instanciaMiembro.setPersona(null);

		return instanciaMiembro;
	}

	public Usuario getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(Usuario usuario) {
		this.nombreUsuario = usuario;
	}

}