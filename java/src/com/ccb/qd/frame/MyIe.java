/*
 * Created by JFormDesigner on Mon Jul 13 15:37:41 CST 2015
 */

package com.ccb.qd.frame;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.awt.*;
import java.io.IOException;

/**
 * @author han
 */
public class MyIe extends JFrame   {
    public JEditorPane getEditPanel(){
        return  this.editorPane1;
    }

 public JFrame getFrame(){
     return  frame1;

 }
    public MyIe() {
        initComponents();
    }

    private void editorPane1HyperlinkUpdate(HyperlinkEvent e) {
        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
        {
            JEditorPane pane = (JEditorPane) e.getSource();
            if (e instanceof HTMLFrameHyperlinkEvent)
            {
                HTMLFrameHyperlinkEvent evt = (HTMLFrameHyperlinkEvent) e;
                HTMLDocument doc = (HTMLDocument) pane.getDocument();
                doc.processHTMLFrameHyperlinkEvent(evt);
            }
            else
            {
                try
                {
                    pane.setPage(e.getURL());
                }
                catch (Throwable t)
                {
                    t.printStackTrace();
                }
            }
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        frame1 = new JFrame();
        scrollPane1 = new JScrollPane();
        editorPane1 = new JEditorPane();

        //======== frame1 ========
        {
            Container frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(new BorderLayout());

            //======== scrollPane1 ========
            {

                //---- editorPane1 ----
                editorPane1.setEditable(false);
                editorPane1.addHyperlinkListener(new HyperlinkListener() {
                    @Override
                    public void hyperlinkUpdate(HyperlinkEvent e) {
                        editorPane1HyperlinkUpdate(e);
                    }
                });
                scrollPane1.setViewportView(editorPane1);
            }
            frame1ContentPane.add(scrollPane1, BorderLayout.CENTER);
            frame1.pack();
            frame1.setLocationRelativeTo(frame1.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JFrame frame1;
    private JScrollPane scrollPane1;
    private JEditorPane editorPane1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static  void main(String [] args) throws IOException {
        MyIe jf=new MyIe();
        jf.getEditPanel().setPage("http://www.sina.com");
        jf.getEditPanel().setEnabled(false);
        jf.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getFrame().setVisible(true);
    }
}
