/*Cinco lebres disputarão uma corrida. Cada lebre pode dar um salto
que varia de 1 a 3 metros de distância. A distância percorrida é de 20
metros. Na corrida, cada lebre dará um salto. Informar quantos
metros ela pulou a cada salto realizado. Em seguida, a lebre pára para
descansar por 2 segundos (sleep). Escreva um programa, utilizando
threads (uma para cada lebre), que informe a lebre vencedora e a
colocação de cada uma delas ao final da corrida. Informar, também,
quantos pulos cada uma delas deu.*/
import java.security.SecureRandom; 
import java.util.ArrayList; 
import java.util.Collections; 
import java.util.List;

public class JavaConcorrente2 {

    public static void main(String[] args) {

        Lebre l1 = new Lebre("Lebre 1");
        Lebre l2 = new Lebre("Lebre 2");
        Lebre l3 = new Lebre("Lebre 3");
        Lebre l4 = new Lebre("Lebre 4");
        Lebre l5 = new Lebre("Lebre 5");

        l1.start();
        l2.start();
        l3.start();
        l4.start();
        l5.start();

        try {
            l1.join();
            l2.join();
            l3.join();
            l4.join();
            l5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n==== RESULTADO FINAL ====");

        List<Lebre> ranking = Corrida.getClassificacao();

        for (int i = 0; i < ranking.size(); i++) {
            Lebre lebre = ranking.get(i);
            System.out.println((i + 1) + "º lugar: " + lebre.getLebreName() +
                    " | pulos: " + lebre.getPulos());
        }

        System.out.println("\nVencedora: " + ranking.get(0).getLebreName());
    }
}

class Lebre extends Thread {

    private String lebreName;
    private int distanciaPercorrida = 0;
    private int pulos = 0;

    public Lebre(String lebreName) {
        this.lebreName = lebreName;
    }

    public int getPulos() {
        return pulos;
    }

    public String getLebreName() {
        return lebreName;
    }

    @Override
    public void run() {
        SecureRandom random = new SecureRandom();

        while (distanciaPercorrida < 20) {

            int salto = random.nextInt(3) + 1; // 1 a 3
            distanciaPercorrida += salto;
            pulos++;

            System.out.println(lebreName + " pulou " + salto + " metros (total: " + distanciaPercorrida + ")");

            try {
                Thread.sleep(300); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Corrida.registrarChegada(this);
    }
}

class Corrida {
    private static List<Lebre> classificacao = Collections.synchronizedList(new ArrayList<>());

    public static void registrarChegada(Lebre lebre) {
        classificacao.add(lebre);
    }

    public static List<Lebre> getClassificacao() {
        return classificacao;
    }
}
