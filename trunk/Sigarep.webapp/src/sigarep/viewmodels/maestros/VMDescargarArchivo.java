package sigarep.viewmodels.maestros;

import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import sigarep.modelos.data.maestros.Reglamento;
import sigarep.modelos.servicio.maestros.ServicioReglamento;

/**
 * Clase VMDescargarArchivo : Clase ViewModels 
relacionada con el Maestro Reglamento. 
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 19/12/2013
 * @last 09/05/2014
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMDescargarArchivo {
	// -----------------Servicios----------------------------
	@WireVariable
	private ServicioReglamento servicioreglamento;
	// -----------------Variables Lista----------------------
	private List<Reglamento> lista;

	// Métodos Set y Get

	public List<Reglamento> getLista() {
		return lista;
	}

	public void setLista(List<Reglamento> lista) {
		this.lista = lista;
	}
	// Fin Métodos Set y Get


	/**
	 * Inicialización
	 * Init. Código de inicialización.
	 * @param categoria
	 * @return Objetos inicializados.
	 * @throws No dispara ninguna excepción.
	 */
	@Init
	public void init(@ExecutionArgParam("categoria") String categoria) {
		// initialization code
		if (categoria.equalsIgnoreCase("REGLAMENTO"))
			buscarReglamentos();
		else
			buscarFormato();
	}

	/**
	 * buscarReglamento 
	 * 
	 * @param Ninguno
	 * @return Busca todos los registros (Objeto Reglamentos).
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "lista" })
	public void buscarReglamentos() {
		lista = servicioreglamento.buscarReglamentoPortal();
	}

	/**
	 * descargarArchivo.
	 * 
	 * @param ContextType.COMPONENT) Component componente
	 * @return Ninguno
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	public void descargarArchivo(
			@ContextParam(ContextType.COMPONENT) Component componente) {
		int idDocumento = Integer.parseInt(componente.getAttribute(
				"idReglamento").toString());
		for (int i = 0; i < lista.size(); i++) {
			if (idDocumento == lista.get(i).getIdDocumento())
				Filedownload.save(lista.get(i).getDocumento()
						.getContenidoDocumento(), lista.get(i).getDocumento()
						.getTipoDocumento(), lista.get(i).getDocumento()
						.getNombreDocumento());
		}
	}

	/**
	 * buscarFormato.
	 * 
	 * @param Ninguno.
	 * @return Ninguno
	 * @throws No dispara ninguna excepción.
	 */
	@Command
	@NotifyChange({ "lista" })
	public void buscarFormato() {
		lista = servicioreglamento.buscarFormatoPortal();
	}

}