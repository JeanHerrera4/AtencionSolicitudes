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

import dao.ClienteDAO;
import dto.Cliente;
import exception.MyException;

/**
 * Implementaci�n de los metodos
 *  de la interfaz ClienteDao.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
@Transactional
public class ClienteDAOimpl implements ClienteDAO {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void guardar(Cliente cliente) throws MyException {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.save(cliente);

		} catch (HibernateException e) {

			throw new MyException("Ocurrio un error guardando el cliente", e);
		}
		
	}

	@Override
	public void modificar(Cliente cliente) throws MyException {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.update(cliente);

		} catch (HibernateException e) {

			throw new MyException("Ocurrio un error actualizando el cliente", e);
		}
		
	}

	@Override
	public void eliminar(Cliente cliente) throws MyException {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.delete(cliente);

		} catch (HibernateException e) {

			throw new MyException("Ocurrio un error eliminando el cliente", e);
		}		
	}

	@Override
	public List<Cliente> obtener() throws MyException {
		Session session = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		Criteria criteria = null;

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(Cliente.class);
			criteria.addOrder(Order.asc("nombre"));

			clientes = criteria.list();
		} catch (HibernateException e) {
			throw new MyException("Ocurrio un error consultando los clientes", e);
		}

		return clientes;
	}

	@Override
	public Cliente obtener(String cedula) throws MyException {
		Cliente cliente = new Cliente();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			cliente = (Cliente) session.get(Cliente.class, cedula);

		} catch (HibernateException e) {
			throw new MyException("Ocurrio un error consultando los clientes", e);
		}

		return cliente;
	}

}
