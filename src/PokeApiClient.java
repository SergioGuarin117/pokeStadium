import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

    // Consulta del programa a la PokeAPI
public class PokeApiClient {
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String consultarRespuesta(String nombre)
        throws IOException, InterruptedException{

        String url = "https://pokeapi.co/api/v2/pokemon/" + nombre.toLowerCase().trim();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404){
            throw new IOException("Pokemon no encontrado");
        }
        if (response.statusCode()== 400){
            throw  new IOException("Error de red: HTTP "+response.statusCode());
        }
        return response.body();

    }

    // La respuesta de la PokeAPI Para traer los datos.
    public Pokemon consultarPokemon(String nombre)
        throws IOException, InterruptedException{
        JSONObject datos = new JSONObject(consultarRespuesta(nombre));
        String nombreApi = datos.getString("name");

        String sprite = datos.getJSONObject("sprites").optString("front_default");

        JSONArray listaEstadisticas = datos.getJSONArray("stats");

        int hp = 0;
        int ataque = 0;
        int defensa = 0;
        int velocidad = 0;

        for (int i=0; i < listaEstadisticas.length(); i++){
            JSONObject statInfo = listaEstadisticas.getJSONObject(i);
            String nombreStat = statInfo.getJSONObject("stat").getString("name");
            int valor = statInfo.getInt("base_stat");

            if (nombreStat.equals("hp")) {
                hp = valor;
            }else if (nombreStat.equals("attack")){
                ataque = valor;
            }else if (nombreStat.equals("defense")){
                defensa = valor;
            }else if (nombreStat.equals("speed")){
                velocidad = valor;
            }
        }

        return new Pokemon(nombreApi,"",sprite, hp, ataque, defensa, velocidad);

    }

}
