
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
public interface ISymbol  {
    public abstract void setValue(int Value);
    public abstract int getValue();
    public abstract void setImage(ImageIcon Image);
    public abstract ImageIcon getImage();
}
