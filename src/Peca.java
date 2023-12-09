public class Peca {
  private String corPeca;
  //private int numPecas;

  public Peca(String cor){
    this.corPeca = cor;
    //this.numPecas = 12;
  }

  public String getCorPeca(){
    return corPeca;
  }


  public void attCorPeca(String c){
    corPeca = c;
  }

  /*public int getNumPecas(){
    return numPecas;
  }*/

  public String identificaPeca(){
    if(corPeca.equalsIgnoreCase("preta")){
      return "P";
    }else if(corPeca.equalsIgnoreCase("branca")){
      return "B";
    }else{
      return " ";
    }
  }

}
