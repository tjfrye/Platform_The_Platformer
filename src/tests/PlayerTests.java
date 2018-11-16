package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import sprite.Player;

public class PlayerTests {
	
	@Test
	public void noSpeeding_X(){
		Player p = new Player();
		
		p.addVelocity_X(40000000);
		
		assertEquals(p.getVelocity_X(), 400.0, 1.0);
	}
	
	@Test
	public void noSpeeding_Y(){
		Player p = new Player();
		
		p.addVelocity_Y(40000000);
		
		assertEquals(p.getVelocity_Y(), 400.0, 1.0);
	}
	
	@Test
	public void onPlatformUpdate(){
		Player p = new Player();
		
		p.setOnPlatform(true, 200);
		
		p.update(10);
		
		assertEquals(p.getPosition_Y(), 200.0, 1.0);
	}
	
	@Test
	public void notOnPlatformUpdate(){
		Player p = new Player();
		
		p.update(10);
		
		assertEquals(p.getVelocity_Y(), 10.0, 1.0);
	}


}
