package com.dedup4.storage.dedup.detection;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import com.dedup4.storage.dedup.util.MD5;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

public class RecordFileIndex implements Serializable {
	private static final long serialVersionUID = -3184035806562088895L;
	static final int DIMENSION = 5;
	static final int RECORD_LEN = 28;
	static final int MAX_CAPACITY = (Integer.MAX_VALUE - 1) / RECORD_LEN;
	
	static final ImmutableMap<String, Integer> ITEMS = ImmutableMap.<String, Integer>builder()
				.put("0", 0).put("1", 1).put("2", 2).put("3", 3)
		        .put("4", 4).put("5", 5).put("6", 6).put("7", 7)
		        .put("8", 8).put("9", 9).put("a", 10).put("b", 11)
		        .put("c", 12).put("d", 13).put("e", 14).put("f", 15)
		        .build();
	
	private int numRecord;
	private String fpFilePath;
	private int capacityRecord;
	private List<List<Integer>> recordIndex;
	
	transient private MappedByteBuffer recordFile = null;
	
	public RecordFileIndex(String fpFilePath, int expectedInsertions) {
		if(expectedInsertions > MAX_CAPACITY) {
			System.err.println("Failed to create RecordFileIndex.");
			System.exit(1);
		}
		
		this.fpFilePath = fpFilePath;
		this.numRecord = 0;
		this.capacityRecord = expectedInsertions;
		
		File fpFile = new File(fpFilePath);
		Preconditions.checkArgument(!fpFile.exists(), "Failed to create file " + fpFilePath + ": File existed.");
		try {
			fpFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// map record file.
		try(FileChannel fc = new RandomAccessFile(fpFilePath,"rw").getChannel()) {
			this.recordFile = fc.map(FileChannel.MapMode.READ_WRITE, 0, RECORD_LEN * capacityRecord);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// create record index.1048576 
		this.recordIndex = new ArrayList<List<Integer>>();
		for(int _1 = 0; _1 < ITEMS.size(); _1++) {
			for(int _2 = 0; _2 < ITEMS.size(); _2++) {
				for(int _3 = 0; _3 < ITEMS.size(); _3++) {
					for(int _4 = 0; _4 < ITEMS.size(); _4++) {
						for(int _5 = 0; _5 < ITEMS.size(); _5++) {
							recordIndex.add(new ArrayList<Integer>());
						}
					}
				}
			}
		}
	}
	
	public boolean add(byte[] fingerprint, int containerID, int startPos, int chunkSize) {
		if(numRecord >= capacityRecord) {
			if(!expandCapacity()) {
				System.err.println("Failed to add record.");
				return false;
			}
		}
		
		recordFile.position(RECORD_LEN * numRecord);
		recordFile.put(generateRecord(fingerprint, containerID, startPos, chunkSize));
		recordIndex.get(getPos(MD5.getMD5Str(fingerprint))).add(numRecord);
	    numRecord++;
	    return true;
	}
	
	public String contains(byte[] fingerprint) {
		List<Integer> indexPart = recordIndex.get(getPos(MD5.getMD5Str(fingerprint)));
	    for(int i = 0; i < indexPart.size(); i++) {
	    	byte[] record = new byte[RECORD_LEN];
	    	
	        recordFile.position(RECORD_LEN * indexPart.get(i));
	        recordFile.get(record);
	        byte[] curFp = getFpFromRecord(record);
	        if(isEqual(curFp, fingerprint)) {
	        	return "" 
	        			+ getContainerIDFromRecord(record) + "," 
	        			+ getStartPosFromRecord(record) + "," 
	        			+ getChunkSizeFromRecord(record) + "\n";
	        }
	    }
	    return null;
	}
	
	private boolean isEqual(byte[] x, byte[] y) {
		if(x.length != y.length)
			return false;
		for(int i = 0; i < x.length; i++)
			if((x[i] ^ y[i]) != 0)
				return false;
		return true;
	}

	private byte[] generateRecord(byte[] fingerprint, int containerID, int startPos, int chunkSize) {
		int curPos = 0;
		byte[] record = new byte[RECORD_LEN];
		for(int i = 0; i < fingerprint.length; i++) 
			record[curPos++] = fingerprint[i];
		
		byte[] bytes_containerID = int2bytes(containerID);
		for(int i = 0; i < bytes_containerID.length; i++) 
			record[curPos++] = bytes_containerID[i];
		
		byte[] bytes_startPos    = int2bytes(startPos);
		for(int i = 0; i < bytes_startPos.length; i++) 
			record[curPos++] = bytes_startPos[i];
		
		byte[] bytes_chunkSize   = int2bytes(chunkSize);
		for(int i = 0; i < bytes_chunkSize.length; i++) 
			record[curPos++] = bytes_chunkSize[i];
		
		return record;
	}
	
	private byte[] int2bytes(int x) {
		byte[] result = new byte[4];  
		result[0] = (byte) (x & 0xff);
		result[1] = (byte) ((x >> 8) & 0xff);
		result[2] = (byte) ((x >> 16) & 0xff);
		result[3] = (byte) (x >>> 24);
		return result;   
	}  
	
	private int byte2int(byte[] x) {   
		int result = (x[0] & 0xff) | ((x[1] << 8) & 0xff00)
		| ((x[2] << 24) >>> 8) | (x[3] << 24);   
		return result;   
	}
	
	private byte[] getFpFromRecord(byte[] record) {
		byte[] fp = new byte[16];
		for(int i = 0; i < 16; i++) { fp[i] = record[i]; }
		return fp;
	}
	
	private int getContainerIDFromRecord(byte[] record) {
		return getIntFromRecord(record, 16);
	}
	
	private int getStartPosFromRecord(byte[] record) {
		return getIntFromRecord(record, 20);
	}
	
	private int getChunkSizeFromRecord(byte[] record) {
		return getIntFromRecord(record, 24);
	}
	
	private int getIntFromRecord(byte[] record, int startPos) {
		byte[] bytes_int = new byte[4];
		for(int i = 0; i < 4; i++) {
			bytes_int[i] = record[startPos + i];
		}
		return byte2int(bytes_int);
	}
	
	private int getPos(String fingerprint) {
		Preconditions.checkNotNull(fingerprint);
		
	    int[] itemPos = new int[DIMENSION];
	    for(int i = 0; i < DIMENSION; i++) {
	        itemPos[i] = ITEMS.get(String.valueOf(fingerprint.charAt(i)));
	    }
	
	    int result = 0;
	    for(int i = 0; i < DIMENSION; i++) {
	        result += itemPos[i] * Math.pow(ITEMS.size(), i);
	    }
	    return result;
	}
	
	List<Integer> get(int i) {
	    return recordIndex.get(i);
	}
	
	public String getFpFilePath() {
		return fpFilePath;
	}
	
	public int getCapacityRecord() {
		return capacityRecord;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(
				fpFilePath,
				numRecord
		);
	}
	
	private boolean expandCapacity() {
		if(capacityRecord * 2 > MAX_CAPACITY) {
			System.err.println("Failed to expand");
			return false;
		}
		capacityRecord *= 2;
		this.recordFile = null;
		try(FileChannel fc = new RandomAccessFile(fpFilePath,"rw").getChannel()) {
			this.recordFile = fc.map(FileChannel.MapMode.READ_WRITE, 0, RECORD_LEN * capacityRecord);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return true;
	}
	
	private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
		is.defaultReadObject();
		try(FileChannel fc = new RandomAccessFile(fpFilePath,"rw").getChannel()) {
			this.recordFile = fc.map(FileChannel.MapMode.READ_WRITE, 0, RECORD_LEN * capacityRecord);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
