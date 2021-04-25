package com.api.dev.apicontabil.model;

import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "LIVRO_CAIXA")
public class LivroCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotNull(message = "Não se pode criar um livro para um cliente de id nulo")
    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name = "client")
    private Client client;

    @CreationTimestamp
    @Column(name = "dataLancamento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;

    @NotBlank(message = "Campo 'Descricao' não pode receber valor em branco ou 'nulo'")
    @Column(name = "descricao",length = 50, nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 1, nullable = false)
    private tipo tipo;


    @Column(name = "valor", length = 13, scale = 2,nullable = false)
    private Double valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public com.api.dev.apicontabil.model.tipo getTipo() {
        return tipo;
    }

    public void setTipo(com.api.dev.apicontabil.model.tipo tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @PrePersist
    protected void onCreate() {
        dataLancamento = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dataLancamento  = new Date();
    }
}
