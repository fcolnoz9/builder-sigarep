
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import sigarep.modelos.data.transacciones.UsuarioGrupo;

/*
 * @ (#) Grupo.java 
 *
 * Copyright 2013 Builder. Todos los derechos reservados.
 * CONFIDENCIAL. El uso está sujeto a los términos de la licencia.
 */
/*
 ** Esta clase es del registro del maestro "Grupo"
 * UCLA DCYT Sistemas de Informacion.
 * @author Equipo: Builder-SIGAREP 
 * @Version 1.0,
 * @Since  04/02/13
 */

@Entity
@Access(AccessType.FIELD)
//anotación indica que el JavaBean es una entidad persistente
@Table(name ="grupo")
public class Grupo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	// Clave primaria de la clase
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	// Genera el ID del Grupo
	@Column(name="id_grupo", unique = true , nullable=false)
	private Integer idGrupo;
	
	@Column(name="nombre")
    private String nombre;
	
	@Column(name = "descripcion")
    private String descripcion;
	
	@Column(name = "estado")
    private boolean estatus;
	
	
	@ManyToMany(fetch=FetchType.EAGER) 
	@JoinTable(name = "funcion_grupo",
	joinColumns = { @JoinColumn(name = "id_grupo",referencedColumnName = "id_grupo") },
	inverseJoinColumns = { @JoinColumn(name = "id_nodo", referencedColumnName = "id") })
    private Set<Nodo> nodos = new HashSet<Nodo>();
	
	//bi-directional many-to-one association to MiembroGrupo
	@OneToMany(fetch = FetchType.EAGER, mappedBy="grupo",  cascade={CascadeType.ALL})
	private Set<UsuarioGrupo> usuariosGrupos = new HashSet<UsuarioGrupo>();

	public Grupo() {
		super();
		nodos = new HashSet<Nodo>();
	}

	// Métodos GET y SET
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
	
	public void addNodos(Nodo nodo)
    {
        this.nodos.add(nodo);
    }
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.grupo")
	public Set<UsuarioGrupo> getUsuariosGrupos() {
		return this.usuariosGrupos;
	}

	public void setUsuarioGrupos(Set<UsuarioGrupo> usuarioGrupos) {
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