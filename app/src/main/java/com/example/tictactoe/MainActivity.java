package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static boolean red=true;
    private static String[] pozitii;
    private boolean gameWon;
    private int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void click(View view)
    {
        ImageView newImage=(ImageView) view;

        int locatie=Integer.parseInt(newImage.getTag().toString());
        if(red)
        {

            if(pozitii[locatie].equals("gol") && !gameWon)
            {
                newImage.setTranslationY(-1500);
                newImage.setImageResource(R.drawable.yellow);
                newImage.animate().translationYBy(1500).rotation(3600).setDuration(200);
                pozitii[locatie]="Yellow";
                red = false;
            }
        }
        else
        {

            if(pozitii[locatie].equals("gol") && !gameWon)
            {
                newImage.setTranslationY(-1500);
                newImage.setImageResource(R.drawable.red);
                newImage.animate().translationYBy(1500).rotation(3600).setDuration(200);
                pozitii[locatie]="Red";
                 red = true;
            }
        }
        for(int[] winningPosition:winningPositions)
        {
            boolean full=true;
            for (String s : pozitii) {
                if (s != "gol")
                    continue;
                else
                    full = false;
            }
            if(full)
                gameWon=true;
            if(pozitii[winningPosition[0]].equals(pozitii[winningPosition[1]])
                    && pozitii[winningPosition[1]].equals(pozitii[winningPosition[2]])
                    && !pozitii[winningPosition[0]].equals("gol"))
            {
                gameWon=true;
                Toast.makeText(this,pozitii[winningPosition[0]]+" has won",Toast.LENGTH_SHORT).show();
            }

        }
        if(gameWon)
        {
            Button newButton=(Button)findViewById(R.id.button);
            newButton.setAlpha(1);
//            newButton.animate().alpha(1).setDuration(10);
        }

    }
    public void restartGame(View view)
    {
        Button newButton=(Button)findViewById(R.id.button);
        newButton.setAlpha(0);
        GridLayout myGrid=findViewById(R.id.gridLayout);
        for(int i=0;i<myGrid.getChildCount();i++)
        {
            ImageView myImage=(ImageView)myGrid.getChildAt(i);
            myImage.setImageDrawable(null);
        }
        Arrays.fill(pozitii, "gol");
        gameWon=false;
        red=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pozitii=new String[] {"gol","gol","gol","gol","gol","gol","gol","gol","gol"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}