package sigarep.modelos.servicio.maestros;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zul.Messagebox;

import sigarep.modelos.data.maestros.EnlaceInteres;
import sigarep.modelos.repositorio.maestros.IEnlaceInteresDAO;

@Service("servicioei")
public class ServicioEnlaceIntere {
	
	private @Autowired IEnlaceInteresDAO daoei;
	
	
	public List<EnlaceInteres> listadoEnlaceIntere() {
		
		List<EnlaceInteres> listaEnlaceInteres = daoei.findAll();
	    return listaEnlaceInteres;
	}

}


