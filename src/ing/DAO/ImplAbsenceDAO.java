/**
 * 
 */
package ing.DAO;

import java.util.List;


import ing.entities.Absence;

/**
 * @author yaya
 *
 */
public class ImplAbsenceDAO implements IAbsenceDAO {


	@Override
	public List<Absence> listeAbsence(long idEtudiant) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see ing.DAO.IAbsenceDAO#ajouterAbsence(ing.entities.Absence, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public boolean ajouterAbsence(Absence abs, Long idEns, Long idMat, Long idEtud) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see ing.DAO.IAbsenceDAO#annulerAbsence(java.lang.Long)
	 */
	@Override
	public boolean annulerAbsence(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see ing.DAO.IAbsenceDAO#listmail()
	 */
	@Override
	public List<Absence> listmail() {
		// TODO Auto-generated method stub
		return null;
	}

}
