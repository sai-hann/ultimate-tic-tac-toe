package ultimate_tic_tac_toe;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;



public class main_frame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane,board;
	private JLabel lbl_Title,lbl_indicate;
	private Random ran;
	private player current_player;
	private match_state current_state;
	private final int rows= 3;
	private final int cols = 3;
	private seed[][] s1= new seed[rows][cols];
	private seed[][] s2= new seed[rows][cols];
	private seed[][] s3= new seed[rows][cols];
	private seed[][] s4= new seed[rows][cols];
	private seed[][] s5= new seed[rows][cols];
	private seed[][] s6= new seed[rows][cols];
	private seed[][] s7= new seed[rows][cols];
	private seed[][] s8= new seed[rows][cols];
	private seed[][] s9= new seed[rows][cols];
	private seed[][][] seed_gp = {s1,s2,s3,s4,s5,s6,s7,s8,s9};
	private seed[][] s_final= new seed[rows][cols];
	
	private JButton btn_replay;
	private final Color panel_background= Color.decode("#3f3f3f");
	private final Color font_color = Color.decode("#F8F9FA");
	private final Color big_tile_color = Color.decode("#121212");
	private final Color tile_color = Color.decode("#292929");
	private final Color tile_win = Color.decode("#507D46");
	
	private final int btn_width = 60;
	private final int btn_height = 60;
	private final int btn_w_increase = 10;
	private final int btn_h_increase = 10;
	
	private final int tile_x_start =32;
	private final int tile_y_start =0;
	private final int tile_width= 200;
	private final int tile_height = 200;
	private final int tile_w_increase = 15;
	private final int tile_h_increase = 15;
	
	private JPanel[] panel_gp = new JPanel[9];
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main_frame frame = new main_frame();
					frame.setIconImage(new ImageIcon(main_frame.class.getResource("logo.png")).getImage());
					frame.setTitle("Ultimate Tik Tak Toe");
