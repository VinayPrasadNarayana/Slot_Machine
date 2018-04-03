/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;       // Using AWT's layouts
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;// Using AWT's event classes and listener interfaces
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;    // Using Swing components and containers
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author vinay
 */
public class SlotMachine extends JFrame {

    int betcount = 0;
    private Lock balanceLock;
    int win;
    int lose;
    int Credits = 0;
    double Average;
    JButton AddCoin;
    JButton BetOne;
    JButton BetMax;
    JButton Reset;
    JButton Spin;
    JButton Statistic;
    JLabel Image1;
    JLabel Image2;
    JLabel Image3;
    JLabel Text1;
    JLabel Text2;
    Point imgPoint;
    JTextField CreditArea;
    JTextField BetArea;
    Reel Slot, Slot1, Slot2, Slot3;
    int Count = 10;
    int CountBet = 0;
    JFrame frame;
    int value1 = 0;
    int value2 = 0;
    int value3 = 0;
    int ClickCount = 0;
    private volatile boolean exit = false;
    private volatile boolean exit1 = false;
    private volatile boolean exit2 = false;
    private volatile boolean suspend = false;
    private volatile boolean suspend1 = false;
    private volatile boolean suspend2 = false;
    Slot1 t = new Slot1();
    Slot2 t1 = new Slot2();
    Slot3 t2 = new Slot3();

    public SlotMachine() {
        Container Reel = getContentPane();
        Reel.setLayout(new BorderLayout());

        JPanel p1 = new JPanel(new GridLayout(1, 1, 1, 1));
        Reel.add(p1, BorderLayout.CENTER);

        Image1 = new JLabel("", JLabel.RIGHT);
        p1.add(Image1, BorderLayout.WEST);

        Image2 = new JLabel("", JLabel.CENTER);

        p1.add(Image2, BorderLayout.CENTER);

        Image3 = new JLabel("", JLabel.LEFT);
        p1.add(Image3, BorderLayout.EAST);

        Slot = new Reel();
        Symbol Initial = Slot.Spin();
        Image1.setIcon(Initial.getImage());
        Image2.setIcon(Initial.getImage());
        Image3.setIcon(Initial.getImage());

        JPanel p2 = new JPanel(new GridLayout(1, 1, 1, 1));

        Reel.add(p2, BorderLayout.NORTH);
        Reset = new JButton("Reset");
        p2.add(Reset);

        Text1 = new JLabel("Credit: ", JLabel.RIGHT);
        p2.add(Text1);
        CreditArea = new JTextField("", 5);
        CreditArea.setText(Count + "");
        CreditArea.setEditable(false);
        p2.add(CreditArea);

        Text2 = new JLabel("Bet: ", JLabel.RIGHT);
        p2.add(Text2);
        BetArea = new JTextField("", 5);
        BetArea.setText(CountBet + "");
        BetArea.setEditable(false);
        p2.add(BetArea);

        AddCoin = new JButton("Add Coin");
        p2.add(AddCoin);
        BetOne = new JButton("Bet One");
        p2.add(BetOne);
        BetMax = new JButton("Bet Max");
        p2.add(BetMax);

        JPanel p3 = new JPanel(new GridLayout(2, 1));
        p3.setSize(300, 300);

        Reel.add(p3, BorderLayout.SOUTH);
        Spin = new JButton("SPIN");
        p3.add(Spin);
        Statistic = new JButton("Statistic");
        p3.add(Statistic);
        JPanel Panel = new JPanel(new GridLayout());

        MyListener myListener = new MyListener();
        BetOne.addActionListener(myListener);
        BetMax.addActionListener(myListener);
        Reset.addActionListener(myListener);
        Spin.addActionListener(myListener);
        AddCoin.addActionListener(myListener);
        Statistic.addActionListener(myListener);
        MouseHandler handler = new MouseHandler();
        Image1.addMouseListener(handler);
        Image2.addMouseListener(handler);
        Image3.addMouseListener(handler);

    }

    public class Slot1 extends Thread {

        @Override
        public void run() {

            try {
                while (!exit) {

                    Slot1 = new Reel();
                    Symbol Initial1 = Slot1.Spin();
                    Image1.setIcon(Initial1.getImage());
                    value1 = Initial1.getValue();
                    System.out.println("VALUE: " + value1);
                    synchronized (this) {
                        while (suspend) {
                            wait();
                        }
                    }

                }
            } catch (InterruptedException e) {
                System.out.println("My thread is in terrupted");
            }
            System.out.println("My thread exiting.");
        }

