package src;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListaPokemon {
    private List<Pokemon> pokemons = new ArrayList<>();

    ListaPokemon(){


    }

    private static String getTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return "";
    }

    public List<Pokemon> getPokemons(){

        try {

            File xmlFile = new File("./src/pokedex.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);


            NodeList todoslosPokemon = doc.getElementsByTagName("pokemon");


            List<Pokemon> listaPokemon = new ArrayList<>();


            for (int i = 0; i < todoslosPokemon.getLength(); i++) {
                Element pokemonElement = (Element) todoslosPokemon.item(i);


                String nombre = getTextContent(pokemonElement, "nombre");
                String tipo = getTextContent(pokemonElement, "tipo");
                int vida = Integer.parseInt(getTextContent(pokemonElement, "vida"));
                int ataque = Integer.parseInt(getTextContent(pokemonElement, "ataque"));
                int defensa = Integer.parseInt(getTextContent(pokemonElement, "defensa"));
                int velocidad = Integer.parseInt(getTextContent(pokemonElement, "velocidad"));



                NodeList ataqueNodes = pokemonElement.getElementsByTagName("movimiento");
                List<Ataque> lat = new ArrayList<Ataque>();


                for (int j = 0; j < ataqueNodes.getLength(); j++) {
                    Element ataqueElement = (Element) ataqueNodes.item(j);


                    String nombreAtaque = getTextContent(ataqueElement, "nombreAtaque");
                    String tipoAtaque = getTextContent(ataqueElement, "tipoAtaque");
                    int poder = Integer.parseInt(getTextContent(ataqueElement, "poder"));
                    int precision = Integer.parseInt(getTextContent(ataqueElement, "precision"));


                    Ataque a = new Ataque(nombreAtaque, tipoAtaque, precision, poder);


                    lat.add(a);
                }
                Pokemon pokemon = new Pokemon(nombre, tipo, vida, ataque, defensa, velocidad,lat);


                listaPokemon.add(pokemon);
            }
            this.pokemons= listaPokemon;



        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.pokemons;

    }

}
