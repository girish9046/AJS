/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 *
 * @author gmartham
 */
public class TimeZoneTest {
	
	
	public static void main(String args[]){
		TimeZone zone = TimeZone.getTimeZone("Canada/Newfoundland"); //US/Eastern     //US/Central
		System.out.println("....zone......."+zone);
		System.out.println("....zone......."+zone.inDaylightTime( new Date("11/07/2016") ));//mm/dd/yyyy
		
	}
	
	
	
//	public void est(){
//		boolean inDaylightTime;
//		SimpleTimeZone SimpleTimeZone = new SimpleTimeZone();
//		inDaylightTime = SimpleTimeZone.inDaylightTime(new Date());
//	}
}
