package gui.panels.edit.dialogs;

import agenda.Agenda;
import agenda.Artist;
import agenda.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gjoosen on 19/02/15.
 */
public class StageDialogPanel extends JDialog{

    private Agenda agenda;
    private DefaultListModel model;
    private JTextField name;
    private Stage stage;
    
    public StageDialogPanel(Agenda agenda, DefaultListModel model){
        this.agenda = agenda;
        this.model = model;
        
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(new Label("new stage"));
        main.add(this.namePanel());
        main.add(this.buttons());
        super.add(main);
    }

    //edit dialog.
    public StageDialogPanel(Agenda agenda, DefaultListModel model, Stage stage){
        this.agenda = agenda;
        this.model = model;
        this.stage = stage;

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(new Label("edit stage"));
        main.add(this.namePanel());
        main.add(this.buttons());
        
        this.name.setText(stage.getName());
        
        super.add(main);
    }
    
    
    
    private JPanel namePanel(){
        JPanel name = new JPanel();
        name.setLayout(new BoxLayout(name, BoxLayout.X_AXIS));
        name.add(new JLabel("Name"));
        this.name = new JTextField();
        name.add(this.name);
        return name;
    }

    private JPanel buttons(){
        JPanel buttons = new JPanel();
        
        JButton save = new JButton("save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        buttons.add(save);
        
        JButton cancel = new JButton("cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        buttons.add(cancel);
        return buttons;
    }
    
    private void save(){
        if(this.stage != null){
            if(this.name.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Name can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }else{
                this.model.removeElement(this.stage);
                this.stage.setName(this.name.getText());
                this.model.addElement(this.stage);
                dispose();
            }
        }else{
            if(this.name.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Name can't be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }else{
                Stage stage = new Stage(this.name.getText());
                this.agenda.addStage(stage);
                this.model.addElement(stage);
                dispose();
            }
        }
    }

    private void cancel(){
        dispose();
    }
    
    protected JButton saveButton() {
        JButton button = new JButton("save");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        return button;
    }
}