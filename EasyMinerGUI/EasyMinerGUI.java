/* EasyMinerGUI.java @BitcoinJake09 9/24/2018
This is an opensource GUI to help creating mining start files like .bat

if you are compiling and running yourself you may need to change your java.policy to allow it to run to be able to midify files to save things. http://mindprod.com/jgloss/signedapplets.html#PROCESS
*/

import java.io.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;



public class EasyMinerGUI extends Applet implements ItemListener, ActionListener {
	
	public static void main(String[] args) throws IOException, InterruptedException {}	
	
	Button StartButton,SaveButton;
	TextField UserName,PassWord,Pool1,Pool2;
	TextArea ConsoleOut = new TextArea(50, 10);
	Choice MinerSelector, isDualMining = new Choice();
	public boolean dualMining = false;
	public String passPass;

/*FileDialog dialog = new FileDialog(new Frame(), "Select Directory", FileDialog.LOAD);
        dialog.show();
        String directory = dialog.getDirectory(); 
        System.out.println(directory);*/

   public void init() {
	this.setLayout(null);

        setSize(800, 640);
    	Dimension appletSize = this.getSize();
    	int appletHeight = appletSize.height;
    	int appletWidth = appletSize.width;

        StartButton = new Button("Start");
	StartButton.setBounds(5,35,50,30);

        SaveButton = new Button("Save");
	SaveButton.setBounds(5,65,50,30);

	MinerSelector = new Choice();
	MinerSelector.addItem("What Miner?");
	MinerSelector.addItem("Claymore");
	MinerSelector.addItem(" - ");
	MinerSelector.addItem(" - ");
	MinerSelector.setBounds(5,125,150,30);

	isDualMining = new Choice();
	isDualMining.setBounds(5,155,100,30);
	isDualMining.addItem("Dual Mine?");
	isDualMining.addItem("Decred/Siacoin/Lbry/Pascal");
//Decred/Siacoin/Lbry/Pascal

        Pool1 = new TextField("Pool",64);
	Pool1.setBounds(55,35,100,30);
	Pool2 = new TextField("Dual Mine Pool",64);
	Pool2.setBounds(5,185,100,30);
        UserName = new TextField("UserName",64);
	UserName.setBounds(55,65,100,30);
        PassWord = new TextField("PassWord",64);
	PassWord.setBounds(55,95,100,30);
        ConsoleOut.setBounds(160,35, 420,420);
        add(StartButton); 
        add(SaveButton); 
        add(MinerSelector); 
        add(isDualMining); 
        add(Pool1);
        add(Pool2);
        add(UserName); 
        add(PassWord);
        add(ConsoleOut);


	StartButton.addActionListener(this);
	SaveButton.addActionListener(this);
	MinerSelector.addItemListener(this);
	isDualMining.addItemListener(this); 
	isDualMining.setVisible(false);
	Pool2.setVisible(false);

	ConsoleOut.setText("This is EasyMinerGUI created by @BitcoinJake09\n" + "input your pool, username, and password to get started :D\n" +"currently save will create a .bat and .sh file to also run instead ;)\n" +"ENJOY! STAY CRYPTIC!\n");

   }
   public void itemStateChanged (ItemEvent e)
   {
	if (e.getSource() == MinerSelector) {
		System.out.println("MinerSelector Clicked");
ConsoleOut.append(MinerSelector.getSelectedItem() + " Selected\n");	
	if (MinerSelector.getSelectedItem().equals("Claymore")) {
		isDualMining.setVisible(true);
	}
	}//Decred/Siacoin/Lbry/Pascal
	if (e.getSource() == isDualMining) {
		System.out.println("isDualMining Clicked");	
	if (isDualMining.getSelectedItem().equals("Decred/Siacoin/Lbry/Pascal")) {
		ConsoleOut.append("Dual mine: " + isDualMining.getSelectedItem() + " Selected\n");
		Pool2.setVisible(true);
		dualMining = true;
	}
	if (!isDualMining.getSelectedItem().equals("Decred/Siacoin/Lbry/Pascal")) {
		ConsoleOut.append("Dual mine: OFF \n");
		Pool2.setVisible(false);
	}
	}
   }

   //@Override
   public void paint (Graphics g) {
     //repaint();
        g.drawString ("BitcoinJake09's EasyMinerGUI", 5, 15);
	

   }
   
   public void actionPerformed(ActionEvent e){
	if (e.getSource() == StartButton) {
		System.out.println("StartButton Clicked");
	}
	if (e.getSource() == SaveButton) {
		System.out.println("SaveButton Clicked");
		if (Pool1.getText().equals("Pool")) {
			ConsoleOut.append("**need to set a Pool**\n");
			return;
		}
		if ((Pool2.getText().equals("Dual Mine Pool"))&&(dualMining==true)) {
			ConsoleOut.append("**need to set a Dual Mine Pool**\n");
			return;
		}
		if (UserName.getText().equals("UserName")) {
			ConsoleOut.append("**need to set a UserName**\n");
			return;
		}
		if (PassWord.getText().equals("PassWord")) {
			ConsoleOut.append("**if no password set will default to: x **\n");
			passPass="x";
		}
	try {
	saveBatFile(MinerSelector.getSelectedItem(),Pool1.getText(), UserName.getText(), passPass);
	saveShFile(MinerSelector.getSelectedItem(),Pool1.getText(), UserName.getText(), passPass);
	//ConsoleOut.append("**SAVED as "+MinerSelector.getSelectedItem()+".bat(windows) & "+MinerSelector.getSelectedItem()+".sh(linux) :p**\n");
	} catch (Exception exc) {ConsoleOut.append("*NO PERMISSION TO SAVE FILES*"); System.out.println("error saving: "+exc);	}

	}
   }

