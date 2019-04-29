package com.zlayar.zlayar.FragmentMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zlayar.zlayar.AuthenticationUser.login.loginUser;
import com.zlayar.zlayar.AuthenticationUser.register.registerUser;
import com.zlayar.zlayar.ChatActivity;
import com.zlayar.zlayar.MainActivity;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.data.User;
import com.zlayar.zlayar.identityFirebase.Constants;
import com.zlayar.zlayar.identityFirebase.ItemClickSupport;
import com.zlayar.zlayar.recyclerView.UserListingRecyclerAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by IrfanRZ on 6/2/2018.
 */

public class fragmentPesanan extends Fragment implements ItemClickSupport.OnItemClickListener {
    private TabLayout tabPesanan;
    private ViewPager viewPesanan;
    private View view;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerViewAllUserListing;
    public static ArrayList<User> listUser = new ArrayList<>();
    private UserListingRecyclerAdapter mUserListingRecyclerAdapter;
    private ArrayList<dataaaa> dataChat = new ArrayList<>();
    private ArrayList<User> dataUser = new ArrayList<>();
    private RelativeLayout pesanLogin, pesanChat;
    private Button login, daftar;
    private TextView textNotFound;

    public fragmentPesanan() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pesanan, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRecyclerViewAllUserListing = (RecyclerView) view.findViewById(R.id.recycler_view_all_user_listing);
        pesanLogin = (RelativeLayout) view.findViewById(R.id.pesanLogin);
        pesanChat = (RelativeLayout) view.findViewById(R.id.pesanChat);
        login = (Button) view.findViewById(R.id.menuLogin);
        daftar = (Button) view.findViewById(R.id.menuRegister);
        textNotFound = (TextView) view.findViewById(R.id.textNothingFound);

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            pesanChat.setVisibility(View.VISIBLE);
            pesanLogin.setVisibility(View.GONE);
            textNotFound.setVisibility(View.GONE);
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
            onGetRoom(uid);
        }
        else if (FirebaseAuth.getInstance().getCurrentUser() == null){
            pesanChat.setVisibility(View.GONE);
            pesanLogin.setVisibility(View.VISIBLE);
            textNotFound.setVisibility(View.GONE);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


        return view;
    }

    private void onGetRoom(final String uid) {
        FirebaseDatabase.getInstance().getReference().child("chat_rooms").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataChatRooms = dataSnapshot.getChildren().iterator();
                while (dataChatRooms.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataChatRooms.next();
                    String roomData = dataSnapshotChild.getKey().toString();
                    if ((roomData != null) && (roomData.contains(uid)) ) {
                        dataChat.add(new dataaaa(roomData));
                    }
                }
                    onGetUser(uid);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void onGetUser(final String uid) {
        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user;
                Iterator<DataSnapshot> dataSnapshots = dataSnapshot.getChildren().iterator();
                while (dataSnapshots.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshots.next();
                    User user = null;
                    user = dataSnapshotChild.getValue(User.class);
                    dataUser.add(user);

                }
                for (int a = 0; a < dataChat.size(); a++){
                    for (int b = 0; b < dataUser.size(); b++){
                        if ((dataChat.get(a).getUid_uid().contains(dataUser.get(b).uid)) && (!dataUser.get(b).uid.contains(uid))){
                            listUser.add(new User(dataUser.get(b).uid.toString(), dataUser.get(b).email.toString(),
                                    dataUser.get(b).firebaseToken.toString(), dataUser.get(b).typeUser.toString()
                                    ));
                        }
                    }
                }
                recyclerView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void recyclerView(){

        if (listUser.isEmpty()){
            textNotFound.setVisibility(View.VISIBLE);
        }
        else{
            textNotFound.setVisibility(View.GONE);
            mUserListingRecyclerAdapter = new UserListingRecyclerAdapter(listUser);
            mRecyclerViewAllUserListing.setAdapter(mUserListingRecyclerAdapter);
            mUserListingRecyclerAdapter.notifyDataSetChanged();
        }

        ItemClickSupport.addTo(mRecyclerViewAllUserListing)
                .setOnItemClickListener(this);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        ChatActivity.startActivity(getActivity(),
                mUserListingRecyclerAdapter.getUser(position).email,
                mUserListingRecyclerAdapter.getUser(position).uid,
                mUserListingRecyclerAdapter.getUser(position).firebaseToken);
    }
    private void register() {
        loginUser.activity = 1;
        Intent intent = new Intent(getActivity(), registerUser.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void login() {
        Intent intent = new Intent(getActivity(), loginUser.class);
        loginUser.activity = 1;
        startActivity(intent);
        getActivity().finish();
    }
}
