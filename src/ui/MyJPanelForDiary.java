package ui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public class MyJPanelForDiary extends JPanel{
	List<MyJButtonForDiary> listButton;
	private List<Map<String,String>> list;
	public List<Map<String, String>> getList() {
		return list;
	}

	public void setList(List<Map<String, String>> list) {
		this.list = list;
	}

	
	
	private int count;
	private int buttonWidth;
	private int buttonHeight;
	private int d1,d2;
	Point startPos;
	
	public MyJPanelForDiary() {
		super();
		this.count=0;
		this.listButton = new ArrayList<>();
		this.d1=10;
		this.d2=11;
		this.buttonWidth = 209;
		this.buttonHeight = 47;
		this.startPos = new Point(d1,d2);

		this.list=null;
	}
	
	public MyJPanelForDiary(List<Map<String,String>> list) {
		super();
		this.count=0;
		this.listButton = new ArrayList<>();
		this.d1=10;
		this.d2=11;
		this.buttonWidth = 209;
		this.buttonHeight = 47;
		this.startPos = new Point(d1,d2);

		this.list = list;
//		makeButtonList();
		
	}
	
	public void addButton(MyJButtonForDiary mjb) {
		mjb.setindex(count);
		int x = d1;
		int y = d2+buttonHeight*count;
		Point p = new Point(x,y);
		
		mjb.setorigin(p);
		mjb.setBounds(x,y,buttonWidth,buttonHeight);
		
		this.add(mjb);
		listButton.add(mjb);

		
		mjb.setisInPanel(true);
		mjb.setMap(list.get(count));
		++count;
		
		//System.out.println(this.getPreferredSize());
		if(this.count*this.buttonHeight+11>this.getPreferredSize().height) {
			this.setPreferredSize(new Dimension(229,this.getPreferredSize().height+this.buttonHeight));
		}
		
		
	}
	
	public void buttonBack(MyJButtonForDiary mjb) {
		mjb.setisInPanel(true);
		mjb.setLocation(mjb.getorigin());
		this.add(mjb);
		this.getParent().revalidate();
		this.getParent().repaint();
	}
	
	public Map<String,String> buttonRemove(int index) {
		MyJButtonForDiary tmp;
		Point p;
		this.remove(listButton.remove(index));
		Map<String,String> map = list.remove(index);
		--count;
		int x;
		int y;
		for(int i=index;i<listButton.size();++i) {
			tmp = listButton.get(i);
			tmp.setindex(tmp.getindex()-1);
			x=d1;
			y=d2+this.buttonHeight*i;
			p = new Point(x,y);
			tmp.setLocation(p);
		}
		this.setPreferredSize(new Dimension(229,this.getPreferredSize().height-this.buttonHeight));
		
		this.getParent().revalidate();
		this.getParent().repaint();
		
		return map;
	}
	
	public void refresh() {
		this.removeAll();
		this.count=0;
		this.setPreferredSize(new Dimension(229, 425));
		this.listButton.clear();
		this.makeButtonList();
		this.getParent().revalidate();
		this.getParent().repaint();
	}
	/*
	public MyJPanelForDiary[] getMjpGroup() {
		return mjpGroup;
	}

	public void setMjpGroup(MyJPanelForDiary[] mjpGroup) {
		this.mjpGroup = mjpGroup;
	}
*/
	public void makeButtonList() {
		MyJButtonForDiary mjb;
		String str_1;
		for(Map<String,String> tmp : list) {
			//str_1="";
			
//			//Date date = new Date();
//			str_1 += " "+tmp.get("title");
//			str_1 += " "+tmp.get("place");
//			str_1 +=" "+ tmp.get("event");
			//str_1 +=" " + tmp.get("");
			
			str_1 = tmp.get("happening");
			for(int i=0;i<20;++i)
				str_1 += " ";
			
			mjb = new MyJButtonForDiary(this);
			mjb.setText("<html><b>"+tmp.get("date").substring(0,10)+" </b> "+tmp.get("weather"));
			mjb.setToolTipText("<html><b>place:</b> "+tmp.get("about_place")+"<br><b>people:</b> "
								+tmp.get("about_people")+"<br><b>happening: </b>"+str_1.substring(0,20)+"...");
			mjb.setFocusPainted(false);
			this.addButton(mjb);
		}
	}
	
}

































