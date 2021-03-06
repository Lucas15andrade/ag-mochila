package algoritmogenetico;

import java.util.Arrays;
import java.util.Random;

public class Individuo implements Comparable<Individuo> {

    private final Random random = new Random();
    private int aptidao;

    //Um indivíduo é uma possível solução para o problema.
    //atributos do problema especifico
    private int PESO[] = {5, 4, 7, 8, 4, 4, 6, 8};
    private int VALOR[] = {3, 3, 2, 4, 2, 3, 5, 8};

    private int[] cromossomo = {0,1,0,1,1,1,0,1};

    //cria um individuo aleatorio da primeira geracao
    public Individuo() {
        do {
//			this.setPeso();
//			this.setValor();
            this.setCromossomo();
        } while (!validar());
        avaliar();
    }

    // cria um individuo a partir de genes definidos
    public Individuo(int[] novoCromossomo) {
//		qtdMilho = genes[0];
//		qtdSoja = genes[1];

        cromossomo = novoCromossomo;

        //testa se deve fazer mutacao
        if (random.nextDouble() <= Genetico.TAXADEMUTACAO) {
            int posAleatoria = random.nextInt(cromossomo.length); //define gene que sera mutado
            mutacao(posAleatoria);
        }
        avaliar();
    }

    private boolean validar() {
//		double proteina = 0.0851 * qtdMilho + 0.456 * qtdSoja;
//		double energia = 3146 * qtdMilho + 2283 * qtdSoja;

        int somaPeso = 0;
        //Validar = Peso
        for (int x = 0; x < 8; x++) {
            if (cromossomo[x] == 1) {
                somaPeso += PESO[x];
            }
        }
        if (somaPeso <= 25) {
            return true;
        } else {
            return false;
        }
        //return proteina >= 0.1716 && energia >= 3000;
    }

    private void mutacao(int posicao) {
        do {
            if (cromossomo[posicao] == 1) {
                cromossomo[posicao] = 0;
            } else {
                cromossomo[posicao] = 1;
            }
        } while (!validar());

    }

    private void setCromossomo() {
        //Criando um cromossomo aletatoriamente
        for (int x = 0; x < 8; x++) {
            if (random.nextBoolean()) {
                cromossomo[x] = 1;
            } else {
                cromossomo[x] = 0;
            }
        }
    }

//    private void setPeso() {
//        this.qtdMilho = random.nextDouble();
//    }
//
//    private void setValor() {
//        this.qtdSoja = random.nextDouble();
//    }

    public double getAptidao() {
        return aptidao;
    }

//    public double[] getGenes() {
//        return new double[]{qtdMilho, qtdSoja};
//    }

    public int[] getCromossomo(){
        
        int[] novoCromossomo = new int[cromossomo.length];
        novoCromossomo = cromossomo;
        
        return cromossomo;
    }
    
    private void avaliar() {
        
        //Avaliar = valor;
        int somaValor = 0;
        for (int x = 0; x < 8; x++) {
            if (cromossomo[x] == 1) {
                somaValor += VALOR[x];
            }
        }
        
        aptidao = somaValor;
    }

    @Override
    public String toString() {
        return "Cromossomo " + Arrays.toString(cromossomo) + " Aptidao: " + aptidao + "\n";
    }

    @Override
    public int compareTo(Individuo i) {
        return 1 /*this.aptidao.compareTo(i.aptidao)*/;
    }
}
