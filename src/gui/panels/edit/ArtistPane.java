package gui.panels.edit;

import agenda.Agenda;
import agenda.Artist;
import gui.panels.edit.dialogs.ArtistDialogPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by gjoosen on 13/02/15.
 */
public class ArtistPane extends JPanel {

    private JList artistList;
    private Agenda agenda;
    private DefaultListModel model;
    
    private JPanel detailsPanel;
    
    public ArtistPane(Agenda agenda, JPanel detailsPanel){
        this.agenda = agenda;
        this.model = new DefaultListModel();
        this.detailsPanel = detailsPanel;
        
        super.setLayout(new BorderLayout());
        super.add(new JLabel("Artists"), BorderLayout.NORTH);
        super.add(new JPanel(), BorderLayout.EAST);
        super.add(new JPanel(), BorderLayout.WEST);
        super.add(this.buttonRow(), BorderLayout.SOUTH);

        //the JList needs an array, so you have to go from the List to an array.

        for(Artist artist: this.agenda.getArtists()){
            this.model.addElement(artist);
        }
        
        //initialize the JList with the artist objects.
        this.artistList = new JList();

        this.artistList.setModel(this.model);
        
        //the cell renderer.
        this.artistList.setCellRenderer(new ArtistCellRenderer());
        
        //double click
        this.artistList.addMouseListener(new DoubleClickArtist(this.artistList, this));
        
        //the JList inside a scrollPane.
        JScrollPane scrollPane = new JScrollPane(this.artistList);
        super.add(scrollPane, BorderLayout.CENTER);
    }
    
    private JPanel buttonRow(){
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        
        JButton addArtist = new JButton("add");
        addArtist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new ArtistDialogPanel(agenda, model);
                dialog.setLocation(getCenterOfScreen(dialog));
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        
        buttonPane.add(addArtist);
        
        JButton removeButton = new JButton("remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object object = model.remove(artistList.getSelectedIndex());
                agenda.removeArtist((Artist) object);
                
                System.out.println(agenda.getArtists());
                System.out.println(model);
            }
        });
        buttonPane.add(removeButton);
        return buttonPane;
    }

    private Point getCenterOfScreen(JDialog dialog){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int x = (int) ((screenSize.getWidth() - dialog.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - dialog.getHeight()) / 2);

        Point center = new Point(x, y);
        System.out.println(center.getX() + " - " + center.getY());
        return center;
    }
    
    public void editDialog(){
        JDialog dialog = new ArtistDialogPanel(ArtistPane.this.agenda, model, (Artist) artistList.getSelectedValue());
        dialog.setLocation(getCenterOfScreen(dialog));
        dialog.pack();
        dialog.setVisible(true);
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

class DoubleClickArtist extends MouseAdapter {
    protected JList list;
    private ArtistPane pane;

    public DoubleClickArtist(JList l, ArtistPane pane){
        this.pane = pane;
        list = l;
    }

    public void mouseClicked(MouseEvent e){
        if(e.getClickCount() == 2){
            int index = list.locationToIndex(e.getPoint());
            ListModel dlm = list.getModel();
            Object item = dlm.getElementAt(index);;
            list.ensureIndexIsVisible(index);
            System.out.println("Double clicked on " + item);
            this.pane.editDialog();
        }
    }
}
