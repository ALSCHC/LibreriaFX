package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sosa Luciano
 */
public class Libro {
    
    private int nro;
    private String nombre;
    private String autor;
    private Categoría categoria;
    private Proveedor proveedor;
    private String paginas;
    private Date fecPublicacion;
    private String precio;
    private String stock;
    private Boolean disponib;

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Categoría getCategoria() {
        return categoria;
    }
    
    public String getCateg() {
        return categoria.getCategoria();
    }

    public void setCategoria(Categoría categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }
    
    public String getEditorial() {
        return proveedor.getProveedor();
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }
    
    public String getFecPublicacion() {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecPublicacion);
    }

    public void setFecPublicacion(Date fecPublicacion) {
        this.fecPublicacion = fecPublicacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDisponib() {
        return disponib ? "Si" : "No";
    }

    public void setDisponib(Boolean disponib) {
        this.disponib = disponib;
    }
}