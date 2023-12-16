public class Dama extends Peca{

    public Dama(String cor) {
        super(cor);
    }

    public void tranformaDama(Casa[][] t){
        for(int j=0; j<t.length; j++){
          if(t[0][j].getPeca().getCorPeca().equalsIgnoreCase("branca")){
              Dama d = new Dama(t[0][j].getPeca().getCorPeca());
              t[0][j] = new Casa(d.getCorPeca(), true, d, 0, j);
              
          }
          if(t[7][j].getPeca().getCorPeca().equalsIgnoreCase("preta")){
              Dama d = new Dama(t[7][j].getPeca().getCorPeca());
              t[7][j] = new Casa(d.getCorPeca(), true, d, 0, j);
              
          }
        }
    }
    

}
