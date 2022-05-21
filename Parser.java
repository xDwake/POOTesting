import java.nio.file.Files;
import java.util.List;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Parser {
    int rand = 2;

    public Loteamento parse(){
        List<String> linhas = lerFicheiro("log.txt");
        String[] linhaPartida;
        Loteamento lot = new Loteamento();
        String divisao = null;
        CasaInteligente casaMaisRecente = null;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Casa":
                    if(casaMaisRecente != null) lot.addCasaInteligente(casaMaisRecente);
                    casaMaisRecente = parseCasa(linhaPartida[1]);
                    break;
                case "Divisao":
                    if (casaMaisRecente == null) System.out.println("Linha inválida.");
                    divisao = linhaPartida[1];
                    assert casaMaisRecente != null;
                    casaMaisRecente.addRoom(divisao);
                    break;
                case "SmartBulb":
                    if (divisao == null) System.out.println("Linha inválida.");
                    SmartBulb sd = parseSmartBulb(linhaPartida[1]);
                    assert casaMaisRecente != null;
                    casaMaisRecente.addDevice(sd);
                    casaMaisRecente.addToRoom(divisao, sd.getID());
                   // System.out.println(casaMaisRecente.toString());
                    break;
                case "SmartSpeaker":
                    if (divisao == null) System.out.println("Linha inválida.");
                    SmartSpeaker ss = parseSmartSpeaker(linhaPartida[1]);
                    assert casaMaisRecente != null;
                    casaMaisRecente.addDevice(ss);
                    casaMaisRecente.addToRoom(divisao, ss.getID());
                    break;
                case "SmartCamera":
                    if (divisao == null) System.out.println("Linha inválida.");
                    SmartCamera sc = parseSmartCamera(linhaPartida[1]);
                    assert casaMaisRecente != null;
                    casaMaisRecente.addDevice(sc);
                    casaMaisRecente.addToRoom(divisao, sc.getID());
                    break;
                case "Fornecedor":
                    Fornecedor forn = parseFornecedor(linhaPartida[1]);
                    break;
                default:
                    System.out.println("Linha inválida.");
                    break;
            }

        }
        System.out.println("done!");
        return lot;
    }

    public List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    public CasaInteligente parseCasa(String input){
        String[] campos = input.split(",");
        String nome = campos[0];
        int nif = Integer.parseInt(campos[1]);
        Fornecedor forn = new Fornecedor();
        forn.setString(campos[2]);
        return new CasaInteligente(nif, nome, forn);
    }

    public SmartBulb parseSmartBulb(String input){
        String[] campos = input.split(",");
        String tonalidade = campos[0];
        int ton = 1;
        if(tonalidade.equalsIgnoreCase("Neutral")) ton=1;
        if(tonalidade.equalsIgnoreCase("Warm")) ton=2;
        if(tonalidade.equalsIgnoreCase("Cold")) ton=3;
        double size = Double.parseDouble(campos[1]);
        double consumo = Double.parseDouble(campos[2]);
        return new SmartBulb(ton, consumo, size);
    }

    public SmartSpeaker parseSmartSpeaker(String input){
        String[] campos = input.split(",");
        int volume = Integer.parseInt(campos[0]);
        String channel = campos[1];
        String marca = campos[2];
        double consumo = Double.parseDouble(campos[3]);
        return new SmartSpeaker(volume, channel, marca, consumo);

    }


    public SmartCamera parseSmartCamera(String input){
        String[] campos = input.split(",");
        String resolucao = campos[0];
        int tamanho = Integer.parseInt(campos[1]);
        double consumo = Double.parseDouble(campos[2]);
        return new SmartCamera(resolucao, tamanho, consumo);
    }


    public Fornecedor parseFornecedor(String input){
        Fornecedor forn = new Fornecedor();
        forn.setString(input);
        forn.setValorBase(rand);
        rand++;
        return forn;
    }



}

