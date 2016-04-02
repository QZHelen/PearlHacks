package readingFromInternet;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Reading1 {
	public static void main(String[] args) throws Exception {

		/* Part 1: filter for foods in rams */
		download("ramsfile.txt","http://menus.dining.unc.edu/rams-head-dining-hall.csv");
		filter("ramsfile.txt");
		
		/* Part 2: filter for foods in lenoir */
		String lenFileName = "lenoirfile.txt";
		String lenoirlink = "http://menus.dining.unc.edu/top-of-lenoir.csv";
		download(lenFileName, lenoirlink);
		filter(lenFileName);
	}
	
	public static void filter(String fileName){
		// set up the file we want to scan
		// believe there would be a better way for the downloading and reading as a whole
		Scanner myScanner = null;
		try {
			myScanner = new Scanner(new File(fileName));
			myScanner.useDelimiter(",");
			
//	TODO: implement filter here, first delete the two testing line below
			for(int i = 0; i < 10; i++){System.out.print(myScanner.next() + " ");}
			System.out.println(fileName);
			
		} catch(FileNotFoundException e){
			System.out.println("fileNotFound");
		} catch (Exception e) {
			System.out.println("no such file");
			System.out.println("Error while trying to read and filter a file");
		}
	}
	
	public static void download(String fileName, String urlName){
		try{
			String ramsFileName = fileName; // The file that will be saved on your computer
			URL ramslink = new URL(urlName);
			// The file that you want to download

			// Code to download
			InputStream in = new BufferedInputStream(ramslink.openStream());
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			while (-1 != (n = in.read(buf))) {
				out.write(buf, 0, n);
			}
			out.close();	
			in.close();
			byte[] response = out.toByteArray();

			FileOutputStream fos = new FileOutputStream(ramsFileName);
			fos.write(response);
			fos.close();
		} catch(Exception e){
			System.out.println("Error in downloading");
		}
		
	}
}