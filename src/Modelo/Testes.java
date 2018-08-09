package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Testes {
    private final int nrCarros;
    private final int nrPortas;
    private List<Teste> testes;

    public Testes(int nrCarros, int nrPortas) {
        this.nrCarros = nrCarros;
        this.nrPortas = nrPortas;
        this.testes = new ArrayList<>();
        addTeste(); // Teste inicial

    }


    public void addTeste(){
        testes.add(new Teste(nrCarros, nrPortas));
    }

    public Teste getTeste(int indice){
        return testes.get(indice);
    }

    public Teste getTesteAtual(){
        return testes.get(testes.size() - 1);
    }

    public int getNrTestes(){
        return testes.size();
    }

    public List<Teste> getTestes() {
        return testes;
    }


}
