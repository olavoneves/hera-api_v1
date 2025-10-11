package br.com.fiap.to;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConsultaTO {
    private Long id;
    @NotNull
    private PacienteTO paciente;
    @NotNull
    private MedicoTO medico;
    @FutureOrPresent
    private LocalDate dataConsulta;
    @FutureOrPresent
    private LocalTime horarioConsulta;
    private String status;
    private String tipoConsulta;
    private String observacoes;
    @NotNull
    private String linkTeleconsulta;

    public ConsultaTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteTO getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteTO paciente) {
        this.paciente = paciente;
    }

    public MedicoTO getMedico() {
        return medico;
    }

    public void setMedico(MedicoTO medico) {
        this.medico = medico;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHorarioConsulta() {
        return horarioConsulta;
    }

    public void setHorarioConsulta(LocalTime horarioConsulta) {
        this.horarioConsulta = horarioConsulta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getLinkTeleconsulta() {
        return linkTeleconsulta;
    }

    public void setLinkTeleconsulta(String linkTeleconsulta) {
        this.linkTeleconsulta = linkTeleconsulta;
    }
}
