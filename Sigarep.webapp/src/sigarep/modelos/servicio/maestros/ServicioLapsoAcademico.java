package sigarep.modelos.servicio.maestros;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;


import sigarep.modelos.data.maestros.LapsoAcademico;
import sigarep.modelos.repositorio.maestros.ILapsoAcademicoDAO;

@Service("serviciolapsoacademico") //Definiendo la variable servicio
public class ServicioLapsoAcademico{
	private @Autowired ILapsoAcademicoDAO iLapsoAcademico ;

	//metodo que permite Guardar
	public void guardarLapso(LapsoAcademico lapsoA) {
		iLapsoAcademico.save(lapsoA);
	}
	//metodo que permite eliminar
	public void eliminarLapso(String codigoLapso){
		LapsoAcademico  lapsoacademico = iLapsoAcademico.findOne(codigoLapso);
		lapsoacademico.setEstatus(false);
		iLapsoAcademico.save(lapsoacademico);
		}
	public List<LapsoAcademico> buscarLapso(String codigoLapso){
			return iLapsoAcademico.buscarActivoLapso();
		}
	// metodo del listado actualizado de los lapsos	
	public List<LapsoAcademico> listadoLapsoAcademico() {
		List<LapsoAcademico> LapsoAcademicoLista=iLapsoAcademico.buscarActivoLapso();
	    return LapsoAcademicoLista ;
	}
	public LapsoAcademico buscarUnLapsoAcademico(String codigoLapso){
		return iLapsoAcademico.findOne(codigoLapso);
	}
	//metodo para buscar el lapso academico
	public List<LapsoAcademico> buscarLapsoAcademico(String codigoLapso){
		List<LapsoAcademico> result = new LinkedList<LapsoAcademico>();
		if (codigoLapso==null || "".equals(codigoLapso)){
			result = listadoLapsoAcademico();
		}else{
			for (LapsoAcademico lapso: listadoLapsoAcademico()){
				if (lapso.getCodigoLapso().toLowerCase().contains(codigoLapso.toLowerCase()))
				{
					result.add(lapso);
				}
			}
		}
		return result;

	}
}
