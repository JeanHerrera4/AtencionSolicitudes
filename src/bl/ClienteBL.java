package bl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.ClienteDAO;
import dao.RolDAO;
import dao.UsuarioDAO;
import dto.Cliente;
import dto.Rol;
import dto.Usuario;
import exception.IWServiceException;
import exception.MyException;
import validation.Validaciones;

/**
 * Clase encargada de la logica de negocio para la clase Cliente. Clase
 * transaccional con la BD.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
@Transactional // Anotacion requerida para informarle a Spring que esta clase
// maneja transacciones

public class ClienteBL {

	/**
	 * Beans para manejar los Dao
	 */
	private ClienteDAO clienteDAO;
	private UsuarioDAO usuarioDAO;

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	/**
	 * Metodo para guardar la informacion ingresada por el cliente.
	 * 
	 * @param cedula
	 *            identificador del cliente
	 * @param nombres
	 *            nombre del cliente
	 * @param apellidos
	 *            apellidos del cliente
	 * @param email
	 *            email del cliente
	 * @param telefono
	 *            telefono del cliente
	 * @param direccion
	 *            direccion del cliente
	 * @param usuario
	 *            username del cliente
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la lógica del negocio.
	 */
	public String guardarCliente(String cedula, String nombres, String apellidos, String email, String telefono,
			String direccion, String usuario) throws MyException, IWServiceException {

		Cliente cliente = null;

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

		if (Validaciones.isTextoVacio(telefono)) {
			throw new IWServiceException("El campo telefono no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(direccion)) {
			throw new IWServiceException("El campo direccion no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(usuario)) {
			throw new IWServiceException("El campo usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}

		// Validar que el usuario exista
		Usuario user = usuarioDAO.obtener(usuario);
		if (usuario == null) {
			throw new MyException("El usuario no existe en el sistema");
		}

		// El cliente no puede existir
		if (clienteDAO.obtener(cedula) != null) {
			throw new IWServiceException("Ya existe un cliente con cedula " + cedula + " en el sistema");
		}

		cliente = new Cliente();

		cliente.setCedula(cedula);
		cliente.setNombre(nombres);
		cliente.setApellido(apellidos);
		cliente.setEmail(email);
		cliente.setTelefono(telefono);
		cliente.setDireccion(direccion);
		cliente.setUsuario(user);

		clienteDAO.guardar(cliente);
		return "Se ha creado el cliente exitosamente";

	}

	/**
	 * Metodo para modificar la informacion ingresada por el cliente.
	 * 
	 * @param cedula
	 *            identificador del cliente
	 * @param nombres
	 *            nombre del cliente
	 * @param apellidos
	 *            apellidos del cliente
	 * @param email
	 *            email del cliente
	 * @param telefono
	 *            telefono del cliente
	 * @param direccion
	 *            direccion del cliente
	 * @param usuario
	 *            username del cliente
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 * @throws IWServiceException
	 *             Manejar las excepciones de la lógica del negocio.
	 */
	public String actualizarCliente(String cedula, String nombres, String apellidos, String email, String telefono,
			String direccion, String usuario) throws MyException, IWServiceException {

		Cliente cliente = null;

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

		if (Validaciones.isTextoVacio(telefono)) {
			throw new IWServiceException("El campo telefono no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(direccion)) {
			throw new IWServiceException("El campo direccion no puede ser nulo, ni una cadena de caracteres vacia");
		}

		if (Validaciones.isTextoVacio(usuario)) {
			throw new IWServiceException("El campo usuario no puede ser nulo, ni una cadena de caracteres vacia");
		}

		// Validar que el usuario exista
		Usuario user = usuarioDAO.obtener(usuario);
		if (usuario == null) {
			throw new MyException("El usuario no existe en el sistema");
		}

		// Validar que el cliente exista
		if (clienteDAO.obtener(cedula) == null) {
			throw new IWServiceException("No existe el cliente con cedula: " + cedula + " en el sistema");
		}

		cliente = new Cliente();

		cliente.setCedula(cedula);
		cliente.setNombre(nombres);
		cliente.setApellido(apellidos);
		cliente.setEmail(email);
		cliente.setTelefono(telefono);
		cliente.setDireccion(direccion);
		cliente.setUsuario(user);

		clienteDAO.modificar(cliente);

		return "Se ha actualizado exitosamente";

	}

	/**
	 * Metodo para eliminar un cliente
	 * 
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 
	public String eliminarCliente(String cedula) throws MyException, IWServiceException {
		Cliente cliente = null;

		cliente = new Cliente();
		cliente.setCedula(cedula);

		clienteDAO.eliminar(cliente);

		return null;

	}*/

	/**
	 * Metodo para obtener el listado de clientes
	 * 
	 * @return Lista de clientes
	 * @throws MyException
	 *             Manejar las excepciones del DAO.
	 */
	public List<Cliente> obtener() throws MyException {
		return clienteDAO.obtener();
	}

}
