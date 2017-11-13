package ittepic.edu.mx.proyectou3;

import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn_right,btn_left;
    ImageView imageView,img_mano1;
    AnimationDrawable blackMen,mano1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_right= (Button)findViewById(R.id.button);
        btn_left=(Button)findViewById(R.id.button2);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.animation_list);
        img_mano1=(ImageView)findViewById(R.id.imageView2);
        img_mano1.setBackgroundResource(R.drawable.animation_list1);

        mano1=(AnimationDrawable) img_mano1.getBackground();
        mano1.setOneShot(true);
        blackMen = (AnimationDrawable) imageView.getBackground();
        blackMen.setOneShot(true);
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //imageView.setVisibility(View.INVISIBLE);

                if (blackMen.isRunning()) {
                    blackMen.stop();
                    imageView.setBackgroundResource(R.drawable.animation_list);
                    blackMen = (AnimationDrawable) imageView.getBackground();
                }
                AsyncTarea asyncTarea = new AsyncTarea(imageView,0);
                asyncTarea.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                blackMen.start();


            }
        });
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mano1.isRunning()) {
                    mano1.stop();
                    img_mano1.setBackgroundResource(R.drawable.animation_list1);
                    mano1 = (AnimationDrawable) img_mano1.getBackground();
                }
                AsyncTarea asyncTarea = new AsyncTarea(img_mano1,1);
                asyncTarea.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                mano1.start();
            }
        });
    }
    private void UnSegundo() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    *

    * */
    private class  AsyncTarea extends AsyncTask<Integer, Integer,Boolean> {
        ImageView img;
        int mano;
        Double val;
        public AsyncTarea(ImageView img,int a){
            this.img=img;
            mano=a;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected Boolean doInBackground(Integer... params) {

            val=  Math.random();
            UnSegundo();
            return true;
        }


        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Boolean aVoid) {
            //super.onPostExecute(aVoid);
            if(val<=0.33){
                if(mano==0)
                    img.setBackgroundResource(R.drawable.right_scissors);
                else img.setBackgroundResource(R.drawable.left_scissors);
            }
            else if(val<=0.66){
                if(mano==0) img.setBackgroundResource(R.drawable.right_paper);
                else img.setBackgroundResource(R.drawable.left_paper);
            } else {
                if(mano==0) img.setBackgroundResource(R.drawable.right_rock);
                else img.setBackgroundResource(R.drawable.left_rock);
            }
            if (aVoid){
                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask",Toast.LENGTH_SHORT).show();
            }
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();

            Toast.makeText(getApplicationContext(),"Tarea NO finaliza AsyncTask",Toast.LENGTH_SHORT).show();

        }


    }
}
