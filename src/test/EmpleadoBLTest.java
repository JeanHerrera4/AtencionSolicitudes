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
import bl.EmpleadoBL;
import dto.Cliente;
import dto.Empleado;
import exception.IWServiceException;
import exception.MyException;


/**
 * Pruebas para La logica de negocio de todo lo relacionado con los empleados del
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
public class EmpleadoBLTest {


	@Autowired // Para injectar el objeto empleadoBL, esto tambien lo inicializa
	EmpleadoBL empleadoBL;

	/**
	 * Prueba para guardar un empleado. Se imprime un mensaje confirmando si fue
	 * exitosa la insercion.
	 */
	@Test
	public void testGuardarEmpleado() {
		try {
			String mensaje = empleadoBL.guardarEmpleado("12345", "Jorge Luis", "Bojaca", "jorge@gmail.com", "jogebojaca");
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
	 * Prueba para modificar un empleado. Se imprime un mensaje confirmando si fue
	 * exitosa la insercion.
	 */
	@Test
	public void testModificarEmpleado() {
		try {
			String mensaje = empleadoBL.actualizarEmpleado("12345", "Jorge Luis", "Bojaca", "jorge@gmail.com", "jogebojaca");
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
	 * Prueba para obtener la lista de empleados. Se imprime un mensaje
	 * confirmando si fue exitosa la consulta.
	 */
	@Test
	public void testObtenerList() {
		List<Empleado> lista = null; 

		try {

			lista = empleadoBL.obtener(); 
			assertTrue(lista.size() > 0); 

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}
}
