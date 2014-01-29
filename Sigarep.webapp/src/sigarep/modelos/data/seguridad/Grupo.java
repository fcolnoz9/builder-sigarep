
package sigarep.modelos.data.seguridad;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;


import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToMany;

import sigarep.modelos.data.transacciones.UsuarioGrupo;


@Entity
@Table(name ="grupo")
public class Grupo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idgrupo", unique = true , nullable=false)
	private Integer idGrupo;
	
	@Column(name="nombre")
    private String nombre;
	
	@Column(name = "descripcion")
    private String descripcion;
	
	@Column(name = "estado")
    private boolean estatus;
	
	
	@ManyToMany(fetch=FetchType.EAGER) 
	@JoinTable(name = "funcion_grupo",
	joinColumns = { @JoinColumn(name = "idGrupo",referencedColumnName = "idGrupo") },
	inverseJoinColumns = { @JoinColumn(name = "idnodo", referencedColumnName = "id") })
    private List<Nodo> nodos = new ArrayList<Nodo>();
	
	//bi-directional many-to-one association to MiembroGrupo
	@OneToMany(mappedBy="grupo")
	private List<UsuarioGrupo> usuariosGrupos = new LinkedList<UsuarioGrupo>();

	public Grupo() {
		super();
		nodos = new ArrayList<Nodo>();
	}


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
	 public List<Nodo> getNodos() {
		return nodos;
	}

	public void setNodos(List<Nodo> nodos) {
		this.nodos = nodos;
	}
	
	public void addNodos(Nodo nodo)
    {
        this.nodos.add(nodo);
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