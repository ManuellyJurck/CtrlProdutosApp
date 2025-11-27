package br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Produto {
    //atributo
    private int idProduto;
    private String nome;
    private int quantidade;
    private String identificacao;
    private double valor;
    private int idMarca;
    private String Disponivel;

    //obs: String é uma classe e int já é algo primitivo do java
    //construtores - garante que os atributos não fiquem com NULL
    public Produto(){
        this.idProduto = 0;
       this.nome = "";
       this.quantidade = 0;
       this.identificacao = "";
       this.valor = 0;
       this.idMarca = 0;
       this.Disponivel = "";

    }
    //CONSTRUTOR - inicializa atributos de um arquivo JSon
    public Produto(JSONObject jp) {
        try {
            this.setIdProduto(jp.getInt("idProduto"));
            this.setNome(jp.getString("nmProduto"));
            this.setQuantidade(jp.getInt("nrQuantidade"));
            this.setIdentificacao(jp.getString("nrIdentificacao"));
            this.setValor(jp.getDouble("Valor"));
            this.setIdMarca(jp.getInt("idMarca"));
            this.setDisponivel(jp.getString("Disponivel"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Metodo retorna o objeto com dados no formato JSON
    public JSONObject toJsonObject() {
        JSONObject json = new JSONObject();
        try {
            json.put("idProduto", this.idProduto);
            json.put("nmProduto", this.nome);
            json.put("nrQuantidade", this.quantidade);
            json.put("Valor", this.valor);
            json.put("idMarca", this.idMarca);
            json.put("Disponivel", this.Disponivel);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) throws Exception {
        if (quantidade > 0) {
            this.quantidade = quantidade;
        } else{
            throw new Exception("A quantidade deve ser maior que zero");
        }
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) throws Exception {
        if(nome.length() > 3){
            this.identificacao = identificacao;
        } else{
            throw new Exception("Identificação Invalida");
        }
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDisponivel() {
        return Disponivel;
    }

    public void setDisponivel(String Disponivel) {
        this.Disponivel = Disponivel;
    }
}
