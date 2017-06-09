package implDAO;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import dao.SucursalDAO;
import dto.Sucursal;
import dto.TipoSolicitud;
import exception.MyException;

/**
 * Implementaci�n de la interfaz SucursalDAO, 
 * en esta clase se hereda HibernateDaoSupport 
 * para adquirir la funcionalidad de Hibernate.
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
@Transactional
public class SucursalDAOImpl implements SucursalDAO {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Sucursal> obtenerSucursales() throws MyException {
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Sucursal.class);
			sucursales = criteria.list();
		} catch (DataAccessException e) {
			throw new MyException(e);
		}
		return sucursales;
	}

	@Override
	public Sucursal obtenerSucursal(Integer id) throws MyException {
		Sucursal sucursal;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			sucursal = (Sucursal)session.get(Sucursal.class, id);
		} catch (DataAccessException e) {
			throw new MyException(e);
		}
		return sucursal;
	}

}