package uia.com.agenda.agendafxjson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AgendaFXController implements Initializable {

    @FXML private TableView<ContactoDTO> table;
    @FXML private TableColumn<ContactoDTO, String> tipo;
    @FXML private TableColumn<ContactoDTO, String> name;
    @FXML private TableColumn<ContactoDTO, String> fechaRecordatorio;
    @FXML private TableColumn<ContactoDTO, String> fecha;
    @FXML
    private Label tipoLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label fechaLabel;
    @FXML
    private Label fechaRecordatorioLabel;

    // Reference to the main application.
    private AgendaFXApplication mainApp;

    public ObservableList<ContactoDTO> list;

    public ObservableList<RecordatorioDTO> listRecordatorios = FXCollections.observableArrayList();

    @FXML
    private TableColumn fechaRecordatorioCol;
    @FXML
    private Label fechaLabelRecordatorio;
    @FXML
    private Button nuevoBoton;
    @FXML
    private TableColumn fechaRecordatorioRecordatorioCol;
    @FXML
    private Button nuevoBotonRecordatorio;
    @FXML
    private TableView tableRecordatorio;
    @FXML
    private TableColumn nameRecordatorioCol;
    @FXML
    private Label nameLabelRecordatorio;
    @FXML
    private Label fechaRecordatorioLabelRecordatorio;
    @FXML
    private TableColumn tipoRecordatorioCol;
    @FXML
    private Label tipoLabelRecordatorio;
    private Agenda miAgenda;
    @FXML
    private Label nombreContactoActual;
    private ContactoDTO contactoActualDTO;
    private RecordatorioDTO recordatorioActualDTO=null;
    private ContactoEdicionDialogoController controllerContacto = null;
    private RecordatorioEdicionDialogoController  controllerRecordatorio = null;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        name.setCellValueFactory(new PropertyValueFactory<ContactoDTO, String>("name"));
        fecha.setCellValueFactory(new PropertyValueFactory<ContactoDTO, String>("fecha"));
        table.setItems(list);

        // Clear person details.
        showContactoDetails(null);

        // Listen for selection changes and show the person details when changed.
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContactoDetails((ContactoDTO) newValue));

        tipoRecordatorioCol.setCellValueFactory(new PropertyValueFactory<RecordatorioDTO, String>("tipo"));
        nameRecordatorioCol.setCellValueFactory(new PropertyValueFactory<RecordatorioDTO, String>("name"));
        fechaRecordatorioRecordatorioCol.setCellValueFactory(new PropertyValueFactory<RecordatorioDTO, String>("fechaRecordatorio"));
        fechaRecordatorioCol.setCellValueFactory(new PropertyValueFactory<RecordatorioDTO, String>("fecha"));
        tableRecordatorio.setItems(listRecordatorios);

        // Clear person details.
        showRecordatorioDetails(null);

        // Listen for selection changes and show the person details when changed.
        tableRecordatorio.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue2) -> showRecordatorioDetails((RecordatorioDTO) newValue2));

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(AgendaFXApplication mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        table.setItems(list);
        tableRecordatorio.setItems(listRecordatorios);
    }




    private void showContactoDetails(ContactoDTO contactoDTO)
    {
        this.contactoActualDTO = contactoDTO;
        if (contactoDTO != null) {
            // Fill the labels with info from the contactoDTO object.
            nameLabel.setText(this.contactoActualDTO.getname());
            fechaLabel.setText(this.contactoActualDTO.getFecha());
            this.mainApp.setContactoActual(contactoDTO);
        } else {
            // ContactoDTO is null, remove all the text.
            tipoLabel.setText("");
            nameLabel.setText("");
            fechaRecordatorioLabel.setText("");
            fechaLabel.setText("");
        }
    }




    private void showRecordatorioDetails(RecordatorioDTO recordatorio) {
        if (recordatorio != null) {
            // Fill the labels with info from the recordatorio object.
            nombreContactoActual.setText(this.contactoActualDTO.getname());
            tipoLabelRecordatorio.setText(recordatorio.getTipo());
            nameLabelRecordatorio.setText(recordatorio.getName());
            fechaRecordatorioLabelRecordatorio.setText(recordatorio.getFechaRecordatorio());
            fechaLabelRecordatorio.setText(recordatorio.getFecha());
        } else {
            // Recordatorio is null, remove all the text.
            tipoLabelRecordatorio.setText("");
            nameLabelRecordatorio.setText("");
            fechaRecordatorioLabelRecordatorio.setText("");
            fechaLabelRecordatorio.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewContacto() throws IOException {
        this.contactoActualDTO = new ContactoDTO();
        boolean okClicked = mainApp.showContactoEdicionDialogo();
        if (okClicked)
        {
            list.add(this.controllerContacto.getContactoActualDTO());
            this.miAgenda.agrega(this.controllerContacto.getContactoActualDTO());
            this.serializa();
            this.controllerContacto.limpiaFX();
            //this.contactoActualDTO.limpia();
        }
    }

    private void serializa() throws IOException {
        this.miAgenda.serializa();
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @Deprecated
    private void handleEditContacto() {
        ContactoDTO selectedContactoDTO = table.getSelectionModel().getSelectedItem();

        if (selectedContactoDTO != null) {
            boolean okClicked = mainApp.showContactoEdicionDialogo();
            if (okClicked) {
                showContactoDetails(selectedContactoDTO);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No ContactoDTO Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }


    @FXML
    public void handleNewRecordatorio(ActionEvent actionEvent) throws IOException {
        this.recordatorioActualDTO = new RecordatorioDTO();
        boolean okClicked = mainApp.showRecordatorioEdicionDialogo();
        if (okClicked) {
            this.contactoActualDTO.getItemsDTO().add(this.controllerRecordatorio.getRecordatorioActualDTO());
            this.miAgenda.agregaRecordatorio(this.contactoActualDTO.getName(), this.controllerRecordatorio.getRecordatorioActualDTO());
            this.serializa();
            this.controllerRecordatorio.limpiaFX();
            //listRecordatorios.add(this.controllerRecordatorio.getRecordatorioActualDTO());
        }
    }

    public void setAgenda(Agenda agenda)
    {
        miAgenda = agenda;
        list = FXCollections.observableArrayList();

        for(int i=0; i<miAgenda.getItems().size(); i++)
        {
            ContactoDTO contacto = new ContactoDTO(miAgenda.getItems().get(i));
            list.add(contacto);
            contactoActualDTO= contacto;
        }
    }

    public void setControllerRecordatorio(RecordatorioEdicionDialogoController controllerRecordatorio)
    {
        this.controllerRecordatorio=controllerRecordatorio;
    }

    public void setControllerContacto(ContactoEdicionDialogoController controllerContacto)
    {
        this.controllerContacto=controllerContacto;
    }

    public void setContactoActual(ContactoDTO contactoDTO)
    {
        this.contactoActualDTO=contactoDTO;

        for(int i=0; i<this.list.size(); i++)
        {
            if(this.contactoActualDTO == this.list.get(i))
            {
                for (int j = 0; j < miAgenda.getItems().size(); j++)
                {
                    if(miAgenda.getItems().get(j).getName().contentEquals(contactoActualDTO.getName()))
                    {
                        nombreContactoActual.setText(contactoActualDTO.getName());
                        this.listRecordatorios = contactoActualDTO.getItemsDTO();
                        tableRecordatorio.setItems(listRecordatorios);
                        break;
                    }
                }
                break;
            }
        }
    }
}