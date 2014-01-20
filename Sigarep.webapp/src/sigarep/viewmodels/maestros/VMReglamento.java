
package sigarep.viewmodels.maestros;

import java.io.IOException;
import java.util.Date;
import java.util.List;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;

import sigarep.herramientas.Documento;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.data.maestros.ReglamentoFiltros;
import sigarep.modelos.servicio.maestros.ServicioReglamento;


@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReglamento {
	
@WireVariable ServicioReglamento servicioreglamento;


private Integer IdDocumento; 
private String titulo,titulofiltro;
private String descripcion,descripcionfiltro;
private Boolean estatus;
private Date fechaSubida;
private  Documento documento = new Documento();
private String categoria,categoriafiltro;
private Media media;
private List<Reglamento> listaReglamento;
private Reglamento reglamentoSeleccionado;
private String nombreDoc;

private ReglamentoFiltros filtros = new ReglamentoFiltros();

private MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario();

public VMReglamento() {
	super();
	// TODO Auto-generated constructor stub
}

@Init 

public void init(){
	
	
	fechaSubida = new Date();
	media = null;
	documento = new Documento();
	filtros();
	
}

@NotifyChange({"filtros"})
public ReglamentoFiltros getFiltros() {
	return filtros;
}
public void setFiltros(ReglamentoFiltros filtros) {
	this.filtros = filtros;
}


public String getNombreDoc() {
	return nombreDoc;
}

public void setNombreDoc(String nombreDoc) {
	this.nombreDoc = nombreDoc;
}

public Integer getIdDocumento() {
	return IdDocumento;
}

public void setIdDocumento(Integer idDocumento) {
	IdDocumento = idDocumento;
}

public String getTitulo() {
	return titulo;
}

public void setTitulo(String titulo) {
	this.titulo = titulo;
}

public String getTitulofiltro() {
	return titulofiltro;
}

public void setTitulofiltro(String titulofiltro) {
	this.titulofiltro = titulofiltro;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public String getDescripcionfiltro() {
	return descripcionfiltro;
}

public void setDescripcionfiltro(String descripcionfiltro) {
	this.descripcionfiltro = descripcionfiltro;
}

public Boolean getEstatus() {
	return estatus;
}

public void setEstatus(Boolean estatus) {
	this.estatus = estatus;
}

public Date getFechaSubida() {
	return fechaSubida;
}

public void setFechaSubida(Date fechaSubida) {
	this.fechaSubida = fechaSubida;
}

public Documento getDocumento() {
	return documento;
}

public void setDocumento(Documento documento) {
	this.documento = documento;
}

public String getCategoria() {
	return categoria;
}

public void setCategoria(String categoria) {
	this.categoria = categoria;
}

public String getCategoriafiltro() {
	return categoriafiltro;
}

public void setCategoriafiltro(String categoriafiltro) {
	this.categoriafiltro = categoriafiltro;
}

public Media getMedia() {
	return media;
}

public void setMedia(Media media) {
	this.media = media;
}

public List<Reglamento> getListaReglamento() {
	return listaReglamento;
}

public void setListaReglamento(List<Reglamento> listaReglamento) {
	this.listaReglamento = listaReglamento;
}






@Command
@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria", "fechaSubida", "nombreDoc"})
public Reglamento getReglamentoSeleccionado() {
	return reglamentoSeleccionado;
}

public void setReglamentoSeleccionado(Reglamento reglamentoSeleccionado) {
	this.reglamentoSeleccionado = reglamentoSeleccionado;
}

@Command
@NotifyChange({"titulo", "descripcion","categoria","fechaSubida", "listaReglamento","nombreDoc"})//, "sexo"})
public void guardarReglamento(){
	System.out.print("ESTO ES NOMBREDOC");
	System.out.print(nombreDoc);
if (titulo == null || descripcion.equals("") || categoria.equals("") || fechaSubida.equals("") || nombreDoc == null)
	mensajeAlUsuario.advertenciaLlenarCampos();	
	//Messagebox.show("No pueden haber campos vacios", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	else{
		Reglamento reg = new Reglamento(IdDocumento,documento ,titulo, descripcion,true, fechaSubida,categoria);
		try{
			servicioreglamento.guardarReglamento(reg);
		}
		catch(Exception e){
		    System.out.println(e.getMessage());
		}
		
		mensajeAlUsuario.informacionArchivoCargado();
		//Messagebox.show("Datos almacenados correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
		filtros();
	}
}


@Command
@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria","fechaSubida", "listaReglamento","nombreDoc"})
public void limpiar(){
	IdDocumento = null;
	titulo = null;
	descripcion = "";
	categoria="";
	fechaSubida= null;
	media = null;
	documento = new Documento();
	nombreDoc="";
	filtros();
}



@Command
@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria","fechaSubida","listaReglamento","nombreDoc"})
public void mostrarSeleccionado(){
	Reglamento reg = getReglamentoSeleccionado();
	IdDocumento = reg.getIdDocumento();
	titulo = reg.getTitulo(); 
	descripcion = reg.getDescripcion();
	categoria = reg.getCategoria();
	fechaSubida = reg.getFechaSubida();
	documento = reg.getDocumento();
	if (reg.getDocumento().getNombreDocumento()!=null){
		nombreDoc=reg.getDocumento().getNombreDocumento();
	}
	

}

@Command
@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria","fechaSubida", "listaReglamento","nombreDoc"})
public void eliminarReglamento(){
if (titulo == null)
	mensajeAlUsuario.ErrorImposibleEliminar();	
	//Messagebox.show("IdDocumento de Reglamento no encontrado, no se pudo eliminar", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
	else{
		try{
			servicioreglamento.eliminar(getReglamentoSeleccionado().getIdDocumento());
		}
		catch(Exception e){
		    System.out.println(e.getMessage());
		}
		mensajeAlUsuario.informacionArchivoEliminado();
		//msjs.informacionEliminarCorrecto();
		limpiar();
		filtros();
	}
}

@Command
@NotifyChange("nombreDoc")
public void cargarDocumento(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
	media = event.getMedia();
	if (media != null) {
		if (media.getContentType().equals("image/jpeg") ||
			media.getContentType().equals("application/pdf") ||
			media.getContentType().equals("application/msword") ||
			media.getContentType().equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ||
			media.getContentType().equals("application/vnd.oasis.opendocument.text") ||
			media.getContentType().equals("application/x-vnd.oasis.opendocument.text"))
		{
			documento.setNombreDocumento(media.getName());
			documento.setTipoDocumento(media.getContentType());
			documento.setContenidoDocumento(media.getByteData());
			nombreDoc=documento.getNombreDocumento();
		} else {
			mensajeAlUsuario.advertenciaFormatoNoSoportado();
		}
	} 
}

@Command
public void descargarDocumento(){
	Filedownload.save(documento.getContenidoDocumento(), documento.getTipoDocumento(), documento.getNombreDocumento());
}


//Método que busca y filtra los archivos
		@Command
		@NotifyChange({ "listaReglamento" })
		public void filtros() {
			List<Reglamento> listaF = servicioreglamento.listaReglamento();
			listaReglamento = servicioreglamento.buscarReglamentoTitulo(filtros, listaF);
			listaReglamento = servicioreglamento.buscarReglamentoDescripcion(filtros, listaReglamento);
			listaReglamento = servicioreglamento.buscarReglamentoCategoria(filtros, listaReglamento);
		}
		
	
}

