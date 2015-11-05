/*
 * Created by JFormDesigner on Thu Jun 18 16:55:03 CST 2015
 */

package com.ccb.qd.frame;

import com.ccb.qd.Do.SearchRunMethods;
import com.ccb.qd.Do.TreeMenuMap;
import com.ccb.qd.common.WebStepUtils;
import com.ccb.qd.webcrawler.IWebSearch;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author han
 */
@org.springframework.stereotype.Component
public class webGroupFrame extends JFrame {
    public webGroupFrame() {
        initComponents();
    }
    private    IWebSearch iWebSearch;
private SearchRunMethods searchRunMethods;
    private  MyIe ie;
    public TreeMenuMap getTreemap() {
        return treemap;
    }

    public void setTreemap(TreeMenuMap treemap) {
        this.treemap = treemap;
    }

    @Autowired
@Qualifier("treeMenuMap")
private TreeMenuMap treemap;

    private void menuItem2KeyReleased(KeyEvent e) {
        System.exit(0);
    }

    private void menuItem2MouseReleased(MouseEvent e) {
        System.exit(0);
    }

    private void menuItem2MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void thisWindowOpened(WindowEvent e) {
        // TODO add your code here
 ie=new MyIe();

    }

    private void button1MouseReleased(MouseEvent e)   {
        // TODO add your code here
       Method run1=(Method)searchRunMethods.Name("search");
        try {
            run1.invoke(iWebSearch);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }


    private void tree1MouseReleased(MouseEvent e)  {
        // TODO add your code here
        DefaultMutableTreeNode selected = (DefaultMutableTreeNode) this.JtreeSearchType.getLastSelectedPathComponent();
      String o=  selected.getUserObject().toString();
        treemap.init();
       iWebSearch= treemap.get(o);

        searchRunMethods=WebStepUtils.getMethodAnnotation(iWebSearch.getClass(), "WebStep");
        this.imagePanel2.setVisible(false);
        this.textField2.setVisible(false);
        this.textField1.setVisible(false);
        this.list1.setVisible(false);
      iWebSearch.TransParam(this.imagePanel2,this.textField2,this.textField1,this.list1,this.ie);
        Method run0=(Method)searchRunMethods.Name("init");
        try {
            run0.invoke(iWebSearch);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        this.imagePanel2.setVisible(true); this.textField2.setVisible(true);this.textField1.setVisible(true);this.list1.setVisible(true);

    }

    private void btnSecCode(MouseEvent e) {
        Method run0=(Method)searchRunMethods.Name("afterSec");
        try {
            run0.invoke(iWebSearch);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }

    private void createUIComponents() {
        // TODO: add custom component creation code here
    }

    private void btnOKfromJlist(MouseEvent e) {
     /* String strl=(String )  list1.getModel().getElementAt(list1.getSelectedIndex());
       String str= strl.split("\\|",0)[1];*/
        Method run0=(Method)searchRunMethods.Name("clickList");
        try {
            run0.invoke(iWebSearch);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        createUIComponents();

        menuBar1 = new JMenuBar();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem2 = new JMenuItem();
        menu5 = new JMenu();
        menuItem3 = new JMenuItem();
        menuItem4 = new JMenuItem();
        splitPane1 = new JSplitPane();
        scrollPane1 = new JScrollPane();
        panel1 = new JPanel();
        tabbedPane1 = new JTabbedPane();
        panel2 = new JPanel();
        textField1 = new JTextField();
        button1 = new JButton();
        panel3 = new JPanel();
        imagePanel2 = new ImagePanel();
        panel4 = new JPanel();
        panel8 = new JPanel();
        panel9 = new JPanel();
        textField2 = new JTextField();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        button2 = new JButton();
        scrollPane2 = new JScrollPane();
        list1 = new JList();
        panel10 = new JPanel();
        button3 = new JButton();
        action1 = new SaveResult();

        //======== this ========
        setIconImage(((ImageIcon)UIManager.getIcon("FileView.computerIcon")).getImage());
        setTitle("Info");
        setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                thisWindowOpened(e);
            }
        });
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout());

        //======== menuBar1 ========
        {

            //======== menu1 ========
            {
                menu1.setText("File");

                //---- menuItem1 ----
                menuItem1.setAction(action1);
                menu1.add(menuItem1);

                //---- menuItem2 ----
                menuItem2.setText("\u9000\u51fa\u7cfb\u7edf");
                menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.ALT_MASK));
                menuItem2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        menuItem2MouseClicked(e);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        menuItem2MouseReleased(e);
                    }
                });
                menuItem2.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        menuItem2KeyReleased(e);
                    }
                });
                menu1.add(menuItem2);
            }
            menuBar1.add(menu1);

            //======== menu5 ========
            {
                menu5.setText("\u5e2e\u52a9");

                //---- menuItem3 ----
                menuItem3.setText("\u5e2e\u52a9");
                menuItem3.setIcon(UIManager.getIcon("OptionPane.questionIcon"));
                menuItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, KeyEvent.CTRL_MASK));
                menu5.add(menuItem3);

                //---- menuItem4 ----
                menuItem4.setText("\u5173\u4e8e");
                menu5.add(menuItem4);
            }
            menuBar1.add(menu5);
        }
        setJMenuBar(menuBar1);

        //======== splitPane1 ========
        {

            //======== scrollPane1 ========
            {

                //---- JtreeSearchType ----
                 JtreeSearchType=new JTree(createTree());
                JtreeSearchType.setName("jtree");
                JtreeSearchType.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        tree1MouseReleased(e);
                    }
                });
                scrollPane1.setViewportView(JtreeSearchType);
            }
            splitPane1.setLeftComponent(scrollPane1);

            //======== panel1 ========
            {
                panel1.setLayout(new GridLayout());

                //======== tabbedPane1 ========
                {

                    //======== panel2 ========
                    {

                        //---- button1 ----
                        button1.setText("\u67e5\u8be2");
                        button1.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseReleased(MouseEvent e) {
                                button1MouseReleased(e);
                            }
                        });

                        //======== panel3 ========
                        {
                            panel3.setLayout(new GridLayout(1, 2));
                            panel3.add(imagePanel2);

                            //======== panel4 ========
                            {
                                panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));

                                //======== panel8 ========
                                {
                                    panel8.setLayout(new VerticalLayout());

                                    //======== panel9 ========
                                    {
                                        panel9.setLayout(new GridLayout());
                                    }
                                    panel8.add(panel9);

                                    //---- textField2 ----
                                    textField2.setHorizontalAlignment(SwingConstants.CENTER);
                                    panel8.add(textField2);
                                }
                                panel4.add(panel8);
                            }
                            panel3.add(panel4);

                            //======== panel5 ========
                            {
                                panel5.setLayout(new VerticalLayout());

                                //======== panel6 ========
                                {
                                    panel6.setLayout(new GridLayout());
                                }
                                panel5.add(panel6);

                                //======== panel7 ========
                                {
                                    panel7.setLayout(new HorizontalLayout());

                                    //---- button2 ----
                                    button2.setText("\u63d0\u4ea4");
                                    button2.setVerticalAlignment(SwingConstants.TOP);
                                    button2.setHorizontalAlignment(SwingConstants.RIGHT);
                                    button2.addMouseListener(new MouseAdapter() {
                                        @Override
                                        public void mouseReleased(MouseEvent e) {
                                            btnSecCode(e);
                                        }
                                    });
                                    panel7.add(button2);
                                }
                                panel5.add(panel7);
                            }
                            panel3.add(panel5);
                        }

                        //======== scrollPane2 ========
                        {

                            //---- list1 ----
                            list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                            scrollPane2.setViewportView(list1);
                        }

                        //======== panel10 ========
                        {
                            panel10.setLayout(new BoxLayout(panel10, BoxLayout.X_AXIS));

                            //---- button3 ----
                            button3.setText("\u786e\u5b9a");
                            button3.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseReleased(MouseEvent e) {
                                    btnOKfromJlist(e);
                                }
                            });
                            panel10.add(button3);
                        }

                        GroupLayout panel2Layout = new GroupLayout(panel2);
                        panel2.setLayout(panel2Layout);
                        panel2Layout.setHorizontalGroup(
                            panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(button1)
                                        .addContainerGap(182, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                        .addGap(0, 17, Short.MAX_VALUE)
                                        .addComponent(panel3, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 71, 71))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(panel10, GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                        panel2Layout.setVerticalGroup(
                            panel2Layout.createParallelGroup()
                                .addGroup(panel2Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(button1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(panel3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(panel10, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                        );
                    }
                    tabbedPane1.addTab("\u9996\u9875", panel2);
                }
                panel1.add(tabbedPane1);
            }
            splitPane1.setRightComponent(panel1);
        }
        contentPane.add(splitPane1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenu menu5;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;
    private JSplitPane splitPane1;
    private JScrollPane scrollPane1;
    private JTree JtreeSearchType;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JTextField textField1;
    private JButton button1;
    private JPanel panel3;
    private ImagePanel imagePanel2;
    private JPanel panel4;
    private JPanel panel8;
    private JPanel panel9;
    private JTextField textField2;
    private JPanel panel5;
    private JPanel panel6;
    private JPanel panel7;
    private JButton button2;
    private JScrollPane scrollPane2;
    private JList list1;
    private JPanel panel10;
    private JButton button3;
    private SaveResult action1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private class SaveResult extends AbstractAction {
        private SaveResult() {
            // JFormDesigner - Action initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
            putValue(NAME, "\u4fdd\u5b58\u7ed3\u679c");
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK));
            // JFormDesigner - End of action initialization  //GEN-END:initComponents
        }

        public void actionPerformed(ActionEvent e) {
            if (e != null) {
                Object o = e.getSource();
                if (o instanceof  JMenuItem){
                    JMenuItem mi=(JMenuItem)o;
                    mi.getParent();
                }
            }

        }
    }

    public DefaultTreeModel createTree(){
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("/");
        DefaultMutableTreeNode      parent;

        parent = new DefaultMutableTreeNode("山东工商查询");
        root.add(parent);
        parent = new DefaultMutableTreeNode("红盾查询");
        root.add(parent);
        return new DefaultTreeModel(root);
    }
}
