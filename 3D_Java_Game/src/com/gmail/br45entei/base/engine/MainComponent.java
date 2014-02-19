package com.gmail.br45entei.base.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.regex.Pattern;

public class MainComponent {
	private static final String STRING = "";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "3D OpenGL Java Game Engine - Please find a way to add renderable objects to me!";
	public static final double FRAME_CAP = 5000.0;
	protected static String[] listArgs = new String[] {STRING};
	protected static String strArgs = STRING;
	private static boolean hasSetSysArgs = false;
	
	private boolean isRunning;
	private static Game game;
	public static String resourceFolder;
	
	public MainComponent() {
		System.out.println(RenderUtil.getOpenGLVersion());
		RenderUtil.initGraphics();
		isRunning = false;
		game = new Game();
	}
	
	public void start() {
		if(isRunning) {
			return;
		}
		
		run();
	}
	
	public void stop() {
		if(!isRunning) {
			return;
		}
		isRunning = false;
		cleanUp();
		Game.print("Game ended with no errors(Exit code 0).");
		System.exit(0);
	}
	
	private void run() {
		isRunning = true;
		game.running = true;
		
		double frames = 0;
		long frameCounter = 0;
		
		final double frameTime = 1.0 / FRAME_CAP;
		
		long lastTime = Time.getTime();
		double unprocessedTime = 0;

		long nanoStart = System.nanoTime();
		while(isRunning) {
			boolean render = false;
			
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double) Time.SECOND;
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime) {
				render = true;
				
				unprocessedTime -= frameTime;
				
				if(Window.isCloseRequested()) {
					stop();
				}
				
				Time.setDelta(frameTime);
				
				game.input();
				Input.update();
				
				game.update();
				
				game.curFPS = frames;
				
				if(frameCounter >= Time.SECOND) {
					double newNanoTime = ((System.nanoTime() - nanoStart) - Time.SECOND) / 1000000.00;
					//Game.print(newNanoTime);
					
					frames += newNanoTime;
					
					game.fps = frames;//Game.print(frames);
					frames = 0;
					frameCounter = 0;
					nanoStart = System.nanoTime();
				}
			}
			if(render) {
				render();
				frames = frames + 1.0;//frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		game.running = false;
		cleanUp();
	}
	
	private void render() {
		RenderUtil.clearScreen();
		game.render();//Render 3D objects
		Window.render();//Update the opened game Window
	}
	
	private void cleanUp() {
		Window.dispose();
		game.getTextWindow().dispose();
	}
	
	public Game getGame() {
		return game;
	}
	
