public class Peca {
  private String corPeca;

  public Peca(String cor){
    this.corPeca = cor;
  }

  public String getCorPeca(){
    return corPeca;
  }


  public void attCorPeca(String c){
    corPeca = c;
  }

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
