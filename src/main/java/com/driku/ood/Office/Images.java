//Model class for images

//Created by Vikas Kumar

package com.driku.ood.Office;

public class Images {
	int image_id;
	int office_id;
	String path;
	
	
	public int getImage_id() {
		return image_id;
	}
	public int getOffice_id() {
		return office_id;
	}
	public String getPath() {
		return path;
	}
	
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public void setOffice_id(int office_id) {
		this.office_id = office_id;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
