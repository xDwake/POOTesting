/**
 * Uma SmartBulb é uma lâmpada inteligente que além de ligar e desligar (já que
 * é subclasse de SmartDevice) também permite escolher a intensidade da iluminação 
 * (a cor da mesma).
 *
 * @author (your name)
 * @version (a version number or a date)
 */
 
public class SmartBulb extends SmartDevice implements Devices {
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 3;

    private double size;
    private double consumoEnergetico;
    private int tone;


    /**
     * Constructor for objects of class SmartBulb
     */
    public SmartBulb() {
        super();
        this.tone = NEUTRAL;
        this.consumoEnergetico = 7.0;
        this.size = 11.0;
    }

    public SmartBulb(String id, boolean b, int tone,double consumoEnergetico,double size) {
        super(id,b);
        this.tone = tone;
        this.consumoEnergetico = consumoEnergetico;
        this.size = size;
    }

    public SmartBulb(int tone,double consumoEnergetico,double size) {
        super();
        this.tone = tone;
        this.consumoEnergetico = consumoEnergetico;
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getConsumoEnergetico() {
        return consumoEnergetico;
    }

    public void setConsumoEnergetico(double consumoEnergetico) {
        this.consumoEnergetico = consumoEnergetico;
    }

    public SmartBulb(String id, boolean b) {
        super(id, b);
        this.tone = NEUTRAL;
        this.consumoEnergetico = 7.0;
        this.size = 11.0;
    }

    public void setTone(int t) {
        if (t>WARM)
            this.tone = WARM;
        else if (t<COLD) 
            this.tone = COLD;
        else 
            this.tone = t;
    }

    public int getTone() {
        return this.tone;
    }


    public String toString() {
        return "SmartBulb{" +
                "size=" + size +
                ", consumoEnergetico=" + consumoEnergetico +
                ", tone=" + tone +
                '}';
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        SmartBulb that = (SmartBulb) o;
        return (this.tone == that.tone);

    }

    public double CalculaConsumoEnergetico(){ //TODO SO PARA CLARIFICAR!!! neste caso os valores atribuidos a WARM, COLD e NEUTRAL sao multiplicados por um valor arbitrario pode ser mudado
        double res = 0.0;
        switch (this.tone){
            case WARM -> {
                res = this.size * this.consumoEnergetico + (WARM * 7.5);
            }
            case COLD -> {
                res= this.size * this.consumoEnergetico + (COLD * 7.5);
            }
            default -> {
                res= this.size * this.consumoEnergetico + (NEUTRAL * 7.5);
            }
        }
        return res;
    }
}
