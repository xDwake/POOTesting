import java.util.Map;
import java.util.Objects;

/**
 * Um SmartSpeaker é um SmartDevice que além de ligar e desligar permite também
 * reproduzir som.
 * Consegue ligar-se a um canal (por simplificação uma rádio online) e permite
 * a regulação do seu nível de volume.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

public class SmartSpeaker extends SmartDevice implements Devices {
    public static final int MAX = 20; //volume máximo
    

    private int volume;
    private String channel;
    private String marca;
    private double consumoEnergetico;

    /**
     * Constructor for objects of class SmartSpeaker
     */
    public SmartSpeaker() {
        super();
        this.marca = "JBL";
        this.channel = "RFM";
        this.volume = 10;
    }

    public SmartSpeaker(String s, boolean b) {
        super(s,b);
        this.marca = "JBL";
        this.channel = "RFM";
        this.volume = 10;
    }

    public SmartSpeaker(int volume, String channel, String marca, double consumoEnergetico){
        super();
        this.channel = channel;
        this.volume = volume;
        this.marca = marca;
        this.consumoEnergetico = consumoEnergetico;

    }



    public void volumeUp() {
        if (this.volume<MAX) this.volume++;
    }

    public void volumeDown() {
        if (this.volume>0) this.volume--;
    }
    
    public String getMarca(){
        return this.marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public void setConsumoEnergetico(double consumoEnergetico){
        this.consumoEnergetico = consumoEnergetico;
    }

    public double getConsumoEnergetico(){
        return this.consumoEnergetico;
    }

    public int getVolume() {
        return this.volume;
    }
    
    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String c) {
        this.channel = c;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartSpeaker that = (SmartSpeaker) o;
        return (this.volume == that.volume && this.marca.equalsIgnoreCase(that.marca) && this.channel.equalsIgnoreCase(that.channel) && this.consumoEnergetico == that.consumoEnergetico);
    }

    public String toString() {
        return "SmartSpeaker{" +
                "volume=" + volume +
                ", channel='" + channel + '\'' +
                ", marca=" + marca +
                ", consumo=" + consumoEnergetico +
                '}';
    }

    public double CalculaConsumoEnergetico(){

                 double res = this.consumoEnergetico * this.volume;

                 return res;
    }
}
