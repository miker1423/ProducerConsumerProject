package producerconsumer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class SwingProducerConsumer {


    public JPanel panelGeneral;
    private JTextField producerSize;
    private JTextField producerTime;
    private JTextField consumerTime;
    private JTextField consumerSize;
    private JTextField bufferSize;
    private JTextField minRandom;
    private JTextField maxRandom;
    private JButton startButton;
    private JButton stopButton;

    private JPanel panelInputBuffer;
    private JPanel panelInputProdCons;
    private JPanel panelInputRandom;
    private JPanel panelStartStop;
    private JPanel panelAnalytics;
    private JPanel panelOutputs;
    private JPanel panelInputs;
    private JPanel panelMainInputs;

    private JTextArea textBufferStatus;

    private JList listDoing;
    private JList listToDo;
    private JList listDone;

    private DefaultListModel<String> listModelToDo;
    private DefaultListModel<String> listModelDone;
    private DefaultListModel<String> listModelDoing;

    private JTextArea totalOpsText;
    private JTextArea totalTimeText;

    private long startTime;
    private long endTime;

    List<JTextField> textFields = new ArrayList<JTextField>() {{
        add(producerSize);
        add(producerTime);
        add(consumerSize);
        add(consumerTime);
        add(bufferSize);
        add(minRandom);
        add(maxRandom);
    }};

    List<OperationProducer> producers = new ArrayList<>();
    List<OperationConsumer> consumers = new ArrayList<>();

    private void runner(){
        int min = Integer.parseInt(minRandom.getText());
        int max = Integer.parseInt(maxRandom.getText());
        int consTime = Integer.parseInt(consumerTime.getText());
        int prodTime = Integer.parseInt(producerTime.getText());
        int consSize = Integer.parseInt(consumerSize.getText());
        int prodSize = Integer.parseInt(producerSize.getText());
        int buffSize = Integer.parseInt(bufferSize.getText());

        QueueBuffer buffer = new QueueBuffer(buffSize, textBufferStatus, listModelDoing);

        for (int i = 0; i < prodSize; i++) {
            OperationBuilder builder = new OperationBuilder(min, max);
            OperationProducer producer = new OperationProducer(buffer, prodTime, builder, listModelToDo, i);
            producers.add(producer);
            producer.start();
        }

        for (int j = 0; j < consSize; j++) {
            OperationConsumer consumer = new OperationConsumer(buffer, consTime, listModelDone, j);
            consumers.add(consumer);
            consumer.start();
        }
    }


    private FocusAdapter listener = new FocusAdapter(){
        @Override
        public void focusLost(FocusEvent e) {
            super.focusLost(e);
            Boolean isInputReady = true;

            for (JTextField textField: textFields) {
                if(textField.getText().isEmpty()){
                    textField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    isInputReady = false;
                }

                try {
                    int result = Integer.parseInt(textField.getText());
                    textField.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                }
                catch (NumberFormatException ex){
                    textField.setBorder(BorderFactory.createLineBorder(Color.RED));
                    isInputReady = false;
                }
            }

            if (isInputReady && isValidInput()){
                startButton.setEnabled(true);
            }
        }
    };

    private boolean isValidInput(){
        boolean isValid = true;

        int min = Integer.parseInt(minRandom.getText());
        int max = Integer.parseInt(maxRandom.getText());
        int consTime = Integer.parseInt(consumerTime.getText());
        int prodTime = Integer.parseInt(producerTime.getText());
        int consSize = Integer.parseInt(consumerSize.getText());
        int prodSize = Integer.parseInt(producerSize.getText());
        int buffSize = Integer.parseInt(bufferSize.getText());


        if (min < 0 || max <= 0 || min >= max){
            minRandom.setBorder(BorderFactory.createLineBorder(Color.RED));
            maxRandom.setBorder(BorderFactory.createLineBorder(Color.RED));
            isValid = false;
        }

        if (consTime <= 0) {
            consumerTime.setBorder(BorderFactory.createLineBorder(Color.RED));
            isValid = false;
        }

        if (prodTime <= 0) {
            producerTime.setBorder(BorderFactory.createLineBorder(Color.RED));
            isValid = false;
        }
        if (consSize <= 0){
            consumerSize.setBorder(BorderFactory.createLineBorder(Color.RED));
            isValid = false;

        }
        if (prodSize <= 0){
            producerSize.setBorder(BorderFactory.createLineBorder(Color.RED));
            isValid = false;
        }

        if (buffSize <= 0){
            bufferSize.setBorder(BorderFactory.createLineBorder(Color.RED));
            isValid = false;
        }

        return isValid;
    }


    private void setEnableTextFields(boolean status){
        for (JTextField textField: textFields) {
            textField.setEnabled(status);
        }
    }

    public SwingProducerConsumer() {

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("START CLICKED!!!");
                stopButton.setEnabled(true);
                startButton.setEnabled(false);
                setEnableTextFields(false);
                listModelDone.clear();
                listModelToDo.clear();
                listModelDoing.clear();
                totalOpsText.setText("");
                totalTimeText.setText("");
                startTime = System.currentTimeMillis();
                runner();
            }
        });
        stopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("STOP CLICKED!!!");
                stopButton.setEnabled(false);
                startButton.setEnabled(true);
                setEnableTextFields(true);

                for (OperationProducer producer: producers) {
                    producer.stopProducing();
                }

                for (OperationConsumer consumer: consumers) {
                    consumer.stopConsuming();
                }
                endTime = System.currentTimeMillis();

                int totalOps = listModelDone.getSize();
                long totalTime = endTime - startTime;

                totalOpsText.setText(totalOps + " ops");
                totalTimeText.setText(totalTime + " ms");
            }
        });

        for (JTextField textField: textFields) {
            textField.addFocusListener(listener);
        }

        listModelToDo = new DefaultListModel<>();
        listToDo.setModel(listModelToDo);

        listModelDone = new DefaultListModel<>();
        listDone.setModel(listModelDone);

        listModelDoing = new DefaultListModel<>();
        listDoing.setModel(listModelDoing);
    }




    private void createUIComponents() {

    }
}
