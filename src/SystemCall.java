import java.util.List;

public class SystemCall {
	private int id;
	private String description;
	private List<Argument> argsList;
	public SystemCall(int id, String description, List<Argument> argsList) {
		this.id = id;
		this.argsList = argsList;
		this.description = description;
	}
	public void run() {
		System.out.println("Вызван Системный вызов под индексом " + id + " \"" + description + "\"");
	}
	public String getDescription() {
		return id+" "+description;
	}
	public String getArgs() {
		String resultString = "";
		for (Argument i : argsList) 
			resultString = resultString + i.getDescription() + ": " + i.getDataType() + "\n";
		return resultString;
	}
	public String getArgsForCheck() {
		String resultString = "";
		for (int i = argsList.size() - 1; i >= 0; i--) {
			Argument arg = argsList.get(i);
			resultString = arg.getDescription() + ": " + arg.getDataType() + " " + resultString + "\n";
		}
		return resultString;
	}
	public int getArgsListSize() {
		return argsList.size();
	}
}
