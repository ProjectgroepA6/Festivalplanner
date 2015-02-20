package gui.panels.edit.dialogs;

import agenda.*;
import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by gjoosen on 20/02/15.
 */
public class AddActDialogPanel extends JDialog{

    private Agenda agenda;
    private JTextField name, genre;
    private JComboBox stageComboBox;

    private DefaultListModel model;
    private final DefaultListModel  artistModel = new DefaultListModel();
    
    public AddActDialogPanel(Agenda agenda, DefaultListModel model){
        this.agenda = agenda;
        this.model = model;

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(new Label("new act"));
        main.add(this.namePanel());
        //add
        main.add(this.stageChooser());
        main.add(this.genreChooser());
        //todo dates
        main.add(this.dates());
        main.add(this.artistsChooser());
        
        main.add(this.buttons());
        super.add(main);
        super.setVisible(true);
        super.pack();
    }
    
    private JPanel namePanel(){
        JPanel name = new JPanel();
        name.setLayout(new BoxLayout(name, BoxLayout.X_AXIS));
        name.add(new JLabel("Name"));

        this.name = new JTextField();

        name.add(this.name);
        return name;
    }

    private JPanel stageChooser(){
        JPanel panel = new JPanel();
        this.stageComboBox = new JComboBox();
        for(Stage stage: this.agenda.getStages()){
            this.stageComboBox.addItem(stage);
        }
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(new JLabel("stage:"));
        panel.add(this.stageComboBox);        
        
        return panel;
    }
    
    private JPanel genreChooser(){
        JPanel genrePanel = new JPanel();
        genrePanel.setLayout(new BoxLayout(genrePanel, BoxLayout.X_AXIS));
        genrePanel.add(new JLabel("Genre"));

        this.genre = new JTextField();

        genrePanel.add(this.genre);
        return genrePanel;
    }
    
    private JPanel dates(){
        JPanel dates = new JPanel();   
        return dates;
    }
    
    private JPanel artistsChooser(){
        final JPanel artists = new JPanel();
        artists.setLayout(new BoxLayout(artists, BoxLayout.X_AXIS));
        
        //list
        JList<Act> list = new JList();
        list.setCellRenderer(new ArtistCellRenderer());
        
        list.setModel(artistModel);
        
        JScrollPane scrollPane = new JScrollPane(list);
        artists.add(scrollPane);
        
        JButton addArtists = new JButton("+");
        addArtists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean chosen = false;
                
                //artists 
                ArrayList listArtists = new ArrayList<Artist>();
                //for every artist in the agenda
                for(Artist artistAgenda: agenda.getArtists()){
                    
                    System.out.println("artist: " + artistAgenda);
                    
                    //for every artist in the model.
                    for(Object artistObject: artistModel.toArray()){
                        Artist artistModel = (Artist) artistObject;
                        
                        System.out.println("vergelijk: " + artistModel + " - " + artistAgenda);
                        
                        if(artistModel == artistAgenda) {
                            System.out.println("true: " + artistModel + " - " + artistAgenda);
                            chosen = true;
                        }                    
                    }
                    if(!chosen){
                        System.out.println(artistAgenda);
                        listArtists.add(artistAgenda);
                    }
                    chosen  = false;
                }

                Artist[] artist = new Artist[listArtists.size()];
                
                for(int i = 0; i<listArtists.size(); i++){
                    artist[i] = (Artist) listArtists.get(i);
                }
                Artist chosenArtist = (Artist) JOptionPane.showInputDialog(null, "add artist", "add artist", JOptionPane.PLAIN_MESSAGE, null, artist ,null);
                artistModel.addElement(chosenArtist);
            }
        });
        
        artists.add(addArtists);
        return artists;
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

        Artist[] artists = new Artist[this.artistModel.size()];
        for(int i = 0; i < artistModel.size(); i++){
            artists[i] = (Artist) artistModel.get(i);
        }
        
        Act act = new Act(this.name.getText(), (Stage) this.stageComboBox.getSelectedItem(), this.genre.getText(), new ActTime(2015,02,11,21,00  ,2015,02,11,23,00), artists);
        System.out.println(act);
        
        this.model.addElement(act);
        this.agenda.addAct(act);
        
        dispose();

    }

    private void cancel(){
        dispose();
    }
}

class ArtistCellRenderer extends JLabel implements ListCellRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    public ArtistCellRenderer() {
        setOpaque(true);
        setIconTextGap(12);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        Artist stage = (Artist) value;
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

