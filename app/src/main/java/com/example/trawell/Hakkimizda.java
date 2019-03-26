package com.example.trawell;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Hakkimizda extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkimizda);



        loadText();
    }
    private void loadText(){
        String hakkimizda = "1998 yılında \"En yeni iletişim ve bilgisayar teknolojilerini kullanarak müşterilerine dünya standartlarında çözümler sunmak\" ilkesiyle yola çıkan isimtescil.net, geçen 16 yıllık süreçte Dünya ve Türkiye’ye, en büyük domain ve hosting firmalarından biri olmayı başarmıştır.\n" +
                "\n" +
                "2008 yılında alan adları standartlarını belirleyen ve denetleyen tek otorite ICANN'e akredite olan isimtescil.net, 2010 yılından beri Türkiye'nin en büyük 500 bilişim şirketi arasında yer almakta ve kurulduğu günden buyana 1 milyonun üzerinde domain kaydı ve 200 bininin üzerinde barındırma hizmetne ev sahipliği yapmıştır.";

    }

    public void btnHakBack(View view) {
        Intent Hakkimizda = new Intent(getApplicationContext(), MainActivity.class );
        startActivity(Hakkimizda);
    }
}
