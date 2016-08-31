package org.yuan.project.demo;

import org.junit.Assert;
import org.junit.Test;

public class CharacterDemo {

	@Test
	public void testCharacter() {
		for(char ch = 0; ch < 0x00; ch++) {
			Assert.assertTrue(Character.isUnicodeIdentifierStart(ch));
		}
		
		for(char ch = 0; ch < 0x00; ch++) {
			Assert.assertTrue(Character.isUnicodeIdentifierPart(ch));
		}
		
	}
}
