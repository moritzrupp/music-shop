package model;

public class Medium {
	private boolean mIsAudio;
	private String mTitle;
	private String mInterpreter;
	private String mDuration;
	private double mFileSize;
	private String mFileLocation;
	
	private int mListened = 0;
	private int mSelled = 0;
	
	private MediaType mMediaType;
	private Album mAlbum;
	
	//TODO Relationships

	public Medium() {
		super();
	}

	public boolean ismIsAudio() {
		return mIsAudio;
	}

	public void setmIsAudio(boolean mIsAudio) {
		this.mIsAudio = mIsAudio;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmInterpreter() {
		return mInterpreter;
	}

	public void setmInterpreter(String mInterpreter) {
		this.mInterpreter = mInterpreter;
	}

	public String getmDuration() {
		return mDuration;
	}

	public void setmDuration(String mDuration) {
		this.mDuration = mDuration;
	}

	public double getmFileSize() {
		return mFileSize;
	}

	public void setmFileSize(double mFileSize) {
		this.mFileSize = mFileSize;
	}

	public String getmFileLocation() {
		return mFileLocation;
	}

	public void setmFileLocation(String mFileLocation) {
		this.mFileLocation = mFileLocation;
	}

	public int getmListened() {
		return mListened;
	}

	public void setmListened(int mListened) {
		this.mListened = mListened;
	}

	public int getmSelled() {
		return mSelled;
	}

	public void setmSelled(int mSelled) {
		this.mSelled = mSelled;
	}

	public MediaType getmMediaType() {
		return mMediaType;
	}

	public void setmMediaType(MediaType mMediaType) {
		this.mMediaType = mMediaType;
	}

	public Album getmAlbum() {
		return mAlbum;
	}

	public void setmAlbum(Album mAlbum) {
		this.mAlbum = mAlbum;
	}

	public String toString() {
		return "Medium [mIsAudio=" + mIsAudio + ", mTitle=" + mTitle
				+ ", mInterpreter=" + mInterpreter + ", mDuration=" + mDuration
				+ ", mFileSize=" + mFileSize + ", mFileLocation="
				+ mFileLocation + ", mListened=" + mListened + ", mSelled="
				+ mSelled + ", mMediaType=" + mMediaType + ", mAlbum=" + mAlbum
				+ "]";
	}

}
