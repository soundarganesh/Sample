import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


@SuppressWarnings("serial")
public class mainClass extends JFrame {
	private JLabel labelStatus;
	private JLabel labelOne;
	private JLabel labelOneValue;
	String stayedHours;
	String stayedRedMineHours;
	String redmineHours;
	static String empId;
	static String userName;
	static String password;
	long diffSec;
	long diffMin;
	long diffHrs;
	private JLabel issue;
	private JLabel date;
	private JLabel hours;
	private JLabel comment;
	private JLabel activity;
	private JLabel meeting;
	private JFormattedTextField textFieldOne;
	private JTextField textFieldTwo;
	private JTextField textFieldThree;
	private JTextField textFieldFour;
	final Choice options = new Choice();
	
	private JLabel issueMeeting;
	private JLabel dateMeeting;
	private JLabel hoursMeeting;
	private JLabel commentMeeting;
	private JLabel activityMeeting;
	private JTextField textFieldOneMeeting;
	private JTextField textFieldTwoMeeting;
	private JTextField textFieldThreeMeeting;
	private JTextField textFieldFourMeeting;
	final Choice optionsMeeting = new Choice();
	
	String inTime;
	Date dateValue = new Date();
	SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	String outTime = String.format("%TR", dateValue);
	public String path = "\\\\192.168.0.241\\cjk-ndc\\ganerama\\LOG FILE\\file\\Attendance_Report_"+ft.format(dateValue)+".xls";
	JFrame jFrame=new JFrame("Log Time");