	protected final void initConsole(final MainComponent m) {
		new Thread(new Runnable() {
			protected final MainComponent main = m;

			protected final String DEBUG(String str) {
				if(m.getGame().showDebugMsgs == false) {
					return "";
				}
				
				println(/*GREEN + */"DEBUG: " + /*DEFAULT + */str);
				
				return str;
			}

			BufferedReader br = null;
			protected final String[] validCmds = new String[] {"?",
															 "exit",
															 "help",
															 "quit",
															 "teleport",
															 "tp",
															 "set_nick"};
			protected final String[] cmdHelp = new String[] {"\"/_CMD_\" is used for obtaining useful information about other commands that you might use.\n\nUsage: \"/_CMD_ cmdName\"; where \"cmdName\" is the command you want to get help for.",
															 "\"/_CMD_\" is used to instantly shut this game instance down. Use this only if you are sure you want to close the game abnormally, i.e. if the game window has become unresponsive and will not close.\n\nUsage: \"/_CMD_\"",
															 "\"/_CMD_\" is used for obtaining useful information about other commands that you might use.\n\nUsage: \"/_CMD_ cmdName\"; where \"cmdName\" is the command you want to get help for.",
															 "\"/_CMD_\" is used to instantly shut this game instance down. Use this only if you are sure you want to close the game abnormally, i.e. if the game window has become unresponsive and will not close.\n\nUsage: \"/_CMD_\"",
															 "\"/_CMD_\" is used for teleporting the player's camera to the three specified points in space(x, y, and z).\nAny number can be used, as long as it is a valid number according to \"Double.valueOf(String str)\".\n\nUsage: \"/_CMD_ x y z\"; where \"x y z\" are valid numbers.",
															 "\"/_CMD_\" is used for teleporting the player's camera to the three specified points in space(x, y, and z).\nAny number can be used, as long as it is a valid number according to \"Double.valueOf(String str)\".\n\nUsage: \"/_CMD_ x y z\"; where \"x y z\" are valid numbers.",
															 "\"/_CMD_\" is used for setting the nickname that is displayed when you \"\\chat\" in game. Usage: \"/_CMD_ Any new name that can have spaces and characters\"."};
			
			private boolean first_msg = true;
			
			private String nickName = "User";
			
			@SuppressWarnings("boxing")
			@Override
			public void run() {
				
				
				
				br = new BufferedReader(new InputStreamReader(System.in));
				try {
					while(main.getGame().running) {
						boolean handled_Input = false;
						Object[] input = handleInput();
						handled_Input = (boolean) input[0];
						//String feedback = (String) input[1];
						
						
						
						
						
						
						
						if(handled_Input) {
							handled_Input = false;
							input = handleInput();
						}
					}
					try {
						br.close();
					} catch (IOException e) {
						
					}
				} catch (Exception e) {
					this.println(e.getMessage() + STRING);
				}
			}
			
			public boolean checkIfIsInteger(String str) {
				try{
					Integer.valueOf(str);
				} catch(Exception e) {
					return false;
				}
				return true;
			}

			public boolean checkIfIsFloat(String str) {
				try{
					Float.valueOf(str);
				} catch(Exception e) {
					return false;
				}
				return true;
			}
			
			@SuppressWarnings("boxing")
			private Object[] handleInput() {
				//DEBUG("Start of handleInput()...");
				if(first_msg) {
					print("Enter a /command or \\chat here:\n>");
					first_msg = false;
				} else {
					//print(">");
				}
				String s = readInput();
				boolean validInput = false;
				
				
				if(s != null) {
					String cmd = s;
					String commandLabel = getBaseCmdFromCmdLine(cmd);
					String strArgs = getArgsFromCmdLine(cmd);
					String[] args = getArgListFromCmdLine(cmd);

					println(cmd);
					println(commandLabel);
					println(strArgs);

					if(commandLabel.equalsIgnoreCase("createobject")) {
						//createobject Name Shape textureName posX posY posZ
						if(args.length == 6) {
							//float one;
							//float two;
							//float three;
							if(checkIfIsFloat(args[3])) {
								//one = Float.valueOf(args[3]);
							} else {
								println("You must enter the function like this: \"createobject {Name} {ShapeName} {textureName} {posX} {posY} {posZ}\"...");
								print(">");
								return new Object[] {true, s};
							}
							if(checkIfIsFloat(args[4])) {
								//two = Float.valueOf(args[4]);
							} else {
								println("You must enter the function like this: \"createobject {Name} {ShapeName} {textureName} {posX} {posY} {posZ}\"...");
								print(">");
								return new Object[] {true, s};
							}
							if(checkIfIsFloat(args[5])) {
								//three = Float.valueOf(args[5]);
							} else {
								println("You must enter the function like this: \"createobject {Name} {ShapeName} {textureName} {posX} {posY} {posZ}\"...");
								print(">");
								return new Object[] {true, s};
							}
							ShapeClass shapeClass = ShapeClass.getInstance();
							if(shapeClass.checkIfStringIsValidShape(args[1])) {
								
								
								
								//Had to move the code into a java thread that had the OpenGL libraries(i.e. the Game class).
								game.createObject(args);
								
								
								
								
							} else {
								String shapes = "";
								for(String curShape : shapeClass.ShapeTypes) {
									shapes += "\"" + curShape + "\"; ";
								}
								shapes += ".";
								shapes = shapes.replace("; .", ".");
								println("The argument you entered, \"" + args[1] + "\", must be one of the following shape types:\n" + shapes);
							}
						} else {
							println("You must enter the function like this: \"createobject {Name} {ShapeName} {textureName} {posX} {posY} {posZ}\"...");
						}
						print(">");
						return new Object[] {true, s};
					}

					if(commandLabel.equalsIgnoreCase("setfov")) {
						if(args.length == 1) {
							float newFOV;
							
							if(checkIfIsFloat(args[0])) {
								newFOV = Float.valueOf(args[0]);
							} else {
								println("You must enter the function like this: \"setfov {#.#f}\"...");
								print(">");
								return new Object[] {true, s};
							}
							
							game.getCamera().setFOV(newFOV);
							
							
						} else {
							println("You must enter the function like this: \"setfov {#.#f}\"...");
						}
						print(">");
						return new Object[] {true, s};
					}

					if(commandLabel.equalsIgnoreCase("setmesh")) {
						if(args.length == 1) {
							if(args[0].equals("floor")) {
								Game.floor.setShape("Floor2D");
								println("Floor2D");
							} else if(args[0].equals("surface")) {
								Game.floor.setShape("Square_2");
								println("Surface2D");
							} else if(args[0].equals("triangle")) {
								Game.floor.setShape("TriangularPrism");
								println("TriangularPrism");
							} else {
								println("You must enter one of the following arguments: \"Floor2D\"; \"Surface2D\"; \"TriangularPrism\"...");
							}
						} else {
							println("You must use the function like this: setmesh {floor|surface|triangle}");
						}
						print(">");
						return new Object[] {true, s};
					}

					if(commandLabel.equalsIgnoreCase("setshape")) {
						if(args.length == 6) {
							//ShapeClass.Surface2D[1][6] = ;
							
							int one;
							int two;
							int three;
							
							int four;
							int five;
							int six;
							
							if(checkIfIsInteger(args[0])) {
								one = Integer.valueOf(args[0]);
							} else {
								println("You must enter the function like this: \"setShape {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3}\"...");
								print(">");
								return new Object[] {true, s};
							}
							if(checkIfIsInteger(args[1])) {
								two = Integer.valueOf(args[1]);
							} else {
								println("You must enter the function like this: \"setShape {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3}\"...");
								print(">");
								return new Object[] {true, s};
							}
							if(checkIfIsInteger(args[2])) {
								three = Integer.valueOf(args[2]);
							} else {
								println("You must enter the function like this: \"setShape {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3}\"...");
								print(">");
								return new Object[] {true, s};
							}
							if(checkIfIsInteger(args[3])) {
								four = Integer.valueOf(args[3]);
							} else {
								println("You must enter the function like this: \"setShape {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3}\"...");
								print(">");
								return new Object[] {true, s};
							}
							if(checkIfIsInteger(args[4])) {
								five = Integer.valueOf(args[4]);
							} else {
								println("You must enter the function like this: \"setShape {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3}\"...");
								print(">");
								return new Object[] {true, s};
							}
							if(checkIfIsInteger(args[5])) {
								six = Integer.valueOf(args[5]);
							} else {
								println("You must enter the function like this: \"setShape {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3}\"...");
								print(">");
								return new Object[] {true, s};
							}
							
							Game.floor.getRenderedShape().Surface2D = new Object[] {
									Game.floor.getRenderedShape().Surface2D[0],
									new int[] {
											0, 1, 2,
											2, 1, 3,
											one, two, three,
											four, five, six
									}
							};
							println("Set the new mesh indices to: (" + args[0] + ", " + args[1] + ", " + args[2] + ");\n(" + args[3] + ", " + args[4] + ", " + args[5] + ")!");
						} else {
							println("You must enter the function like this: \"setShape {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3} {0 - 3}\"...");
						}
						print(">");
						return new Object[] {true, s};
					}
					if(commandLabel.equalsIgnoreCase("cls")) {
						println((char)27 + "[2J");
						print(">");
						return new Object[] {true, s};
					}
					
					if(s.startsWith("/")) {
						if(s.length() >= 2) {
							performCommand(cmd.substring(1));
							validInput = true;
						}
					} else if(s.startsWith("\\")) {
						if(s.length() >= 2) {
							chat(s.substring(1));
							validInput = true;
						}
					}
					
					if(!validInput) {
						println("Unknown function: \"" + s + "\"... Please check your spelling and try again!");
					}
					DEBUG("End[1] of handleInput()...");
					print(">");
					return new Object[] {true, s};
				}
				DEBUG("End[1] of handleInput()... (s == null!)");
				print(">");
				return new Object[] {false, STRING};
			}
			
			private void chat(String s) {
				println("<" + this.nickName + ">: " + s);
			}

			private String getBaseCmdFromCmdLine(String cmd) {
				if(!(cmd.isEmpty())) {
					if(cmd.length() == 1 || cmd.contains(" ") == false) {
						return cmd;
					} else if(cmd.indexOf(" ") >= 1) {
						String command = cmd.substring(0, cmd.indexOf(" "));
						return command;
					} else {
						return cmd;
					}
				}
				return "null";
			}
			
			private String[] getArgListFromCmdLine(String cmd) {
				String[] args = new String[] {};
				if(cmd.isEmpty()) {return args;}
				if(cmd.length() >= getBaseCmdFromCmdLine(cmd).length()+1) {
					args = cmd.substring(getBaseCmdFromCmdLine(cmd).length()+1).trim().split("\\s+");
				} else {
					args = cmd.substring(getBaseCmdFromCmdLine(cmd).length()).trim().split("\\s+");
				}
				return args;
			}
			
			private String getArgsFromCmdLine(String cmd) {
				String args = STRING;
				if(cmd.isEmpty()) {return args;}
				if(cmd.length() >= getBaseCmdFromCmdLine(cmd).length()+1) {
					args = cmd.substring(getBaseCmdFromCmdLine(cmd).length()+1).trim();
				} else {
					args = cmd.substring(getBaseCmdFromCmdLine(cmd).length()).trim();
				}
				return args;
			}
			
			@SuppressWarnings("boxing")private double toNumber(String str) {final String Digits = "(\\p{Digit}+)";final String HexDigits  = "(\\p{XDigit}+)";final String Exp = "[eE][+-]?"+Digits;final String fpRegex = ("[\\x00-\\x20]*[+-]?(NaN|Infinity|((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|(\\.("+Digits+")("+Exp+")?)|"+ "(((0[xX]" + HexDigits + "(\\.)?)|(0[xX]" + HexDigits + "?(\\.)" + HexDigits + "))[pP][+-]?" + Digits + "))[fFdD]?))[\\x00-\\x20]*");if(Pattern.matches(fpRegex, str)) {return Double.valueOf(str);}return Double.NaN;}
			private boolean checkIsNumber(String str) {final String Digits = "(\\p{Digit}+)";final String HexDigits  = "(\\p{XDigit}+)";final String Exp = "[eE][+-]?"+Digits;final String fpRegex = ("[\\x00-\\x20]*[+-]?(NaN|Infinity|((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|(\\.("+Digits+")("+Exp+")?)|"+ "(((0[xX]" + HexDigits + "(\\.)?)|(0[xX]" + HexDigits + "?(\\.)" + HexDigits + "))[pP][+-]?" + Digits + "))[fFdD]?))[\\x00-\\x20]*");if(Pattern.matches(fpRegex, str)) {return true;}return false;}

			private String getValidCmds() {
				String validCmds = "\n";
				for(String curCmd : this.validCmds) {
					validCmds += "\n\"" + curCmd + "\"";
				}
				return validCmds;
			}

			private boolean performCommand(String cmd) {
				String commandLabel = getBaseCmdFromCmdLine(cmd);
				String strArgs = getArgsFromCmdLine(cmd);
				String[] args = getArgListFromCmdLine(cmd);
				//print("Base command: \"" + commandLabel + "\"...");
				//print("Arguments following command: \"" + strArgs + "\"...");
				boolean validCmd = false;
				if(commandLabel.equalsIgnoreCase("tp") || commandLabel.equalsIgnoreCase("teleport")) {
					validCmd = true;
					if(args.length == 3) {
						Vector3f newPos = new Vector3f(0, 0, 0);
						if(checkIsNumber(args[0])) {
							newPos.setX((float) toNumber(args[0]));
						} else {
							println("\"" + args[0] + "\" is not a valid number!");
							return validCmd;
						}
						if(checkIsNumber(args[1])) {
							newPos.setY((float) toNumber(args[1]));
						} else {
							println("\"" + args[1] + "\" is not a valid number!");
							return validCmd;
						}
						if(checkIsNumber(args[2])) {
							newPos.setZ((float) toNumber(args[2]));
						} else {
							println("\"" + args[2] + "\" is not a valid number!");
							return validCmd;
						}
						this.main.getGame().getCamera().setPos(newPos);
						println("You've successfully changed the camera's position to \"" + this.main.getGame().getCamera().getPos().toString() + "\"!");
						return validCmd;
					}
				} else if(commandLabel.equalsIgnoreCase("help") || commandLabel.equalsIgnoreCase("?")) {
					validCmd = true;
					println("args.length: " + args.length);
					if(args.length == 0) {
						println("Listing all valid commands: " + getValidCmds());
					} else {
						println(getHelpForCmd(strArgs));
					}
					return validCmd;
				} else if(commandLabel.equalsIgnoreCase("exit") || commandLabel.equalsIgnoreCase("quit")) {
					validCmd = true;
					String response = STRING;
					boolean validResponse = false;
					println("Are you sure you wish to terminate the game at this time? Type \"yes\" or \"y\" to confirm, or type \"no\" or \"n\" to cancel.");
					boolean stopped = false;
					while(!validResponse) {
						response = readInput();
						if(response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) {
							validResponse = true;
							main.stop();
							break;
						} else if(response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) {
							validResponse = true;
							break;
						} else {
							println("Sorry, but \"" + response + "\" isn't a valid response. Type \"yes\" or \"y\" to confirm, or type \"no\" or \"n\" to cancel.");
							validResponse = false;
						}
					}
					if(stopped) {
						System.exit(0);
					}
					return validCmd;
				} else if(commandLabel.equalsIgnoreCase("set_nick")) {
					validCmd = true;
					if(args.length >= 1) {
						this.nickName = strArgs;
						println("Your nickname has successfully been set to: " + this.nickName);
					} else {
						println("Usage: \"/" + commandLabel + " Any new name that can have spaces and characters\".");
					}
					return validCmd;
				} else if(commandLabel.equalsIgnoreCase("setshowdebugs")) {
					validCmd = true;
					if(args.length == 1) {
						if(args[0].equalsIgnoreCase("true")) {
							m.getGame().showDebugMsgs = true;
						} else if(args[0].equalsIgnoreCase("false")) {
							m.getGame().showDebugMsgs = false;
						} else {
							println("Usage: \"/" + commandLabel + " {true|false}\".");
							return validCmd;
						}
						println("Successfully set internal var \"showDebugMsgs\" to: \"" + args[0].toLowerCase() + "\"!");
					} else {
						println("Usage: \"/" + commandLabel + " {true|false}\".");
					}
					return validCmd;
				} else if(commandLabel != null && (commandLabel + STRING).equalsIgnoreCase("null") == false) {
					validCmd = true;//This is only a 'valid' command because the user typed the input with a '/' at the beginning.
					println("\"" + commandLabel + "\" is not a valid command. Please try again, making sure to spell the command correctly.");
					println("Listing all valid commands: " + getValidCmds());
				}
				if(!validCmd) {
					print("Unknown command: \"" + cmd + "\"... Please check your spelling and try again!\nListing all valid commands:\n" + getValidCmds());
				}
				//print(">"); //print("\n>");
				return validCmd;
			}

			private String getHelpForCmd(String cmd) {
				String help = "No help found for \"" + cmd + "\"...";
				boolean foundHelp = false;
				int index = 0;
				for(String curCmd : this.validCmds) {
					if(cmd.equalsIgnoreCase(curCmd)) {
						if(curCmd.equalsIgnoreCase("tp") || curCmd.equalsIgnoreCase("teleport")) {
							help = "\"" + curCmd + "\" is used for teleporting the player's camera to the three specified points in space(x, y, and z).\nAny number can be used, as long as it is a valid number according to \"Double.valueOf(String str)\".\n\nUsage: \"" + curCmd + " x y z\"; where \"x y z\" are valid numbers.";
							foundHelp = true;
						} else if(curCmd.equalsIgnoreCase("?") || curCmd.equalsIgnoreCase("help")) {
							help = "\"" + curCmd + "\" is used for obtaining useful information about other commands that you might use.\n\nUsage: \"" + curCmd + " cmdName\"; where \"cmdName\" is the command you want to get help for.";
							foundHelp = true;
						}
					}
					index++;
				}
				if(!foundHelp) {
					if(getArgListFromCmdLine(cmd).length != 0) {
						index = 0;
						for(String curCmd : this.validCmds) {
							//print("cmd: " + cmd);
							//print("curCmd: " + curCmd);
							if(curCmd.contains(cmd)) {
								//print("curCmd.contains(cmd)= true");
								foundHelp = true;
								help = this.cmdHelp[index].replaceAll("_CMD_", curCmd);
							} else {
								//print("cmd.contains(\"" + curCmd + "\")= false");
							}
							index++;
						}
						return "Showing help for the command that most resembles \""+ cmd + "\": " + help;
					}
					return "Listing all valid commands: " + getValidCmds();
				}
				return "Help for \""+ cmd + "\": \n" + help;
			}

			private String readInput() {
				try {
					return br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "null";
			}
			/**@param arg0 */protected final void print(Object arg0) {System.out.print(arg0);}
			/**@param arg0 */protected final void println(Object arg0) {System.out.print(arg0 + "\n");}
		}).start();
	}
	
	public static void main(String[] args) {
		String strArgs = STRING;
		for(String curArg : args) {
			strArgs += "" + curArg + " ";//"\"" + curArg + "\" ";
		}
		setSysArgs(strArgs.trim()/*.replaceAll("\"", STRING)*/);
		String newResourcesPath = "";
		for(String curArg : args) {
			if(curArg.isEmpty() == false) {
				if(curArg.contains("--setResourcesPath=")) {
					newResourcesPath = curArg.replace("--setResourcesPath=", "");
					if(newResourcesPath.substring(newResourcesPath.length() - 1).equals("\"")) {
						newResourcesPath = newResourcesPath.substring(0, newResourcesPath.length() - 1) + File.separatorChar;
						
					//	//newResourcesPath;
						
					}
				}
			}
		}
		
		if(newResourcesPath.equals("") == false) {
			resourceFolder = newResourcesPath;
			System.out.println("New resources path as set by \"--setResourcesPath\" is now: \"" + newResourcesPath + "\"!");
		} else {
			resourceFolder = "./../resources/";
			System.out.println("The resources path has been set to the default directory(for portability): \"" + resourceFolder + "\"!");
		}
		
		System.out.println(MessageFormat.format("System passed arguments: {0}", getSysArgs()));
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		
		MainComponent main = new MainComponent();
		
		main.initConsole(main);
		
		main.start();
		
	}

	/**
	 * @return the strArgs
	 */
	public static String getSysArgs() {
		return strArgs;
	}

	/**
	 * @param strArgs the strArgs to set
	 */
	private static void setSysArgs(String newArgs) throws IllegalArgumentException {
		if(!hasSetSysArgs) {
			strArgs = newArgs;
			hasSetSysArgs = true;
		} else {
			throw new IllegalArgumentException("The system passed arguments have already been set; they are: \"" + strArgs + "\"...");
		}
	}
	
}
