package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Teste {
    private final int nrCarros;
    private final int nrPortas;
    private List<Porta> portas;
    private Porta escolhaInicial;
    private Porta portaAbertaPeloApresentador;
    private Porta escolhaFinal;
    private boolean terminado;

    public Teste(int nrCarros, int nrPortas) {
        this.nrCarros = nrCarros;
        this.nrPortas = nrPortas;
        this.portas = new ArrayList<>(nrPortas);
        this.terminado = false;
        initTeste();
    }

    private void inicializaPortas(){
        for(int i = 0; i < nrCarros; i++){
            portas.add(new Porta(true)); // Adiciona a(s) porta(s) com carro
        }

        for(int i = nrCarros; i < nrPortas; i++){
            portas.add(new Porta(false)); // Adiciona as restantes portas
        }
        baralharPortas();
    }

    private void baralharPortas(){
        Collections.shuffle(portas);
    }

    private int gerarNrAleatorio(int min, int max){
        Random r = new Random();

        return (r.nextInt(max-min) + min);
    }


    public void acao_escolhaDoApresentador(){
        apresentadorAbrePorta().setSelecionada(true);

    }

    private Porta apresentadorAbrePorta() {
        int portaAAbrir;

        // Retorna a primeira porta aleatória que encontrar sem carro
        do{
            portaAAbrir = gerarNrAleatorio(0, portas.size() /*- 1*/);

            if(!portas.get(portaAAbrir).isTemCarro() && portas.get(portaAAbrir) != escolhaInicial){
                portaAbertaPeloApresentador = portas.get(portaAAbrir);
                portaAbertaPeloApresentador.setSelecionada(true);
                return portas.get(portaAAbrir);
            }

        }while(1 == 1);
    }

    private void initTeste() {
        inicializaPortas();

    }


    public List<Porta> getPortas() {
        return portas;
    }

    public Porta getEscolhaInicial() {
        return escolhaInicial;
    }

    public void setEscolhaInicial(Porta escolhaInicial) {
        this.escolhaInicial = escolhaInicial;
        escolhaInicial.setSelecionada(true);
    }

    public Porta getPortaAbertaPeloApresentador() {
        return portaAbertaPeloApresentador;
    }

    public void setPortaAbertaPeloApresentador(Porta portaAbertaPeloApresentador) {
        this.portaAbertaPeloApresentador = portaAbertaPeloApresentador;
    }

    public Porta getEscolhaFinal() {
        return escolhaFinal;
    }

    public void setEscolhaFinal(Porta escolhaFinal) {
        this.escolhaFinal = escolhaFinal;
    }

    public void setEscolhaFinal2(boolean manterDecisao){
        if(manterDecisao)
            escolhaFinal = escolhaInicial;
        else
            mudarPortaEscolhida();
    }

    private void mudarPortaEscolhida(){
        boolean valido = false;
        Porta p;

        while(!valido){
            p = portas.get(gerarNrAleatorio(0, portas.size() /*- 1*/));
            if(p != escolhaInicial && p != portaAbertaPeloApresentador){
                escolhaFinal = p;
                valido = true;
            }
        }
    }

    public boolean isTerminado() {
        return terminado;
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }
}
