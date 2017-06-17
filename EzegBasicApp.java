import java.io.*;
import java.io.File;
import java.util.*;

class EzegBasicApp {
	public static void main(String[] args) {
		//declarations
		int menuSelector = 0;
		String customPath = "";
		Scanner keyboard = new Scanner(System.in);
		CompileProgram program = new CompileProgram();
		
		while (menuSelector != 999) {
			//main menu
			System.out.println();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Welcome to EzegBasic! What kind of program would you like to run?");
			System.out.println("1. Premade programs");
			System.out.println("2. User-created");
			System.out.println("999 to end.");
			System.out.print("Enter selection: ");
			menuSelector = keyboard.nextInt();
			keyboard.nextLine();
			System.out.println();
			switch (menuSelector) {
				case 1:
					//premade programs
					System.out.println("Premade programs: ");
					System.out.println("~~~~~~~~~~~~~~~~~");
					System.out.println("1. calcAreaSquare");
					System.out.println("2. calcAreaRectangle");
					System.out.println("3. calcAreaTriangle");
					System.out.println("4. calcPythagreanTheorumHypotenuse");
					System.out.println("Press any other number to go back.");
					System.out.print("Enter selection: ");
					menuSelector = keyboard.nextInt();
					keyboard.nextLine();
					System.out.println();
					switch (menuSelector) {
						case 1:
							//call program 1
							program.compileAndRun("calcAreaSquare.txt");
							break;
						case 2:
							//2
							program.compileAndRun("calcAreaRectangle.txt");
							break;
						case 3:
							//3
							program.compileAndRun("calcAreaTriangle.txt");
							break;
						case 4:
							//4
							program.compileAndRun("calcPythagreanTheorumHypotenuse.txt");
						default:
							break;
					}
					break;
				case 2:
					//user programs
					System.out.println("Custom Program: Enter file pathname or 999 to quit.");
					customPath = keyboard.nextLine();
				//everything between these two - if test to see if file exists & can be read and then call compiler	
					try {
						File testRead = new File (customPath);
						if(testRead.canRead()) {
							System.out.println("File " + testRead.getPath() + " can be read");
							System.out.println();
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
							//compile and run customPath
							program.compileAndRun(customPath);
						}
						else {
							System.out.println("File " + testRead.getPath() + " can not be read");
						}
					} 
					catch (Exception e) {
						System.out.println(e.getCause());
						System.out.println(e.getMessage());
					}
					break;
				default:
					break;
			}
		System.out.println();	
		}
	}
}