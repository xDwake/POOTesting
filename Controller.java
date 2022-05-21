import java.util.*;

public class Controller {

    public void run(){
           Scanner sc = new Scanner(System.in);

           System.out.println("\n\nBem-vindo!\n\n");
           System.out.println("Escolha uma das seguintes opcoes:\n");


           System.out.println("0: Carregar dados.\n");

           int op = sc.nextInt();

           if(op==0){
             Parser p = new Parser();
             Loteamento lot = p.parse();
             System.out.println("Dados carregados com sucesso!\n");

             int dias;
             int opcao;
             int flag;
             while(true){
                 System.out.println("Pretende avancar no tempo?\n");
                 System.out.println("0: Sim\n");
                 System.out.println("1: Nao\n");
                 opcao = sc.nextInt();
                 if(opcao ==1){
                     break;
                 }
                 System.out.println("Quanto tempo, em dias, pretende avançar no tempo?\n");
                 dias = sc.nextInt();
                 System.out.println("Indique a operação que pretende realizar:\n");
                 System.out.println("0:Calcular faturas de todas as casas.\n");
                 System.out.println("1:Alterar valores base fornecedores.\n");
                 System.out.println("2:Ligar/desligar dispositivos.\n");
                 System.out.println("3:Verificar casa com maior gasto monetario.\n");
                 System.out.println("4:Verificar fornecedor com maior volume de faturacao.\n");
                 System.out.println("5:Listar faturas emitidas pelos fornecedores.\n");
                 opcao = sc.nextInt();
                 if (opcao == 0) {
                    printFaturasTodas(dias,lot);

                 }

                 if (opcao == 1){
                    alteraValForn(lot);

                 }

                 if(opcao == 2){
                    changeStateDevices(lot);
                 }

                if(opcao == 3){
                    maxGastoCasa(calculaFaturasTodas(dias,lot));
                }

                if(opcao == 4){
                    flag = 0;
                    maxFaturacaoForn(faturacaoFornecedores(lot,dias,flag));
                }

                if(opcao == 5){
                    flag = 1;
                    faturacaoFornecedores(lot,dias,flag);
                }
             }

             System.out.println("Fim\n");

           }


    }

    //DEBUG
    public void printFaturasTodas(int dias, Loteamento lot){
        Map<CasaInteligente, Double> tot = calculaFaturasTodas(dias, lot);
        for (Map.Entry<CasaInteligente, Double> pair : tot.entrySet()) {
            System.out.println("Casa:" + pair.getKey() + "; Fatura:" + pair.getValue() + ";\n");
        }
    }

    public void alteraValForn(Loteamento lot){
        Scanner sc = new Scanner(System.in);
        String texto;
        int valBase;
        System.out.println("Insira o fornecedor que quer alterar:\n"); //TODO começar o relatorio.
        texto = sc.nextLine();
        System.out.println("Insira o novo valor base:\n");
        valBase = sc.nextInt();

        for(ArrayList<CasaInteligente> cil : lot.getLoteamento().values()){
            for(CasaInteligente ci : cil){
                if(texto.equals(ci.getForn().getString())){
                    ci.getForn().setValorBase(valBase);
                }
            }
        }
    }

    public void changeStateDevices(Loteamento lot){
        System.out.println("Insira o id do dispositivo ou 'all' se pretender alterar o estado de todos os dispositivos.\n");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();

        System.out.println("Insira:\n 'on' para ligar.\n 'off' para desligar.\n");
        String stateLine = sc.nextLine();
        boolean state = false;

        if (stateLine.equals("on")) {
            state = true;
        }

        if(id.equals("all")){
            System.out.println("Insira o nif do proprietario:\n");
            String nif = sc.nextLine();

            for(ArrayList<CasaInteligente> cil: lot.getLoteamento().values()){
                for(CasaInteligente ci : cil){
                    if (nif.equals(ci.getNif())){
                        ci.setAllOn(state);
                    }
                }
            }

        }
        else {

            for (ArrayList<CasaInteligente> cil : lot.getLoteamento().values()) {
                for (CasaInteligente ci : cil) {
                    for (SmartDevice sd : ci.getDevices().values()) {
                        if (id.equals(sd.getID())) {
                            sd.setOn(state);
                        }
                    }
                }
            }
        }
    }

