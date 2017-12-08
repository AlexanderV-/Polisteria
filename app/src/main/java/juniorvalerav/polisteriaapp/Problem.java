package juniorvalerav.polisteriaapp;

/**
 * Created by SNAP on 7/12/2017.
 */

public class Problem {
    private String Titulo;
    private String Descripcion;
    private String UrlImage;
    private int Voto= 0;

    

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

    public Problem(String titulo, String descripcion, String urlImage, int voto) {
        Titulo = titulo;
        Descripcion = descripcion;
        UrlImage = urlImage;
        Voto = voto;
    }
}
