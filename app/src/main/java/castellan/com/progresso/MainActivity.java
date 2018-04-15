package castellan.com.progresso;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar circulo,linear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circulo = findViewById(R.id.progressBar);
        linear = findViewById(R.id.linearBar);

        linear.setProgress(0);
        linear.setSecondaryProgress(0);
        linear.setMax(100);
        circulo.setVisibility(View.GONE);

        new SynCular().execute();
    }

    public class SynCular extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            for (int i = 0; i < 100; i++){
                try{
                    publishProgress(i);
                    Thread.sleep(300);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            circulo.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            circulo.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            linear.setProgress(values[0]);
            linear.setSecondaryProgress(values[0] + 20);
        }
    }
}
