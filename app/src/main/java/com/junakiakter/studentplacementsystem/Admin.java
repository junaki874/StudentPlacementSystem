package com.junakiakter.studentplacementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Admin extends AppCompatActivity {
    EditText userT, password;
    Button login,forgot,create;
    AdminDatabase db;
    TextView  txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        db=new AdminDatabase(this);
        userT=(EditText)findViewById(R.id.userId);
        password=(EditText)findViewById(R.id.passId);
        txt=(TextView)findViewById(R.id.txtId) ;

        login=(Button)findViewById(R.id.logInId);
        create=(Button)findViewById(R.id.createId);
        forgot=(Button)findViewById(R.id.forgotId);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cr_intent=new Intent(Admin.this,AdminRegistration.class);
                startActivity(cr_intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= userT.getText().toString().trim();
                String pwd=password.getText().toString().trim();
                boolean res=db.checkUser(user,pwd);
                try{
                    if(res==true){
                        Intent ad=new Intent(Admin.this,    Admin_Module.class);
                        startActivity(ad);
                    }
                    else{

                        txt.setText("wrong password");

                        //Toast.makeText(Admin.this,"wrong password",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    txt.setText((CharSequence) e);
                    Toast.makeText(Admin.this, (CharSequence) e,Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
