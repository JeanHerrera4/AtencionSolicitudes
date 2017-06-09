package implDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.transaction.annotation.Transactional;

import dao.UsuarioDAO;
import dto.Empleado;
import dto.Usuario;
import exception.MyException;

/**
 * Implementaci�n de los metodos
 *  de la interfaz UsuarioDAO.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
@Transactional
public class UsuarioDAOImpl implements UsuarioDAO{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Usuario obtener(String user) throws MyException {
		Session session = null;
		Usuario usuario = new Usuario();

		try {
			session = sessionFactory.getCurrentSession();
			usuario = (Usuario) session.get(Usuario.class, user);

		} catch (HibernateException e) {
			throw new MyException("Ocurrio un error consultando el usuario", e);

		} 
		return usuario;
	}

	@Override
	public void guardar(Usuario usuario) throws MyException {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.save(usuario);

		} catch (HibernateException e) {

			throw new MyException("Ocurrio un error guardando el usuario", e);
		}		
	}

	@Override
	public void eliminar(Usuario usuario) throws MyException {
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.delete(usuario);

		} catch (HibernateException e) {

			throw new MyException("Ocurrio un error eliminando el usuario", e);
		}
		
	}

	@Override
	public List<Usuario> obtenerUsuarios() throws MyException {
		Session session = null;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Criteria criteria = null;

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(Usuario.class);
			criteria.addOrder(Order.asc("user"));

			usuarios = criteria.list();
		} catch (HibernateException e) {
			throw new MyException("Ocurrio un error consultando los usuarios", e);
		}

		return usuarios;
	}

}
