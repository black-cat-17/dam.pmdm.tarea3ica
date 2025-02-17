package dam.example.dampmdmtarea03ica;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    TabAdapter tabAdapter = new TabAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        // Configurar el TabLayout con el ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position)
            {
                case 0:
                    tab.setText("Pokémon Capturados");
                    break;
                case 1:
                    tab.setText("Pokédex");
                    break;
                case 2:
                    tab.setText("Ajustes");
                    break;
            }
        }).attach();
    }

    // Declaramos
    private void initView()
    {
        // Inicializar el ViewPager2 y el TabLayout
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Configurar el ViewPager2 con un Adapter
        viewPager.setAdapter(tabAdapter);
    }
}