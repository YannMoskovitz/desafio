package com.api.dev.apicontabil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @CreationTimestamp
    @Column(name = "dataCadastro", nullable = false)
    private Date dataCadastro;

    @NotBlank(message = "Campo 'nome' não pode ser nulo/em branco")
    @Pattern(regexp = "[a-zA-Z]*",message = "Campo 'nome' só pode receber letras")
    @Column(name ="nome" ,length = 30 ,nullable = false)
    private String nome;

    @NotBlank(message = "Campo 'login' não pode ser nulo")
    @Column(name ="login" ,length = 15 , unique = true ,nullable = false)
    private String login;

    @NotBlank(message = "Campo 'senha' não pode ser nulo")
    @Column(name ="senha" ,length =10 ,nullable = false)
    private String senha;


    @Email(message = "endereço de e-mail inválido")
    @Column(nullable = true, name ="email" ,length =100, unique = true) //deve poder ser null
    private String email;

    @JsonProperty
    @Pattern(regexp = "[0-9]+", message = "Campo 'telefone' só aceita numeros como 'String'")
    @Column(nullable = true, name ="telefone" ,length = 11) // deve poder ser null
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name ="status", length = 1, nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name ="perfil", length = 1, nullable = false)
    private Perfil perfil;


    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @PrePersist
    protected void onCreate() {
        dataCadastro = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        dataCadastro  = new Date();
    }
}
