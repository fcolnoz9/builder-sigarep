package sigarep.viewmodels.maestros;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;

//import sigarep.modelos.data.maestros.Noticia;
//import sigarep.modelos.servicio.maestros.ServicioNoticia;

import sigarep.herramientas.Archivo;
import sigarep.modelos.data.maestros.Noticia;
import sigarep.modelos.servicio.maestros.ServicioNoticia;
import java.util.Map;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMWindow {
	
	@WireVariable ServicioNoticia servicionoticia;
	@Wire("#modalDialog")
    private Window win;
    
    
    private List<Noticia> listaNoticia1;
    private List<Noticia> listaNoticia;
    private String titulo;
    private String contenido;
    private String enlaceNoticia;
    
    private Integer idNoticia;
    private Archivo fotoNoticia = new Archivo();
    private Noticia noticiaSeleccionada1;
	
	public Integer getIdNoticia() {
		return idNoticia;
	}

	public void setIdNoticia(Integer idNoticia) {
		this.idNoticia = idNoticia;
	}

	public Archivo getFotoNoticia() {
		return fotoNoticia;
	}

	public void setFotoNoticia(Archivo fotoNoticia) {
		this.fotoNoticia = fotoNoticia;
	}

	public String getEnlaceNoticia() {
		return enlaceNoticia;
	}

	public void setEnlaceNoticia(String enlaceNoticia) {
		this.enlaceNoticia = enlaceNoticia;
	}

	public List<Noticia> getListaNoticia() {
		return listaNoticia1;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public void setListaNoticia(List<Noticia> listaNoticia) {
		this.listaNoticia1 = listaNoticia;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Init
    public void init(
    		
    		@ContextParam(ContextType.VIEW) Component view,
           // @ExecutionArgParam("titulo") String v1 ,
           // @ExecutionArgParam("contenido") String v2,
           // @ExecutionArgParam("enlaceNoticia") String v3,
           // @ExecutionArgParam("idNoticia") Integer v4,
           // @ExecutionArgParam("listaNoticia") List<Noticia> v5,
            @ExecutionArgParam("noticiaSeleccionada") Noticia v6
    		
    		) {
        Selectors.wireComponents(view, this, false);
        /*this.titulo = v1;
        
        this.contenido = v2;
        this.enlaceNoticia = v3;*/
       // this.idNoticia = v4;
        
        buscarNoticia2();
        //this.listaNoticia1 = v5;
        this.noticiaSeleccionada1 = v6;
//        System.out.println(noticiaSeleccionada1.getTitulo());
       /* if(listaNoticia1.get(0).getIdNoticia() == idNoticia){
        	System.out.println("si lo encontro");
        }else{System.out.println("no lo encontro");}*/
        //System.out.println(listaNoticia1.size());
        //System.out.println(idNoticia);
        //this.fotoNoticia = listaNoticia1.get(0).getFotoNoticia();
        this.titulo = noticiaSeleccionada1.getTitulo();
        this.contenido = noticiaSeleccionada1.getContenido();
        this.enlaceNoticia = noticiaSeleccionada1.getEnlaceNoticia();
        this.fotoNoticia = noticiaSeleccionada1.getFotoNoticia();
        if(fotoNoticia != null){System.out.println("hay");}else{System.out.println("no hay");}
          }
	
	@Command
	@NotifyChange({"listaNoticia1"})
	public void buscarNoticia2(){
		listaNoticia1 =servicionoticia.listadoNoticia();
	}
    
   
    @Command
    public void closeThis() {
        win.detach();
    }
	

}
