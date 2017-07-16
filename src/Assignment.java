import java.util.ArrayList;
import java.util.List;

public class Assignment {
	private int[] inputNK;
	private int[] balls;
	private List<Operation> operationList;
	private List<int[]> ballMemory;
	private InputManager inputManager;
	
	public void run(){
		inputManager = new InputManager();
		setInputValue();
		setDefaultBall();
		startOperation();
		printResult();
	}
	
	private void startOperation(){
		System.out.println("Start Operation . . . .");
		for(int kIndex = 0 ; kIndex < inputNK[1] ; kIndex ++){
			for(int nIndex = 0 ; nIndex < inputNK[0]; nIndex++){
				exchange(operationList.get(nIndex).getA() , operationList.get(nIndex).getB());
//				printResult();
			}
			if(isRepeated(balls)){
				int value = inputNK[1] % ballMemory.size() - 1;
				balls = ballMemory.get(value);
				System.out.println("");
				break;
			}
			System.out.println("");
		}
	}
	
	private boolean isRepeated(int[] ballArr){
		if(ballMemory == null){
			ballMemory = new ArrayList();
			ballMemory.add(ballArr.clone());
			return false;
		}
		for(int i = 0 ; i < ballMemory.size() ; i++){
			boolean isMatch = true;
			for(int j = 0 ; j < ballArr.length ; j++){
				if(ballMemory.get(i)[j] != ballArr[j]){
					isMatch = false;
				}
			}
			if(isMatch)
			{
				return true;
			}
		}
		ballMemory.add(ballArr.clone());
		return false;
	}
	
	private void printResult(){
		for(int i = 0 ; i < balls.length ; i++){
			System.out.print(balls[i]+" ");
		}
		System.out.print(" ");
	}
	
	private void setInputValue(){
		inputNK = inputManager.getInputNK();
		operationList = inputManager.getOperationList();
//		inputNK = new int[]{16,1000000000};
//		operationList = new ArrayList();
//		operationList.add(new Operation(1,3));
//		operationList.add(new Operation(6,8));
//		operationList.add(new Operation(3,5));
//		operationList.add(new Operation(2,6));
//		operationList.add(new Operation(1,7));
//		operationList.add(new Operation(3,4));
//		operationList.add(new Operation(4,7));
//		operationList.add(new Operation(2,4));
//		operationList.add(new Operation(1,3));
//		operationList.add(new Operation(2,7));
//		operationList.add(new Operation(2,7));
//		operationList.add(new Operation(2,4));
//		operationList.add(new Operation(6,7));
//		operationList.add(new Operation(1,7));
//		operationList.add(new Operation(3,4));
//		operationList.add(new Operation(1,6));
	}
	
	private void setDefaultBall(){
		balls = new int[8];
		for(int i = 0 ; i < balls.length ; i++){
			balls[i] = i+1;
		}
	}
	
	private void exchange(int firstPosition, int secondPosition){
		int tempB = balls[secondPosition-1];
		balls[secondPosition-1] = balls[firstPosition-1];
		balls[firstPosition-1] = tempB;
	}
	
}
