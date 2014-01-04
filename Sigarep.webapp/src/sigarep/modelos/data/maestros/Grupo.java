package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.herramientas.Archivo;
import sigarep.modelos.data.transacciones.UsuarioGrupo;

import java.util.LinkedList;
import java.util.List;


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@Table(name="grupo")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_grupo", unique=true, nullable=false)
	private Integer idGrupo;

	@Column(name="descripcion", length=255)
	private String descripcion;

	@Column(nullable=false)
	private Boolean estatus;

	@Column(name = "nombre", nullable=false, length=50)
	private String nombre;

	//bi-directional many-to-one association to MiembroGrupo
	@OneToMany(mappedBy="grupo")
	private List<UsuarioGrupo> usuariosGrupos = new LinkedList<UsuarioGrupo>();

	public Grupo() {
	}
	
	public Grupo(String descripcion, Boolean estatus, String nombre) {
		super();
		this.descripcion = descripcion;
		this.estatus = estatus;
		this.nombre = nombre;
	}

	public Integer getIdGrupo() {
		return this.idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.grupo")
	public List<UsuarioGrupo> getUsuariosGrupos() {
		return this.usuariosGrupos;
	}

	public void setUsuarioGrupos(List<UsuarioGrupo> usuarioGrupos) {
		this.usuariosGrupos = usuarioGrupos;
	}

	public UsuarioGrupo addUsuarioGrupo(UsuarioGrupo usuarioGrupo) {
		getUsuariosGrupos().add(usuarioGrupo);
		usuarioGrupo.setGrupo(this);
		return usuarioGrupo;
	}

	public UsuarioGrupo removeUsuarioGrupo(UsuarioGrupo usuarioGrupo) {
		getUsuariosGrupos().remove(usuarioGrupo);
		usuarioGrupo.setGrupo(null);
		return usuarioGrupo;
	}

}