package com.example.demo.Persistencia;

import com.example.demo.Aplicacao.Pessoa;

import java.sql.*;
import java.util.ArrayList;

public class PessoaDAO {
    private static final String inserir="INSERT INTO pessoa(nome,senha) VALUES(?,?)";
    private static final   String buscar = "SELECT * FROM pessoa WHERE nome = ? AND senha = ? ";

    private static final   String Listagem = "SELECT * FROM pessoa";

    private static final String deletar = "DELETE FROM pessoa WHERE nome = ? AND senha = ?";

    private  static final String atualizar = "UPDATE pessoa SET nome = ?,senha = ? WHERE nome = ? AND senha = ?";

    private static  final String buscarId = "Select * FROM pessoa WHERE id = ?";
    public static ArrayList<Pessoa> lista(){
        Connection connection = null;
        ArrayList<Pessoa> Lista = new ArrayList<>();
        ResultSet rs;
        Statement stmt;
        try{
            connection = Conexao.getConnection();
            stmt = (Statement) connection.createStatement();
            rs = stmt.executeQuery(Listagem);
            while (rs.next()){
                Pessoa pessoa = new Pessoa(rs.getLong("id"),rs.getString("nome"),rs.getString("senha"));
                Lista.add(pessoa);
            }
            connection.close();
        }catch (SQLException e){
            System.out.println("Erro SQL");
        }catch (Exception e){
            System.out.println("Erro generico");
        }
        return Lista;
    }
    public static void Cadastrar(Pessoa p){
        Connection connection = null;
        //ResultSet rs;
        PreparedStatement instrucao;

        try{
            connection = Conexao.getConnection();
            instrucao = connection.prepareStatement(inserir);
            instrucao.setString(1,p.getNome());
            instrucao.setString(2,p.getSenha());

            instrucao.executeQuery();
            instrucao.close();
            connection.close();
        }catch (SQLException e){
            System.out.println("Deu Prego"+e);
        }catch (Exception e){
            System.out.println("Deu Erro sem ser no SQL");
        }
    }
    public static void Deletar(Pessoa p){
        Connection connection = null;
        PreparedStatement instrucao;
        Pessoa teste = PessoaDAO.buscar(p);
        try{
            if (teste != null){
                connection = Conexao.getConnection();
                instrucao = connection.prepareStatement(deletar);
                instrucao.setString(1,p.getNome());
                instrucao.setString(2, p.getSenha());
                instrucao.executeQuery();
                instrucao.close();
                connection.close();
            }else {
                System.out.println("Esse usuario não existe");
            }
        }catch (SQLException e){
            System.out.println("Erro no SQL");
        }catch (Exception e){
            System.out.println("Erro generico");
        }

    }
    public static void Atualizar(Pessoa p , String novonome,String novasenha){
        Connection connection = null;
        PreparedStatement instrucao;
        Pessoa teste = PessoaDAO.buscar(p);
        try{
            if (teste != null){
                connection = Conexao.getConnection();
                instrucao = connection.prepareStatement(atualizar);
                instrucao.setString(1,novonome);
                instrucao.setString(2, novasenha);
                instrucao.setString(3, p.getNome());
                instrucao.setString(4,p.getSenha());
                instrucao.executeQuery();
                instrucao.close();
                connection.close();
            }else {
                System.out.println("Esse usuario não existe");
            }
        }catch (SQLException e){
            System.out.println("Erro no SQL abc");
        }catch (Exception e){
            System.out.println("Erro generico");
        }
    }
    public static Pessoa buscar(Pessoa p){
        Connection connection = null;
        PreparedStatement instrucao;
        ResultSet rs;
        Pessoa correto = null;
        try{

                connection = Conexao.getConnection();
                instrucao = connection.prepareStatement(buscar);
                instrucao.setString(1,p.getNome());
                instrucao.setString(2, p.getSenha());
                rs = instrucao.executeQuery();
                if(rs.next()){
                     correto = new Pessoa(rs.getString("nome"),rs.getString("senha"));
                }
                instrucao.close();
                connection.close();



        }catch (SQLException e){
            System.out.println("Erro no SQL");
        }catch (Exception e){
            System.out.println("Erro generico");
        }
        return correto;

    }
    public static Pessoa buscar(long id){
        Connection connection = null;
        PreparedStatement instrucao;
        ResultSet rs;
        Pessoa correto = null;
        try{

            connection = Conexao.getConnection();
            instrucao = connection.prepareStatement(buscarId);
            instrucao.setLong(1,id);

            rs = instrucao.executeQuery();
            if(rs.next()){
                correto = new Pessoa(rs.getLong("id"),rs.getString("nome"),rs.getString("senha"));
            }
            instrucao.close();
            connection.close();



        }catch (SQLException e){
            System.out.println("Erro no SQL");
        }catch (Exception e){
            System.out.println("Erro generico");
        }
        return correto;

    }
}