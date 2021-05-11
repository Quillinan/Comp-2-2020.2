import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EncontrarPar {

    static void encontrarPar(List<Integer> lista, int k)
    {
        Collections.sort(lista);
        int maior = lista.get(lista.size() - 1);
        for (int numero : lista)
        {
            int parte = k - numero;
            if (numero > k / 2 || numero == maior) {
                if (lista.contains(parte))
                {
                    System.out.printf("%d, %d\n", numero, parte);
                    break;
                }
            }
        }
    }

    //Teste
    public static void main(String[] args)
    {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(5);
        lista.add(-10);
        lista.add(56);
        lista.add(44);
        lista.add(12);
        lista.add(18);

        encontrarPar(lista,34);
    }
}