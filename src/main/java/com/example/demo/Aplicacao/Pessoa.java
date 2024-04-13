package com.example.demo.Aplicacao;

import com.example.demo.Persistencia.PessoaDAO;

import java.util.ArrayList;

public class Pessoa {
    private long id;
    private String nome;
    private String senha;

    public Pessoa(){

    }
    public Pessoa(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }
    public Pessoa(long id,String nome, String senha){
        this.id = id;
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public static boolean autenticar(Pessoa p){
        Pessoa teste = PessoaDAO.buscar(p);
        return teste != null;
    }
    public static void deletar(Pessoa p){
        PessoaDAO.Deletar(p);
    }
    public static void atualizar(Pessoa p,String novonome,String novasenha){
        PessoaDAO.Atualizar(p,novonome,novasenha);
    }
    public static void cadastrar(Pessoa p){
        PessoaDAO.Cadastrar(p);
    }
    public static ArrayList<Pessoa> Listar(){
        ArrayList<Pessoa> lista = new ArrayList<>();
        lista = PessoaDAO.lista();
        return lista;
    }

}
