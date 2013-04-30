package model;

public class Album {
	private String Name;
	private String Interpreter;
	private String CoverPicture;
	
	//TODO Relationship one Album contains media (1:n)
	public Album() {
		super();
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getInterpreter() {
		return Interpreter;
	}
	public void setInterpreter(String interpreter) {
		Interpreter = interpreter;
	}
	public String getCoverPicture() {
		return CoverPicture;
	}
	public void setCoverPicture(String coverPicture) {
		CoverPicture = coverPicture;
	}

	public String toString() {
		return "Album: Name: " + Name + ", Interpreter: " + Interpreter
				+ ", CoverPicture location: " + CoverPicture;
	}
	
}
