package sigarep.viewmodels.seguridad;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;
import sigarep.herramientas.Archivo;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.UtilidadesSigarep;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.Persona;
import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.data.transacciones.InstanciaMiembro;
import sigarep.modelos.data.transacciones.InstanciaMiembroPK;
import sigarep.modelos.data.transacciones.UsuarioGrupo;
import sigarep.modelos.data.transacciones.UsuarioGrupoPK;
import sigarep.modelos.servicio.maestros.ServicioContactoSigarep;
import sigarep.modelos.servicio.maestros.ServicioInstanciaApelada;
import sigarep.modelos.servicio.maestros.ServicioPersona;
import sigarep.modelos.servicio.seguridad.ServicioGrupo;
import sigarep.modelos.servicio.seguridad.ServicioUsuario;
import sigarep.modelos.servicio.transacciones.ServicioInstanciaMiembro;
import sigarep.modelos.servicio.transacciones.ServicioUsuarioGrupo;

/**
* Clase VMUsuario : Clase ViewModels relacionada con el Maestro Usuario.
*
* @author Equipo Builder
* @version 1.0
* @since 04/12/13
* @last 10/05/2014
*/

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMUsuario {
	//-----------------Servicios----------------------------
	@WireVariable 
	private ServicioContactoSigarep serviciocontactosigarep;
	@WireVariable 
	private ServicioPersona serviciopersona;
	@WireVariable 
	private ServicioInstanciaApelada servicioInstanciaApelada;
	@WireVariable 
	private ServicioInstanciaMiembro servicioInstanciaMiembro;
	@WireVariable
	private ServicioUsuarioGrupo serviciousuariogrupo;
	@WireVariable
	private ServicioGrupo serviciogrupo;
	@WireVariable
	private ServicioUsuario serviciousuario;
	//-----------------Variables de control ------------------	
	String ruta = UtilidadesSigarep.obtenerDirectorio();
	//-----------------Variables Usuario ------------------
	private String cedulaPersona="";
	private String nombre="";
	private String apellido="";
	private String telefono="";
	private String nombreUsuario="";
	private String correo="";
	private String confirmarcorreo="";
	private String clave="";
	private String confirmarcontrasenia="";
	private String nuevaContrasenia = "";
	private String nombreCompleto="";
	private String tituloinstancia = "";
	private String cargo ="";
	private String correoLogin;
	private String nombreUsuarioAuxiliar="";
	//-----------------Variables Filtro---------------------
	private String cedulaPersonafiltro = "";
	private String nombreCompletofiltro = "";
	private String nombreUsuariofiltro = "";
	//-----------------Variables Lista----------------------
	private List<InstanciaApelada> listaInstancia = new LinkedList<InstanciaApelada>();	
	private List<InstanciaMiembro> listaInstanciaMiembro = new LinkedList<InstanciaMiembro>();
	private List<Persona> listaPersona;
	private ListModelList<Grupo> modeloGrupo;
	private List<Grupo> listGrupo;
	private List<Usuario> listaUsuario = new LinkedList<Usuario>();
	private List<Grupo> listaGrupoPertenece = new LinkedList<Grupo>();
	private List<Grupo> listaGrupoNoPertenece = new LinkedList<Grupo>();
	//-----------------Variables Objeto---------------------
	private InstanciaApelada instanciaseleccionada;	
	private InstanciaMiembro instanciaMiembro = new InstanciaMiembro();
	private InstanciaMiembroPK instanciaMiembroPK = new InstanciaMiembroPK();
	private Persona personaSeleccionado = new Persona();
	private Archivo fotoUsuario = new Archivo();
	private Media mediaUsuario;
	private AImage imagenUsuario;
	private Usuario usuarioSeleccionado;
	private Grupo grupoSeleccionado;
	VMUtilidadesDeSeguridad seguridad = new VMUtilidadesDeSeguridad();
	MensajesAlUsuario mensajeAlUsuario = new MensajesAlUsuario(); //para llamar a los diferentes mensajes de dialogo
		
	@Wire("#winRegistrarUsuario")//para conectarse a la ventana con el ID
	Window ventana;
	
	@AfterCompose //para poder conectarse con los componentes en la vista, es necesario si no da null Pointer
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }

	// Métodos Set y Get
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
	//Fin Métodos Set y Get
	
	/**
	 * Init. Código de inicialización.
	 * 
	 * @param ninguno.
	 * @return Carga las listas requeridas para el registro de usuario
	 * @throws No dispara ninguna excepcion.
	 */
	
	@Init
	public void init() {
		// initialization code
		buscarUsuario();
		buscarListadoUsuario();
		buscarListadoGrupos();
	}
	
	/**Busca los usuarios registrados en la BD.  
	 * @parameters ninguno.
	 * @return Llena la lista de usuario, la lista de personas y la lista de instancias, 
	 * el command indica a las variables el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaUsuario","listaPersona","listaInstancia" })
	public void buscarUsuario() {
		listaUsuario = serviciousuario.buscarUsuario(nombreUsuario);
		listaPersona = serviciopersona.listadoPersona();
		listaInstancia = servicioInstanciaApelada.listadoInstanciaApelada();
	}
	
	/**Busca los grupos de usuarios registrados. 
	 * @param ninguno.
	 * @return Llena la lista de listadoGruposActivos.
	 * @throws No dispara ninguna excepción.
	 */
	@NotifyChange({ "listaGrupoNoPertenece" })
	public void buscarListadoGrupos() {
		List<Grupo> listadoGruposActivos = serviciogrupo.listadoGrupo();
		listaGrupoNoPertenece = listadoGruposActivos;
	}
	
	/**Busca la lista de los usuarios registrados. 
	 * @param ninguno.
	 * @return Llena la lista de usuarios, 
	 * el command indica a las variables el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */
	
	@Command
	@NotifyChange({ "listaUsuario" })
	public void buscarListadoUsuario() {
		listaUsuario = serviciousuario.listadoUsuario();
	}
		
	/**Guarda la información de un usuario en la BD.
	 * @parameters gruposDelUsuario.
	 * @return Registro en el sistema de un usuario, 
	 * el command indica a las variables el cambio que se hará en el objeto.
	 * @throws Dispara excepción si la imagenUsuario es nula, servicioInstanciaMiembro, serviciousuario y al enviar correo.
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
			mensajeAlUsuario.advertenciaContraseñasNoCoinciden();
		}
		else if(!clave.equals(confirmarcontrasenia)){
			mensajeAlUsuario.advertenciaContraseñasNoCoinciden();
		}
		else if(gruposDelUsuario.size()==0){
			mensajeAlUsuario.advertenciaSeleccionarGrupoUsuario();
		}
		else if(!mensajeAlUsuario.errorValidarCorreo(correo)){}
		else {
			nombreUsuarioAuxiliar = nombreUsuario;
			Usuario usuarioAux = serviciousuario.encontrarUsuario(nombreUsuario);
			Persona mipersona1 = null;
			Persona mipersona2 = serviciopersona.buscarPersonaNombreUsuario(nombreUsuario);
			List<Persona> personaencontrado = serviciopersona.buscarPersonaFiltro(cedulaPersona, "", "");
			boolean encontrado = false;
			boolean encontrado2 = false;
			boolean permiso = false;
			if(usuarioAux!=null){
				existeUsuario = true;
			}
			if(personaencontrado.size()>0 && mipersona2 != null){
				mipersona1 = personaencontrado.get(0);
				if( mipersona1.getCedulaPersona().equals(mipersona2.getCedulaPersona()) ){
					encontrado = true;
					permiso = true;	
					encontrado2 = true;
				}	
				else{	
					mensajeAlUsuario.errorUsuarioNoValido();
				} 
			}
			if(personaencontrado.size()==0 && mipersona2 != null ){
				mensajeAlUsuario.errorUsuarioNoValido();
			}
			if(personaencontrado.size()>0 && mipersona2 == null ){
				permiso = true;
				encontrado = true;
				mipersona1 = personaencontrado.get(0);
			}
			if(personaencontrado.size()==0 && mipersona2 == null ){
				permiso = true;
			}
			
			if(permiso) {
				if(encontrado){
					if(mipersona1 != null){
						usuarioAux = serviciousuario.encontrarUsuario(mipersona1.getUsuario().getNombreUsuario());
						for (UsuarioGrupo usuarioGrupoABorrar : usuarioAux.getUsuariosGrupos()) {
							 serviciousuariogrupo.eliminarUsuarioGrupo(usuarioGrupoABorrar.getId().getIdGrupo(), usuarioGrupoABorrar.getId().getNombreUsuario());
						}
					}

					if(mipersona2 != null){
						if( mipersona1.getCedulaPersona().equals(mipersona2.getCedulaPersona()) && !mipersona1.getUsuario().getNombreUsuario().equals(mipersona2.getUsuario().getNombreUsuario()) )
							{
								usuarioAux = serviciousuario.encontrarUsuario(nombreUsuario);
								for (UsuarioGrupo usuarioGrupoABorrar : usuarioAux.getUsuariosGrupos()) {
									 serviciousuariogrupo.eliminarUsuarioGrupo(usuarioGrupoABorrar.getId().getIdGrupo(), usuarioGrupoABorrar.getId().getNombreUsuario());
								}
							}
						else{}
					}
				}
				if(encontrado2){
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
					serviciousuario.guardarUsuario(usuario);
					if(!marca){
						marca=true;
						if(marca){
							grupo = new Grupo();
							grupo = serviciogrupo.buscarGrupo(1);
							usuarioGrupoPK = new UsuarioGrupoPK();
							usuarioGrupo = new UsuarioGrupo();
							usuarioGrupoPK.setIdGrupo(grupo.getIdGrupo());
							usuarioGrupoPK.setNombreUsuario(nombreUsuario);
							
							usuarioGrupo.setId(usuarioGrupoPK);
							usuarioGrupo.setUsuario(usuario);
							usuarioGrupo.setGrupo(grupo);
							usuarioGrupo.setEstatus(true);
							
							usuario.addUsuarioGrupo(usuarioGrupo);
							serviciousuario.guardarUsuario(usuario);
						}
					}
				}
				
				Persona persona = new Persona();
				persona.setCedulaPersona(cedulaPersona);
				persona.setNombre(nombre);
				persona.setApellido(apellido);
				persona.setUsuario(usuario);
				persona.setCorreo(correo);
				persona.setEstatus(true);
				persona.setTelefono(telefono);
				serviciopersona.guardar(persona);
				
				if(mipersona1!=null && mipersona2 != null){
						if( mipersona1.getCedulaPersona().equals(mipersona2.getCedulaPersona()) && !mipersona1.getUsuario().getNombreUsuario().equals(mipersona2.getUsuario().getNombreUsuario()))
							{
								serviciousuario.eliminarFisicamente(mipersona2.getUsuario().getNombreUsuario());	
							}else if ( !mipersona1.getCedulaPersona().equals(mipersona2.getCedulaPersona()) && !mipersona1.getUsuario().getNombreUsuario().equals(mipersona2.getUsuario().getNombreUsuario()) ){ 	
							}else {}
				}
				else if(mipersona1==null && mipersona2 != null) {
				}else if(mipersona1!=null && mipersona2 == null){
					serviciousuario.eliminarFisicamente(mipersona1.getUsuario().getNombreUsuario());
				}else;
				
				if (tituloinstancia.equals("")) {}
				else
				{
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
				}
				mensajeAlUsuario.informacionRegistroCorrecto();
				try {
					serviciocontactosigarep.sendEmailWelcomeToSigarep(correo,nombreUsuario,clave);
					mensajeAlUsuario.informacionHemosEnviadoCorreo();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				//fin
				listaInstanciaMiembro = null;
				instanciaMiembroPK = null;
				instanciaMiembro= null;
				tituloinstancia = "";
				cargo = "";
				limpiar();
			}else ;
		}
	}
	
	/**Actualizar la foto de Perfil y Menu de Usuario
	 * @param ninguno.
	 * @return ninguno, el command indica a las variables el cambio que se hará en el objeto..
	 * @throws No dispara ninguna excepción.
	 */
	
	@GlobalCommand
	@NotifyChange({ "nombreUsuarioAuxiliar"})
    public void actualizarPerfilYMenuUsuario(){
		Usuario usuario = serviciousuario.encontrarUsuario(nombreUsuarioAuxiliar);
		if(usuario!=null){			
			if(usuario.getNombreUsuario().equals(seguridad.getUsuario().getUsername())){
				BindUtils.postGlobalCommand(null, null, "Init", null);
				BindUtils.postGlobalCommand(null, null, "cargarFotoImagen", null);
			}			
		}
    }
	
	/**Agregar Instancia a la lista de instancias del miembro (usuario seleccionado).
	 * @parameters ninguno.
	 * @return Llena la lista listaInstanciaMiembro, 
	 * el command indica a las variables el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaUsuario","tituloinstancia","listaInstanciaMiembro","fotoUsuario","imagenUsuario"})
	public void agregarInstancia() {		
		InstanciaMiembro instanciaM = new InstanciaMiembro();
		InstanciaMiembroPK instanciaMPK = new InstanciaMiembroPK();
		if (tituloinstancia.equals("")) {
		}
		else
		{	
			if(cargo.equals(""))
				cargo="No asignado";
			instanciaMPK.setCedulaPersona("0000");
			instanciaMPK.setIdInstanciaApelada(getInstanciaseleccionada().getIdInstanciaApelada());
			instanciaM.setId(instanciaMPK);
			instanciaM.setCargo(cargo);
			instanciaM.setEstatus(true);
			instanciaM.setFechaEntrada(new Date());
			instanciaM.setInstanciaApelada(getInstanciaseleccionada());
			boolean llego = false;
			
			for(int j = 0;listaInstanciaMiembro.size()>j && !llego ;j++){
				if((!listaInstanciaMiembro.get(j).getCargo().equals(instanciaM.getCargo())) 
						&& (listaInstanciaMiembro.get(j).getId().getIdInstanciaApelada() == instanciaM.getId().getIdInstanciaApelada())){
					listaInstanciaMiembro.remove(j);
					listaInstanciaMiembro.add(instanciaM); 
					llego = true;
					break;
				}
				else if( listaInstanciaMiembro.get(j).getCargo().equals(instanciaM.getCargo()) 
						&& (listaInstanciaMiembro.get(j).getId().getIdInstanciaApelada() == instanciaM.getId().getIdInstanciaApelada())){ 
					break;
				}
				if(listaInstanciaMiembro.size()-1==j ){
					listaInstanciaMiembro.add(instanciaM);
				}
			}
			if(listaInstanciaMiembro.size()==0)
				listaInstanciaMiembro.add(instanciaM);
		}
	}
		
	/**Quitar grupo seleccionado a un usuario de la lista de grupos asociados al usuario.
	 * @parameters itemGrupoPertenece.
	 * @return Actualiza la lista de los grupos a los que pertenece un usuario y la lista 
	 * de los grupos a los que no pertenece un usuario, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"listaGrupoNoPertenece","listaGrupoPertenece"})
	public void quitarGrupo(@BindingParam("itemGrupoPertenece") Listitem itemGrupoPertenece) {
		Grupo grupoAux1 = serviciogrupo.buscarGrupoNombre(itemGrupoPertenece.getLabel());
		listaGrupoNoPertenece.add(grupoAux1);
		listaGrupoPertenece.remove(itemGrupoPertenece.getIndex());
	}
	
	/**Agregar grupo seleccionado a un usuario de la lista de grupos No asociados al usuario.
	 * @parameters itemGrupoNoPertenece.
	 * @return Actualiza la lista de los grupos a los que pertenece un usuario y la 
	 * lista de los grupos a los que no pertenece un usuario, el command indica a las 
	 * variables el cambio que se hará en el objeto..
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"listaGrupoPertenece","listaGrupoNoPertenece"})
	public void agregarGrupo(@BindingParam("itemGrupoNoPertenece") Listitem itemGrupoNoPertenece) {
		Grupo grupoAux2 = serviciogrupo.buscarGrupoNombre(itemGrupoNoPertenece.getLabel());
		listaGrupoPertenece.add(grupoAux2);
		listaGrupoNoPertenece.remove(itemGrupoNoPertenece.getIndex());		
	}
	
	/**Cambiar contraseña con la cual el usuario ingresa al sistema.
	 * @parame ninguno.
	 * @return Actualiza contraseña de usuario, el command indica a las 
	 * variables el cambio que se hará en el objeto.
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
	
	/**Recuperar contraseña actual con la cual el usuario accede al sistema. 
	 * @param ninguno.
	 * @return Envia contraseña a correo de usuario, el command indica a las 
	 * variables el cambio que se hará en el objeto.
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
				    serviciocontactosigarep.sendEmail(usuario.getCorreo(), usuario.getClave());
					mensajeAlUsuario.informacionContrasennaRecuperada();
				}
				else mensajeAlUsuario.errorUsuarioEmailNoRegistrado();
		}
	}
		
	/** cargarImagenUsuario
	 * @param @ContextParam(ContextType.TRIGGER_EVENT) UploadEvent event
	 * @return ninguno, el command indica a las variables el cambio que se hará en el objeto..
	 * @throws Dispara excepción si la mediaUsuario es null
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
				if(fotoUsuario.getTamano()>50000){
					mensajeAlUsuario.advertenciaTamannoImagen(50);				
					fotoUsuario = new Archivo();
				}else{imagenUsuario = (AImage) mediaUsuario;}
			} else {
				mensajeAlUsuario.advertenciaFormatoImagenNoSoportado(mediaUsuario);
			}
		} 
	}

	/**Eliminar lógicamente un usuario seleccionado.
	 * @param ninguno.
	 * @return Elimina un usuario seleccionado, el command indica a las 
	 * variables el cambio que se hará en el objeto.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "cedulaPersona","listaUsuario","listaPersona","listaGrupoPertenece","listaGrupoNoPertenece"})
	public void eliminarUsuario() {
		serviciousuario.eliminar(getPersonaSeleccionado().getUsuario().getNombreUsuario());
		serviciopersona.eliminar(getPersonaSeleccionado().getCedulaPersona());
		listaInstanciaMiembro = servicioInstanciaMiembro.buscarPorCedula(getPersonaSeleccionado().getCedulaPersona());
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
	 * @parameters ninguno.
	 * @return Llena los campos asociados al registro de usaurio, 
	 * el command indica a las variables el cambio que se hará en el objeto..
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "nombreUsuario","clave","confirmarcontrasenia","confirmarcorreo","correo","cedulaPersona","apellido","nombre","telefono","listaGrupoPertenece", "listaGrupoNoPertenece", "personaSeleccionado","fotoUsuario","imagenUsuario","listaInstanciaMiembro","tituloinstancia","cargo"})
	public void mostrarSeleccionado() {	
		limpiar();
		listaInstanciaMiembro = new LinkedList<InstanciaMiembro>();
		instanciaMiembroPK = null;
		instanciaMiembro= null;
		
		nombreUsuario = getPersonaSeleccionado().getUsuario().getNombreUsuario();
		clave = getPersonaSeleccionado().getUsuario().getClave();
		confirmarcontrasenia = getPersonaSeleccionado().getUsuario().getClave();
		correo = getPersonaSeleccionado().getCorreo();
		confirmarcorreo = getPersonaSeleccionado().getCorreo();
		cedulaPersona = getPersonaSeleccionado().getCedulaPersona();
		nombre = getPersonaSeleccionado().getNombre();
		apellido = getPersonaSeleccionado().getApellido();
		telefono = getPersonaSeleccionado().getTelefono();
		fotoUsuario = getPersonaSeleccionado().getUsuario().getFoto();
		listaInstanciaMiembro = servicioInstanciaMiembro.buscarPorCedula(getPersonaSeleccionado().getCedulaPersona());
		
		for(int i=0;listaInstanciaMiembro.size()>i;i++){
			if(listaInstanciaMiembro.get(i).getEstatus()==false)
    		instanciaMiembro = listaInstanciaMiembro.remove(i);		
    	}
		
		if(fotoUsuario!=null){
			if (getPersonaSeleccionado().getUsuario().getFoto().getTamano() > 0){
				try {
					imagenUsuario = new AImage(getPersonaSeleccionado().getUsuario().getFoto().getNombreArchivo(),getPersonaSeleccionado().getUsuario().getFoto().getContenidoArchivo());
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
			else{imagenUsuario = null;}
		}else{
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

		listaGrupoPertenece = serviciogrupo.listadoGrupoPerteneceUsuario(getPersonaSeleccionado().getUsuario().getNombreUsuario());
		listaGrupoNoPertenece = serviciogrupo.listadoGrupoNoPerteneceUsuario(getPersonaSeleccionado().getUsuario().getNombreUsuario());
	}
	
	/** Mostrar instancia seleccionada del usuario.
	 * @parameters ninguno.
	 * @return Llena los campos cargo y tituloinstancia, el command indica a las 
	 * variables el cambio que se hará en el objeto, 
	 * el command indica a las variables el cambio que se hará en el objeto..
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "listaUsuario","tituloinstancia","listaInstanciaMiembro","cargo"})
	public void mostrarInstancia() {
		cargo = instanciaMiembro.getCargo();
		tituloinstancia = instanciaMiembro.getInstanciaApelada().getInstanciaApelada();
		instanciaseleccionada = instanciaMiembro.getInstanciaApelada();
	}

	/**Limpiar los campos de texto de la funcionalidad registrar usuario.
	 * @parameters ninguno.
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

	/** Filtro de lista de usuarios
	 * @param cedulaPersonafiltro, nombreCompletofiltro, nombreUsuariofiltro.
	 * @return No devuelve ningun valor.
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({"listaPersona"})
	public void filtros(){
		listaPersona = serviciopersona.buscarPersonaFiltro(cedulaPersonafiltro, nombreCompletofiltro, nombreUsuariofiltro);
	}
	
	/**
	 * Cerrar Ventana
	 * 
	 * @param ventana
	 * @return cierra el .zul asociado al VM
	 * @throws No dispara ninguna excepcion, el command indica a las variables el 
	 * cambio que se hará en el objeto.
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

	/**
	 * Cerrar VentanaCambiarContrasenha
	 * 
	 * @param ventana
	 * @return cierra el .zul asociado al VM
	 * @throws No dispara ninguna excepcion, el command indica a las variables 
	 * el cambio que se hará en el objeto.
	 */
	
	@Command
	@NotifyChange({"confirmarcontrasenia", "nuevaContrasenia" })
	public void cerrarVentanaCambiarContrasenha(@BindingParam("ventana") final Window ventana){
		boolean condicion = false;
		if(!confirmarcontrasenia.equals("") || !nuevaContrasenia.equals(""))
			condicion = true;
		mensajeAlUsuario.confirmacionCerrarVentanaMaestros(ventana,condicion);		
	}
} //fin VMUsuario.