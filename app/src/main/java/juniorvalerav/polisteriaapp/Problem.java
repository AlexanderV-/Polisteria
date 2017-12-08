package juniorvalerav.polisteriaapp;

public class Problem {
    private String titulo;
    private String descripcion;
    private String urlImage;
    private String estatus;
    private String avenida;
    private String calle;
    private String estado;
    private int voto= 0;
    private String uid;
    private String key;

    public Problem() {}

    public Problem(String titulo, String descripcion, String urlImage, String estatus, String avenida, String calle, String estado, int voto, String uid, String key) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImage = urlImage;
        this.estatus = estatus;
        this.avenida = avenida;
        this.calle = calle;
        this.estado = estado;
        this.voto = voto;
        this.uid = uid;
        this.key = key;
    }


    public String gettitulo() {
        return titulo;
    }

    public void settitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String geturlImage() {
        return urlImage;
    }

    public void seturlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getAvenida() {
        return avenida;
    }

    public void setAvenida(String avenida) {
        this.avenida = avenida;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getVoto() {
        return voto;
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
