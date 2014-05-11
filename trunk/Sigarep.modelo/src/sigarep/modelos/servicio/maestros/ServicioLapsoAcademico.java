package sigarep.modelos.servicio.maestros;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.repositorio.maestros.ILapsoAcademicoDAO;

/**
 * Clase  ServicioLapsoAcademico (Servicio para la
 * Clase LapsoAcademico)
 * 
 * @author Equipo Builder
 * @version 1.0
 * @since 18/12/2013
 * @last 10/05/2014
 */
@Service("serviciolapsoacademico")
public class ServicioLapsoAcademico {
	private @Autowired
	ILapsoAcademicoDAO iLapsoAcademico;

	/**
	 * Guardar lapso académico
	 * 
	 * @return objeto guardado
	 * @param el objeto lapso Academico
	 * @throws No dispara ninguna excepción.
	 */
	public void guardarLapso(LapsoAcademico lapsoA) {
		iLapsoAcademico.save(lapsoA);
	}

	/**
	 * Buscar lapso académico activo
	 * 
	 * @return el lapso académico activo
	 * @param vacio
	 * @throws No dispara ninguna excepción.
	 */
	public LapsoAcademico buscarLapsoActivo() {
		return iLapsoAcademico.findByEstatusTrue();
	}

	/**
	 * Lista de lapsos académico inactivos
	 * 
	 * @return Lista de lapsos académicos inactivos
	 * @param vacio
	 * @throws No dispara ninguna excepción.
	 */
	public List<LapsoAcademico> listadoLapsoAcademicoInactivos() {
		List<LapsoAcademico> LapsoAcademicoLista = iLapsoAcademico
				.findByEstatusFalse();
		return LapsoAcademicoLista;
	}

	/**
	 * Lista de lapso académico
	 * 
	 * @return Lista de todos los lapsos académicos activas e inactivas
	 * @param vacio
	 * @throws No dispara ninguna excepción.
	 */
	public List<LapsoAcademico> buscarTodosLosLapsos() {
		return iLapsoAcademico.findAll();
	}

	/**
	 * Buscar un lapso académico
	 * 
	 * @return lapso académico buscado
	 * @param String codigolapso
	 * @throws No
	 *             dispara ninguna excepcion.
	 */
	public LapsoAcademico buscarUnLapsoAcademico(String codigoLapso) {
		return iLapsoAcademico.findOne(codigoLapso);
	}

	/**
	 * Buscar un lapso académico por codigo lapso
	 * 
	 * @return Busca un lapso académico por código lapso
	 * @param String codigolapso
	 * @throws No dispara ninguna excepcion.
	 */
	public List<LapsoAcademico> filtrarLapsoAcademico(String codigoLapso) {
		List<LapsoAcademico> result = new LinkedList<LapsoAcademico>();
		if (codigoLapso == null || "".equals(codigoLapso)) {
			result = iLapsoAcademico.findAll();
		} else {
			for (LapsoAcademico lapso : iLapsoAcademico.findAll()) {
				if (lapso.getCodigoLapso().toLowerCase()
						.contains(codigoLapso.toLowerCase())) {
					result.add(lapso);
				}
			}
		}
		return result;
	}
}