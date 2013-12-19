package com.school.domain;

public class DiskInfo {
	private String diskName;
	private boolean isLocalDisk;
	private String useableSize;
	private String totalSpace;
	private long useablePercent;

	public long getUseablePercent() {
		return useablePercent;
	}

	public void setUseablePercent(long useablePercent) {
		this.useablePercent = useablePercent;
	}

	public String getDiskName() {
		return diskName;
	}

	public void setDiskName(String diskName) {
		this.diskName = diskName;
	}

	public boolean isLocalDisk() {
		return isLocalDisk;
	}

	public void setLocalDisk(boolean isLocalDisk) {
		this.isLocalDisk = isLocalDisk;
	}

	public String getUseableSize() {
		return useableSize;
	}

	public void setUseableSize(String useableSize) {
		this.useableSize = useableSize;
	}

	public String getTotalSpace() {
		return totalSpace;
	}

	public void setTotalSpace(String totalSpace) {
		this.totalSpace = totalSpace;
	}
}