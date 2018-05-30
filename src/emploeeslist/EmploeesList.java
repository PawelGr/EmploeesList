package emploeeslist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EtchedBorder;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import javafx.scene.input.KeyCode;

/**
 *
 * @author PG
 */
public class EmploeesList extends JFrame
{
    public EmploeesList()
    {
        initComponents();
    }
    
    public void initComponents()
    {
    this.setTitle("Emploees List");
    this.setBounds(700, 200, 400, 300);
    this.setDefaultCloseOperation(3);
    this.getContentPane().setLayout(new GridLayout (4,1));
    this.getContentPane().add(panel1);
    this.getContentPane().add(panel2);
    this.getContentPane().add(panel3);
    this.getContentPane().add(panel4);
    
    panel1.add(pesel);
    panel1.add(peselField);
    
    peselField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    peselField.setPreferredSize(new Dimension(100,20));
   
    panel2.add(name);
    panel2.add(nameField);
    panel2.add(surname);
    panel2.add(surnameField);
    nameField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    surnameField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    nameField.setPreferredSize(new Dimension (100,20));
    surnameField.setPreferredSize(new Dimension (100,20));
    
    panel3.add(birthdate);
    panel3.add(day);
    panel3.add(month);
    panel3.add(year);
    day.addItem("Day");
    month.addItem("Month");
    year.addItem("Year");
    
    for (int i = 1; i <= 9; i++)
    {
    day.addItem("0"+i);
    }
    
    for (int i = 10; i <= 31; i++)
    {
    day.addItem(i);
    }
    
    for (int i = 1; i <= 9; i++)
    {
    month.addItem("0"+i);
    }
    
    for (int i = 10; i <= 12; i++)
    {
    month.addItem(i);
    }
    
    for (int i = 1918; i <= 2018; i++)
    {
    year.addItem(i);
    }
    
    panel4.add(save, BorderLayout.CENTER);
    panel4.add(resave);
     
    this.add(mainMenu);
    this.setJMenuBar(mainMenu);
    mainMenu.add(file);
    file.add(close);
    
    close.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) 
        {
          System.exit(0);
        }
    });
   
     
    save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {

            
                        if (!nameField.equals(null) & !nameField.equals(null) & day.getSelectedIndex() != 0 & month.getSelectedIndex() != 0 & year.getSelectedIndex() != 0 & !nameField.equals(null))
                            
                                if (plik.exists())
                                        try
                                            {                                
                                                BufferedReader reader = new BufferedReader(new FileReader(plik));
                                                BufferedWriter drukarz = new BufferedWriter(new FileWriter(plik, true));
                                                drukarz.newLine();
                                                drukarz.write("PESEL: " + peselField.getText() + " NAME: " + nameField.getText() + " SURNAME: " + surnameField.getText() + " BIRTHDATE: " + day.getSelectedItem() + "." + month.getSelectedItem() + "." + year.getSelectedItem() + ";");
                                                                                                
                                                peselField.setText("");
                                                nameField.setText("");
                                                surnameField.setText("");
                                                day.setSelectedIndex(0);
                                                month.setSelectedIndex(0);
                                                year.setSelectedIndex(0);
                                                reader.close();
                                                drukarz.close();
                                            }
                                        catch(IOException e)
                                            {
                                                System.out.println(e.getMessage());
                                            }
                                else
                                        try    
                                            {
                                                BufferedWriter drukarz = new BufferedWriter(new FileWriter(plik));
                                                drukarz.write("PESEL: " + peselField.getText() + " NAME: " + nameField.getText() + " SURNAME: " + surnameField.getText() + " BIRTHDATE: " + day.getSelectedItem() + "." + month.getSelectedItem() + "." + year.getSelectedItem() + ";");
                                                                                          
                                                peselField.setText("");
                                                nameField.setText("");
                                                surnameField.setText("");
                                                day.setSelectedIndex(0);
                                                month.setSelectedIndex(0);
                                                year.setSelectedIndex(0);
                                                drukarz.close();

                                            }  
                                        catch(IOException e)
                                            {
                                                System.out.println(e.getMessage());
                                            }
                        else
                            {
                            JOptionPane.showMessageDialog(rootPane, "WARNING! Fill in all fields.");
                            }
                                    }
                                });
     
    resave.addActionListener(new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(plik));
                
                Pracownik[] pracownik2 = Pracownik.odczytajZPliku(reader);
            
                for (int i = 0; i < pracownik2.length; i++)
                System.out.println(pracownik2[i]);
            
                reader.close();
                
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
            
            
            
        }
    });
   
    }
    
    JFrame mainWindow = new JFrame("Employees List");
    JMenuBar mainMenu = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenuItem close = new JMenuItem("Close");
    
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    
    JLabel pesel = new JLabel("PESEL: ");
    JLabel name = new JLabel("Name: ");
    JLabel surname = new JLabel("Surname: ");
    JLabel birthdate = new JLabel("Birthdate: ");
    
    JComboBox day = new JComboBox();
    JComboBox month = new JComboBox();
    JComboBox year = new JComboBox();
    
    JTextArea peselField = new JTextArea();
    JTextArea nameField = new JTextArea();
    JTextArea surnameField = new JTextArea();
    
    JButton save = new JButton("Add employee to file");
    JButton resave = new JButton("Nadpisz employee");
      
    File plik = new File("Employees List.txt");
 
    StringTokenizer strTok;
  
    public static void main(String[] args){
    new EmploeesList().setVisible(true);
    }
    
}

class Pracownik extends EmploeesList
    {
        Pracownik (String peselS, String nameS, String surnameS, String birthdateS)
        {
            this.peselS = peselS;
            this.nameS = nameS;
            this.surnameS = surnameS;
            this.birthdateS = birthdateS;
            
        }
        
        public static Pracownik[] odczytajZPliku(BufferedReader reader) throws IOException
    {
//        int dl = Integer.parseInt(reader.readLine());
        Pracownik[] pracownik = new Pracownik[4];
        String linia = reader.readLine();
        StringTokenizer strTok = new StringTokenizer(linia, ";");
        for (int i = 0; i < 4; i++)
        {
            String peselS = strTok.nextToken();
            String nameS = strTok.nextToken();
            String surnameS = strTok.nextToken();
            String birthdateS = strTok.nextToken();
            
            pracownik[i] = new Pracownik(peselS, nameS, surnameS, birthdateS);
        }
        
        return pracownik;
    }
        
    String peselS;
    String nameS;
    String surnameS;
    String birthdateS;
    }