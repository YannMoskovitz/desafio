package com.api.dev.apicontabil.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "LIVRO_CAIXA")
public class LivroCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private Date dataLancamento;

    @Column(name ="descricao" ,length =50 ,nullable = false)
    private String descricao;

    @Column(name ="tipo" ,length =1 ,nullable = false)
    private char tipo;

    @Column(name ="valor" ,length =13 ,nullable = false)
    private double valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client_id;

    public LivroCaixa() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Client getClient_id() {
        return client_id;
    }

    public void setClient_id(Client client_id) {
        this.client_id = client_id;
    }
}
