package com.example.asmr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

// Developed by Schair A. Maniega.

// [Before starting...]
/*

This APK has been designed for one person, known as Laura Coronado Blázquez, one of my favourite person
in this world.
Yes, this is, in fact, a personalized ASMR player for her, and I'd like to let every of my feelings written down here,
just in case she decided to data-mine this app (which I barely think is going to happen).
I just wanted her to know, that I'm and probably always will be in love with her, so every effort I can take for making
her life easier, I'd like to do it, without any doubt in my not-as-clear-as-it-could-seem mind
Some time ago, I spent the most beautiful time and relationship I've ever have with her, and nowadays, I'd really like to
spent my entire life with her.
So, basically, if you, Laura, have casually arrived here, or if you, the person reading this, has discovered this, for you
I assume, nonsense commentary, please, do me a favor, and notice her of the feeling the developer of this amateur media player,
which I'm completely sure she knows, but just in case, her twitter is @interlaustellar, take it easy.

So, that's it, I love you, and I'll always will, and I'd be the happiest on earth of joining you for the rest of my life,
as I said countless times back on those days :)

 */


public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;

    String ASMRTitles[], ASMRSubtitles[];

    TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.asmrandsong);
        description = findViewById(R.id.description);
        title.setTextColor(Color.BLACK);
        description.setTextColor(Color.BLACK);

        recyclerView = findViewById(R.id.recyclerId);

        ASMRTitles = getResources().getStringArray(R.array.asmr_titles);
        ASMRSubtitles = getResources().getStringArray(R.array.asmr_subtitles);

        MyAdapter myASMRAdapter = new MyAdapter(this, ASMRTitles, ASMRSubtitles);
        recyclerView.setAdapter(myASMRAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



}