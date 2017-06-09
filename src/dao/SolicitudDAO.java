package dao;

import java.util.List;

import dto.Solicitud;
import dto.TipoSolicitud;
import exception.MyException;


/**
 * DAO
 * Interfaz que define los metodos que va a proveer
 * la clase Solicitud.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

public interface SolicitudDAO {
	/**
	 * Método que nos permite obtener todos las solicitudes.
	 * 
	 * @return Lista con las solicitudes.
	 * @throws MyException
	 */
	public List<Solicitud> obtenerSolicitudes() throws MyException;
	
	/**
	 * Método que nos permite obtener una unica solicitud.
	 * 
	 * @param id con la cual se realizará la búsqueda.
	 * @return Retorna la solicitud buscada.
	 * @throws MyException
	 */
	public Solicitud obtenerSolicitud(int id) throws MyException;

	/**
	 * Método que nos permite crear una nueva solicitud.
	 * 
	 * @param solicitud
	 *            que se va a crear en el sistema y almacenar en la BD.
	 * @throws MyException
	 */
	public void guardar(Solicitud solicitud) throws MyException;

	/**
	 * Método que nos permite eliminar una solicitud de la base de datos. 
	 * @param solicitud que se va a eliminar.
	 * @throws MyException
	 */
	public void eliminarSolicitud(Solicitud solicitud) throws MyException;

	/**
	 * Método para actualizar una solicitud existente.
	 * @param solicitud que se desea actualizar
	 * @throws MyException
	 */
	public void actualizar(Solicitud solicitud) throws MyException;
	
	/**
	 * Método para filtrar las solicitudes entre quejas, reclamos, sugerencias o preguntas
	 * @param tipo de solicitud
	 * @throws MyException
	 * 
	 */
	public List<Solicitud> filtrarSolicitudes(TipoSolicitud tipoSolicitud) throws MyException;
	
}
