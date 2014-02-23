package sigarep.herramientas;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class EnviarCorreo {

	private final Properties properties = new Properties();

	private final String username = "jorgechaviel@gmail.com"; // Nota: este
																// correo emisor
																// está aun sin
																// existencia.
	private final String password = "87997872";

	private Session session;

	private void init() {
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
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.debug", "true");
		properties.put("mail.smtp.mail.sender", "jorgechaviel@gmail.com");
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
	}

	// función para recuperar la contraseña
	public void sendEmail(String correoReceptor, String clave) {
		init();
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
		init();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties
					.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					correoReceptor));
			message.setSubject("Bienvenida al sistema SIGAREP - Envio de login y password de usuario autorizado");
			message.setText("Usted ha sido autorizado para acceder al sistema "
					+ "integrado para el Apoyo a la Gestión del Regimen de Repitencia y Permanencia"
					+ "\n\n ingresando con login: " + nombreUsuario + "y "
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
		init();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties
					.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"javier.kllao@gmail.com"));
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
