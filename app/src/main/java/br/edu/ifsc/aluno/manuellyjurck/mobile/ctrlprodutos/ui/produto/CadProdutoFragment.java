package br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.ui.produto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.R;
import br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.model.Marca;

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
        this.requestQueue = Volley.newRequestQueue(view.getContext());
        this.requestQueue.start();
        //
        try {
            this.consultaMarcas();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        //return default
        return this.view;
    }

    private void consultaMarcas() throws JSONException {
        //requisição para o Rest Server
        JsonArrayRequest jsonArrayReq = null;
        try {
            jsonArrayReq = new JsonArrayRequest(
                    Request.Method.POST,
                    "http://10.0.2.2/consultaMarca.php",
                    new JSONArray("[{}]"),
                    response -> {
                        try {
                            //se a consulta não veio vazia
                            if (response != null) {
                                //array list para receber a resposta
                                ArrayList<Marca> lista= new ArrayList<>();
                                //preenchendo ArrayList com JSONArray recebido
                                for (int pos = 0;pos < response.length();pos++){
                                    JSONObject jo = response.getJSONObject(pos);
                                    Marca marca = new Marca();
                                    marca.setIdMarca(jo.getInt("idMarca"));
                                    marca.setNmMarca(jo.getString("nmMarca"));
                                    marca.setQualidade(jo.getString("Qualidade"));
                                    lista.add(pos, marca);
                                }
                                //Criando um adapter para o spinner
                                ArrayAdapter<Marca> adapter = new ArrayAdapter<>(
                                        requireContext(),
                                        android.R.layout.simple_spinner_item,
                                        lista);
                               //colocando o adapter no spinner
                                this.spMarca.setAdapter(adapter);
                            } else {
                                Snackbar mensagem = Snackbar.make(view,
                                        "A consulta não retornou nenhum registro!",
                                        Snackbar.LENGTH_LONG);
                                mensagem.show();
                            }
                        } catch (JSONException e) {
                            Snackbar mensagem = Snackbar.make(view,
                                    "Ops!Problema com o arquivo JSON: " + e,
                                    Snackbar.LENGTH_LONG);
                            mensagem.show();
                        }
                    },
                    error -> {
                        //mostrar mensagem que veio do servidor
                        Snackbar mensagem = Snackbar.make(view,
                                "Ops! Houve um problema ao realizar a consulta: " +
                                        error.toString(), Snackbar.LENGTH_LONG);
                        mensagem.show();
                    }
            );
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        //colocando nova request para fila de execução
        requestQueue.add(jsonArrayReq);
    }

}