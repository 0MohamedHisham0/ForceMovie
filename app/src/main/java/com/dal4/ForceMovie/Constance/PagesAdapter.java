package com.dal4.ForceMovie.Constance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dal4.ForceMovie.Fragments.TopPopular_Frag;
import com.dal4.ForceMovie.Fragments.TopRated_Frag;
import com.dal4.ForceMovie.Fragments.UpComing_Frag;
import com.dal4.ForceMovie.R;
import com.dal4.ForceMovie.ui.main.Search;

import java.util.List;

public class PagesAdapter extends RecyclerView.Adapter<PagesAdapter.RecyclerViewHolder> {

    private int selectedStarPosition = 0;
    private List<Integer> integerList;
    private Context context;
    private AdapterView.OnItemClickListener onItemClickListener;
    Fragment fragment;
    String movie_name;

    public PagesAdapter(List<Integer> galaxies, Context c, Fragment frag, String movie_name) {
        this.integerList = galaxies;
        this.context = c;
        this.movie_name = movie_name;
        this.fragment = frag;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        if (context instanceof Search) {
            v = LayoutInflater.from(context).inflate(R.layout.item_page_number_search, viewGroup, false);

        } else {
            v = LayoutInflater.from(context).inflate(R.layout.item_page_number, viewGroup, false);
        }

        return new RecyclerViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder, final int position) {
        int s = integerList.get(position);
        try {
            viewHolder.bindData(s, position);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return integerList == null ? 0 : integerList.size();
    }


    public void onItemHolderClick(RecyclerViewHolder holder) {
        if (onItemClickListener != null)
            onItemClickListener.onItemClick(null, holder.itemView,
                    holder.getAdapterPosition(), holder.getItemId());
    }

    /**
     * Let's come create our ViewHolder class.
     */
    class RecyclerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private PagesAdapter mAdapter;
        private RadioButton mRadioButton;
        private TextView textView;

        public RecyclerViewHolder(View itemView, final PagesAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;

            if (mRadioButton == null)
                mRadioButton = itemView.findViewById(R.id.radioButton_ItemPage);
            if (textView == null)
                textView = itemView.findViewById(R.id.Text_ItemPage);

            itemView.setOnClickListener(this);
            mRadioButton.setOnClickListener(this);
        }


        public void bindData(int pageNumber, int position) {
            mRadioButton.setChecked(position == selectedStarPosition);

            textView.setText(pageNumber + "");
            if (mRadioButton.isChecked()) {
                if (fragment != null && movie_name.equals("")) {
                    if (fragment instanceof TopRated_Frag)
                        ((TopRated_Frag) fragment).TopMoviesData(pageNumber + "");
                    if (fragment instanceof TopPopular_Frag)
                        ((TopPopular_Frag) fragment).TopMoviesData(pageNumber + "");
                    if (fragment instanceof UpComing_Frag)
                        ((UpComing_Frag) fragment).TopMoviesData(pageNumber + "");
                } else {

                    if (context instanceof Search) {
                        ((Search) context).GetSearchList(movie_name, pageNumber + "");
                        Toast.makeText(context, pageNumber + "" + movie_name, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        @Override
        public void onClick(View v) {
            selectedStarPosition = getAdapterPosition();
            notifyItemRangeChanged(0, integerList.size());
            mAdapter.onItemHolderClick(RecyclerViewHolder.this);
        }
    }
}