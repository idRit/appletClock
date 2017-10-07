import java.io.*;

class GetTime {
	
	public static void main(String args[]) {
		FileWriter fw = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean exit = false;
		
		String ss = "";
		String hh = "";
		String mm = "";
		
		int h;
		int m;
		int s;
		
		try {
			
			File out = new File("getTime.txt");
			fw = new FileWriter(out);
			
			System.out.println("Enter Time in 24 hour format");
			System.out.println("After Enterring, Please Press Enter Button: ");
			
			do {
				try {
					exit = false;
					System.out.println("Enter Hours:");
					hh = br.readLine();
					h = Integer.parseInt(hh);
					if(h >= 24) {
						System.out.println("Enter Valid Hours!");
						exit = true;
					}
				} catch(NumberFormatException nfe) {
					System.out.println("Enter an Integer Number!");
					exit = true;
				}			
			} while(exit);
			
			do {
				try {
					exit = false;
					System.out.println("Enter Minutes:");
					mm = br.readLine();
					m = Integer.parseInt(mm);
					if(m >= 60) {
						System.out.println("Enter Valid Minutes!");
						exit = true;
					}
				} catch(NumberFormatException nfe) {
					System.out.println("Enter an Integer Number!");
					exit = true;
				}
			} while(exit);
				
			do {
				try {
					exit = false;
					System.out.println("Enter Seconds:");
					ss = br.readLine();
					s = Integer.parseInt(ss);
					if(s >= 60) {
						System.out.println("Enter Valid Seconds!");
						exit = true;
					}
				} catch(NumberFormatException nfe) {
					System.out.println("Enter an Integer Number!");
					exit = true;
				}	
			} while(exit);
			
			fw.write(hh+":"+mm+":"+ss);
		
			fw.flush();
			fw.close();
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}