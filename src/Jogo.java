import java.util.ArrayList;

public class Jogo {
   private Jogador j1,j2;
   private Tabuleiro tabuleiro;
   private Dama d;
   private ArrayList<Casa> capturas = new ArrayList<Casa>();



   public Jogo(Jogador j1, Jogador j2){
      this.j1 = j1;
      this.j2 = j2;
      this.tabuleiro = new Tabuleiro();
      this.d = new Dama("nula");
   }

   public String iniciarJogo(){
       tabuleiro.gerarTabuleiro();
       return "     =======JOGO DAMA=======\r\n\r\n"+tabuleiro.corpoTabuleiro();
   }

   public Jogador getJ1(){
      return j1;
   }

   public Jogador getJ2(){
      return j2;
   }

   public ArrayList<Casa> getCapturas(){
      return capturas;
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
               if(orig.getPeca().getCorPeca().equalsIgnoreCase("preta")){
                  t[paraX][paraY] = new Casa(orig.getPeca().getCorPeca(), true, orig.getPeca(), paraX, paraY);
                  t[x][y] = new Casa(dest.getPeca().getCorPeca(), false, dest.getPeca(), x, y);
                  
               }else{
                  t[paraX][paraY] = new Casa(orig.getPeca().getCorPeca(), true, orig.getPeca(), paraX, paraY);
                  t[x][y] = new Casa(dest.getPeca().getCorPeca(), false, dest.getPeca(), x, y);
               }
       }
       d.tranformaDama(t);
       
