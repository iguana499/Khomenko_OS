import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			Stack stack = new Stack(256);
			Kernel kernel = new Kernel(stack);
			System.out.println(kernel.getCallsList());
			System.out.println("Введите индекс и аргументы необходимые для Системного Вызова");
			String input = scanner.nextLine();
			scanner.close();
			String[] inputs = input.split(" ");
			for (int i = 1; i < inputs.length - 1; i += 2) 
				stack.Push(new Argument(inputs[i], inputs[i + 1]));
			kernel.execute(Integer.parseInt(inputs[0]));
	
	}

}
