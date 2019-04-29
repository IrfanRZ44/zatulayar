package com.zlayar.zlayar.FragmentMain;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import com.zlayar.zlayar.MainActivity;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.SplashScreen;
import com.zlayar.zlayar.data.User;
import com.zlayar.zlayar.identityFirebase.SharedPrefUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by IrfanRZ on 6/2/2018.
 */

public class FragmentPengaturan extends Fragment {
    View view;
    private RelativeLayout relativeLogout, relativeAuth;
    private Button login, logout, register, edit;
    private RelativeLayout aboutTerms;
    private TextView textNama, textEmail;

    public FragmentPengaturan() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_pengaturan, container, false);

        relativeAuth = (RelativeLayout) view.findViewById(R.id.contentAuth);
        relativeLogout = (RelativeLayout) view.findViewById(R.id.contentLogout);
        login = (Button) view.findViewById(R.id.menuLogin);
        logout = (Button) view.findViewById(R.id.menuLogout);
        register = (Button) view.findViewById(R.id.menuRegister);
        aboutTerms = (RelativeLayout) view.findViewById(R.id.about_terms);
        edit = (Button) view.findViewById(R.id.menuEdit);
        textNama = (TextView) view.findViewById(R.id.personName);
        textEmail = (TextView) view.findViewById(R.id.emailPerson);

        aboutTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), terms.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(getView(), "We are sorry, this is not available for now",Snackbar.LENGTH_LONG);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            relativeLogout.setVisibility(View.VISIBLE);
            relativeAuth.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
            getPersonData();
        } else {
            // otherwise redirect the User to login activity
            relativeLogout.setVisibility(View.GONE);
            relativeAuth.setVisibility(View.VISIBLE);
            logout.setVisibility(View.GONE);

        }
        return view;
    }

    private void getPersonData() {
        String subNama = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString().substring(0, 5);
        textNama.setText(subNama);
        textEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
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

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        new SharedPrefUtil(getActivity()).saveString("type", null);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Toast.makeText(getActivity(), "Succesfully Sign Out", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(), MainActivity.class);
            getActivity().finish();
            startActivity(intent);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            relativeLogout.setVisibility(View.VISIBLE);
            relativeAuth.setVisibility(View.GONE);
            logout.setVisibility(View.VISIBLE);
        }
    }

}
