package sigarep.viewmodels.seguridad;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import org.zkoss.zul.ListModelList;

import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;


import sigarep.herramientas.Archivo;
import sigarep.herramientas.EnviarCorreo;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.UtilidadesSigarep;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.Persona;

import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.data.transacciones.InstanciaMiembro;
import sigarep.modelos.data.transacciones.InstanciaMiembroPK;
import sigarep.modelos.data.transacciones.SolicitudApelacion;
import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;

import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioPersona;
import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioUsuario;
import sigarep.modelos.servicio.transacciones.ServicioInstanciaMiembro;
import sigarep.modelos.servicio.transacciones.ServicioUsuarioGrupo;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMUsuario {
	@WireVariable 
	private ServicioPersona serviciopersona;
	@WireVariable 
	private ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable 
	private ServicioInstanciaMiembro servicioInstanciaMiembro;
	@WireVariable
	private ServicioUsuarioGrupo serviciousuariogrupo;
	
	private List<InstanciaApelada> listaInstancia = new LinkedList<InstanciaApelada>();	
	private InstanciaApelada instanciaseleccionada;
	private String tituloinstancia = "";
	private String cargo ="";
	
	
	private InstanciaMiembro instanciaMiembro = new InstanciaMiembro();
	private InstanciaMiembroPK instanciaMiembroPK = new InstanciaMiembroPK();
	private List<InstanciaMiembro> listaInstanciaMiembro = new LinkedList<InstanciaMiembro>();
	
	private String cedulaPersona="";
	private String nombre="";
	private String apellido="";
	private String telefono="";
	
	private List<Persona> listaPersona;
	private Persona personaSeleccionado = new Persona();
	private String nombreUsuario="";
	private String correo="";
	private String confirmarcorreo="";
	private String clave="";
	private String confirmarcontrasenia="";
	private String nuevaContrasenia = "";
	private String nombreCompleto="";
	private String cedulaPersonafiltro = "";
	private String nombreCompletofiltro = "";
	private String nombreUsuariofiltro = "";
	
	private Archivo fotoUsuario = new Archivo();
	private Media mediaUsuario;
	private AImage imagenUsuario;
	String ruta = UtilidadesSigarep.obtenerDirectorio();
	
	
	private ListModelList<Grupo> modeloGrupo;
	List<Grupo> listGrupo;
	
	@WireVariable
	private String correoLogin;


	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario(); //para llamar a los diferentes mensajes de dialogo

	
	private Usuario usuarioSeleccionado;
	@WireVariable
	private Grupo grupoSeleccionado;	
	@WireVariable
	private List<Usuario> listaUsuario = new LinkedList<Usuario>();
	@WireVariable
	private List<Grupo> listaGrupoPertenece = new LinkedList<Grupo>();
	@WireVariable
	private List<Grupo> listaGrupoNoPertenece = new LinkedList<Grupo>();
	@WireVariable
	private ServicioGrupo serviciogrupo;
	@WireVariable
	private ServicioUsuario serviciousuario;
	
	VMUtilidadesDeSeguridad seguridad = new VMUtilidadesDeSeguridad();
	
	@Wire("#winRegistrarUsuario")//para conectarse a la ventana con el ID
	Window ventana;
	 @AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
	
	public List<InstanciaMiembro> getListaInstanciaMiembro() {
		return listaInstanciaMiembro;
	}

	public void setListaInstanciaMiembro(
			List<InstanciaMiembro> listaInstanciaMiembro) {
		this.listaInstanciaMiembro = listaInstanciaMiembro;
	}

	public String getConfirmarcorreo() {
		return confirmarcorreo;
	}

	public void setConfirmarcorreo(String confirmarcorreo) {
		this.confirmarcorreo = confirmarcorreo;
	}

	public Archivo getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(Archivo fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public Media getMediaUsuario() {
		return mediaUsuario;
	}

	public void setMediaUsuario(Media mediaUsuario) {
		this.mediaUsuario = mediaUsuario;
	}

	public AImage getImagenUsuario() {
		return imagenUsuario;
	}

	public void setImagenUsuario(AImage imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}

	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}
	
	
	public List<Grupo> getListaGrupoPertenece() {
		return listaGrupoPertenece;
	}

	public void setListaGrupoPertenece(List<Grupo> listaGrupoPertenece) {
		this.listaGrupoPertenece = listaGrupoPertenece;
	}
	
	public List<Grupo> getListaGrupoNoPertenece() {
		return listaGrupoNoPertenece;
	}

	public void setListaGrupoNoPertenece(List<Grupo> listaGrupoNoPertenece) {
		this.listaGrupoNoPertenece = listaGrupoNoPertenece;
	}

		
	// Metodo que buscar los lapsos y cargarlos en el combobox
	@Command
	@NotifyChange({ "listaUsuario" })
	public void buscarListadoUsuario() {
		listaUsuario = serviciousuario.listadoUsuario();
	}
	
	public InstanciaMiembro getInstanciaMiembro() {
		return instanciaMiembro;
	}

	public void setInstanciaMiembro(InstanciaMiembro instanciaMiembro) {
		this.instanciaMiembro = instanciaMiembro;
	}

	public InstanciaMiembroPK getInstanciaMiembroPK() {
		return instanciaMiembroPK;
	}

	public void setInstanciaMiembroPK(InstanciaMiembroPK instanciaMiembroPK) {
		this.instanciaMiembroPK = instanciaMiembroPK;
	}

	public List<InstanciaApelada> getListaInstancia() {
		return listaInstancia;
	}

	public void setListaInstancia(List<InstanciaApelada> listaInstancia) {
		this.listaInstancia = listaInstancia;
	}

	public InstanciaApelada getInstanciaseleccionada() {
		return instanciaseleccionada;
	}

	public void setInstanciaseleccionada(InstanciaApelada instanciaseleccionada) {
		this.instanciaseleccionada = instanciaseleccionada;
	}

	public String getTituloinstancia() {
		return tituloinstancia;
	}

	public void setTituloinstancia(String tituloinstancia) {
		this.tituloinstancia = tituloinstancia;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Persona getPersonaSeleccionado() {
		return personaSeleccionado;
	}

	public void setPersonaSeleccionado(Persona personaSeleccionado) {
		this.personaSeleccionado = personaSeleccionado;
	}
	
	public List<Persona> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<Persona> listaPersona) {
		this.listaPersona = listaPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public ListModelList<Grupo> getModeloGrupo() {
		return modeloGrupo;
	}

	public void setModeloGrupo(ListModelList<Grupo> modeloGrupo) {
		this.modeloGrupo = modeloGrupo;
	}
	
	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}
	
	public String getCorreoLogin() {
		return correoLogin;
	}

	public void setCorreoLogin(String correoLogin) {
		this.correoLogin = correoLogin;
	}

	public String getConfirmarcontrasenia() {
		return confirmarcontrasenia;
	}

	public void setConfirmarcontrasenia(String confirmarcontrasenia) {
		this.confirmarcontrasenia = confirmarcontrasenia;
	}
	
	public List<Grupo> getListGrupo() {
		return listGrupo;
	}

	public void setListGrupo(List<Grupo> listGrupo) {
		this.listGrupo = listGrupo;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getNuevaContrasenia() {
		return nuevaContrasenia;
	}

	public void setNuevaContrasenia(String nuevaContrasenia) {
		this.nuevaContrasenia = nuevaContrasenia;
	}
	
	public String getCedulaPersona() {
		return cedulaPersona;
	}

	public void setCedulaPersona(String cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}
	
	public String getCedulaPersonafiltro() {
		return cedulaPersonafiltro;
	}

	public void setCedulaPersonafiltro(String cedulaPersonafiltro) {
		this.cedulaPersonafiltro = cedulaPersonafiltro;
	}

	public String getNombreCompletofiltro() {
		return nombreCompletofiltro;
	}

	public void setNombreCompletofiltro(String nombreCompletofiltro) {
		this.nombreCompletofiltro = nombreCompletofiltro;
	}

	public String getNombreUsuariofiltro() {
		return nombreUsuariofiltro;
	}

	public void setNombreUsuariofiltro(String nombreUsuariofiltro) {
		this.nombreUsuariofiltro = nombreUsuariofiltro;
	}
	
	
	/**Guardar usuario
	 * @parameters Todos los asociados al registro de usuario.
	 * @return Registrar en el sistema un usuario .
	 * @throws Dispara excepción imagenUsuario es nula, servicioInstanciaMiembro, serviciousuario y al enviar correo .
	 */
	@Command
	@NotifyChange({ "nombreUsuario", "clave", "confirmarcontrasenia","correo","confirmarcorreo","listaPersona","listaInstancia","listaUsuario","cedulaPersona","nombre",
		"apellido","telefono", "listaGrupoPertenece","listaGrupoNoPertenece","imagenUsuario","listaInstanciaMiembro","tituloinstancia","cargo", "imagenUsuario","fotoUsuario"})
	public void guardarUsuario(@BindingParam("gruposDelUsuario") List<Listitem> gruposDelUsuario) {
		boolean existeUsuario = false;
		Usuario usuario = new Usuario();
		if (nombreUsuario.equals("") || correo.equals("") || cedulaPersona.equals("") || nombre.equals("")  || apellido.equals("") 
				|| clave.equals("")  || confirmarcontrasenia.equals("") ) {

			mensajeAlUsuario.advertenciaLlenarCampos();

		}
		else if(!correo.equals(confirmarcorreo)){

			mensajeAlUsuario.advertenciaContrasennasNoCoinciden();

		}
		else if(!clave.equals(confirmarcontrasenia)){

			mensajeAlUsuario.advertenciaContrasennasNoCoinciden();
		}
		else if(gruposDelUsuario.size()==0){

			mensajeAlUsuario.advertenciaSeleccionarGrupoUsuario();
		}
		else
		{

			Usuario usuarioAux = serviciousuario.encontrarUsuario(nombreUsuario);
			Persona mipersona1 = null;
			Persona mipersona2 = serviciopersona.buscarPersonaNombreUsuario(nombreUsuario);
			List<Persona> personaencontrado = serviciopersona.buscarPersonaFiltro(cedulaPersona, "", "");
			
			boolean encontrado = false;
			boolean encontrado2 = false;
			boolean permiso = false;
				//mipersona = serviciopersona.buscaUnaPersona(cedulaPersona);
			
			if(usuarioAux!=null){
				
				existeUsuario = true;
//				 
//				for (UsuarioGrupo usuarioGrupoABorrar : usuarioAux.getUsuariosGrupos()) {
//					 serviciousuariogrupo.eliminarUsuarioGrupo(usuarioGrupoABorrar.getId().getIdGrupo(), usuarioGrupoABorrar.getId().getNombreUsuario());
//				}
			}
			//serviciopersona..buscarPersonaNombreUsuario(nombreUsuario);
			
			//usuarioAux.getNombreUsuario().equals(nombreUsuario) ||
			if(personaencontrado.size()>0 && mipersona2 != null){
				System.out.println("si encontra la persona y el usuario existe");
				mipersona1 = personaencontrado.get(0);
				if( mipersona1.getCedulaPersona().equals(mipersona2.getCedulaPersona()) ){
					encontrado = true;
					permiso = true;	
					encontrado2 = true;
				}	
				else{	
					Messagebox.show("El nombre de usuario ya esta en uso, escriba otro.", "Error", Messagebox.OK, Messagebox.ERROR);//msj obligatorio Debe cambiar el nombre del usuario
				}
				
			}
			if(personaencontrado.size()==0 && mipersona2 != null ){
				System.out.println("no existe persona y ya existe ese usuario");
				Messagebox.show("El nombre de usuario ya esta en uso, escriba otro.", "Error", Messagebox.OK, Messagebox.ERROR);//msj obligatorio Debe cambiar el nombre del usuario
			}
			if(personaencontrado.size()>0 && mipersona2 == null ){
				System.out.println("si existe persona y no existe ese usuario");
				permiso = true;
				encontrado = true;
				mipersona1 = personaencontrado.get(0);
			}
			if(personaencontrado.size()==0 && mipersona2 == null ){
				System.out.println("no existe persona y no existe ese usuario");
				permiso = true;
			}
			
			if(permiso) {
				System.out.println("tengo permiso");
			
				if(encontrado){
					System.out.println("paso por encontrado");
					if(mipersona1 != null){
						System.out.println("se cumple la condicion 0");
						usuarioAux = serviciousuario.encontrarUsuario(mipersona1.getNombreUsuario().getNombreUsuario());
						for (UsuarioGrupo usuarioGrupoABorrar : usuarioAux.getUsuariosGrupos()) {
							 serviciousuariogrupo.eliminarUsuarioGrupo(usuarioGrupoABorrar.getId().getIdGrupo(), usuarioGrupoABorrar.getId().getNombreUsuario());
						}
						
						}
//					else{
//							usuarioAux = serviciousuario.encontrarUsuario(nombreUsuario);
//							if(usuarioAux != null){
//								existeUsuario = true;
//							
//								for (UsuarioGrupo usuarioGrupoABorrar : usuarioAux.getUsuariosGrupos()) {
//									 serviciousuariogrupo.eliminarUsuarioGrupo(usuarioGrupoABorrar.getId().getIdGrupo(), usuarioGrupoABorrar.getId().getNombreUsuario());
//								}
//								
//							}
//						}
					if(mipersona2 != null){
						if( mipersona1.getCedulaPersona().equals(mipersona2.getCedulaPersona()) && !mipersona1.getNombreUsuario().getNombreUsuario().equals(mipersona2.getNombreUsuario().getNombreUsuario()) )
							{
								System.out.println("se cumple la condicion 1");
								usuarioAux = serviciousuario.encontrarUsuario(nombreUsuario);
								for (UsuarioGrupo usuarioGrupoABorrar : usuarioAux.getUsuariosGrupos()) {
									 serviciousuariogrupo.eliminarUsuarioGrupo(usuarioGrupoABorrar.getId().getIdGrupo(), usuarioGrupoABorrar.getId().getNombreUsuario());
								}
								
							}
						else{ System.out.println("no se cumple la condicion 2");
								
								
//								usuarioAux = serviciousuario.encontrarUsuario(mipersona1.getNombreUsuario().getNombreUsuario());
//								for (UsuarioGrupo usuarioGrupoABorrar : usuarioAux.getUsuariosGrupos()) {
//									 serviciousuariogrupo.eliminarUsuarioGrupo(usuarioGrupoABorrar.getId().getIdGrupo(), usuarioGrupoABorrar.getId().getNombreUsuario());
//								}
								
							}
					}
					
				}
					
					
				
				if(encontrado2){
					System.out.println("encontrado 2");
					usuarioAux = serviciousuario.encontrarUsuario(nombreUsuario);
					if(usuarioAux!=null){
					
						existeUsuario = true;
						 
						for (UsuarioGrupo usuarioGrupoABorrar : usuarioAux.getUsuariosGrupos()) {
							 serviciousuariogrupo.eliminarUsuarioGrupo(usuarioGrupoABorrar.getId().getIdGrupo(), usuarioGrupoABorrar.getId().getNombreUsuario());
						}
					}
						
				}
				
			//inicio
				
				
				
				
				if(!existeUsuario)System.out.println("no existia");usuario.setFechaCreacion(new Date());
				usuario.setNombreUsuario(nombreUsuario);
				usuario.setClave(clave);
				usuario.setCorreo(correo);
				nombreCompleto = nombre + " " + apellido;
				usuario.setNombreCompleto(nombreCompleto);
				usuario.setEstatus(true);
				
				if(imagenUsuario == null){
					try {
						imagenUsuario = new AImage(ruta+"/Sigarep.webapp/WebContent/imagenes/iconos/usuario.png");
						fotoUsuario = new Archivo();
						fotoUsuario.setNombreArchivo(imagenUsuario.getName());
						fotoUsuario.setTipo(imagenUsuario.getContentType());
						fotoUsuario.setContenidoArchivo(imagenUsuario.getByteData());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
				
				usuario.setFoto(fotoUsuario);
				
//				Listcell celda= new Listcell();
//				label.setValue("generico");
//				
//				Listbox aux = new Listbox();
//				
//				
//				
//				Listitem listi = new Listitem();
//				listi.insertBefore(listi, celda);
//						listi.i lbxNoticias.getChildren().get(lbxNoticias.getChildren().size()-2); 
//				listi.i
//				lbxNoticias.removeChild(lbxNoticias.getChildren().get(lbxNoticias.getChildren().size()-2));  
//				lbxNoticias.insertBefore(listi, (Component) lbxNoticias.getChildren().get(0)); 	 
//				gruposDelUsuario
				boolean marca= false;
				for(Listitem miGrupo :gruposDelUsuario){
					Grupo grupo = new Grupo();
					String nombreGrupo = miGrupo.getLabel();
					grupo = serviciogrupo.buscarGrupoNombre(nombreGrupo);
					
					UsuarioGrupoPK usuarioGrupoPK = new UsuarioGrupoPK();
					UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
					usuarioGrupoPK.setIdGrupo(grupo.getIdGrupo());
					usuarioGrupoPK.setNombreUsuario(nombreUsuario);
					
					usuarioGrupo.setId(usuarioGrupoPK);
					usuarioGrupo.setUsuario(usuario);
					usuarioGrupo.setGrupo(grupo);
					usuarioGrupo.setEstatus(true);
					
					UsuarioGrupo usuarioGrupoAux = new UsuarioGrupo();
					usuarioGrupoAux.setId(usuarioGrupo.getId());
					usuarioGrupoAux.setGrupo(usuarioGrupo.getGrupo());
					usuarioGrupoAux.setUsuario(usuarioGrupo.getUsuario());
					usuarioGrupoAux.setEstatus(true);
					usuario.addUsuarioGrupo(usuarioGrupoAux);
					
					Usuario usuario1 = new Usuario();
					usuario1.setClave(usuario.getClave());
					usuario1.setCorreo(usuario.getCorreo());
					usuario1.setEstatus(usuario.getEstatus());
					usuario1.setFechaCreacion(usuario.getFechaCreacion());
					usuario1.setFoto(usuario.getFoto());
					usuario1.setNombreCompleto(usuario.getNombreCompleto());
					usuario1.setUltimoAcceso(usuario1.getUltimoAcceso());
					System.out.println("parte 1 ++++++++");
					serviciousuario.guardarUsuario(usuario);
					System.out.println("parte 2 ++++++++");
					//+++++++++AQui
					if(!marca){
						marca=true;
						Grupo grupo1 = new Grupo();
						grupo1 = serviciogrupo.buscarGrupoNombre("generico");
						UsuarioGrupoPK usuarioGrupoPK1 = new UsuarioGrupoPK();
						UsuarioGrupo usuarioGrupo1 = new UsuarioGrupo();
						usuarioGrupoPK1.setIdGrupo(1);
						usuarioGrupoPK1.setNombreUsuario(nombreUsuario);
						
						usuarioGrupo1.setId(usuarioGrupoPK1);
						usuarioGrupo1.setUsuario(usuario1);
						usuarioGrupo1.setGrupo(grupo1);
						usuarioGrupo1.setEstatus(true);
						
						UsuarioGrupo usuarioGrupoAux1 = new UsuarioGrupo();
						usuarioGrupoAux1.setId(usuarioGrupo1.getId());
						usuarioGrupoAux1.setGrupo(usuarioGrupo1.getGrupo());
						usuarioGrupoAux1.setUsuario(usuarioGrupo1.getUsuario());
						usuarioGrupoAux1.setEstatus(true);
						usuario1.addUsuarioGrupo(usuarioGrupoAux1);
						System.out.println("parte 3 ++++++++");
						serviciousuario.guardarUsuario(usuario1);
						System.out.println("parte 4 ++++++++");
					
					}
				}
				
				Persona persona = new Persona();
				persona.setCedulaPersona(cedulaPersona);
				persona.setNombre(nombre);
				persona.setApellido(apellido);
				
				persona.setNombreUsuario(usuario);
				persona.setCorreo(correo);
				persona.setEstatus(true);
				persona.setTelefono(telefono);
				serviciopersona.guardar(persona);
				
				if(mipersona1!=null && mipersona2 != null){
						if( mipersona1.getCedulaPersona().equals(mipersona2.getCedulaPersona()) && !mipersona1.getNombreUsuario().getNombreUsuario().equals(mipersona2.getNombreUsuario().getNombreUsuario()) )
							{
								System.out.println("se borra el mipersona1");
								serviciousuario.eliminarFisicamente(mipersona2.getNombreUsuario().getNombreUsuario());	
							}else if ( !mipersona1.getCedulaPersona().equals(mipersona2.getCedulaPersona()) && !mipersona1.getNombreUsuario().getNombreUsuario().equals(mipersona2.getNombreUsuario().getNombreUsuario()) ){ 
								System.out.println("las cedulas y usuarios son diferentes");
								
							}else {
								System.out.println("no se q mas");
							}
				}
				else if(mipersona1==null && mipersona2 != null) {
					System.out.println("la persona no existe, ya se valido");
				}else if(mipersona1!=null && mipersona2 == null){System.out.println("usuario no existe pero la pesona si");
					serviciousuario.eliminarFisicamente(mipersona1.getNombreUsuario().getNombreUsuario());
				}else System.out.println("ambos son nulos");
				
				
				if (tituloinstancia.equals("")) {
						
				}
				else
				{
	//			    if (cargo.equals(""))
	//			    	mensajes.advertenciaLlenarCampos();
	//			    else
	//			    {
						
				    	for(int i=0;listaInstanciaMiembro.size()>i;i++){
				    		instanciaMiembro = listaInstanciaMiembro.remove(i);
					    	instanciaMiembro.getId().setCedulaPersona(cedulaPersona);
					    	
							instanciaMiembro.setPersona(persona);
							try {
								
								servicioInstanciaMiembro.guardar(instanciaMiembro);
							} catch (Exception e) {
								System.out.println(e.getMessage());
							}	
				    	}
	//			    }
				}
				
	
				mensajeAlUsuario.informacionRegistroCorrecto();
	
//				if(existeUsuario==false){
//					try {
//						usuario.setFechaCreacion(new Date());
//						UsuarioGrupoPK usuarioGrupoPK = new UsuarioGrupoPK();
//						UsuarioGrupo usuarioGrupo = new UsuarioGrupo();
//						usuarioGrupoPK.setIdGrupo(1);
//						usuarioGrupoPK.setNombreUsuario(nombreUsuario);
//						Grupo grupo = new Grupo();
//						grupo = serviciogrupo.buscarGrupo(1); //Grupo de id=1 que tiene la función cambiar contraseña
//						usuarioGrupo.setId(usuarioGrupoPK);
//						usuarioGrupo.setUsuario(usuario);
//						usuarioGrupo.setGrupo(grupo);
//						usuarioGrupo.setEstatus(true);
//						usuario.addUsuarioGrupo(usuarioGrupo);
//						serviciousuario.guardarUsuario(usuario); //Agregandole el grupo que tiene la función cambiar contraseña al usuario nuevo
//						
//					} catch (Exception e) {
//						System.out.println(e.getMessage());
//					}
//		
//				}
				try {
					EnviarCorreo enviar = new EnviarCorreo();
					enviar.sendEmailWelcomeToSigarep(correo,nombreUsuario,clave);
	
					mensajeAlUsuario.informacionHemosEnviadoCorreo();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				//fin
				listaInstanciaMiembro = new LinkedList<InstanciaMiembro>();
				instanciaMiembroPK = null;
				instanciaMiembro= null;
				tituloinstancia = "";
				cargo = "";
				limpiar();
			}else System.out.println("tengo permiso");
			
				
		}
	}
	
	/**Agregar Instancia
	 * @parameters instanciaMiembro.
	 * @return Llena la lista listaInstanciaMiembro .
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaUsuario","tituloinstancia","listaInstanciaMiembro","fotoUsuario","imagenUsuario"})
	public void agregarInstancia() {		
		InstanciaMiembro instanciaM = new InstanciaMiembro();
		InstanciaMiembroPK instanciaMPK = new InstanciaMiembroPK();
		if (tituloinstancia.equals("")) {
			//falta el msj
			System.out.println("Debe seleccionar una instancia");	
		}
		else
		{	
			if(cargo.equals(""))cargo="No asignado";
			instanciaMPK.setCedulaPersona("0000");
			instanciaMPK.setIdInstanciaApelada(getInstanciaseleccionada().getIdInstanciaApelada());
			instanciaM.setId(instanciaMPK);
			instanciaM.setCargo(cargo);
			instanciaM.setEstatus(true);
			instanciaM.setFechaEntrada(new Date());
			instanciaM.setInstanciaApelada(getInstanciaseleccionada());
			boolean llego = false;
			
			for(int j = 0;listaInstanciaMiembro.size()>j && !llego ;j++){
				
				if( (!listaInstanciaMiembro.get(j).getCargo().equals(instanciaM.getCargo())) && (listaInstanciaMiembro.get(j).getId().getIdInstanciaApelada() == instanciaM.getId().getIdInstanciaApelada()) ){
					listaInstanciaMiembro.remove(j);
					listaInstanciaMiembro.add(instanciaM); 
					
					llego = true;
					break;
				}else if( listaInstanciaMiembro.get(j).getCargo().equals(instanciaM.getCargo()) && (listaInstanciaMiembro.get(j).getId().getIdInstanciaApelada() == instanciaM.getId().getIdInstanciaApelada()) ){ break;}
				if(listaInstanciaMiembro.size()-1==j  ){
					listaInstanciaMiembro.add(instanciaM);
					
				}
			}
			if(listaInstanciaMiembro.size()==0)listaInstanciaMiembro.add(instanciaM);
				
		
		}
	}
	
	/**Mostrar instancia seleccionada
	 * @parameters instanciaMiembro.
	 * @return Llena los campos cargo y tituloinstancia .
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaUsuario","tituloinstancia","listaInstanciaMiembro","cargo"})
	public void mostrarInstancia() {
	
		cargo = instanciaMiembro.getCargo();
		tituloinstancia = instanciaMiembro.getInstanciaApelada().getInstanciaApelada();
		instanciaseleccionada = instanciaMiembro.getInstanciaApelada();
	}
	
	/**Inicializar 
	 * @parameters No posee parametros.
	 * @return Llena las listas requeridas para el registro de usuario.
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	public void init() {
		// initialization code
		buscarUsuario();
		buscarListadoUsuario();
		buscarListadoGrupos();
	}
//REVISAR
	/**Busca los usuarios  
	 * @parameters No posee parametros.
	 * @return Llena la lista de listadoGruposActivos.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaUsuario","listaPersona","listaInstancia" })
	public void buscarUsuario() {
		listaUsuario = serviciousuario.buscarUsuario(nombreUsuario);
		listaPersona = serviciopersona.listadoPersona();
		listaInstancia = servicioInstanciaApelada.listadoInstanciaApelada();
	}
	
	/**Busca grupos 
	 * @parameters No posee parametros.
	 * @return Llena la lista de listadoGruposActivos.
	 * @throws No dispara ninguna excepción.
	 */
	@NotifyChange({ "listaGrupoNoPertenece" })
	public void buscarListadoGrupos() {
		List<Grupo> listadoGruposActivos = serviciogrupo.listadoGrupo();
		listaGrupoNoPertenece = listadoGruposActivos;
	}

	/**Limpiar los campos
	 * @parameters confirmarcontrasenia,nuevaContrasenia.
	 * @return Metodo que limpia todos los campos asociados al registro de usuario.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "nombreUsuario", "clave", "confirmarcontrasenia","correo","confirmarcorreo","listaPersona","listaInstancia","listaUsuario","cedulaPersona","nombre",
		"apellido","telefono", "listaGrupoPertenece","listaGrupoNoPertenece","imagenUsuario","fotoUsuario","listaInstanciaMiembro","tituloinstancia","cargo","listaGrupoPertenece"})
	public void limpiar() {
		
		nombreUsuario = "";
		clave = "";
		confirmarcontrasenia = "";
		correo = "";
		cedulaPersona = "";
		nombre = "";
		apellido = "";
		telefono = "";
		mediaUsuario = null;
		imagenUsuario = null;
		fotoUsuario = null;
		confirmarcorreo = "";
		listaInstanciaMiembro = new LinkedList<InstanciaMiembro>();
		instanciaMiembroPK = null;
		instanciaMiembro= null;
		tituloinstancia = "";
		cargo = "";
		instanciaseleccionada= null;
		listaGrupoPertenece.clear();
		buscarUsuario();
		buscarListadoGrupos();
	}
	
	/**Cancelar cambiar contraseña
	 * @parameters confirmarcontrasenia,nuevaContrasenia.
	 * @return Limpia los campos.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "nombreUsuario", "contrasenia", "confirmarcontrasenia","nuevaContrasenia"})
	public void limpiarCambiarContrasenia() {
		confirmarcontrasenia = "";
		nuevaContrasenia = "";
	}	

	/**Eliminar usuario seleccionado
	 * @parameters personaSeleccionado.
	 * @return Elimina un usuario seleccionado.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "cedulaPersona","listaUsuario","listaPersona","listaGrupoPertenece","listaGrupoNoPertenece"})
	public void eliminarUsuario() {
		serviciousuario.eliminar(getPersonaSeleccionado().getNombreUsuario().getNombreUsuario());
		listaInstanciaMiembro = getPersonaSeleccionado().getInstanciaMiembros();
		serviciopersona.eliminar(getPersonaSeleccionado().getCedulaPersona());
		for(int i=0;listaInstanciaMiembro.size()>i;i++){
    		instanciaMiembro = listaInstanciaMiembro.remove(i);

			try {
				
				servicioInstanciaMiembro.eliminar(instanciaMiembro.getId());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}	
    	}

		mensajeAlUsuario.informacionEliminarCorrecto();
		limpiar();
	}
	
	/**Mostrar usuario seleccionado
	 * @parameters personaSeleccionado.
	 * @return Llena los campos asociados al registro de usaurio.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "nombreUsuario","clave","confirmarcontrasenia","confirmarcorreo","correo","cedulaPersona","apellido","nombre","telefono","listaGrupoPertenece", "listaGrupoNoPertenece", "personaSeleccionado","fotoUsuario","imagenUsuario","listaInstanciaMiembro","tituloinstancia","cargo"})
	public void mostrarSeleccionado() {	
		limpiar();
		listaInstanciaMiembro = new LinkedList<InstanciaMiembro>();
		instanciaMiembroPK = null;
		instanciaMiembro= null;
		
		nombreUsuario = getPersonaSeleccionado().getNombreUsuario().getNombreUsuario();
		clave = getPersonaSeleccionado().getNombreUsuario().getClave();
		confirmarcontrasenia = getPersonaSeleccionado().getNombreUsuario().getClave();
		correo = getPersonaSeleccionado().getCorreo();
		confirmarcorreo = getPersonaSeleccionado().getCorreo();
		cedulaPersona = getPersonaSeleccionado().getCedulaPersona();
		nombre = getPersonaSeleccionado().getNombre();
		apellido = getPersonaSeleccionado().getApellido();
		telefono = getPersonaSeleccionado().getTelefono();
		fotoUsuario = getPersonaSeleccionado().getNombreUsuario().getFoto();
		listaInstanciaMiembro = getPersonaSeleccionado().getInstanciaMiembros();
		
		for(int i=0;listaInstanciaMiembro.size()>i;i++){
			if(listaInstanciaMiembro.get(i).getEstatus()==false)
    		instanciaMiembro = listaInstanciaMiembro.remove(i);
    					
    	}
		
		if(fotoUsuario!=null){
			System.out.println("esta no nula");
			if (getPersonaSeleccionado().getNombreUsuario().getFoto().getTamano() > 0){
				try {
					imagenUsuario = new AImage(getPersonaSeleccionado().getNombreUsuario().getFoto().getNombreArchivo(),getPersonaSeleccionado().getNombreUsuario().getFoto().getContenidoArchivo());
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
			else{imagenUsuario = null;}
		}else{
			System.out.println("esta nula");
			try {
				imagenUsuario = new AImage(ruta+"/Sigarep.webapp/WebContent/imagenes/iconos/usuario.png");
				
				fotoUsuario = new Archivo();
				
				fotoUsuario.setNombreArchivo(imagenUsuario.getName());
				fotoUsuario.setTipo(imagenUsuario.getContentType());
				fotoUsuario.setContenidoArchivo(imagenUsuario.getByteData());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		listaGrupoPertenece = serviciogrupo.listadoGrupoPerteneceUsuario(getPersonaSeleccionado().getNombreUsuario().getNombreUsuario());
		listaGrupoNoPertenece = serviciogrupo.listadoGrupoNoPerteneceUsuario(getPersonaSeleccionado().getNombreUsuario().getNombreUsuario());
	}
	
	/**Quitar grupo a un usuario
	 * @parameters listaGrupoPertenece,listaGrupoNoPertenece.
	 * @return Actualiza la lista de los grupos a los que pertenece un usuario y la lista de los grupos a los que no pertenece un usuario.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"listaGrupoNoPertenece","listaGrupoPertenece"})
	public void quitarGrupo(@BindingParam("itemGrupoPertenece") Listitem itemGrupoPertenece) {
		Grupo grupoAux1 = serviciogrupo.buscarGrupoNombre(itemGrupoPertenece.getLabel());
		listaGrupoNoPertenece.add(grupoAux1);
		listaGrupoPertenece.remove(itemGrupoPertenece.getIndex());
	}
	
	/**Agregar grupo a un usuario
	 * @parameters listaGrupoPertenece,listaGrupoNoPertenece.
	 * @return Actualiza la lista de los grupos a los que pertenece un usuario y la lista de los grupos a los que no pertenece un usuario.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"listaGrupoPertenece","listaGrupoNoPertenece"})
	public void agregarGrupo(@BindingParam("itemGrupoNoPertenece") Listitem itemGrupoNoPertenece) {
		Grupo grupoAux2 = serviciogrupo.buscarGrupoNombre(itemGrupoNoPertenece.getLabel());
		listaGrupoPertenece.add(grupoAux2);
		listaGrupoNoPertenece.remove(itemGrupoNoPertenece.getIndex());		
	}
	
	/**Cambiar contraseña
	 * @parameters confirmarcontrasenia,nuevaContrasenia.
	 * @return Actualiza contraseña de usuario.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"confirmarcontrasenia", "nuevaContrasenia" })
	public void cambiarContrasenia() {
	    if(confirmarcontrasenia.equals("") || nuevaContrasenia.equals(""))
	    	mensajeAlUsuario.advertenciaLlenarCampos();
	    else{
	    	if(serviciousuario.cambiarContrasena(seguridad.getUsuario().getUsername(),nuevaContrasenia, confirmarcontrasenia)==true){
	    		mensajeAlUsuario.informacionContrasennaAtualizada();
	    		limpiarCambiarContrasenia();
	    	}
	    }
	}
	
	/**Recuperar contraseña
	 * @parameters correoLogin.
	 * @return Envia contraseña a correo de usuario.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "correoLogin" })
	public void recuperarContrasenna() {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("-1");
		if (correoLogin=="")

			mensajeAlUsuario.advertenciaLlenarCampos();

		else {
			List<Usuario> listaUsuarios = serviciousuario.listadoUsuario();
				Usuario usuarioAux = new Usuario();
				for (int i = 0; i < listaUsuarios.size(); i++) {
					usuarioAux = listaUsuarios.get(i);
					if (usuarioAux.getCorreo() != null) 
						if (usuarioAux.getCorreo().equals(correoLogin) || usuarioAux.getNombreUsuario().equals(correoLogin)){
							usuario = usuarioAux;
						}
				}
				if (usuario.getNombreUsuario()!="-1") {
					EnviarCorreo enviar = new EnviarCorreo();
					enviar.sendEmail(usuario.getCorreo(), usuario.getClave());

					mensajeAlUsuario.informacionContrasennaRecuperada();

				}
				else

					mensajeAlUsuario.errorUsuarioEmailNoRegistrado();

		}
	}
	
	/** Filtro de lista de usuarios
	 * @parameters cedulaPersonafiltro, nombreCompletofiltro, nombreUsuariofiltro.
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"listaPersona"})
	public void filtros(){
		listaPersona = serviciopersona.buscarPersonaFiltro(cedulaPersonafiltro, nombreCompletofiltro, nombreUsuariofiltro);
	}
	
	
	
	/** cargarImagenUsuario
	 * @parameters imagenUsuario, UploadEvent event Zkoss UI.
	 * @return No devuelve ningun valor.
	 * @throws la Excepcion es que la mediaUsuario sea null
	 */
	@Command
	@NotifyChange("imagenUsuario")
	public void cargarImagenUsuario(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event){
		mediaUsuario = event.getMedia();
		fotoUsuario = new Archivo();
		if (mediaUsuario != null) {
			
			if (mediaUsuario instanceof org.zkoss.image.Image) {
				fotoUsuario.setNombreArchivo(mediaUsuario.getName());
				fotoUsuario.setTipo(mediaUsuario.getContentType());
				fotoUsuario.setContenidoArchivo(mediaUsuario.getByteData());
				if(fotoUsuario.getTamano()>500000){
					mensajeAlUsuario.advertenciaTamannoImagen(500);
					
					fotoUsuario = new Archivo();
					}else{imagenUsuario = (AImage) mediaUsuario;}
				
			} else {
				Messagebox.show("El archivo: "+mediaUsuario+" no es una imagenNoticia valida", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		} 
	}

	
	
	
	/**
	 * Cerrar Ventana
	 * 
	 * @param binder
	 * @return cierra el .zul asociado al VM
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	@Command
	@NotifyChange({ "nombreUsuario", "clave", "confirmarcontrasenia","correo","confirmarcorreo","listaPersona","listaInstancia","listaUsuario","cedulaPersona","nombre",
		"apellido","telefono", "listaGrupoPertenece","listaGrupoNoPertenece","imagenUsuario","fotoUsuario","listaInstanciaMiembro","tituloinstancia","cargo","listaGrupoPertenece"})
	public void cerrarVentana(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if( !nombreUsuario.equals("") || !correo.equals("") || !cedulaPersona.equals("") || !nombre.equals("")  || !apellido.equals("") 
				|| !clave.equals("")  || !confirmarcontrasenia.equals("") )
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
	
	@Command
	@NotifyChange({"confirmarcontrasenia", "nuevaContrasenia" })
	public void cerrarVentanaCambiarContrasenha(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(!confirmarcontrasenia.equals("") || !nuevaContrasenia.equals(""))
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
	
}