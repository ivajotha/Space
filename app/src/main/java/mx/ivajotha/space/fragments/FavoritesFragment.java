package mx.ivajotha.space.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.ivajotha.space.R;
import mx.ivajotha.space.data.FavoritosAdapter;
import mx.ivajotha.space.model.ModelFavoritos;
import mx.ivajotha.space.sql.DataSource;


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
        String titleShareToday =  getResources().getString(R.string.titleFavorites);
        getActivity().setTitle(titleShareToday);

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
            public void onItemClick(final ModelFavoritos mFavoritos) {

                    new AlertDialog.Builder(getActivity())
                            .setTitle(String.format(getString(R.string.titleDialogDelete)))
                            .setMessage(String.format(getString(R.string.textDeleteFav)))
                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    //favoritesFragment.
                                    dataSource.deleteFav(mFavoritos);
                                    getFragmentManager().beginTransaction().replace(R.id.main_container,new FavoritesFragment()).commit();

                                }
                            }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).setCancelable(false).create().show();

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

        loadingData.setVisibility(View.GONE);

        //Instance APO
        /*ApodService apodService = Data.getInstance().create(ApodService.class);

        String apiKey = "5Njm32H3YhmhIkWBJxNpXAReRHJdoXLi4hD4pBvw";
        apodService.getMarsPhoto(apiKey).enqueue(new Callback<MarsPhotos>() {
            @Override
            public void onResponse(Call<MarsPhotos> call, Response<MarsPhotos> response) {
                nasaApodAdapter.setMarsPhoto(response.body().getPhotos());
                //recyclerView.setAdapter(new NasaApodAdapter(response.body().getPhotos()));
                recyclerView.setAdapter(nasaApodAdapter);
                loadingData.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<MarsPhotos> call, Throwable t) {

            }
        });
        */

    }


}
