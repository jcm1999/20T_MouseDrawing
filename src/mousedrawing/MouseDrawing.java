//josephm
package mousedrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class MouseDrawing extends JFrame {
    
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenu canvasMenu = new JMenu("Canvas");
    JMenuItem whiteMenuItem = new JMenuItem("WHITE");
    JMenuItem blackMenuItem = new JMenuItem("BLACK");
    JMenuItem blueMenuItem = new JMenuItem("BLUE");
    JMenuItem yellowMenuItem = new JMenuItem("YELLOW");
    JMenuItem pinkMenuItem = new JMenuItem("PINK");
    JMenu penMenu = new JMenu("Pen");
    JMenuItem smallMenuItem = new JMenuItem("Small");
    JMenuItem mediumMenuItem = new JMenuItem("Medium");
    JMenuItem largeMenuItem = new JMenuItem("Large");
    JPanel drawPanel = new JPanel();
    JLabel leftColorLabel = new JLabel();
    JLabel rightColorLabel = new JLabel();
    JPanel colorPanel = new JPanel();
    JLabel[] colorLabel = new JLabel[16];
    Graphics2D g2D;
    double xPrevious, yPrevious;
    Color drawColor, leftColor, rightColor;

    public static void main(String args[]) {

        // construct frame
        new MouseDrawing().setVisible(true);
    }

    public MouseDrawing() {
        //frame constructor
        setTitle("Artistic Creations by Joseph M. March 2018");
        setResizable(false);
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        }
        );
        getContentPane().setLayout(new GridBagLayout());

        // build menu
        setJMenuBar(mainMenuBar);
        fileMenu.setMnemonic('F');
        mainMenuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        
        canvasMenu.setMnemonic('C');
        mainMenuBar.add(canvasMenu);
        canvasMenu.add(whiteMenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(blackMenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(blueMenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(yellowMenuItem);
        canvasMenu.addSeparator();
        canvasMenu.add(pinkMenuItem);
        
        penMenu.setMnemonic('P');
        mainMenuBar.add(penMenu);
        penMenu.add(smallMenuItem);
        penMenu.addSeparator();
        penMenu.add(mediumMenuItem);
        penMenu.addSeparator();
        penMenu.add(largeMenuItem);
        
        mainMenuBar.setBackground(Color.decode("#4499DD"));
        getContentPane().setBackground(Color.decode("#AAFFAA"));

        newMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                newMenuItemActionPerformed(e);
            }
        });
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitMenuItemActionPerformed(e);
            }
        });
        whiteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                whiteMenuItemActionPerformed(e);
            }
        });
        blackMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                blackMenuItemActionPerformed(e);
            }
        });
        blueMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                blueMenuItemActionPerformed(e);
            }
        });
        yellowMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                yellowMenuItemActionPerformed(e);
            }
        });
        pinkMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pinkMenuItemActionPerformed(e);
            }
        });
        smallMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                smallMenuItemActionPerformed(e);
            }
        });
        mediumMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mediumMenuItemActionPerformed(e);
            }
        });
        largeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                largeMenuItemActionPerformed(e);
            }
        });
        drawPanel.setPreferredSize(new Dimension(700, 700));
        drawPanel.setBackground(Color.WHITE);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 2;
        gridConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(drawPanel, gridConstraints);
        drawPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                drawPanelMousePressed(e);
            }
        });
        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                drawPanelMouseDragged(e);
            }
        });
        drawPanel.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                drawPanelMouseReleased(e);
            }
        });

        leftColorLabel.setPreferredSize(new Dimension(40, 40));
        leftColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10, 5, 10, 10);
        getContentPane().add(leftColorLabel, gridConstraints);
        
        rightColorLabel.setPreferredSize(new Dimension(40, 40));
        rightColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10, 5, 10, 10);
        getContentPane().add(rightColorLabel, gridConstraints);
        
        colorPanel.setPreferredSize(new Dimension(80,270));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(colorPanel, gridConstraints);
        
        colorPanel.setLayout(new GridBagLayout());
        int j = 0;
        for(int i = 0; i<16; i++) {
            colorLabel[i] = new JLabel();
            colorLabel[i].setPreferredSize(new Dimension(30,30));
            colorLabel[i].setOpaque(true);
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = j;
            gridConstraints.gridy = i - j * 8;
            colorPanel.add(colorLabel[i], gridConstraints);
            if(i == 7) {
                j++;
            }
            
            // add this listener first 06 March 2018
            colorLabel[i].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e)
                {
                    colorMousePressed(e);
                }
            });
        }
            // set color labels
            colorLabel[0].setBackground(Color.WHITE);
            colorLabel[1].setBackground(Color.RED);
            colorLabel[2].setBackground(Color.ORANGE);
            colorLabel[3].setBackground(Color.YELLOW);
            colorLabel[4].setBackground(Color.GREEN);
            colorLabel[5].setBackground(Color.CYAN);
            colorLabel[6].setBackground(Color.PINK);
            colorLabel[7].setBackground(Color.LIGHT_GRAY);
            colorLabel[8].setBackground(Color.BLACK);
            colorLabel[9].setBackground(Color.decode("#AA2222"));
            colorLabel[10].setBackground(Color.decode("#BB8844"));
            colorLabel[11].setBackground(Color.decode("#AAAA22"));
            colorLabel[12].setBackground(Color.decode("#22AA22"));
            colorLabel[13].setBackground(Color.BLUE);
            colorLabel[14].setBackground(Color.MAGENTA);
            colorLabel[15].setBackground(Color.DARK_GRAY);
            leftColor = colorLabel[0].getBackground();
            leftColorLabel.setBackground(leftColor);
            rightColor = colorLabel[15].getBackground();
            rightColorLabel.setBackground(rightColor);
            
            pack();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setBounds((int) (0.5 * (screenSize.width - getWidth())),
                    (int) (0.5 * (screenSize.height - getHeight())),
                    getWidth(), getHeight());
            //create graphics object
            g2D = (Graphics2D) drawPanel.getGraphics();
        }
    // Top menu bar functions
    private void newMenuItemActionPerformed(ActionEvent e) {
                int response;
                response = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to start a new drawing?",
                "New Drawing",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.YES_OPTION) {
                    g2D.setPaint(drawPanel.getBackground());
                    g2D.fill(new Rectangle2D.Double(0,0,drawPanel.getWidth(),
                            drawPanel.getHeight()));
                }
                      }
    private void exitMenuItemActionPerformed(ActionEvent e) {
                int response;
                response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the Blackboard program?",
                        "Exit Program",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(response == JOptionPane.NO_OPTION) {
                    return;
                } else {
                    exitForm(null);
                }
            }
    private void whiteMenuItemActionPerformed(ActionEvent e) {
        drawPanel.setBackground(Color.WHITE);
    }
    private void blackMenuItemActionPerformed(ActionEvent e) {
        drawPanel.setBackground(Color.BLACK);
    }
    private void blueMenuItemActionPerformed(ActionEvent e) {
        drawPanel.setBackground(Color.BLUE);
    }
    private void yellowMenuItemActionPerformed(ActionEvent e) {
        drawPanel.setBackground(Color.YELLOW);
    }
    private void pinkMenuItemActionPerformed(ActionEvent e) {
        drawPanel.setBackground(Color.PINK);
    }
    private void smallMenuItemActionPerformed(ActionEvent e) {
        g2D.setStroke(new BasicStroke(1F));
    }
    private void mediumMenuItemActionPerformed(ActionEvent e) {
        g2D.setStroke(new BasicStroke(4F));
    }
    private void largeMenuItemActionPerformed(ActionEvent e) {
        g2D.setStroke(new BasicStroke(8F));
    }
    private void colorMousePressed(MouseEvent e) {
        // decide which color was selected and which button was used
        Component clickedColor = e.getComponent();
        // Make audible tone and set drawing color
        Toolkit.getDefaultToolkit().beep();
        if(e.getButton() == MouseEvent.BUTTON1){
            leftColor = clickedColor.getBackground();
            leftColorLabel.setBackground(leftColor);
        } else if(e.getButton() == MouseEvent.BUTTON3) {
            rightColor = clickedColor.getBackground();
            rightColorLabel.setBackground(rightColor);
        }
    }
    private void drawPanelMousePressed(MouseEvent e) {
        // if left button or right button clicked, set color and start drawing
        if(e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
            xPrevious = e.getX();
            yPrevious = e.getY();
            if(e.getButton() == MouseEvent.BUTTON1) {
                drawColor = leftColor;
            } else {
                drawColor = rightColor;
            }
        }
        //System.out.println("mouse x, y =" + xPrevious + ", " + yPrevious);
    }
    
    private void drawPanelMouseDragged(MouseEvent e) {
        // if drawing, connect previous point with new point
        Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
        g2D.setPaint(drawColor);
        g2D.draw(myLine);
        xPrevious = e.getX();
        yPrevious = e.getY();
    }
    private void drawPanelMouseReleased(MouseEvent e) {
        //if left or button released, connect last point
        if(e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
            Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, e.getX(), e.getY());
            g2D.setPaint(drawColor);
            g2D.draw(myLine);
        }
    }
    private void exitForm(WindowEvent e) {
        g2D.dispose();
        System.exit(0);
    }

}
