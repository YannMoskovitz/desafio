package com.api.dev.apicontabil.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp
    @Column(name = "dataCadastro", nullable = false)
    private Date dataCadastro;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<LivroCaixa> livroCaixas;

    @NotBlank(message = "Campo 'nome' não pode receber valores 'branco' ou 'nulo'")
    @Pattern(regexp = "[a-zA-Z]*", message = "Campo 'nome' só pode receber valores do tipo String")
    @Column(name = "nome",length = 30, nullable = false)
    private String nome;

    @Pattern(regexp = "[0-9]+", message = "Campo 'cpfCnpj' só pode receber numeros como valores do tipo String")
    @Size(min = 11, max = 14, message = "cpf ou cnpj digitado é inválido")
    @Column(name = "cpfCnpj",length = 14, nullable = false)
    private String cpfCnpj;

    @Pattern(regexp = "[a-zA-Z]*",message = "Campo 'logradouro' só pode receber letras")
    @Column(name = "logradouro",length = 50, nullable = false)
    private String logradouro;

    @Pattern(regexp = "[a-zA-Z]*", message = "Campo 'cidade' só pode receber letras")
    @Column(name = "cidade",length = 40, nullable = false)
    private String cidade;

    @Pattern(regexp = "[A-Z]*", message = "Campo 'UF' só pode receber letras")
    @Size(min = 2, max = 2, message = "UF inválida")
    @Column(name = "uf",length = 2, nullable = false)
    private String uf;

    @Pattern(regexp = "[0-9]*", message = "cep só pode aceita numeros como 'Strings'")
    @Size(min = 8, max = 8, message = "Numero de cep inválido")
    @Column(name = "cep",length = 8, nullable = false)
    private String cep;


    @Pattern(regexp = "[0-9]+", message = "Campo 'telefone' só aceita numeros como 'String'")
    @Size(min = 9, max = 11, message = "Numero de telefone invalido")
    @Column(name = "telefone",length = 11, nullable = true)
    private String telefone;

    @NotNull(message = "e-mail não pode ser nulo")
    @Email(message = "endereço de e-mail inválido")
    @Column(name = "email",length = 100, nullable = true)
    private String email;

    public Client() {
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PrePersist
    protected void onCreate() { dataCadastro = new Date();    }

    @PreUpdate
    protected void onUpdate() {
        dataCadastro  = new Date();
    }


}
