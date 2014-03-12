package sigarep.modelos.servicio.maestros;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sigarep.modelos.data.maestros.ContactoSigarep;
import sigarep.modelos.repositorio.maestros.IContactoSigarepDAO;

/**
 * Clase ServicioContactoSigarep
 * 
 * @author BUILDER
 * @version 1.0
 * @since 18/12/2013
 */

@Service("serviciocontactosigarep")
public class ServicioContactoSigarep {
	private @Autowired
	IContactoSigarepDAO iContactoSigarep;

	/**
	 * Guardar ContactoSigarep
	 * @param ContactoSigarep contactoSigarep
	 * @return Guarda el objeto
	 * @throws No dispara ninguna excepción.
	 */
	public void guardar(ContactoSigarep contactoSigarep) {
		if (contactoSigarep.getIdContacto() != null)
			iContactoSigarep.save(contactoSigarep);
		else {
			contactoSigarep.setIdContacto(iContactoSigarep.buscarUltimoID() + 1);
			iContactoSigarep.save(contactoSigarep);
		}
	}

	/**
	 * Retorna una lista con el UNICO registro de contacto.
	 * @param
	 * @return List<ContactoSigarep> contactoSigarep
	 * @throws No dispara ninguna excepción.
	 */
	public List<ContactoSigarep> buscarContactoSigarep() {
		return iContactoSigarep.findAll();
	}
}
