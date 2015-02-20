package gui.panels.edit.infoPanes;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by gjoosen on 20/02/15.
 */
public class ActInfo extends JPanel {
    
    public ActInfo(){
        super.setLayout(new BorderLayout());
        super.add(new JButton("act"), BorderLayout.CENTER);
        super.add(new JButton("act"));
        super.add(new JButton("act"));
        super.add(new JButton("act"));
        super.invalidate();
        super.validate();
        super.repaint();
    }
    
}
