package gui.panels.edit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import agenda.Agenda;

/**
 * Created by gjoosen on 13/02/15.
 */
public class EditPane extends JPanel {

    private Agenda agenda;
    
    public EditPane(Agenda agenda) {
        this.agenda = agenda;
        super.setBackground(Color.BLACK);
        super.setLayout(new GridLayout(1, 4));

        super.add(this.detailsPanel());
        super.add(new ArtistPane(this.agenda));
        super.add(new StagesPane(this.agenda));
        super.add(new ActsPane(this.agenda));
    }

    private JPanel detailsPanel() {
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BorderLayout());
        detailsPanel.add(new JPanel(), BorderLayout.EAST);
        detailsPanel.add(new JPanel(), BorderLayout.WEST);
        detailsPanel.add(new JPanel(), BorderLayout.SOUTH);
        detailsPanel.add(new Label("Details"), BorderLayout.NORTH);

        detailsPanel.add(new JTextArea("Test"), BorderLayout.CENTER);
        return detailsPanel;
    }
}


