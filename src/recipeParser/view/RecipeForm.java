/*
 * Created by JFormDesigner on Mon Feb 20 04:49:00 CST 2012
 */

package recipeParser.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jgoodies.forms.factories.*;
import com.jgoodies.forms.layout.*;

/**
 * @author unknown
 */
public class RecipeForm {
    public RecipeForm() {
        initComponents();
    }

    public JTextField getRecipeNameText() {
        return RecipeNameText;
    }

    private void ButtonParseActionPerformed(ActionEvent e) {

    }

    public JTextArea getOutputIngredients() {
        return OutputIngredients;
    }

    public JTextArea getOutputDirections() {
        return OutputDirections;
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

    public JButton getButtonParse() {
        return ButtonParse;
    }

    public JPanel getRecipeParserPanel() {
        return RecipeParserPanel;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Najim Yaqubie
        DefaultComponentFactory compFactory = DefaultComponentFactory.getInstance();
        RecipeParserPanel = new JPanel();
        title1 = compFactory.createTitle("Recipe Parser");
        RecipeNameLabel = new JLabel();
        RecipeNameText = new JTextField();
        radioButton1 = new JRadioButton();
        ButtonParse = new JButton();
        RecipeMetaLabel = new JLabel();
        scrollPane2 = new JScrollPane();
        RecipeMetaInput = new JTextArea();
        RecipeIngLabel = new JLabel();
        scrollPane3 = new JScrollPane();
        RecipeIngInput = new JTextArea();
        OutputIngLabel = new JLabel();
        scrollPane1 = new JScrollPane();
        OutputIngredients = new JTextArea();
        RecipeDirectionsLabel = new JLabel();
        scrollPane4 = new JScrollPane();
        RecipeDirInput = new JTextArea();
        OuputDirLabel = new JLabel();
        scrollPane5 = new JScrollPane();
        OutputDirections = new JTextArea();

        //======== RecipeParserPanel ========
        {

            // JFormDesigner evaluation mark
            RecipeParserPanel.setBorder(new javax.swing.border.CompoundBorder(
                new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                    "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                    javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                    java.awt.Color.red), RecipeParserPanel.getBorder())); RecipeParserPanel.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

            RecipeParserPanel.setLayout(new FormLayout(
                "[20dlu,default], $lcgap, 55dlu, $lcgap, 130dlu, $lcgap, [24dlu,default], $lcgap, 80dlu, $lcgap, 100dlu, $lcgap, [20dlu,default]",
                "[20dlu,default], 4*($lgap, default), $lgap, [35dlu,default], 2*($lgap, default), $lgap, [125dlu,default], 8*($lgap, default), $lgap, [80dlu,default], 7*($lgap, default), $lgap, [20dlu,default]"));

            //---- title1 ----
            title1.setFont(title1.getFont().deriveFont(title1.getFont().getStyle() | Font.BOLD, title1.getFont().getSize() + 12f));
            RecipeParserPanel.add(title1, CC.xywh(3, 3, 3, 1, CC.CENTER, CC.DEFAULT));

            //---- RecipeNameLabel ----
            RecipeNameLabel.setText("Name Of Recipe:");
            RecipeNameLabel.setLabelFor(RecipeNameText);
            RecipeParserPanel.add(RecipeNameLabel, CC.xy(3, 7));
            RecipeParserPanel.add(RecipeNameText, CC.xy(5, 7));

            //---- radioButton1 ----
            radioButton1.setText("Regular Parse");
            RecipeParserPanel.add(radioButton1, CC.xy(9, 7));

            //---- ButtonParse ----
            ButtonParse.setText("Parse!");
            ButtonParse.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ButtonParseActionPerformed(e);
                }
            });
            RecipeParserPanel.add(ButtonParse, CC.xy(11, 7));

            //---- RecipeMetaLabel ----
            RecipeMetaLabel.setText("Meta-Data:");
            RecipeMetaLabel.setLabelFor(RecipeMetaInput);
            RecipeParserPanel.add(RecipeMetaLabel, CC.xy(3, 9));

            //======== scrollPane2 ========
            {

                //---- RecipeMetaInput ----
                RecipeMetaInput.setLineWrap(true);
                scrollPane2.setViewportView(RecipeMetaInput);
            }
            RecipeParserPanel.add(scrollPane2, CC.xywh(5, 9, 1, 3));

            //---- RecipeIngLabel ----
            RecipeIngLabel.setText("Ingredients:");
            RecipeIngLabel.setLabelFor(RecipeIngInput);
            RecipeParserPanel.add(RecipeIngLabel, CC.xy(3, 15));

            //======== scrollPane3 ========
            {

                //---- RecipeIngInput ----
                RecipeIngInput.setLineWrap(true);
                scrollPane3.setViewportView(RecipeIngInput);
            }
            RecipeParserPanel.add(scrollPane3, CC.xywh(5, 15, 1, 17));

            //---- OutputIngLabel ----
            OutputIngLabel.setText("Structured Ingredients:");
            OutputIngLabel.setLabelFor(OutputIngredients);
            RecipeParserPanel.add(OutputIngLabel, CC.xy(9, 15));

            //======== scrollPane1 ========
            {

                //---- OutputIngredients ----
                OutputIngredients.setEditable(false);
                OutputIngredients.setLineWrap(true);
                OutputIngredients.setBackground(new Color(204, 204, 255));
                scrollPane1.setViewportView(OutputIngredients);
            }
            RecipeParserPanel.add(scrollPane1, CC.xywh(9, 17, 3, 15));

            //---- RecipeDirectionsLabel ----
            RecipeDirectionsLabel.setText("Directions:");
            RecipeDirectionsLabel.setLabelFor(RecipeDirInput);
            RecipeParserPanel.add(RecipeDirectionsLabel, CC.xy(3, 33));

            //======== scrollPane4 ========
            {

                //---- RecipeDirInput ----
                RecipeDirInput.setLineWrap(true);
                scrollPane4.setViewportView(RecipeDirInput);
            }
            RecipeParserPanel.add(scrollPane4, CC.xywh(5, 33, 1, 17));

            //---- OuputDirLabel ----
            OuputDirLabel.setText("Structured Directions:");
            OuputDirLabel.setLabelFor(OutputDirections);
            RecipeParserPanel.add(OuputDirLabel, CC.xy(9, 33));

            //======== scrollPane5 ========
            {

                //---- OutputDirections ----
                OutputDirections.setEditable(false);
                OutputDirections.setLineWrap(true);
                OutputDirections.setBackground(new Color(204, 204, 255));
                scrollPane5.setViewportView(OutputDirections);
            }
            RecipeParserPanel.add(scrollPane5, CC.xywh(9, 35, 3, 15));
        }

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButton1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Najim Yaqubie
    private JPanel RecipeParserPanel;
    private JLabel title1;
    private JLabel RecipeNameLabel;
    private JTextField RecipeNameText;
    private JRadioButton radioButton1;
    private JButton ButtonParse;
    private JLabel RecipeMetaLabel;
    private JScrollPane scrollPane2;
    private JTextArea RecipeMetaInput;
    private JLabel RecipeIngLabel;
    private JScrollPane scrollPane3;
    private JTextArea RecipeIngInput;
    private JLabel OutputIngLabel;
    private JScrollPane scrollPane1;
    private JTextArea OutputIngredients;
    private JLabel RecipeDirectionsLabel;
    private JScrollPane scrollPane4;
    private JTextArea RecipeDirInput;
    private JLabel OuputDirLabel;
    private JScrollPane scrollPane5;
    private JTextArea OutputDirections;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
