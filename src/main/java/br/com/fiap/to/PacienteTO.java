package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PacienteTO {
    private Long id;
    @NotBlank
    private String nome;
    @Email @NotNull
    private String email;
    private String sexo;
    @NotNull
    private TelefoneTO telefone;
    private String status;
    @PositiveOrZero
    private int consultasRestantes;
    @PositiveOrZero
    private int faltas;
    private boolean possuiDeficiencia;
    private String tipoDeficiencia;
    private boolean videoEnviado;
    @PastOrPresent
    private LocalDate dataNascimento;
    @NotNull
    private EnderecoTO endereco;
    private String preferenciaContato;
    @PastOrPresent
    private LocalDateTime dataCadastro;
    @PastOrPresent
    private LocalDateTime ultimaAtualizacao;
    @NotNull
    private AcompanhanteTO acompanhante;

    public PacienteTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public TelefoneTO getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneTO telefone) {
        this.telefone = telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getConsultasRestantes() {
        return consultasRestantes;
    }

    public void setConsultasRestantes(int consultasRestantes) {
        this.consultasRestantes = consultasRestantes;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public boolean isPossuiDeficiencia() {
        return possuiDeficiencia;
    }

    public void setPossuiDeficiencia(boolean possuiDeficiencia) {
        this.possuiDeficiencia = possuiDeficiencia;
    }

    public String getTipoDeficiencia() {
        return tipoDeficiencia;
    }

    public void setTipoDeficiencia(String tipoDeficiencia) {
        this.tipoDeficiencia = tipoDeficiencia;
    }

    public boolean isVideoEnviado() {
        return videoEnviado;
    }

    public void setVideoEnviado(boolean videoEnviado) {
        this.videoEnviado = videoEnviado;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EnderecoTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoTO endereco) {
        this.endereco = endereco;
    }

    public String getPreferenciaContato() {
        return preferenciaContato;
    }

    public void setPreferenciaContato(String preferenciaContato) {
        this.preferenciaContato = preferenciaContato;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDateTime ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public AcompanhanteTO getAcompanhante() {
        return acompanhante;
    }

    public void setAcompanhante(AcompanhanteTO acompanhante) {
        this.acompanhante = acompanhante;
    }
}
