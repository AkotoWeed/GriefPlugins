package net.korex.api.commands;

public class Parameter {

	private int place;
	private String command;
	private Executor execute;

	public Parameter(String command, int place, Executor execute) {
		this.setPlace(place);
		this.setCommand(command);
		this.setExecute(execute);
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Executor getExecute() {
		return execute;
	}

	public void setExecute(Executor execute) {
		this.execute = execute;
	}

}
