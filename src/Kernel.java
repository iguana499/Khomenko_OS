import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kernel {
	private ArrayList<SystemCall> callsList;
	private Stack kernelStack;
	public Kernel(Stack stack) {
		this.callsList = new ArrayList<SystemCall>();
		this.kernelStack = stack;
		List<Argument> list = Arrays.asList(new Argument[] {
				new Argument("x", "int"), new Argument("y", "int")});
		callsList.add(new SystemCall(0, "sum", list));
		list = Arrays.asList(new Argument[] {
				new Argument("path", "string"), new Argument("type", "string"), new Argument("mode", "int")});
		callsList.add(new SystemCall(1, "openFile", list));
		list = Arrays.asList(new Argument[] { new Argument("stringToWrite", "string")});
		callsList.add(new SystemCall(2, "writeLine", list));
		list = Arrays.asList(new Argument[] { new Argument("boolValue", "bool")});
		callsList.add(new SystemCall(3, "negate", list));
		list = Arrays.asList(new Argument[] {
				new Argument("leftString", "string"), new Argument("rightString", "string")});
		callsList.add(new SystemCall(4, "concat", list));
	}
	public String getCallsList() {
		String result = "";
		for(SystemCall i : callsList) 
			result = result + i.getDescription() +"\n"+ i.getArgs();
		return result;
	}
	public void execute(int index) {
		try {
			String curArgs = "";
			for (int i = 0; i < callsList.get(index).getArgsListSize(); i++) {
				Argument arg = kernelStack.Pop();
				curArgs = arg.getDescription() + ": " + arg.getDataType() + " " + curArgs + "\n";
			}
			String kernelArgs = "";
			kernelArgs = kernelArgs + callsList.get(index).getArgsForCheck();
			if (curArgs.equals(kernelArgs)) 
				callsList.get(index).run();
			else 
				System.out.println("Аргументы не совпадают, необходимые аргументы :");
				System.out.println(kernelArgs);
				
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Количество аргументов недостаточно");
		}
	}
}
