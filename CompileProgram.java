//created by EternalGoat (Conner Cox), last revision on 6/16/17
//This is the EzegCompiler. Refer to the file labeled EzegReadME.txt for explinations, or start the application with EzegBasicApp.
import java.nio.file.*;;
import java.nio.*;
import java.io.*;
import java.util.*;

class CompileProgram {
	//uncomment this main for bug testing
	/*
	public static void main(String[] args) {
		CompileProgram program = new CompileProgram();
		//                      V V V  replace this path for bug testing
		program.compileAndRun("test.txt");
	}
	*/
	
	public void compileAndRun(String pathName) {
		//declarations
		  //path and file
		Path file = Paths.get(pathName);
		file.toAbsolutePath();
		  //strings and operators
		String inputS = "";
		String op = "";
		boolean opPresent = false;
		String mathS = "";
		String sign = "";
		char tempChar;
		  //variables
		Double[] varList = new Double[10];
		for (int i = 0; i < 10; i++) {
			varList[i] = 0.0;
		}
		int errorLineCounter = 1;
		  //scanner
		Scanner keyboard = new Scanner(System.in);
			
		//while loop gets a line from program each time it loops - compiler handles one line at a time
		try {
			//reader declaration
			InputStream input = null;
			input = Files.newInputStream(file);
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			inputS = reader.readLine();
			while (inputS != null) {
					//op seperator
					if (inputS.substring(0, 1).equals("{")) {
						opPresent = true;
						for (int x = 0, y = 1; !inputS.substring(x, y).equals("}"); x++, y++) {
							op = op + inputS.substring(x, y);
						}
						op += "}";
						mathS = inputS.replace(op, "");
						if (mathS.substring(0, 1).equals(" ")) {
							mathS = mathS.replaceFirst(" ", "");
						}
					}
					//operatior functions
					if (opPresent) {
						switch (op) {
							case "{=}":
								mathS = mathS.replace(" ", "");
								varList[returnVarInt(mathS.substring(0, 1))] = Double.parseDouble(mathS.substring(2));
								break;
							case "{print}":
								if (mathS.contains("#")) {
									String var = mathS.substring(mathS.indexOf('#') + 1, mathS.indexOf('#') + 2);
									String place = "#" + var;
									mathS = mathS.replace(place, Double.toString(varList[returnVarInt(var)]));
								}
								System.out.print(mathS);
								break;
							case "{println}":
								if (mathS.contains("#")) {
									String var = mathS.substring(mathS.indexOf('#') + 1, mathS.indexOf('#') + 2);
									String place = "#" + var;
									mathS = mathS.replace(place, Double.toString(varList[returnVarInt(var)]));
								}
								System.out.println(mathS);
								break;
							case "{get}":
								varList[returnVarInt(mathS.substring(0, 1))] = keyboard.nextDouble();
								break;
							case "{comm}":
								//do absolutely nothing
								break;
							default:
								break;
						}
					} 
					else {
						mathS = inputS.replace(" ", "");
						sign = mathS.substring(1, 2);
						//nothing will work below if a solid num is present. Only works with variables
						switch (sign) {
							case "+":
								varList[returnVarInt(mathS.substring(4, 5))] = varList[returnVarInt(mathS.substring(0, 1))] + varList[returnVarInt(mathS.substring(2, 3))];
								break;
							case "-":
								varList[returnVarInt(mathS.substring(4, 5))] = varList[returnVarInt(mathS.substring(0, 1))] - varList[returnVarInt(mathS.substring(2, 3))];
								break;
							case "/":
								varList[returnVarInt(mathS.substring(4, 5))] = varList[returnVarInt(mathS.substring(0, 1))] / varList[returnVarInt(mathS.substring(2, 3))];
								//^^^^ this does not work if division by a solid num not variable occurs - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
								break;
							case "*":
								varList[returnVarInt(mathS.substring(4, 5))] = varList[returnVarInt(mathS.substring(0, 1))] * varList[returnVarInt(mathS.substring(2, 3))];
								break;
							case "^":
								varList[returnVarInt(mathS.substring(0, 1))] = Math.sqrt(varList[returnVarInt(mathS.substring(0, 1))]);
								break;
							default:
								break;
						}
					}
				errorLineCounter++;
				op = "";
				mathS = "";
				inputS = reader.readLine();
				opPresent = false;
				//System.out.println("\""+inputS+"\"");
				} 
			//clear variable list
			for (int i = 0; i < 10; i++) {
				varList[i] = null;
			}
		}
		catch (Exception e) {
			System.out.println("In the custom program an error occured on line " + errorLineCounter);
			System.out.println(e);
		}
	}
	int returnVarInt(String letter) {
		if (letter.equals("a")) {
			return 0;
		} else if (letter.equals("b")) {
			return 1;
		} else if (letter.equals("c")) {
			return 2;
		} else if (letter.equals("d")) {
			return 3;
		} else if (letter.equals("e")) {
			return 4;
		} else if (letter.equals("f")) {
			return 5;
		} else if (letter.equals("g")) {
			return 6;
		} else if (letter.equals("h")) {
			return 7;
		} else if (letter.equals("i")) {
			return 8;
		}
		else {
			return 9;
		}
	}
}