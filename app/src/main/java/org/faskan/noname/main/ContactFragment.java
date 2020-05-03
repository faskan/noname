package org.faskan.noname.main;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.faskan.noname.R;
import org.faskan.noname.model.Contact;
import org.faskan.noname.dao.GetContactsService;
import org.faskan.noname.dao.RetrofitClientInstance;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ContactFragment extends Fragment {

    private static final String CONTACTS_STATE = "contacts_state";

    private OnListFragmentInteractionListener mListener;
    private MyContactRecyclerViewAdapter myContactRecyclerViewAdapter;
    private GetContactsService service;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_contact_list, container, false);
        layoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(layoutManager);
        service = RetrofitClientInstance.getRetrofitInstance().create(GetContactsService.class);

        fetchDataAndPopulateView(recyclerView);

        //view.setOnScrollChangeListener(new ContactsScrollChangeListener(() -> fetchDataAndPopulateView(view)));
        return recyclerView;
    }

//    public void restorePreviousState(Bundle savedInstanceState){
//        // getting recyclerview position
//        Parcelable stateParcelable = savedInstanceState.getParcelable(CONTACTS_STATE);
//        // getting recyclerview items
//        ArrayList<Parcelable> savedContactList = savedInstanceState.getParcelableArrayList(CONTACTS_LIST_STATE);
//        // Restoring adapter items
//        myContactRecyclerViewAdapter.setContacts(savedContactList);
//        // Restoring recycler view position
//        mRvMedia.getLayoutManager().onRestoreInstanceState(mListState);
//    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable contactsState = layoutManager.onSaveInstanceState();
        outState.putParcelable(CONTACTS_STATE, contactsState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null){
            layoutManager.onRestoreInstanceState(savedInstanceState.getParcelable(CONTACTS_STATE));
        }
    }

    private void fetchDataAndPopulateView(RecyclerView view) {
        Call<List<Contact>> call = service.getAllContacts();
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                myContactRecyclerViewAdapter = new MyContactRecyclerViewAdapter(response.body(), mListener);
                view.setAdapter(myContactRecyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {
                Toast.makeText(view.getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Contact contact);
    }
}