   public void saveBatFile(String fName, String pools, String uNames, String pWords) throws IOException {
	File saveFile = new File(fName+".bat");
		saveFile.createNewFile();
		FileWriter writer = new FileWriter(saveFile);
		writer.write("setx GPU_FORCE_64BIT_PTR 0\n"); 
		writer.write("setx GPU_MAX_HEAP_SIZE 100\n"); 
		writer.write("setx GPU_USE_SYNC_OBJECTS 1\n"); 
		writer.write("setx GPU_MAX_ALLOC_PERCENT 100\n"); 
		writer.write("setx GPU_SINGLE_ALLOC_PERCENT 100\n"); 
		writer.write("EthDcrMiner64.exe -epool " + pools + " -ewal " + uNames +" -epsw "+pWords+"\n"); 
      		writer.flush();
      		writer.close();

		ConsoleOut.append("	\n"); 
		ConsoleOut.append("setx GPU_FORCE_64BIT_PTR 0\n"); 
		ConsoleOut.append("setx GPU_MAX_HEAP_SIZE 100\n"); 
		ConsoleOut.append("setx GPU_USE_SYNC_OBJECTS 1\n"); 
		ConsoleOut.append("setx GPU_MAX_ALLOC_PERCENT 100\n"); 
		ConsoleOut.append("setx GPU_SINGLE_ALLOC_PERCENT 100\n"); 
		ConsoleOut.append("EthDcrMiner64.exe -epool " + pools + " -ewal " + uNames +" -epsw "+pWords+"\n"); 
		ConsoleOut.append("File saved as: "+fName+".bat\n");
   }

   public void saveShFile(String fName, String pools, String uNames, String pWords) throws IOException {
	File saveFile = new File(fName+".sh");
		saveFile.createNewFile();
		FileWriter writer = new FileWriter(saveFile);

		writer.write("#!/bin/bash\n"); 
		writer.write("export GPU_FORCE_64BIT_PTR 0\n"); 
		writer.write("export GPU_MAX_HEAP_SIZE 100\n"); 
		writer.write("export GPU_USE_SYNC_OBJECTS 1\n"); 
		writer.write("export GPU_MAX_ALLOC_PERCENT 100\n"); 
		writer.write("export GPU_SINGLE_ALLOC_PERCENT 100\n"); 
		writer.write("./ethdcrminer64 -epool " + pools + " -ewal " + uNames +" -epsw "+pWords+"\n"); 
      		writer.flush();
      		writer.close();

		ConsoleOut.append("	\n");
		ConsoleOut.append("#!/bin/bash\n"); 
		ConsoleOut.append("export GPU_FORCE_64BIT_PTR 0\n"); 
		ConsoleOut.append("export GPU_MAX_HEAP_SIZE 100\n"); 
		ConsoleOut.append("export GPU_USE_SYNC_OBJECTS 1\n"); 
		ConsoleOut.append("export GPU_MAX_ALLOC_PERCENT 100\n"); 
		ConsoleOut.append("export GPU_SINGLE_ALLOC_PERCENT 100\n"); 
		ConsoleOut.append("./ethdcrminer64 -epool " + pools + " -ewal " + uNames +" -epsw "+pWords+"\n"); 
		ConsoleOut.append("File saved as: "+fName+".sh\n");
   }
}




//https://bitcointalk.org/index.php?topic=1433925.0
//EthDcrMiner64.exe -epool eu1.ethermine.org:4444 -ewal <Your_Ethereum_Address>.<RigName> -epsw x
/*-dpool    Decred/Siacoin/Lbry/Pascal pool address. Use "http://" prefix for HTTP pools, "stratum+tcp://" for Stratum pools. If prefix is missed, Stratum is assumed.
   Decred: both Stratum and HTTP are supported. Siacoin: both Stratum and HTTP are supported, though note that not all Stratum versions are supported currently. Lbry: only Stratum is supported.

-dwal   Your Decred/Siacoin/Lbry/Pascal wallet address or worker name, it depends on pool.

-dpsw    Password for Decred/Siacoin/Lbry/Pascal pool.

-di    GPU indexes, default is all available GPUs. For example, if you have four GPUs "-di 02" will enable only first and third GPUs (#0 and #2).
   You can also turn on/off cards in runtime with "0"..."9" keys and check current statistics with "s" key.
   For systems with more than 10 GPUs: use letters to specify indexes more than 9, for example, "a" means index 10, "b" means index 11, etc; also "a", "b", and "c" keys allow you to turn on/off GPU #10, #11 and #12 in runtime.

-mode   Select mining mode:
   "-mode 0" (default) means dual Ethereum + Decred/Siacoin/Lbry mining mode.
   "-mode 1" means Ethereum-only mining mode. You can set this mode for every card individually, for example, "-mode 1-02" will set mode "1" for first and third GPUs (#0 and #2).
   For systems with more than 10 GPUs: use letters to specify indexes more than 9, for example, "a" means index 10, "b" means index 11, etc.

-dcoin   select second coin to mine in dual mode. Possible options are "-dcoin dcr", "-dcoin sc", "-dcoin lbc", "-dcoin pasc", "-dcoin blake2s", "-dcoin keccak". Default value is "dcr".
*/

