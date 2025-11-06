package br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.model;

public class Produto {
    //atributo
    private String nome;
    private int quantidade;
    private String identificacao;
    private double valor;
    private int idMarca;
    //obs: String é uma classe e int já é algo primitivo do java


    //métodos


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception{
        if (nome.length() > 3) {
            this.nome = nome;
        } else {
            throw new Exception("Nome muito curto!");
          //  this.nome = "Inválido";
        }
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        if (quantidade > 0) {
            this.quantidade = quantidade;
        } else{
            throw new IllegalArgumentException("A quantidade deve ser maior que zero")
        }
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }
}
