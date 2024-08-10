package com.example.profisraelproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity {

    Button openWebPageButton;
    ImageView btnPupMenu;
    String whatsAppNumber = "+972539578359";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_help);

        Button openWebPageButton = findViewById(R.id.loginButton);
        openWebPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage();
            }
        });

        Button whatsappButton = findViewById(R.id.button_whatsapp);
        whatsappButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWhatsApp();
            }
        });

        Button helpButton = findViewById(R.id.button_help);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpActivity();
            }
        });

        btnPupMenu = findViewById(R.id.menuPUP);
        registerForContextMenu(btnPupMenu);
        btnPupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.showContextMenu();
            }
        });

    }

    public void logoClick(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater =  new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_items, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.item2:
                Intent intent1 = new Intent(this, ContactsActivity.class);
                startActivity(intent1);
                break;
            case R.id.item3:
                openInstagram();
                break;
        }
        return super.onContextItemSelected(item);
    }
    private void openInstagram() {
        Uri uri = Uri.parse("https://www.instagram.com/profi.israel");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.instagram.android");

        // Check if the Instagram app is installed
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // If Instagram app is not installed, open in browser
            intent.setPackage(null);
            startActivity(intent);
        }
    }



    private void openWhatsApp() {

        String url = "https://api.whatsapp.com/send?phone="+whatsAppNumber;

        try {
            PackageManager pm = getApplicationContext().getPackageManager();
            pm.getPackageInfo("com.whatsapp",PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    private void openHelpActivity() {
        Intent intent = new Intent(HelpActivity.this, HelpActivity.class);
        startActivity(intent);
    }

    private void openWebPage() {
        String url = "https://profisrael.com/login/";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // Покажите Toast сообщение, если нет подходящих приложений
            Toast.makeText(this, "No application found to handle the action.", Toast.LENGTH_LONG).show();
        }
    }
}