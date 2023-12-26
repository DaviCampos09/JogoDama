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

   //metodo que inicia o jogo retornando o tabuleiro inicial.
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

   //metodo criado para ter uma forma de passar as coordenadas x,y de uma casa do tabuleiro e, assim, retornar esta casa
   //para poder ter acesso a uma casa especifica qualquer. 
   public Casa verificaCasa(int x, int y){
      Casa[][] t = tabuleiro.getTab();
      return t[x][y];
   }


   //metodo responsavel por realizar os movimentos das pecas e retornar o tabuleiro atualizado apos 
   //as movimentacoes serem realizadas.
   public String mov(Casa orig, Casa dest){
       int x = orig.getX();
       int y = orig.getY();
       int paraX = dest.getX();
       int paraY = dest.getY();
       Casa[][] t = tabuleiro.getTab();


       //se a casa de origem estiver ocupada e a de destino desocupada entao o movimento sera realizado.
       if(orig.getOcupada() && !dest.getOcupada()){
            t[paraX][paraY] = new Casa(orig.getPeca().getCorPeca(), true, orig.getPeca(), paraX, paraY);//a casa de destino
                                                                                                                //"vira" a casa de origem.

            t[x][y] = new Casa(dest.getPeca().getCorPeca(), false, dest.getPeca(), x, y);//a casa de origem "vira" a casa de destino,
                                                                                                 //isto eh, fica vazia. 
       }
    
       d.transformaDama(t);//verifica caso haja dama apos o movimento.
       tabuleiro.attTab(t);//atualiza o tabuleiro apos o movimento.
       return tabuleiro.corpoTabuleiro();
   }


   // Método para verificar as possíveis capturas de uma dama
public void verificacaoDama(int x, int y, String corDama) {
   //capturas.clear(); // Limpa a lista de capturas

   Casa[][] t = tabuleiro.getTab();

   // Define os passos para as diagonais
   int[][] passos = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

   
   // Itera sobre os passos para as diagonais
   for (int k = 0; k < passos.length; k++) {
       int passoX = passos[k][0];
       int passoY = passos[k][1];

       int i = x + passoX;
       int j = y + passoY;

       // Itera sobre as posições na diagonal
       while (i >= 0 && i < t.length && j >= 0 && j < t[i].length) {
           Casa casaAtual = t[i][j];

           // Verifica se a casa atual está ocupada
           if (casaAtual.getOcupada()) {
               Peca pecaAtual = casaAtual.getPeca();

               // Verifica se a peça na casa atual é de cor diferente da dama
               if (!pecaAtual.getCorPeca().equalsIgnoreCase(corDama)) {
                   // Calcula a posição após a peça atual na diagonal
                   int posicaoAposPecaX = i + passoX;
                   int posicaoAposPecaY = j + passoY;

                   // Verifica se a posição após a peça atual está dentro dos limites do tabuleiro
                   if (posicaoAposPecaX >= 0 && posicaoAposPecaX < 8 &&
                       posicaoAposPecaY >= 0 && posicaoAposPecaY < 8) {
                       
                       Casa casaAposPeca = t[posicaoAposPecaX][posicaoAposPecaY];

                       // Verifica se a posição após a peça atual está livre
                       if (!casaAposPeca.getOcupada()) {
                           capturas.add(t[x][y]); // Adiciona a posição após a captura à lista de capturas
                           //break; // Sai do loop, pois a captura foi encontrada
                       }else{
                        break;
                       }
                   }
               } else {
                   break; // Sai do loop, pois encontrou uma peça da mesma cor (não é possível capturar)
               }
           }

           i += passoX;
           j += passoY;
       }
   }
   

   //return capturas; // Retorna a lista com as possíveis capturas da dama
} 
    

