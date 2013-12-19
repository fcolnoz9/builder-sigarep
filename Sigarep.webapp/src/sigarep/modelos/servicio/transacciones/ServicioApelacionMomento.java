package sigarep.modelos.servicio.transacciones;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import sigarep.modelos.data.maestros.PreguntaBasica;
import sigarep.modelos.data.transacciones.ApelacionMomento;
import sigarep.modelos.data.transacciones.ApelacionMomentoPK;
import sigarep.modelos.repositorio.transacciones.IApelacionMomentoDAO;

// El servicio interactua con la base de datos

@Service("servicioapelacionmomento") //Definiendo la variable servicio
public class ServicioApelacionMomento{
	private @Autowired IApelacionMomentoDAO ape ;

//	public ApelacionMomento buscarApelaciones(ApelacionMomentoPK momento){
//		return ape.findOne(momento);
//	
//	}
	

}

	
