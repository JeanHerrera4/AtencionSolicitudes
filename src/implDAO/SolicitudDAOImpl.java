package implDAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import dao.SolicitudDAO;
import dto.Solicitud;
import dto.TipoSolicitud;
import exception.MyException;

/**
 * Implementaci�n de la interfaz SolicitudDAO. 
 * 
 * @author Yuri Quejada
 * @author Daniel Pelaez
 * @author Jean Herrera
 * @version 1.0
 */
@Transactional
public class SolicitudDAOImpl implements SolicitudDAO
{
	private SessionFactory sessionFactory;


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Solicitud> obtenerSolicitudes() throws MyException {
	List<Solicitud> solicitudes = new ArrayList<Solicitud>();
	Session session = null;
		
		try{
			 session = sessionFactory.getCurrentSession();
			
			Criteria criteria = session.createCriteria(Solicitud.class);
			
			solicitudes = criteria.list();
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		return solicitudes;
	}

	@Override
	public Solicitud obtenerSolicitud(int id) throws MyException{
		Solicitud solicitud = null;
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			
			solicitud = (Solicitud)session.get(Solicitud.class, id);
			
		}catch(HibernateException e){
			throw new MyException(e);
			
	}
	
	return solicitud;

	}


	@Override
	public void guardar(Solicitud solicitud) throws MyException {
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			session.save(solicitud);
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void eliminarSolicitud(Solicitud solicitud) throws MyException {
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			
			session.delete(solicitud);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}

	@Override
	public void actualizar(Solicitud solicitud) throws MyException {
		Session session = null;
		try{
			session = sessionFactory.getCurrentSession();
			
			session.update(solicitud);
			
		}catch(HibernateException e){
			throw new MyException(e);
		}
		
	}
	
	@Override
	public List<Solicitud> filtrarSolicitudes(TipoSolicitud tipoSolicitud) throws MyException {
		Session session = null;
		List<Solicitud> solicitudes;
		try {
			session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Solicitud.class);
			criteria.add(Restrictions.eq("tipoSolicitud", tipoSolicitud));
			solicitudes = criteria.list();
		} catch (HibernateException e) {
			throw new MyException(e);
		}
		return solicitudes;
	}	

}
