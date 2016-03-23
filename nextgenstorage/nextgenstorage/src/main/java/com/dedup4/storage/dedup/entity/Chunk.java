package com.dedup4.storage.dedup.entity;

public class Chunk {
	/**
	 * Chunk ID.
	 */
	private int id;
	
	/**
	 * Chunk Ref Container ID
	 */
	private int containerID;
	
	/**
	 * Chunk's fingerprint of content. We need to store it into DBA.
	 */
	private byte[] fingerPrint;

	/**
	 * Chunk's reference count.
	 */
	private int refCount;

	/**
	 * Chunk's content.
	 */
	private byte[] content;

	/**
	 * Chunk's length.
	 */
	private int length;

	/**
	 * Chunk's offset.
	 */
	private int startPos;

	public Chunk() {
		
	}
	
	public Chunk(int id, byte[] fingerPrint, int refCount, byte[] content, int length, int startPos) {
		this.id = id;
		this.fingerPrint = fingerPrint;
		this.refCount = refCount;
		/**
		 * Store the content.
		 */
		this.content = new byte[length];
		System.arraycopy(content, 0, this.content, 0, length);
		this.length = length;
		this.startPos = startPos;
	}
	
	public Chunk(byte[] fingerPrint, byte[] content, int length, int startPos) {
		this.fingerPrint = fingerPrint;
		this.content = new byte[length];
		System.arraycopy(content, 0, this.content, 0, length);
		this.length = length;
		this.startPos = startPos;
	}

	public static String mergeInts(char ch, int... ints) {
		return String.valueOf(ints[0]) +
				ch +
				ints[1] +
				ch +
				ints[2] +
				"\n";
	}
	
	/**
	 * write into container meta data.
	 */
	public String getFileMetadata() {
		return mergeInts(',', containerID, startPos, length);
	}
	
	/**
	 * write into file recipe. should add container id in the front.
	 */
	public String toFileRecipe() {
		return "," + this.id + "\n";
	}

	/**
	 * Getter and Setter.
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getFingerPrint() {
		return fingerPrint;
	}

	public void setFingerPrint(byte[] fingerPrint) {
		this.fingerPrint = fingerPrint;
	}

	public int getRefCount() {
		return refCount;
	}

	public void setRefCount(int refCount) {
		this.refCount = refCount;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getContainerID() {
		return containerID;
	}

	public void setContainerID(int containerID) {
		this.containerID = containerID;
	}

}
