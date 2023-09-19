package ga.viz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.filechooser.FileFilter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ga.sim.GeneticAlgorithm;

public class DefaultAlgController extends AlgorithmController {

    public DefaultAlgController(GeneticAlgorithm ga) {
        super(ga);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel generationsLabel = new JLabel("Generations");
        JTextField generationsInput = new JTextField("1000", 5);
        JButton runFor = new JButton("Run For ");
        runFor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int generations = Integer.parseInt(generationsInput.getText());
                    run(generations);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Exception",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton run = new JButton("Run");
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run();
            }
        });

        JButton stop = new JButton("Stop");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        JButton log = new JButton("Log to CSV");
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a file chooser dialog
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setSelectedFile(new File("log.csv")); // Set the default filename

                // Create a file filter to restrict selection to CSV files
                FileFilter csvFilter = new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.isDirectory() || file.getName().toLowerCase().endsWith(".csv");
                    }

                    @Override
                    public String getDescription() {
                        return "CSV Files (*.csv)";
                    }
                };

                fileChooser.setFileFilter((javax.swing.filechooser.FileFilter) csvFilter);

                int returnValue = fileChooser.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    System.out.println("file approved");
                    File selectedFile = fileChooser.getSelectedFile();
                    String filename = selectedFile.getAbsolutePath(); // Update the filename

                    // Ensure the selected file has a .csv extension
                    if (!filename.toLowerCase().endsWith(".csv")) {
                        filename += ".csv";
                    }

                    try {
                        logCSV(filename);
                        JOptionPane.showMessageDialog(null, "Successfully logged data to " + selectedFile.getName(),
                                "Success!",
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Exception",
                                JOptionPane.ERROR_MESSAGE);
                    }

                }

            }
        });

        controlPanel.add(run);
        controlPanel.add(stop);

        controlPanel.add(Box.createHorizontalStrut(50));

        controlPanel.add(runFor);
        controlPanel.add(generationsInput);
        controlPanel.add(generationsLabel);

        controlPanel.add(Box.createHorizontalStrut(50));

        controlPanel.add(reset);

        controlPanel.add(Box.createHorizontalStrut(50));

        controlPanel.add(log);

        this.add(controlPanel);
    }

}
