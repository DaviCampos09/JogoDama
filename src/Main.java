public class Main {
    public static void main(String a[]){
        Jogador j1 = new Jogador("Davi");
        Jogador j2 = new Jogador("Messi");

        Jogo j = new Jogo(j1,j2);

        System.out.println(j.iniciarJogo());


    }

}
