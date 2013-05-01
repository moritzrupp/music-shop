package model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table( name = "MEDIA" )
public class Medium {
	private int mId;
	private String mTitle;
	private String mInterpreter;
	private String mDuration;
	private long mFileSize;
	private String mFileLocation;
	
	private int mListened;
	private int mSold;
	
	private MediaType mMediaType;
	private Album mAlbum;
	
	public Medium() {
		super();
	}

	/**
	 * @param mTitle The title of the medium.
	 * @param mInterpreter The interpreter of the medium.
	 * @param mFileLocation The file location of the medium.
	 * @param mMediaType The media type.
	 * @param mAlbum The album of the medium. Can be <tt>null</tt>.
	 */
	public Medium(String mTitle, String mInterpreter, String mFileLocation,
			MediaType mMediaType, Album mAlbum) {
		super();
		this.mTitle = mTitle;
		this.mInterpreter = mInterpreter;
		this.mFileLocation = mFileLocation;
		this.mMediaType = mMediaType;
		this.mAlbum = mAlbum;
		
		// TODO calculate filesize and duration from file
		this.mFileSize = 0;
		this.mDuration = "1";
		
		this.mListened = 0;
		this.mSold = 0;
	}

	// TODO PK generation doesn't work properly. Has to be fixed in order to retrieve the correct values from Oracle sequences.
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
//	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "MEDIA_PK_SEQ"))
//    @GeneratedValue(generator = "generator")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_seq")
//	@SequenceGenerator(name = "media_seq", sequenceName = "MEDIA_PK_SEQ", allocationSize=1)
	@Column(name = "MEDIUM_ID", nullable = false)
	public int getId() {
		return mId;
	}
	
	public void setId(int mId) {
		this.mId = mId;
	}

	@Column(name="TITLE", nullable = false)
	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	@Column(name="INTERPRETER", nullable = false)
	public String getInterpreter() {
		return mInterpreter;
	}
	
	public void setInterpreter(String mInterpreter) {
		this.mInterpreter = mInterpreter;
	}

	@Column(name="DURATION", nullable = false)
	public String getDuration() {
		return mDuration;
	}

	public void setDuration(String mDuration) {
		this.mDuration = mDuration;
	}

	@Column(name="FILESIZE", nullable = false)
	public long getFileSize() {
		return mFileSize;
	}

	public void setFileSize(long mFileSize) {
		this.mFileSize = mFileSize;
	}

	@Column(name="MEDIUM_PATH", nullable = false)
	public String getFileLocation() {
		return mFileLocation;
	}

	public void setFileLocation(String mFileLocation) {
		this.mFileLocation = mFileLocation;
	}
	
	@Column(name="NO_LISTENED", nullable = false)
	public int getListened() {
		return mListened;
	}

	public void setListened(int mListened) {
		this.mListened = mListened;
	}

	@Column(name="NO_SOLD", nullable = false)
	public int getSold() {
		return mSold;
	}

	public void setSold(int mSold) {
		this.mSold = mSold;
	}

	@ManyToOne
	@JoinColumn(name="MEDIA_TYPES_MEDIA_ID", referencedColumnName="MEDIA_ID", nullable = false)
//	@NotNull
	public MediaType getMediaType() {
		return mMediaType;
	}

	public void setMediaType(MediaType mMediaType) {
		this.mMediaType = mMediaType;
	}

	@ManyToOne
	@JoinColumn(name="ALBUMS_ALBUM_ID", referencedColumnName="ALBUM_ID", nullable = true)
	@NotFound(action=NotFoundAction.IGNORE)
	public Album getAlbum() {
		return mAlbum;
	}

	protected void setAlbum(Album mAlbum) {
		this.mAlbum = mAlbum;
	}

	public String toString() {
		return "Medium [mTitle=" + mTitle
				+ ", mInterpreter=" + mInterpreter + ", mDuration=" + mDuration
				+ ", mFileSize=" + mFileSize + ", mFileLocation="
				+ mFileLocation + ", mListened=" + mListened + ", mSold="
				+ mSold + ", mMediaType=" + mMediaType + ((mAlbum != null) ? ", mAlbum=" + mAlbum.getName() : "")
				+ "]";
	}

}
