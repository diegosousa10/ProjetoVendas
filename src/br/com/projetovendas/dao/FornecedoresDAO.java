/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetovendas.dao;

import br.com.projetovendas.jdbc.ConnectionFactory;
import br.com.projetovendas.model.Fornecedores;
import br.com.projetovendas.view.utilitarios.WebServiceCep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ederc
 */
public class FornecedoresDAO {

    private final Connection con;

    public FornecedoresDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void cadastrarFornecedores(Fornecedores obj) {
        //primeiro passo criar comando sql
        try {
            String sql = "insert into tb_fornecedores(nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + " values(?,?,?,?,?,?,?,?,?,?,?,?)";
            //Segundo Passo conectar o banco de dados e organizar o comando.
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            //Terceiro passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar! Motivo: " + erro);
        }
    }

    public List<Fornecedores> listarFornecedores() {

        try {
            //1 passo criar lista
            List<Fornecedores> lista = new ArrayList<>();
            //2 passo criar comando sql, organizar e executar.
            String sql = "select * from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);

            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public void alterarFornecedores(Fornecedores obj) {
        //primeiro passo criar comando sql
        try {
            String sql = "update tb_fornecedores set nome=?, cnpj=?, email=?, telefone=?, "
                    + "celular=?, cep=?, endereco=?, numero=?, complemento=?, bairro=?, cidade=?, estado=? where id=?";
            //Segundo Passo conectar o banco de dados e organizar o comando.
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            stmt.setInt(13, obj.getId());
            //Terceiro passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar registro! Motivo: " + erro);
        }
    }

    public void excluirFornecedores(Fornecedores obj) {
        //primeiro passo criar comando sql
        try {
            String sql = "delete from tb_fornecedores where id = ?";

            //Segundo Passo conectar o banco de dados e organizar o comando.
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //Terceiro passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir! Motivo: " + erro);
        }
    }

    public Fornecedores consultaPorNome(String nome) {
        try {
            String sql = "select * from tb_fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Fornecedores obj = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
            }
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado.");
            return null;
        }
    }

    public List<Fornecedores> buscaFornecedoresPorNome(String nome) {

        try {
            //1 passo criar lista
            List<Fornecedores> lista = new ArrayList<>();
            //2 passo criar comando sql, organizar e executar.
            String sql = "select * from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedores obj = new Fornecedores();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);

            }
            return lista;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }

    public Fornecedores buscaCep(String cep) {

        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);

        Fornecedores obj = new Fornecedores();

        if (webServiceCep.wasSuccessful()) {
            obj.setEndereco(webServiceCep.getLogradouroFull());
            obj.setCidade(webServiceCep.getCidade());
            obj.setBairro(webServiceCep.getBairro());
            obj.setEstado(webServiceCep.getUf());
            return obj;
        } else {
            JOptionPane.showMessageDialog(null, "Erro numero: " + webServiceCep.getResulCode());
            JOptionPane.showMessageDialog(null, "Descrição do erro: " + webServiceCep.getResultText());
            return null;
        }

    }

}
