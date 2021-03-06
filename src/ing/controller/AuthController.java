package ing.controller;

import java.io.IOException;

import ing.DAO.ImplEnseignantDAO;
import ing.DAO.ImplEtudiantDAO;
import ing.DAO.ResponsableService;
import ing.controller.etudiant.ConsulterAbsence;
//import ing.controller.professeur.Enseignant;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AuthController {

	@FXML
        private VBox panel;
	@FXML
	private Button Conectbutton;
	@FXML
	private TextField txtIdent;
	@FXML
	private TextField txtfield;

	@FXML
	private Label lblerreur;
	@FXML
	private ComboBox<String> profileCombo;

	public TextField getTxtIdent() {
		return txtIdent;
	}

	public TextField getPassfield() {
		return txtfield;
	}

	public void connect() {

		try {
			// System.out.println(profileCombo.getValue());

			if (profileCombo.getValue().equals("Etudiant")) {
				// etudiant
				// table
				ImplEtudiantDAO e = new ImplEtudiantDAO();
				Long test = e.connecter(txtIdent.getText(), txtfield.getText());
				if (test != null) {
					Stage stage1 = (Stage) Conectbutton.getScene().getWindow();

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/ing/view/etudiant/AcuueilE.fxml"));

					Pane pane = (Pane) loader.load();

					ConsulterAbsence controller = loader.<ConsulterAbsence> getController();

					controller.setUserId((test));

					Scene scene = new Scene(pane);
					// on peut faire new stage

					stage1.setScene(scene);
					stage1.show();
				} else {

					lblerreur.setText("Etudiant doesn't exist");
					lblerreur.setVisible(true);

				}

			} else if (profileCombo.getValue().equals("Enseignant")) {
				ImplEnseignantDAO e = new ImplEnseignantDAO();
				Long test = e.connect(txtIdent.getText(), txtfield.getText());
				if (test != null) {
					Stage stage1 = (Stage) Conectbutton.getScene().getWindow();


					FXMLLoader loader = new FXMLLoader(getClass().getResource("/ing/view/professeur/pageEnseignant.fxml"));

					Pane pane = (Pane) loader.load();

//					 Enseignant controller = loader.<Enseignant>getController();

					// controller.setUserId(test);

					Scene scene = new Scene(pane);
					// on peut faire new stage

					stage1.setScene(scene);
					stage1.show();
				} else {

					lblerreur.setVisible(true);

				}

			} else if (profileCombo.getValue().equals("Responsable")) {
				ResponsableService e = new ResponsableService();
				Long test = e.connect(txtIdent.getText(), txtfield.getText());
				if (test != null) {
					Stage stage1 = (Stage) Conectbutton.getScene().getWindow();

					FXMLLoader loader = new FXMLLoader(getClass().getResource("/ing/view/responsable/AccueilR.fxml"));

					Pane pane = (Pane) loader.load();

					

					Scene scene = new Scene(pane);
					// on peut faire new stage

					stage1.setScene(scene);
					stage1.show();
				} else {

					lblerreur.setText("Responsable doesn't exist");
					lblerreur.setVisible(true);

				}

			} else if (profileCombo.getValue() == null) {
				lblerreur.setText("Choisissez votre profile");
				lblerreur.setVisible(true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