	public void prepareGUI() throws IOException, ParseException{
		jFrame.setSize(400,400);
		
		labelStatus = new JLabel("Hello  "+System.getenv("USERNAME")+" ! ! ", JLabel.CENTER );
		labelStatus.setBounds(30, 5, 300, 30);
		getValuesFromXls();
		labelOne= new JLabel("Stayed Hours : ");
		labelOne.setBounds(8,20, 100,40);
		labelOneValue = new JLabel(stayedHours+" ( RedMine Time : "+stayedRedMineHours+" )");
		labelOneValue.setBounds(100,20, 500,40);
		if(stayedHours == null ){
			labelOneValue.setForeground(Color.RED);
			JOptionPane.showMessageDialog(labelOneValue, "Your In Time Is Not Registered  !!");
		}else {
			labelOneValue.setForeground(Color.BLUE);
		}
		jFrame.add(labelOne);
		jFrame.add(labelOneValue);
		
		/**
		 *  TASK ONE :
		 */
		issue= new JLabel("Issue             :");
		issue.setBounds(50, 70, 100, 10);
		textFieldOne = new JFormattedTextField();
		textFieldOne.setBounds(150, 65, 70, 20);
		date=new JLabel("Date               :");
		date.setBounds(50, 100, 100, 10);
		textFieldTwo = new JTextField(ft.format(dateValue));
		textFieldTwo.setBounds(150, 95, 70, 20);
		hours= new JLabel("Hours            :");
		hours.setBounds(50, 130, 100, 10);
		textFieldThree = new JTextField(redmineHours);
		textFieldThree.setBounds(150, 125, 70, 20);
		comment= new JLabel("Comment     :");
		comment.setBounds(50, 160, 100, 10);
		textFieldFour = new JTextField();
		textFieldFour.setBounds(150, 155, 200, 20);
		activity=new JLabel("Activity          :");
		activity.setBounds(50, 190, 100, 20);
		
		options.add("Training");
		options.add("Environment Setup");
		options.add("Design");
		options.add("Development");
		options.add("QA");
		options.add("Code Review");
		options.add("Testing");
		options.add("Chennai Hyouka");
		options.add("Hue Framework Research");
		options.add("Hue Component Research");
		options.add("Hue Technology Learning");
		options.add("Hue Client Meeting");
		options.add("PMO Meeting");
		options.add("Project Meeting");
		options.add("General Meeting");
		options.add("Technology Learning");
		options.add("Project Management");
		options.add("Japanese Training");
		options.add("Dream Event");
		options.add("Lableader Meeting");
		options.add("Group Meeting");
		options.add("ISMS Test");
		options.add("Technical Documentation");
		options.add("Marketing Communication");
		options.add("Content Development");
		options.add("Graphics & Design");
		options.add("Support");
		
		options.select(3);
		options.setBounds(150, 185, 200, 20);
		
		
		/**
		 *  TASK MEETING :
		 */
		meeting = new JLabel("( Meeting )");
		meeting.setBounds(480, 30, 100, 20);
		issueMeeting= new JLabel("Issue             :");
		issueMeeting.setBounds(400, 70, 100, 10);
		textFieldOneMeeting = new JTextField();
		textFieldOneMeeting.setBounds(550, 65, 70, 20);
		dateMeeting=new JLabel("Date               :");
		dateMeeting.setBounds(400, 100, 100, 10);
		textFieldTwoMeeting = new JTextField(ft.format(dateValue));
		textFieldTwoMeeting.setBounds(550, 95, 70, 20);
		hoursMeeting= new JLabel("Hours            :");
		hoursMeeting.setBounds(400, 130, 100, 10);
		textFieldThreeMeeting = new JTextField("0.50");
		textFieldThreeMeeting.setBounds(550, 125, 70, 20);
		commentMeeting= new JLabel("Comment     :");
		commentMeeting.setBounds(400, 160, 100, 10);
		textFieldFourMeeting = new JTextField();
		textFieldFourMeeting.setBounds(550, 155, 200, 20);
		activityMeeting=new JLabel("Activity          :");
		activityMeeting.setBounds(400, 190, 100, 20);
		
		optionsMeeting.add("Training");
		optionsMeeting.add("Environment Setup");
		optionsMeeting.add("Design");
		optionsMeeting.add("Development");
		optionsMeeting.add("QA");
		optionsMeeting.add("Code Review");
		optionsMeeting.add("Testing");
		optionsMeeting.add("Chennai Hyouka");
		optionsMeeting.add("Hue Framework Research");
		optionsMeeting.add("Hue Component Research");
		optionsMeeting.add("Hue Technology Learning");
		optionsMeeting.add("Hue Client Meeting");
		optionsMeeting.add("PMO Meeting");
		optionsMeeting.add("Project Meeting");
		optionsMeeting.add("General Meeting");
		optionsMeeting.add("Technology Learning");
		optionsMeeting.add("Project Management");
		optionsMeeting.add("Japanese Training");
		optionsMeeting.add("Dream Event");
		optionsMeeting.add("Lableader Meeting");
		optionsMeeting.add("Group Meeting");
		optionsMeeting.add("ISMS Test");
		optionsMeeting.add("Technical Documentation");
		optionsMeeting.add("Marketing Communication");
		optionsMeeting.add("Content Development");
		optionsMeeting.add("Graphics & Design");
		optionsMeeting.add("Support");
		
		optionsMeeting.select(20);
		optionsMeeting.setBounds(550, 185, 200, 20);
		
		
		JButton createButton = new JButton("create");
		createButton.setBounds(250,230,80,25);
		
		JButton refreshButton = new JButton("refresh");
		refreshButton.setBounds(350,230,80,25);
		
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.setVisible(false);
				try {
					main(null);
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(dateValue);
				int dateValidation = 0;
				int monthValidation = 0;
				int yearValidation = 0;
				int dateValidationMeeting = 0;
				int monthValidationMeeting = 0;
				int yearValidationMeeting = 0;
				dateValidation = Integer.valueOf(textFieldTwo.getText().charAt(8)+""+textFieldTwo.getText().charAt(9));
				monthValidation = Integer.valueOf(textFieldTwo.getText().charAt(5)+""+textFieldTwo.getText().charAt(6));
				yearValidation = Integer.valueOf(textFieldTwo.getText().charAt(0)+""+textFieldTwo.getText().charAt(1)+
						textFieldTwo.getText().charAt(2)+""+textFieldTwo.getText().charAt(3));
				dateValidationMeeting = Integer.valueOf(textFieldTwoMeeting.getText().charAt(8)+""+textFieldTwoMeeting.getText().charAt(9));
				monthValidationMeeting = Integer.valueOf(textFieldTwoMeeting.getText().charAt(5)+""+textFieldTwoMeeting.getText().charAt(6));
				yearValidationMeeting = Integer.valueOf(textFieldTwoMeeting.getText().charAt(0)+""+textFieldTwoMeeting.getText().charAt(1)+
						textFieldTwo.getText().charAt(2)+""+textFieldTwoMeeting.getText().charAt(3));
				int flag = 1;
				if(!textFieldOne.getText().trim().isEmpty() && textFieldOneMeeting.getText().trim().isEmpty()) {
					flag = 1;
				}else if(textFieldOne.getText().trim().isEmpty() && !textFieldOneMeeting.getText().trim().isEmpty()) {
					flag = 2;
				}else {
					flag = 0;
				}
				
				if(flag == 1) {
					if(textFieldOne.getText().trim().isEmpty()) {
						issue.setForeground(Color.red);
						JOptionPane.showMessageDialog(issue, "Enter "+issue.getText());
						return;
					}else if(textFieldTwo.getText().trim().isEmpty()) {
						date.setForeground(Color.red);
						JOptionPane.showMessageDialog(date, "Enter the "+date.getText());
						return;
					}else if(textFieldThree.getText().trim().isEmpty()) {
						hours.setForeground(Color.red);
						JOptionPane.showMessageDialog(hours, "Enter no. of "+hours.getText());
						return;
					}else {
						if(dateValidation > dateValue.getDate()) {
							date.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else if(monthValidation > dateValue.getMonth()+1) {
							date.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else if(yearValidation > cal.get(Calendar.YEAR)) {
							date.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else {
							issue.setForeground(Color.black);
							date.setForeground(Color.black);
							hours.setForeground(Color.black);
							try {
								redmineCall(flag);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
				}else if(flag == 2){
					if(textFieldOneMeeting.getText().trim().isEmpty()) {
						issueMeeting.setForeground(Color.red);
						JOptionPane.showMessageDialog(issueMeeting, "Enter "+issueMeeting.getText());
						return;
					}else if(textFieldTwoMeeting.getText().trim().isEmpty()) {
						dateMeeting.setForeground(Color.red);
						JOptionPane.showMessageDialog(dateMeeting, "Enter the "+dateMeeting.getText());
						return;
					}else if(textFieldThreeMeeting.getText().trim().isEmpty()) {
						hoursMeeting.setForeground(Color.red);
						JOptionPane.showMessageDialog(hoursMeeting, "Enter no. of "+hoursMeeting.getText());
						return;
					}else {
						if(dateValidationMeeting > dateValue.getDate()) {
							dateMeeting.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else if(monthValidationMeeting > dateValue.getMonth()+1) {
							dateMeeting.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else if(yearValidationMeeting > cal.get(Calendar.YEAR)) {
							dateMeeting.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else {
							issueMeeting.setForeground(Color.black);
							dateMeeting.setForeground(Color.black);
							hoursMeeting.setForeground(Color.black);
							try {
								redmineCall(flag);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
				}else {
					if(textFieldOne.getText().trim().isEmpty()) {
						issue.setForeground(Color.red);
						JOptionPane.showMessageDialog(issue, "Enter "+issue.getText());
						return;
					}else if(textFieldTwo.getText().trim().isEmpty()) {
						date.setForeground(Color.red);
						JOptionPane.showMessageDialog(date, "Enter the "+date.getText());
						return;
					}else if(textFieldThree.getText().trim().isEmpty()) {
						hours.setForeground(Color.red);
						JOptionPane.showMessageDialog(hours, "Enter no. of "+hours.getText());
						return;
					}if(textFieldOneMeeting.getText().trim().isEmpty()) {
						issueMeeting.setForeground(Color.red);
						JOptionPane.showMessageDialog(issueMeeting, "Enter "+issueMeeting.getText());
						return;
					}else if(textFieldTwoMeeting.getText().trim().isEmpty()) {
						dateMeeting.setForeground(Color.red);
						JOptionPane.showMessageDialog(dateMeeting, "Enter the "+dateMeeting.getText());
						return;
					}else if(textFieldThreeMeeting.getText().trim().isEmpty()) {
						hoursMeeting.setForeground(Color.red);
						JOptionPane.showMessageDialog(hoursMeeting, "Enter no. of "+hoursMeeting.getText());
						return;
					}else {
						if(dateValidation > dateValue.getDate()) {
							date.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else if(monthValidation > dateValue.getMonth()+1) {
							date.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else if(yearValidation > cal.get(Calendar.YEAR)) {
							date.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}if(dateValidationMeeting > dateValue.getDate()) {
							dateMeeting.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else if(monthValidationMeeting > dateValue.getMonth()+1) {
							dateMeeting.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else if(yearValidationMeeting > cal.get(Calendar.YEAR)) {
							dateMeeting.setForeground(Color.red);
							JOptionPane.showMessageDialog(date, "You can't put log time for this date.");
							return;
						}else {
							issue.setForeground(Color.black);
							date.setForeground(Color.black);
							hours.setForeground(Color.black);
							issueMeeting.setForeground(Color.black);
							dateMeeting.setForeground(Color.black);
							hoursMeeting.setForeground(Color.black);
							try {
								redmineCall(flag);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
				JOptionPane.showMessageDialog(issue,"Log Time is Updated ! !");
			}
		});
		jFrame.add(labelStatus);
		jFrame.add(issue);
		jFrame.add(date);
		jFrame.add(hours);
		jFrame.add(comment);
		jFrame.add(activity);
		jFrame.add(textFieldOne);
		jFrame.add(textFieldTwo);
		jFrame.add(textFieldThree);
		jFrame.add(textFieldFour);
		jFrame.add(options);
		
		jFrame.add(meeting);
		jFrame.add(issueMeeting);
		jFrame.add(dateMeeting);
		jFrame.add(hoursMeeting);
		jFrame.add(commentMeeting);
		jFrame.add(activityMeeting);
		jFrame.add(textFieldOneMeeting);
		jFrame.add(textFieldTwoMeeting);
		jFrame.add(textFieldThreeMeeting);
		jFrame.add(textFieldFourMeeting);
		jFrame.add(optionsMeeting);
		jFrame.add(createButton);
		jFrame.add(refreshButton);
		
        jFrame.setResizable(false);
		jFrame.setSize(800,300);
		jFrame.setLayout(null);
		jFrame.setLocation(550, 200);
		jFrame.setVisible(true);
	}
	@SuppressWarnings("resource")
	private void getValuesFromXls() throws IOException, ParseException {

		FileInputStream inputStream = new FileInputStream(path);
		Workbook workbook = new HSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		for(int index=0; index<sheet.getPhysicalNumberOfRows(); index++) {
			Row rowValue = sheet.getRow(index);
			String id = rowValue.getCell(0).toString();
			if(id.equalsIgnoreCase(empId)) {
				inTime = rowValue.getCell(6).toString();
				if(!inTime.isEmpty()) {
					calculateTimeDifference();
					break;
				}else {
					return;
				}
			}
		}
	}
	private void calculateTimeDifference() throws ParseException {
		DateFormat format = new SimpleDateFormat("hh:mm:ss");
		Date d = format.parse(inTime);
		inTime = String.format("%TR", d);
		long milliSec = dateValue.getTime() - d.getTime();
		diffSec = milliSec/1000%60;
		diffMin = milliSec/(60*1000)%60;
		diffHrs = milliSec/(60*60*1000)%24;
		
		if(d.getHours() < 13 && dateValue.getHours() >= 14) {
			if(diffMin < 10) {
				stayedHours = (diffHrs-1)+" : 0"+diffMin;
			}else {
				stayedHours = (diffHrs-1)+" : "+diffMin;
			}
			redmineHours = meetingTime(diffHrs-1, diffMin);
		}else if(d.getHours() < 13 && dateValue.getHours() <= 14) {
			stayedHours = diffHrs+" : "+diffMin;
			redmineHours = meetingTime(diffHrs, diffMin);
		}else if(d.getHours() >= 14) {
			stayedHours = diffHrs+" : "+diffMin;
			redmineHours = meetingTime(diffHrs, diffMin);
		}
	}
	public String meetingTime(long hours, long minutes) {
		String finalHours = null;
		int minute = (int) (minutes);
		int redMinutes=(minute*100)/60;
		if(redMinutes < 10) {
			stayedRedMineHours = hours+".0"+redMinutes;
		}else {
			stayedRedMineHours = hours+"."+redMinutes;
		}
		if(redMinutes > 50){
			redMinutes = redMinutes - 50;
		}else if(redMinutes < 50 && hours > 0) {
			hours = hours - 1;
			redMinutes = 50 + redMinutes;
		}if(redMinutes < 10){
			finalHours = String.valueOf(hours+".0"+redMinutes);
		}else {
			finalHours = String.valueOf(hours+"."+redMinutes);
		}
		return finalHours;
	}
	public void redmineCall (int flag) throws InterruptedException {
		
		String currentPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", currentPath+"//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://192.168.41.237/redmine/login");
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("login")).click();
		Thread.sleep(1000);
		if(flag == 1) {
			driver.navigate().to("http://192.168.41.237/redmine/issues/"+textFieldOne.getText().trim()+"/time_entries/new");
			Thread.sleep(1000);
			driver.findElement(By.id("time_entry_spent_on")).clear();
			driver.findElement(By.id("time_entry_spent_on")).sendKeys(textFieldTwo.getText().trim());
			driver.findElement(By.id("time_entry_hours")).sendKeys(textFieldThree.getText().trim());
			driver.findElement(By.id("time_entry_comments")).sendKeys(textFieldFour.getText().trim());
			WebElement select = driver.findElement(By.id("time_entry_activity_id"));
			select.click();

			Select dropDown = new Select(select);
			dropDown.selectByIndex(options.getSelectedIndex()+1);
			driver.findElement(By.name("commit")).click();
		}else if(flag == 2) {
			driver.navigate().to("http://192.168.41.237/redmine/issues/"+textFieldOneMeeting.getText().trim()+"/time_entries/new");
			Thread.sleep(1000);
			driver.findElement(By.id("time_entry_spent_on")).clear();
			driver.findElement(By.id("time_entry_spent_on")).sendKeys(textFieldTwoMeeting.getText().trim());
			driver.findElement(By.id("time_entry_hours")).sendKeys(textFieldThreeMeeting.getText().trim());
			driver.findElement(By.id("time_entry_comments")).sendKeys(textFieldFourMeeting.getText().trim());
			WebElement selectValue = driver.findElement(By.id("time_entry_activity_id"));
			selectValue.click();
			Select dropDownList = new Select(selectValue);
			dropDownList.selectByIndex(21);		
			driver.findElement(By.name("commit")).click();
		}else {
			driver.navigate().to("http://192.168.41.237/redmine/issues/"+textFieldOne.getText().trim()+"/time_entries/new");
			Thread.sleep(1000);
			driver.findElement(By.id("time_entry_spent_on")).clear();
			driver.findElement(By.id("time_entry_spent_on")).sendKeys(textFieldTwo.getText().trim());
			driver.findElement(By.id("time_entry_hours")).sendKeys(textFieldThree.getText().trim());
			driver.findElement(By.id("time_entry_comments")).sendKeys(textFieldFour.getText().trim());
			WebElement select = driver.findElement(By.id("time_entry_activity_id"));
			select.click();

			Select dropDown = new Select(select);
			dropDown.selectByIndex(options.getSelectedIndex()+1);
			driver.findElement(By.name("commit")).click();
			
			Thread.sleep(1000);
			driver.navigate().to("http://192.168.41.237/redmine/issues/"+textFieldOneMeeting.getText().trim()+"/time_entries/new");
			Thread.sleep(1000);
			driver.findElement(By.id("time_entry_spent_on")).clear();
			driver.findElement(By.id("time_entry_spent_on")).sendKeys(textFieldTwoMeeting.getText().trim());
			driver.findElement(By.id("time_entry_hours")).sendKeys(textFieldThreeMeeting.getText().trim());
			driver.findElement(By.id("time_entry_comments")).sendKeys(textFieldFourMeeting.getText().trim());
			WebElement selectValue = driver.findElement(By.id("time_entry_activity_id"));
			selectValue.click();
			Select dropDownList = new Select(selectValue);
			dropDownList.selectByIndex(21);		
			driver.findElement(By.name("commit")).click();
		}
		Thread.sleep(1000);
		driver.navigate().to("http://192.168.41.237/redmine/time_entries?utf8=%E2%9C%93&f%5B%5D=spent_on&op%5Bspent_on%5D=t&f%5B%5D=user_id&op%5Buser_id%5D=%3D&v%5Buser_id%5D%5B%5D=me&f%5B%5D=&c%5B%5D=project&c%5B%5D=spent_on&c%5B%5D=user&c%5B%5D=activity&c%5B%5D=issue&c%5B%5D=comments&c%5B%5D=hours");;
	}
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, ParseException {
		mainClass mainObject = new mainClass();
		String currentPath = System.getProperty("user.dir");
		int temp;
		FileInputStream inputStream = new FileInputStream(currentPath+"\\details.xls");
		Workbook workbook = new HSSFWorkbook(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
			empId = sheet.getRow(0).getCell(1).toString();
			userName = sheet.getRow(1).getCell(1).toString();
			password = sheet.getRow(2).getCell(1).toString();
			if(empId.contains(".")) {
				temp = (int) sheet.getRow(0).getCell(1).getNumericCellValue();
				empId = String.valueOf(temp);
			}
		mainObject.prepareGUI();
	}
}
