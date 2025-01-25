# xalts-assignment
QA Engineer Assignment
Test Plan: End-to-End Testing of CRUD Web Application
Objective
The objective of this test plan is to thoroughly evaluate the CRUD web application’s functionality, performance, usability, and reliability. The plan will include manual and automated tests to ensure all functionalities are working as expected.
________________________________________
Scope of Testing
1.	Sign Up Functionality
o	Validates the ability to create an account using email and password.
2.	Sign In Functionality
o	Tests the ability to log in using valid credentials.
3.	Submit Request to Onboard Nodes
o	Covers the process of adding node details and submitting the request.
4.	Submit Request to Create New Private Blockchain
o	Ensures users can create a new private blockchain.
5.	Sign Out Functionality
o	Tests the user’s ability to log out successfully.
6.	General Application Performance
o	Tests performance under different load conditions.
________________________________________
Types of Testing
1.	Functional Testing
o	Validates each feature against the defined requirements.
2.	UI/UX Testing
o	Ensures the interface is user-friendly and all elements function as expected.
3.	Boundary Value Testing
o	Tests edge cases for input fields like node IDs, wallet addresses, and IP addresses.
4.	Negative Testing
o	Ensures the system handles invalid inputs gracefully.
5.	Performance Testing
o	Measures response times and system behavior under load.
6.	Integration Testing
o	Verifies interactions between modules such as node onboarding and wallet permissions.
7.	Regression Testing
o	Ensures new changes do not affect existing functionalities.
8.	Security Testing
o	Verifies secure data handling, including password storage and validation of IPs and wallet addresses.
________________________________________
Test Cases
1. Sign Up Functionality
•	Success Scenarios:
1.	Create an account with a valid email and password.
2.	Verify the system sends a confirmation email (if applicable).
•	Failure Scenarios:
1.	Use an invalid email format (e.g., missing “@”).
2.	Password less than the required length.
2. Sign In Functionality
•	Success Scenarios:
1.	Log in with correct email and password.
•	Failure Scenarios:
1.	Log in with an incorrect email.
2.	Log in with an incorrect password.
3. Submit Request to Onboard Nodes
•	Success Scenarios:
1.	Add valid Node IDs and public IPs, then submit.
•	Failure Scenarios:
1.	Use invalid Node ID format.
2.	Submit without adding any nodes.
4. Submit Request to Create Private Blockchain
•	Success Scenarios:
1.	Enter valid network name, wallet address, and nodes.
•	Failure Scenarios:
1.	Use invalid wallet address format.
2.	Skip entering nodes.
5. Sign Out Functionality
•	Success Scenario:
1.	Log out after being signed in.
•	Failure Scenario:
1.	Attempt to access protected pages after logging out.
________________________________________
Tools
1.	Manual Testing Tools: 
o	Excel/Google Sheets for tracking test cases.
2.	Automation Framework: 
o	Java with Selenium WebDriver, TestNG, and Cucumber for BDD.
3.	Bug Tracking: 
o	JIRA or any similar tool.
4.	Performance Testing Tools: 
o	Apache JMeter.
________________________________________
Deliverables
1.	Test Execution Reports.
2.	Defect Logs.
3.	Automated Test Scripts.
4.	Test Metrics.
________________________________________
<table>
<tr>
<td>ID</td>   
<td> Feature</td>
<td> Scenario</td>    
<td> Steps</td>
<td> Expected Result</td>
</tr>
<tr>
  <td>TC001</td>
  <td>	Sign Up</td>
  <td>	Valid input	</td><td>
    <li>1. Navigate to Sign Up</li>
    <li>2. Fill valid details</li>
    <li>3. Submit</li></td>
  <td>Success message displayed</td>
</tr>
<tr>
  <td>TC002</td>
  <td>	Sign Up</td><td>	Invalid email</td>
  <td>	
    <li>1. Navigate to Sign Up</li>
    <li>2. Enter invalid emaill.</li>
    <li> Submit</li></td>	
  <td>Error: Invalid email format</td>
</tr>
<tr>
  <td>TC003</td>
  <td>	Sign Up</td><td></td>	Password not meeting requirements</td>
  <td><li>	1. Navigate to Sign Up</li>
    <li>2. Enter password without lowercase, uppercase, number, or special character</li>
    <li>3. Submit</li></td>
  <td>	Error: Password does not meet security requirements</td>
</tr>
<tr>
  <td>TC004</td>
  <td>	Sign In</td><td>Valid credentials</td>
  <td>	<li>1. Navigate to Sign In</li>
    <li>2. Enter valid credentials</li>
    <li>3. Submit</li></td>
  <td>Redirect to dashboard</td></tr>
<tr>
  <td>TC005</td>
  <td>Sign Out</td>
  <td>Successful logout</td>
  <td>
    <li>	1. Log in</li>
  <li>Click Sign Out</li></td>
  <td>	Redirect to home page</td>
</tr>
</table>
