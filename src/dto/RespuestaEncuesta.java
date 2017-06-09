package dto;

/**
 * DTO Clase para manejar la tabla RespuestaSolicitud de la Base de Datos
 * atencionSolicitudes.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

public class RespuestaEncuesta {
	private int id;
	private int satisfaccion;
	private Cliente cliente;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSatisfaccion() {
		return satisfaccion;
	}

	public void setSatisfaccion(int satisfaccion) {
		this.satisfaccion = satisfaccion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}