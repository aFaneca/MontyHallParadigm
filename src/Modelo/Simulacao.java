package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Simulacao {
    private int  nrPortas;
    private int nrCarros;
    private boolean mudarEscolha;
    private boolean encontrouCarro; // TRUE - encontrou carro | FALSE - não encontrou carro
    private List<Porta> portas;
    private Porta escolhaInicial;
    private Porta portaAbertaPeloApresentador;
    private Porta escolhaFinal;


    public Simulacao(boolean mudarEscolha, int nrPortas, int nrCarros){
        this.mudarEscolha = mudarEscolha;
        this.nrPortas = nrPortas;
        this.nrCarros = nrCarros;
        this.portas = new ArrayList<>(nrPortas);
        fazerSimulacao();
    }

    private void inicializaPortas(){
        for(int i = 0; i < nrCarros; i++){
            portas.add(new Porta(true)); // Adiciona a(s) porta(s) com carro
        }

        for(int i = nrCarros; i < nrPortas; i++){
            portas.add(new Porta(false)); // Adiciona as restantes portas
        }
    }

    private Porta fazerEscolhaInicial(){
        int nr;
        boolean valido = false;
        nr = gerarNrAleatorio(0, portas.size() /*- 1*/);
        escolhaInicial = portas.get(nr);
        return escolhaInicial;
    }

    private Porta apresentadorAbrePorta() {
        int portaAAbrir;

        // Retorna a primeira porta aleatória que encontrar sem carro
        do{
           portaAAbrir = gerarNrAleatorio(0, portas.size() /*- 1*/);

           if(!portas.get(portaAAbrir).isTemCarro() && portas.get(portaAAbrir) != escolhaInicial){
               portaAbertaPeloApresentador = portas.get(portaAAbrir);
               return portas.get(portaAAbrir);
           }

        }while(1 == 1);
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

    public void fazerSimulacao(){
        inicializaPortas();
        System.out.println(portas);

        System.out.println("O participante escolheu " + fazerEscolhaInicial());
        System.out.println("O apresentador abriu a porta " + apresentadorAbrePorta());
        System.out.print("O participante decidiu ");
        if(mudarEscolha) {
            System.out.println(" mudar a sua escolha inicial.");
            mudarPortaEscolhida();
        } else{
            escolhaFinal = escolhaInicial;
            System.out.println(" manter a sua escolha inicial.");
        }

        System.out.println("PORTA ABERTA: " + escolhaFinal);

        if(escolhaFinal.isTemCarro())
            encontrouCarro = true;

    }

    private void baralharPortas(){
        Collections.shuffle(portas);
    }


    public boolean isEncontrouCarro() {
        return encontrouCarro;
    }

    private int gerarNrAleatorio(int min, int max){
        Random r = new Random();

        return (r.nextInt(max-min) + min);
    }
}
