package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "MEDIA_TYPES" )
public class MediaType {
	
	private int mId;
	private String mName;
	private String mIcon;
	
	public MediaType() {
		super();
	}
	
	/**
	 * @param mName The name of the media type.
	 * @param mIcon The path to the icon of the media type.
	 */
	public MediaType(String mName, String mIcon) {
		super();
		this.mName = mName;
		this.mIcon = mIcon;
	}
	
	// TODO PK generation doesn't work properly. Has to be fixed in order to retrieve the correct values from Oracle sequences.
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
//	@GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "MEDIA_TYPES_PK_SEQ"))
//    @GeneratedValue(generator = "generator")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "media_type_seq")
//	@SequenceGenerator(name = "media_type_seq", sequenceName = "MEDIA_TYPES_PK_SEQ", allocationSize=1)
	@Column(name = "MEDIA_ID", nullable = false)
	public int getId() {
		return mId;
	}
	public void setId(int mId) {
		this.mId = mId;
	}
	
	@Column(name="ICON_PATH", nullable = false)
	public String getIcon() {
		return mIcon;
	}

	public void setIcon(String mIcon) {
		this.mIcon = mIcon;
	}

	@Column(name="NAME", nullable = false)
	public String getName() {
		return mName;
	}
	public void setName(String mName) {
		this.mName = mName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MediaType [mId=" + mId + ", mName=" + mName + ", mIcon="
				+ mIcon + "]";
	}
}
