package model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "ALBUMS" )
public class Album {
	private int mId;
	private String mName;
	private String mInterpreter;
	private String mCoverPicture;
	
	private List<Medium> mediaList;
	
	public Album() {
		super();
	}

	/**
	 * @param mName The name of the album.
	 * @param mInterpreter The interpreter of the album.
	 * @param mCoverPicture The path to the cover picture of the album.
	 * @param mediaList The list of media files of the album. Can be <tt>null</tt>.
	 */
	public Album(String mName, String mInterpreter, String mCoverPicture,
			List<Medium> mediaList) {
		super();
		this.mName = mName;
		this.mInterpreter = mInterpreter;
		this.mCoverPicture = mCoverPicture;
		
		if(mediaList == null)
			this.mediaList = new LinkedList<Medium>();
		else
			this.mediaList = mediaList;
	}

	/*
	 * @return the mId
	*/
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name = "ALBUM_ID", nullable = false)
	public int getId() {
	
		return mId;
	}

	/**
	 * @return the mName
	 */
	@Column(name = "NAME", nullable = false)
	public String getName() {
	
		return mName;
	}

	/**
	 * @return the mInterpreter
	 */
	@Column(name = "INTERPRETER", nullable = true)
	public String getInterpreter() {
	
		return mInterpreter;
	}

	/**
	 * @return the mCoverPicture
	 */
	@Column(name = "COVER_PATH", nullable = false)
	public String getCoverPicture() {
	
		return mCoverPicture;
	}
	
	/**
	 * @return the mediaList
	 */
	@OneToMany(mappedBy="album") // , cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Medium> getMediaList() {
	
		return mediaList;
	}

	/**
	 * @param mId the mId to set
	 */
	public void setId(int mId) {
	
		this.mId = mId;
	}

	/**
	 * @param mName the mName to set
	 */
	public void setName(String mName) {
	
		this.mName = mName;
	}

	/**
	 * @param mInterpreter the mInterpreter to set
	 */
	public void setInterpreter(String mInterpreter) {
	
		this.mInterpreter = mInterpreter;
	}

	/**
	 * @param mCoverPicture the mCoverPicture to set
	 */
	public void setCoverPicture(String mCoverPicture) {
	
		this.mCoverPicture = mCoverPicture;
	}

	/**
	 * @param mediaList the mediaList to set
	 */
	public void setMediaList(List<Medium> mediaList) {
	
		this.mediaList = mediaList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Album [mId=" + mId + ", mName=" + mName + ", mInterpreter="
				+ mInterpreter + ", mCoverPicture=" + mCoverPicture +"]";
	}	
}