       tabuleiro.attTab(t);
       return tabuleiro.corpoTabuleiro();
   }


   public ArrayList<Casa> verificacao(Jogador jo) {
      capturas.clear();
    
      Casa[][] t = tabuleiro.getTab();
  
      if(jo.getCorJogador().equalsIgnoreCase("branca")){
         for (int i = 0; i < t.length; i++) {
          for (int j = 0; j < t.length; j++) {
              Casa orig = t[i][j];
              int x = orig.getX();
              int y = orig.getY();
  
              if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
                  if (orig.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("branca")) {
                      // Verificar captura na diagonal esquerda superior
                      if (x - 2 >= 0 && y - 2 >= 0 && !t[x - 2][y - 2].getOcupada() && t[x - 1][y - 1].getOcupada() &&
                              t[x - 1][y - 1].getPeca().getCorPeca().equalsIgnoreCase("preta")) {
                           capturas.add(t[x][y]);
                      }
                      // Verificar captura na diagonal direita superior
                      if (x - 2 >= 0 && y + 2 < 8 && !t[x - 2][y + 2].getOcupada() && t[x - 1][y + 1].getOcupada() &&
                              t[x - 1][y + 1].getPeca().getCorPeca().equalsIgnoreCase("preta")) {
                              
                           capturas.add(t[x][y]);   
                      }

                      // Verificar captura na diagonal esquerda inferior
                      if (x + 2 < t.length && y - 2 >= 0 && !t[x + 2][y - 2].getOcupada() && t[x + 1][y - 1].getOcupada() &&
                              t[x + 1][y - 1].getPeca().getCorPeca().equalsIgnoreCase("preta")) {
                           capturas.add(t[x][y]);
                      }

                       // Verificar captura na diagonal direita inferior
                      if (x + 2 < t.length && y + 2 < t.length && !t[x + 2][y + 2].getOcupada() && t[x + 1][y + 1].getOcupada() &&
                              t[x + 1][y + 1].getPeca().getCorPeca().equalsIgnoreCase("preta")) {
                           capturas.add(t[x][y]);
                      }
                     
                  }
              }
            }
         }  
         return capturas;
      }else if(jo.getCorJogador().equalsIgnoreCase("preta")){
          for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t.length; j++) {
              Casa orig = t[i][j];
              int x = orig.getX();
              int y = orig.getY();
               
              if (orig.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("preta")) {
                      // Verificar captura na diagonal esquerda inferior
                      if (x + 2 < t.length && y - 2 >= 0 && !t[x + 2][y - 2].getOcupada() && t[x + 1][y - 1].getOcupada() &&
                              t[x + 1][y - 1].getPeca().getCorPeca().equalsIgnoreCase("branca")) {
                          capturas.add(t[x][y]);
                      }
                      // Verificar captura na diagonal direita inferior
                      if (x + 2 < t.length && y + 2 < t.length && !t[x + 2][y + 2].getOcupada() && t[x + 1][y + 1].getOcupada() &&
                              t[x + 1][y + 1].getPeca().getCorPeca().equalsIgnoreCase("branca")) {
                          capturas.add(t[x][y]);
                      }

                      // Verificar captura na diagonal esquerda superior
                      if (x - 2 >= 0 && y - 2 >= 0 && !t[x - 2][y - 2].getOcupada() && t[x - 1][y - 1].getOcupada() &&
                              t[x - 1][y - 1].getPeca().getCorPeca().equalsIgnoreCase("branca")) {
                          capturas.add(t[x][y]);
                      }

                      // Verificar captura na diagonal direita superior
                      if (x - 2 >= 0 && y + 2 < 8 && !t[x - 2][y + 2].getOcupada() && t[x - 1][y + 1].getOcupada() &&
                              t[x - 1][y + 1].getPeca().getCorPeca().equalsIgnoreCase("branca")) {
                          capturas.add(t[x][y]);
                      }
                     
                  }
            }
         } 
         return capturas;   
      }        

  
      return null;
  }

   public String darParaCapturar(Jogador jo){
      if(verificacao(jo)!= null && !capturas.isEmpty()){
         if(capturas.size()==1){
            return "A peca "+capturas.get(0).formaCasa()+" tem obrigacao de realizar uma captura\r\n";
         }else{
            StringBuilder capturasStr = new StringBuilder("As pecas ");
            for (Casa casa : capturas) {
                capturasStr.append(casa.formaCasa()).append(", ");
            }
            capturasStr.delete(capturasStr.length() - 2, capturasStr.length()); // Remover a última vírgula e espaço
            capturasStr.append(" sao obrigadas a realizar uma captura. Escolha uma.\r\n");
            return capturasStr.toString();
         }   
      }
      return "";
   }

   public String verificaCaptura(Casa orig, Casa dest){
      int x = orig.getX();
      int y = orig.getY();
      int paraX = dest.getX();
      int paraY = dest.getY();
      Casa[][] t = tabuleiro.getTab();
    
      if(paraY>y){
       if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("branca")){
         if(!t[x - 2][y + 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , paraX, paraY);
           t[x-2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y+2);  
         }else if(!t[x + 2][y + 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , paraX, paraY);
           t[x+2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y+2);
         }else if(t[x -2][y + 2].getOcupada() || t[x + 2][y + 2].getOcupada()){
            return "Nao tem como comer";
         }
       }else if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("preta")){
         if(!t[x + 2][y + 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , paraX, paraY);
           t[x+2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y+2);  
         }else if(!t[x - 2][y + 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , paraX, paraY);
           t[x-2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y+2);
         }else if(t[x + 2][y + 2].getOcupada() || t[x - 2][y + 2].getOcupada()){
            return "Nao tem como comer";
         }
       }
      }else if(paraY<y){
       if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("branca")){
         if(!t[x - 2][y - 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , paraX, paraY);
           t[x-2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y-2);  
         }else if(!t[x + 2][y - 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , paraX, paraY);
           t[x+2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y-2); 
         }else if(t[x - 2][y - 2].getOcupada() || t[x + 2][y - 2].getOcupada()){
            return "Nao tem como comer";
         }
       }else if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("preta")){
         if(!t[x + 2][y - 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , paraX, paraY);
           t[x+2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y-2);  
         }else if(!t[x - 2][y - 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , paraX, paraY);
           t[x-2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y-2);
         }else if(t[x + 2][y - 2].getOcupada() || t[x - 2][y - 2].getOcupada()){
            return "Nao tem como comer";
         }
       }
      }

      d.tranformaDama(t);
      tabuleiro.attTab(t);
      return tabuleiro.corpoTabuleiro();

   }

   public boolean acabouJogo(){
      Casa[][] t = tabuleiro.getTab();
      int contP=0, contB=0;

      for (int i = 0; i < t.length; i++) {
          for (int j = 0; j < t.length; j++) {
             if(t[i][j].getPeca().getCorPeca().equalsIgnoreCase("preta")){
                  contP++;
             }
             if(t[i][j].getPeca().getCorPeca().equalsIgnoreCase("branca")){
                  contB++;
             }

          }
      }          

      if(contP==0 || contB==0){
               return true;
      }else{
         return false;
      }

   }



}
