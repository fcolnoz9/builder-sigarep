package sigarep.viewmodels.seguridad;
import java.util.ArrayList;


import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import sigarep.herramientas.Archivo;
import sigarep.herramientas.MensajesAlUsuario;
import sigarep.herramientas.UtilidadesSigarep;
import sigarep.modelos.data.seguridad.Usuario;
import sigarep.modelos.servicio.seguridad.ServicioUsuario;


/**
 * Utility class for ZK spring security.
 * @author henrichen
 */
@SuppressWarnings("rawtypes")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMUtilidadesDeSeguridad {

	private Archivo fotoUsuario = new Archivo();
	private Media media;
	private String href = "";
	private AImage imagenUsuario;
	private String nombreCompleto;
	private static User usuario;
	private String ruta = UtilidadesSigarep.obtenerDirectorio();
	MensajesAlUsuario mensajes = new MensajesAlUsuario();
	@WireVariable
	ServicioUsuario serviciousuario;

	/**
	 * Retorna el objeto de autenticación actual.
	 */
	
	public static Usuario getUser() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				try {
					Object p = auth.getPrincipal();
					if (p instanceof Usuario)
						return (Usuario) p;
				} catch (RuntimeException e) {
					e.printStackTrace();
					throw e;
				}
			}
			return null;
		} catch (RuntimeException e) {
			Window ventana = (Window) Executions.createComponents(
					"/accesoDenegado.zul", null, null);
			ventana.doModal();
			throw e;
		}
	}

	/**
	 * Devuelve true si el principal autenticado no concede NINGUNA 
	 * de las funciones especificadas en las autoridades.
	 * 
	 * @param autoridades
	 *	Una lista separada por comas de las funciones que el usuario debe tener concedido NINGUNO
	 * @return true si el principal autenticado no posee autoridades de NINGUNA de las funciones especificadas.
	 */
	public static boolean esAutorizado(String autoridades) {
		if (null == autoridades || "".equals(autoridades)) {
			return false;
		}
		final Collection<? extends GrantedAuthority> autoridadesConcedidas = getPrincipalAutoridades();

		final Set autoridadesConcedidasCopia = conservarTodos(autoridadesConcedidas,convertirAutoridades(autoridades));
		return autoridadesConcedidasCopia.isEmpty();
	}

	/**
	 * Devuelve true si se concede el principal autenticado TODOS los roles 
	 * que se especifican en las autoridades.
	 * 
	 * @param autoridades
	 *	Una lista separada por comas de las funciones que al usuario se le debe haber otorgado, todas separados.
	 * @return true si al principal autenticado se le concedieron las autoridades 
	 * de TODAS las funciones especificadas.
	 */

	public User getUsuario() {
		usuario = (User) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return usuario;
	}

	public Archivo getFotoUsuario() {
		return fotoUsuario;
	}

	public void setFotoUsuario(Archivo fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}

	public Media getMedia() {
		return media;
	}

	public void setMedia(Media media) {
		this.media = media;
	}

	public AImage getImagenUsuario() {
		return imagenUsuario;
	}

	public void setImagenUsuario(AImage imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * Inicialización
	 * 
	 * @param init
	 * @return Carga de Variables y metodos inicializados
	 * @throws No dispara ninguna excepcion.
	 */
		
	@Init
	@NotifyChange("nombreCompleto")
	public void init() {
		Usuario usuarioAux = new Usuario();
		usuarioAux = serviciousuario.encontrarUsuario(getUsuario().getUsername());
		usuarioAux.setUltimoAcceso(new Date());
		cargarFotoImagen();
		serviciousuario.guardarUsuario(usuarioAux);
		nombreCompleto = usuarioAux.getNombreCompleto();
	}
		
	@Command
	@GlobalCommand
	@NotifyChange({ "fotoUsuario", "imagenUsuario","nombreCompleto"})
	public void cargarFotoImagen() {
		fotoUsuario = new Archivo();
		imagenUsuario = null;
		Usuario usuario = new Usuario();
		usuario = serviciousuario.encontrarUsuario(getUsuario().getUsername());
		try {
			nombreCompleto = usuario.getNombreCompleto();
			if (usuario.getFoto().getNombreArchivo().equals("usuario.png")) {
				imagenUsuario = new AImage(ruta + "/Sigarep.webapp/WebContent/imagenes/iconos/usuario.png");
			} else imagenUsuario = usuario.getFoto().getAImage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
		
	@Command
	public void editarPerfil() {
		try {
			// get an instance of the borderlayout defined in the zul-file
			Borderlayout bl = (Borderlayout) Path
					.getComponent("/mainBorderLayout");
			// get an instance of the searched CENTER layout area
			Center center = bl.getCenter();
			// clear the center child comps
			center.getChildren().clear();
			// call the zul-file and put it in the center layout area
			Executions.createComponents("/WEB-INF/sigarep/vistas/administracionSistema/EditarPerfilUsuario.zul",center, null);
		} catch (Exception e) {
			Messagebox.show(e.toString());
		}
	}
	
	@Command
	public void cerrarSesion() {
		mensajes.confirmacionCerrarSesion();
	}	
       
	public static boolean sonTodasLasAutoridadesConcedidas(String autoridades) {
		if (null == autoridades || "".equals(autoridades)) {
			return false;
		}
		final Collection<? extends GrantedAuthority> autoridadesConcedidas = getPrincipalAutoridades();
		boolean sonTodasLasAutoridadesConcedidas = autoridadesConcedidas.containsAll(convertirAutoridades(autoridades));
		return sonTodasLasAutoridadesConcedidas;
	}
        
	/**
	 * Devuelve true si se concede al principal autenticado CUALQUIERA de 
	 * las funciones especificadas en las autoridades.
	 * 
	 * @param autoridades
	 * Una lista separada por comas de los roles que debe 
	 *         haber sido otorgados al usuario CUALQUIER separado.
	 * @return true si al principal autenticado se le concede autoridades
	 *  	   de TODAS las funciones especificadas.
	 */
	public static boolean tieneConcedidaAlgunaAutoridad(String autoridades) {
		if (null == autoridades || "".equals(autoridades)) {
			return false;
		}
		final Collection<? extends GrantedAuthority> autoridadesConcedidas = getPrincipalAutoridades();
		final Set autoridadesConcedidasCopia = conservarTodos(autoridadesConcedidas,convertirAutoridades(autoridades));
		return !autoridadesConcedidasCopia.isEmpty();
	}
        
	public static List<String> roles() {
		List<String> roles = new ArrayList<String>();
		Authentication usuarioActual = SecurityContextHolder.getContext().getAuthentication();
		Iterator<? extends GrantedAuthority> iterador = usuarioActual.getAuthorities().iterator();
		while (iterador.hasNext()) {
			roles.add(iterador.next().getAuthority());
		}
		return roles;
	}
        
	private static Collection<? extends GrantedAuthority> getPrincipalAutoridades() {
		Authentication usuarioActual = SecurityContextHolder.getContext().getAuthentication();
		if (null == usuarioActual) {
			return Collections.emptyList();
		}
		if ((null == usuarioActual.getAuthorities())
				|| (usuarioActual.getAuthorities().isEmpty())) {
			return Collections.emptyList();
		}
		Collection<? extends GrantedAuthority> autoridadesConcedidas = usuarioActual.getAuthorities();
		return autoridadesConcedidas;
	}

    private static Collection<GrantedAuthority> convertirAutoridades(String autorizacionesString) {
        final ArrayList<GrantedAuthority> autoridadesRequeridas = new ArrayList<GrantedAuthority>();
        final String[] roles = autorizacionesString.split(",");

        for (int i = 0; i < roles.length; i++) {
            String rol = roles[i].trim();
            autoridadesRequeridas.add(new SimpleGrantedAuthority(rol));
        }
        return autoridadesRequeridas;
    }

    /**
     * Encuentra las autoridades comunes entre la corriente de autenticación 
     * {@ link GrantedAuthority} y las que se han especificado en ifAny de la etiqueta, 
     * o IfNot ifAllGranted atributos. <p> Tenemos que iterar manualmente sobre ambas colecciones, 
     * porque las autoridades concedidas podrían no aplicar {@ linkObject # equals (Object)} y 
     * {@ link Object # hashCode ()} de la misma manera que {@ link SimpleGrantedAuthority}, lo 
     * que invalida {@ link Collection # retainAll (java.util.Collection)} resultados.</p>
     * <p>
     * <strong>CAVEAT</strong>:  Este metodo <strong>no funcionará</strong> si las autoridades otorgadas
     * devuelven un <code>null</code> string como el valor de retorno de {@link
     * org.springframework.security.GrantedAuthority#getAuthority()}.
     * </p>
     * <p>Reportado por rawdave, el Vie 04 de febrero 2005 14:11 en el foro de Spring Security</p>
     *
     * @param concedido las autorizaciones otorgadas por la autenticación. Puede ser cualquier implementación de {@link
     *        GrantedAuthority} que <strong>no</strong> devuelve <code>null</code> desde {@link
     *        org.springframework.security.GrantedAuthority#getAuthority()}.
     * @param requiere un {@link Set} de {@link SimpleGrantedAuthority}s que se han construido utilizando ifAny, ifAll o
     *        ifNotGranted.
     *
     * @return Un conjunto que contiene sólo las autoridades comunes entre <var>granted</var> y <var>required</var>.
     */
    private static Set conservarTodos(final Collection<? extends GrantedAuthority> autoridadesConcedidas, final Collection<? extends GrantedAuthority> autoridadesRequeridas) {
        Set<String> rolesConcedidos = deRoles(autoridadesConcedidas);
        Set<String> rolesRequeridos = deRoles(autoridadesRequeridas);
        rolesConcedidos.retainAll(rolesRequeridos);
        return aLasAutoridades(rolesConcedidos, autoridadesConcedidas);
    }
    
    /**
     * 
     * @param autoridades
     * @return
     */
	private static Set<String> deRoles(Collection<? extends GrantedAuthority> autoridades) {
		final Set<String> autoridadesConcedidas = new HashSet<String>();
		for (GrantedAuthority au : autoridades) {

			if (null == au.getAuthority()) {
				throw new IllegalArgumentException(
						"No se puede procesar objetos GrantedAuthority que vuelven null de getAuthority () - intentando procesar "
								+ au.toString());
			}

			autoridadesConcedidas.add(au.getAuthority());
		}
		return autoridadesConcedidas;
	}
        
    /**
     * 
     * @param rolesConcedidos
     * @param autoridadesConcedidas
     * @return autoridadObjetivo
     */
	private static Set<GrantedAuthority> aLasAutoridades(Set<String> rolesConcedidos,Collection<? extends GrantedAuthority> autoridadesConcedidas) {
		Set<GrantedAuthority> autoridadObjetivo = new HashSet<GrantedAuthority>();

		for (String rol : rolesConcedidos) {
			for (GrantedAuthority autoridad : autoridadesConcedidas) {

				if (autoridad.getAuthority().equals(rol)) {
					autoridadObjetivo.add(autoridad);
					break;
				}
			}
		}
		return autoridadObjetivo;
	}

	/**
	 * prueba que verifica si el usuario actual contiene todas las autoridades que figuran
	 * 
	 * @param autoridades de los roles que serán verificados
	 */
	public static void afirmarTodos(String... autoridades) {
		if (null == autoridades || autoridades.length == 0) {
			return;
		}

		final ArrayList<GrantedAuthority> autoridadesRequeridas = new ArrayList<GrantedAuthority>();
		for (String auth : autoridades) {
			autoridadesRequeridas.add(new SimpleGrantedAuthority(auth));
		}

		final Collection<? extends GrantedAuthority> autoridadesConcedidas = getPrincipalAutoridades();
		if (!autoridadesConcedidas.containsAll(autoridadesRequeridas)) {
			Authentication usuarioActual = SecurityContextHolder.getContext()
					.getAuthentication();
			throw new AccessDeniedException(
					"El usuario actual no tiene suficiente autoridad. autenticación: "
						+ (usuarioActual == null ? "" : usuarioActual.getName()));
		}
	}
}
