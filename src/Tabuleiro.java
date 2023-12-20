public class Tabuleiro {
 private Casa[][] tab = new Casa[8][8];
 private Peca[] pecas;


 public Tabuleiro(){
    for(int i=0; i<tab.length;i++){
      for(int j=0; j<tab.length;j++){
       if(i%2 == 0 && j%2 != 0){
         tab[i][j] = new Casa("preta", false, i, j);
       }else{
         tab[i][j] = new Casa("branca", false, i, j);
       }
      }
    }
    this.pecas = new Peca[64];
 }

 public Casa[][] getTab(){
    return tab;
 }

 //metodo usado para atualizar o tabuleiro apos ser realizado algum movimento ou captura.
 public void attTab(Casa[][] t){
   tab = t;
 }

 public Peca[] getPecas(){
   return pecas;
 }

 //neste metodo ele inicializa o tabuleiro, com as pecas brancas e pretas em seu devido lugar.
 public void gerarTabuleiro(){   
   for(int i=0;i<tab.length;i++){
      for(int j=0;j<tab.length;j++){
         if(i%2 == 0 && j%2 != 0 && i<3 && j<8 || i%2 != 0 && j%2 == 0 && i<3 && j<8){
            Peca p = new Peca("preta");
            tab[i][j] = new Casa(p.getCorPeca(), true, p, i, j);//a primeira parte do tabuleiro eh preenchida com
                                                                        //as pecas pretas tendo seus lugares seguindo as
                                                                        //condicoes do if acima.
            
         }else if(i%2 != 0 && j%2 == 0 && i>4 && j<8 || i%2 == 0 && j%2 != 0 && i>4 && j<8){
            Peca p = new Peca("branca");
            tab[i][j] = new Casa(p.getCorPeca(), true, p, i, j);//a parte final do tabuleiro eh preenchida com
                                                                        //as pecas brancas tendo seus lugares seguindo as
                                                                        //condicoes do if acima. 
         }else {
            Peca p = new Peca("nula");
            tab[i][j] = new Casa(p.getCorPeca(), false,p, i, j);//a parte do meio do tabuleiro e as demais casas
                                                                        //que nao foram preenchidas, sao preenchidas por
                                                                        //casas sem cor, isto eh, vazias.
        }
      }
   }
 }


//metodo usado para criar uma estrutura que simule um tabuleiro
public String corpoTabuleiro() {
   StringBuilder tabuleiroString = new StringBuilder();
   
   for (int i = 0; i < tab.length; i++) {
       if (i > 0) {
         tabuleiroString.append("----+---+---+---+---+---+---+----\n");
     }
      tabuleiroString.append("| ");
       for (int j = 0; j < tab[i].length; j++) {
           Peca p = tab[i][j].getPeca();
 
           tabuleiroString.append(tab[i][j].mostraCasa(p)).append(" | ");
       }
       tabuleiroString.append("\n");
   }

   return tabuleiroString.toString();
}


}
