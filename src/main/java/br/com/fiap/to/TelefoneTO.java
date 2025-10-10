package br.com.fiap.to;

import jakarta.validation.constraints.NotNull;

public class TelefoneTO {
    private Long id;
    @NotNull
    private String ddd;
    @NotNull
    private String numero;
    private String tipoDeTelefone;

    public TelefoneTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipoDeTelefone() {
        return tipoDeTelefone;
    }

    public void setTipoDeTelefone(String tipoDeTelefone) {
        this.tipoDeTelefone = tipoDeTelefone;
    }
}
