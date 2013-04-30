package model;

public class Album {
	private String mName;
	private String mInterpreter;
	private String mCoverPicture;
	
	//TODO Relationship one Album contains media (1:n)
	public Album() {
		super();
	}
	public String getmName() {
		return mName;
	}
	public void setName(String name) {
		mName = name;
	}
	public String getInterpreter() {
		return mInterpreter;
	}
	public void setInterpreter(String interpreter) {
		mInterpreter = interpreter;
	}
	public String getCoverPicture() {
		return mCoverPicture;
	}
	public void setCoverPicture(String coverPicture) {
		mCoverPicture = coverPicture;
	}

	public String toString() {
		return "Album: Name: " + mName + ", Interpreter: " + mInterpreter
				+ ", CoverPicture location: " + mCoverPicture;
	}
	
}
