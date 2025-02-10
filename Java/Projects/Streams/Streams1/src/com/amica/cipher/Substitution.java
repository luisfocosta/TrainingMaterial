package com.amica.cipher;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides functions that can translate a string using a simple
 * substitution cipher, develop the inverse of a known cipher, and develop
 * a cippher by doing frequency analysis on an encoded string.
 * 
 * @author Will Provost
 */
public class Substitution {

	/**
	 * Returns a string that is the given string, with each character that is
	 * found as a key in the given map translated to the corresponding value.
	 * Characters not found in the cipher are preserved. 
	 */
	public static String translate(String message, Map<Character,Character> cipher) {
		StringBuilder translated = new StringBuilder();
		for (int i = 0; i < message.length(); ++i) {
			char c = message.charAt(i);
			if (cipher.containsKey(c)) {
				translated.append(cipher.get(c));
			} else {
				translated.append(c);
			}
		}
		return translated.toString();
	}
	
	/**
	 * Returns a map that is the inverse of the given map.
	 * This assumes "isomorphism" of the original map: if for example two
	 * letters both translate to the same letter, the resulting inverse will
	 * map back to just one of them. 
	 */
	public static Map<Character,Character> invertCipher(Map<Character,Character> cipher) {
		Map<Character,Character> inverse = new HashMap<>();
		for (char c : cipher.keySet()) {
			inverse.put(cipher.get(c), c);
		}
		
		return inverse;
	}
	
	/**
	 * Returns a cipher map determined by counting occurrences of all letters
	 * in the given message, and then mapping the most common letter to the 
	 * first character in the given common-letters string; then the 
	 * second-most-common to the second given letter, and so on. 
	 */
	public static Map<Character,Character> cipherFromFrequencies
			(String message, String commonLetters) {
		
		Map<Character,Integer> counts = new HashMap<>();
		for (char letter = 'A'; letter <= 'Z'; ++letter) {
			counts.put(letter, 0);
		}
		for (int i = 0; i < message.length(); ++i) {
			char c = message.charAt(i);
			if (counts.containsKey(c)) {
				counts.put(c,  counts.get(c) + 1);
			}
		}

		Map<Character,Character> cipher = new HashMap<>();
		for (int i = 0; i < commonLetters.length(); ++i) {
			int maxOccurrence = 0;
			char maxOccurred = ' ';
			for (char c : counts.keySet()) {
				if (counts.get(c) > maxOccurrence) {
					maxOccurrence = counts.get(c);
					maxOccurred = c;
				}
			}
			cipher.put(maxOccurred, commonLetters.charAt(i));
			counts.remove(maxOccurred);
		}

		return cipher;
	}
	
