package np.com.softwarica.mongoapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHeroesAPI extends AppCompatActivity {

    private EditText etName, etDescription;
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_heroes_api);

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.eDescription);
        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Save();
            }
        });
    }

    private void Save() {

        String name = etName.getText().toString();
        String desc = etDescription.getText().toString();

        Map<String,String> map = new HashMap<>();
        map.put("name",name);
        map.put("desc",desc);

        Call<Void> heroesCall = MyRetrofit.getAPI().addHero(map);


        Hero hero = new Hero(name, desc, name + ".jpg");

 //       Call<Void> heroesCall = MyRetrofit.getAPI().addHero(name, desc);

        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(AddHeroesAPI.this, "Code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(AddHeroesAPI.this, "Successfully Added", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddHeroesAPI.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


        MyRetrofit.getAPI().addHero(hero).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddHeroesAPI.this, "Code" + response.code(), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(AddHeroesAPI.this, "Successfully Added", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddHeroesAPI.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}