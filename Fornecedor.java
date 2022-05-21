import java.util.Objects;

public class Fornecedor {

        private String string;
        private int valorbase;

        public Fornecedor(){
            this.string = "EDP Comercial";
            this.valorbase = 4;

        }

        public Fornecedor(String string){
            this.string = string;
            this.valorbase = 4;
        }

        public Fornecedor(String string, int vb){
            this.string = string;
            this.valorbase = vb;
        }

        public Fornecedor (Fornecedor forn){
            this.string = forn.getString();
            this.valorbase = forn.getValorBase();
        }

        public int getValorBase() {
           return this.valorbase;
        }

        public void setValorBase(int vb){
            this.valorbase = vb;

        }

        public String getString() {
            return this.string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Fornecedor)) return false;
            Fornecedor that = (Fornecedor) o;
            return this.string.equalsIgnoreCase(that.getString());
        }

        public String toString() {
            return "Fornecedor{" +
                    "fornecedor=" + string +
                    "valor base=" + valorbase +
                    '}';
        }

        
        public Fornecedor clone(){
            return new Fornecedor(this);
        }

}
