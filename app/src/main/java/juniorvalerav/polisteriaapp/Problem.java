package juniorvalerav.polisteriaapp;

/**
 * Created by SNAP on 7/12/2017.
 */

public class Problem {
    private String Titulo;
    private String Descripcion;
    private String UrlImage;
    private int Voto= 0;
    private String uid;

    public String getUid() {
        return uid;
    }

    public Problem(String titulo, String descripcion, String urlImage, int voto) {
        Titulo = titulo;
        Descripcion = descripcion;
        UrlImage = urlImage;
        Voto = voto;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Problem(String titulo, String descripcion, String urlImage, int voto, String uid) {
        Titulo = titulo;
        Descripcion = descripcion;
        UrlImage = urlImage;
        Voto = voto;
        this.uid = uid;
    }

    public Problem(){}

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getUrlImage() {
        return UrlImage;
    }

    public void setUrlImage(String urlImage) {
        UrlImage = urlImage;
    }

    public int getVoto() {
        return Voto;
    }

    public void setVoto(int voto) {
        Voto = voto;
    }

}
