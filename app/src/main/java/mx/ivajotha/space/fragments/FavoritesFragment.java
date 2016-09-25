package mx.ivajotha.space.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.ivajotha.space.DetailActivity;
import mx.ivajotha.space.DetailFavoriteActivity;
import mx.ivajotha.space.R;
import mx.ivajotha.space.data.ApodService;
import mx.ivajotha.space.data.FavoritosAdapter;
import mx.ivajotha.space.data.NasaApodAdapter;
import mx.ivajotha.space.helper.Data;
import mx.ivajotha.space.model.MarsPhotos;
import mx.ivajotha.space.model.ModelFavoritos;
import mx.ivajotha.space.model.Photo;
import mx.ivajotha.space.sql.DataSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavoritesFragment extends Fragment {

    @BindView(R.id.favortos_listing)
    RecyclerView recyclerView;

    @BindView(R.id.loadingData)
    ProgressBar loadingData;

    private DataSource dataSource;



    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if(getArguments()!=null){
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(linearLayoutManager);

        final FavoritosAdapter favoritosAdapter = new FavoritosAdapter();

        favoritosAdapter.setOnItemClickListener(new FavoritosAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(ModelFavoritos mFavoritos) {
                Intent intent = new Intent(getActivity(), DetailFavoriteActivity.class)
                        .putExtra("imageFav", mFavoritos);
                startActivity(intent);

            }
        });

        dataSource = new DataSource(getActivity());
        List<ModelFavoritos> mFavoritosList = dataSource.getAllItems();
        if (!mFavoritosList.isEmpty()){
            favoritosAdapter.setFavoritos(mFavoritosList);
            recyclerView.setAdapter(favoritosAdapter);
        }else{
            Snackbar.make(getView(),getResources().getText(R.string.notFavorites),Snackbar.LENGTH_SHORT).show();
        }

    }


}
