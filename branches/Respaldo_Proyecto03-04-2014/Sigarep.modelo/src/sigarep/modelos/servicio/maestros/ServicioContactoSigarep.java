package sigarep.modelos.servicio.maestros;

import java.security.GeneralSecurityException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zul.Messagebox;

import com.sun.mail.util.MailSSLSocketFactory;

import sigarep.modelos.data.maestros.ContactoSigarep;
import sigarep.modelos.repositorio.maestros.IContactoSigarepDAO;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase ServicioContactoSigarep
 * 
 * @author BUILDER
 * @version 1.0
 * @since 18/12/2013
 */

@SuppressWarnings("unused")
@Service("serviciocontactosigarep")
public class ServicioContactoSigarep {
	private @Autowired IContactoSigarepDAO iContactoSigarepDAO;
	private final Properties properties = new Properties();
	private Session session;
	
	public void guardar(ContactoSigarep contactoSigarep) {
		if (contactoSigarep.getIdContacto() != null)
			iContactoSigarepDAO.save(contactoSigarep);
		else {
			contactoSigarep.setIdContacto(iContactoSigarepDAO.buscarUltimoID() + 1);
			iContactoSigarepDAO.save(contactoSigarep);
		}
	}

	/**
	 * Retorna una lista con el UNICO registro de contacto.
	 * @param
	 * @return List<ContactoSigarep> contactoSigarep
	 * @throws No dispara ninguna excepción.
	 */
	
	public List<ContactoSigarep> buscarContactoSigarep(){
		return iContactoSigarepDAO.findAll();
	}
	
	private void cargarPropiedadesEnvio() {
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.socketFactory", sf);
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", buscarContactoSigarep().get(0).getServidorSalidaSmtp());
		properties.put("mail.smtp.port", buscarContactoSigarep().get(0).getPuertoSalidaSmtp());
		properties.put("mail.smtp.debug", "true");
		properties.put("mail.smtp.mail.sender", buscarContactoSigarep().get(0).getCorreoContacto());
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(buscarContactoSigarep().get(0).getCorreoContacto(), buscarContactoSigarep().get(0).getClaveCorreo());
					}
				});
	}

	// función para recuperar la contraseña
	public void sendEmail(String correoReceptor, String clave) {
	      cargarPropiedadesEnvio();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties
					.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					correoReceptor));
			message.setSubject("Recuperación de clave de acceso al sistema SIGAREP");
			message.setText("La Recuperación de contraseña se realizó "
					+ "satisfactoriamente."
					+ "\n\n SIRAGEP le informa que su contraseña para acceder a "
					+ "nuestro sistema es: "
					+ clave
					+ "\n\n\t\t Sistema de Información "
					+ "para el Apoyo a la Gestión del Regimen de Repitencia y Permanencia");
			Transport.send(message);
			System.out.println("Enviado");
		} catch (MessagingException me) {
			throw new RuntimeException(me);
		}
	}

	// función para recuperar la contraseña
	public void sendEmailWelcomeToSigarep(String correoReceptor,
			String nombreUsuario, String clave) {
		cargarPropiedadesEnvio();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties
					.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					correoReceptor));
			message.setSubject("Bienvenida al sistema SIGAREP - Envio de login y password de usuario autorizado");
			message.setText("Usted ha sido autorizado para acceder al sistema "
					+ "integrado para el Apoyo a la Gestión del Regimen de Repitencia y Permanencia"
					+ "\n\n ingresando con login: " + nombreUsuario + " y "
					+ "password: " + clave + "\n\n\t\t" + "");
			Transport.send(message);
			System.out.println("Enviado");
		} catch (MessagingException me) {
			throw new RuntimeException(me);
		}
	}

	// Función para contactanos
	public void sendEmailContactanos(String correo, String nombre,
			String telefono, String consulta) {
		cargarPropiedadesEnvio();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties
					.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(buscarContactoSigarep().get(0).getCorreoContacto()));
			message.setSubject("Mensaje o consulta enviado por: " + nombre);
			message.setText(nombre + ", "
					+ "\nNúmero de teléfono: " + telefono
					+ "\nCorreo: " + correo
					+ "\nHa enviado la siguiente consulta: " + "\n\n"
					+ consulta + "\n\n\t\t" + "");
			Transport.send(message);
		} catch (MessagingException me) {
			throw new RuntimeException(me);
		}
	}
	
}
