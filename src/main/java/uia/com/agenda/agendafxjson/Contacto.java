package uia.com.agenda.agendafxjson;

public class Contacto extends InfoAgenda {

    private String email="";
    private String telefono="";

    public Contacto(int id, String name, String fecha, String email, String telefono) {
        super(id, name, fecha);
        this.email=email;
        this.telefono=telefono;
    }

    public Contacto() {
    }

    public Contacto(ContactoDTO contactoDTO)
    {
        super(contactoDTO.getId(), contactoDTO.getName(), contactoDTO.getFecha());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
