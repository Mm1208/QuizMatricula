package com.miker.login.curso;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.miker.login.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Luis Carrillo Rodriguez on 18/4/2018.
 */
public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.MyViewHolder> implements Filterable {

    private List<Curso> cursoList;
    private List<Curso> cursoListFiltered;
    private CursoAdapterListener listener;
    private Curso object;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView codigo, nombre;
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;

        public MyViewHolder(View view) {
            super(view);
            codigo = (TextView) view.findViewById(R.id.codigo);
            nombre = (TextView) view.findViewById(R.id.nombre);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onSelected(cursoListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public CursosAdapter(List<Curso> cursoList, CursoAdapterListener listener) {
        this.cursoList = cursoList;
        this.listener = listener;
        this.cursoListFiltered = cursoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.curso_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Curso curso = cursoListFiltered.get(position);
        holder.codigo.setText(curso.getCodigo());
        holder.nombre.setText(curso.getNombre());
    }

    @Override
    public int getItemCount() {
        return cursoListFiltered.size();
    }

    public void removeItem(int position) {
        object = cursoListFiltered.remove(position);
        Iterator<Curso> iter = cursoList.iterator();
        while (iter.hasNext()) {
            Curso aux = iter.next();
            if (object.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (cursoListFiltered.size() == cursoList.size()) {
            cursoListFiltered.add(position, object);
        } else {
            cursoListFiltered.add(position, object);
            cursoList.add(object);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Curso getSwipedItem(int index) {
        if (this.cursoList.size() == this.cursoListFiltered.size()) { //not filtered yet
            return cursoList.get(index);
        } else {
            return cursoListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (cursoList.size() == cursoListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(cursoList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(cursoList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(cursoListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(cursoListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    cursoListFiltered = cursoList;
                } else {
                    List<Curso> filteredList = new ArrayList<>();
                    for (Curso row : cursoList) {
                        // filter use two parameters
                        if (row.getCodigo().toLowerCase().contains(charString.toLowerCase()) || row.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    cursoListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = cursoListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                cursoListFiltered = (ArrayList<Curso>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface CursoAdapterListener {
        void onSelected(Curso curso);
    }
}

