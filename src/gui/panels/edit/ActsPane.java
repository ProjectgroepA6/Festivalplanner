package gui.panels.edit;

import agenda.Act;
import agenda.Agenda;
import agenda.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by gjoosen on 19/02/15.
 */
public class ActsPane extends JPanel {

    private JList actsList;
    private Agenda agenda;

    public ActsPane(Agenda agenda){
        this.agenda = agenda;
        super.setLayout(new BorderLayout());
        super.add(new Label("Acts"), BorderLayout.NORTH);
        super.add(new JPanel(), BorderLayout.EAST);
        super.add(new JPanel(), BorderLayout.WEST);
        super.add(this.buttonRow(), BorderLayout.SOUTH);

        //the JList needs an array, so you have to go from the List to an array.
        Act[] acts = new Act[this.agenda.getActs().size()];
        int i = 0;
        for(Act act: this.agenda.getActs()){
            acts[i] = act;
            i++;
        }

        //initialize the JList with the artist objects.
        this.actsList = new JList(acts);

        //the cell renderer.
        this.actsList.setCellRenderer(new ActCellRenderer());

        //the JList inside a scrollPane.
        JScrollPane scrollPane = new JScrollPane(this.actsList);

        super.add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel buttonRow(){
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.add(new Button("add"));
        buttonPane.add(new Button("remove"));
        return buttonPane;
    }
    
}

class ActCellRenderer extends JLabel implements ListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    public ActCellRenderer() {
        setOpaque(true);
        setIconTextGap(12);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        Act act = (Act) value;
        setText(act.getName());
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
