/*
<applet code = "TestClock.class" 
 width = "500"
 height = "500">

</applet>
*/

import java.io.*;
import java.awt.*;
import java.applet.*;

public class TestClock extends Applet implements Runnable {
	
	String recievedTime;
	String recievedDay;
	
	int startSec;
	int startMin;
	int startHr;
	
	int startDay;
	int startMon;
	int startYear;
	
	Color big;
	Color med;
	Color small;
	Color back;
	Color bright;
	
	Font f1, f2;
	
	public void run() {
		try {
			Thread.sleep(1000);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}
	
	public void init() {
		FileReader fr1 = null;
		FileReader fr2 = null;
		try {
			fr1 = new FileReader("getTime.txt");
			BufferedReader br1 = new BufferedReader(fr1);
			
			fr2 = new FileReader("getDay.txt");
			BufferedReader br2 = new BufferedReader(fr2);
			
			String s1, s2;
		
			while((s1 = br1.readLine()) != null) {
				recievedTime = s1;
			}
			
			while((s2 = br2.readLine()) != null) {
				recievedDay = s2;
			}
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}finally {
			try {
				fr1.close();
				fr2.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		
		String[] stringArray1 = recievedTime.split(":");
		int timeDivider1[] = new int[recievedTime.length()];
			
		for (int i = 0; i < stringArray1.length; i++) {
			String numberAsString = stringArray1[i]; 
			timeDivider1[i] = Integer.parseInt(numberAsString);
		}
		
		String[] stringArray2 = recievedDay.split(":");
		int timeDivider2[] = new int[recievedDay.length()];
		
		for (int i = 0; i < stringArray2.length; i++) {
			String numberAsString = stringArray2[i]; 
			timeDivider2[i] = Integer.parseInt(numberAsString);
		}
		
		startSec = timeDivider1[2];
		startMin = timeDivider1[1];
		startHr = timeDivider1[0];
		
		startDay = timeDivider2[0];
		startMon = timeDivider2[1];
		startYear = timeDivider2[2];
		
		f1 = new Font("Times New Roman", Font.PLAIN, 19);
		f2 = new Font("Times New Roman", Font.BOLD, 15);
		
		big = new Color(155, 89, 182); //min
		med = new Color(44, 62, 80); //background
		small = new Color(211, 84, 0); //hour
		bright = new Color(241, 196, 15); //second
	}
	
	public void paint(Graphics g) {
		int hr = startHr;
		int min = startMin;
		int sec = startSec;
		
		int day = startDay;
		int mon = startMon;
		int year = startYear;
		
		int augTag0 = 0;
		int augTag1 = 1;
		
		int yDec = 20;
		
		Dimension d = getSize();
		
		int sw;
		
		while(1 < 2) {
			
			g.setColor(med);
			g.fillRect(0, 0, d.width, d.height);
			
			int h1 = convertTo12Hour(hr);
			String state = AmOrPm(hr);
			
			g.setColor(bright);
			sw = returnSweepAngle60(sec);
			g.fillArc(125, 125 - yDec, 240, 240, 90, sw); //for sec
			
			g.setColor(med);
			g.fillArc(135, 135 - yDec, 220, 220, 90, sw+3);
			
			g.setColor(big);
			sw = returnSweepAngle60(min);
			g.fillArc(145, 145 - yDec, 200, 200, 90, sw); //for min
			
			g.setColor(med);
			g.fillArc(155, 155 - yDec, 180, 180, 90, sw+6);
			
			g.setColor(small);
			sw = returnSweepAngle12(hr);
			g.fillArc(165, 165 - yDec, 160, 160, 90, sw); //for hr 
			
			g.setColor(med);
			g.fillArc(170, 175 - yDec, 149, 149, 90, sw+5);
			g.fillOval(185, 185 - yDec, 119, 119);
			
			g.setColor(big);
			g.setFont(f1);
			g.drawString(h1 + ":" + min + ":" + sec + " " + state, 205, 230);
			
			g.setColor(small);
			g.setFont(f2);
			g.drawString(day + " / " + mon + " / " + year, 220, 243);
	
			run();
			sec++;
			if(sec == 60) {
				sec = 0;
				min++;
			}
			if(min == 60) {
				min = 0;
				hr++;
			}
			if(hr == 24) {
				hr = 0;
				min = 0;
				sec = 0;
				day++;
			}
			if(!(mon >= 7)){
				augTag1 = 0;
				augTag0 = 1;
			}else {
				augTag0 = 0;
				augTag1 = 1;
			}
			if(((day == 31) && (mon % 2 == augTag1))  
				|| ((day == 30) && (mon % 2 == augTag0) && (mon != 2)) 
				|| ((mon == 2) && (day > 28) && ((year % 4) == 0)) 
				|| ((mon == 2) && (day > 27) && ((year % 4) == 1))) {
					mon++;
					day = 1;
			}
			if((mon == 12) && (day == 31) && (hr == 23) && (min == 60) && (sec == 60)) {
				year++;
				mon = 1;
				day = 1;
				hr = 0;
				min = 0;
				sec = 0;
			}
		}
			
	}
	
	int returnSweepAngle60(int actualElement) {
		return(-1*(actualElement*6));
	}
	
	int returnSweepAngle12(int actualElement) {
		int j = 0;
		
		if((actualElement >= 1) && (actualElement < 12)) {
			return(-1*(actualElement*30));
		}
		if((actualElement >= 12) && (actualElement < 24)){
			for(int i = 13; i < 24; i++) {
				j++;
				if(i == actualElement){
					return(-1*(j*30));
				}
			}
		} return(0);
	}
	
	int convertTo12Hour(int hour24) {
		if(hour24 == 13){ return(1); }
		if(hour24 == 14){ return(2); }
		if(hour24 == 15){ return(3); }
		if(hour24 == 16){ return(4); }
		if(hour24 == 17){ return(5); }
		if(hour24 == 18){ return(6); }
		if(hour24 == 19){ return(7); }
		if(hour24 == 20){ return(8); }
		if(hour24 == 21){ return(9); }
		if(hour24 == 22){ return(10); }
		if(hour24 == 23){ return(11); }
		if(hour24 == 00){ return(12); }
		if((hour24 <= 12) && (hour24 > 00)) {
			return(hour24);
		}
		return(0);
	}	
	
	String AmOrPm(int hour24) {
		if((hour24 > 11) && (hour24 < 24)) {
			return("PM");
		}else{
			return("AM");
		}
	}
}