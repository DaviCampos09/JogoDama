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

   public Jogador getJ1(){
      return j1;
   }

   public Jogador getJ2(){
      return j2;
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
       tabuleiro.attTab(t);
       return tabuleiro.corpoTabuleiro();
   }


   public String verificaCaptura(Casa orig, Casa dest){
      int x = orig.getX();
       int y = orig.getY();
       int paraX = dest.getX();
       int paraY = dest.getY();
      Casa[][] t = tabuleiro.getTab();
    

      if(paraY>y){
       if(orig.getOcupada() && dest.getOcupada() && orig.getCorCasa().equalsIgnoreCase("branca")){
         if(!t[x - 2][y + 2].getOcupada()){
           t[x][y] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , paraX, paraY);
           t[x-2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y+2);  
         }else if(t[x -2][y + 2].getOcupada()){
            return "Nao tem como comer";
         }
       }else if(orig.getOcupada() && dest.getOcupada() && orig.getCorCasa().equalsIgnoreCase("preta")){
         if(!t[x + 2][y + 2].getOcupada()){
           t[x][y] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , paraX, paraY);
           t[x+2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y+2);  
         }else if(t[x + 2][y + 2].getOcupada()){
            return "Nao tem como comer";
         }
       }
      }else if(paraY<y){
       if(orig.getOcupada() && dest.getOcupada() && orig.getCorCasa().equalsIgnoreCase("branca")){
         if(!t[x - 2][y - 2].getOcupada()){
           t[x][y] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , paraX, paraY);
           t[x-2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y-2);  
         }else if(t[x -2][y - 2].getOcupada()){
            return "Nao tem como comer";
         }
       }else if(orig.getOcupada() && dest.getOcupada() && orig.getCorCasa().equalsIgnoreCase("preta")){
         if(!t[x + 2][y - 2].getOcupada()){
           t[x][y] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , x, y); 
           t[paraX][paraY] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , paraX, paraY);
           t[x+2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y-2);  
         }else if(t[x + 2][y - 2].getOcupada()){
            return "Nao tem como comer";
         }
       }



      }
      tabuleiro.attTab(t);
      return tabuleiro.corpoTabuleiro();

   }

   



}