	public static void main(String[] args) {
		
		// Substituation cipher
		String encodedLetters = "LKDWSEAHUQMTCGVRJPYZINOBFX";
		Map<Character,Character> cipher = new HashMap<>();
		char originalChar = 'A';
		for (int i = 0; i < encodedLetters.length(); ++i) {
			cipher.put(originalChar, encodedLetters.charAt(i));
			++originalChar;
		}

		String original = "ABLE WAS I ERE I SAW ELBA";
		System.out.println("Original: " + original);
		String encoded = translate(original, cipher);
		System.out.println("Encoded: " + encoded);
		
		Map<Character,Character> inverse = invertCipher(cipher);
		String decoded = translate(encoded, inverse);
		System.out.println("Decoded: " + decoded);
		System.out.println();
		
		// Frequency analysis
		String mystery = "UV SK KWGVNFP IVC SWPF OGTVFPIXTF KFIPE SK YILHFP NIOF SF I XUL WY ICOUMF LHIL UOF XFFV LGPVUVN WOFP UV SK SUVC FOFP EUVMF  DHFVFOFP KWG YFFT TUBF MPULUMUAUVN IVK WVF  HF LWTC SF  QGEL PFSFSXFP LHIL ITT LHF RFWRTF UV LHUE DWPTC HIOFVL HIC LHF ICOIVLINFE LHIL KWGOF HIC  HF CUCVL EIK IVK SWPF  XGL DFOF ITDIKE XFFV GVGEGITTK MWSSGVUMILUOF UV I PFEFPOFC DIK  IVC U GVCFPELWWC LHIL HF SFIVL I NPFIL CFIT SWPF LHIV LHIL  UV MWVEFJGFVMF  US UVMTUVFC LW PFEFPOF ITT QGCNSFVLE  I HIXUL LHIL HIE WRFVFC GR SIVK MGPUWGE VILGPFE LW SF IVC ITEW SICF SF LHF OUMLUS WY VWL I YFD OFLFPIV XWPFE  LHF IXVWPSIT SUVC UE JGUMB LW CFLFML IVC ILLIMH ULEFTY LW LHUE JGITULK DHFV UL IRRFIPE UV I VWPSIT RFPEWV  IVC EW UL MISF IXWGL LHIL UV MWTTFNF U DIE GVQGELTK IMMGEFC WY XFUVN I RWTULUMUIV  XFMIGEF U DIE RPUOK LW LHF EFMPFL NPUFYE WY DUTC  GVBVWDV SFV  SWEL WY LHF MWVYUCFVMFE DFPF GVEWGNHL   YPFJGFVLTK U HIOF YFUNVFC ETFFR  RPFWMMGRILUWV  WP I HWELUTF TFOULK DHFV U PFITUAFC XK EWSF GVSUELIBIXTF EUNV LHIL IV UVLUSILF PFOFTILUWV DIE JGUOFPUVN WV LHF HWPUAWV  YWP LHF UVLUSILF PFOFTILUWVE WY KWGVN SFV  WP IL TFIEL LHF LFPSE UV DHUMH LHFK FZRPFEE LHFS  IPF GEGITTK RTINUIPUELUM IVC SIPPFC XK WXOUWGE EGRRPFEEUWVE  PFEFPOUVN QGCNSFVLE UE I SILLFP WY UVYUVULF HWRF  U IS ELUTT I TULLTF IYPIUC WY SUEEUVN EWSFLHUVN UY U YWPNFL LHIL  IE SK YILHFP EVWXXUEHTK EGNNFELFC  IVC U EVWXXUEHTK PFRFIL  I EFVEF WY LHF YGVCISFVLIT CFMFVMUFE UE RIPMFTTFC WGL GVFJGITTK IL XUPLH  IVC  IYLFP XWIELUVN LHUE DIK WY SK LWTFPIVMF  U MWSF LW LHF ICSUEEUWV LHIL UL HIE I TUSUL  MWVCGML SIK XF YWGVCFC WV LHF HIPC PWMB WP LHF DFL SIPEHFE  XGL IYLFP I MFPLIUV RWUVL U CWVL MIPF DHIL ULE YWGVCFC WV  DHFV U MISF XIMB YPWS LHF FIEL TIEL IGLGSV U YFTL LHIL U DIVLFC LHF DWPTC LW XF UV GVUYWPS IVC IL I EWPL WY SWPIT ILLFVLUWV YWPFOFP  U DIVLFC VW SWPF PUWLWGE FZMGPEUWVE DULH RPUOUTFNFC NTUSREFE UVLW LHF HGSIV HFIPL  WVTK NILEXK  LHF SIV DHW NUOFE HUE VISF LW LHUE XWWB  DIE FZFSRL YPWS SK PFIMLUWV   NILEXK  DHW PFRPFEFVLFC FOFPKLHUVN YWP DHUMH U HIOF IV GVIYYFMLFC EMWPV  UY RFPEWVITULK UE IV GVXPWBFV EFPUFE WY EGMMFEEYGT NFELGPFE  LHFV LHFPF DIE EWSFLHUVN NWPNFWGE IXWGL HUS  EWSF HFUNHLFVFC EFVEULUOULK LW LHF RPWSUEFE WY TUYF  IE UY HF DFPF PFTILFC LW WVF WY LHWEF UVLPUMILF SIMHUVFE LHIL PFNUELFP FIPLHJGIBFE LFV LHWGEIVC SUTFE IDIK  LHUE PFERWVEUOFVFEE HIC VWLHUVN LW CW DULH LHIL YTIXXK USRPFEEUWVIXUTULK DHUMH UE CUNVUYUFC GVCFP LHF VISF WY LHF MPFILUOF LFSRFPISFVL   UL DIE IV FZLPIWPCUVIPK NUYL YWP HWRF  I PWSIVLUM PFICUVFEE EGMH IE U HIOF VFOFP YWGVC UV IVK WLHFP RFPEWV IVC DHUMH UL UE VWL TUBFTK U EHITT FOFP YUVC INIUV  VW   NILEXK LGPVFC WGL ITT PUNHL IL LHF FVC  UL UE DHIL RPFKFC WV NILEXK  DHIL YWGT CGEL YTWILFC UV LHF DIBF WY HUE CPFISE LHIL LFSRWPIPUTK MTWEFC WGL SK UVLFPFEL UV LHF IXWPLUOF EWPPWDE IVC EHWPL DUVCFC FTILUWVE WY SFV";
		String commonLetters = "ETAINOSRHDMLUFCYGWBVPKQJXZ";
		Map<Character,Character> decoder = cipherFromFrequencies(mystery, commonLetters);
		System.out.println(translate(mystery, decoder));
	}

}
