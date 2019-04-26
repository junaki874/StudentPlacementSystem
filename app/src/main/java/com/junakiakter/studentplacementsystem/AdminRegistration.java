package com.junakiakter.studentplacementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminRegistration extends AppCompatActivity {
    EditText Ruser, Rpass, RcPass;
    Button  signup;
    AdminDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_registration);

        db = new AdminDatabase(this);

        Ruser = (EditText) findViewById(R.id.ReguserId);
        Rpass = (EditText) findViewById(R.id.RegpassId);
        RcPass = (EditText) findViewById(R.id.CofirmpassId);


        signup = (Button) findViewById(R.id.SignId);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Ruser.getText().toString().trim();
                String pwd = Rpass.getText().toString().trim();
                String cpwd = RcPass.getText().toString().trim();
                if (pwd.equals(cpwd)) {
                    long val = db.addUser(user, pwd);
                    if (val > 0) {
                        Intent signIntent = new Intent(AdminRegistration.this, Admin.class);
                        startActivity(signIntent);
                    } else {
                        Toast.makeText(AdminRegistration.this, "registration error", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(AdminRegistration.this, "password not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
