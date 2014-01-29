package sigarep.viewmodels.seguridad;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Row;


import sigarep.herramientas.EnviarCorreo;
import sigarep.herramientas.mensajes;
import sigarep.modelos.data.maestros.InstanciaApelada;
import sigarep.modelos.data.maestros.Persona;

import sigarep.modelos.data.seguridad.Grupo;
import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.data.transacciones.InstanciaMiembro;
import sigarep.modelos.data.transacciones.InstanciaMiembroPK;
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
	
	private List<InstanciaApelada> listaInstancia;	
	private InstanciaApelada instanciaseleccionada;
	private String tituloinstancia = "";
	private String cargo ="";
	
	private InstanciaMiembro instanciaMiembro = new InstanciaMiembro();
	private InstanciaMiembroPK instanciaMiembroPK = new InstanciaMiembroPK();
	
	private String cedulaPersona="";
	private String nombre="";
	private String apellido="";
	private String telefono="";
	
	private List<Persona> listaPersona;
	private Persona personaSeleccionado = new Persona();
	private String nombreUsuario;
	private String correo;
	private String clave;
	private String confirmarcontrasenia;
	private String nuevaContrasenia;
	private String nombreCompleto;
	private String estado;
	private ListModelList<Grupo> modeloGrupo;
	List<Grupo> listGrupo;
	
	@WireVariable
	private String correoLogin;

	mensajes msjs = new mensajes(); //para llamar a los diferentes mensajes de dialogo
	
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
	private ServicioGrupo sg;
	@WireVariable
	private ServicioUsuario su;
	

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
		listaUsuario = su.listadoUsuario();
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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

	@Command
	@NotifyChange({ "nombreUsuario","nombreCompleto", "clave","confirmarcontrasenia", "correo",
			"listaUsuario","cedulaPersona","nombre","apellido","telefono","listaPersona" })
	public void guardarUsuario(@BindingParam("gruposDelUsuario") List<Listitem> gruposDelUsuario, @BindingParam("rowContrasenna") Row rowContrasenna) {
		boolean existeUsuario = false;
		Usuario usuario = new Usuario();
		if (nombreUsuario == null || correo == null || cedulaPersona == null || nombre == null || apellido == null
				|| telefono == null) {
			msjs.advertenciaLlenarCampos();
		}
		else if(gruposDelUsuario.size()==0){
			Messagebox.show("Debe seleccionar al menos un grupo",
					"Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		}
		else {
			Usuario usuarioAux = su.encontrarUsuario(nombreUsuario);
			if(usuarioAux==null){
				usuario.setNombreUsuario(nombreUsuario);
				usuario.setClave(clave);
			}
			else
			{
				usuario.setNombreUsuario(usuarioAux.getNombreUsuario());
				usuario.setClave(usuarioAux.getClave());
				existeUsuario = true;
//				System.out.println("tamanno: "+usuario.getUsuariosGrupos().size());
				if(sg.listadoGrupoPerteneceUsuario(nombreUsuario).size()>0){
					for(int j=0;j<sg.listadoGrupoPerteneceUsuario(nombreUsuario).size();j++){
						UsuarioGrupo usuarioGrupoABorrar = usuarioAux.getUsuariosGrupos().get(j);
						usuario.removeUsuarioGrupo(usuarioGrupoABorrar);
//						System.out.println(usuarioAux.getUsuariosGrupos().get(j).getGrupo().getNombre());
					}
				}
			}
			usuario.setCorreo(correo);
			nombreCompleto = nombre + " " + apellido;
			usuario.setNombreCompleto(nombreCompleto); // esto se lo debería traer de la vista, ojo JM.
			usuario.setEstatus(true);
			
			for(Listitem miGrupo :gruposDelUsuario){
				Grupo grupo = new Grupo();
				String nombreGrupo = miGrupo.getLabel();
				grupo = sg.buscarGrupoNombre(nombreGrupo);
				
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
				su.guardarUsuario(usuario);
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
			
			if (tituloinstancia.equals("")) {
				System.out.println("instancia vacia");
			} else {
				instanciaMiembroPK.setCedulaPersona(cedulaPersona);
				instanciaMiembroPK
						.setIdInstanciaApelada(getInstanciaseleccionada()
								.getIdInstanciaApelada());
				instanciaMiembro.setId(instanciaMiembroPK);
				instanciaMiembro.setCargo(cargo);
				instanciaMiembro.setEstatus(true);
				instanciaMiembro.setFechaEntrada(new Date());
				instanciaMiembro
						.setInstanciaApelada(getInstanciaseleccionada());
				instanciaMiembro.setPersona(persona);

				try {
					servicioInstanciaMiembro.guardar(instanciaMiembro);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			
			msjs.informacionRegistroCorrecto();
			if(existeUsuario==false){
				EnviarCorreo enviar = new EnviarCorreo();
				enviar.sendEmailWelcomeToSigarep(correo,nombreUsuario,clave);
				Messagebox.show("Te hemos enviado un email con tu nombre de usuario y contraseña.","Información", Messagebox.OK, Messagebox.INFORMATION);	
			}
			limpiar(rowContrasenna);
		}
	}
	
	@Init
	public void init() {
		// initialization code
		buscarUsuario();
		buscarListadoUsuario();
		buscarListadoGrupos();
	}

	// Metodo que busca una noticia partiendo por su titulo
	@Command
	@NotifyChange({ "listaUsuario","listaPersona","listaInstancia" })
	public void buscarUsuario() {
		listaUsuario = su.buscarUsuario(nombreUsuario);
		listaPersona = serviciopersona.buscarper(cedulaPersona);
		listaInstancia = servicioInstanciaApelada.buscarTodas();
	}
	
	@NotifyChange({ "listaGrupoNoPertenece" })
	public void buscarListadoGrupos() {
		List<Grupo> listadoGruposActivos = sg.listadoGrupo();
		listaGrupoNoPertenece = listadoGruposActivos;
	}

	// Metodo que limpia todos los campos de la pantalla
	@Command
	@NotifyChange({ "nombreUsuario", "contrasenia", "confirmarcontrasenia","correo","listaPersona","listaInstancia","listaUsuario","cedulaPersona","nombre","apellido","telefono", "listaGrupoPertenece","listaGrupoNoPertenece"})
	public void limpiar(@BindingParam("rowContrasenna") Row rowContrasenna) {
		nombreUsuario = "";
		clave = "";
		confirmarcontrasenia = "";
		correo = "";
		cedulaPersona = "";
		nombre = "";
		apellido = "";
		telefono = "";
		buscarUsuario();
		listaGrupoPertenece.clear();
		buscarListadoGrupos();
		rowContrasenna.setVisible(true);
	}
	
	@Command
	@NotifyChange({ "nombreUsuario", "contrasenia", "confirmarcontrasenia","nuevaContrasenia","listaUsuario"})
	public void cancelarCambiarContrasenia() {
		nombreUsuario = "";
		clave = "";
		confirmarcontrasenia = "";
		nuevaContrasenia = "";
	}	

	// Metodo que elimina una actividad tomando en cuenta el idActividad
	@Command
	@NotifyChange({ "cedulaPersona","listaUsuario","listaPersona","listaGrupoPertenece","listaGrupoNoPertenece"})
	public void eliminarUsuario(@BindingParam("rowContrasenna") Row rowContrasenna) {
		su.eliminar(getPersonaSeleccionado().getNombreUsuario().getNombreUsuario());
		serviciopersona.eliminar(cedulaPersona);
		msjs.informacionEliminarCorrecto();
		limpiar(rowContrasenna);
	}

	// permite tomar los datos del objeto usaurioseleccionado
	@Command
	@NotifyChange({ "nombreUsuario", "fechaCreacion","correo","cedulaPersona","apellido","nombre","telefono","listaGrupoPertenece", "listaGrupoNoPertenece", "personaSeleccionado"})
	public void mostrarSeleccionado(@BindingParam("rowContrasenna") Row rowContrasenna) {	
		nombreUsuario = getPersonaSeleccionado().getNombreUsuario().getNombreUsuario();
		correo = getPersonaSeleccionado().getCorreo();
		cedulaPersona = getPersonaSeleccionado().getCedulaPersona();
		nombre = getPersonaSeleccionado().getNombre();
		apellido = getPersonaSeleccionado().getApellido();
		telefono = getPersonaSeleccionado().getTelefono();

		listaGrupoPertenece = sg.listadoGrupoPerteneceUsuario(getPersonaSeleccionado().getNombreUsuario().getNombreUsuario());
		listaGrupoNoPertenece = sg.listadoGrupoNoPerteneceUsuario(getPersonaSeleccionado().getNombreUsuario().getNombreUsuario());
		rowContrasenna.setVisible(false);
	}
	
	@Command
	@NotifyChange({"listaGrupoNoPertenece","listaGrupoPertenece"})
	public void quitarGrupo(@BindingParam("itemGrupoPertenece") Listitem itemGrupoPertenece) {
		Grupo grupoAux1 = sg.buscarGrupoNombre(itemGrupoPertenece.getLabel());
		listaGrupoNoPertenece.add(grupoAux1);
		listaGrupoPertenece.remove(itemGrupoPertenece.getIndex());
	}
	
	@Command
	@NotifyChange({"listaGrupoPertenece","listaGrupoNoPertenece"})
	public void agregarGrupo(@BindingParam("itemGrupoNoPertenece") Listitem itemGrupoNoPertenece) {
		Grupo grupoAux2 = sg.buscarGrupoNombre(itemGrupoNoPertenece.getLabel());
		listaGrupoPertenece.add(grupoAux2);
		listaGrupoNoPertenece.remove(itemGrupoNoPertenece.getIndex());		
	}
	
	
	@Command
	@NotifyChange({ "nombreUsuario","clave","confirmarcontrasenia", "nuevaContrasenia" })
	public void cambiarContrasenia() {
	    if(nombreUsuario == null || clave==null || confirmarcontrasenia==null || nuevaContrasenia == null)
	    	msjs.advertenciaLlenarCampos();
	    else{
	    	
	    	if(su.cambiarContrasena(nombreUsuario, clave, nuevaContrasenia, confirmarcontrasenia)==true)
	    		Messagebox.show("Se ha actualizado su contraseña", "Información",Messagebox.OK, Messagebox.EXCLAMATION);
	    }
	}
	
	@Command
	@NotifyChange({ "correoLogin" })
	public void recuperarContrasenna() {
		Usuario usuario = new Usuario();
		usuario.setNombreUsuario("-1");
		if (correoLogin=="")
			msjs.advertenciaLlenarCampos();
		else {
			List<Usuario> listaUsuarios = su.listadoUsuario();
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
					Messagebox.show("Te hemos enviado un email con tu contraseña.","Información", Messagebox.OK, Messagebox.INFORMATION);
				}
				else
					Messagebox.show("Usuario o correo e-mail no registrados","Información", Messagebox.OK, Messagebox.INFORMATION);
		}
	}
	
	@Command
	@NotifyChange({ "listaUsuario","tituloinstancia" })
	public void pasepase() {
		System.out.println(tituloinstancia);
	}
}