package br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.model;

public class Marca {
    private int idMarca;
    private String nmMarca;
    private String Qualidade;

    //metodo tostring() para o adapter do spinner
    @Override
    public String toString() {
        return this.nmMarca;
    }
    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNmMarca() {
        return nmMarca;
    }

    public void setNmMarca(String nmMarca) {
        this.nmMarca = nmMarca;
    }

    public String getQualidade() {
        return Qualidade;
    }

    public void setQualidade(String qualidade) {
        Qualidade = qualidade;
    }
}
