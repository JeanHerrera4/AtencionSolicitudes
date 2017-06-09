package bl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ClienteDAO;
import dao.EmpleadoDAO;
import dao.RespuestaEncuestaDAO;
import dao.SolicitudDAO;
import dao.SucursalDAO;
import dao.TipoSolicitudDAO;
import dao.UsuarioDAO;
import dto.Cliente;
import dto.Empleado;
import dto.RespuestaEncuesta;
import dto.Solicitud;
import dto.Sucursal;
import dto.TipoSolicitud;
import exception.MyException;
import validation.Validaciones;



/**
 * Clase encargada de la logica de negocio para la clase Solicitud. Clase
 * transaccional con la BD.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

public class SolicitudBL {
	// Representan los Bean en el archivo de configuración, para poder realizar
	// la inyección de dependencia
	private SolicitudDAO solicitudDAO;
	private UsuarioDAO usuarioDAO;
	private TipoSolicitudDAO tipoSolicitudDAO;
	private ClienteDAO clienteDAO;
	private EmpleadoDAO empleadoDAO;
	private SucursalDAO sucursalDAO;
	private RespuestaEncuestaDAO respuestaEncuestaDAO;

	public RespuestaEncuestaDAO getRespuestaEncuestaDAO() {
		return respuestaEncuestaDAO;
	}

	public void setRespuestaEncuestaDAO(RespuestaEncuestaDAO respuestaEncuestaDAO) {
		this.respuestaEncuestaDAO = respuestaEncuestaDAO;
	}

	/**
	 * Metodo para guardar la solicitud realizada por el Cliente. Los campos
	 * necesarios para realizar la solicitud son los ingresados como parametros.
	 * 
	 *
	private String complejidad;
	private Date fechaRespuesta;
	private String respuestaSolicitud;
	private Sucursal sucursal;
	private Empleado responsable;
	 * 
	 * Los campos fechaRespuesta y respuestaSolicitud, se dejan vacíos hasta que se
	 * responda la solicitud
	 * 
	 * @param descripcion
	 *            campo de texto, contiene el motivo de la solicitud.
	 *  @param complejidad
	 *            campo de texto, contiene la complejidad de la solicitud.           
	 * @param tiposolicitud
	 *            campo que contiene el tipo de solicitud.
	 * @param cliente
	 *            user del cliente.
	 * @param idSucursal
	 *            identificador de la sucursal.
	 * @param fechaSolicitud
	 *            fecha en que el cliente hizo la solitud.
	 * @throws MyException
	 *             cuando ocurre cualquier error en la comunicaci�n con la BD.
	 * @throws IWServiceException
	 *             cuando ocurre cualquier error en la logica de negocio.
	 */
	public Solicitud guardarSolicitud(String descripcion,int tiposolicitud,
			Date fechaSolicitud, String clienteS)
			throws MyException {

		Cliente cliente;
		TipoSolicitud tipoSolicitud = null;
		Solicitud solicitud;
		Sucursal sucursal;
		cliente = clienteDAO.obtener(clienteS);
		if (cliente == null) {
			throw new MyException("El usuario no existe en el sistema");
		}
		/*if (!cliente.getUsuario().getRol().getDescripcion().equals("cliente")) {
			throw new MyException(
					"No tiene permisos para realizar solicitudes");
		}*/

		tipoSolicitud = tipoSolicitudDAO.obtenerTipoSolicitud(tiposolicitud);
		if (tipoSolicitud == null) {
			throw new MyException(
					"El tipo de solicitud no es valida en el sistema");
		}
		if (Validaciones.isTextoVacio(descripcion)) {
			throw new MyException(
					"El campo descripcion no puede ser nulo, ni una cadena de caracteres vacia");
		}

		solicitud = new Solicitud();
		solicitud.setDescripcion(descripcion);
		solicitud.setCliente(cliente);
		solicitud.setTipoSolicitud(tipoSolicitud);
		solicitud.setFechaSolicitud(fechaSolicitud);

		solicitudDAO.guardar(solicitud);

		return solicitud;

	}

	/**
	 * Metodo para asignar un responsable que se encargue de responder la
	 * solicitud.
	 * 
	 * @param idSolicitud
	 *            identificador de la solicitud.
	 * @param responsable
	 *            usuario del encargado de responder la solicitud.
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 * @throws MyException
	 *             Manejar las excepciones de la lógica del negocio.
	 * @throws exception.MyException 
	 */
	public Solicitud asignarResponsable(int idSolicitud,
			String usuarioResponsable, String usuarioGerente)
			throws MyException, exception.MyException {

		if (Validaciones.isTextoVacio(Integer.toString(idSolicitud))) {
			throw new MyException(
					" El campo idSolicitud no debe ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(usuarioResponsable)) {
			throw new MyException(
					" El campo usuarioResponsable no debe ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(usuarioGerente)) {
			throw new MyException(
					" El campo usuarioGerente no debe ser nulo, ni una cadena de caracteres vacia");
		}
		Empleado usrGerente = empleadoDAO.obtener(usuarioGerente);
		if (usrGerente == null) {
			throw new MyException(
					"No tiene permisos para realizar esta accion");
		}
		if (!usrGerente.getUsuario().getRol().getDescripcion().equals("Gerente")) {
			throw new MyException(
					"No tiene permisos para realizar esta accion");
		}

		Empleado usrResponsable;
		Solicitud solicitud;

		usrResponsable = empleadoDAO.obtener(usuarioResponsable);
		solicitud = solicitudDAO.obtenerSolicitud(idSolicitud);

		if (usrResponsable == null) {
			throw new MyException(
					"El usuario al que le desea asignar la solicitud no se encuentra en el sistema");
		}
		if (usrResponsable.getUsuario().getRol().getDescripcion().equals("cliente")) {
			throw new MyException(
					"No le puede asignar esta responsabilidad a un cliente");
		}

		if (solicitud == null) {
			throw new MyException("No se encuentra la solicitud");
		}
		solicitud.setResponsable(usrResponsable);
		solicitudDAO.actualizar(solicitud);

		return solicitud;

	}

	/**
	 * Metodo para asignar la respuesta de la solicitud ingresada como
	 * parametro.
	 * 
	 * @param idSolicitud
	 *            identificador de la solicitud
	 * @param respuestaSolicitud
	 *            Campo de texto, contiene la respuesta por parte de la
	 *            organizacion al cliente.
	 * @param fechaRespuesta
	 *            fecha en que se respondio la solicitud
	 * @throws ExceptionDao
	 *             Manejar las excepciones del DAO.
	 * @throws MyException
	 *             Manejar las excepciones de la lógica del negocio.
	 * @throws exception.MyException 
	 */
	public void responderSolicitud(int idSolicitud, String respuestaSolicitud,
			Date fechaRespuesta, String usuarioResponsable)
			throws MyException {

		if (Validaciones.isTextoVacio(usuarioResponsable)) {
			throw new MyException(
					" El campo usuarioResponsable no debe ser nulo, ni una cadena de caracteres vacia");
		}
		if (Validaciones.isTextoVacio(respuestaSolicitud)) {
			throw new MyException(
					" El campo respuesta no debe ser nulo, ni una cadena de caracteres vacia");
		}

		Empleado usrResponsable = empleadoDAO.obtener(usuarioResponsable);
		if (usrResponsable == null) {
			throw new MyException("No existe el usuario");
		}
		if (usrResponsable.getUsuario().getRol().getDescripcion().equals("cliente")) {
			throw new MyException(
					"No tiene permisos para responder una solicitud");
		}

		Solicitud solicitud = solicitudDAO.obtenerSolicitud(idSolicitud);

		if (solicitud == null) {
			throw new MyException("No se encuentra la solicitud");
		}

		solicitud.setRespuestaSolicitud(respuestaSolicitud);
		solicitud.setFechaRespuesta(fechaRespuesta);
		solicitud.setResponsable(usrResponsable);

		solicitudDAO.actualizar(solicitud);

	}

	/**
	 * Metodo para obtener la lista de solicitudes
	 * 
	 * @return Lista de solicitudes
	 * @throws ExceptionDao
	 *             Manejar las excepciones del DAO.
	 * @throws MyException
	 *             Manejar las excepciones de la l�gica del negocio.
	 */
	public List<Solicitud> obtenerSolicitudes() throws MyException
	{
		//FALTAN VALIDACIONES
		return solicitudDAO.obtenerSolicitudes();
	}

	/**
	 * Metodo para retornar una solicitud, dado su identificador
	 * 
	 * @param id
	 *            identificador de la solicitud
	 * @return Solicitud
	 * @throws ExceptionDao
	 *             Manejar las excepciones del DAO.
	 * @throws MyException
	 *             Manejar las excepciones de la lógica del negocio.
	 */
	public Solicitud obtenerSolicitud(int id) throws MyException
		{
		Solicitud solicitud;
		solicitud = solicitudDAO.obtenerSolicitud(id);
		if (solicitud == null) {
			throw new MyException("No se encuentra la solicitud");
		}
		return solicitud;
	}
	
	/**
	 * Generar estadistica de satisfacci�n de la encuesta 
	 * 
	 * @return int resultado
	 */
	
	public int nivelSatisfaccionClientes() throws MyException{
		
		int resultado=0;
		int suma=0;
		List<RespuestaEncuesta> respuestas;
		
		respuestas = respuestaEncuestaDAO.listarRespuestas();
		
		int numeroRespuestas = respuestas.size();
		
		for (RespuestaEncuesta respuesta : respuestas) {
			suma = suma + respuesta.getSatisfaccion();
		}
		
		resultado = (suma / numeroRespuestas) ;
		
		return resultado;
		
	}
	
	/**
	 * Lista con los tiempos de respuestas de las solicitudes 
	 * 
	 * @return int resultado
	 */
	
	public List<Integer> infoTiemposRespuestas() throws MyException{ //Tipo Solicitud (?)
		
		List<Integer> tiemposRespuestas = new ArrayList<Integer>();
		List<Solicitud> solicitudes;

		Date fechaSolicitud, fechaRespuesta;
		int dias = 0;
		int id;
		
		solicitudes = solicitudDAO.obtenerSolicitudes();
		
		for (Solicitud solicitud : solicitudes) {
			fechaSolicitud = solicitud.getFechaSolicitud();
			fechaRespuesta = solicitud.getFechaRespuesta();
			id = solicitud.getId();
			
			dias = diferenciaEnDias(fechaRespuesta, fechaSolicitud);
			
			tiemposRespuestas.add(dias);
		}
		
		return tiemposRespuestas;
	}
	/**
	 * Hacer seguimiento de fecha limite para responder una solicitud TERMINAR!!
	 * 
	 * @return lista de las solicitudes atrasadas.
	 */

	public List<Solicitud> seguimientoSolicitudes() throws MyException {
		List<Solicitud> solicitudesAtrasadas = new ArrayList<Solicitud>();
		List<Solicitud> solicitudes;
		Date fechaActual = new Date();
		int dias = 0;
		Date fechaSolicitud;

		solicitudes = solicitudDAO.obtenerSolicitudes();

		for (Solicitud solicitud : solicitudes) {
			if (solicitud.getRespuestaSolicitud() == null) {
				fechaSolicitud = solicitud.getFechaSolicitud();
				dias = diferenciaEnDias(fechaActual, fechaSolicitud);
				if (dias >= 15) {
					solicitudesAtrasadas.add(solicitud);
				}

			}
		}
		return solicitudesAtrasadas;

	}

	/**
	 * Obtener los dias de diferencia entre dos fechas
	 * 
	 * @param fechaMayor
	 *            fecha mas actual
	 * @param fechaMenor
	 *            fecha mas antifua
	 * @return diferencia dias de diferencia entre las dos fechas
	 */
	private int diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		long diferenciaDias;
		long dias;
		diferenciaDias = fechaMayor.getTime() - fechaMenor.getTime();
		dias = diferenciaDias / (1000 * 60 * 60 * 24);
		return (int) dias;
	}

	/**
	 * 
	 * @param idSolicitud
	 * @return Lista de solicitudes filtradas por tipo
	 * @throws MyException
	 * @throws ExceptionDao
	 */
	public List<Solicitud> filtrarPorTipo(Integer idTipoSolicitud)
			throws  MyException {
		List<Solicitud> solicitudes;
		TipoSolicitud tipoS = tipoSolicitudDAO
				.obtenerTipoSolicitud(idTipoSolicitud);
		if (tipoS == null) {
			throw new MyException("No existe el tipo de solicitud");
		}
		solicitudes = solicitudDAO.filtrarSolicitudes(tipoS);
		return solicitudes;

	}

	public SolicitudDAO getSolicitudDAO() {
		return solicitudDAO;
	}

	public void setSolicitudDAO(SolicitudDAO solicitudDAO) {
		this.solicitudDAO = solicitudDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public TipoSolicitudDAO getTipoSolicitudDAO() {
		return tipoSolicitudDAO;
	}

	public void setTipoSolicitudDAO(TipoSolicitudDAO tipoSolicitudDAO) {
		this.tipoSolicitudDAO = tipoSolicitudDAO;
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public EmpleadoDAO getEmpleadoDAO() {
		return empleadoDAO;
	}

	public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
		this.empleadoDAO = empleadoDAO;
	}

	public SucursalDAO getSucursalDAO() {
		return sucursalDAO;
	}

	public void setSucursalDAO(SucursalDAO sucursalDAO) {
		this.sucursalDAO = sucursalDAO;
	}
	
}