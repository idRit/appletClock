import java.io.*;

class GetDay {
	
	public static void main(String args[]) {
		FileWriter fw = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean exit = false;
		
		String dd = "";
		String mm = "";
		String yy = "";
		
		int d = 0;
		int m = 0;
		int y = 0;
		
		int bin;
		
		try {
			
			File out = new File("getDay.txt");
			fw = new FileWriter(out);
			
			System.out.println("Enter Date in dd:mm:yy format in following order");
			System.out.println("After Enterring, Please Press Enter Button: ");
			
			do {
				try {
					exit = false;
					System.out.println("Enter Day:");
					dd = br.readLine();
					d = Integer.parseInt(dd);
					if(d > 31) {
						System.out.println("Enter Valid Day!");
						exit = true;
					}
				}catch(NumberFormatException nfe) {
					System.out.println("Enter an Integer Number!");
					exit = true;
				}
			} while(exit);
			
			do {
				try {
					exit = false;
					System.out.println("Enter Month:");
					mm = br.readLine();
					m = Integer.parseInt(mm);
				
					if(m >= 7) {
						bin = 1;
					}else {
						bin = 0;
					}
					if(((d == 31) && ((m % 2) == bin)) 
						|| ((d > 28) && (m == 2)) 
						|| (m > 12) 
						|| ((d > 30) && (bin == 1) )) {
							System.out.println("Enter Valid Month!");
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
					System.out.println("Enter Year:");
					yy = br.readLine();
					y = Integer.parseInt(yy);
					if(((d == 28) && (m == 2) && (y % 4 == 1)) || ((d > 27) && (y % 4 == 1) && (m == 2))){
						System.out.println("Enter Valid Year!");
						exit = true;
					}
				} catch(NumberFormatException nfe) {
					System.out.println("Enter an Integer Number!");
					exit = true;
				}
			} while(exit);	
					
				
			
			fw.write(dd+":"+mm+":"+yy);
		
			fw.flush();
			fw.close();
			
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}