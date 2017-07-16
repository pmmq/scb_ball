import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputManager {
	
	public final String INPUT_OPERATION_ORDER = "INPUT_OPERATION_ORDER";
	public final String INPUT_OPERATION_EXCHANGE = "INPUT_OPERATION_EXCHANGE";
	public final String REGEX_INT_DETECTOR = "^-?\\d+$";
	
	private int[] inputNK;
	private List<Operation> operationList;
	
	public InputManager(){
		Scanner sc = new Scanner(System.in);
		operationList = new ArrayList();
		
		boolean verifyInputOperation = true;
		while(verifyInputOperation){
			System.out.println("Please insert N K following this format");
			String operationInput = sc.nextLine();
			if(verifyInputFormat(operationInput,INPUT_OPERATION_ORDER)){
				inputNK = getInputAsInteger(operationInput);
				verifyInputOperation = false;
			}
		}
		
		for(int i = 0; i < inputNK[0]; i++){
			System.out.println("Please insert A B following this format");
			String operationInput = sc.nextLine();
			if(verifyInputFormat(operationInput, INPUT_OPERATION_EXCHANGE)){
				int[] inputArr = getInputAsInteger(operationInput);
				operationList.add(new Operation(inputArr[0],inputArr[1]));
			}else{
				--i;
			}
		}
	}
	
	public int[] getInputNK(){
		return inputNK;
	}
	
	public List<Operation> getOperationList(){
		return operationList;
	}
	
	public boolean verifyInputFormat(String input,String Type){
		int[] inputArr = getInputAsInteger(input);
		
		if(inputArr == null){
			return false;
		}
		
		int firstValue = inputArr[0];
		int secondValue = inputArr[1];
		
		switch(Type){
			case INPUT_OPERATION_ORDER : 
				if(firstValue < 1 || firstValue > 50){
					showError(INPUT_OPERATION_ORDER);
					return false;
				}
				if(secondValue < 1 || secondValue > 1000000000){
					showError(INPUT_OPERATION_ORDER);
					return false;
				}
				break;
			case INPUT_OPERATION_EXCHANGE :
				if(firstValue == secondValue){
					showError(INPUT_OPERATION_EXCHANGE);
					return false;
				}
				if(firstValue > 1 && firstValue > 8){
					showError(INPUT_OPERATION_EXCHANGE);
					return false;
				}
				if(secondValue > 1 && secondValue > 8){
					showError(INPUT_OPERATION_EXCHANGE);
					return false;
				}
				break;
		}
		return true;
	}
	
	private int[] getInputAsInteger(String input){
		String[] inputSplit = input.split("\\s+");	
		
		// check space
		if(inputSplit.length != 2){
			showError(INPUT_OPERATION_ORDER);
			return null;
		}
		// check integer
		if(!inputSplit[0].matches(REGEX_INT_DETECTOR) || !inputSplit[1].matches(REGEX_INT_DETECTOR)){
			showError(INPUT_OPERATION_ORDER);
			return null;
		}
		
		int[] returnArr = new int[2];
		returnArr[0] = Integer.parseInt(inputSplit[0]);
		returnArr[1] = Integer.parseInt(inputSplit[1]);
		
		return  returnArr;
	}
	
	
	private void showError(String Type){
		System.out.println("Invalid Input Format Please insert " + Type + " again");
	}
}