/*public ArrayList<Casa> verificacaoDama(int x, int y, String corDama) {
   capturas.clear(); // Limpa a lista de capturas

   Casa[][] t = tabuleiro.getTab();

   // Define os passos para as diagonais
   int[][] passos = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

   // Itera sobre os passos para as diagonais
   for (int[] passo : passos) {
       int passoX = passo[0];
       int passoY = passo[1];

       int i = x + passoX;
       int j = y + passoY;

       boolean encontrouPecaDaMesmaCor = false;

       // Itera sobre as posições na diagonal
       while (i >= 0 && i < t.length && j >= 0 && j < t[i].length) {
           Casa casaAtual = t[i][j];

           // Verifica se a casa atual está ocupada
           if (casaAtual.getOcupada()) {
               Peca pecaAtual = casaAtual.getPeca();

               // Verifica se a peça na casa atual é de cor diferente da dama
               if (!pecaAtual.getCorPeca().equalsIgnoreCase(corDama)) {
                   // Calcula a posição após a peça atual na diagonal
                   int posicaoAposPecaX = i + passoX;
                   int posicaoAposPecaY = j + passoY;

                   // Verifica se a posição após a peça atual está dentro dos limites do tabuleiro
                   if (posicaoAposPecaX >= 0 && posicaoAposPecaX < t.length &&
                       posicaoAposPecaY >= 0 && posicaoAposPecaY < t[i].length) {

                       Casa casaAposPeca = t[posicaoAposPecaX][posicaoAposPecaY];

                       // Verifica se a posição após a peça atual está livre
                       if (!casaAposPeca.getOcupada() && casaAposPeca.getX()>=0 && casaAposPeca.getY()>=0 && casaAposPeca.getX()<8 && casaAposPeca.getY()<8) {
                           capturas.add(t[x][y]); // Adiciona a posição após a captura à lista de capturas
                       }
                   }
               } else {
                   encontrouPecaDaMesmaCor = true;
                   break; // Sai do loop, pois encontrou uma peça da mesma cor (não é possível capturar)
               }
           }

           i += passoX;
           j += passoY;
       }
   }

   return capturas; // Retorna a lista com as possíveis capturas da dama
}*/




   //metodo para verificar as possiveis capturas.
   public ArrayList<Casa> verificacao(Jogador jo) {
      capturas.clear();
    
      Casa[][] t = tabuleiro.getTab();
  

      //caso esteja na vez do jogador com as pecas brancas entao ira verificar as opcoes de captura apenas
      //dentre as pecas brancas.
      if(jo.getCorJogador().equalsIgnoreCase("branca")){
         for (int i = 0; i < t.length; i++) {
          for (int j = 0; j < t.length; j++) {
              Casa orig = t[i][j];
              int x = orig.getX();
              int y = orig.getY();

           //se a peca for uma dama entao a verificacao dela eh feita de forma diferente.
           if(orig.getPeca() instanceof Dama){
             verificacaoDama(orig.getX(), orig.getY(),jo.getCorJogador());
          

              //quando a origem esta ocupada e sua peca eh branca.
           }else if (orig.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("branca")) {
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
         return capturas;//retorna a lista com todas possiveis capturas das pecas brancas.


       //caso esteja na vez do jogador com as pecas pretas entao ira verificar as opcoes de captura apenas
       //dentre as pecas pretas.  
      }else if(jo.getCorJogador().equalsIgnoreCase("preta")){
          for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t.length; j++) {
              Casa orig = t[i][j];
              int x = orig.getX();
              int y = orig.getY();

            //se a peca for uma dama entao a verificacao dela eh feita de forma diferente.
            if(orig.getPeca() instanceof Dama){
             verificacaoDama(orig.getX(), orig.getY(),jo.getCorJogador());

              //quando a origem esta ocupada e sua peca eh preta. 
            }else if (orig.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("preta")) {
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
         return capturas;//retorna a lista com todas possiveis capturas das pecas pretas.  
      }        

  
      return null;//retorna null caso haja algum problema e nao entre em nenhum if.
  }


   


   //metodo para retornar uma string que informa as opcoes de captura existentes para o jogador.
   public String darParaCapturar(Jogador jo){

      //se o metodo responsavel por verificar as capturas nao teve nenhum problema ou se a lista capturas nao esta
      //vazia entao eh preciso retornar as opcoes de captura.
      if(verificacao(jo)!= null && !capturas.isEmpty()){

         //caso a lista tenha apenas um elemento entao so tem uma opcao de captura.
         if(capturas.size()==1){
            return "A peca "+capturas.get(0).formaCasa()+" tem obrigacao de realizar uma captura\r\n";//retorna o primeiro
                                                                                                            //elemento da lista
           
           
          //caso haja mais de um elemento na lista entao eh preciso retornar todas as opcoes de captura.                                                                                                  //como unica opcao.
         }else{
            StringBuilder capturasStr = new StringBuilder("As pecas ");
            for (Casa casa : capturas) {
                capturasStr.append(casa.formaCasa()).append(", ");//percorre toda a lista para poder retornar todas opcoes.
            }
            capturasStr.delete(capturasStr.length() - 2, capturasStr.length()); // Remove a ultima virgula e espaco.
            capturasStr.append(" sao obrigadas a realizar uma captura. Escolha uma.\r\n");
            return capturasStr.toString();
         }   
      }
      
      return "";//caso tenha tido um problema no metodo de verificar as capturas ou a lista esteja vazia, retorna um caractere vazio.
   }



   //metodo para captura realizada por damas.
/*public String realizarCapturaDama(Casa orig, Casa dest) {
   int x = orig.getX();
   int y = orig.getY();
   int paraX = dest.getX();
   int paraY = dest.getY();
   Casa[][] t = tabuleiro.getTab();

   //quando a origem realmente possui uma peca e eh uma dama.
   if (orig.getOcupada() && orig.getPeca() instanceof Dama) {
      if(paraY>y){
       //se a origem e a casa a ser eliminada estao ocupadas e a peca da origem eh branca.  
       if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("preta")){
         //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh menor que o x da origem.
         if(!t[x - 2][y + 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , x, y);//casa origem fica vazia.
           t[paraX][paraY] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x-2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y+2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem. 

          //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh maior que o x da origem.                                                                                            
         }else if(!t[x + 2][y + 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x+2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y+2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.

          //quando a casa apos a casa a ser eliminada esta ocupada.                                                                                            
         }else if(t[x -2][y + 2].getOcupada() || t[x + 2][y + 2].getOcupada()){
            return "Nao tem como comer";//nao eh possivel realizar a captura.
         }

         //se a origem e a casa a ser eliminada estao ocupadas e a peca da origem eh preta.
       }else if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("branca")){
         //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh maior que o x da origem.
         if(!t[x + 2][y + 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x+2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y+2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem. 
           
          //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh menor que o x da origem. 
         }else if(!t[x - 2][y + 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x-2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y+2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.

          //quando a casa apos a casa a ser eliminada esta ocupada. 
         }else if(t[x + 2][y + 2].getOcupada() || t[x - 2][y + 2].getOcupada()){
            return "Nao tem como comer";//nao eh possivel realizar a captura.
         }
       }
       //quando a casa a ser eliminada esta a esquerda da casa origem.
      }else if(paraY<y){
         //se a origem e a casa a ser eliminada estao ocupadas e a peca da origem eh preta.
       if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("preta")){
          //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh menor que o x da origem.
         if(!t[x - 2][y - 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x-2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y-2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.  

           //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh maior que o x da origem. 
         }else if(!t[x + 2][y - 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x+2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y-2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.
                                                                                                      
          //quando a casa apos a casa a ser eliminada esta ocupada.                                                                                            
         }else if(t[x - 2][y - 2].getOcupada() || t[x + 2][y - 2].getOcupada()){
            return "Nao tem como comer";//nao eh possivel realizar a captura.
         }
       }else if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("branca")){
          //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh maior que o x da origem.
         if(!t[x + 2][y - 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x+2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y-2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.  
         }else if(!t[x - 2][y - 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x-2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y-2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.

          //quando a casa apos a casa a ser eliminada esta ocupada.                                                                                            
         }else if(t[x + 2][y - 2].getOcupada() || t[x - 2][y - 2].getOcupada()){
            return "Nao tem como comer";//nao eh possivel realizar a captura.
         }
       }
      }

   }

   d.transformaDama(t);//verifica caso haja dama apos a captura.
   tabuleiro.attTab(t);//atualiza o tabuleiro apos a captura.
   return tabuleiro.corpoTabuleiro();
         
}*/


public String realizarCapturaDama(Casa orig, Casa dest) {
   int x = orig.getX();
   int y = orig.getY();
   int paraX = dest.getX();
   int paraY = dest.getY();
   Casa[][] t = tabuleiro.getTab();

   // Quando a origem realmente possui uma peça e é uma dama.
   if (orig.getOcupada() && orig.getPeca() instanceof Dama) {
       // Verificar todas as direções possíveis (cima/direita, cima/esquerda, baixo/direita, baixo/esquerda).
       int[] direcaoX = {-1, -1, 1, 1};
       int[] direcaoY = {1, -1, 1, -1};

       for (int i = 0; i < direcaoX.length; i++) {
           int passoX = direcaoX[i];
           int passoY = direcaoY[i];

           int j = x + passoX;
           int k = y + passoY;

           // Percorrer a diagonal até encontrar uma peça ou atingir o destino.
           while (j+passoX >= 0 && j+passoY < t.length && k+passoX >= 0 && k+passoY < t.length) {
               if (t[j][k].getOcupada()) {
                   // Se a casa contiver uma peça de cor diferente e a próxima casa estiver vazia, realizar a captura.
                   if (!t[j][k].getPeca().getCorPeca().equalsIgnoreCase(orig.getPeca().getCorPeca()) && !t[j + passoX][k + passoY].getOcupada()) {
                       t[x][y] = new Casa("nula", false, t[j+passoX][k+passoY].getPeca(), x, y); // Casa origem fica vazia.
                       t[paraX][paraY] = new Casa("nula", false, t[j+passoX][k+passoY].getPeca(), j + passoX, k + passoY); // Casa a ser eliminada fica vazia.
                       t[j + passoX][k + passoY] = new Casa(orig.getPeca().getCorPeca(), true, orig.getPeca(), j + passoX, k + passoY); // Casa após a casa a ser eliminada "vira" a casa origem.

                       // Atualiza o tabuleiro após a captura.
                       d.transformaDama(t);
                       tabuleiro.attTab(t);
                       return tabuleiro.corpoTabuleiro();
                   } else {
                       // Se a peça na diagonal não for de cor diferente ou a próxima casa não estiver vazia, continue verificando.
                       break;
                   }
               }

               j += passoX;
               k += passoY;
           }
       }
   }

   // Caso as condições acima não sejam atendidas, não é possível realizar a captura.
   return "Não é possível realizar a captura.";
}







   //metodo para realizar as capturas quando necessario e retornar o tabuleiro atualizado apos estas capturas serem
   //feitas.
   public String verificaCaptura(Casa orig, Casa dest){
      int x = orig.getX();
      int y = orig.getY();
      int paraX = dest.getX();
      int paraY = dest.getY();
      Casa[][] t = tabuleiro.getTab();
    
      if(orig.getPeca() instanceof Dama){
         realizarCapturaDama(orig, dest);


       //quando a casa a ser eliminada esta a direita da casa origem.  
      }else if(paraY>y){
       //se a origem e a casa a ser eliminada estao ocupadas e a peca da origem eh branca.  
       if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("branca")){
         //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh menor que o x da origem.
         if(!t[x - 2][y + 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , x, y);//casa origem fica vazia.
           t[paraX][paraY] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x-2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y+2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem. 

          //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh maior que o x da origem.                                                                                            
         }else if(!t[x + 2][y + 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x+2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y+2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.

          //quando a casa apos a casa a ser eliminada esta ocupada.                                                                                            
         }else if(t[x -2][y + 2].getOcupada() || t[x + 2][y + 2].getOcupada()){
            return "Nao tem como comer";//nao eh possivel realizar a captura.
         }

         //se a origem e a casa a ser eliminada estao ocupadas e a peca da origem eh preta.
       }else if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("preta")){
         //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh maior que o x da origem.
         if(!t[x + 2][y + 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x+2][y+2].getPeca().getCorPeca(),false,t[x+2][y+2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x+2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y+2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem. 
           
          //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh menor que o x da origem. 
         }else if(!t[x - 2][y + 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x-2][y+2].getPeca().getCorPeca(),false,t[x-2][y+2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x-2][y+2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y+2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.

          //quando a casa apos a casa a ser eliminada esta ocupada. 
         }else if(t[x + 2][y + 2].getOcupada() || t[x - 2][y + 2].getOcupada()){
            return "Nao tem como comer";//nao eh possivel realizar a captura.
         }
       }
       //quando a casa a ser eliminada esta a esquerda da casa origem.
      }else if(paraY<y){
         //se a origem e a casa a ser eliminada estao ocupadas e a peca da origem eh branca.
       if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("branca")){
          //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh menor que o x da origem.
         if(!t[x - 2][y - 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x-2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y-2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.  

           //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh maior que o x da origem. 
         }else if(!t[x + 2][y - 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x+2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y-2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.
                                                                                                      
          //quando a casa apos a casa a ser eliminada esta ocupada.                                                                                            
         }else if(t[x - 2][y - 2].getOcupada() || t[x + 2][y - 2].getOcupada()){
            return "Nao tem como comer";//nao eh possivel realizar a captura.
         }
       }else if(orig.getOcupada() && dest.getOcupada() && orig.getPeca().getCorPeca().equalsIgnoreCase("preta")){
          //quando a casa apos a casa a ser eliminada esta desocupada e o x da casa a ser eliminada eh maior que o x da origem.
         if(!t[x + 2][y - 2].getOcupada() && paraX>x){
           t[x][y] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x+2][y-2].getPeca().getCorPeca(),false,t[x+2][y-2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x+2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x+2, y-2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.  
         }else if(!t[x - 2][y - 2].getOcupada() && paraX<x){
           t[x][y] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , x, y);//casa origem fica vazia. 
           t[paraX][paraY] = new Casa(t[x-2][y-2].getPeca().getCorPeca(),false,t[x-2][y-2].getPeca() , paraX, paraY);//casa a ser eliminada fica vazia.
           t[x-2][y-2] = new Casa(orig.getPeca().getCorPeca(), true,orig.getPeca() ,x-2, y-2);//casa apos a casa a ser eliminada
                                                                                                      //"vira" a casa origem.

          //quando a casa apos a casa a ser eliminada esta ocupada.                                                                                            
         }else if(t[x + 2][y - 2].getOcupada() || t[x - 2][y - 2].getOcupada()){
            return "Nao tem como comer";//nao eh possivel realizar a captura.
         }
       }
      }
      

      d.transformaDama(t);//verifica caso haja dama apos a captura.
      tabuleiro.attTab(t);//atualiza o tabuleiro apos a captura.
      return tabuleiro.corpoTabuleiro();

   }


   //metodo para verificar quando o jogo acabar.
   public boolean acabouJogo(){
      Casa[][] t = tabuleiro.getTab();
      int contP=0, contB=0;//um contador para as pecas pretas e outro para as brancas.

      for (int i = 0; i < t.length; i++) {
          for (int j = 0; j < t.length; j++) {
             if(t[i][j].getPeca().getCorPeca().equalsIgnoreCase("preta")){
                  contP++;//conta as pecas pretas
             }
             if(t[i][j].getPeca().getCorPeca().equalsIgnoreCase("branca")){
                  contB++;//conta as pecas brancas
             }

          }
      }          

      if(contP==0 || contB==0){
               return true;//se nao houver mais pecas brancas ou pretas entao eh porque o jogo acabou.
      }else{
         return false;//caso ainda haja pecas pretas e brancas ainda nao acabou o jogo.
      }

   }



}
