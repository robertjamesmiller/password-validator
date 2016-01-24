package com.dropclip.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

	public void initialize(Password annotation) {}
	
	/* 
	 * (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 * 
	 * Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.
	 * Must be between 5 and 12 characters in length.
	 * Must not contain any sequence of characters immediately followed by the same sequence.
	 */
	public boolean isValid(CharSequence charSequence, ConstraintValidatorContext context) {
		if (charSequence == null || charSequence.length() < 5 || charSequence.length() > 12) {
			return false;
		}
		
		boolean foundNumber = false;
		boolean foundAlphaCharacter = false;
		
		for(int x=0; x < charSequence.length(); x++){
			char singleCharacter = charSequence.charAt(x);
			// return false if a character is found that is not alphanumeric
			if(!Character.isLetterOrDigit(singleCharacter)){
				return false;
			}
			// return false if an alpha characters is uppercase
			if (Character.isAlphabetic(singleCharacter) && Character.isUpperCase(singleCharacter)){
				return false;
			}
			// return false if consecutive characters are found
			if (x > 0 && singleCharacter == charSequence.charAt(x-1)){
				return false;
			// return false if consecutive pairs of characters are found
			} else if (x > 2 && charSequence.subSequence(x-3, x-1).equals(charSequence.subSequence(x-1, x+1))){
				return false;
			// return false if consecutive triplets of characters are found
			} else if (x > 4 && charSequence.subSequence(x-5, x-2).equals(charSequence.subSequence(x-2, x+1))){
				return false;
			// return false if consecutive quadruplets of characters are found
			} else if (x > 6 && charSequence.subSequence(x-7, x-3).equals(charSequence.subSequence(x-3, x+1))){
				return false;
			// return false if consecutive quintuplets of characters are found
			} else if (x > 8 && charSequence.subSequence(x-9, x-4).equals(charSequence.subSequence(x-4, x+1))){
				return false;
			// return false if consecutive sextuplets of characters are found
			} else if (x > 10 && charSequence.subSequence(x-11, x-5).equals(charSequence.subSequence(x-5, x+1))){
				return false;
			}
			
			// confirm that the password has at least one lowercase character and one number
			if (Character.isAlphabetic(singleCharacter)) {
				foundAlphaCharacter = true;
			} else if (Character.isDigit(singleCharacter)) {
				foundNumber = true;
			}
        }
		
		return foundNumber && foundAlphaCharacter;
	}
}
