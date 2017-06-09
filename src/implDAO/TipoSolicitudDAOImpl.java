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

import dao.TipoSolicitudDAO;
import dto.TipoSolicitud;
import dto.Usuario;
import exception.MyException;

/**
 * Implementaci�n de la interfaz TipoSolicitudDao, 
 * esta se basara en Hibernate,
 * en esta clase se hereda de HibernateDaoSupport 
 * para adquirir la funcionalidad de Hibernate.
 * 
 * @author Yuri Quejada
 * @author Jean Herrera
 * @author Daniel Pelaez
 * @version 1.0
 */
@Transactional
public class TipoSolicitudDAOImpl implements TipoSolicitudDAO{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<TipoSolicitud> obtenerTipoSolicitudes() throws MyException {
		List<TipoSolicitud> tiposolicitudes = new ArrayList<TipoSolicitud>();
		Session session = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(TipoSolicitud.class);
			tiposolicitudes = criteria.list();
		} catch (DataAccessException e) {
			throw new MyException(e);
		}
		return tiposolicitudes;
	}

	@Override
	public TipoSolicitud obtenerTipoSolicitud(Integer id) throws MyException {
		TipoSolicitud tiposolicitud;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			tiposolicitud = (TipoSolicitud)session.get(TipoSolicitud.class, id);
		} catch (DataAccessException e) {
			throw new MyException(e);
		}
		return tiposolicitud;
	}

}