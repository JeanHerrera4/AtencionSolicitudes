package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dao.ClienteDAO;
import dao.EmpleadoDAO;
import dto.Cliente;
import dto.Empleado;
import exception.MyException;

/**
 * Pruebas para La implementacion del DAO relacionado con los empleados del
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
public class EmpleadoDAOImplTest {
	
	@Autowired // Para injectar el objeto empleadoDAO, esto tambien lo inicializa
	EmpleadoDAO empleadoDAO;

	/**
	 * Prueba para guardar un empleado. Se imprime un mensaje confirmando si fue
	 * exitosa la insercion.
	 */
	@Test
	public void testGuardarEmpleado() {
		Empleado empleado = new Empleado();

		try {
			empleado.setCedula("1146436892");
			empleado.setNombre("Yuri");
			empleado.setApellido("Quejada");
			empleado.setEmail("ymarcela.santos@gmail.com");

			empleadoDAO.guardar(empleado);

			

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	/**
	 * Prueba para modificar un empleado. Se imprime un mensaje confirmando si
	 * fue exitosa la actualización.
	 */
	@Test
	public void testModificarEmpleado() {
		Empleado empleado = new Empleado();

		try {
			empleado.setCedula("1146436892");
			empleado.setNombre("Yuri");
			empleado.setApellido("Quejada Santos");
			empleado.setEmail("ymarcela.quejada@udea.edu.co");

			empleadoDAO.modificar(empleado);

		} catch (MyException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

	/**
	 * Prueba para eliminar un empleado. Se imprime un mensaje confirmando si fue
	 * exitosa la eliminación.
	 */
	@Test
	public void testEliminarEmpleado() {
		Empleado empleado = new Empleado();

		try {
			empleado.setCedula("1146436892");

			empleadoDAO.eliminar(empleado);

		} catch (MyException e) {
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
			lista = empleadoDAO.obtener();

			// Assert.assertTrue(lista.size() > 0);
			assertTrue(lista.size() > 0);

		} catch (MyException e) {
			fail(e.getMessage());
		}

	}

	/**
	 * Prueba para obtener un empleado dada su cedula. Se imprime un mensaje
	 * confirmando si fue exitosa la consulta.
	 */
	@Test
	public void testObtenerEmpleado() {
		Empleado empleado = new Empleado();
		try {

			empleado = empleadoDAO.obtener("1146436892");

			 System.out.println(empleado.getNombre() + " " + empleado.getApellido() + " " + empleado.getEmail());

			assertTrue(empleado != null);

		} catch (MyException e) {
			fail(e.getMessage());
		}

	}

}
