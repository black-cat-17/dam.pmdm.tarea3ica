package dam.example.dampmdmtarea03ica;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabAdapter extends FragmentStateAdapter
{

    public TabAdapter(MainActivity mainActivity)
    {
        super(mainActivity);
    }

    @Override
    public Fragment createFragment(int position)
    {
        switch (position)
        {
            case 0:
                return new PokemonCapturados();
            case 1:
                return new Pokedex();
            case 2:
                return new Ajustes();
            default:
                return new PokemonCapturados();
        }
    }

    @Override
    public int getItemCount()
    {
        return 3; // Tres pestañas
    }
}