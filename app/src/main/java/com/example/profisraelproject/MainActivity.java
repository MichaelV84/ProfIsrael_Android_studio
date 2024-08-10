package com.example.profisraelproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<DataModel> mList;
    private ItemAdapter adapter;
    ImageView btnPupMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_main);

        Button helpButton = findViewById(R.id.button_help);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpActivity();
            }
        });
        ImageView reklamaImageView = findViewById(R.id.reklama);
        reklamaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHelpActivity();
            }
        });




        Button openWebPageButton = findViewById(R.id.loginButton);
        openWebPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebPage();
            }
        });

        recyclerView = findViewById(R.id.main_recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        mList = new ArrayList<>();
        List<String> nestedList1 = new ArrayList<>();
        String reklama_na_portale = "В верхнем правом углу сайта кнопка «Регистрация», нажав на неё вы переходите к заполнению информации, которая будет отображаться в вашей анкете. \n" +
                "Инстаграм мы берем только для продвижения в социальных сетях.\n" +
                "Фото мастера обязательно, по статистике клиент выбирает мастера, который ему внешне близок.\n" +
                "Фото работ - чем больше вы добавите, тем лучше, клиент увидит ваше портфолио и сможет принять решение.\n" +
                "Есть профессии где достаточно и одного фото (психолог, репетитор и тд), но если вы фотограф, мастер маникюра, парикмахер или делаете тортики на заказ или рисуете картины, без фото работ, как клиент вас сможет оценить?\n" +
                "Отнеситесь к заполнению анкеты максимально серьезно, мы помогаем вас найти, но продаете себя вы сами.";
        String kogo_mojno_naiti ="Наш портал помогает найти специалистов в различных областях, стоит выбрать город и категорию услуг, далее вы увидите всех представленных в нем специалистов.";
        String chem_mi_lychshe ="Мы постарались сделать наиболее удобный и простой в использовании поисковик по услугам в Израиле, без навязчивой рекламы, который отвечает всем современным требованиям по использованию с любых устройств.";
        String pomogite_stat_luchshe ="Если вы заметили на портале какую-либо неточность, ошибку или опечатку, пожалуйста, дайте нам знать об этом. Так же вы можете отправить нам на почту любые ваши пожелания связанные с функционалом и наполнением портала, которые будут рассмотрены в обязательном порядке.";
        String kto_mi ="Мы - молодая команда репатриантов, которые в свое время столкнулись с трудностями адаптации и поиска необходимых услуг в Израиле, поэтому мы решили создать этот портал для удобства всех русскоязычных пользователей.";

        nestedList1.add(reklama_na_portale);

        List<String> nestedList2 = new ArrayList<>();
        nestedList2.add(kogo_mojno_naiti);
        List<String> nestedList3 = new ArrayList<>();
        nestedList3.add(chem_mi_lychshe);
        List<String> nestedList4 = new ArrayList<>();
        nestedList4.add(pomogite_stat_luchshe);
        List<String> nestedList5 = new ArrayList<>();
        nestedList5.add(kto_mi);


        mList.add(new DataModel(nestedList1,"Реклама на портале"));
        mList.add(new DataModel(nestedList2,"Кого здесь можно найти?"));
        mList.add(new DataModel(nestedList3,"Чем мы лучше других?"));
        mList.add(new DataModel(nestedList4,"Помогите нам стать лучше!"));
        mList.add(new DataModel(nestedList5,"Кто мы?"));

        adapter = new ItemAdapter(mList,this);
        recyclerView.setAdapter(adapter);


        btnPupMenu = findViewById(R.id.menuPUP);
        registerForContextMenu(btnPupMenu);
        btnPupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.showContextMenu();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        View view = getCurrentFocus();
        if (view != null && (event.getAction() == MotionEvent.ACTION_DOWN)) {
            if (view instanceof EditText) {
                Rect outRect = new Rect();
                view.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    view.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
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

    private void openHelpActivity() {
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
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