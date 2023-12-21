public class Peca {
  private String corPeca;

  public Peca(String cor){
    this.corPeca = cor;
  }

  public String getCorPeca(){
    return corPeca;
  }

  //metodo para atualizar a cor da peca quando necessario.
  public void attCorPeca(String c){
    corPeca = c;
  }

  //metodo resposanvel por identificar se a peca eh preta, branca ou nula e, alem disso, se eh dama ou nao
  //e, com isso, retornar uma string que corresponde a uma identificacao para cada uma, a qual sera usada
  //no metodo mostraCasa()
  public String identificaPeca(boolean isDama){
    if(isDama==false){
     if(corPeca.equalsIgnoreCase("preta")){
      return "P"; // se nao eh dama e eh preta entao retorna P.
     }else if(corPeca.equalsIgnoreCase("branca")){
      return "B"; // se nao eh dama e eh branca entao retorna B.
     }else{
      return " "; // se nao eh dama e nem branca ou preta entao retorna um caractere vazio.
     }
    }else{
     if(corPeca.equalsIgnoreCase("preta")){
      return "DP"; // se eh dama e eh preta entao retorna DP.
     }else if(corPeca.equalsIgnoreCase("branca")){
      return "DB"; // se eh dama e eh branca entao retorna DB.
     }else{
      return " "; // se eh dama, mas nao eh nem branca nem preta entao retorna um caractere vazio.
     }
    }
    
  }

}
