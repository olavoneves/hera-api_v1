package br.com.fiap.to;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class RemedioTO {
    private Long id;
    @NotBlank
    private String nome;
    @PositiveOrZero @NotNull
    private Double preco;
    @PastOrPresent
    private LocalDate dataFabricacao;
    @FutureOrPresent
    private LocalDate dataValidade;

    public RemedioTO() {
    }

    public RemedioTO(Long id, String nome, Double preco, LocalDate dataFabricacao, LocalDate dataValidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.dataFabricacao = dataFabricacao;
        this.dataValidade = dataValidade;
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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }
}
