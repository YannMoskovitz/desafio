package com.api.dev.apicontabil.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LIVRO_CAIXA")
public class LivroCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn(name = "id_client")
    @ManyToOne(optional = false)
    private Client id_client;

    @CreationTimestamp
    @Column(name = "dataLancamento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;

    @Column(name = "descricao",length = 50, nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 1, nullable = false)
    private tipo tipo;

    @Column(name = "valor", length = 13,nullable = false)
    private String valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getId_client() {
        return id_client;
    }

    public void setId_client(Client id_client) {
        this.id_client = id_client;
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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
