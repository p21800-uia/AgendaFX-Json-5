package uia.com.agenda.agendafxjson;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ContactoDTO extends Contacto{

    private ObservableList<RecordatorioDTO> itemsDTO = FXCollections.observableArrayList();;

    public ContactoDTO(String tipo, String name, String fechaRecordatorio, String fecha) {
        super(-1, name, fecha, "", "");
    }

    public ContactoDTO() {
    }

    public ContactoDTO(InfoAgenda infoAgenda)
    {
        this.setName(infoAgenda.getName());
        this.setFecha(infoAgenda.getFecha());
        if(infoAgenda.getItems().size()>0) {
            for(int i=0; i<infoAgenda.getItems().size(); i++)
            {
                this.getItems().add(infoAgenda.getItems().get(i));
                this.getItemsDTO().add(new RecordatorioDTO(infoAgenda.getItems().get(i)));
            }
        }
    }

    @Override
    public List<InfoAgenda> getItems() {
        return super.getItems();
    }

    public void setItems(List<InfoAgenda> items) {
        super.setItems(items);
    }

    public ObservableList<RecordatorioDTO> getItemsDTO() {
        return itemsDTO;
    }

    public void setItemsDTO(ObservableList<RecordatorioDTO> itemsDTO) {
        this.itemsDTO = itemsDTO;
    }

    public String getname() {
        return super.getName();
    }

    public void setname(String text) {
        super.setName(text);
    }

}
