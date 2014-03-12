package sigarep.herramientas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.zkoss.image.AImage;

@Embeddable
public class Documento {

	/**
	 * 
	 */
	@Column(name = "contenido_documento", nullable=true)
	private byte[] contenidoDocumento = new byte[] {};

	@Column(name = "nombre_documento", nullable=true)
	private String nombreDocumento = "";

	@Column(name = "tipo_documento", nullable=true)
	private String tipoDocumento = "";

	@Column(name = "tamano_documento", nullable=true)
	private Long tamanoDocumento = 0l;

	public Documento() {
	}

	public Documento(File doc) {
		try {
			byte[] bytes = Documento.toByteArray(doc);
			this.contenidoDocumento = bytes;
			this.nombreDocumento = doc.getName();
		} catch (Exception ex) {
			// Seguramente un Error de Entrada y Salida
		}
	}

	
	public byte[] getContenidoDocumento() {
		return contenidoDocumento;
	}

	public void setContenidoDocumento(byte[] contenido) {
		if (contenido != null) {
			this.setTamanoDocumento(new Long(contenido.length));
		} else {
			contenido = new byte[] {};
		}
		this.contenidoDocumento = contenido;
	}

	public String getNombreDocumento() {
		return nombreDocumento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setNombreDocumento(String nombreArchivo) {
		this.nombreDocumento = nombreArchivo;
	}

	public void setTipoDocumento(String tipo) {
		this.tipoDocumento = tipo;
	}


	@Transient
	public InputStream toInputStream() {
		ByteArrayInputStream input;

		input = new ByteArrayInputStream(contenidoDocumento);

		return input;
	}

	public static byte[] toByteArray(InputStream is) throws SQLException,
			IOException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buf = new byte[4000];
		try {
			for (;;) {
				int dataSize = is.read(buf);
				if (dataSize == -1) {
					break;
				}
				baos.write(buf, 0, dataSize);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
				}
			}
		}
		return baos.toByteArray();
	}

	public static byte[] toByteArray(File archivo) throws IOException {

		FileInputStream fileInput = new FileInputStream(archivo);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] buf = new byte[4000];
		InputStream is = fileInput;
		try {
			for (;;) {
				int dataSize = is.read(buf);
				if (dataSize == -1) {
					break;
				}
				baos.write(buf, 0, dataSize);
			}
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
				}
			}
		}

		return baos.toByteArray();
	}

	public Long getTamanoDocumento() {
		return tamanoDocumento;
	}

	public void setTamanoDocumento(Long tamano) {
		this.tamanoDocumento = tamano;
	}
	
}
