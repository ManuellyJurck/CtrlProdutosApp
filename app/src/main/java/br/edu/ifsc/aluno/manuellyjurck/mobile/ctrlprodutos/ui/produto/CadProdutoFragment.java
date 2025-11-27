package br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.ui.produto;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.R;
import br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.model.Marca;
import br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.model.Produto;

public class CadProdutoFragment extends Fragment implements View.OnClickListener {
    private EditText etNome;
    private EditText etQuantidade;
    private EditText etIdentificacao;
    private EditText etValor;
    private EditText etDisponivel;
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
        //EditText
        this.etNome = (EditText) view.findViewById(R.id.etNome);
        this.etQuantidade = (EditText) view.findViewById(R.id.etQuantidade);
        this.etIdentificacao = (EditText) view.findViewById(R.id.etIdentificacao);
        this.etValor = (EditText) view.findViewById(R.id.etValor);
        this.etDisponivel = (EditText) view.findViewById(R.id.etDisponivel);
        this.btSalvar = (Button) view.findViewById(R.id.btSalvar);
        //definindo o listener do botão
        this.btSalvar.setOnClickListener(this);
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
    private void cadastrarProduto(Produto produto) throws JSONException {
//requisição para o Rest Server
        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(
                Request.Method.POST,
                "http://10.0.2.2/cadProduto.php",
                produto.toJsonObject(),
                response -> {
                    try {
        //se a request não veio vazia
                        if (response != null) {
                            Context context = requireContext();
                            if (response.getBoolean("success")) {
//limpar campos da tela
                                this.etNome.setText("");
                                this.etIdentificacao.setText("");
                                this.etQuantidade.setText("");
                                this.etValor.setText("");
                                this.etDisponivel.setText("");
                                //primeiro item dos spinners
                                this.spMarca.setSelection(0);
                            }
//mostrando a mensagem que veio do JSON
                            Toast toast = Toast.makeText(
                                    view.getContext(),
                                    response.getString("message"),
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
//mostrar mensagem do response == null
                            Snackbar mensagem = Snackbar.make(
                                    view,
                                    "A consulta não retornou nada!",
                                    Snackbar.LENGTH_LONG);
                            mensagem.show();
                        }
                    } catch (Exception e) {
//mostrar mensagem da exception
                        Snackbar mensagem = Snackbar.make(
                                view,
                                "Ops!Problema com o arquivo JSON: " + e,
                                Snackbar.LENGTH_LONG);
                        mensagem.show();
                    }
                },
                error -> {
//mostrar mensagem que veio do servidor
                    Snackbar mensagem = Snackbar.make(
                            view,
                            "Ops! Houve um problema: " + error.toString(),
                            Snackbar.LENGTH_LONG);
                    mensagem.show();
                }
        );
//colocando nova request para fila de execução
        requestQueue.add(jsonObjectReq);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btSalvar) {
            try {
                //instanciando objeto de negócio
                Produto produto = new Produto();
                //populando objeto com dados da tela
                produto.setNome(this.etNome.getText().toString());
                produto.setQuantidade(Integer.valueOf(this.etQuantidade.getText().toString()));
                produto.setIdentificacao(this.etIdentificacao.getText().toString());
                produto.setValor(Double.valueOf(this.etValor.getText().toString()));
                produto.setDisponivel(this.etDisponivel.getText().toString());

                //objeto do item selecionado do Spinner
                int pos = this.spMarca.getSelectedItemPosition();
                Marca marca = (Marca) spMarca.getItemAtPosition(pos);
                produto.setIdMarca(marca.getIdMarca());

        //chamada do web service de cadastro
                try {
                    cadastrarProduto(produto);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}