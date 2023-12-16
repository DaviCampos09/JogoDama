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
  
   public void attPecaCasa(Peca p){
      peca = p;
   }

   public void attSituacaoPeca(boolean b){
      ocupada = b;
   }

   public String mostraCasa(Peca p){
      if(p instanceof Dama){
        return p.identificaPeca(true);
      }else{
        return p.identificaPeca(false);
      }
      
   }

}
