package rocket.app.view;

import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController implements Initializable{

	private MainApp mainApp;
	private ObservableList<Integer> TermList = FXCollections.observableArrayList(15, 30);
	
	//	TODO - RocketClient.RocketMainController
	
	//	Create private instance variables for:
	//		TextBox  - 	txtIncome
	//		TextBox  - 	txtExpenses
	//		TextBox  - 	txtCreditScore
	//		TextBox  - 	txtHouseCost
	//		ComboBox -	loan term... 15 year or 30 year
	//		Labels   -  various labels for the controls
	//		Button   -  button to calculate the loan payment
	//		Label    -  to show error messages (exception throw, payment exception)
	
	@FXML
	private TextField txtIncome;
	@FXML
	private TextField txtExpenses;
	@FXML
	private TextField txtCreditScore;
	@FXML
	private TextField txtHouseCost;
	@FXML
	private TextField txtDownPayment;
	@FXML
	private ComboBox<Integer> cmbTerm = new ComboBox<Integer>(TermList);
	@FXML
	private Label iCreditScore;
	@FXML
	private Label Term;
	@FXML
	private Label Rate;
	@FXML
	private Label DownPayment;
	@FXML
	private Label lblMortgagePayment;
	@FXML
	private Label lblHousingPayment;
	@FXML
	private Label Amount;
	@FXML
	private Label Income;
	@FXML
	private Label Expenses;
	@FXML
	private Label ErrorMessage;
	@FXML
	private Button btnPayment;
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cmbTerm.setItems(TermList);
	}
	
	//	TODO - RocketClient.RocketMainController
	//			Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Object message = null;
		//	TODO - RocketClient.RocketMainController
		
		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		//	TODO - RocketClient.RocketMainController
		//			set the loan request details...  rate, term, amount, credit score, downpayment
		//			I've created you an instance of lq...  execute the setters in lq
		lq.setIncome(Double.parseDouble(txtIncome.getText()));
		lq.setExpenses(Double.parseDouble(txtExpenses.getText()));
		lq.setdAmount(Double.parseDouble(txtHouseCost.getText()));
		lq.setiDownPayment(Integer.parseInt(txtDownPayment.getText()));
		lq.setiTerm(cmbTerm.getValue().intValue());
		try {
			lq.setdRate(RateBLL.getRate(Integer.parseInt(txtCreditScore.getText())));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RateException e) {
			e.printStackTrace();
		}
		

		a.setLoanRequest(lq);
		//	send lq as a message to RocketHub		
		mainApp.messageSend(lq);
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		//	TODO - RocketClient.HandleLoanRequestDetails
		//			lRequest is an instance of LoanRequest.
		//			after it's returned back from the server, the payment (dPayment)
		//			should be calculated.
		//			Display dPayment on the form, rounded to two decimal places
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		
		Rate.setText(String.valueOf(lRequest.getdRate()) + "%");
		lblMortgagePayment.setText("$" + df.format(lRequest.getdPayment()));
		lblHousingPayment.setText("$" + df.format(lRequest.getdHousingPayment()));
		
	}

}
