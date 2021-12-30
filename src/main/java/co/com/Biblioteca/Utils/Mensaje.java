package co.com.Biblioteca.Utils;

import java.util.Date;

public class Mensaje {

        private boolean estado;
        private String mensaje;

    public Mensaje() {

    }
        public Mensaje(boolean estado, String mensaje) {
            this.estado = estado;
            this.mensaje = mensaje;
        }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Mensaje imprimirDisponibilidad(Boolean disponible, Date fechaPrestamo) {
        if(disponible){
            return new Mensaje(true,  "El recurso esta disponible");
        }else{
            return new Mensaje(false,"El recurso fue prestado el: "+fechaPrestamo);
        }
    }

    public Mensaje imprimiPrestamo(Boolean disponible, Date fechaPrestamo) {
        if(disponible){
            return new Mensaje(true,  "El recurso esta disponible");
        }else{
            return new Mensaje(false,"El recurso fue prestado el: "+fechaPrestamo);
        }
    }

    public Mensaje imprimirDevolucion(Boolean disponible , Date fechaPrestamo){
        if(!disponible){
            return new Mensaje(true,  "El recurso fue entregado con exito");
        }
        return new Mensaje(false,("El recurso no esta prestado" ));
    }
}
