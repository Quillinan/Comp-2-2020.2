import java.util.*;

public class CaracterFrequencia {

    static void encontrarCaracterMaisFrequente(String string)
    {
        List<Character> listaDeCaracteres = interarString(string);
        Map<Character, Integer> quantidadeDeCaracter = new HashMap<>();
        int maiorVezes = 0;
        char maior = 'z';
        for(char caracter : listaDeCaracteres)
        {
            if(quantidadeDeCaracter.containsKey(caracter))
            {
                int vezesNovo = quantidadeDeCaracter.get(caracter) + 1;
                quantidadeDeCaracter.replace(caracter, vezesNovo);
                if(vezesNovo > maiorVezes)
                {
                    maiorVezes = vezesNovo;
                    maior = caracter;
                }
            }
            else{
                quantidadeDeCaracter.put(caracter, 1);
            }
        }
        System.out.printf(maior +", %d", quantidadeDeCaracter.get(maior));
    }

    public static List<Character> interarString(String palavra){
        List<Character> listaFinal = new ArrayList<>();
        for (int i = 0; i < palavra.length(); i++) {
            char caracter = palavra.toLowerCase(Locale.ROOT).charAt(i);
            listaFinal.add(caracter);
        }
        return listaFinal;
    }

    //Para Teste
    public static void main(String[] args){
        encontrarCaracterMaisFrequente("Arara");
    }
}