        //stop the thread
        synchronized void mystop() {
            exit = true;
            // the following ensure that a thread that was suspended is stopped
            suspend = false;
            notify();
        }

        //suspended the thread
        synchronized void mysuspend() {
            suspend = true;
        }

        // resume
        synchronized void myresume() {
            suspend = false;
            notify();
        }

    }

    public class Slot2 extends Thread {

        public void run() {

            try {
                while (!exit1) {

                    Slot2 = new Reel();
                    Symbol Initial2 = Slot2.Spin();
                    Image2.setIcon(Initial2.getImage());
                    value2 = Initial2.getValue();
                    System.out.println("VALUE1: " + value2);
                    synchronized (this) {
                        while (suspend1) {
                            wait();
                        }
                    }

                }
            } catch (InterruptedException e) {
                System.out.println("My thread2 is in terrupted");
            }
            System.out.println("My thread2 exiting.");
        }

        //stop the thread
        synchronized void mystop() {
            exit1 = true;
            // the following ensure that a thread that was suspended is stopped
            suspend1 = false;
            notify();
        }

        //suspended the thread
        synchronized void mysuspend1() {
            suspend1 = true;
        }

        // resume
        synchronized void myresume1() {
            suspend1 = false;
            notify();
        }

    }

    public class Slot3 extends Thread {

        @Override
        public void run() {

            try {
                while (!exit2) {

                    Slot3 = new Reel();
                    Symbol Initial3 = Slot3.Spin();
                    Image3.setIcon(Initial3.getImage());

                    value3 = Initial3.getValue();
                    System.out.println("VALUE2: " + value3);
                    synchronized (this) {
                        while (suspend2) {
                            wait();
                        }
                    }

                }
            } catch (InterruptedException e) {
                System.out.println("My thread3 is in terrupted");
            }
            System.out.println("My thread3 exiting.");
        }

        //suspended the thread
        synchronized void mysuspend2() {
//            Image3.setIcon(value3.getImage());
            suspend2 = true;
        }

        // resume
        synchronized void myresume2() {
            suspend2 = false;
            notify();
        }

    }

    private class MyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            String Click = evt.getActionCommand();
            if (Click.equals("Add Coin")) {
                Count++;
                CreditArea.setText(Count + "");
            }

            if (Click.equals("Bet One")) {
                if (Count > 0) {
                    Count--;
                    CountBet++;
                    CreditArea.setText(Count + "");
                    BetArea.setText(CountBet + "");
                } else {
                    JOptionPane.showMessageDialog(frame, "Not Enough Credit");
                }
            }

