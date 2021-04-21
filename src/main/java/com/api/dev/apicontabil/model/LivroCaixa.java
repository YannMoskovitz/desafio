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




}
