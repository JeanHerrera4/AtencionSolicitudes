package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import bl.ClienteBL;
import dao.ClienteDAO;
import dto.Cliente;
import exception.IWServiceException;
import exception.MyException;

/**
 * Pruebas para La logica de negocio de todo lo relacionado con los clientes del
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
public class ClienteBLTest {

	@Autowired // Para injectar el objeto clienteBL, esto tambien lo inicializa
	ClienteBL clienteBL;

	/**
	 * Prueba para guardar un cliente. Se imprime un mensaje confirmando si fue
	 * exitosa la insercion.
	 */
	@Test
	@Rollback(false)
	public void testGuardarCliente() {
		try {
			String mensaje = clienteBL.guardarCliente("12345", "Jorge Luis", "Bojaca", "jorge@gmail.com", "32422",
					"CL 3 # 2-4","jogebojaca");
			System.out.println(mensaje);
		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (IWServiceException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
	
	/**
	 * Prueba para modificar un cliente. Se imprime un mensaje confirmando si fue
	 * exitosa la insercion.
	 */
	@Test
	public void testModificarCliente() {
		try {
			String mensaje = clienteBL.actualizarCliente("12345", "Jorge Luis", "Bojaca", "jorge@hotmail.com", "32422",
					"CL 3 # 2-4","jogebojaca");
			System.out.println(mensaje);
		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (IWServiceException e) {
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

			lista = clienteBL.obtener(); 
			assertTrue(lista.size() > 0); 

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
