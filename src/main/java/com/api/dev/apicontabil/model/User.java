package com.api.dev.apicontabil.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @CreationTimestamp
    @Column(name = "dataCadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @NotBlank
    @Column(name ="nome" ,length =30 ,nullable = false)
    private String nome;

    @Column(name ="login" ,length =15 ,nullable = false)
    private String login;

    @Column(name ="senha" ,length =10 ,nullable = false)
    private String senha;

    @JsonProperty
    @Email
    @Column(nullable = true, name ="email" ,length =100) //deve poder ser null
    private String email;

    @JsonProperty
    @Pattern(regexp = "[0-9]+")
    @Column(nullable = true, name ="telefone" ,length = 11) // deve poder ser null
    private String telefone;

    @Column(name ="status", length = 1)
    private char status;

    @Column(name ="perfil", length = 1)
    private char perfil;


    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getPerfil() {
        return perfil;
    }

    public void setPerfil(char perfil) {
        this.perfil = perfil;
    }
}
