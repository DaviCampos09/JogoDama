public class Dama extends Peca{

    public Dama(String cor) {
        super(cor);
    }

    //metodo para verificar se alguma peca deve virar dama ou nao.
    public void transformaDama(Casa[][] t){
        for(int j=0; j<t.length; j++){
          if(t[0][j].getPeca().getCorPeca().equalsIgnoreCase("branca")){
              Dama d = new Dama(t[0][j].getPeca().getCorPeca());
              t[0][j] = new Casa(d.getCorPeca(), true, d, 0, j);//se tiver uma peca branca na primeira linha do
                                                                          //tabuleiro entao ela eh tranformada em dama.
              
          }
          if(t[7][j].getPeca().getCorPeca().equalsIgnoreCase("preta")){
              Dama d = new Dama(t[7][j].getPeca().getCorPeca());
              t[7][j] = new Casa(d.getCorPeca(), true, d, 7, j);//se tiver uma peca preta na ultima linha do
                                                                          //tabuleiro entao ela eh transformada em dama.
              
          }
        }
    }
    

}