    public void maxGastoCasa(Map<CasaInteligente,Double> faturas){
        Double max = Double.MIN_VALUE;
        CasaInteligente resultado = new CasaInteligente();
        for(Map.Entry<CasaInteligente,Double> pair : faturas.entrySet()){
            if(pair.getValue() > max){
                max = pair.getValue();
                resultado = pair.getKey().clone();
                resultado.setId(pair.getKey().getId());
            }
        }
        System.out.println("Casa:" + resultado.getId() + "; Fatura:" + max + ";");
    }


    public Map<String,Map<Integer,Double>> faturacaoFornecedores(Loteamento lot,int dias,int flag) {
        Map<String, Map<Integer, Double>> faturas = new HashMap<>();
        Double val = 0.0;
        for (ArrayList<CasaInteligente> cil : lot.getLoteamento().values()) {
            for (CasaInteligente ci : cil) {
                val = calculaFaturaCasa(dias, ci);
                if (faturas.containsKey(ci.getForn().getString())) {
                    faturas.get(ci.getForn().getString()).put(ci.getId(), val);
                }

                else {
                    Map<Integer, Double> novo = new HashMap<>();
                    novo.put(ci.getId(), val);
                    faturas.put(ci.getForn().getString(), novo);
                }
            }
        }
        if(flag == 1) {
            for (Map.Entry<String, Map<Integer, Double>> pair : faturas.entrySet()) {

                System.out.println("Fornecedor:" + pair.getKey() + "\n");

                for (Map.Entry<Integer, Double> par : pair.getValue().entrySet()) {
                    System.out.println("\tCasa: " + par.getKey() + "-> Fatura: " + par.getValue() + "\n");
                }
            }
        }
        return faturas;
    }

    public void maxFaturacaoForn(Map<String,Map<Integer,Double>> faturas){
        double max = Double.MIN_VALUE;
        double sum = 0.0;
        String forn = "EDP Comercial";
        for(Map.Entry<String,Map<Integer,Double>> pair : faturas.entrySet()){
            sum = 0.0;
            for(Double d : pair.getValue().values()){
                sum += d;
            }
            if(sum > max){
                forn = pair.getKey();
                max = sum;
            }
        }
        System.out.println("Fornecedor:" + forn + "-> Faturacao: " + max);
    }


    public double calculaEnergiaCasa(int dias, CasaInteligente casa){
               double res = 0;

               for(SmartDevice d: casa.getDevices().values()){
                   if( d instanceof SmartBulb){
                      res += ((SmartBulb) d).getConsumoEnergetico() * 24 * dias;
                   }
                   if( d instanceof SmartSpeaker){
                       res += ((SmartSpeaker) d).CalculaConsumoEnergetico()* 24 * dias;
                   }
                   if( d instanceof  SmartCamera){
                       res += ((SmartCamera) d).CalculaConsumoEnergetico() * 24 * dias;
                   }
               }

               return res;
    }

    public double calculaFaturaCasa(int dias, CasaInteligente casa){

        double res = casa.CustoDiarioDispositivos() * dias;

        return res;
    }

    public Map<CasaInteligente, Double> calculaFaturasTodas(int dias, Loteamento lot){

        Map<CasaInteligente, Double> faturas = new HashMap<>();


        for(ArrayList<CasaInteligente> arr : lot.getLoteamento().values()){

            for(CasaInteligente c: arr){
                double temp = calculaFaturaCasa(dias, c);
                faturas.put(c.clone(), temp);
            }
        }

        return faturas;
    }
}
