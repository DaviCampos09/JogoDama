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
      return "P";
     }else if(corPeca.equalsIgnoreCase("branca")){
      return "B";
     }else{
      return " ";
     }
    }else{
     if(corPeca.equalsIgnoreCase("preta")){
      return "DP";
     }else if(corPeca.equalsIgnoreCase("branca")){
      return "DB";
     }else{
      return " ";
     }
    }
    
  }

}
