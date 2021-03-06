/**
 * 
 */
package ing.DAO;

import java.util.ArrayList;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.criterion.Restrictions;

import ing.entity.Absence;
import ing.model.ImprimeModel;
import ing.model.MailClass;
import ing.util.HibernateUtil;



/**
 * @author yaya
 *
 */
public class ImplAbsenceDAO implements IAbsenceDAO {
	
	
	
	public List<Absence> Etudiants(String matiere, Long groupe){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		
		
		Criteria c = session.createCriteria(Absence.class);
		c.createAlias("etudiant", "e");
		c.createAlias("matiere", "m");
		c.add(Restrictions.eq("m.libelle", matiere));
		c.add(Restrictions.eq("e.groupe.id", groupe));

		if (c.list().size() == 0) {
			System.out.println("requete null");
			session.getTransaction().commit();
			session.close();
			return null;
		}

		else {
			List<Absence> ret = c.list();
			session.getTransaction().commit();
			session.close();
			return ret;
		}
		
		
	}

	@Override
	public List<Absence> listeAbsenceParMatiere(Long idEtudiant, Long idMatiere) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<Absence> l = null;

		/*
		 * // sql code SQLQuery q=session.createSQLQuery(
		 * "select a.date from Absence a where a.id_etud=:x and a.id_mat=:y");
		 * 
		 * q.setParameter("x", idEtudiant); q.setParameter("y", idMatiere);
		 */

		Criteria c = session.createCriteria(Absence.class);
		c.createAlias("etudiant", "a");
		c.createAlias("matiere", "m");
		c.add(Restrictions.eq("a.id", idEtudiant));
		c.add(Restrictions.eq("m.id", idMatiere));
		if (c.list().size() != 0) {
			l = new ArrayList<>();
			for (Object o : c.list()) {

				l.add((Absence) o);
			}
			session.getTransaction().commit();
			session.close();
			return l;
		}
		// autre methode
		/*
		 * Iterator<Absence> it=c.list().iterator(); while (it.hasNext()) {
		 * Absence absence = (Absence) it.next();
		 * //System.out.println(absence.getDate()); l.add(absence);
		 * 
		 * }
		 */
		else {

			session.getTransaction().commit();
			session.close();
			return null;
		}
	}

	@Override
	public boolean ajouterAbsence(Absence abs) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean annulerAbsence(Long id) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query q = session.createQuery("delete from Absence  where id=:x ");
			 q.setParameter("x", id);
			 
					q.executeUpdate();
					session.getTransaction().commit();
					session.close();
					
					
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ing.DAO.IAbsenceDAO#listmail()
	 */
	@Override
	public List<MailClass> listmail() {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		List<MailClass> mailListe=new ArrayList<>() ;
		Query q = session.createQuery("select count(distinct a.date),a.etudiant.id,a.etudiant.email,a.matiere.libelle "
				+ "from Absence a group by a.etudiant.id,a.matiere.libelle");

		List<Object[]> rows = q.list();

		for (Object[] row : rows) {
			System.out.println(" implAbsence : "+row[0] + " " + row[1] + " " + row[2] + " " + row[3]);

			if ((Integer.parseInt(row[0].toString())) > 2)
				mailListe.add(new MailClass( row[2].toString(),row[3].toString()));

		}

		session.getTransaction().commit();
		session.close();
		if(mailListe.size()==0)
		return null;
		else{
			for (MailClass mc : mailListe) {
				System.out.println("list mail + matiere : "+ mc.getMail()+"  mail : "+mc.getMatiere());
				
			}
			
			return mailListe;
		}
	}

	@Override
	public List<Absence> absencesByMatiereAndGroupe(String matiere, Long groupe) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		/*
		 * Query q=session.createQuery(
		 * "select e.nom,e.prenom,a.date from Absence a,Etudiant e" +
		 * " where a.matiere:=x and e.groupe:=y"); q.setParameter("x", idMat);
		 * q.setParameter("y", idGroupe);
		 */

		System.out.println(matiere);
		Criteria c = session.createCriteria(Absence.class);
		c.createAlias("etudiant", "e");
		c.createAlias("matiere", "m");
		c.add(Restrictions.eq("m.libelle", matiere));
		c.add(Restrictions.eq("e.groupe.id", groupe));

		if (c.list().size() == 0) {
			System.out.println("requete null");
			session.getTransaction().commit();
			session.close();
			return null;
		}

		else {
			List<Absence> ret = c.list();
			session.getTransaction().commit();
			session.close();
			return ret;
		}

	}

	@Override
	public List<Absence> getAbsenceForResponsable(int cin, String matiere) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria c = session.createCriteria(Absence.class);
		c.createAlias("etudiant", "e");
		c.createAlias("matiere", "m");
		c.add(Restrictions.eq("m.libelle", matiere));
		c.add(Restrictions.eq("e.cin", cin));
		if (c.list().size() == 0) {
			System.out.println("requete null");
			session.getTransaction().commit();
			session.close();
			return null;
		}

		else {
			List<Absence> ret = c.list();
			session.getTransaction().commit();
			session.close();
			return ret;
		}
	}
/*
    @Override
    public List<ImprimeModel> imprimer(String matiere, Long groupe) {
    
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	Session session = sessionFactory.openSession();
	session.beginTransaction();

	List<ImprimeModel> imprimeListe=new ArrayList<>() ;
	Query q = session.createQuery("select count(distinct a.date),a.etudiant.id,a.etudiant.cin,a.etudiant.nom "
	+ "a.etudiant.prenom from Absence a "
                +"where a.matiere.libelle=:x  and a.etudiant.groupe.id=:y  group by a.etudiant.id");
 q.setParameter("x", matiere);
 q.setParameter("y", groupe);
		List<Object[]> rows = q.list();

		for (Object[] row : rows) {
			System.out.println(" implAbsence : "+row[0] + " " + row[1] + " " + row[2] + " " + row[3]);

			if ((Integer.parseInt(row[0].toString())) > 2)
				imprimeListe.add(new ImprimeModel(row[2].toString(),row[3].toString()));

		}

		session.getTransaction().commit();
		session.close();
		if(mailListe.size()==0)
		return null;
		else{
			for (MailClass mc : mailListe) {
				System.out.println("list mail + matiere : "+ mc.getMail()+"  mail : "+mc.getMatiere());
				
			}
			
			return mailListe;
		}
    

	
}
  */  

    @Override
    public List<ImprimeModel> imprimer(String matiere, Long groupe) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

