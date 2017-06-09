package dao;

import java.util.List;

import dto.Cliente;
import dto.Empleado;
import exception.MyException;

/**
 * DAO
 * Interfaz que define los metodos que va a proveer
 * la clase Empleado.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

public interface EmpleadoDAO {
	/**
	* Registrar un nuevo empleado en la tabla Empleado.
	* 
	* @param empleado, registro que contiene la informacion del empleado que se va a ingresar.
	 * @throws MyException cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public void guardar(Empleado empleado) throws MyException;


	/**
	 * Actualiza la informacion del empleado en la tabla Empleado.
	 * 
	 * @param empleado, registro que contiene la informacion del empleado que se va a modificar.
	 * @throws ExceptionDao cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public void modificar(Empleado empleado) throws MyException;
	
	/**
	* Eliminar un empleado de la tabla Empleado.
	* 
	* @param empleado, registro que contiene la informacion del empleado que se va a eliminar.
	 * @throws MyException cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public void eliminar(Empleado empleado) throws MyException;
	
	
	/**
	 * Obtiene la lista de empleados almacenados en la tabla Empleado de la base de datos.
	 * 
	 * @return lista de empleados.
	 * @throws MyException cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public List<Empleado> obtener() throws MyException;
	

	/**
	 * Obtiene el empleado que corresponda a la cédula ingresada como parámetro.
	 * 
	 * @param cedula, identificacion del empleado.
	 * @return empleado con toda su información.
	 * @throws MyException cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public Empleado obtener(String cedula) throws MyException;
}
