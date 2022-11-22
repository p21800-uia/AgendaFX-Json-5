package uia.com.agenda.agendafxjson;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a contactoDTO.
 */
public class ContactoEdicionDialogoController {

    @FXML
    private TextField tipoField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField fechaField;
    @FXML
    private TextField fechaRecordatorioField;


    private Stage dialogStage;
    private boolean okClicked = false;
    private RecordatorioEdicionDialogoController controllerRecordatorio = null;
    private AgendaFXController controllerAgenda=null;
    private ContactoDTO contactoActualDTO;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the contactoDTO to be edited in the dialog.
     *
     * @param contactoDTO
     */
    public void setContacto(ContactoDTO contactoDTO)
    {
        this.contactoActualDTO = contactoDTO;
        nameField.setText(this.contactoActualDTO.getname());
        fechaField.setText(this.contactoActualDTO.getFecha());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked()
    {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid())
        {
            this.contactoActualDTO = new ContactoDTO();
            this.contactoActualDTO.setname(nameField.getText());
            this.contactoActualDTO.setFecha(fechaField.getText());
            okClicked = true;
            this.dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (tipoField.getText() == null || tipoField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (fechaField.getText() == null || fechaField.getText().length() == 0) {
            errorMessage += "No valid fecha!\n";
        }

        if (fechaRecordatorioField.getText() == null || fechaRecordatorioField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public void setControllerRecordatorio(RecordatorioEdicionDialogoController controllerRecordatorio)
    {
        this.controllerRecordatorio = controllerRecordatorio;
    }

    public void setControllerAgenda(AgendaFXController controllerAgenda)
    {
        this.controllerAgenda = controllerAgenda;
    }

    public void setContactoActualDTO(ContactoDTO contactoActual) {
        this.contactoActualDTO = contactoActual;
    }

    public ContactoDTO getContactoActualDTO() {
        return contactoActualDTO;
    }

    public void limpiaFX() {
        this.fechaField.setText("");
        this.tipoField.setText("");
        this.nameField.setText("");
        this.fechaRecordatorioField.setText("");
    }
}

