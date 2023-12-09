import java.util.Scanner;

public class Main {
    public static void main(String a[]){
        Jogador j1,j2;
        int cont=0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Olá, bem vindo. Deseja:\r\n[1]Iniciar jogo\r\n[0]Sair");
        int opc = sc.nextInt();

        
         switch (opc) {
            case 1:
                System.out.println("Digite o nome do jogador 1:");
                String n1 = sc.next();
                System.out.println("Agora o nome do jogador 2:");
                String n2 = sc.next();
                System.out.println("Qual jogador ficara com as pecas brancas?\r\n[1]Jogador 1\r\n[2]Jogador 2");
                int b = sc.nextInt();

                /*if(b == 1){
                  j1 = new Jogador(n1);
                  j2 = new Jogador(n2);
                }else{
                  j1 = new Jogador(n1);
                  j2 = new Jogador(n2);
                  
                }*/

                j1 = new Jogador(n1);
                j2 = new Jogador(n2);
                
                
                Jogo j = new Jogo(j1, j2);
                
                System.out.println(j.iniciarJogo());
               //do{
                if(cont%2==0){
                    System.out.println("Vez do jogador "+j1.getNome());
                    System.out.println("Digite a linha da peca que deseja mover:");
                    int x = sc.nextInt();
                    System.out.println("Agora a coluna da peca que deseja mover:");
                    int y = sc.nextInt();
                    System.out.println("Digite a linha da peca para onde deseja mover:");
                    int paraX = sc.nextInt();
                    System.out.println("Agora a coluna da peca para onde deseja mover:");
                    int paraY = sc.nextInt();
                    
                    System.out.println(j.mov(j.verificaCasa(x, y), j.verificaCasa(paraX, paraY)));
                    cont++;
                }else{
                    System.out.println("Vez do jogador"+j2.getNome());
                    System.out.println("Digite a linha da peca que deseja mover:");
                    int x = sc.nextInt();
                    System.out.println("Agora a coluna da peca que deseja mover:");
                    int y = sc.nextInt();
                    System.out.println("Digite a linha da peca para onde deseja mover:");
                    int paraX = sc.nextInt();
                    System.out.println("Agora a coluna da peca para onde deseja mover:");
                    int paraY = sc.nextInt();
                    
                    System.out.println(j.mov(j.verificaCasa(x, y), j.verificaCasa(paraX, paraY)));
                    cont++;
                }
                 

                break;
           // }while();
         




            default:
                break;
         } 



   

        
                


        /*Jogador j1 = new Jogador("j1");
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
        System.out.println(j.verificaCaptura(j.verificaCasa(4,5), j.verificaCasa(3,4)));*/
        sc.close();
    }

}
