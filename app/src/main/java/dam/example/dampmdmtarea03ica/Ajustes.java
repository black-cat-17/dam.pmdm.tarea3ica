package dam.example.dampmdmtarea03ica;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import java.util.Locale;

public class Ajustes extends Fragment
{

    ////////////////////////////////////////////////////////////////////////////////////////////
    @SuppressLint("WrongViewCast")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_ajustes, container, false);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Obtener los componentes de la vista
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Declaramos componentes
        SwitchCompat ajustesEliminar = rootView.findViewById(R.id.ajustesEliminar);
        Spinner ajustesIdioma = rootView.findViewById(R.id.ajustesIdioma);
        Button ajustesBtnAcercaDe = rootView.findViewById(R.id.ajustesBtnAcercaDe);
        Button ajustesBtnCerrarSesion = rootView.findViewById(R.id.ajustesBtnCerrarSesion);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Idiomas: Configura la opción de cambio de idioma entre castellano e inglés

        // Crear el arreglo con el título y las opciones
        String[] opciones = {"Seleccionar el idioma", "Castellano", "Inglés"};

        // Crear el ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Establecer el adaptador en el Spinner
        ajustesIdioma.setAdapter(adapter);

        // Configurar el listener para el evento de selección
        ajustesIdioma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                // Verificar cuál opción fue seleccionada y ejecutar el método correspondiente
                if(position == 1)
                {
                    opcionIdioma(requireContext(), "es"); // Si la opción 1 fue seleccionada
                }
                else if(position == 2)
                {
                    opcionIdioma(requireContext(), "en"); // Si la opción 2 fue seleccionada
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                // No hacer nada si no se selecciona ninguna opción
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        // Eliminación de Pokémon capturados: Añade un switch para activar o desactivar la eliminación de Pokémon desde la pestaña de Pokémon Capturados

        // About: incluye una sección “Acerca de” que abra un diálogo emergente que muestre el nombre del
        // desarrollador y la versión actual de la aplicación (ej., “Versión 1.0.0”)
        ajustesBtnAcercaDe.setOnClickListener(this::onClick);

        // Cerrar sesión: cerrará la sesión de autenticación con Firebase
        ajustesBtnCerrarSesion.setOnClickListener(this::onClick2);

        return rootView;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    // Método para configurar el Spinner según el idioma
    private void opcionIdioma(Context context, String idioma)
    {
        Toast.makeText(getContext(), "Ejecutando el idioma "+idioma+" .", Toast.LENGTH_SHORT).show();

        // Crear una nueva instancia de Locale con el idioma
        Locale nuevaLocalizacion = new Locale(idioma);
        Locale.setDefault(nuevaLocalizacion);

        // Cambiar la configuración del idioma
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(nuevaLocalizacion);

        // Aplicar la nueva configuración a los recursos
        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());

        // Recargar la actividad para aplicar el nuevo idioma
        // Esto hará que la actividad se reinicie y se carguen los recursos en el nuevo idioma
        Intent intent = new Intent(context, MainActivity.class); // O la actividad que desees
        context.startActivity(intent);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////
    // About: incluye una sección “Acerca de” que abra un diálogo emergente que muestre el nombre del
    // desarrollador y la versión actual de la aplicación (ej., “Versión 1.0.0”)
    private void mostrarAcercaDe()
    {
        assert getActivity() != null;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Acerca de");
        builder.setMessage("Desarrolladora:\nIrene Condado Alcantarilla\nVersión:\n1.0.0");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
    ////////////////////////////////////////////////////////////////////////////////////////////
    // Cerrar sesión: cerrará la sesión de autenticación con Firebase
    private void cerrarSesion()
    {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        // Cerrar la actividad actual
        assert getActivity() != null;
        getActivity().finish();
        Toast.makeText(getActivity(), "La sesión se ha cerrado.", Toast.LENGTH_SHORT).show();
    }

    private void onClick(View v) {
        mostrarAcercaDe();
    }

    private void onClick2(View v) {
        cerrarSesion();
    }
}