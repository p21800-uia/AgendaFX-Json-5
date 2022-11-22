package uia.com.agenda.agendafxjson;

public class Recordatorio extends InfoAgenda {

    private String fechaRecordatorio="";



    private String tipo="";

    public Recordatorio(int id, String fechaRecordatorio, String name, String fecha, String email, String telefono) {
        super(id, name, fecha);
        this.fechaRecordatorio=fechaRecordatorio;
    }

    public Recordatorio() {
    }

    public Recordatorio(RecordatorioDTO recDTO)
    {
        super(recDTO.getId(), recDTO.getName(), recDTO.getFecha());
        this.fechaRecordatorio=recDTO.getFechaRecordatorio();
    }

    public String getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public void setFechaRecordatorio(String fechaRecordatorio) {
        this.fechaRecordatorio = fechaRecordatorio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
