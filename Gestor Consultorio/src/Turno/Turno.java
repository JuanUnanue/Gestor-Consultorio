package Turno;

import Medico.Medico;
import Paciente.Paciente;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Turno  implements Serializable{
    private int dniPaciente;
    private int matriculaMedico;
    private LocalDateTime fechaHora;
    private boolean disponible;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Turno() {
    }

    public Turno(int dniPaciente, int matriculaMedico, LocalDateTime fechaHora, boolean disponible) {
        this.dniPaciente = dniPaciente;
        this.matriculaMedico = matriculaMedico;
        this.fechaHora = fechaHora;
        this.disponible = disponible;
    }
    public Turno(LocalDateTime fechaHora, int matriculaMedico){
        this.fechaHora = fechaHora;
        this.matriculaMedico=matriculaMedico;
        this.disponible=true;
        dniPaciente=0;
    }
    public Turno(LocalDateTime fechaHora, int matriculaMedico,boolean disponible){
        this.fechaHora = fechaHora;
        this.matriculaMedico=matriculaMedico;
        this.disponible=disponible;
        dniPaciente=0;
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

    public void setDniPaciente(int dniPaciente) {
        this.dniPaciente = dniPaciente;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaFormateada = fechaHora.format(formatter);
        return "Turno{" +
                "paciente=" + dniPaciente +
              ", medico=" + matriculaMedico +
                ", fechaHora=" + fechaFormateada +
                ", disponible=" + disponible +
                '}'+"\n";
    }
}
