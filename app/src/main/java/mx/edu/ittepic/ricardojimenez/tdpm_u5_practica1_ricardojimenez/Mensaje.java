package mx.edu.ittepic.ricardojimenez.tdpm_u5_practica1_ricardojimenez;

import java.util.Date;

public class Mensaje {
    String origen,cuerpo;
    Date fecha;

    public Mensaje(String origen, Date fecha, String cuerpo) {
        this.origen = origen;
        this.fecha = fecha;
        this.cuerpo = cuerpo;
    }

    public String getOrigen() {
        return origen;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCuerpo() {
        return cuerpo;
    }
}

