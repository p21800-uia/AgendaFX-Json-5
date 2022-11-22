package uia.com.agenda.agendafxjson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.IOException;

public class AgendaFXApplication extends Application {

    private static Agenda miAgenda;
    private Stage primaryStage;
    private BorderPane layoutRaiz;
    private ContactoEdicionDialogoController controllerContacto = null;
    private ContactoDTO contactoActual=null;
    private Stage dialogStageContacto = null;
    private FXMLLoader loaderContacto = null;
    private AgendaFXController controllerAgenda = null;
    private RecordatorioEdicionDialogoController  controllerRecordatorio = null;
    private Stage dialogStageRecordatorio = null;
    private FXMLLoader loaderRecordatorio = null;

    public  Window getPrimaryStage() {
        return this.primaryStage;
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Agenda");
        initLayoutRaiz();
        showContactoOverview();
    }
    /**
     * Initializes the root layout.
     */
    public void initLayoutRaiz() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AgendaFXApplication.class.getResource("layoutRaizFX.fxml"));
            layoutRaiz = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(layoutRaiz);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showContactoEdicionDialogo() {
        try {
            if(dialogStageContacto == null) {
                // Load the fxml file and create a new stage for the popup dialog.
                this.loaderContacto = new FXMLLoader();
                this.loaderContacto.setLocation(AgendaFXApplication.class.getResource("ContactoEdicionDialogo.fxml"));
                AnchorPane page = (AnchorPane) this.loaderContacto.load();

                // Create the dialog Stage.
                this.dialogStageContacto = new Stage();
                this.dialogStageContacto.setTitle("Edición de ContactoDTO");
                this.dialogStageContacto.initModality(Modality.WINDOW_MODAL);
                this.dialogStageContacto.initOwner(primaryStage);
                Scene scene = new Scene(page);
                this.dialogStageContacto.setScene(scene);
            }

            if(this.controllerContacto == null) {
                this.controllerContacto =this.loaderContacto.getController();
                this.controllerContacto.setControllerAgenda(this.controllerAgenda);
                this.controllerAgenda.setControllerContacto(this.controllerContacto);
            }
            // Set the newContactoDTO into the controller.
            this.controllerContacto.setDialogStage(this.dialogStageContacto);
            this.controllerContacto.setContactoActualDTO(contactoActual);

            // Show the dialog and wait until the user closes it
            this.dialogStageContacto.showAndWait();

            return this.controllerContacto.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showRecordatorioEdicionDialogo() {
        try {
            if(this.dialogStageRecordatorio == null) {
                // Load the fxml file and create a new stage for the popup dialog.
                this.loaderRecordatorio = new FXMLLoader();
                this.loaderRecordatorio.setLocation(AgendaFXApplication.class.getResource("RecordatorioEdicionDialogo.fxml"));
                AnchorPane page = (AnchorPane) this.loaderRecordatorio.load();

                // Create the dialog Stage.
                this.dialogStageRecordatorio = new Stage();
                this.dialogStageRecordatorio.setTitle("Edición de RecordatorioOld");
                this.dialogStageRecordatorio.initModality(Modality.WINDOW_MODAL);
                this.dialogStageRecordatorio.initOwner(primaryStage);
                Scene scene = new Scene(page);
                this.dialogStageRecordatorio.setScene(scene);
            }

            // Set the newRecordatorioOld into the controller.
            if(this.controllerRecordatorio == null)
            {
                this.controllerRecordatorio = this.loaderRecordatorio.getController();
                this.controllerAgenda.setControllerRecordatorio(this.controllerRecordatorio);
            }
            this.controllerRecordatorio.setDialogStage(this.dialogStageRecordatorio);
            this.controllerRecordatorio.setRecordatorios(this.contactoActual);

            // Show the dialog and wait until the user closes it
            this.dialogStageRecordatorio.showAndWait();

            return this.controllerRecordatorio.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Shows the contacto overview inside the root layout.
     */
    public void showContactoOverview() {
        try {
            // Load contacto overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(AgendaFXApplication.class.getResource("AgendaFX.fxml"));
            AnchorPane contactoOverview = (AnchorPane) loader.load();

            // Set contacto overview into the center of root layout.
            layoutRaiz.setCenter(contactoOverview);

            // Give the controller access to the main app.
            this.controllerAgenda = loader.getController();
            this.controllerAgenda.setAgenda(miAgenda);
            this.controllerAgenda.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) throws IOException {
        System.out.println("Hola UIA");

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            miAgenda = mapper.readValue(new FileInputStream("miAgenda-2.json"), Agenda.class);
        }catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        launch();


    }

    public void setContactoActual(ContactoDTO contactoDTO)
    {
        this.contactoActual = contactoDTO;
        this.controllerAgenda.setContactoActual(contactoDTO);
    }
}