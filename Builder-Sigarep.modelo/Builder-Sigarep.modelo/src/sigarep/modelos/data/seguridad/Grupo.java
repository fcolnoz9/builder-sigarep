package sigarep.modelos.data.seguridad;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Index;

import sigarep.modelos.data.transacciones.UsuarioGrupo;

/** Clase Grupo
 * Registra y Modifica el Grupo de usuarios del sistema.
 * @author BUILDER
 * @version 1
 * @since 04/02/2014 
 */

@org.hibernate.annotations.Table(
		appliesTo = "grupo",
		indexes = {
				@Index(name="grupo_index", columnNames={"id_grupo","nombre","descripcion","estatus"}),
		}
		)

@Entity
@Access(AccessType.FIELD)
//anotación indica que el JavaBean es una entidad persistente
@Table(name ="grupo")
public class Grupo implements Serializable{
	private static final long serialVersionUID = 1L;
	// Atributos de la clase
	@Id
	// Clave primaria de la clase
	@Column(name="id_grupo", unique = true , nullable=false)
	private Integer idGrupo;

	@Column(name="nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "estatus")
	private boolean estatus;

	// Relación bidireccional de muchos a muchos, asociada a la clase Nodo
	@ManyToMany(fetch=FetchType.EAGER) 
	@JoinTable(name = "funcion_grupo",
	joinColumns = { @JoinColumn(name = "id_grupo",referencedColumnName = "id_grupo") },
	inverseJoinColumns = { @JoinColumn(name = "id_nodo", referencedColumnName = "id") })
	private Set<Nodo> nodos = new HashSet<Nodo>();

	// Relación bidireccional de uno a muchos, asociada a la clase Usuario
	@OneToMany(fetch = FetchType.EAGER, mappedBy="grupo",  cascade={CascadeType.ALL})
	private Set<UsuarioGrupo> usuariosGrupos = new HashSet<UsuarioGrupo>();

	// Constructor por defecto
	public Grupo() {
		super();
		nodos = new HashSet<Nodo>();
	}

	// Métodos Set y Get
	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}
	public Set<Nodo> getNodos() {
		return nodos;
	}

	public void setNodos(Set<Nodo> nodos) {
		this.nodos = nodos;
	}

	public Set<UsuarioGrupo> getUsuariosGrupos() {
		return this.usuariosGrupos;
	}

	public void setUsuarioGrupos(Set<UsuarioGrupo> usuarioGrupos) {
		this.usuariosGrupos = usuarioGrupos;
	}
	
	public void addNodos(Nodo nodo){
		this.nodos.add(nodo);
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
	}// Fin Métodos Set y Get

}//Fin Clase Grupo