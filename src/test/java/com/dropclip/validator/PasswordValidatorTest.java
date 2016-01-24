package com.dropclip.validator;

import static org.junit.Assert.*;

import javax.validation.ConstraintValidatorContext;

import org.junit.Before;
import org.junit.Test;

public class PasswordValidatorTest {

	private PasswordValidator passwordValidator;
	private ConstraintValidatorContext constraintValidatorContext; // Added for code clarity.
	private final static String[] ARRAY_OF_INVALID_CHARACTERS = {"`","~","!","@","#","$","%","^","&","*","(",")","-","_","=",
		 "+","{","[","}","]","|","\\",":",";","\"","'",";","<",",",">",".","?","/"};

	@Before
	public void setUp() throws Exception {
		this.passwordValidator = new PasswordValidator();
		this.constraintValidatorContext = null;
	}
    /*
     * Invalid password scenarios.
     */
	@Test
	public void nullPasswordShouldBeInvalid() {
		String password = null;
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void emptyPasswordShouldBeInvalid() {
		String password = "";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void whiteSpacePasswordWithLengthOfFiveShouldBeInvalid() {
		String password = "     ";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void lowercaseMixedCharacterWithLengthOfFourShouldBeInvalid() {
		String password = "123a";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void lowercaseMixedCharacterWithLengthOf13ShouldBeInvalid() {
		String password = "123456789abcd";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithUppercaseShouldBeInvalid() {
		String password = "123aB";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithoutANumberShouldBeInvalid() {
		String password = "abcde";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithoutALetterShouldBeInvalid() {
		String password = "12345";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithNonAlphanumericCharacterAtTheEndShouldBeInvalid() {	
		for(int x=0; x < ARRAY_OF_INVALID_CHARACTERS.length; x++){
			String password = "12345a" + ARRAY_OF_INVALID_CHARACTERS[x];
			assertFalse(ARRAY_OF_INVALID_CHARACTERS[x] + " is an invalid character." , this.passwordValidator.isValid( password, this.constraintValidatorContext ));
		}
	}
	
	@Test
	public void passwordWithNonAlphanumericCharacterAtTheBeginningShouldBeInvalid() {	
		for(int x=0; x < ARRAY_OF_INVALID_CHARACTERS.length; x++){
			String password = ARRAY_OF_INVALID_CHARACTERS[x] + "12345a";
			assertFalse(ARRAY_OF_INVALID_CHARACTERS[x] + " is an invalid character." , this.passwordValidator.isValid( password, this.constraintValidatorContext ));
		}
	}
	
	@Test
	public void passwordWithNonAlphanumericCharacterInTheMiddleShouldBeInvalid() {	
		for(int x=0; x < ARRAY_OF_INVALID_CHARACTERS.length; x++){
			String password = "123" + ARRAY_OF_INVALID_CHARACTERS[x] + "45a";
			assertFalse(ARRAY_OF_INVALID_CHARACTERS[x] + " is an invalid character." , this.passwordValidator.isValid( password, this.constraintValidatorContext ));
		}
	}
	
	@Test
	public void passwordWithConsecutiveLetterShouldBeInvalid() {
		String password = "12aa345";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutiveLetterAtBeginningShouldBeInvalid() {
		String password = "aa123";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutiveLetterAtEndShouldBeInvalid() {
		String password = "12345aa";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutiveNumberShouldBeInvalid() {
		String password = "12a3345";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutivePatternOfLengthTwoShouldBeInvalid() {
		String password = "abab1";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutivePatternOfLengthTwoAtEndShouldBeInvalid() {
		String password = "1abab";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	
	@Test
	public void passwordWithConsecutivePatternOfLengthTwoAtEndOf12CharacterPasswordShouldBeInvalid() {
		String password = "12345678abab";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutivePatternOfLengthThreeShouldBeInvalid() {
		String password = "ab1ab1";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutivePatternOfLengthThreeAtBeginningShouldBeInvalid() {
		String password = "abcabc1234";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutivePatternOfLengthFourShouldBeInvalid() {
		String password = "abc1abc1";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutivePatternOfLengthFourAtBeginningShouldBeInvalid() {
		String password = "abcdabcd1234";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutivePatternOfLengthFourAtEndShouldBeInvalid() {
		String password = "1234abcdabcd";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutivePatternOfLengthFiveAtEndShouldBeInvalid() {
		String password = "zyabcd1abcd1";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutivePatternOfLengthFiveShouldBeInvalid() {
		String password = "abcd1abcd1";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordWithConsecutivePatternOfLengthSixShouldBeInvalid() {
		String password = "abcd12abcd12";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordBananaShouldBeInvalid() {
		String password = "bananas123";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void passwordmississippiShouldBeInvalid() {
		String password = "mississippi8";
		assertFalse( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	/*
	 * Valid password scenarios.
	 */
	@Test
	public void lowercaseMixedCharacterPasswordWithLengthOfFiveShouldBeValid() {
		String password = "1234a";
		assertTrue( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}

	@Test
	public void lowercaseMixedCharacterWithLengthOf12ShouldBeValid() {
		String password = "123456789abc";
		assertTrue( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void lowercaseMixedCharacterWithMultipleNonConsecutiveAsShouldBeValid() {
		String password = "a2a4a6a8a9ba";
		assertTrue( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void lowercaseMixedCharacterWithMultipleNonConsecutiveTwosShouldBeValid() {
		String password = "a2b2c2d2e2fg";
		assertTrue( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void lowercaseMixedCharacterWithMultipleNonConsecutiveThreesShouldBeValid() {
		String password = "ab3cd3ef3gh3";
		assertTrue( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
	@Test
	public void lowercaseMixedCharacterWithMultipleABCsShouldBeValid() {
		String password = "abc3abc4abc5";
		assertTrue( this.passwordValidator.isValid( password, this.constraintValidatorContext ) );
	}
	
}
