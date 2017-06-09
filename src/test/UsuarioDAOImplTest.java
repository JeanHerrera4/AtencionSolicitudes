package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dao.UsuarioDAO;
import dto.Empleado;
import dto.Usuario;
import exception.MyException;

/**
 * Pruebas para La implementacion del DAO relacionado con los usuarios del
 * sistema.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = "classpath:configuracionSpring.xml")
public class UsuarioDAOImplTest {
	
	@Autowired // Para injectar el objeto usuarioDAO, esto tambien lo inicializa
	UsuarioDAO usuarioDAO;

	/**
	 * Prueba para obtener un usuario dado su username. Se imprime un mensaje
	 * confirmando si fue exitosa la consulta.
	 */
	@Test
	public void testObtenerUsuario() {
		Usuario usuario = new Usuario();
		try {

			usuario = usuarioDAO.obtener("Marcela18");

			 System.out.println(usuario.getUser()+ " " + usuario.getPassword());

			assertTrue(usuario != null);

		} catch (MyException e) {
			fail(e.getMessage());
		}

	}
	
	/**
	 * Prueba para obtener la lista de usuarios. Se imprime un mensaje
	 * confirmando si fue exitosa la consulta.
	 */
	@Test
	public void testObtenerList() {
		List<Usuario> lista = null;

		try {
			lista = usuarioDAO.obtenerUsuarios();

			// Assert.assertTrue(lista.size() > 0);
			assertTrue(lista.size() > 0);

		} catch (MyException e) {
			fail(e.getMessage());
		}

	}
	
	/**
	 * Prueba para guardar un usuario. Se imprime un mensaje confirmando si fue
	 * exitosa la insercion.
	 */
	@Test
	public void testGuardarUsuario() {
		Usuario usuario = new Usuario();

		try {
			usuario.setUser("Marcela18");
			usuario.setPassword("1234");

			usuarioDAO.guardar(usuario);

			

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
	
	/**
	 * Prueba para eliminar un usuario. Se imprime un mensaje confirmando si fue
	 * exitosa la eliminación.
	 */
	@Test
	public void testEliminarUsuario() {
		Usuario usuario = new Usuario();

		try {
			usuario.setUser("Marcela18");

			usuarioDAO.eliminar(usuario);

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
