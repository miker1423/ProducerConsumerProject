package producerconsumer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingProducerConsumer {


    public JPanel panelGeneral;
    private JTextField producerSize;
    private JTextField consumerSize;
    private JTextField producerTime;
    private JTextField consumerTime;
    private JTextField bufferSize;
    private JPanel panelInputBuffer;
    private JPanel panelInputProdCons;
    private JTextField minRandom;
    private JTextField maxRandom;
    private JButton startButton;
    private JButton stopButton;
    private JPanel panelInputRandom;
    private JPanel panelStartStop;
    private JTextArea textBufferStatus;
    private JTable tableAnalytics;
    private JPanel panelAnalytics;
    private JPanel panelOutputs;
    private JPanel panelInputs;
    private JPanel panelMainInputs;
    private JList listToDo;
    private JList listDone;

    public SwingProducerConsumer() {
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    private void createUIComponents() {

    }
}
