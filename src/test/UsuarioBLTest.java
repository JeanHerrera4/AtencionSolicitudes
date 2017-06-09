package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import bl.ClienteBL;
import bl.UsuarioBL;
import dto.Empleado;
import dto.Usuario;
import exception.IWServiceException;
import exception.MyException;

/**
 * Pruebas para La logica de negocio de todo lo relacionado con los usuarios del
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
public class UsuarioBLTest {

	@Autowired // Para injectar el objeto usuarioBL, esto tambien lo inicializa
	UsuarioBL usuarioBL;

	/**
	 * Prueba para vericar que un determinado usuario exista en la base de
	 * datos. Se verifica que el usuario y contraseña esten almacendos en la
	 * base de datos. Se imprime el nombre del usuario y el rol que tiene
	 * asignado.
	 */
	
	@Test
	public void autenticarUsuario() {

		try {
			Usuario user = usuarioBL.autenticarUsuario("Marcela18", "1234");

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (IWServiceException e) {
			e.printStackTrace();
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

			lista = usuarioBL.obtener();
			assertTrue(lista.size() > 0);

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	/**
	 * Prueba para obtener un usuario dado su username. Se imprime un mensaje
	 * confirmando si fue exitosa la consulta. @throws
	 */
	@Test
	public void testObtenerUsuario() {
		Usuario usuario = new Usuario();
		try {

			usuario = usuarioBL.obtener("Marcela18");

			System.out.println(usuario.getUser() + " " + usuario.getPassword());

			assertTrue(usuario != null);

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (IWServiceException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
}
