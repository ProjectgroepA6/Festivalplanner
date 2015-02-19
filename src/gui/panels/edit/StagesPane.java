package gui.panels.edit;

import agenda.Agenda;
import agenda.Stage;
import gui.panels.edit.dialogs.AddStageDialogPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by gjoosen on 19/02/15.
 */
public class StagesPane extends JPanel {

    private JList stagesList;
    private Agenda agenda;
    private DefaultListModel model;
    
    public StagesPane(Agenda agenda){
        this.agenda = agenda;
        super.setLayout(new BorderLayout());
        super.add(new Label("Stages"), BorderLayout.NORTH);
        super.add(new JPanel(), BorderLayout.EAST);
        super.add(new JPanel(), BorderLayout.WEST);
        super.add(this.buttonRow(), BorderLayout.SOUTH);

        //the JList needs an array, so you have to go from the List to an array.

        this.model = new DefaultListModel();

        for(Stage stage: this.agenda.getStages()){
            this.model.addElement(stage);
        }
        
        this.stagesList = new JList();
        this.stagesList.setModel(this.model);

        //the cell renderer.
        this.stagesList.setCellRenderer(new StageCellRenderer());

        //the JList inside a scrollPane.
        JScrollPane scrollPane = new JScrollPane(this.stagesList);

        super.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel buttonRow(){
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        JButton addButton = new JButton("add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setContentPane(new AddStageDialogPanel(agenda, model));
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        buttonPane.add(addButton);
        buttonPane.add(new Button("remove"));
        return buttonPane;
    }
    
}

class StageCellRenderer extends JLabel implements ListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    public StageCellRenderer() {
        setOpaque(true);
        setIconTextGap(12);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        Stage stage = (Stage) value;
        setText(stage.getName());
        if (isSelected) {
            setBackground(HIGHLIGHT_COLOR);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
          return this;
    }
}
