package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Estatistica {
    private final int nrCarros;
    private final int nrPortas;
    private List<Experiencia> expSemMudar;
    private List<Experiencia> expAMudar;


    public Estatistica(int nrRepeticoes, int nrCarros, int nrPortas){
        this.nrCarros = nrCarros;
        this.nrPortas = nrPortas;
        this.expAMudar = new ArrayList<>();
        this.expSemMudar = new ArrayList<>();
        initSimulacoes(nrRepeticoes);
    }


    public int getNrSucessosMudar(){
        int aux = 0;

        for(Experiencia e : expAMudar){
            aux += e.getNrSucessos();
        }

        return aux;
    }

    public int getNrInsucessosMudar(){
        int aux = 0;

        for(Experiencia e : expAMudar){
            aux += e.getNrInsucessos();
        }

        return aux;
    }

    public int getNrSucessosSemMudar(){
        int aux = 0;

        for(Experiencia e : expSemMudar){
            aux += e.getNrSucessos();
        }

        return aux;
    }

    public int getNrInsucessosSemMudar(){
        int aux = 0;

        for(Experiencia e : expSemMudar){
            aux += e.getNrInsucessos();
        }

        return aux;
    }

    public double getMediaTaxaSucessoMudar(){
        double aux = 0;

        for(Experiencia e : expAMudar){
            aux += e.getPercentagemSucessos();
        }

        return (aux / expAMudar.size());
    }

    public double getMediaTaxaSucessoSemMudar(){
        double aux = 0;

        for(Experiencia e : expSemMudar){
            aux += e.getPercentagemSucessos();
        }

        return aux / expSemMudar.size();
    }


    public List<Double> getListaTaxaSucessoMudar(){
        List<Double> lista = new ArrayList<>();
        for(Experiencia e : expAMudar){
            lista.add(e.getPercentagemSucessos());
        }
        return lista;
    }

    public List<Double> getListaTaxaSucessoSemMudar(){
        List<Double> lista = new ArrayList<>();
        for(Experiencia e : expSemMudar){
            lista.add(e.getPercentagemSucessos());
        }
        return lista;
    }



    private void initSimulacoes(int n){
            expSemMudar.add(new Experiencia(n,nrCarros,nrPortas, false)); // Inicializa as experiências em q existe mudança de porta
            expAMudar.add(new Experiencia(n,nrCarros,nrPortas, true)); // ... não existe mudança de porta
    }

    public List<Experiencia> getExpSemMudar() {
        return expSemMudar;
    }

    public List<Experiencia> getExpAMudar() {
        return expAMudar;
    }
}