//					frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main_frame() 
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(750, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(panel_background);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		header_panel = new JPanel();
//		header_panel.setBounds(0,0,745,35);
//		header_panel.setLayout(null);
//		header_panel.setComponentZOrder(board, btn_h_increase);
//		contentPane.add(header_panel);
		
//		header_panel.setBackground(Color.TRANSLUCENT);
		
		lbl_Title = new JLabel("Ultimate tic tac toe");
		lbl_Title.setForeground(font_color);
		lbl_Title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_Title.setBounds(272, 11, 196, 23);
//		contentPane.setComponentZOrder(lbl_Title, 0);
		contentPane.add(lbl_Title);
		
		
		lbl_indicate = new JLabel("Turn: ");
		lbl_indicate.setForeground(font_color);
		lbl_indicate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_indicate.setBounds(47, 13, 119, 19);
//		contentPane.setComponentZOrder(lbl_indicate, 0);
		contentPane.add(lbl_indicate);
		
		board = new JPanel();
		board.setBounds(21, 43, 699, 677);
		board.setBackground(panel_background);
		contentPane.add(board);
		board.setLayout(null);
		
		btn_replay = new JButton("Replay");
		btn_replay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_replay.setBounds(559, 10, 119, 25);
		btn_replay.setForeground(tile_color);
		btn_replay.setBackground(font_color);
		btn_replay.setFocusable(false);
		btn_replay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				main_frame m = new main_frame();
				m.setVisible(true);
				m.setLocationRelativeTo(null);
				m.setTitle("Ultimate Tik Tak Toe");
				
				// TODO Auto-generated method stub
				
			}
		});
		contentPane.add(btn_replay);
		
		
		

		start_game();
	
	}
	public void announce()
	{
		
		if(current_state==match_state.Playing)
		{
			if(current_player==player.player_O)
			{
				lbl_indicate.setText("Turn: O");
				
				
			}
			else
			{
				lbl_indicate.setText("Turn: X");
				
				
			}
		}
		else
		{
			if(current_state==match_state.O_win)
			{
				lbl_indicate.setText("O Wins");
			}
			else if(current_state ==match_state.X_win)
			{
				lbl_indicate.setText("X Wins");
			}
			else
			{
				lbl_indicate.setText("Draw");
			}
			
			
			
		}
		
		
	}
	
	public void disable_btn()
	{
		for(Component c: board.getComponents())
		{
			if(c instanceof JPanel)
			{
				JPanel p = (JPanel)c;
				for(Component sc: p.getComponents())
				{
					if(sc instanceof JButton)
					{
						sc.setEnabled(false);
					}
				}
			}
		}
	}
	
	public void btn_change(match_state m,int pid)
	{
		JPanel p = panel_gp[pid];		
		String show="";
		
		if(m==match_state.O_win)
		{
			show ="O";
		}
		else if(m==match_state.X_win)
		{
			show="X";
		
		}
		else if(m==match_state.Draw)
		{
			show="=";
		}
		p.removeAll();
		
		Rectangle r = p.getBounds();
		JButton b = new JButton(show);
		b.setBackground(big_tile_color);
		b.setForeground(font_color);
		b.setFocusable(false);
		b.setFont(new Font("Tahoma", Font.PLAIN, 80));
		b.setBounds(r);
		board.remove(p);
//		b.setLocation(0,0);
		board.add(b, pid);
//		System.out.println(board.getComponent(pid));
//		p.add(b);
		
		
		
		board.repaint();
		
//		lbl_Title.repaint();
//		lbl_indicate.repaint();
		
	}
	
	public void start_game()
	{
		
		//get starting player
		current_state = match_state.Playing;
		ran = new Random();
		int a = ran.nextInt(0,2);
		if(a==0)
		{
			current_player = player.player_O;
			
			
		}
		else
		{
			current_player = player.player_X;
		}
		announce();
		
		
		int p_x=tile_x_start;int p_y=tile_y_start;
		int count =0;
		for(int pw=0; pw<3; pw++) //panel_width
		{
			for(int ph=0; ph<3; ph++) //panel_height
			{
				s_final[pw][ph]=seed.No_Seed;
				JPanel pan = new JPanel();
				pan.setLayout(null);
				pan.setBackground(big_tile_color);
				pan.setBounds(p_x,p_y,tile_width,tile_height);
				board.add(pan);
				panel_gp[count] =pan;
				count++;
//				System.out.println(p_x+" "+p_y);
				p_x+=tile_width+tile_w_increase;
				
			}
			p_x=tile_x_start;
			p_y+= tile_height+tile_h_increase;
			
			
		}
		
		for(int p=0; p<panel_gp.length; p++)
		{
			
			int x=0; int y=0;
			for(int i=0; i<3; i++)
			{
				
				
				for(int j=0; j<3; j++)
				{
					
					seed_gp[p][i][j]=seed.No_Seed;
					btn_tile b = new btn_tile(i,j,p);
					b.setBackground(tile_color);
					b.setForeground(font_color);
					b.setFont(new Font("Tahoma", Font.PLAIN, 30));
					b.setFocusable(false);
//					b.setText("O");
					b.setBounds(x,y,btn_width,btn_height);
					ActionListener ml = new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) 
						{
							
							
							
							if(seed_gp[b.pid][b.i][b.j]!=seed.No_Seed)
							{
								JOptionPane.showMessageDialog(null, "You cannot place in the already placed seed.");
								return;
							}
							
							
							if(current_player==player.player_O)
							{
								b.setText("O");
								seed_gp[b.pid][b.i][b.j]=seed.O;
								
//								current_player=player.player_X;
							}
							else
							{
								b.setText("X");
								seed_gp[b.pid][b.i][b.j]=seed.X;
//								current_player=player.player_O;
							}
							
							
							
							int pid = b.pid;
//							seed[][] added_seed = seed_gp[pid];
							match_state m = game_decide(pid);
							if(m==match_state.O_win||m==match_state.X_win||m==match_state.Draw)
							{
								int final_i = b.pid/3;
								int final_j = b.pid%3;
								if(m==match_state.O_win)
								{
									s_final[final_i][final_j]= seed.O;
								}
								if(m==match_state.X_win)
								{
									s_final[final_i][final_j]=seed.X;
								}
								if(m==match_state.Draw)
								{
									s_final[final_i][final_j]=seed.Draw;
								}
								btn_change(m, pid);
								current_state= game_decide(-1);
								
							}
							announce();
							if(current_state!= match_state.Playing)
							{

								disable_btn();
								return;
							}
							//player_switch
							if(current_player==player.player_O)
							{
						
								current_player=player.player_X;
							}
							else
							{
								current_player=player.player_O;
							}
							announce();
							
							//get jbutton position to calculate next allowed tile
							
							
							if(s_final[b.i][b.j]==seed.No_Seed)
							{
								int next_pid=b.i*3+b.j;
								for(int pi=0; pi<panel_gp.length; pi++)
									{
										int fi = pi/3;
										int fj = pi%3;
										if(s_final[fi][fj]==seed.No_Seed)
										{
											JPanel p=panel_gp[pi];
											if(next_pid == pi)
											{
												for(Component c: p.getComponents())
												{
													if(c instanceof JButton)
													{
														c.setBackground(Color.decode("#2F2636"));
														c.setEnabled(true);
													}
												}
											}
											else
											{
												for(Component c: p.getComponents())
												{
													if(c instanceof JButton)
													{
														c.setEnabled(false);
														c.setBackground(tile_color);
													}
												}
											}
										}
										
										
									}
							}
							else
							{
								for(int pi=0; pi<panel_gp.length; pi++)
								{
									int fi= pi/3;
									int fj = pi%3;
									if(s_final[fi][fj]==seed.No_Seed)
									{
										for(Component c: panel_gp[pi].getComponents())
										{
											if(c instanceof JButton)
											{
												c.setBackground(Color.decode("#2F2636"));
												c.setEnabled(true);
											}
										}
									}
								}
							}
							
							
//							 
							
							
//							System.out.println(pid);
							
//							System.out.println(b.pid+""+b.i+b.j);
							
							
							
						}
					};
					
					b.addActionListener(ml);
					panel_gp[p].add(b);
                    x+=btn_width+btn_w_increase;
					
					
				}
				x=0;
				y+=btn_height+btn_h_increase;
				
				
				
			}
