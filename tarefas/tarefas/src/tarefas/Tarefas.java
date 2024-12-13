package tarefas;

import javax.swing.*;
import java.time.LocalDateTime;

public class Tarefas {

    private String nome;
    private String descricao;
    private LocalDateTime dataLimite;
    private boolean concluida;
    private JCheckBox checkBox; 
    private boolean notificada; 


    public Tarefas(String nome, String descricao, LocalDateTime dataLimite, JCheckBox checkBox) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataLimite = dataLimite;
        this.concluida = false; 
        this.checkBox = checkBox; 
        this.notificada = false; 
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDateTime dataLimite) {
        this.dataLimite = dataLimite;
    }

    public boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(boolean concluida) {
        this.concluida = concluida;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }


    public boolean isNotificada() {
        return notificada;
    }

    public void setNotificada(boolean notificada) {
        this.notificada = notificada;
    }


    @Override
    public String toString() {
        return nome + " - " + descricao + " - " + dataLimite + " - " +
                (concluida ? "Concluída" : "Pendente");
    }
}
