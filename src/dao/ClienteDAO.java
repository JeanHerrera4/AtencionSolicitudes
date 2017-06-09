package dao;

import java.util.List;

import dto.Cliente;
import exception.MyException;

/**
 * DAO Interfaz que define los metodos que va a proveer la clase Cliente.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

public interface ClienteDAO {

	/**
	 * Registrar un nuevo cliente en la tabla Cliente.
	 * 
	 * @param cliente,
	 *            registro que contiene la informacion del cliente que se va a
	 *            ingresar.
	 * @throws MyException
	 *             cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public void guardar(Cliente cliente) throws MyException;

	/**
	 * Actualiza la informacion del cliente en la tabla Cliente.
	 * 
	 * @param cliente,
	 *            registro que contiene la informacion del cliente que se va a
	 *            modificar.
	 * @throws ExceptionDao
	 *             cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public void modificar(Cliente cliente) throws MyException;

	/**
	 * Eliminar un cliente de la tabla Cliente.
	 * 
	 * @param cliente,
	 *            registro que contiene la informacion del cliente que se va a
	 *            eliminar.
	 * @throws MyException
	 *             cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public void eliminar(Cliente cliente) throws MyException;

	/**
	 * Obtiene la lista de clientes almacenados en la tabla Cliente de la base
	 * de datos.
	 * 
	 * @return lista de clientes.
	 * @throws MyException
	 *             cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public List<Cliente> obtener() throws MyException;

	/**
	 * Obtiene el cliente que corresponda a la cédula ingresada como parámetro.
	 * 
	 * @param cedula,
	 *            identificacion del cliente.
	 * @return cliente con toda su información.
	 * @throws MyException
	 *             cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public Cliente obtener(String cedula) throws MyException;

}
