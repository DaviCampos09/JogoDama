public class Jogo {
   private Jogador j1,j2;
   private Tabuleiro tabuleiro;


   public Jogo(Jogador j1, Jogador j2){
      this.j1 = j1;
      this.j2 = j2;
      this.tabuleiro = new Tabuleiro();
   }

   public String iniciarJogo(){
       tabuleiro.gerarTabuleiro();
       return "     =======JOGO DAMA=======\r\n\r\n"+tabuleiro.corpoTabuleiro();
   }


   public Casa verificaCasa(int x, int y){
      Casa[][] t = tabuleiro.getTab();
      return t[x][y];
   }

   public String mov(Casa orig, Casa dest){
      int x = orig.getX();
       int y = orig.getY();
       int paraX = dest.getX();
       int paraY = dest.getY();
       Casa[][] t = tabuleiro.getTab();

       if(orig.getOcupada() && !dest.getOcupada()){
        // for(int i=0;i<t.length;i++){
            //for(int j=0;j<t.length;j++){
               if(orig.getPeca().getCorPeca().equalsIgnoreCase("preta")){
                  t[paraX][paraY] = new Casa(orig.getPeca().getCorPeca(), true, orig.getPeca(), paraX, paraY);
                  t[x][y] = new Casa(dest.getPeca().getCorPeca(), false, dest.getPeca(), x, y);
                  
               }else{
                  t[paraX][paraY] = new Casa(orig.getPeca().getCorPeca(), true, orig.getPeca(), paraX, paraY);
                  t[x][y] = new Casa(dest.getPeca().getCorPeca(), false, dest.getPeca(), x, y);
               }
            //}
         //}
       }
       tabuleiro.attTab(t);
       return tabuleiro.corpoTabuleiro();
   }

}
