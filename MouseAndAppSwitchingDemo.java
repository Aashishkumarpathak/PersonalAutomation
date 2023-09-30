import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MouseAndAppSwitchingDemo {
    public static final int INTERVAL = 1000;
    public static final int MAX_Y = 400;
    public static final int MAX_X = 400;
	public static final int MIN_T = 3000;
	public static final int MAX_T = 6000;
    public static int counter = 0;

    public static void main(String... args) throws Exception {
	// Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Ask the user for their choice (options 'a' or 'b')
        System.out.println("CHOOSE AUTOMATION TYPE:");
        System.out.println("a -> AWAY");
        System.out.println("b -> VIDEO");
        char userChoice = scanner.nextLine().charAt(0);

        // Configure OS choice (1 for Windows, 2 for MAC)
        int osChoice = -1;
        if (userChoice == 'a') {
			osChoice = configureOS(scanner);
            System.out.println("RUNNING AWAY AUTOMATION");
			minimiseCMD();
			runWindowsAutomation(osChoice);
        } else if (userChoice == 'b') {
            System.out.println("RUNNING VIDEO AUTOMATION");
			minimiseCMD();
			runVideoAutomation();
        } else {
            System.out.println("Invalid choice. Please choose 'a' or 'b'.");
            System.exit(1); // Exit the program for invalid input
        }
    }

	//Configure OS
	 public static int configureOS(Scanner scanner) {
        System.out.println("Choose your OS:");
        System.out.println("1 -> Windows");
        System.out.println("2 -> MAC");
        return Integer.parseInt(scanner.nextLine());
    }
    // Windows Automation
	public static void runWindowsAutomation(int osChoice){
		try {
				Robot robot = new Robot();
        Random random = new Random();
		while (true) {
			int currentCase = random.nextInt(9) + 1;
            switch (currentCase) {
                case 1:
                    // Case 1: Move the mouse to a random position within the defined bounds
                    robot.mouseMove(random.nextInt(MAX_X), random.nextInt(MAX_Y));
                    counter++;
                    break;
                case 2:
                    // Case 2: Simulate switching between open applications (Alt + Tab)
                    simulateAltTab(robot);
                    break;
                case 3:
                    // Case 3: Simulate pressing the HOME key
                    simulateKeyPress(robot, KeyEvent.VK_HOME);
                    break;
                case 4:
                    // Case 4: Simulate pressing the Windows key (Meta key)
                    simulateKeyPress(robot, KeyEvent.VK_WINDOWS);
                    break;
                case 5:
                    // Case 5: Simulate opening a new application (e.g., Ctrl + N)
                    simulateCtrlKeyAction(robot, KeyEvent.VK_N);
                    break;
                case 6:
                    // Case 6: Simulate pressing the Page Down key
                    simulateKeyPress(robot, KeyEvent.VK_PAGE_DOWN);
                    break;
                case 7:
                    // Case 7: Simulate pressing the Page Up key
                    simulateKeyPress(robot, KeyEvent.VK_PAGE_UP);
                    break;
                case 8:
                    // Case 8: Simulate pressing the Down Arrow key 10 times
                    simulateMultipleKeyPress(robot, KeyEvent.VK_DOWN, 10);
                    break;
                case 9:
                    // Case 9: Simulate pressing the Up Arrow key
                    simulateMultipleKeyPress(robot, KeyEvent.VK_UP, 10);
                    break;
            }


			int AWAY_ACTION_INTERVAL = random.nextInt((MAX_T - MIN_T) + 1) + MIN_T;
            Thread.sleep(AWAY_ACTION_INTERVAL);
        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}
	
	public static void runVideoAutomation(){
		try {
		Robot robot = new Robot();
        Random random = new Random(System.currentTimeMillis());

 
		while (true) {
		    // Generate a random number between 1 and 15 (inclusive)
			int currentCase = random.nextInt(3) + 1;
            switch (currentCase) {
                case 1:
                    // Case 1: Move the mouse to a random position within the defined bounds
                    simulateKeyPress(robot, KeyEvent.VK_SPACE);
                    break;
                case 2:
                    // Case 2: Simulate switching between open applications (Alt + Tab)
                    simulateKeyPress(robot, KeyEvent.VK_LEFT);
                    break;
                case 3:
                    // Case 3: Simulate pressing the HOME key
                    simulateKeyPress(robot, KeyEvent.VK_RIGHT);
                    break;
            }


			int VIDEO_ACTION_INTERVAL = random.nextInt((MAX_T - MIN_T) + 1) + MIN_T;
            Thread.sleep(VIDEO_ACTION_INTERVAL);
        }
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

    // Simulate Alt + Tab to switch between open applications
    public static void simulateAltTab(Robot robot) {
		Random random = new Random();
		int randomNumber = random.nextInt(5) + 1;
				
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_ALT);

        // Delay to allow time for the window switcher to appear
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
			e.printStackTrace();
        }
        // Repeat the Alt + Tab sequence to cycle through open windows
        for (int i = 0; i < randomNumber; i++) { // Adjust the number of cycles as needed
			robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
            // Adjust the delay between switches as needed
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }

        // Release the Alt key
        robot.keyRelease(KeyEvent.VK_ALT);
    }

    // Simulate a single key press
    public static void simulateKeyPress(Robot robot, int keyEvent) {
        robot.keyPress(keyEvent);
        robot.keyRelease(keyEvent);
    }

    // Simulate a key press for Ctrl + another key
    public static void simulateCtrlKeyAction(Robot robot, int keyEvent) {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(keyEvent);
        robot.keyRelease(keyEvent);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    // Simulate pressing a key multiple times
    public static void simulateMultipleKeyPress(Robot robot, int keyEvent, int times) {
        for (int i = 0; i < times; i++) {
            simulateKeyPress(robot, keyEvent);
        }
    }
	
	public static void minimiseCMD(){
	try {
            // Create a Robot object to simulate keyboard actions
            Robot robot = new Robot();

            // Simulate Alt + Space to open the window menu
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_ALT);

            // Simulate pressing the letter 'n' to minimize the window
            robot.keyPress(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_N);
        } catch (AWTException e) {
            e.printStackTrace();
        }
}
}
