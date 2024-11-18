package ultimate_tic_tac_toe;



import javax.swing.JButton;

public class btn_tile extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int i,j;
	int pid;
	boolean click = true;


	
	

	public btn_tile(int i, int j,int pid) 
	{
		this.i = i;
		this.j = j;
		this.pid = pid;	
	}
	

	public boolean isClick() {
		return click;
	}


	public void setClick(boolean click) {
		this.click = click;
	}


	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	public void setPid(int pid)
	{
		this.pid = pid;
	}
	public int getSid()
	{
		return this.pid;
	}
	
}