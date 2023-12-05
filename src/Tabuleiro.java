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


 public Peca[] getPecas(){
   return pecas;
 }

 public void gerarTabuleiro(){   
   for(int i=0;i<tab.length;i++){
      for(int j=0;j<tab.length;j++){
         if(i%2 == 0 && j%2 != 0 && i<3 && j<8 || i%2 != 0 && j%2 == 0 && i<3 && j<8){
            Peca p = new Peca("preta");
            tab[i][j] = new Casa(p.getCorPeca(), true, p, i, j);
            
         }else if(i%2 != 0 && j%2 == 0 && i>4 && j<8 || i%2 == 0 && j%2 != 0 && i>4 && j<8){
            Peca p = new Peca("branca");
            tab[i][j] = new Casa(p.getCorPeca(), true, p, i, j);
         }else {
            Peca p = new Peca("nula");
            tab[i][j] = new Casa(p.getCorPeca(), false,p, i, j);
        }
      }
   }
 }



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