            if (Click.equals("Bet Max")) {

                if (betcount < 1) {
                    if (Count > 2) {
                        CountBet = CountBet + 3;
                        Count = Count - 3;
                        CreditArea.setText(Count + "");
                        BetArea.setText(CountBet + "");
                        betcount++;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Not Enough Credit");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "BetMax can be used once per spin");
                }
            }

            if (Click.equals("Reset")) {
                Count = Count + CountBet;
                CountBet = 0;
                CreditArea.setText(Count + "");
                BetArea.setText(CountBet + "");
                betcount = 0;
            }

            if (Click.equals("SPIN")) {
                if (betcount > 0) {
                    betcount = 0;
                }
                if ((Count > 0) || (CountBet > 0)) {
                    if ((CountBet > 0)) {
                        if (suspend == true) {
                            t.myresume();
                            t1.myresume1();
                            t2.myresume2();
                            if (ClickCount > 0) {
                                ClickCount = 0;
                            }
                        } else {
                            t.start();
                            t1.start();
                            t2.start();

                            if (ClickCount > 0) {
                                ClickCount = 0;
                            }
                        }
                    } else if ((CountBet < 1)) {
                        JOptionPane.showMessageDialog(frame, "Cannot Spin! Not Enough Bet Credit");
                    }
                } else if ((Count < 1)) {
                    JOptionPane.showMessageDialog(frame, "Cannot Spin! Not Enough Credit");
                }
            }
            JPanel panel = new JPanel();
            JProgressBar progressBar1;
            JProgressBar progressBar2;
            JLabel Win;
            JLabel avg;
            JLabel Lose;
            JTextField average;
            Win = new JLabel("Win");
            Lose = new JLabel("Lose");
            avg = new JLabel("Average Credits Won: ");
            average = new JTextField(Average + "", 5);
            average.setEditable(false);
            progressBar1 = new JProgressBar(0, 150);
            progressBar2 = new JProgressBar(0, 150);
            progressBar1.setValue(win);
            progressBar2.setValue(lose);
            progressBar1.setStringPainted(true);
            progressBar1.setForeground(Color.blue);
            progressBar2.setStringPainted(true);
            progressBar2.setForeground(Color.red);
            panel.add(avg);
            panel.add(average);
            panel.add(Win);
            panel.add(progressBar1);
            panel.add(Lose);
            panel.add(progressBar2);
            int selected;
            if (Click.equals("Statistic")) {

                Object[] options = {avg, average, Win, progressBar1, Lose, progressBar2, "Save", "Cancel"};

                selected = JOptionPane.showOptionDialog(
                        panel,
                        "The Statical Part",
                        "Statistics",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null,
                        options,
                        options[1]);
                if (selected == 6) {
                    inputStudent(Average, win, lose);

                }

            }
        }

    }

    private class MouseHandler implements MouseListener, MouseMotionListener {

        @Override
        public void mouseClicked(MouseEvent me) {

        }

//|| (value2 == value3) || (value1 == value3))
        @Override
        public void mousePressed(MouseEvent me) {
            System.out.println("End");

            t.mysuspend();
            t1.mysuspend1();
            t2.mysuspend2();
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            int Total = 0;
            if (Total == 0) {
                if (ClickCount == 0) {
                    ClickCount++;

                    if ((value1 == value2) || (value2 == value1)) {
                        System.out.println("This" + value1);
                        Total = value1 * CountBet;

                        if (Total > 0) {
                            Average = (Total + Credits) / 2;
                            Credits = Total;
                        }

                        Count = Total + Count;
                        CountBet = 0;
                        CreditArea.setText(Count + "");
                        BetArea.setText(CountBet + "");

                    }
                    else if ((value2 == value3) || (value3 == value2)) {
                        System.out.println("This3" + value3);
                        Total = value3 * CountBet;

                        if (Total > 0) {
                            Average = (Total + Credits) / 2;
                            Credits = Total;
                        }
                        Count = Total + Count;
                        CountBet = 0;
                        CreditArea.setText(Count + "");
                        BetArea.setText(CountBet + "");

                    }
                    else if ((value1 == value3) || (value3 == value1)) {
                        System.out.println("This2" + value1);
                        Total = value1 * CountBet;

                        if (Total > 0) {
                            Average = (Total + Credits) / 2;
                            Credits = Total;
                        }
                        Count = Total + Count;
                        CountBet = 0;
                        CreditArea.setText(Count + "");
                        BetArea.setText(CountBet + "");

                    }
//                    if ((value1 == value2) && (value2 == value1) && (value2 == value3) && (value3 == value2) && (value1 == value3) && (value3 == value1)) {
//                        System.out.println("This1" + value1);
//                        Total = value1 * CountBet;
//
//                        if (Total > 0) {
//                            Average = (Total + Credits) / 2;
//                            Credits = Total;
//                        }
//                        Count = Total + Count;
//                        CountBet = 0;
//                        CreditArea.setText(Count + "");
//                        BetArea.setText(CountBet + "");
//
//                    }

                    if (Total > 0) {
                        JOptionPane.showMessageDialog(frame, "You Win! " + Total + " £");
                        win = win + 5;
                    } else {
                        CountBet = 0;
                        BetArea.setText(CountBet + "");
                        JOptionPane.showMessageDialog(frame, "You Lose! ");
                        lose = lose + 5;
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "Spin Again! Already Stopped it!");
                }
            }
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent me) {
            //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseMoved(MouseEvent me) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public void inputStudent(double avrg, int wins, int loses) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

        try {

            //open
            FileWriter fw = new FileWriter(dateFormat.format(date) + ".txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            //write
            bw.write("Average Credits Won: " + avrg + " \n");
            bw.write("Win times: " + (wins / 5) + " \n");
            bw.write("Lose times: " + (loses / 5) + " \n");

            bw.flush();
            System.out.println("Done");

            //close
            bw.close();
            fw.close();
        } catch (IOException e) {
        }

    }

    public static void main(String[] args) {

        SlotMachine myFrame = new SlotMachine();

        myFrame.setSize(1500, 1000);       // "super" Frame sets initial size
        myFrame.setTitle("Slot Machine");           // "super" Frame sets title
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);

    }

}
