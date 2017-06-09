package dao;

import java.util.List;

import dto.Cliente;
import dto.Usuario;
import exception.MyException;

/**
 * DAO
 * Interfaz que define los metodos que va a proveer
 * la clase Usuario.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

public interface UsuarioDAO {
	
	/**
	* Registrar un nuevo usuario en la tabla Usuario.
	* 
	* @param usuario, registro que contiene la informacion del usuario que se va a ingresar.
	 * @throws MyException cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public void guardar(Usuario usuario) throws MyException;
	
	/**
	* Eliminar un usuario de la tabla Usuario.
	* 
	* @param usuario, registro que contiene la informacion del usuario que se va a eliminar.
	 * @throws MyException cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public void eliminar(Usuario usuario) throws MyException;
	
	/**
	 * Obtiene el usuario que corresponda al user ingresado como parámetro.
	 * 
	 * @param user, username del usuario.
	 * @return usuario con toda su información.
	 * @throws MyException cuando ocurre cualquier error en la comunicación con la BD.
	 */
	public Usuario obtener(String user) throws MyException;
	
	/**
	 * Obtener la lista de usuarios almacenados en la tabla Usuario de la base
	 * de datos.
	 * 
	 * @return lista de Usuarios.
	 * @throws MyException cuando ocurre cualquier error en la comunicación con la BD.
	 */
public List<Usuario> obtenerUsuarios() throws MyException;

}
