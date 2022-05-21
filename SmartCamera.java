public class SmartCamera extends SmartDevice implements Devices{

    private String resolucao;  
    /*- VGA: 640 x 480 pixels;
      - QVGA: 320 x 240 pixels;
      - WVGA: 800 x 480 pixels;
      - SVGA / Super VGA: 800 x 600 pixels;
      - XGA: 1024 x 768 pixels;
      - WXGA: de 1152 x 768 pixels a 1366 x 768 pixels;
      - HD / 720p : 1280 x 720 pixels;
      - qHD: 960 x 540 pixels;
      - full HD (FHD ou 1080p): 1920 x 1080 pixels;
      - QHD (WQHD): 2560 x 1440 pixels.
      - 2K: 2048 x 1080 pixels;
      - 4K (UHDTV ou QFHD): 3840 x 2160 pixels;
      - 8K: de 7680 x 4320 pixels a 10080 x 4320 pixels (depende da proporção do aparelho).
    */
    private int tamanho;
    private double consumoEnergetico;


    public SmartCamera(){
        super();
        this.resolucao = "(1280x720)";
        this.tamanho = 10;
        this.consumoEnergetico = 7.0;
    }
    
    
    public SmartCamera(String id, boolean b, double consumo){
        super(id,b);
        this.resolucao = "(1280x720)";
        this.tamanho = 10;
        this.consumoEnergetico = 7.0;
    }

    public SmartCamera(String id, boolean b, double consumo, String res, int tam, double consumoEnergetico){
        super(id,b);
        this.resolucao = res;
        this.tamanho = tam;
        this.consumoEnergetico = consumoEnergetico;
    }

    public SmartCamera(String res, int tam, double consumoEnergetico){
        super();
        this.resolucao = res;
        this.tamanho = tam;
        this.consumoEnergetico = consumoEnergetico;

    }


    public String getResolucao(){
        return this.resolucao;
    }


    public int getTamanho() {
        return tamanho;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public SmartCamera clone(){
        return new SmartCamera(this.resolucao, this.tamanho, this.consumoEnergetico);
    }


    public String toString() {
        return "SmartCamera{" +
                "resolucao='" + resolucao + '\'' +
                ", tamanho=" + tamanho +
                ", consumoEnergetico=" + consumoEnergetico +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartCamera that = (SmartCamera) o;
        return (this.resolucao.equalsIgnoreCase(that.resolucao) && this.tamanho == that.tamanho && this.consumoEnergetico == that.consumoEnergetico);
    }


    public double CalculaConsumoEnergetico(){

        double res = .0;
          switch(resolucao.length()){
              case 9 -> {
                  res = this.consumoEnergetico * (10);
              }
              case 10 -> {
                  res = this.consumoEnergetico * (15);
              }

              case 11 -> {
                  res = this.consumoEnergetico * (20);
              }

              case 12 ->{
                  res = this.consumoEnergetico * (30);
              }

          }


        return res;
    }

    
}
