package tarefas;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

public class Notificacao {
    private List<Tarefas> tarefas; 

    public Notificacao(List<Tarefas> tarefas) {
        this.tarefas = tarefas;
    }

    public void iniciarVerificacao() {
        Timer timer = new Timer(1000 * 60, e -> verificarTarefas());
        timer.start();
    }

    private void verificarTarefas() {
        LocalDateTime agora = LocalDateTime.now();
        for (Tarefas tarefa : tarefas) {
            if (!tarefa.getConcluida() && !tarefa.isNotificada() && tarefa.getDataLimite().isBefore(agora)) {
                emitirNotificacao(tarefa);
                tarefa.setNotificada(true);
            }
        }
    }

    private void emitirNotificacao(Tarefas tarefa) {
        JOptionPane.showMessageDialog(
            null,
            "A tarefa '" + tarefa.getNome() + "' está atrasada!\nPrazo: " + tarefa.getDataLimite(),
            "Notificação",
            JOptionPane.WARNING_MESSAGE
        );
    }
}
