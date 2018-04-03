
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vinay
 */
public class Reel {

    private ArrayList<Symbol> reel;

    public Reel() {
        Symbol Bell = null;
        Symbol Cherry = null;
        Symbol Lemon = null;
        Symbol Plum = null;
        Symbol Redseven = null;
        Symbol Watermelon = null;

        reel = new ArrayList<Symbol>();

        try {
            Bell = new Symbol(6, new ImageIcon(getClass().getResource("bell.png")));
        } catch (NullPointerException e) {
            System.out.println("The Image bell is not found");
        }

        try {
            Cherry = new Symbol(2, new ImageIcon(getClass().getResource("cherry.png")));
        } catch (NullPointerException e) {
            System.out.println("The Image cherry is not found");
        }

        try {
            Lemon = new Symbol(3, new ImageIcon(getClass().getResource("lemon.png")));
        } catch (NullPointerException e) {
            System.out.println("The Image lemon is not found");
        }

        try {
            Plum = new Symbol(4, new ImageIcon(getClass().getResource("plum.png")));
        } catch (NullPointerException e) {
            System.out.println("The Image plum is not found");
        }

        try {
            Redseven = new Symbol(7, new ImageIcon(getClass().getResource("redseven.png")));
        } catch (NullPointerException e) {
            System.out.println("The Image redseven is not found");
        }

        try {
            Watermelon = new Symbol(5, new ImageIcon(getClass().getResource("watermelon.png")));
        } catch (NullPointerException e) {
            System.out.println("The Image watermelon is not found");
        }
        reel.add(Bell);
        reel.add(Cherry);
        reel.add(Lemon);
        reel.add(Plum);
        reel.add(Redseven);
        reel.add(Watermelon);

    }

    
     public Symbol Spin(){
    Random rand = new Random();
        int randomNum; 
        randomNum = rand.nextInt(reel.size());
        
        return reel.get(randomNum);
    }
}
