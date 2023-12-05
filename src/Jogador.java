public class Jogador {
   private String nome;
   private Peca[] p;

   public Jogador(String n){
    this.nome=n;
    this.p = new Peca[12]; 
   }

   public String getNome(){
    return nome;
   }

   public Peca[] getP(){
    return p;
   }

}
