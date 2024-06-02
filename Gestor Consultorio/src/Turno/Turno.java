package Turno;

import Medico.Medico;
import Paciente.Paciente;

import java.time.LocalDateTime;

public class Turno {
    private int dniPaciente;
    private int matriculaMedico;
    private LocalDateTime fechaHora;
    private boolean disponible;
    //


    public Turno(LocalDateTime fechaHora ){
        this.fechaHora = fechaHora;
        disponible=true;
    }

    public Turno(LocalDateTime fechaHora, int matriculaMedico, int dniPaciente) {
        this.disponible = true;
        this.fechaHora = fechaHora;
        this.matriculaMedico=matriculaMedico;
        this.dniPaciente=dniPaciente;
    }

    public int getPaciente() {
        return dniPaciente;
    }

    public void setPaciente(int paciente) {
        this.dniPaciente = paciente;
    }

    public int getMatriculaMedico() {
        return matriculaMedico;
    }

    public void setMatriculaMedico(int matriculaMedico) {
        this.matriculaMedico = matriculaMedico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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
