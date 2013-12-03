package sigarep.modelos.data.maestros;

import java.io.Serializable;
import javax.persistence.*;

import sigarep.modelos.data.transacciones.GrupoOperacion;
import sigarep.modelos.data.transacciones.MiembroGrupo;

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

	@Column(length=255)
	private String descripcion;

	@Column(nullable=false)
	private Boolean estatus;

	private byte[] imagen;

	@Column(nullable=false, length=50)
	private String nombre;

	//bi-directional many-to-one association to GrupoOperacion
	@OneToMany(mappedBy="grupo")
	private List<GrupoOperacion> grupoOperacions;

	//bi-directional many-to-one association to MiembroGrupo
	@OneToMany(mappedBy="grupo")
	private List<MiembroGrupo> miembroGrupos;

	public Grupo() {
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

	public byte[] getImagen() {
		return this.imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<GrupoOperacion> getGrupoOperacions() {
		return this.grupoOperacions;
	}

	public void setGrupoOperacions(List<GrupoOperacion> grupoOperacions) {
		this.grupoOperacions = grupoOperacions;
	}

	public GrupoOperacion addGrupoOperacion(GrupoOperacion grupoOperacion) {
		getGrupoOperacions().add(grupoOperacion);
		grupoOperacion.setGrupo(this);

		return grupoOperacion;
	}

	public GrupoOperacion removeGrupoOperacion(GrupoOperacion grupoOperacion) {
		getGrupoOperacions().remove(grupoOperacion);
		grupoOperacion.setGrupo(null);

		return grupoOperacion;
	}

	public List<MiembroGrupo> getMiembroGrupos() {
		return this.miembroGrupos;
	}

	public void setMiembroGrupos(List<MiembroGrupo> miembroGrupos) {
		this.miembroGrupos = miembroGrupos;
	}

	public MiembroGrupo addMiembroGrupo(MiembroGrupo miembroGrupo) {
		getMiembroGrupos().add(miembroGrupo);
		miembroGrupo.setGrupo(this);

		return miembroGrupo;
	}

	public MiembroGrupo removeMiembroGrupo(MiembroGrupo miembroGrupo) {
		getMiembroGrupos().remove(miembroGrupo);
		miembroGrupo.setGrupo(null);

		return miembroGrupo;
	}

}