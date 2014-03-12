package sigarep.modelos.servicio.transacciones;

import java.util.Date;

public class ListaHistorialEstudianteVeredicto {
	private String cedulaEstudiante;
	private String primerNombre;
	private String primerApellido;
	private String email;
	private String telefono;
	private String programa;
	private String segundoNombre;
	private String segundoApellido;
	private Date  fechaNacimiento;
	private String sexo;
	private Date anioIngreso;
	private Integer unidadesCursadas;
	private Integer unidadesAprobadas;
	private float indiceGrado;
	private String nombreSancion;
	private String codigoLapso;
	private String nombreTipoMotivo;
	private String nombreAsignatura;
	private String veredicto1;
	private String veredicto2;
	private String veredicto3;
	public ListaHistorialEstudianteVeredicto(String cedulaEstudiante,
			String primerNombre, String primerApellido, String email,
			String telefono, String programa, String segundoNombre,
			String segundoApellido, Date fechaNacimiento, String sexo,
			Date anioIngreso, Integer unidadesCursadas,
			Integer unidadesAprobadas, float indiceGrado, String nombreSancion,
			String codigoLapso, String nombreTipoMotivo,
			String nombreAsignatura, String veredicto1, String veredicto2,
			String veredicto3) {
		super();
		this.cedulaEstudiante = cedulaEstudiante;
		this.primerNombre = primerNombre;
		this.primerApellido = primerApellido;
		this.email = email;
		this.telefono = telefono;
		this.programa = programa;
		this.segundoNombre = segundoNombre;
		this.segundoApellido = segundoApellido;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
		this.anioIngreso = anioIngreso;
		this.unidadesCursadas = unidadesCursadas;
		this.unidadesAprobadas = unidadesAprobadas;
		this.indiceGrado = indiceGrado;
		this.nombreSancion = nombreSancion;
		this.codigoLapso = codigoLapso;
		this.nombreTipoMotivo = nombreTipoMotivo;
		this.nombreAsignatura = nombreAsignatura;
		this.veredicto1 = veredicto1;
		this.veredicto2 = veredicto2;
		this.veredicto3 = veredicto3;
	}
	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}
	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getAnioIngreso() {
		return anioIngreso;
	}
	public void setAnioIngreso(Date anioIngreso) {
		this.anioIngreso = anioIngreso;
	}
	public Integer getUnidadesCursadas() {
		return unidadesCursadas;
	}
	public void setUnidadesCursadas(Integer unidadesCursadas) {
		this.unidadesCursadas = unidadesCursadas;
	}
	public Integer getUnidadesAprobadas() {
		return unidadesAprobadas;
	}
	public void setUnidadesAprobadas(Integer unidadesAprobadas) {
		this.unidadesAprobadas = unidadesAprobadas;
	}
	public float getIndiceGrado() {
		return indiceGrado;
	}
	public void setIndiceGrado(float indiceGrado) {
		this.indiceGrado = indiceGrado;
	}
	public String getNombreSancion() {
		return nombreSancion;
	}
	public void setNombreSancion(String nombreSancion) {
		this.nombreSancion = nombreSancion;
	}
	public String getCodigoLapso() {
		return codigoLapso;
	}
	public void setCodigoLapso(String codigoLapso) {
		this.codigoLapso = codigoLapso;
	}
	public String getNombreTipoMotivo() {
		return nombreTipoMotivo;
	}
	public void setNombreTipoMotivo(String nombreTipoMotivo) {
		this.nombreTipoMotivo = nombreTipoMotivo;
	}
	public String getNombreAsignatura() {
		return nombreAsignatura;
	}
	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}
	public String getVeredicto1() {
		return veredicto1;
	}
	public void setVeredicto1(String veredicto1) {
		this.veredicto1 = veredicto1;
	}
	public String getVeredicto2() {
		return veredicto2;
	}
	public void setVeredicto2(String veredicto2) {
		this.veredicto2 = veredicto2;
	}
	public String getVeredicto3() {
		return veredicto3;
	}
	public void setVeredicto3(String veredicto3) {
		this.veredicto3 = veredicto3;
	}
	

	}