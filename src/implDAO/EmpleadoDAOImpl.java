package implDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.transaction.annotation.Transactional;

import dao.EmpleadoDAO;
import dto.Cliente;
import dto.Empleado;
import exception.MyException;

/**
 * Implementaci�n de los metodos
 *  de la interfaz EmpleadoDAO.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
@Transactional
public class EmpleadoDAOImpl implements EmpleadoDAO{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void guardar(Empleado empleado) throws MyException {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.save(empleado);

		} catch (HibernateException e) {

			throw new MyException("Ocurrio un error guardando el empleado", e);
		}		
	}

	@Override
	public void modificar(Empleado empleado) throws MyException {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.update(empleado);

		} catch (HibernateException e) {

			throw new MyException("Ocurrio un error actualizando el empleado", e);
		}		
	}

	@Override
	public void eliminar(Empleado empleado) throws MyException {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.delete(empleado);

		} catch (HibernateException e) {

			throw new MyException("Ocurrio un error eliminando el empleado", e);
		}		
	}

	@Override
	public List<Empleado> obtener() throws MyException {
		Session session = null;
		List<Empleado> empleados = new ArrayList<Empleado>();
		Criteria criteria = null;

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(Empleado.class);
			criteria.addOrder(Order.asc("nombre"));

			empleados = criteria.list();
		} catch (HibernateException e) {
			throw new MyException("Ocurrio un error consultando los empleados", e);
		}

		return empleados;
	}

	@Override
	public Empleado obtener(String cedula) throws MyException {
		Empleado empleado = new Empleado();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			empleado = (Empleado) session.get(Empleado.class, cedula);

		} catch (HibernateException e) {
			throw new MyException("Ocurrio un error consultando los empleados", e);
		}

		return empleado;
	}

}
