
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
public class Symbol implements ISymbol {
   
     protected ImageIcon Image;
    protected int value;
    
    public Symbol(){
        Image =null;
        value = 0;
    }
    
    public Symbol(int Value, ImageIcon Image){
        this.Image =Image;
        this.value =Value;
    }
     @Override
     public void setValue(int Value){
        this.value =Value;
    }
     @Override
    public int getValue(){
        return value;
    }
    @Override
    public void setImage(ImageIcon Image){
        this.Image = Image;
    }

    /**
     *
     * @return
     */
    @Override
    public ImageIcon getImage(){
        return Image;
    }
   
}
