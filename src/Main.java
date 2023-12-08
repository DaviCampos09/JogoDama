public class Main {
   
    public static void main(String a[]){
        Jogador j1 = new Jogador("j1");
        Jogador j2 = new Jogador("j2");

        Jogo j = new Jogo(j1,j2);

        System.out.println(j.iniciarJogo());
        //System.out.println("\r\n\r\n");
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
        
        System.out.println(j.mov(j.verificaCasa(5, 4), j.verificaCasa(4, 5)));
        System.out.println("\r\n\r\n");
        System.out.println(j.mov(j.verificaCasa(2, 5), j.verificaCasa(3, 4)));
        System.out.println("\r\n\r\n");
        //System.out.println(j.mov(j.verificaCasa(6, 5), j.verificaCasa(5, 4)));
        //System.out.println("\r\n\r\n");
        System.out.println(j.verificaCaptura(j.verificaCasa(4,5), j.verificaCasa(3,4)));
    }

}
