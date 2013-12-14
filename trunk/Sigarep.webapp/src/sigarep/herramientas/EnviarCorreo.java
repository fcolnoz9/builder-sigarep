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

	private final String username = "sistemasiragep@gmail.com"; //Nota: este correo está aun sin existencia.
	private final String password = "equipo1si";

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
		properties.put("mail.smtp.mail.sender", "sistemasiragep@gmail.com");

		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

	}

	//función para recuperar la contraseña
	public void sendEmail(String correoReceptor, String clave) {
		init();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties
					.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					correoReceptor));
			message.setSubject("Olvido su contraseña");
			message.setText("La Recuperación de contraseña se realizó "
					+ "satisfactoriamente."
					+ "\n\n SIRAGEP le informa que su contraseña para acceder a "
					+ "nuestro sistema es: " + clave
					+ "\n\n\t\t Sistema de Información "
					+ "para el Apoyo a la Gestión del Regimen de Repitencia y Permanencia");

			Transport.send(message);
			
			System.out.println("Enviado");

		} catch (MessagingException me) {
			throw new RuntimeException(me);
		}
	}
}
