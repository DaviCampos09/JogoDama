public class Casa {
   private String corCasa;
   private boolean ocupada;
   private Peca peca;
   private int x,y;

   public Casa(String cor, boolean ocupada, Peca p,int x, int y){
    this.corCasa = cor;
    this.ocupada = ocupada;
    this.peca = p;
    this.x = x;
    this.y = y;
   }

   public Casa(String cor, boolean ocupada,int x, int y){
    this.corCasa = cor;
    this.ocupada = ocupada;
    this.x = x;
    this.y = y;
   }


   public String getCorCasa(){
    return corCasa;
   }

   public boolean getOcupada(){
    return ocupada;
   }

   public int getX(){
    return x;
   }

   public int getY(){
    return y;
   }

   public Peca getPeca(){
      return peca;
   }
  
   //metodo usado para atualizar a peca que esta na casa
   public void attPecaCasa(Peca p){
      peca = p;
   }

   //metodo para atualizar se a casa esta ocupada ou nao
   public void attSituacaoPeca(boolean b){
      ocupada = b;
   }   
   
   //metodo para poder printar a Casa em sua devida formatacao x,y
   public String formaCasa(){
      return "("+getX()+","+getY()+")";
   }   

   //metodo para mostrar o que esta dentro da casa no tabuleiro.
   public String mostraCasa(Peca p){
      if(p instanceof Dama){
        return p.identificaPeca(true);
      }else{
        return p.identificaPeca(false);
      }
      
   }

}
