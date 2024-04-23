import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DistributorGUI {
    private JPanel panel1; private JFrame mainFrame;private JButton reportsMenuButton;
    private JLabel label1; private JButton subsMenuButton;private JPanel reportsPanel;
    private JButton journalMenuButton; private JPanel journalPanel;
    private JButton paymentMenuButton; private JLabel journalLabel;
    private JPanel subsPanel;private JPanel paymentPanel;
    private JLabel subsLabel;private JLabel paymentLabel;

    private Distributor distributor;
    DistributorGUI(){
        // ------- Distributor ----------------
        distributor = new Distributor();
        Individual bora = new Individual("Bora","Bursa","54001234",12,2030,432);
        Individual egemen = new Individual("Egemen","İstanbul","34171724",5,2030,444);
        Individual yigit = new Individual("Yigit","Ankara","361724",5,2030,444);
        Corporation tekinAS = new Corporation("Tekin AS","Çanakkale","QNB",345,1,5,2024,223344);
        Corporation baturalpAS = new Corporation("Baturalp AS","Giresun","QNB",322,1,1,2024,221244);
        Journal gq = new Journal(24,10.90,"GQMan");
        Journal forbes = new Journal(6,25,"Forbes");

        Subscription boraToGq = new Subscription(new DateInfo(1,2024),1,gq,bora);
        Subscription egemenToGq = new Subscription(new DateInfo(1,2024),2,gq,egemen);
        Subscription tekinasToGq = new Subscription(new DateInfo(1,2024),100,gq,tekinAS);

        Subscription baturalpasToForbes = new Subscription(new DateInfo(1,2024),100,forbes,baturalpAS);
        Subscription yigitToForbes = new Subscription(new DateInfo(1,2025),10,forbes,yigit);


        distributor.addJournal(forbes);
        distributor.addJournal(gq);
        distributor.addSubscriber(egemen);
        distributor.addSubscriber(baturalpAS);
        distributor.addSubscriber(tekinAS);
        distributor.addSubscriber(bora);
        distributor.addSubscriber(yigit);
        distributor.addSubscription(gq.getIssn(),egemen,egemenToGq);
        distributor.addSubscription(gq.getIssn(),bora,boraToGq);
        distributor.addSubscription(gq.getIssn(),tekinAS,tekinasToGq);
        distributor.addSubscription(forbes.getIssn(),baturalpAS,baturalpasToForbes);
        distributor.addSubscription(forbes.getIssn(),yigit,yigitToForbes);
        // ---------- Main Menu ----------------
        mainFrame = new JFrame("Alperen Tekin 21011010");
        mainFrame.setSize(960,540);
        mainFrame.setVisible(true);
        mainFrame.setPreferredSize(new Dimension(1080, 640));
        mainFrame.setResizable(false);
        mainFrame.setLayout(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        var MainMenuContentPane = mainFrame.getContentPane();
        MainMenuContentPane.setLayout(null);
        //----------------- Main Menu Panel --------------------------------------
        {
            panel1 = new JPanel();
            panel1.setBackground(new Color(0xf0c419));
            panel1.setBounds(0,0,1080,640);
            panel1.setPreferredSize(new Dimension(1080, 640));
            panel1.setLayout(null);
            //------------------ Main Menu Background ---------------
            label1 = new JLabel();
            label1.setText("TKN Journal Distributor  ");
            label1.setFont(new Font("High Tower Text", Font.BOLD | Font.ITALIC, 56));
            label1.setForeground(new Color(0xd34234));
            label1.setIcon(new ImageIcon("book_cropped.gif"));
            label1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            label1.setBounds(70, 0, 1080, 480);
            panel1.add(label1);
            //----------------- Main Menu Journal Button -----------------
            journalMenuButton = new JButton();
            journalMenuButton.setText("Journal Menu");
            journalMenuButton.setBackground(new Color(0xf0c419));
            journalMenuButton.setForeground(new Color(0xd34234));
            journalMenuButton.setFont(new Font("Bahnschrift", Font.BOLD, 22));
            journalMenuButton.setContentAreaFilled(false);
            journalMenuButton.setFocusable(false);
            panel1.add(journalMenuButton);
            journalMenuButton.setBounds(450, 355, 240, 50);
            journalMenuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel1.setEnabled(false);
                    panel1.setVisible(false);
                    journalPanel.setVisible(true);
                    journalPanel.setEnabled(true);
                }
            });

            //----------------- Main Menu Payment Button -----------------
            paymentMenuButton = new JButton();
            paymentMenuButton.setText("Payments Menu");
            paymentMenuButton.setBackground(new Color(0xf0c419));
            paymentMenuButton.setForeground(new Color(0xd34234));
            paymentMenuButton.setFont(new Font("Bahnschrift", Font.BOLD, 22));
            paymentMenuButton.setContentAreaFilled(false);
            paymentMenuButton.setFocusable(false);
            panel1.add(paymentMenuButton);
            paymentMenuButton.setBounds(725, 355, 240, 50);
            paymentMenuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel1.setEnabled(false);
                    panel1.setVisible(false);
                    paymentPanel.setVisible(true);
                    paymentLabel.setEnabled(true);
                }
            });
            //----------------- Main Menu Subs Button -----------------
            subsMenuButton = new JButton();
            subsMenuButton.setText("Subscriptions Menu");
            subsMenuButton.setBackground(new Color(0xf0c419));
            subsMenuButton.setForeground(new Color(0xd34234));
            subsMenuButton.setFont(new Font("Bahnschrift", Font.BOLD, 20));
            subsMenuButton.setHorizontalTextPosition(SwingConstants.CENTER);
            subsMenuButton.setVerticalTextPosition(SwingConstants.CENTER);
            subsMenuButton.setFocusable(false);
            subsMenuButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            panel1.add(subsMenuButton);
            subsMenuButton.setBounds(450, 425, 240, 50);
            subsMenuButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    panel1.setEnabled(false);
                    panel1.setVisible(false);
                    subsPanel.setVisible(true);
                    subsPanel.setEnabled(true);
                }
            });
        }
        //-----------------  Report Button -----------------
        reportsMenuButton = new JButton();
        reportsMenuButton.setText("Reports");
        reportsMenuButton.setBackground(new Color(0xf0c419));
        reportsMenuButton.setForeground(new Color(0xd34234));
        reportsMenuButton.setFont(new Font("Bahnschrift", Font.BOLD, 22));
        reportsMenuButton.setHorizontalTextPosition(SwingConstants.CENTER);
        reportsMenuButton.setVerticalTextPosition(SwingConstants.CENTER);
        reportsMenuButton.setFocusable(false);
        reportsMenuButton.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panel1.add(reportsMenuButton);
        reportsMenuButton.setBounds(725, 425, 240, 50);
        reportsMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame();
                frame.setLayout(new FlowLayout());
                frame.setSize(640,120);
                frame.setPreferredSize(new Dimension(640,120));
                frame.setVisible(true);
                frame.getContentPane().setBackground(Color.orange);
                frame.setLocationRelativeTo(null);

                JButton save = new JButton("Save");
                save.setFocusable(false);
                save.setPreferredSize(new Dimension(200,50));
                save.setBackground(new Color(0xd34234));
                save.setForeground(Color.white);

                JButton load = new JButton("Load");
                load.setFocusable(false);
                load.setPreferredSize(new Dimension(200,50));
                load.setBackground(new Color(0xd34234));
                load.setForeground(Color.white);

                JButton report = new JButton("Report");
                report.setFocusable(false);
                report.setPreferredSize(new Dimension(200,50));
                report.setBackground(new Color(0xd34234));
                report.setForeground(Color.white);

                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (distributor.saveState("data.dat")){
                            JOptionPane.showMessageDialog(
                                    null, "File Saved!",
                                    "FILE", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(
                                    null, "File Couldn't Saved!",
                                    "Warning", JOptionPane.WARNING_MESSAGE);
                        }
                        frame.setEnabled(false);
                        frame.setVisible(false);
                    }
                });


                load.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame frame = new JFrame();
                        frame.setLayout(new FlowLayout());
                        frame.setSize(240,150);
                        frame.setPreferredSize(new Dimension(240,150));
                        frame.setVisible(true);
                        frame.getContentPane().setBackground(Color.orange);
                        frame.setLocationRelativeTo(null);

                        JTextField textField1 = new JTextField("Enter A File Name");
                        textField1.setEditable(true);
                        textField1.setBackground(Color.white);
                        textField1.setForeground(Color.black);
                        textField1.setOpaque(true);
                        textField1.setPreferredSize(new Dimension(200,50));


                        JButton submit = new JButton("Submit");
                        submit.setFocusable(false);
                        submit.setPreferredSize(new Dimension(200,50));
                        submit.setBackground(new Color(0xd34234));
                        submit.setForeground(Color.white);

                        submit.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String string;
                                string = textField1.getText().trim();
                                   if (distributor.loadState(string)){
                                       JOptionPane.showMessageDialog(
                                               null, "File Loaded!",
                                               "FILE", JOptionPane.INFORMATION_MESSAGE);
                                   }
                                   else{
                                       JOptionPane.showMessageDialog(
                                               null, "File Couldn't Loaded!",
                                               "Warning", JOptionPane.WARNING_MESSAGE);
                                   }
                                frame.setEnabled(false);
                                frame.setVisible(false);
                            }
                        });

                        frame.add(textField1);
                        frame.add(submit);
                        frame.pack();
                    }
                });
                report.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if ( distributor.isThreadPoolDone ){
                            distributor.report();
                        }
                        else
                            JOptionPane.showMessageDialog(
                                    null,"System is Busy Right Now Try Again($Thread)!",
                                    "Warning",JOptionPane.WARNING_MESSAGE);
                        frame.setEnabled(false);
                        frame.setVisible(false);
                    }
                });
                frame.add(report);
                frame.add(save);
                frame.add(load);
                frame.pack();



            }
        });

        //---------------- Journal Menu Panel ---------------------------------------------------------------------
        {
            journalPanel = new JPanel();
            journalPanel.setBackground(new Color(0xf0c419));
            journalPanel.setBounds(0,0,1080,640);
            journalPanel.setPreferredSize(new Dimension(1080, 640));
            journalPanel.setLayout(null);
            journalPanel.setVisible(false);
            journalPanel.setEnabled(true);
            journalPanel.setLayout(null);
                //---------- Journal Menu LABEL -----------------------------------
                journalLabel = new JLabel();
                journalLabel.setVisible(true);
                journalLabel = new JLabel();
                journalLabel.setText("Journal Menu");
                journalLabel.setFont(new Font("Consolas", Font.BOLD, 48));
                journalLabel.setForeground(new Color(0xd34234));
                journalLabel.setIcon(new ImageIcon("journal_book256.png"));
                journalLabel.setIconTextGap(150);
                journalLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
                journalLabel.setBounds(-100, 25, 1080, 300);
                journalPanel.add(journalLabel);
                    // -------------------- BACK TO MAIN BUTTON -------------------------
                    JButton backToMain = new JButton("Main Menu");
                    backToMain.setBackground(new Color(0xd34234));
                    backToMain.setForeground(new Color(0xf0c419));
                    backToMain.setFont(new Font("Maiandra GD", Font.BOLD, 26));
                    backToMain.setHorizontalTextPosition(SwingConstants.CENTER);
                    backToMain.setFocusable(false);
                    backToMain.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    journalPanel.add(backToMain);
                    backToMain.setBounds(650, 400, 250, 50);
                    backToMain.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            journalPanel.setEnabled(false);
                            journalPanel.setVisible(false);
                            panel1.setEnabled(true);
                            panel1.setVisible(true);
                        }
                    });
                    //--------------------- ADD JOURNAL BUTTON -----------------------------
                    JButton addJournal = new JButton("Add Journal");
                    addJournal.setBackground(new Color(0xd34234));
                    addJournal.setForeground(new Color(0xf0c419));
                    addJournal.setFont(new Font("Maiandra GD", Font.BOLD, 26));
                    addJournal.setHorizontalTextPosition(SwingConstants.CENTER);
                    addJournal.setFocusable(false);
                    addJournal.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    journalPanel.add(addJournal);
                    addJournal.setBounds(50, 400, 250, 50);
                    addJournal.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            JFrame frame = new JFrame();
                            frame.setLayout(new FlowLayout());
                            frame.setSize(480,200);
                            frame.setPreferredSize(new Dimension(480,200));
                            frame.setVisible(true);
                            frame.getContentPane().setBackground(Color.orange);
                            frame.setLocationRelativeTo(null);

                            JTextField textField1 = new JTextField("Enter name");
                            textField1.setEditable(true);
                            textField1.setBackground(Color.white);
                            textField1.setForeground(Color.black);
                            textField1.setOpaque(true);
                            textField1.setPreferredSize(new Dimension(150,50));

                            JTextField textField2 = new JTextField("Enter Frequency");
                            textField2.setEditable(true);
                            textField2.setBackground(Color.WHITE);
                            textField2.setForeground(Color.BLACK);
                            textField2.setOpaque(true);
                            textField2.setPreferredSize(new Dimension(150,50));

                            JTextField textField3 = new JTextField("Enter Issue Price");
                            textField3.setEditable(true);
                            textField3.setBackground(Color.white);
                            textField3.setForeground(Color.BLACK);
                            textField3.setOpaque(true);
                            textField3.setPreferredSize(new Dimension(150,50));

                            JButton submit = new JButton("Submit");
                            submit.setFocusable(false);
                            submit.setPreferredSize(new Dimension(200,50));
                            submit.setBackground(new Color(0xd34234));
                            submit.setForeground(Color.white);

                            submit.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String name;int frequency; double issuePrice;
                                    name = textField1.getText();
                                    frequency = Integer.parseInt(textField2.getText());
                                    issuePrice = Double.parseDouble(textField3.getText());
                                    Journal j = new Journal(frequency,issuePrice,name);
                                    if (j != null){
                                        if(distributor.addJournal(j)){
                                            JOptionPane.showMessageDialog(
                                                    null, "Journal Successfully added! ISSN:"+j.getIssn(),
                                                    "Information",JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }
                                    frame.setEnabled(false);
                                    frame.setVisible(false);
                                }
                            });

                            frame.add(textField1);
                            frame.add(textField2);
                            frame.add(textField3);
                            frame.add(submit);
                            frame.pack();
                        }
                    });

                    //--------------------- SEARCH JOURNAL BUTTON -----------------------------
                    JButton searchJournal = new JButton("Search Journal");
                    searchJournal.setBackground(new Color(0xd34234));
                    searchJournal.setForeground(new Color(0xf0c419));
                    searchJournal.setFont(new Font("Maiandra GD", Font.BOLD, 26));
                    searchJournal.setHorizontalTextPosition(SwingConstants.CENTER);
                    searchJournal.setFocusable(false);
                    searchJournal.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
                    journalPanel.add(searchJournal);
                    searchJournal.setBounds(350, 400, 250, 50);
                    searchJournal.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name; int frequency; double issuePrice;
                            JFrame frame = new JFrame();
                            frame.setLayout(new FlowLayout());
                            frame.setSize(240,150);
                            frame.setPreferredSize(new Dimension(240,150));
                            frame.setVisible(true);
                            frame.getContentPane().setBackground(Color.orange);
                            frame.setLocationRelativeTo(null);

                            JTextField textField1 = new JTextField("Enter an ISSN");
                            textField1.setEditable(true);
                            textField1.setBackground(Color.white);
                            textField1.setForeground(Color.black);
                            textField1.setOpaque(true);
                            textField1.setPreferredSize(new Dimension(200,50));


                            JButton submit = new JButton("Submit");
                            submit.setFocusable(false);
                            submit.setPreferredSize(new Dimension(200,50));
                            submit.setBackground(new Color(0xd34234));
                            submit.setForeground(Color.white);

                            submit.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String issn;
                                    issn = textField1.getText();
                                    Journal j =distributor.searchJournal(issn);
                                    if ( j == null)
                                        JOptionPane.showMessageDialog(
                                                null, "Journal Couldn't Be Found!",
                                                "Warning",JOptionPane.WARNING_MESSAGE);
                                    else
                                        JOptionPane.showMessageDialog(
                                                null,j.toString(),
                                                "Journal",JOptionPane.INFORMATION_MESSAGE);
                                    frame.setEnabled(false);
                                    frame.setVisible(false);
                                }
                            });

                            frame.add(textField1);
                            frame.add(submit);
                            frame.pack();


                        }
                    });


        }
        //--------------- Subscription Menu ------------------------------------------
        {
            subsPanel = new JPanel();
            subsPanel.setBackground(new Color(0xf0c419));
            subsPanel.setBounds(0,0,1080,640);
            subsPanel.setPreferredSize(new Dimension(1080, 640));
            subsPanel.setLayout(null);
            subsPanel.setVisible(false);
            subsPanel.setEnabled(true);
            subsPanel.setLayout(null);
            //---------- Subscription Menu LABEL -----------------------------------
            subsLabel = new JLabel();
            subsLabel.setVisible(true);
            subsLabel = new JLabel();
            subsLabel.setText("Subscription Menu");
            subsLabel.setFont(new Font("Consolas", Font.BOLD, 48));
            subsLabel.setForeground(new Color(0xd34234));
            subsLabel.setIcon(new ImageIcon("subs256.png"));
            subsLabel.setIconTextGap(100);
            subsLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            subsLabel.setBounds(-100, 25, 1080, 300);
            subsPanel.add(subsLabel);
            // -------------------- BACK TO MAIN BUTTON -------------------------
            JButton backToMain = new JButton("Main Menu");
            backToMain.setBackground(new Color(0xd34234));
            backToMain.setForeground(new Color(0xf0c419));
            backToMain.setFont(new Font("Maiandra GD", Font.BOLD, 24));
            backToMain.setHorizontalTextPosition(SwingConstants.CENTER);
            backToMain.setFocusable(false);
            backToMain.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            subsPanel.add(backToMain);
            backToMain.setBounds(810, 400, 200, 50);
            backToMain.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    subsPanel.setEnabled(false);
                    subsPanel.setVisible(false);
                    panel1.setEnabled(true);
                    panel1.setVisible(true);
                }
            });

            //--------------------- ADD SUBSCRIBER BUTTON -----------------------------
            JButton addSubscriber = new JButton("Add Subscriber");
            addSubscriber.setBackground(new Color(0xd34234));
            addSubscriber.setForeground(new Color(0xf0c419));
            addSubscriber.setFont(new Font("Maiandra GD", Font.BOLD, 24));
            addSubscriber.setHorizontalTextPosition(SwingConstants.CENTER);
            addSubscriber.setFocusable(false);
            addSubscriber.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            subsPanel.add(addSubscriber);
            addSubscriber.setBounds(150, 400, 250, 50);
            addSubscriber.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    final boolean[] isIndividual = new boolean[1];

                    JFrame frame = new JFrame();
                    frame.setLayout(new FlowLayout());
                    frame.setSize(760,200);
                    frame.setPreferredSize(new Dimension(760,200));
                    frame.setVisible(true);
                    frame.getContentPane().setBackground(Color.orange);
                    frame.setLocationRelativeTo(null);

                    JButton button1 = new JButton("Individual");
                    button1.setFocusable(false);
                    button1.setVisible(true);
                    button1.setPreferredSize(new Dimension(350,75));
                    button1.setVerticalTextPosition(SwingConstants.CENTER);
                    button1.setBackground(new Color(0xd34234));
                    button1.setForeground(Color.white);

                    JButton button2 = new JButton("Corporation");
                    button2.setVisible(true);
                    button2.setVerticalTextPosition(SwingConstants.CENTER);
                    button2.setFocusable(false);
                    button2.setPreferredSize(new Dimension(350,75));
                    button2.setBackground(new Color(0xd34234));
                    button2.setForeground(Color.white);

                    JTextField textField1 = new JTextField("Enter name");
                    textField1.setEditable(true);
                    textField1.setBackground(Color.white);
                    textField1.setForeground(Color.black);
                    textField1.setOpaque(true);
                    textField1.setPreferredSize(new Dimension(100,50));

                    JTextField textField2 = new JTextField("Enter Address");
                    textField2.setEditable(true);
                    textField2.setBackground(Color.WHITE);
                    textField2.setForeground(Color.BLACK);
                    textField2.setOpaque(true);
                    textField2.setPreferredSize(new Dimension(170,50));

                    JTextField textField3 = new JTextField("Credit Card No");
                    textField3.setEditable(true);
                    textField3.setBackground(Color.white);
                    textField3.setForeground(Color.BLACK);
                    textField3.setOpaque(true);
                    textField3.setPreferredSize(new Dimension(170,50));

                    JTextField textField4 = new JTextField("Expire Date 'mm/yy'");
                    textField4.setEditable(true);
                    textField4.setBackground(Color.white);
                    textField4.setForeground(Color.black);
                    textField4.setOpaque(true);
                    textField4.setPreferredSize(new Dimension(170,50));

                    JTextField textField5 = new JTextField("CCV");
                    textField5.setEditable(true);
                    textField5.setBackground(Color.white);
                    textField5.setForeground(Color.black);
                    textField5.setOpaque(true);
                    textField5.setPreferredSize(new Dimension(50,50));

                    JTextField textField6 = new JTextField("Bank Code");
                    textField6.setEditable(true);
                    textField6.setBackground(Color.white);
                    textField6.setForeground(Color.BLACK);
                    textField6.setOpaque(true);
                    textField6.setPreferredSize(new Dimension(80,50));

                    JTextField textField7 = new JTextField("Bank Name");
                    textField7.setEditable(true);
                    textField7.setBackground(Color.white);
                    textField7.setForeground(Color.BLACK);
                    textField7.setOpaque(true);
                    textField7.setPreferredSize(new Dimension(80,50));

                    JTextField textField8 = new JTextField("Issue Date 'dd/mm/yy'");
                    textField8.setEditable(true);
                    textField8.setBackground(Color.white);
                    textField8.setForeground(Color.BLACK);
                    textField8.setOpaque(true);
                    textField8.setPreferredSize(new Dimension(140,50));

                    JTextField textField9 = new JTextField("Account Number");
                    textField9.setEditable(true);
                    textField9.setBackground(Color.white);
                    textField9.setForeground(Color.BLACK);
                    textField9.setOpaque(true);
                    textField9.setPreferredSize(new Dimension(80,50));

                    JButton submit = new JButton("Submit");
                    submit.setFocusable(false);
                    submit.setPreferredSize(new Dimension(350,50));
                    submit.setBackground(new Color(0xd34234));
                    submit.setForeground(Color.white);
                    button1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            isIndividual[0] = true;
                            frame.add(textField1);
                            frame.add(textField2);
                            frame.add(textField3);
                            frame.add(textField4);
                            frame.add(textField5);
                            frame.add(submit);
                            button2.setEnabled(false);
                            button1.setEnabled(false);
                            button2.setVisible(false);
                            button1.setVisible(false);
                        }
                    });
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            button2.setEnabled(false);
                            button1.setEnabled(false);
                            button2.setVisible(false);
                            button1.setVisible(false);
                            isIndividual[0] = false;
                            frame.add(textField1);
                            frame.add(textField2);
                            frame.add(textField6);
                            frame.add(textField7);
                            frame.add(textField8);
                            frame.add(textField9);
                            frame.add(submit);
                        }
                    });
                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name; String address;
                            if (isIndividual[0]) {
                                String creditCardNo = textField3.getText().replaceAll(" ","");
                                String[] expire = new String[2];
                                expire = textField4.getText().replaceAll("[A-z]","").replaceAll(" ","").split("/");
                                int CCV = Integer.parseInt(textField5.getText());
                                name = textField1.getText().trim();
                                address = textField2.getText().trim();
                                Individual individual = new Individual(name,address,creditCardNo,Integer.parseInt(expire[0]),Integer.parseInt(expire[1]),CCV);
                                if (distributor.addSubscriber(individual))
                                    JOptionPane.showMessageDialog(
                                            null, "Individual added Successfully!",
                                            "Success",JOptionPane.INFORMATION_MESSAGE);
                                else
                                    JOptionPane.showMessageDialog(
                                        null, "Individual Couldn't be added!",
                                        "Warning",JOptionPane.WARNING_MESSAGE);
                            }
                            else {
                                name = textField1.getText().trim();
                                address = textField2.getText().trim();
                                int bankCode = Integer.parseInt(textField6.getText());
                                String bankName = textField7.getText().trim();
                                String[] issue = new String[3];
                                issue = textField8.getText().replaceAll("[A-z]","").replaceAll(" ","").split("/");
                                int accountNumber = Integer.parseInt(textField9.getText());
                                Corporation corporation = new Corporation(name,address,bankName,bankCode,
                                        Integer.parseInt(issue[0]),Integer.parseInt(issue[1]),Integer.parseInt(issue[2]),
                                        accountNumber);
                                if (distributor.addSubscriber(corporation))
                                    JOptionPane.showMessageDialog(
                                            null, "Corporation added Successfully!",
                                            "Success",JOptionPane.INFORMATION_MESSAGE);
                                else
                                    JOptionPane.showMessageDialog(
                                            null, "Corporation Couldn't be added!",
                                            "Warning",JOptionPane.WARNING_MESSAGE);

                            }
                            frame.setEnabled(false);
                            frame.setVisible(false);
                        }
                    });
                    frame.add(button1);
                    frame.add(button2);
                    frame.pack();
                }
            });

            //--------------------- LIST SUBSRCIPTIONS BUTTON -----------------------------
            JButton listSubscriptions = new JButton("List Subscriptions");
            listSubscriptions.setBackground(new Color(0xd34234));
            listSubscriptions.setForeground(new Color(0xf0c419));
            listSubscriptions.setFont(new Font("Maiandra GD", Font.BOLD, 24));
            listSubscriptions.setHorizontalTextPosition(SwingConstants.CENTER);
            listSubscriptions.setFocusable(false);
            listSubscriptions.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            subsPanel.add(listSubscriptions);
            listSubscriptions.setBounds(150, 470, 250, 50);
            listSubscriptions.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name; int frequency; double issuePrice;
                    JFrame frame = new JFrame();
                    frame.setLayout(new FlowLayout());
                    frame.setSize(240,150);
                    frame.setPreferredSize(new Dimension(240,150));
                    frame.setVisible(true);
                    frame.getContentPane().setBackground(Color.orange);
                    frame.setLocationRelativeTo(null);

                    JTextField textField1 = new JTextField("Enter an ISSN or a Subscriber Name");
                    textField1.setEditable(true);
                    textField1.setBackground(Color.white);
                    textField1.setForeground(Color.black);
                    textField1.setOpaque(true);
                    textField1.setPreferredSize(new Dimension(200,50));


                    JButton submit = new JButton("Submit");
                    submit.setFocusable(false);
                    submit.setPreferredSize(new Dimension(200,50));
                    submit.setBackground(new Color(0xd34234));
                    submit.setForeground(Color.white);

                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String string;
                            string = textField1.getText().trim();
                            String output = distributor.listSubscriptions(string);
                            if ( output == null )
                                JOptionPane.showMessageDialog(
                                        null, "Subscription Couldn't Found!",
                                        "Warning",JOptionPane.WARNING_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(
                                        null,output,
                                        "Subscriptions",JOptionPane.INFORMATION_MESSAGE);
                            frame.setEnabled(false);
                            frame.setVisible(false);
                        }
                    });

                    frame.add(textField1);
                    frame.add(submit);
                    frame.pack();


                }
            });


            //--------------------- SEARCH SUBSCRIBER BUTTON -----------------------------
            JButton searchSubscriber = new JButton("Search Subscriber");
            searchSubscriber.setBackground(new Color(0xd34234));
            searchSubscriber.setForeground(new Color(0xf0c419));
            searchSubscriber.setFont(new Font("Maiandra GD", Font.BOLD, 24));
            searchSubscriber.setHorizontalTextPosition(SwingConstants.CENTER);
            searchSubscriber.setFocusable(false);
            searchSubscriber.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            subsPanel.add(searchSubscriber);
            searchSubscriber.setBounds(450, 400, 250, 50);
            searchSubscriber.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    JFrame frame = new JFrame();
                    frame.setLayout(new FlowLayout());
                    frame.setSize(240,150);
                    frame.setPreferredSize(new Dimension(240,150));
                    frame.setVisible(true);
                    frame.getContentPane().setBackground(Color.orange);
                    frame.setLocationRelativeTo(null);

                    JTextField textField1 = new JTextField("Enter name");
                    textField1.setEditable(true);
                    textField1.setBackground(Color.white);
                    textField1.setForeground(Color.black);
                    textField1.setOpaque(true);
                    textField1.setPreferredSize(new Dimension(200,50));


                    JButton submit = new JButton("Submit");
                    submit.setFocusable(false);
                    submit.setPreferredSize(new Dimension(200,50));
                    submit.setBackground(new Color(0xd34234));
                    submit.setForeground(Color.white);

                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String name;
                            name = textField1.getText();
                            Subscriber s =distributor.searchSubscriber(name);
                            if ( s == null)
                                JOptionPane.showMessageDialog(
                                        null, "Subscriber Couldn't Be Found!",
                                        "Warning",JOptionPane.WARNING_MESSAGE);
                            else
                                JOptionPane.showMessageDialog(
                                        null,s.toString() + "\n" + s.getBillingInformation(),
                                        "Subscriber",JOptionPane.INFORMATION_MESSAGE);
                            frame.setEnabled(false);
                            frame.setVisible(false);
                        }
                    });

                    frame.add(textField1);
                    frame.add(submit);
                    frame.pack();


                }
            });

            //--------------------- ADD SUBSCRIPTION BUTTON -----------------------------
            JButton addSubscription = new JButton("Add Subscription");
            addSubscription.setBackground(new Color(0xd34234));
            addSubscription.setForeground(new Color(0xf0c419));
            addSubscription.setFont(new Font("Maiandra GD", Font.BOLD, 24));
            addSubscription.setHorizontalTextPosition(SwingConstants.CENTER);
            addSubscription.setFocusable(false);
            addSubscription.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            subsPanel.add(addSubscription);
            addSubscription.setBounds(450, 470, 250, 50);
            addSubscription.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name; int frequency; double issuePrice;
                    JFrame frame = new JFrame();
                    frame.setLayout(new FlowLayout());
                    frame.setSize(640,200);
                    frame.setPreferredSize(new Dimension(640,200));
                    frame.setVisible(true);
                    frame.getContentPane().setBackground(Color.orange);
                    frame.setLocationRelativeTo(null);

                    JTextField textField1 = new JTextField("Journal Issn");
                    textField1.setEditable(true);
                    textField1.setBackground(Color.white);
                    textField1.setForeground(Color.black);
                    textField1.setOpaque(true);
                    textField1.setPreferredSize(new Dimension(140,50));

                    JTextField textField2 = new JTextField("Subscriber Name");
                    textField2.setEditable(true);
                    textField2.setBackground(Color.white);
                    textField2.setForeground(Color.black);
                    textField2.setOpaque(true);
                    textField2.setPreferredSize(new Dimension(140,50));

                    JTextField textField3 = new JTextField("Date Info dd/mm/yy");
                    textField3.setEditable(true);
                    textField3.setBackground(Color.white);
                    textField3.setForeground(Color.black);
                    textField3.setOpaque(true);
                    textField3.setPreferredSize(new Dimension(140,50));

                    JTextField textField4 = new JTextField("Copies");
                    textField4.setEditable(true);
                    textField4.setBackground(Color.white);
                    textField4.setForeground(Color.black);
                    textField4.setOpaque(true);
                    textField4.setPreferredSize(new Dimension(140,50));


                    JButton submit = new JButton("Submit");
                    submit.setFocusable(false);
                    submit.setPreferredSize(new Dimension(200,50));
                    submit.setBackground(new Color(0xd34234));
                    submit.setForeground(Color.white);

                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String issn = textField1.getText().trim();
                            Journal j = distributor.searchJournal(issn);
                            if (j == null){
                                JOptionPane.showMessageDialog(
                                        null,"Couldn't find the Journal!",
                                        "Journal",JOptionPane.WARNING_MESSAGE);
                                frame.setEnabled(false);
                                frame.setVisible(false);
                            }
                            String name = textField2.getText().trim();
                            Subscriber s = distributor.searchSubscriber(name);
                            if (s == null){
                                JOptionPane.showMessageDialog(
                                        null,"Couldn't find the Subscriber!",
                                        "Subscriber",JOptionPane.WARNING_MESSAGE);
                                frame.setEnabled(false);
                                frame.setVisible(false);
                            }
                            String[] date = new String[3];
                            date = textField3.getText().trim().replaceAll("[A-z]","").replaceAll(" ","").split("/");
                            int copies = Integer.parseInt(textField4.getText());
                            Subscription subscription = new Subscription(
                                    new DateInfo(Integer.parseInt(date[1]),Integer.parseInt(date[2])),copies,j,s);
                            if ( distributor.addSubscription(issn,s,subscription) ){
                                JOptionPane.showMessageDialog(
                                        null,"Subscriber and Journal Connected.\n" +
                                                "Subcription Successfull!",
                                        "Subscription",JOptionPane.INFORMATION_MESSAGE);
                            }
                            else {
                                JOptionPane.showMessageDialog(
                                        null,"Subscriber and Journal Couldn't Connected.\n" +
                                                "Subcription Failed!",
                                        "Subscription",JOptionPane.WARNING_MESSAGE);
                            }
                            frame.setEnabled(false);
                            frame.setVisible(false);
                        }
                    });
                    frame.add(textField1);
                    frame.add(textField2);
                    frame.add(textField3);
                    frame.add(textField4);
                    frame.add(submit);
                    frame.pack();


                }
            });

        }
        //------------------ Payment MENU ---------------------------
        {
            paymentPanel = new JPanel();
            paymentPanel.setBackground(new Color(0xf0c419));
            paymentPanel.setBounds(0,0,1080,640);
            paymentPanel.setPreferredSize(new Dimension(1080, 640));
            paymentPanel.setLayout(null);
            paymentPanel.setVisible(false);
            paymentPanel.setEnabled(true);
            paymentPanel.setLayout(null);
            //---------- Payment Menu LABEL -----------------------------------
            paymentLabel = new JLabel();
            paymentLabel.setVisible(true);
            paymentLabel = new JLabel();
            paymentLabel.setText("Payment Menu");
            paymentLabel.setFont(new Font("Consolas", Font.BOLD, 48));
            paymentLabel.setForeground(new Color(0xd34234));
            paymentLabel.setIcon(new ImageIcon("payment_logo.png"));
            paymentLabel.setIconTextGap(100);
            paymentLabel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            paymentLabel.setBounds(-100, 25, 1080, 350);
            // -------------------- BACK TO MAIN BUTTON -------------------------
            JButton backToMain = new JButton("Main Menu");
            backToMain.setBackground(new Color(0xd34234));
            backToMain.setForeground(new Color(0xf0c419));
            backToMain.setFont(new Font("Maiandra GD", Font.BOLD, 24));
            backToMain.setHorizontalTextPosition(SwingConstants.CENTER);
            backToMain.setFocusable(false);
            backToMain.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            paymentPanel.add(backToMain);
            backToMain.setBounds(810, 400, 200, 50);
            backToMain.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    paymentPanel.setEnabled(false);
                    paymentPanel.setVisible(false);
                    panel1.setEnabled(true);
                    panel1.setVisible(true);
                }
            });
            //--------------------- LIST SENDING ORDERS BUTTON -----------------------------
            JButton listOrders = new JButton("Sending Orders");
            listOrders.setBackground(new Color(0xd34234));
            listOrders.setForeground(new Color(0xf0c419));
            listOrders.setFont(new Font("Maiandra GD", Font.BOLD, 24));
            listOrders.setHorizontalTextPosition(SwingConstants.CENTER);
            listOrders.setFocusable(false);
            listOrders.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
            paymentPanel.add(listOrders);
            listOrders.setBounds(150, 400, 250, 50);
            listOrders.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    final boolean[] isAll = new boolean[1];

                    JFrame frame = new JFrame();
                    frame.setLayout(new FlowLayout());
                    frame.setSize(760,200);
                    frame.setPreferredSize(new Dimension(760,200));
                    frame.setVisible(true);
                    frame.getContentPane().setBackground(Color.orange);
                    frame.setLocationRelativeTo(null);

                    JButton button1 = new JButton("List Every Journal");
                    button1.setFocusable(false);
                    button1.setVisible(true);
                    button1.setPreferredSize(new Dimension(350,75));
                    button1.setVerticalTextPosition(SwingConstants.CENTER);
                    button1.setBackground(new Color(0xd34234));
                    button1.setForeground(Color.white);

                    JButton button2 = new JButton("List Spesific Journal");
                    button2.setVisible(true);
                    button2.setVerticalTextPosition(SwingConstants.CENTER);
                    button2.setFocusable(false);
                    button2.setPreferredSize(new Dimension(350,75));
                    button2.setBackground(new Color(0xd34234));
                    button2.setForeground(Color.white);

                    JTextField textField1 = new JTextField("Enter a date 'mm/yy'");
                    textField1.setEditable(true);
                    textField1.setBackground(Color.white);
                    textField1.setForeground(Color.black);
                    textField1.setOpaque(true);
                    textField1.setPreferredSize(new Dimension(200,50));

                    JTextField textField2 = new JTextField("Issn");
                    textField2.setEditable(true);
                    textField2.setBackground(Color.WHITE);
                    textField2.setForeground(Color.BLACK);
                    textField2.setOpaque(true);
                    textField2.setPreferredSize(new Dimension(150,50));



                    JButton submit = new JButton("Submit");
                    submit.setFocusable(false);
                    submit.setPreferredSize(new Dimension(350,50));
                    submit.setBackground(new Color(0xd34234));
                    submit.setForeground(Color.white);
                    button1.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            isAll[0] = true;
                            frame.add(textField1);
                            frame.add(submit);
                            button2.setEnabled(false);
                            button1.setEnabled(false);
                            button2.setVisible(false);
                            button1.setVisible(false);

                        }
                    });
                    button2.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            button2.setEnabled(false);
                            button1.setEnabled(false);
                            button2.setVisible(false);
                            button1.setVisible(false);
                            isAll[0] = false;
                            frame.add(textField1);
                            frame.add(textField2);
                            frame.add(submit);
                        }
                    });
                    submit.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String[] date = new String[2];String issn;
                            if (isAll[0]) {
                                date = textField1.getText().trim().replaceAll("[A-z]","").replaceAll(" ","").split("/");
                                String o = String.valueOf(distributor.listAllSendingOrders(Integer.parseInt(date[0]), Integer.parseInt(date[1])));
                                JOptionPane.showMessageDialog(
                                            null, o,
                                            "Information",JOptionPane.INFORMATION_MESSAGE);

                            }
                            else {
                                date = textField1.getText().trim().replaceAll("[A-z]","").replaceAll(" ","").split("/");
                                issn = textField2.getText().trim();
                                String o = distributor.listSendingOrders(issn,Integer.parseInt(date[0]), Integer.parseInt(date[1]));
                                JOptionPane.showMessageDialog(
                                        null, o,
                                        "Information",JOptionPane.INFORMATION_MESSAGE);

                            }
                            frame.setEnabled(false);
                            frame.setVisible(false);
                        }
                    });
                    frame.add(button1);
                    frame.add(button2);
                    frame.pack();
                }
            });



            paymentPanel.add(paymentLabel);
        }
        //--------------------- LIST INCOMPELETE PAYMENTS BUTTON -----------------------------
        JButton listIncomplete = new JButton("List Incomplete Payments");
        listIncomplete.setBackground(new Color(0xd34234));
        listIncomplete.setForeground(new Color(0xf0c419));
        listIncomplete.setFont(new Font("Maiandra GD", Font.BOLD, 24));
        listIncomplete.setHorizontalTextPosition(SwingConstants.CENTER);
        listIncomplete.setFocusable(false);
        listIncomplete.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        paymentPanel.add(listIncomplete);
        listIncomplete.setBounds(225, 470, 320, 50);
        listIncomplete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String output=null;
                output = distributor.listIncompletePayments();
                if(output != null)
                        JOptionPane.showMessageDialog(
                        null, output,
                        "Information",JOptionPane.INFORMATION_MESSAGE);
                else JOptionPane.showMessageDialog(
                        null, "No Data Found!",
                        "Warning",JOptionPane.WARNING_MESSAGE);
            }
        });

        //--------------------- ACCEPT PAYMENT BUTTON -----------------------------
        JButton acceptPayment = new JButton("Accept Payment");
        acceptPayment.setBackground(new Color(0xd34234));
        acceptPayment.setForeground(new Color(0xf0c419));
        acceptPayment.setFont(new Font("Maiandra GD", Font.BOLD, 24));
        acceptPayment.setHorizontalTextPosition(SwingConstants.CENTER);
        acceptPayment.setFocusable(false);
        acceptPayment.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        paymentPanel.add(acceptPayment);
        acceptPayment.setBounds(450, 400, 250, 50);
        acceptPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame();
                frame.setLayout(new FlowLayout());
                frame.setSize(240,150);
                frame.setPreferredSize(new Dimension(640,150));
                frame.setVisible(true);
                frame.getContentPane().setBackground(Color.orange);
                frame.setLocationRelativeTo(null);

                JTextField textField1 = new JTextField("Subscriber Name");
                textField1.setEditable(true);
                textField1.setBackground(Color.white);
                textField1.setForeground(Color.black);
                textField1.setOpaque(true);
                textField1.setPreferredSize(new Dimension(200,50));

                JTextField textField2 = new JTextField("ISSN");
                textField2.setEditable(true);
                textField2.setBackground(Color.white);
                textField2.setForeground(Color.black);
                textField2.setOpaque(true);
                textField2.setPreferredSize(new Dimension(100,50));


                JTextField textField3 = new JTextField("Payment Amount");
                textField3.setEditable(true);
                textField3.setBackground(Color.white);
                textField3.setForeground(Color.black);
                textField3.setOpaque(true);
                textField3.setPreferredSize(new Dimension(200,50));


                JButton submit = new JButton("Submit");
                submit.setFocusable(false);
                submit.setPreferredSize(new Dimension(200,50));
                submit.setBackground(new Color(0xd34234));
                submit.setForeground(Color.white);

                submit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name; double payment; String issn;
                        name = textField1.getText();
                        Subscriber s =distributor.searchSubscriber(name);
                        issn = textField2.getText().trim();
                        Journal j = distributor.searchJournal(issn);
                        double amount = Double.parseDouble(textField3.getText());
                        if( s != null && j !=null){
                            for (Subscription each:j.getSubscriptions()){
                                if (each.getSubscriber().equals(s)){
                                    each.acceptPayment(amount);
                                    JOptionPane.showMessageDialog(
                                            null, "Payment Accepted!",
                                            "Information",JOptionPane.INFORMATION_MESSAGE);
                                    break;
                                }
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(
                                    null, "Subscriber/Journal Couldn't Be Found!",
                                    "Warning",JOptionPane.WARNING_MESSAGE);
                        }




                        frame.setEnabled(false);
                        frame.setVisible(false);
                    }
                });
                frame.add(textField1);
                frame.add(textField2);
                frame.add(textField3);
                frame.add(submit);
                frame.pack();


            }
        });


        // ----------- ADDING TO MAIN MENU ---------------------
        mainFrame.add(journalPanel);
        mainFrame.add(subsPanel);
        mainFrame.add(paymentPanel);
        mainFrame.add(panel1);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.pack();

    }

    public static void main(String[] args) {
        new DistributorGUI();
    }







}
