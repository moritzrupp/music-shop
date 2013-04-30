package model;

public class MediaType {
	
	private int mId;
	private String mName;
	private String mIcon;
	
	public MediaType() {
		super();
	}
	
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmIcon() {
		return mIcon;
	}

	public void setmIcon(String mIcon) {
		this.mIcon = mIcon;
	}

	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}

	
	public String toString() {
		return ("Type: " + mName + "Icon location: "+ mIcon);
	}
}
