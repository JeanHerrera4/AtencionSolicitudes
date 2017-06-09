package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dao.ClienteDAO;
import dto.Cliente;
import exception.MyException;

/**
 * Pruebas para La implementacion del DAO relacionado con los clientes del
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

public class ClienteDAOImplTest {
	@Autowired // Para injectar el objeto clienteDAO, esto tambien lo inicializa
	ClienteDAO clienteDAO;

	/**
	 * Prueba para guardar un cliente. Se imprime un mensaje confirmando si fue
	 * exitosa la insercion.
	 */
	@Test
	public void testGuardarCliente() {
		Cliente cliente = new Cliente();

		try {
			cliente.setCedula("1146437892");
			cliente.setNombre("Yuri");
			cliente.setApellido("Quejada");
			cliente.setEmail("ymarcela.santos@gmail.com");
			cliente.setTelefono("3127571732");

			clienteDAO.guardar(cliente);

			cliente.setCedula("1146462289");
			cliente.setNombre("Jorge");
			cliente.setApellido("Bojacá");
			cliente.setEmail("jorgebojaca@gmail.com");
			cliente.setTelefono("3127573478");

			clienteDAO.guardar(cliente);

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	/**
	 * Prueba para modificar un cliente. Se imprime un mensaje confirmando si
	 * fue exitosa la actualización.
	 */
	@Test
	public void testModificarCliente() {
		Cliente cliente = new Cliente();

		try {
			cliente.setCedula("1146437892");
			cliente.setNombre("Yuri");
			cliente.setApellido("Quejada Santos");
			cliente.setEmail("ymarcela.santos@gmail.com");
			cliente.setTelefono("3127571732");

			clienteDAO.modificar(cliente);

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	/**
	 * Prueba para eliminar un cliente. Se imprime un mensaje confirmando si fue
	 * exitosa la eliminación.
	 */
	@Test
	public void testEliminarCliente() {
		Cliente cliente = new Cliente();

		try {
			cliente.setCedula("1146437892");

			clienteDAO.eliminar(cliente);

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	/**
	 * Prueba para obtener la lista de clientes. Se imprime un mensaje
	 * confirmando si fue exitosa la consulta.
	 */
	@Test
	public void testObtenerList() {
		List<Cliente> lista = null;

		try {
			lista = clienteDAO.obtener();

			// Assert.assertTrue(lista.size() > 0);
			assertTrue(lista.size() > 0);

		} catch (MyException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Prueba para obtener un cliente dada su cedula. Se imprime un mensaje
	 * confirmando si fue exitosa la consulta.
	 */
	@Test
	public void testObtenerCliente() {
		Cliente cliente = new Cliente();
		try {

			cliente = clienteDAO.obtener("1146437892");

			 System.out.println(cliente.getNombre() + " " + cliente.getApellido() + " " + cliente.getEmail());

			assertTrue(cliente != null);

		} catch (MyException e) {
			fail(e.getMessage());
		}

	}

}
