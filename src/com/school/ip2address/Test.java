package com.school.ip2address;

public class Test {

	public static void main(String[] args) {
		IPSeeker seeker = IPSeeker.getInstance();
		String ip="182.148.112.214";
				System.out.println(ip+"的所在地址是:"+seeker.getAddress(ip));
	}
}