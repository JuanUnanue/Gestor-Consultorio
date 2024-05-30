package Modelo;

import Medico.Medico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Turno {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    private boolean disponible;
    //


    public Turno(LocalDateTime fechaHora ){
        this.fechaHora = fechaHora;
        disponible=true;
    }

    public Turno(LocalDateTime fechaHora, Medico medico, Paciente paciente) {
        this.disponible = true;
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Turno{" +
             /*   "paciente=" + paciente +
                ", medico=" + medico +*/
                ", fechaHora=" + fechaHora +
                ", disponible=" + disponible +
                '}'+"\n";
    }
}
