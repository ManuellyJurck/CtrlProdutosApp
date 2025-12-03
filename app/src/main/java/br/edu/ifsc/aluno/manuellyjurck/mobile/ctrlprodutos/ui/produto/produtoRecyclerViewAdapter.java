package br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.ui.produto;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.databinding.FragmentConprodutoBinding;
import br.edu.ifsc.aluno.manuellyjurck.mobile.ctrlprodutos.model.Produto;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Produto}.
 * TODO: Replace the implementation with code for your data type.
 */
public class produtoRecyclerViewAdapter extends RecyclerView.Adapter<produtoRecyclerViewAdapter.ViewHolder> {

    private final List<Produto> mValues;

    public produtoRecyclerViewAdapter(List<Produto> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentConprodutoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getNome());
        holder.mContentView.setText(String.valueOf(mValues.get(position).getValor()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public Produto mItem;

        public ViewHolder(FragmentConprodutoBinding binding) {
            super(binding.getRoot());
            mIdView = binding.itemNumber;
            mContentView = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}