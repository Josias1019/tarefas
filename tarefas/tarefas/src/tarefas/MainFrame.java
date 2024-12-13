package tarefas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;


    private JTextField nomeField;
    private JTextField descricaoField;
    private JTextField dataLimiteField; 
    private JTextField horaLimiteField;
    private JButton adicionarButton;
    private JPanel tarefasPanel;


    private List<Tarefas> tarefas;

    public MainFrame() {
        setTitle("Gerenciador de Tarefas");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tarefas = new ArrayList<>();

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        inputPanel.add(new JLabel("Nome da Tarefa:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Descrição:"));
        descricaoField = new JTextField();
        inputPanel.add(descricaoField);

        inputPanel.add(new JLabel("Data Limite (dd/MM/yyyy):"));
        dataLimiteField = new JTextField();
        inputPanel.add(dataLimiteField);

        inputPanel.add(new JLabel("Hora Limite (HH:mm):"));
        horaLimiteField = new JTextField();
        inputPanel.add(horaLimiteField);

        adicionarButton = new JButton("Adicionar Tarefa");
        inputPanel.add(new JLabel());
        inputPanel.add(adicionarButton);

        tarefasPanel = new JPanel();
        tarefasPanel.setLayout(new BoxLayout(tarefasPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(tarefasPanel);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText().trim();
                String descricao = descricaoField.getText().trim();
                String dataTexto = dataLimiteField.getText().trim();
                String horaTexto = horaLimiteField.getText().trim();

                if (!nome.isEmpty() && !descricao.isEmpty() && !dataTexto.isEmpty() && !horaTexto.isEmpty()) {
                    try {
                      
                        String dataHoraTexto = dataTexto + " " + horaTexto;

                    
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime dataHoraLimite = LocalDateTime.parse(dataHoraTexto, formatter);

                      
                        addTarefa(nome, descricao, dataHoraLimite);

                   
                        nomeField.setText("");
                        descricaoField.setText("");
                        dataLimiteField.setText("");
                        horaLimiteField.setText("");
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(
                            MainFrame.this,
                            "Formato inválido de data/hora. Use o formato dd/MM/yyyy HH:mm.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                        MainFrame.this,
                        "Por favor, preencha todos os campos!",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

       
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarPrazos();
            }
        });
        timer.start();
    }

    private void addTarefa(String nome, String descricao, LocalDateTime dataLimite) {
        JPanel tarefaPanel = new JPanel();
        tarefaPanel.setLayout(new BoxLayout(tarefaPanel, BoxLayout.Y_AXIS));

        JCheckBox checkBox = new JCheckBox("Concluída");
        checkBox.setSelected(false);

        tarefaPanel.add(new JLabel("Tarefa: " + nome));
        tarefaPanel.add(new JLabel("Descrição: " + descricao));
        tarefaPanel.add(new JLabel("Data Limite: " + dataLimite.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        tarefaPanel.add(new JLabel("Hora Limite: " + dataLimite.format(DateTimeFormatter.ofPattern("HH:mm"))));
        tarefaPanel.add(checkBox);

        tarefas.add(new Tarefas(nome, descricao, dataLimite, checkBox));
        tarefasPanel.add(tarefaPanel);
        tarefasPanel.revalidate();
    }

    private void verificarPrazos() {
        LocalDateTime agora = LocalDateTime.now();
        for (Tarefas tarefa : tarefas) {
            if (!tarefa.getConcluida() && tarefa.getDataLimite().isBefore(agora)) {
                JOptionPane.showMessageDialog(
                    MainFrame.this,
                    "A tarefa \"" + tarefa.getNome() + "\" está atrasada!",
                    "Notificação",
                    JOptionPane.WARNING_MESSAGE
                );
                tarefa.setNotificada(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
