/**
 * Student Name: Rehan Nagoor Mohideen
 * Student ID: 1100592
 */
package eStoreSearch;

import java.io.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

@SuppressWarnings("unchecked")
public class EStoreSearchGUI extends JFrame{
    public static final int WIDTH = 500;
    public static final int HEIGHT = 435;

    private JPanel initialPanel;
    private JPanel addPanel;
    private JPanel searchPanel;

    private JPanel authorsProductPanel;
    private JPanel publisherProductPanel;
    private JPanel makerProductPanel;

    private JTextArea memoDisplay;
    private JTextField makerText;
    private JTextField publisherText;
    private JTextField authorsText;
    private JTextField yearText;
    private JTextField priceText;
    private JTextField descriptionText;
    private JTextField PIDText;

    private JTextArea smemoDisplay;
    private JTextField startYearText;
    private JTextField endYearText;
    private JTextField descriptionKeywordsText;
    private JTextField searchPIDText;

    private boolean bookCheck = true;

    /**
     * The listener to change screen on add menu item
     */
    private class AddListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            initialPanel.setVisible(false);
            addPanel.setVisible(true);
            searchPanel.setVisible(false);
        }
    } //End of AddListener inner class

    /**
     * The listener to change screen on search menu item
     */
    private class SearchListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            initialPanel.setVisible(false);
            addPanel.setVisible(false);
            searchPanel.setVisible(true);
        }
    } //End of SearchListener inner class

    /**
     * The listener to close window on quit menu option
     */
    private class QuitListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    } //End of QuitListener inner class

    /**
     * The listener to show and hide entries to switch between adding book or electronic product
     */
    private class TypeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            JComboBox cb = (JComboBox)e.getSource();
            String PName = (String)cb.getSelectedItem();

            if (PName.equals("book")) {
                bookCheck = true;
                authorsProductPanel.setVisible(true);
                publisherProductPanel.setVisible(true);
                makerProductPanel.setVisible(false);

            } else if (PName.equals("electronic")) {
                bookCheck = false;
                authorsProductPanel.setVisible(false);
                publisherProductPanel.setVisible(false);
                makerProductPanel.setVisible(true);
            }
        }
    } //End of TypeListener inner class

    /**
     * The listener to reset all the elements on the screen on button click
     */
    private class ProductResetButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            makerText.setText("");
            publisherText.setText("");
            authorsText.setText("");
            yearText.setText("");
            priceText.setText("");
            descriptionText.setText("");
            PIDText.setText("");
            memoDisplay.setText("");
        }
    } //End of ProductResetButtonListener inner class

    /**
     * The listener to add the type of product to the database on button click
     */
    private class ProductAddButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if (bookCheck==true) {
                try {
                    Book tempBook = EStoreSearch.createBook(EStoreSearch.productList, PIDText.getText(), descriptionText.getText(), yearText.getText(), priceText.getText(), authorsText.getText(), publisherText.getText());
                    EStoreSearch.addHash(EStoreSearch.index, tempBook.getDescription(), EStoreSearch.productList.size());
                    EStoreSearch.productList.add(tempBook);
                } catch (Exception ex) {
                    memoDisplay.setText(ex.getMessage());
                    return;
                }
                
            } else {
                try {
                    Electronic tempElectronic = EStoreSearch.createElectronic(EStoreSearch.productList, PIDText.getText(), descriptionText.getText(), yearText.getText(), priceText.getText(), makerText.getText());
                    EStoreSearch.addHash(EStoreSearch.index, tempElectronic.getDescription(), EStoreSearch.productList.size());
                    EStoreSearch.productList.add(tempElectronic);
                } catch (Exception ex) {
                    memoDisplay.setText(ex.getMessage());
                    return;
                }
            }
            memoDisplay.setText("Product successfully added");
            if (!(EStoreSearch.argument0 == null)) {
                EStoreSearch.saveData(EStoreSearch.productList, EStoreSearch.argument0);
            }
        }
    } //End of ProductAddButtonListener inner class

    /**
     * The listener to reset all the elements on the screen on button click for seach screen
     */
    private class searchProductResetButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            startYearText.setText("");
            endYearText.setText("");
            descriptionKeywordsText.setText("");
            searchPIDText.setText("");
            smemoDisplay.setText("");
        }
    } //End of searchProductResetButtonListener inner class

    /**
     * The listener to search for valid database elements based on entries
     */
    private class ProductSearchButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int productID = 0;
            int sYear = 0;
            int eYear = 0;

            if (!(searchPIDText.getText().isEmpty())) {//conver the Product ID to an integer and make sure its in the valid range
                try {
                    productID = Integer.parseInt(searchPIDText.getText());
                } catch (Exception ex) {
                    smemoDisplay.setText("error: Product ID entered cannot be converted to an integer");
                    productID = -1;
                }
                if( !(productID < 1000000 && productID > 0) ) { 
                    smemoDisplay.setText("error: Product ID not in valid range of 6 digits");
                    return;
                }
            }else {
                productID = -1;
            }

            String UserDescriptionKeywords[];
            ArrayList<Integer> searchIndexes;
            UserDescriptionKeywords = (descriptionKeywordsText.getText()).split("[ ,.\"-]+");

            if (!((startYearText.getText()).isEmpty())) {//search based on which year values were enterred after ensuring validity
                try {
                    sYear = Integer.parseInt(startYearText.getText());
                } catch (Exception e1) {
                    smemoDisplay.setText("error: start year is invalid and could not be converted to an integer");
                    return;
                }

                if(!( sYear < 10000 && sYear > 999)) { 
                    smemoDisplay.setText("error: Start Year not in valid range of 1000 to 9999 so will be ignored");
                    return;
                }

                if (!((endYearText.getText()).isEmpty())) {
                    try {
                        eYear = Integer.parseInt(endYearText.getText());
                    } catch (Exception e2) {
                        smemoDisplay.setText("error: end year is invalid and could not be converted to an integer");
                        return;
                    }
                    if(!( eYear < 10000 && eYear > 999)) { 
                        smemoDisplay.setText("error: End Year not in valid range of 1000 to 9999 so will be ignored");
                        return;
                    }
                    if(!( sYear <= eYear)) { 
                        smemoDisplay.setText("error: Start Year must be before End Year");
                        return;
                    }
                    searchIndexes = EStoreSearch.printSearchValues(EStoreSearch.productList, EStoreSearch.index, productID, UserDescriptionKeywords, sYear + "-" + eYear);
                }else {
                    searchIndexes = EStoreSearch.printSearchValues(EStoreSearch.productList, EStoreSearch.index, productID, UserDescriptionKeywords, sYear + "-");
                }
            }else {
                if (!((endYearText.getText()).isEmpty())) {
                    try {
                        eYear = Integer.parseInt(endYearText.getText());
                    } catch (Exception e2) {
                        smemoDisplay.setText("error: end year is invalid and could not be converted to an integer");
                        return;
                    }
                    if(!( eYear < 10000 && eYear > 999)) { 
                        smemoDisplay.setText("error: End Year not in valid range of 1000 to 9999 so will be ignored");
                        return;
                    }
                    searchIndexes = EStoreSearch.printSearchValues(EStoreSearch.productList, EStoreSearch.index, productID, UserDescriptionKeywords, "-" + eYear);
                }else {
                    searchIndexes = EStoreSearch.printSearchValues(EStoreSearch.productList, EStoreSearch.index, productID, UserDescriptionKeywords, "");
                }
            }

            smemoDisplay.setText("");
            for (Integer i : searchIndexes) {//prints all the search indexes obtained
                String oldText = smemoDisplay.getText();
                smemoDisplay.setText( oldText + EStoreSearch.productList.get(i).toString2());
            }
        }
    } //End of ProductSearchButtonListener inner class

    /**
     * the main GUI display object
     */
    public EStoreSearchGUI( ) {
        super("eStoreSearch");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        //for the initial panel
        initialPanel = new JPanel();
        initialPanel.setVisible(true);
        add(initialPanel);

        //-> message displayed in the panel
        JLabel welcomeMsg = new JLabel("<html> <br/><br/><br/>Welcome to eStoreSearch<br/><br/><br/>Choose a command from the <html>" + 
        "<html>\"Commands\" menu above for<br/>adding a product, searching products, or quitting the program.<html>");
        initialPanel.add(welcomeMsg);


        //For the add Panel from menu option
        addPanel = new JPanel();
        addPanel.setLayout(new BorderLayout());
        addPanel.setVisible(false);
        add(addPanel);

        //->Add Product Panel Box layout for entrees
        JPanel addProductPanel = new JPanel();
        addProductPanel.setLayout(new BoxLayout(addProductPanel, BoxLayout.Y_AXIS));
        addPanel.add(addProductPanel, BorderLayout.CENTER);

        //->-> the label entree for the Add Product Panel
        JLabel addProductLabel = new JLabel("Adding a product");
        JPanel addProductLabelFlowPanel = new JPanel();
        addProductLabelFlowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addProductPanel.add(addProductLabelFlowPanel);
        addProductLabelFlowPanel.add(addProductLabel);

        //->-> the Type boxpanel entree for the Add Product Panel
        JPanel typeProductPanel = new JPanel( );
        typeProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addProductPanel.add(typeProductPanel);
        //->->-> the type label for the type product panel
        JLabel typeLabel = new JLabel("   Type:              ");
        typeProductPanel.add(typeLabel);
        //->->-> the combo box for the type of product
        String[] productType = {"book", "electronic"};
        JComboBox productComboBox = new JComboBox(productType);
        productComboBox.setBackground(Color.WHITE);
        productComboBox.setSelectedIndex(0);
        productComboBox.addActionListener(new TypeListener( ));
        typeProductPanel.add(productComboBox);

        //->-> the PID entree for the Add Product Panel
        JPanel PIDProductPanel = new JPanel( );
        PIDProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addProductPanel.add(PIDProductPanel);
        //->->-> the PID label for the PID
        JLabel PIDLabel = new JLabel("   Product ID:   ");
        PIDProductPanel.add(PIDLabel);
        //->->-> the text box for the product ID
        PIDText = new JTextField(6);
        PIDProductPanel.add(PIDText);

        //->-> the description entree for the Add Product Panel
        JPanel descriptionProductPanel = new JPanel( );
        descriptionProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addProductPanel.add(descriptionProductPanel);
        //->->-> the description label for the description
        JLabel descriptionLabel = new JLabel("   Description: ");
        descriptionProductPanel.add(descriptionLabel);
        //->->-> the text box for the description
        descriptionText = new JTextField(20);
        //String inputString = descriptionText.getText();
        descriptionProductPanel.add(descriptionText);

        //->-> the price entree for the Add Product Panel
        JPanel priceProductPanel = new JPanel( );
        priceProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addProductPanel.add(priceProductPanel);
        //->->-> the price label for the price
        JLabel priceLabel = new JLabel("   Price:             ");
        priceProductPanel.add(priceLabel);
        //->->-> the text box for the price
        priceText = new JTextField(5);
        //String inputString = priceText.getText();
        priceProductPanel.add(priceText);

        //->-> the year entree for the Add Product Panel
        JPanel yearProductPanel = new JPanel( );
        yearProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addProductPanel.add(yearProductPanel);
        //->->-> the year label for the year
        JLabel yearLabel = new JLabel("   Year:              ");
        yearProductPanel.add(yearLabel);
        //->->-> the text box for the year
        yearText = new JTextField(5);
        //String inputString = yearText.getText();
        yearProductPanel.add(yearText);

        //->-> the authors entree for the Add Product Panel
        authorsProductPanel = new JPanel( );
        authorsProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addProductPanel.add(authorsProductPanel);
        //->->-> the authors label for the authors
        JLabel authorsLabel = new JLabel("   Authors:        ");
        authorsProductPanel.add(authorsLabel);
        //->->-> the text box for the author
        authorsText = new JTextField(20);
        //String inputString = authorsText.getText();
        authorsProductPanel.add(authorsText);

        //->-> the publisher entree for the Add Product Panel
        publisherProductPanel = new JPanel( );
        publisherProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addProductPanel.add(publisherProductPanel);
        //->->-> the publisher label for the publisher
        JLabel publisherLabel = new JLabel("   Publisher:     ");
        publisherProductPanel.add(publisherLabel);
        //->->-> the text box for the publisher
        publisherText = new JTextField(20);
        publisherProductPanel.add(publisherText);

        //->-> the maker entree for the Add Product Panel
        makerProductPanel = new JPanel( );
        makerProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addProductPanel.add(makerProductPanel);
        //->->-> the maker label for the maker
        JLabel makerLabel = new JLabel("   Maker:           ");
        makerProductPanel.add(makerLabel);
        //->->-> the text box for the maker
        makerText = new JTextField(4);
        makerProductPanel.setVisible(false);
        makerProductPanel.add(makerText);


        //->Add Product Panel buttons layout for entrees
        JPanel addProductButtonsPanel = new JPanel( );
        addProductButtonsPanel.setLayout(new BoxLayout(addProductButtonsPanel, BoxLayout.Y_AXIS));
        addPanel.add(addProductButtonsPanel, BorderLayout.EAST);

        JPanel dummyFlowPanel = new JPanel();
        dummyFlowPanel.setLayout(new FlowLayout());
        addProductButtonsPanel.add(dummyFlowPanel);
        //->->the reset button for the add products window
        JButton productResetButton = new JButton("Reset");
        productResetButton.addActionListener(new ProductResetButtonListener( ));
        JPanel productResetButtonFlowPanel = new JPanel();
        productResetButtonFlowPanel.setLayout(new FlowLayout());
        addProductButtonsPanel.add(productResetButtonFlowPanel);
        productResetButtonFlowPanel.add(productResetButton);

        //->->the add button for the add products window
        JButton productAddButton = new JButton("Add");
        productAddButton.addActionListener(new ProductAddButtonListener( ));
        JPanel productAddButtonFlowPanel = new JPanel();
        productAddButtonFlowPanel.setLayout(new FlowLayout());
        addProductButtonsPanel.add(productAddButtonFlowPanel);
        productAddButtonFlowPanel.add(productAddButton);


        //->Add Product messages layout for entrees
        JPanel addProductMessagesPanel = new JPanel( );
        addProductMessagesPanel.setLayout(new BoxLayout(addProductMessagesPanel, BoxLayout.Y_AXIS));
        addPanel.add(addProductMessagesPanel, BorderLayout.SOUTH);
        //->->label for Messaages
        JLabel messagesLabel = new JLabel("Messages");
        JPanel messagesLabelFlowPanel = new JPanel();
        messagesLabelFlowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        addProductMessagesPanel.add(messagesLabelFlowPanel);
        messagesLabelFlowPanel.add(messagesLabel);
        //->->text area for messages
        JPanel textPanel = new JPanel( );
        memoDisplay = new JTextArea(5, 40);
        memoDisplay.setBackground(Color.WHITE);
        memoDisplay.setEditable(false);
        JScrollPane scrolledText = new JScrollPane(memoDisplay);
        scrolledText.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrolledText.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        textPanel.add(scrolledText);
        addProductMessagesPanel.add(textPanel);



        //For the search Panel from menu option
        searchPanel = new JPanel( );
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setVisible(false);
        add(searchPanel);

        //->search Product Panel Box layout for entrees
        JPanel searchProductPanel = new JPanel( );
        searchProductPanel.setLayout(new BoxLayout(searchProductPanel, BoxLayout.Y_AXIS));
        searchPanel.add(searchProductPanel, BorderLayout.CENTER);

        //->-> the label entree for the Search Product Panel
        JLabel searchProductLabel = new JLabel("Searching products");
        JPanel searchProductLabelFlowPanel = new JPanel();
        searchProductLabelFlowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchProductPanel.add(searchProductLabelFlowPanel);
        searchProductLabelFlowPanel.add(searchProductLabel);

        //->-> the PID entree for the Search Product Panel
        JPanel searchPIDProductPanel = new JPanel( );
        searchPIDProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchProductPanel.add(searchPIDProductPanel);
        //->->-> the PID label for the search. same label as in add
        searchPIDProductPanel.add(new JLabel("   Product ID: "));
        //->->-> the text box for the product ID
        searchPIDText = new JTextField(6);
        searchPIDProductPanel.add(searchPIDText);

        //->-> the description entree for the Search Product Panel
        JPanel searchDescriptionProductPanel = new JPanel( );
        searchDescriptionProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchProductPanel.add(searchDescriptionProductPanel);
        //->->-> the description label for the description
        JLabel searchDescriptionLabel = new JLabel("<html>   Description   <br/>   Keywords:    <html>");
        searchDescriptionProductPanel.add(searchDescriptionLabel);
        //->->-> the text box for the description
        descriptionKeywordsText = new JTextField(20);
        searchDescriptionProductPanel.add(descriptionKeywordsText);

        //->-> the start year entree for the Search Product Panel
        JPanel startYearProductPanel = new JPanel( );
        startYearProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchProductPanel.add(startYearProductPanel);
        //->->-> the start year label for the start year
        JLabel startYearLabel = new JLabel("   Start Year:  ");
        startYearProductPanel.add(startYearLabel);
        //->->-> the text box for the startYear
        startYearText = new JTextField(4);
        startYearProductPanel.add(startYearText);

        //->-> the end year entree for the Search Product Panel
        JPanel endYearProductPanel = new JPanel( );
        endYearProductPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchProductPanel.add(endYearProductPanel);
        //->->-> the end year label for the end year
        JLabel endYearLabel = new JLabel("   End Year:    ");
        endYearProductPanel.add(endYearLabel);
        //->->-> the text box for the price
        endYearText = new JTextField(4);
        endYearProductPanel.add(endYearText);


        //->Add Product Panel buttons layout for entrees
        JPanel searchProductButtonsPanel = new JPanel( );
        searchProductButtonsPanel.setLayout(new BoxLayout(searchProductButtonsPanel, BoxLayout.Y_AXIS));
        searchPanel.add(searchProductButtonsPanel, BorderLayout.EAST);

        JPanel dummy2FlowPanel = new JPanel();
        dummy2FlowPanel.setLayout(new FlowLayout());
        searchProductButtonsPanel.add(dummy2FlowPanel);
        //->->the reset button for the search products window
        JButton searchProductResetButton = new JButton("Reset");
        searchProductResetButton.addActionListener(new searchProductResetButtonListener( ));
        JPanel searchProductResetButtonFlowPanel = new JPanel();
        searchProductResetButtonFlowPanel.setLayout(new FlowLayout());
        searchProductButtonsPanel.add(searchProductResetButtonFlowPanel);
        searchProductResetButtonFlowPanel.add(searchProductResetButton);

        //->->the search button for the search products window
        JButton productSearchButton = new JButton("Search");
        productSearchButton.addActionListener(new ProductSearchButtonListener( ));
        JPanel productSearchButtonFlowPanel = new JPanel();
        productSearchButtonFlowPanel.setLayout(new FlowLayout());
        searchProductButtonsPanel.add(productSearchButtonFlowPanel);
        productSearchButtonFlowPanel.add(productSearchButton);


        //->Search Product messages layout for entrees
        JPanel searchProductMessagesPanel = new JPanel( );
        searchProductMessagesPanel.setLayout(new BoxLayout(searchProductMessagesPanel, BoxLayout.Y_AXIS));
        searchPanel.add(searchProductMessagesPanel, BorderLayout.SOUTH);
        //->->label for Messaages
        JLabel searchResLabel = new JLabel("Search Results");
        JPanel searchResLabelFlowPanel = new JPanel();
        searchResLabelFlowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        searchProductMessagesPanel.add(searchResLabelFlowPanel);
        searchResLabelFlowPanel.add(searchResLabel);
        //->->text area for messages
        JPanel searchTextPanel = new JPanel( );
        smemoDisplay = new JTextArea(10, 40);
        smemoDisplay.setBackground(Color.WHITE);
        //smemoDisplay.setEditable(false);
        JScrollPane sScrolledText = new JScrollPane(smemoDisplay);
        sScrolledText.setHorizontalScrollBarPolicy(
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sScrolledText.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        searchTextPanel.add(sScrolledText);
        searchProductMessagesPanel.add(searchTextPanel);


        //Menu bar and options
        JMenu commandsMenu = new JMenu("Commands");

        JMenuItem addChoice = new JMenuItem("add");
        addChoice.addActionListener(new AddListener( ));
        commandsMenu.add(addChoice);

        JMenuItem searchChoice = new JMenuItem("search");
        searchChoice.addActionListener(new SearchListener( ));
        commandsMenu.add(searchChoice);

        JMenuItem quitChoice = new JMenuItem("quit");
        quitChoice.addActionListener(new QuitListener( ));
        commandsMenu.add(quitChoice);

        JMenuBar bar = new JMenuBar( );
        bar.add(commandsMenu);
        setJMenuBar(bar);
    }
}


