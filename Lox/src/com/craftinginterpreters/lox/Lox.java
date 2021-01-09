package com.craftinginterpreters.lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Lox {
	public static void main(String[] args) throws IOException {
		if (args.length>1) {
			System.out.println("Usage: jlox [script]");
		}else if (args.length==1) {
			runFile(args[0]);
		}else {
			runPrompt();
		}
	}
	
	private static void runFile(String path) throws IOException {
		//this takes in a path as an argument and then executes that file
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		run(new String(bytes, Charset.defaultCharset()));
	}//end runfile
	
	private static void runPrompt() throws IOException {
		//this is the interpreter. like a repl? reads in user input and then executes code line by line
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		//this just keeps on trucking I think?
		for (;;) {
			System.out.print("> ");
			String line = reader.readLine();
			//user wants to stop doing stuff
			if (line == null) break;
			run(line);
		}
	}//end run prompt
	
	private static void run(String source){
	    Scanner scanner = new Scanner(source);
	    List<Token> tokens = scanner.scanTokens();
	    for (Token token : tokens) {
	      System.out.println(token);
	    }
	}//end run
}
