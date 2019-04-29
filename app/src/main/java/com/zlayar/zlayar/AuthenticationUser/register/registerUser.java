package com.zlayar.zlayar.AuthenticationUser.register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zlayar.zlayar.AuthenticationUser.login.loginUser;
import com.zlayar.zlayar.MainActivity;
import com.zlayar.zlayar.SplashScreen;
import com.zlayar.zlayar.data.User;
import com.zlayar.zlayar.detailJasa.pekerjaDetail;
import com.zlayar.zlayar.R;
import com.zlayar.zlayar.identityFirebase.Constants;
import com.zlayar.zlayar.identityFirebase.SharedPrefUtil;

import java.util.Iterator;

public class registerUser extends AppCompatActivity implements RegisterContract.View, AddUserContract.View{
    private EditText email, pass;
    private ImageView back;
    private Button register;
    private RegisterPresenter mRegisterPresenter;
    private AddUserPresenter mAddUserPresenter;
    private Button showPassword;
    private int show = 0;
    private ProgressDialog progressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        email = (EditText) findViewById(R.id.registerEmail);
        pass = (EditText) findViewById(R.id.registerPass);
        register = (Button) findViewById(R.id.registerUser);
        showPassword = (Button) findViewById(R.id.show_password);
        back = (ImageView) findViewById(R.id.registerBack);

        mRegisterPresenter = new RegisterPresenter(this);
        mAddUserPresenter = new AddUserPresenter(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (loginUser.activity){
                    case 1:
                        Intent intent = new Intent(registerUser.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent2 = new Intent(registerUser.this, pekerjaDetail.class);
                        startActivity(intent2);
                        break;
                }
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId = email.getText().toString();
                String password = pass.getText().toString();

                progressDialog = new ProgressDialog(registerUser.this);
                progressDialog.setMessage("Mohon Tunggu...");
                progressDialog.setTitle("Proses");
                progressDialog.setCancelable(false);
                progressDialog.show();
                mRegisterPresenter.register(registerUser.this, emailId, password);
            }
        });

        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (show==1){
                    show=0;
                    showPassword.setBackgroundResource(R.drawable.ic_dont_show);
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else if (show==0){
                    show=1;
                    showPassword.setBackgroundResource(R.drawable.ic_show);
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void onRegistrationSuccess(FirebaseUser firebaseUser) {
        mAddUserPresenter.addUser(registerUser.this, firebaseUser);
    }

    @Override
    public void onRegistrationFailure(String message) {
        progressDialog.dismiss();
        Toast.makeText(registerUser.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddUserSuccess(String message) {
        Toast.makeText(registerUser.this, "Registrasi Berhasil !", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        switch (loginUser.activity){
            case 1:
                Intent intent = new Intent(registerUser.this, MainActivity.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent2 = new Intent(registerUser.this, pekerjaDetail.class);
                startActivity(intent2);
                break;
        }
        finish();
    }

    @Override
    public void onAddUserFailure(String message) {
        Toast.makeText(registerUser.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        switch (loginUser.activity){
            case 1:
                Intent intent = new Intent(registerUser.this, MainActivity.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent2 = new Intent(registerUser.this, pekerjaDetail.class);
                startActivity(intent2);
                break;
        }
        finish();
    }
}
