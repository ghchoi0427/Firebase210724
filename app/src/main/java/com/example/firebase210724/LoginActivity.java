package com.example.firebase210724;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase210724.util.FirebaseDatabaseHandler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    EditText editId;
    EditText editPassword;
    Button btnLogin;
    Button btnSignup;
    private FirebaseAuth auth;
    private final String EMAIL_SUFFIX = "@gmail.com";
    public static final String LAST_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editId = findViewById(R.id.edit_login_id);
        editPassword = findViewById(R.id.edit_login_password);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);

        auth = FirebaseAuth.getInstance();
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);

        editId.setText(pref.getString(LAST_ID, ""));

        editId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pref.edit().putString(LAST_ID, editable.toString()).apply();
            }
        });

        btnLogin.setOnClickListener(view -> signIn(editId.getText().toString().trim(), editPassword.getText().toString().trim()));

        btnSignup.setOnClickListener(view -> startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void signIn(String name, String password) {
        try {
            auth.signInWithEmailAndPassword(name + EMAIL_SUFFIX, password).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "로그인 되었습니다", Toast.LENGTH_SHORT).show();
                    FirebaseDatabaseHandler firebaseHandler = new FirebaseDatabaseHandler(FirebaseFirestore.getInstance());
                    startActivity(new Intent(this, MainActivity.class));
                }
            }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "로그인 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
