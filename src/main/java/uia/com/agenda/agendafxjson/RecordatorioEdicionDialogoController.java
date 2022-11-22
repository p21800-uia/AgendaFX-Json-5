package uia.com.agenda.agendafxjson;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecordatorioEdicionDialogoController {
    @FXML
    public TextField tipoRecordatorioField;
    @FXML
    public TextField nameRecordatorioField;
    @FXML
    public TextField fechaRecordatorioField;
    @FXML
    public TextField fechaRecordatorioRecordatorioField;
    @FXML
    public Button okRecordatorioBoton;

    private Stage dialogStage;
    private RecordatorioDTO recordatorioActualDTO;
    private boolean okClicked = false;

    @FXML
    public void handleRecordatorioOk(ActionEvent actionEvent) {
        if (isInputValid())
        {
            this.recordatorioActualDTO = new RecordatorioDTO();
            this.recordatorioActualDTO.setTipo(tipoRecordatorioField.getText());
            this.recordatorioActualDTO.setName(nameRecordatorioField.getText());
            this.recordatorioActualDTO.setFecha(fechaRecordatorioField.getText());
            this.recordatorioActualDTO.setFechaRecordatorio(fechaRecordatorioRecordatorioField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (tipoRecordatorioField.getText() == null || tipoRecordatorioField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (nameRecordatorioField.getText() == null || nameRecordatorioField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (fechaRecordatorioField.getText() == null || fechaRecordatorioField.getText().length() == 0) {
            errorMessage += "No valid fecha!\n";
        }

        if (fechaRecordatorioRecordatorioField.getText() == null || fechaRecordatorioRecordatorioField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }



    /**
     * Sets the recordatorioActualDTO to be edited in the dialog.
     *
     * @param recordatorioActualDTO
     */
    public void setRecordatorio(RecordatorioDTO recordatorioActualDTO)
    {
        this.recordatorioActualDTO = recordatorioActualDTO;

        tipoRecordatorioField.setText(recordatorioActualDTO.getTipo());
        nameRecordatorioField.setText(recordatorioActualDTO.getName());
        fechaRecordatorioField.setText(recordatorioActualDTO.getFecha());
        fechaRecordatorioRecordatorioField.setText(recordatorioActualDTO.getFechaRecordatorio());
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

    public void setRecordatorios(ContactoDTO contactoActual)
    {

    }

    public RecordatorioDTO getRecordatorioActualDTO() {
        return recordatorioActualDTO;
    }

    public void setRecordatorioActualDTO(RecordatorioDTO recordatorioActualDTO) {
        this.recordatorioActualDTO = recordatorioActualDTO;
    }

    public void limpiaFX() {
        this.fechaRecordatorioField.setText("");
        this.tipoRecordatorioField.setText("");
        this.nameRecordatorioField.setText("");
        this.fechaRecordatorioRecordatorioField.setText("");
    }
}
