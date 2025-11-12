package br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.ui.produto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.R;

public class CadProdutoFragment extends Fragment {
    private EditText etNome;
    private EditText etQuantidade;
    private EditText etIdentificacao;
    private EditText etValor;
    private Spinner spMarca;
    private Button btSalvar;
    public CadProdutoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cad_produto, container, false);
    }
}