/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sosa Luciano
 */
public class Venta {
    
    private int nro;
    private Date fecVenta;

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getFecVenta() {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecVenta);
    }

    public void setFecVenta(Date fecVenta) {
        this.fecVenta = fecVenta;
    }
    
}
