package br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.ui.produto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;

import br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.R;

public class CadProdutoFragment extends Fragment {
    private EditText etNome;
    private EditText etQuantidade;
    private EditText etIdentificacao;
    private EditText etValor;
    private Spinner spMarca;
    private Button btSalvar;

    private RequestQueue requestQueue;
    private View view;


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
        this.view = inflater.inflate(R.layout.fragment_cad_produto, container, false);
        //spinner
        this.spMarca = (Spinner) view.findViewById(R.id.spMarca);
        //inicializando a fila de request do SO
        this.requestQueue.start();
        //return default
        return this.view;
    }
}