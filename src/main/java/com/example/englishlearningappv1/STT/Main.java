package com.example.englishlearningappv1.STT;

import com.example.englishlearningappv1.STT.microphone.Microphone;
import com.example.englishlearningappv1.STT.recognizer.GSpeechDuplex;
import com.example.englishlearningappv1.STT.recognizer.GSpeechResponseListener;
import com.example.englishlearningappv1.STT.recognizer.GoogleResponse;
import net.sourceforge.javaflacencoder.FLACFileWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final Microphone mic = new Microphone(FLACFileWriter.FLAC);
        final GSpeechDuplex duplex = new GSpeechDuplex("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");
        duplex.setLanguage("en");

        JFrame frame = new JFrame("Jarvis Speech API DEMO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextArea response = new JTextArea();
        response.setEditable(false);
        response.setWrapStyleWord(true);
        response.setLineWrap(true);

        final JButton record = new JButton("Record");
        final JButton stop = new JButton("Stop");
        stop.setEnabled(false);

        record.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                record.setEnabled(false);
                stop.setEnabled(true);

                // Start the recognition process in a separate thread
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    @Override
                    protected Void doInBackground() {
                        try {
                            duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        record.setEnabled(true);
                        stop.setEnabled(false);
                    }
                };

                worker.execute();
            }
        });

        stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                mic.close();
            }
        });
        JLabel infoText = new JLabel("<html><div style=\"text-align: center;\">Just hit record and watch your voice be translated into text.\n<br>Only English is supported by this demo, but the full API supports dozens of languages.<center></html>",

                0);
        frame.getContentPane().add(infoText);
        infoText.setAlignmentX(0.5F);
        JScrollPane scroll = new JScrollPane(response);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), 1));
        frame.getContentPane().add(scroll);
        JPanel recordBar = new JPanel();
        frame.getContentPane().add(recordBar);
        recordBar.setLayout(new BoxLayout(recordBar, 0));
        recordBar.add(record);
        recordBar.add(stop);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        duplex.addResponseListener(new GSpeechResponseListener() {
            String last;
            String old_text = "";
            public void onResponse(GoogleResponse gr) {
                String output = "";
                output = gr.getResponse();
                if (gr.getResponse() == null) {
                    this.old_text = response.getText();
                    if (this.old_text.contains("("))
                        this.old_text = this.old_text.substring(0, this.old_text.indexOf('('));
                    System.out.println("Paragraph Line Added");
                    this.old_text = String.valueOf(response.getText()) + "\n";
                    this.old_text = this.old_text.replace(")", "").replace("( ", "");
                    response.setText(this.old_text);
                    return;
                }
                if (output.contains("("))
                    output = output.substring(0, output.indexOf('('));
                if (!gr.getOtherPossibleResponses().isEmpty())
                    output = String.valueOf(output) + " (" + (String)gr.getOtherPossibleResponses().get(0) + ")";
                System.out.println(output);
                response.setText("");
                response.append(this.old_text);
                response.append(output);
            }
        });
    }
}