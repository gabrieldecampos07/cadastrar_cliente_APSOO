package br.com.apsoo.model;

import java.time.LocalDate; // Biblioteca para inserir a data de nascimento
import java.time.Period; // Biblioteca para fazer a comparação da data atual com a data de nascimento

public class Cliente {
    private Integer id;
    private String cpf;
    private String nome;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String fone;
    private String email;
    private LocalDate dataNascimento;

    public Cliente() {}

    public Cliente(Integer id, String cpf, String nome, String rua, String numero, 
                   String bairro, String cidade, String uf, String fone, 
                   String email, LocalDate dataNascimento) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.fone = fone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    // Regra de Negócio: O cliente deve ser maior de idade (18 anos ou mais)
    public boolean isMaiorDeIdade() {
        if (this.dataNascimento == null) {
            return false;
        }
        else {
            return Period.between(this.dataNascimento, LocalDate.now()).getYears() >= 18;
        }
    }

    // Métodos Getters e Setters
    public Integer getId() {
        return id; 
    }
    public void setId(Integer id) {
        this.id = id; 
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() { 
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getFone() {
        return fone;
    }
    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}