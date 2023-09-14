package evolution.visualization;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VisualizationSelector extends JPanel {
    public VisualizationSelector(Visualization start, Visualization... visualizations) {
        this.setLayout(new BorderLayout());
        JPanel visualizationPanel = new JPanel();
        JMenu visualizationMenu = new JMenu("Visualizations");

        JMenuItem noneOption = new JMenuItem("None");
        noneOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                visualizationPanel.removeAll();
                visualizationPanel.revalidate();
                visualizationPanel.repaint();

            }
        });
        visualizationMenu.add(noneOption);

        for (Visualization visualization : visualizations) {
            JMenuItem item = new JMenuItem(visualization.name());
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(visualization.name());
                    visualizationPanel.removeAll();
                    visualizationPanel.add(visualization.visualization());
                    visualizationPanel.revalidate();
                    visualizationPanel.repaint();
                }
            });
            visualizationMenu.add(item);
        }

        // Create a JMenuBar and add the visualization menu to it
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        menuBar.add(visualizationMenu);

        visualizationPanel.add(start.visualization());
        visualizationPanel.repaint();
        visualizationPanel.revalidate();

        // Add the JMenuBar and initial visualization panel to the main panel
        this.add(menuBar, BorderLayout.NORTH);
        this.add(visualizationPanel, BorderLayout.CENTER);
    }
}
