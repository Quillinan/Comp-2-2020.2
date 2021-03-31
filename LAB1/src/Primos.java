import java.util.stream.IntStream; // Importei um método para criação e preenchimento de listas

public class Primos {

    /**
     * Retorna um array contendo exatamente os números primos em [1, n].
     * @param n O maior número a ser considerado
     * @return um array de inteiros com os primos no intervalo dado.
     */
    public static int[] obterPrimos(int n) {
        int[] listaNumeros;
        listaNumeros = IntStream.range(2, n+1).toArray();
        int c = 0; // contador de números primos
        for ( int numero = 2; numero <= n; numero++) {
            int c2 = 2; // partindo da
            while ( c2 <= numero/2 ) {
                if ( numero % c2 == 0) {
                    listaNumeros[numero-2] = 0;
                }
                c2++;
            }
            if ( listaNumeros[numero-2] != 0) {
                c++;
            }
        }
        int c3 = 0; // contador de elementos na lista de primos
        int[] listaNumerosPrimos = new int[c];
        for (int x : listaNumeros) { // função para retirar os elementos 0 do array
            if ( x != 0) {
                listaNumerosPrimos[c3] = x;
                c3++;
            }
        }


        return listaNumerosPrimos;
    }

    public static int[] obterPrimosViaCrivo(int n) {
        int[] listaNumeros;
        listaNumeros = IntStream.range(2, n+1).toArray(); // descartaremos o número 1 da lista seguindo o método do crivo
        int numero = 2; // numero inicial do array é 2
        int c = 0; // contador de primos
        while ( numero < n ) {
            int i = 1;
            while( (i + numero) <= n){
                if ( (i + numero) % numero == 0) {
                    listaNumeros[(i + numero - 2)] = 0;
                }
                i++;
            }
            if (listaNumeros[numero-2] != 0) {
                c++;
            }
            numero++;
        }
        int cp = 0; // contador de elementos na lista de primos
        int[] listaNumerosPrimos = new int[c]; // função para retirar os elementos 0 do array
        for (int x : listaNumeros) {
            if ( x != 0) {
                listaNumerosPrimos[cp] = x;
                cp++;
            }
        }

        return listaNumerosPrimos;
    }

    public static void main(String[] args) {

        for (int n = 10; n <= 10_000; n *= 10) {
            long inicio = System.currentTimeMillis();
            int[] obterPrimos = obterPrimos(n);
            long duracao = System.currentTimeMillis() - inicio;
            long inicioCrivo = System.currentTimeMillis();
            int[] obterPrimosViaCrivo = obterPrimos(n);
            long duracaoCrivo = System.currentTimeMillis() - inicioCrivo;
            System.out.printf("Duração da Obtenção de primos: %.3f segundos\n", duracao / 1000.0);
            System.out.printf("Duração da Obtenção de primos via crivo: %.3f segundos\n", duracao / 1000.0);
        }
    }
}
