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
public class Archivo {

	/**
	 * 
	 */
	@Column(name = "contenidoarchivo", nullable=true)
	private byte[] contenido = new byte[] {};

	@Column(name = "nombrearchivo", nullable=true)
	private String nombreArchivo = "";

	@Column(name = "tipoarchivo", nullable=true)
	private String tipo = "";

	@Column(name = "tamanoarchivo", nullable=true)
	private Long tamano = 0l;

	public Archivo() {
	}

	public Archivo(File archivo) {
		try {
			byte[] bytes = Archivo.toByteArray(archivo);
			this.contenido = bytes;
			this.nombreArchivo = archivo.getName();
		} catch (Exception ex) {
			// Seguramente un Error de Entrada y Salida
		}
	}

	
	public byte[] getContenido() {
		return contenido;
	}

	public void setContenido(byte[] contenido) {
		if (contenido != null) {
			this.setTamano(new Long(contenido.length));
		} else {
			contenido = new byte[] {};
		}
		this.contenido = contenido;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	@Transient
	public InputStream toInputStream() {
		ByteArrayInputStream input;

		input = new ByteArrayInputStream(contenido);

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

	public Long getTamano() {
		return tamano;
	}

	public void setTamano(Long tamano) {
		this.tamano = tamano;
	}

	public AImage getAImage() throws IOException{
		if (tamano > 0){
			try {
				return new AImage(nombreArchivo, contenido);
			} catch (IOException e) {
				return null;
			}
		}
		else
			return null;
		
	}
	
	public void setAImage(AImage image){	
	}
	
}
