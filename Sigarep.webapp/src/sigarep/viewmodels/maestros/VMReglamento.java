
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
import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.data.maestros.ReglamentoFiltros;
import sigarep.modelos.servicio.maestros.ServicioReglamento;

/**Reglamento
 * UCLA-DCYT Sistemas de Información
 * @author Equipo:Builder-SIGAREP
 * @version 1.0
 * @since 22/01/14
 */

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMReglamento {
	
@WireVariable ServicioReglamento servicioreglamento;


private Integer IdDocumento; 
private String titulo;
private String descripcion;
private Boolean estatus;
private Date fechaSubida;
private  Documento documento = new Documento();
private String categoria;
private Media media;
private List<Reglamento> listaReglamento;
private Reglamento reglamentoSeleccionado;
private String nombreDoc;
private String tituloF ="";
private String categoriaF ="";
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

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
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

public String getTituloF() {
	return tituloF;
}

public void setTituloF(String tituloF) {
	this.tituloF = tituloF;
}

public String getCategoriaF() {
	return categoriaF;
}

public void setCategoriaF(String categoriaF) {
	this.categoriaF = categoriaF;
}

/** guardarReglamento
 * @param "titulo", "descripcion","categoria","fechaSubida", "listaReglamento","nombreDoc"
 * @return No devuelve ningun valor
 * @throws las excepciones son que los datos a guardar esten vacios
 */

@Command // Permite manipular la propiedad de ViewModel
@NotifyChange({"IdDocumento","titulo", "descripcion","categoria","fechaSubida", "listaReglamento","nombreDoc", "documento"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es los atributos de la pantalla se va a colocar en blanco al guardar!!
public void guardarReglamento(){
if (titulo == null || descripcion ==null|| categoria==null )
	mensajeAlUsuario.advertenciaLlenarCampos();	

else if (documento.getTamanoDocumento() < 1)
//else if (documento.getNombreDocumento().equals(""))
	mensajeAlUsuario.advertenciaCargarDocumento();
	else{
		Reglamento reg = new Reglamento(IdDocumento,documento ,titulo, descripcion,true, fechaSubida,categoria);
		try{
			servicioreglamento.guardarReglamento(reg);
		}
		catch(Exception e){
		    System.out.println(e.getMessage());
		}
		limpiar();
		filtros();
		mensajeAlUsuario.informacionArchivoCargado();
		
	}
}
/** limpiar
 * @param "IdDocumento","titulo", "descripcion", "categoria","fechaSubida", "listaReglamento","nombreDoc"
 * @return no devuelve nada
 * @throws 
 * 
 */

@Command
@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria","fechaSubida", "listaReglamento","nombreDoc", "documento"})
public void limpiar(){
	IdDocumento = null;
	titulo = null;
	descripcion = null;
	categoria=null;
	fechaSubida= new Date();
	media = null;
	documento = new Documento();
	nombreDoc=null;
	filtros();
	
}

/**mostrarSeleccionado
 *@param IdDocumento,titulo,descripcion,categoria,fechaSubida,listaReglamento,nombreDoc
 *@return No devuelve ningun valor 
 */


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
//	
//	IdDocumento =  reglamentoSeleccionado.getIdDocumento();
//	titulo =  reglamentoSeleccionado.getTitulo();
//	descripcion =  reglamentoSeleccionado.getDescripcion();
//	categoria =  reglamentoSeleccionado.getCategoria();
//	fechaSubida =  reglamentoSeleccionado.getFechaSubida();
//	documento =  reglamentoSeleccionado.getDocumento();
	if (reg.getDocumento().getNombreDocumento()!=null){
	nombreDoc=reg.getDocumento().getNombreDocumento();
	}
	

}
/**eliminarReglamento
 * @param IdDocumento,titulo,descripcion,categoria,fechaSubida,listaReglamento,nombreDoc
 * @return No devuelve ningun valor
 * @throws La Excepcion es que quiera eliminar con los campos vacion, sin seleccionar ningun registro
 */

@Command 
@NotifyChange({"IdDocumento","titulo", "descripcion", "categoria","fechaSubida", "listaReglamento","nombreDoc"})
public void eliminarReglamento(){
if (titulo == null || descripcion ==null || categoria ==null|| documento ==null)
	mensajeAlUsuario.advertenciaSeleccionarParaEliminar();
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
/**cargarDocumento
 * @param nombreDoc,UploadEvent event Zkoss UI.
 * @return No devuelve ningun valor
 * @throws La excepcion es que media sea null
 */

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
/**descargarDocumento
 * @return No devuelve ningun valor
 */

@Command
public void descargarDocumento(){
	Filedownload.save(documento.getContenidoDocumento(), documento.getTipoDocumento(), documento.getNombreDocumento());
}


/**filtros. Filtra por las variables tituloF,categoriaF 
 *@param tituloF,categoriaF
 *@return No devuelve ningun valor.
 */
		@Command
		@NotifyChange({ "listaReglamento","tituloF","categoriaF" })
		public void filtros() {
			
			listaReglamento = servicioreglamento.buscarReglamento(tituloF,categoriaF);

		}
		
	
}

