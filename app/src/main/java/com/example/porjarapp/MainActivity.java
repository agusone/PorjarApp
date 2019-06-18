package com.example.porjarapp;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView  bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        getFragmentPage(new HomeFragment());

        Intent intent= new Intent(MainActivity.this,SigninActivity.class);
        startActivity(intent);



//        bottomNavigationView=findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//                switch (menuItem.getItemId()){
//                    case R.id.menu_home:
//                         getFragmentPage(new HomeFragment());
//                        break;
//
//                    case R.id.menu_publik:
//                        getFragmentPage(new PublikFragment());
//                        break;
//
//                    case R.id.add_person:
//                        getFragmentPage(new DaftarFragment());
//
//                        break;
//                }
//
//                return true;
//            }
//        });
    }

    private boolean getFragmentPage(Fragment fragment){
        if (fragment !=null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container,fragment)
                    .commit();

            return true;
        }

        return false;
    }
}

