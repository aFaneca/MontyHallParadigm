package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Experiencia {
    private List<Simulacao> simulacoes;
    private int nrRepeticoes;
    private int nrSucessos;
    private int nrInsucessos;
    private double percentagemSucessos;

    public Experiencia(int nrRepeticoes, int nrCarros, int nrPortas, boolean mudarEscolha){
        this.simulacoes = new ArrayList<>();
        this.nrRepeticoes = nrRepeticoes;
        fazerExperiencia(nrCarros, nrPortas, mudarEscolha);
        coletaDados();
    }

    private void coletaDados() {

        for(Simulacao s : simulacoes){
            if(s.isEncontrouCarro())
                ++nrSucessos;
            else
                ++nrInsucessos;
        }

        if(nrInsucessos == 0)
            percentagemSucessos = 100; // %
        else
            percentagemSucessos = ((float)nrSucessos / simulacoes.size()) * 100.00;
    }

    private void fazerExperiencia(int nrCarros,int nrPortas, boolean mudarEscolha){

        for(int i = 0; i < nrRepeticoes; i++){
            simulacoes.add(new Simulacao(mudarEscolha, nrPortas, nrCarros));
        }
    }

    public int getNrRepeticoes() {
        return nrRepeticoes;
    }

    public int getNrSucessos() {
        return nrSucessos;
    }

    public int getNrInsucessos() {
        return nrInsucessos;
    }

    public double getPercentagemSucessos() {
        return percentagemSucessos;
    }
}
