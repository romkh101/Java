package gestionuser.controllers;

import gestionuser.entities.Actualite;
import gestionuser.entities.Categorie;
import gestionuser.services.ActualiteCRUD;
import gestionuser.services.CategorieCRUD;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

public class ShowcategorieController implements Initializable {

    private Categorie categorie = null;
    private CategorieCRUD categorieCRUD = null;
    private ObservableList<Categorie> categories = null;
    
    private static final String DIALOG_TITLE = "Modifier la catégorie";
    private static final String DIALOG_CONTENT = "Nouveau nom :";
    private static final String WARNING_TITLE = "Attention";
    private static final String WARNING_CONTENT = "La catégorie est utilisée dans une ou plusieurs actualités et ne peut pas être supprimée !";

    @FXML
    private TableView<Categorie> tvC;
    @FXML
    private TableColumn<Categorie, Integer> colid;
    @FXML
    private TableColumn<Categorie, String> colnom;
    @FXML
    private TableColumn<Categorie, Void> colsupprimer;
    @FXML
    private TableColumn<Categorie, Void> colmodifier;
    
private void handleSupprimer(ActionEvent event) {
    try {
        CategorieCRUD categorieCRUD = new CategorieCRUD();
        ActualiteCRUD actualiteCRUD = new ActualiteCRUD();
        List<Actualite> actualites = actualiteCRUD.listerActualitesParCategorie(categorie);
        
        if (actualites.isEmpty()) {
            categorieCRUD.supprimerCategorie(categorie);
            categories.remove(categorie); // enlever l'élément supprimé de la liste observable
            tvC.refresh();
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Attention");
            alert.setHeaderText(null);
            alert.setContentText("La catégorie est utilisée dans une ou plusieurs actualités et ne peut pas être supprimée !");
            alert.showAndWait();
        }
    } catch (ParseException e) {
        Logger.getLogger(ShowcategorieController.class.getName()).log(Level.SEVERE, null, e);
    }
}

private void handleModifier(ActionEvent event) {
    TextInputDialog dialog = new TextInputDialog(categorie.getNom());
    dialog.setTitle("Modifier la catégorie");
    dialog.setHeaderText(null);
    dialog.setContentText("Nouveau nom :");

    Optional<String> result = dialog.showAndWait();
    if (result.isPresent()) {
        String nouveauNom = result.get();
        categorieCRUD.modifierCategorie(categorie, nouveauNom);
        categorie.setNom(nouveauNom); // mettre à jour le nom de la catégorie dans la liste observable
        tvC.refresh();
    }
}

@Override
public void initialize(URL url, ResourceBundle rb) {
    try {
        categorieCRUD = new CategorieCRUD();

        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            
        categories = categorieCRUD.listerCategorie();
        tvC.setItems(categories);

        colmodifier.setCellFactory(param -> new TableCell<Categorie, Void>() {
            private final Button btnModifier = new Button("Modifier");

            {
                btnModifier.setOnAction((ActionEvent event) -> {
                    categorie = getTableView().getItems().get(getIndex());
                    handleModifier(event);
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnModifier);
                }
            }
        });
        colmodifier.setPrefWidth(100);

        colsupprimer.setCellFactory(param -> new TableCell<Categorie, Void>() {
            private final Button btnSupprimer = new Button("Supprimer");

            {
                btnSupprimer.setOnAction((ActionEvent event) -> {
                    categorie = getTableView().getItems().get(getIndex());
                    handleSupprimer(event);
                });
            }
  @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnSupprimer);
                }
            }
        });
        colsupprimer.setPrefWidth(100);

    } catch (ParseException ex) {
        Logger.getLogger(ShowcategorieController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}