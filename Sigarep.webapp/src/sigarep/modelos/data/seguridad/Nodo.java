package sigarep.modelos.data.seguridad;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu_arbol")
public class Nodo {
	
	@Id
	@Column(name="id", unique = true ,length=10, nullable=false)
	private Integer id;
	
	@Column(name="estado")
	private String estado;
	
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="nombrefuncion")
    private String nombrefuncion;
	
	@Column(name="padre")
	private Integer padre;
	
	@Column(name="vinculo")
    private String vinculo;
	
	@Column(name="ruta_modal")
    private String rutaModal;
   
	//bi-directional many-to-one association to MiembroGrupo
//	@OneToMany(mappedBy="grupo")
//	private List<FuncionGrupo> funcionesGrupo = new LinkedList<FuncionGrupo>();
    
    public Nodo() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public Nodo(Integer id, String tipo, String nombrefuncion,
			String vinculo, String estado, Integer padre) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.nombrefuncion = nombrefuncion;
		this.vinculo = vinculo;
		this.estado = estado;
		this.padre = padre;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getNombrefuncion() {
		return nombrefuncion;
	}
	public void setNombrefuncion(String nombrefuncion) {
		this.nombrefuncion = nombrefuncion;
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
	
	
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id.nodo")
//	public List<FuncionGrupo> getFuncionesGrupo() {
//		return this.funcionesGrupo;
//	}
//
//	public void setFuncionesGrupo(List<FuncionGrupo> funcionesGrupo) {
//		this.funcionesGrupo = funcionesGrupo;
//	}
//
//	public FuncionGrupo addFuncionGrupo(FuncionGrupo funcionGrupo) {
//		getFuncionesGrupo().add(funcionGrupo);
//		funcionGrupo.setNodo(this);
//		return funcionGrupo;
//	}
//
//	public FuncionGrupo removeUsuarioGrupo(FuncionGrupo funcionGrupo) {
//		getFuncionesGrupo().remove(funcionGrupo);
//		funcionGrupo.setGrupo(null);
//		return funcionGrupo;
//	}
}

