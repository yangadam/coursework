package com.dedup4.storage.dedup.util;

import com.google.common.base.Preconditions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static int LEN = 16;

    static char[] hexChars = {
            '0', '1', '2', '3',
            '4', '5', '6', '7',
            '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f'};

    public static String getMD5Str(byte[] chunkBytes) {
        return toHexString(getMD5(chunkBytes));
    }

    public static String getMD5Str(byte[] chunkBytes, int startPos) {
        return getMD5Str(chunkBytes, startPos, chunkBytes.length - startPos);
    }

    public static String getMD5Str(byte[] chunkBytes, int startPos, int len) {
        Preconditions.checkArgument(len > 0);
        Preconditions.checkArgument(startPos + len <= chunkBytes.length);

        byte[] subChunkBytes = new byte[len];
        for (int i = 0; i < len; i++) {
            subChunkBytes[i] = chunkBytes[startPos + i];
        }
        return getMD5Str(subChunkBytes);
    }

//	public static long md5Time = 0;

    public static byte[] getMD5(byte[] chunkBytes) {
        return getMD5(chunkBytes, chunkBytes.length);
    }

    public static byte[] getMD5(byte[] chunkBytes, int len) {
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        md5.update(chunkBytes, 0, len);
        byte[] result = md5.digest();
        return result;
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChars[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChars[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    // Test
    public static void main(String[] args) {
//		final int _1KB = 1024;
//		final int numTest = 1000000;
//		Random ramd = new Random();
//		
//		try(FileOutputStream writer = new FileOutputStream("E:\\fingerprints.txt")) {
//			for(int i = 0; i < numTest; i++) {
//				byte[] data = new byte[(ramd.nextInt(15) + 2) * _1KB];
//				ramd.nextBytes(data);
//				writer.write(MD5.getMD5(data));
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

//		try(FileInputStream reader = new FileInputStream("E:\\fingerprints.txt")) {
//			for(int i = 0; i < 1000; i++) {
//				byte[] fingerprint = new byte[16];
//				reader.read(fingerprint);
//				System.out.println(MD5.toHexString(fingerprint));
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		

    }

}
