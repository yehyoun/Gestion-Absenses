
package ing.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ing.entity.Responsable;
import ing.util.HibernateUtil;

// Generated 5 mars 2016 15:35:36 by Hibernate Tools 4.0.0

/**
 * Home object for domain model class ResponsableService.
 * 
 * @see .ResponsableService
 * @author Hibernate Tools
 */
public class ResponsableService implements IResponsableDAO {

	@Override
	public Long connect(String login, String password) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query q = session.createQuery(" from Responsable e where e.login=:x and e.password=:y");
		q.setParameter("x", login);
		q.setParameter("y", password);
		if (q.list().size() == 0) {
			session.getTransaction().commit();
			session.close();

			return null;
		}

		else {
			Responsable e = (Responsable) q.list().get(0);
			session.getTransaction().commit();
			session.close();

			return e.getId();
		}

	}
}
