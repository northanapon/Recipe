/*
 * Created by JFormDesigner on Mon Feb 20 06:06:50 CST 2012
 */

package recipeParser.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author Najim Yaqubie
 */
public class RecipeGUI extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RecipeGUI() {
        initComponents();
    }
	
    public JTextArea getOutputText() {
        return OutputText;
    }

    public JTextArea getRecipeDirInput() {
        return RecipeDirInput;
    }

    public JTextArea getRecipeIngInput() {
        return RecipeIngInput;
    }

    public JTextArea getRecipeMetaInput() {
        return RecipeMetaInput;
    }

    public JTextArea getRecipeNameInput() {
        return RecipeNameInput;
    }

    public JButton getButtonParse() {
        return ButtonParse;
    }
    
    public JButton getBtnReplace() {
    	return this.btnReplace;
    }
    
    public JButton getBtnChange() {
    	return this.btnChange;
    }
    
    public JButton getBtnSubstitute() {
    	return this.btnSubstitute;
    }
    
    public JTextField getTxtIngredientFrom(){
    	return this.txtIngredientFrom;
    }
    
    public JTextField getTxtIngredientTo(){
    	return this.txtIngredientTo;
    }
    
    public JComboBox getDdlCuisine() {
    	return this.ddlCuisine;
    }
    
    public JComboBox getDdlActionFrom(){
    	return this.ddlActionFrom;
    }
    
    public JComboBox getDdlActionTo(){
    	return this.ddlActionTo;
    }
    
    private void ButtonParseActionPerformed(ActionEvent e) {

    }

	private void btnReplaceActionPerformed(ActionEvent e) {
		
	}

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Najim Yaqubie
		panel1 = new JPanel();
		RecipeNameLabel = new JLabel();
		scrollPane1 = new JScrollPane();
		RecipeNameInput = new JTextArea();
		RecipeMetaLabel = new JLabel();
		scrollPane2 = new JScrollPane();
		RecipeMetaInput = new JTextArea();
		RecipeIngLabel = new JLabel();
		scrollPane3 = new JScrollPane();
		RecipeIngInput = new JTextArea();
		RecipeDirLabel = new JLabel();
		scrollPane4 = new JScrollPane();
		RecipeDirInput = new JTextArea();
		ButtonParse = new JButton();
		panel2 = new JPanel();
		label1 = new JLabel();
		txtIngredientFrom = new JTextField();
		txtIngredientTo = new JTextField();
		btnReplace = new JButton();
		label3 = new JLabel();
		ddlCuisine = new JComboBox();
		btnChange = new JButton();
		label5 = new JLabel();
		ddlActionFrom = new JComboBox();
		ddlActionTo = new JComboBox();
		btnSubstitute = new JButton();
		OutputLabel = new JLabel();
		scrollPane5 = new JScrollPane();
		OutputText = new JTextArea();

		//======== this ========
		setTitle("Recipe Parser");
		Container contentPane = getContentPane();
		contentPane.setLayout(new FormLayout(
			"[60dlu,default], $lcgap, [150dlu,default], $lcgap, [20dlu,default], $lcgap, [60dlu,default], $lcgap, 150dlu, $lcgap, [15dlu,default], $lcgap, default",
			"top:[357dlu,default]"));

		//======== panel1 ========
		{

			// JFormDesigner evaluation mark
			panel1.setBorder(new javax.swing.border.CompoundBorder(
				new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
					"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
					javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
					java.awt.Color.red), panel1.getBorder())); panel1.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

			panel1.setLayout(new FormLayout(
				"60dlu, $lcgap, 150dlu",
				"2*(default, $lgap), [30dlu,default], $lgap, default, $lgap, [130dlu,default], $lgap, default, $lgap, [130dlu,default], $lgap, default"));

			//---- RecipeNameLabel ----
			RecipeNameLabel.setText("Recipe Name:");
			RecipeNameLabel.setLabelFor(RecipeNameInput);
			panel1.add(RecipeNameLabel, CC.xy(1, 1, CC.RIGHT, CC.DEFAULT));

			//======== scrollPane1 ========
			{
				scrollPane1.setViewportView(RecipeNameInput);
			}
			panel1.add(scrollPane1, CC.xy(3, 1));

			//---- RecipeMetaLabel ----
			RecipeMetaLabel.setText("Meta-Data:");
			panel1.add(RecipeMetaLabel, CC.xy(1, 3, CC.RIGHT, CC.DEFAULT));

			//======== scrollPane2 ========
			{

				//---- RecipeMetaInput ----
				RecipeMetaInput.setLineWrap(true);
				scrollPane2.setViewportView(RecipeMetaInput);
			}
			panel1.add(scrollPane2, CC.xywh(3, 3, 1, 3));

			//---- RecipeIngLabel ----
			RecipeIngLabel.setText("Ingredients:");
			RecipeIngLabel.setLabelFor(RecipeIngInput);
			panel1.add(RecipeIngLabel, CC.xy(1, 7, CC.RIGHT, CC.DEFAULT));

			//======== scrollPane3 ========
			{

				//---- RecipeIngInput ----
				RecipeIngInput.setLineWrap(true);
				RecipeIngInput.setWrapStyleWord(true);
				scrollPane3.setViewportView(RecipeIngInput);
			}
			panel1.add(scrollPane3, CC.xywh(3, 7, 1, 3));

			//---- RecipeDirLabel ----
			RecipeDirLabel.setText("Directions:");
			RecipeDirLabel.setLabelFor(RecipeDirInput);
			panel1.add(RecipeDirLabel, CC.xy(1, 11, CC.RIGHT, CC.DEFAULT));

			//======== scrollPane4 ========
			{

				//---- RecipeDirInput ----
				RecipeDirInput.setLineWrap(true);
				RecipeDirInput.setWrapStyleWord(true);
				scrollPane4.setViewportView(RecipeDirInput);
			}
			panel1.add(scrollPane4, CC.xywh(3, 11, 1, 3));

			//---- ButtonParse ----
			ButtonParse.setText("Parse!");
			ButtonParse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ButtonParseActionPerformed(e);
				}
			});
			panel1.add(ButtonParse, CC.xy(3, 15));
		}
		contentPane.add(panel1, CC.xywh(1, 1, 3, 1));

		//======== panel2 ========
		{
			panel2.setLayout(new FormLayout(
				"75dlu, $lcgap, [75dlu,default], $lcgap, 60dlu",
				"7*(default, $lgap), 241dlu, $lgap, default"));

			//---- label1 ----
			label1.setText("Replace Ingredient (from -> to):");
			panel2.add(label1, CC.xywh(1, 1, 3, 1));
			panel2.add(txtIngredientFrom, CC.xy(1, 3));
			panel2.add(txtIngredientTo, CC.xy(3, 3));

			//---- btnReplace ----
			btnReplace.setText("Replace");
			btnReplace.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					btnReplaceActionPerformed(e);
				}
			});
			panel2.add(btnReplace, CC.xy(5, 3));

			//---- label3 ----
			label3.setText("Change Cuisine:");
			panel2.add(label3, CC.xywh(1, 5, 3, 1));
			panel2.add(ddlCuisine, CC.xywh(1, 7, 3, 1));

			//---- btnChange ----
			btnChange.setText("Change");
			panel2.add(btnChange, CC.xy(5, 7));

			//---- label5 ----
			label5.setText("Substitute Action (from -> to):");
			panel2.add(label5, CC.xywh(1, 9, 3, 1));
			panel2.add(ddlActionFrom, CC.xy(1, 11));
			panel2.add(ddlActionTo, CC.xy(3, 11));

			//---- btnSubstitute ----
			btnSubstitute.setText("Substitute");
			panel2.add(btnSubstitute, CC.xy(5, 11));

			//---- OutputLabel ----
			OutputLabel.setText("Output:");
			panel2.add(OutputLabel, CC.xy(1, 13));

			//======== scrollPane5 ========
			{

				//---- OutputText ----
				OutputText.setBackground(Color.lightGray);
				OutputText.setEditable(false);
				OutputText.setLineWrap(true);
				OutputText.setWrapStyleWord(true);
				scrollPane5.setViewportView(OutputText);
			}
			panel2.add(scrollPane5, CC.xywh(1, 15, 5, 3));
		}
		contentPane.add(panel2, CC.xywh(7, 1, 4, 1, CC.DEFAULT, CC.TOP));
		setSize(1025, 645);
		setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
		
		
		
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Najim Yaqubie
	private JPanel panel1;
	private JLabel RecipeNameLabel;
	private JScrollPane scrollPane1;
	private JTextArea RecipeNameInput;
	private JLabel RecipeMetaLabel;
	private JScrollPane scrollPane2;
	private JTextArea RecipeMetaInput;
	private JLabel RecipeIngLabel;
	private JScrollPane scrollPane3;
	private JTextArea RecipeIngInput;
	private JLabel RecipeDirLabel;
	private JScrollPane scrollPane4;
	private JTextArea RecipeDirInput;
	private JButton ButtonParse;
	private JPanel panel2;
	private JLabel label1;
	private JTextField txtIngredientFrom;
	private JTextField txtIngredientTo;
	private JButton btnReplace;
	private JLabel label3;
	private JComboBox ddlCuisine;
	private JButton btnChange;
	private JLabel label5;
	private JComboBox ddlActionFrom;
	private JComboBox ddlActionTo;
	private JButton btnSubstitute;
	private JLabel OutputLabel;
	private JScrollPane scrollPane5;
	private JTextArea OutputText;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

}
