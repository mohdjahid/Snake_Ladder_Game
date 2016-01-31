import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class SnakeLadder extends JFrame implements ActionListener
   {
	//Generated Number
	JLabel luser1,luser2;
	int cUser1[][],cUser2[][];

	//FLAGs for Start
	private boolean FLAG_1=false;
	private boolean FLAG_2=false;

	//Positions For Users
	private byte p1=0,p2=0;
	
	// User turn 1 for user1 and 2 for user2
	byte turn=1;
	
	int interSix=0;  // iteration of number 6;
	JDesktopPane jdp,jdp1,jdp2;
	JLabel l[][];
	JButton user1,user2;
	
	
	/*--------------Constructor starts------------*/
	SnakeLadder(){
		super("Snake and Ladder");
		jdp=new JDesktopPane();
		jdp1=new JDesktopPane();
		jdp2=new JDesktopPane();
		user1=new JButton("Throw Dice");
		user2=new JButton("Throw Dice");
		luser1=new JLabel("1");
		luser2=new JLabel("2");
		l=new JLabel[10][10];
		
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
				l[i][j]=new JLabel();
				
		user1.setBounds(150,570,100,30);
		user2.setBounds(550,570,100,30);
		luser1.setBounds(300,570,100,30);
		luser2.setBounds(700,570,100,30);

		user1.addActionListener(this);
		user2.addActionListener(this);
		
		add(user1); add(luser1);
		add(user2); add(luser2);

		Integer count=new Integer(100);
		
		for(int i=0,color=0;i<10;i++,color++)
			{
				for(int j=0;j<10;j++,color++){
					if(i%2==0){
						
						l[i][j].setIcon(new ImageIcon(count+".jpg"));
						count--;
					}
					else{
						l[i][j].setIcon(new ImageIcon(count+".jpg"));
						count++;
					}
					if(color%2==0)	
						l[i][j].setBackground(Color.RED);
					else
						l[i][j].setBackground(Color.BLUE);
					jdp.add(l[i][j]);
				}		
				
				if(i%2==0)
					count-=9;
				else
					count-=11;
			}
			
		setResizable(false);	
		setSize(800,700);
		setLayout(null);
		jdp.setLayout(new GridLayout(10,10));
		jdp.setSize(800,500); jdp.setVisible(true); 
		jdp1.setSize(400,200);           jdp1.setBackground(Color.GREEN);
		jdp1.setBounds(0,500,400,300);   jdp1.setVisible(true);   
		jdp2.setSize(400,200);           jdp2.setBackground(Color.PINK);
		jdp2.setBounds(400,500,400,300); jdp2.setVisible(true);
		add(jdp); add(jdp1); add(jdp2); 
		
		setVisible(true);		
	}
	/*--------------Constructor endss------------*/
	
	
	//Startup of the Program
	public static void main(String []args){
		new SnakeLadder();
	}
		
	//Receiver of Button Clicks
	public void actionPerformed(ActionEvent e)
	{
		Random r=new Random();
		Integer i=new Integer(0);
		i=r.nextInt(6)+1;
			
		/*-------user1 button action strats------*/
		if(e.getSource()==user1 && turn==1)
		{
			luser1.setText(i.toString());
			
			if(i==6 && !FLAG_1){
				FLAG_1=true;
				return;
			}
			if(i!=6 && FLAG_1){
					user1.setBackground(Color.GRAY);
					user2.setBackground(Color.GREEN);
					p1+=6*interSix;// interSix=0;
					turn=2;
			}
			
			if(FLAG_1){
				
				if(i==6)  // incerement interSix with each iteration of 6
				{
					interSix++;
					if(interSix==3)
						interSix=0;
				}
				
				else	// if  i not 6	then update
				{
					p1+=i; // update the position
					if(p1>100)
						p1-=6*interSix+i;
					if(p1==100)
						win("P1 wins");
					System.out.println("P1 "+p1);
					interSix=0;
					p1=checkSL(p1);
					updateGrid();
				}
			}
			else{	// it runs only before start
					user1.setBackground(Color.GRAY);
					user2.setBackground(Color.GREEN);
					turn=2;
			}
		}/*-------user1 button action ends------*/
		
		/*-------user2 button action strats------*/
		if(e.getSource()==user2 && turn==2)
		{
			luser2.setText(i.toString());
			
			if(i==6 && !FLAG_2){
				FLAG_2=true;
				return;
			}
			if(i!=6 && FLAG_2){
					user2.setBackground(Color.GRAY);
					user1.setBackground(Color.GREEN);
					p2+=6*interSix;// interSix=0;
					turn=1;
			}
			
			if(FLAG_2){
				if(i==6)  // incerement interSix with each iteration of 6
				{
					interSix++;
					if(interSix==3)
						interSix=0;
				}
				
				else	// if  i not 6	then update
				{		
					p2+=i; 
					if(p2>100)
						p2-=6*interSix+i;
					if(p2==100)
						win("P2 wins");
					System.out.println("P2 "+p2);
					interSix=0;
					p2=checkSL(p2);
					updateGrid();
				}
			}
			else{	// it runs only before start
					user2.setBackground(Color.GRAY);
					user1.setBackground(Color.GREEN);
					turn=1;
			}
		}/*-------user2 button action ends------*/
	}
	
	//Changes brought in the Grid
	void updateGrid()
	{
		refresh();
		
		
		//For User-1
		int rem=p1%10,quo=p1/10,row=0,col=0;
			/*------For col-----*/
			if(rem==0 || rem==1)
			{
				if(quo%2==0)
					col=0;
				else
					col=9;
			}
			else
			{
				if(quo%2==0)
					col=rem-1;
				else
					col=10-rem;
			}
			/*-------- For row---------*/
			if(rem==0)
				row=10-quo;
			else
				row=9-quo;
		if(p1>0)
			l[row][col].setIcon(new ImageIcon("user1.jpg")); 
		
		//For User-2
		rem=p2%10;
		quo=p2/10;
			/*------For col-----*/
			if(rem==0 || rem==1)
			{
				if(quo%2==0)
					col=0;
				else
					col=9;
			}
			else
			{
				if(quo%2==0)
					col=rem-1;
				else
					col=10-rem;
			}
			/*-------- For row---------*/
			if(rem==0)
				row=10-quo;
			else
				row=9-quo;
		
		if(p2>0)
			l[row][col].setIcon(new ImageIcon("user2.jpg")); 
	}
    
	void refresh()
	{
		Integer count=new Integer(100);
		
		for(int i=0,color=0;i<10;i++,color++)
			{
				for(int j=0;j<10;j++,color++){
					if(i%2==0){
						
						l[i][j].setIcon(new ImageIcon(count+".jpg"));
						count--;
					}
					else{
						l[i][j].setIcon(new ImageIcon(count+".jpg"));
						count++;
					}
				}
				
				if(i%2==0)
					count-=9;
				else
					count-=11;
			}
		
	}

	byte checkSL(byte p)
	{
		if(p==7) // Ladder
			return 26;
		 else if(p==9)
				return 55;
			else if(p==21)
					return 97;
				else if(p==36)
						return 64;
					else if(p==41)
						return 93;
							else if(p==44)
									return 65;
								else if(p==50)
										return 92;
									else if(p==61)
											return 98;
										else if(p==66)
												return 86;
											else if(p==67)
													return 88;  // Ladder
												 
			else if(p==99) // Snake
					return 10;
				else if(p==95)
						return 65;
					else if(p==91)
						return 51;
							else if(p==73)
									return 3;
								else if(p==62)
										return 22;
									else if(p==58)
											return 24;
										else if(p==52)
												return 11;
											else if(p==46)
													return 16;  
												else if(p==43)
														return 17;
													else if(p==32)
															return 5; // Snake
													else
														return p;     // If not Snake r Ladder
													
		
	 }

	public void win(String msg)
	 {
			JOptionPane.showMessageDialog(this,msg);
			try
			{
				Thread.sleep(3000);
			}
			catch (InterruptedException e)
			{
			}
			System.exit(0);

	 }

	
}



