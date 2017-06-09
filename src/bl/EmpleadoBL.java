package bl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.EmpleadoDAO;
import dao.UsuarioDAO;
import dto.Empleado;
import dto.Usuario;
import exception.IWServiceException;
import exception.MyException;
import validation.Validaciones;

/**
 * Clase encargada de la logica de negocio para la clase Empleado. Clase
 * transaccional con la BD.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

@Transactional // Anotacion requerida para informarle a Spring que esta clase
// maneja transacciones
public class EmpleadoBL {

	/**
	 * Beans para manejar los Dao
	 */
	private EmpleadoDAO empleadoDAO;
	private UsuarioDAO usuarioDAO;

	public EmpleadoDAO getEmpleadoDAO() {
		return empleadoDAO;
	}

	public void setEmpleadoDAO(EmpleadoDAO empleadoDAO) {
		this.empleadoDAO = empleadoDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	/**
	 * Metodo para guardar la informacion ingresada por el empleado.
	 * 
	 * @param cedula
	 *            identificador del empleado
	 * @param nombres
	 *            nombre del empleado
	 * @param apellidos
	 *            apellidos del empleado
	 * @param email
	 *            email del empleado
	 * @param telefono
	 *            telefono del empleado
	 * @param direccion
	 *            direccion del empleado
	 * @param usuario
	 *            username del empleado
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la lógica del negocio.
	 */
	public String guardarEmpleado(String cedula, String nombres, String apellidos, String email, String usuario)
			throws MyException, IWServiceException {

		Empleado empleado = null;

		if (Validaciones.isTextoVacio(cedula)) {
			throw new IWServiceException("El campo cédula no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(nombres)) {
			throw new IWServiceException("El campo nombres  no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(apellidos)) {
			throw new IWServiceException("El campo apellidos no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(email)) {
			throw new IWServiceException("El campo email no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (!Validaciones.isEmail(email)) { // SI NO ES UN CORREO MANDAR LA
											// EXCEPTION
			throw new IWServiceException("El correo electronico debe ser valido");
		}

		if (Validaciones.isTextoVacio(usuario)) {
			throw new IWServiceException("El campo usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}

		// Validar que el usuario exista
		Usuario user = usuarioDAO.obtener(usuario);
		if (usuario == null) {
			throw new MyException("El usuario no existe en el sistema");
		}

		// El empleado no puede existir
		if (empleadoDAO.obtener(cedula) != null) {
			throw new IWServiceException("Ya existe un empleado con cedula " + cedula + " en el sistema");
		}

		empleado = new Empleado();

		empleado.setCedula(cedula);
		empleado.setNombre(nombres);
		empleado.setApellido(apellidos);
		empleado.setEmail(email);
		empleado.setUsuario(user);

		empleadoDAO.guardar(empleado);
		return "Se ha creado el empleado exitosamente";

	}

	/**
	 * Metodo para modificar la informacion ingresada por el empleado.
	 * 
	 * @param cedula
	 *            identificador del empleado
	 * @param nombres
	 *            nombre del empleado
	 * @param apellidos
	 *            apellidos del empleado
	 * @param email
	 *            email del empleado
	 * @param telefono
	 *            telefono del empleado
	 * @param direccion
	 *            direccion del empleado
	 * @param usuario
	 *            username del empleado
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la lógica del negocio.
	 */
	public String actualizarEmpleado(String cedula, String nombres, String apellidos, String email, String usuario) 
			throws MyException, IWServiceException {

		Empleado empleado = null;

		if (Validaciones.isTextoVacio(cedula)) {
			throw new IWServiceException("El campo cédula no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(nombres)) {
			throw new IWServiceException("El campo nombres  no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(apellidos)) {
			throw new IWServiceException("El campo apellidos no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(email)) {
			throw new IWServiceException("El campo email no puede ser nulo, ni una cadena de caracteres vacia");
		}
		
		if (!Validaciones.isEmail(email)) { // SI NO ES UN CORREO MANDAR LA
											// EXCEPTION
			throw new IWServiceException("El correo electronico debe ser valido");
		}

		if (Validaciones.isTextoVacio(usuario)) {
			throw new IWServiceException("El campo usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}

		// Validar que el usuario exista
		Usuario user = usuarioDAO.obtener(usuario);
		if (usuario == null) {
			throw new MyException("El usuario no existe en el sistema");
		}

		// Validar que el empleado exista
		if (empleadoDAO.obtener(cedula) == null) {
			throw new IWServiceException("No existe el empleado con cedula: " + cedula + " en el sistema");
		}

		empleado = new Empleado();

		empleado.setCedula(cedula);
		empleado.setNombre(nombres);
		empleado.setApellido(apellidos);
		empleado.setEmail(email);
		empleado.setUsuario(user);

		empleadoDAO.modificar(empleado);

		return "Se ha actualizado exitosamente";

	}

	/**
	 * Metodo para eliminar un empleado
	 * 
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 */
	public String eliminarEmpleado(String cedula) throws MyException, IWServiceException {
		Empleado empleado = null;

		empleado = new Empleado();
		empleado.setCedula(cedula);

		empleadoDAO.eliminar(empleado);

		return null;

	}

	/**
	 * Metodo para obtener el listado de empleados
	 * 
	 * @return Lista de empleados
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 */
	public List<Empleado> obtener() throws MyException {
		return empleadoDAO.obtener();
	}

}