//			System.out.println(count);
		}
	}
	
	private match_state game_decide(int pid)
	{
		seed[][] seed_group;
		JPanel p;
		if(pid==-1)
		{
			seed_group = s_final;
			p = board;
		}
		else
		{
			seed_group = seed_gp[pid];
			p = panel_gp[pid];
		}
		
		for(int i=0; i<rows; i++)
		{
		
			
			if(seed_group[i][0]==seed_group[i][1]&&seed_group[i][0]==seed_group[i][2]&&seed_group[i][0]!=seed.No_Seed)
			{
				
				
				p.getComponent(3*i).setBackground(tile_win);
				p.getComponent(3*i+1).setBackground(tile_win);
				p.getComponent(3*i+2).setBackground(tile_win);
				return (current_player==player.player_O)?match_state.O_win:match_state.X_win;
			}
		}
		
		//vertical check
		for(int j=0; j<cols; j++)
		{
			if(seed_group[0][j]==seed_group[1][j]&&seed_group[0][j]==seed_group[2][j]&&seed_group[0][j]!=seed.No_Seed)
			{
				p.getComponent(j).setBackground(tile_win);
				p.getComponent(j+3).setBackground(tile_win);
				p.getComponent(j+6).setBackground(tile_win);
				return (current_player==player.player_O)?match_state.O_win:match_state.X_win;
			}

		}
		
		//left diagonal
		if(seed_group[0][0]==seed_group[1][1]&& seed_group[0][0]==seed_group[2][2]&&seed_group[0][0]!=seed.No_Seed)
		{
			p.getComponent(4).setBackground(tile_win);
			p.getComponent(0).setBackground(tile_win);
			p.getComponent(8).setBackground(tile_win);
			
			return (current_player==player.player_O)?match_state.O_win:match_state.X_win;
			
		}
		//right diagonal
		if(seed_group[0][2]==seed_group[1][1]&& seed_group[0][2]==seed_group[2][0]&&seed_group[0][2]!=seed.No_Seed)
		{
			p.getComponent(2).setBackground(tile_win);
			p.getComponent(4).setBackground(tile_win);
			p.getComponent(6).setBackground(tile_win);
			return (current_player==player.player_O)?match_state.O_win:match_state.X_win;
		}
		
		for(int i=0; i<rows;i++)
		{
			for(int j=0; j<cols; j++)
			{
				if(seed_group[i][j]==seed.No_Seed)
				{
					return match_state.Playing;
				}
			}
		}
		
		return match_state.Draw;
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}
