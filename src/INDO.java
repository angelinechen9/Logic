
public class INDO {
	public static String sentence1;
	public static String sentence2;
	
	/**
	 * simplifies the propositional sentence
	 * @param sentence the unsimplified propositional sentence
	 * @return the simplified propositional sentence
	 */
	public static String simplify(String sentence) {
		sentence1 = sentence;
		sentence2 = distribution(sentence1);
		sentence2 = negations(sentence2);
		sentence2 = implications(sentence2);
		return sentence2;
	}
	
	/**
	 * implements the INDO step of implications
	 * @param sentence the unsimplified propositional sentence
	 * @return the simplified propositional sentence
	 */
	private static String implications(String sentence1) {
		for (int i = 0; i < sentence1.length(); i++) {
			//if (~p|q)&(p|~q)
			if ((i + 13 <= sentence1.length()) && (sentence1.substring(i, i + 2).equals("(~")) && (sentence1.substring(i + 3, i + 4).equals("|")) && (sentence1.substring(i + 5, i + 8).equals(")&(")) && (sentence1.substring(i + 9, i + 11).equals("|~")) && (sentence1.substring(i + 12, i + 13).equals(")"))) {
				//p<=>q
				String sentence2 = sentence1.charAt(i + 2) + "<=>" + sentence1.charAt(i + 4);
				TruthColumn truthcolumn = new TruthColumn(2);
				boolean[] evaluate = truthcolumn.biconditional(truthcolumn.constants1, truthcolumn.constants2);
				for (int j = 0; j < evaluate.length; j++) {
					sentence2 = sentence2 + "\n" + truthcolumn.constants1[j] + " " + truthcolumn.constants2[j] + " " + evaluate[j];
				}
				return sentence2;
			}
			//if ~p|q
			else if ((i + 4 <= sentence1.length()) && (sentence1.substring(i, i + 1).equals("~")) && (sentence1.substring(i + 2, i + 3).equals("|"))) {
				//p=>q
				String sentence2 = sentence1.charAt(i + 1) + "=>" + sentence1.charAt(i + 3);
				TruthColumn truthcolumn = new TruthColumn(2);
				boolean[] evaluate = truthcolumn.imply(truthcolumn.constants1, truthcolumn.constants2);
				for (int j = 0; j < evaluate.length; j++) {
					sentence2 = sentence2 + "\n" + truthcolumn.constants1[j] + " " + truthcolumn.constants2[j] + " " + evaluate[j];
				}
				return sentence2;
			}
		}
		return sentence1;
	}
	
	/**
	 * implements the INDO step of negations
	 * @param sentence the unsimplified propositional sentence
	 * @return the simplified propositional sentence
	 */
	private static String negations(String sentence1) {
		for (int i = 0; i < sentence1.length(); i++) {
			//if ~p|~q
			if ((i + 5 <= sentence1.length()) && (sentence1.substring(i, i + 1).equals("~")) && (sentence1.substring(i + 2, i + 3).equals("|")) && (sentence1.substring(i + 3, i + 4).equals("~"))) {
				//~(p&q)
				String sentence2 = "~(" + sentence1.charAt(i + 1) + "&" + sentence1.charAt(i + 4) + ")";
				TruthColumn truthcolumn = new TruthColumn(2);
				boolean[] evaluate1 = truthcolumn.and(truthcolumn.constants1, truthcolumn.constants2);
				boolean[] evaluate2 = truthcolumn.negate(evaluate1);
				for (int j = 0; j < evaluate2.length; j++) {
					sentence2 = sentence2 + "\n" + truthcolumn.constants1[j] + " " + truthcolumn.constants2[j] + " " + evaluate2[j];
				}
				return sentence2;
			}
			//if ~p&~q
			else if ((i + 5 <= sentence1.length()) && (sentence1.substring(i, i + 1).equals("~")) && (sentence1.substring(i + 2, i + 3).equals("&")) && (sentence1.substring(i + 3, i + 4).equals("~"))) {
				//~(p|q)
				String sentence2 = "~(" + sentence1.charAt(i + 1) + "|" + sentence1.charAt(i + 4) + ")";
				TruthColumn truthcolumn = new TruthColumn(2);
				boolean[] evaluate1 = truthcolumn.or(truthcolumn.constants1, truthcolumn.constants2);
				boolean[] evaluate2 = truthcolumn.negate(evaluate1);
				for (int j = 0; j < evaluate2.length; j++) {
					sentence2 = sentence2 + "\n" + truthcolumn.constants1[j] + " " + truthcolumn.constants2[j] + " " + evaluate2[j];
				}
				return sentence2;
			}
		}
		return sentence1;
	}
	
	/**
	 * implements the INDO step of distribution
	 * @param sentence the unsimplified propositional sentence
	 * @return the simplified propositional sentence
	 */
	private static String distribution(String sentence1) {
		for (int i = 0; i < sentence1.length(); i++) {
			//if (p|q)&(p|r)
			if ((i + 11 <= sentence1.length()) && (sentence1.substring(i, i + 1).equals("(")) && (sentence1.substring(i + 2, i + 3).equals("|")) && (sentence1.substring(i + 4, i + 7).equals(")&(")) && (sentence1.substring(i + 8, i + 9).equals("|")) && (sentence1.substring(i + 10, i + 11).equals(")")) && (sentence1.charAt(i + 1) == sentence1.charAt(i + 7))) {
				//p|(q&r)
				String sentence2 = sentence1.charAt(i + 1) + "|(" + sentence1.charAt(i + 3) + "&" + sentence1.charAt(i + 9) + ")";
				TruthColumn truthcolumn = new TruthColumn(3);
				boolean[] evaluate1 = truthcolumn.and(truthcolumn.constants1, truthcolumn.constants2);
				boolean[] evaluate2 = truthcolumn.or(evaluate1, truthcolumn.constants3);
				for (int j = 0; j < evaluate2.length; j++) {
					sentence2 = sentence2 + "\n" + truthcolumn.constants1[j] + " " + truthcolumn.constants2[j] + " " + truthcolumn.constants3[j] + " " + evaluate2[j];
				}
				return sentence2;
			}
			//if (p&q)|(p&r)
			else if ((i + 11 <= sentence1.length()) && (sentence1.substring(i, i + 1).equals("(")) && (sentence1.substring(i + 2, i + 3).equals("&")) && (sentence1.substring(i + 4, i + 7).equals(")|(")) && (sentence1.substring(i + 8, i + 9).equals("&")) && (sentence1.substring(i + 10, i + 11).equals(")")) && (sentence1.charAt(i + 1) == sentence1.charAt(i + 7))) {
				//p&(q|r)
				String sentence2 = sentence1.charAt(i + 1) + "&(" + sentence1.charAt(i + 3) + "|" + sentence1.charAt(i + 9) + ")";
				TruthColumn truthcolumn = new TruthColumn(3);
				boolean[] evaluate1 = truthcolumn.or(truthcolumn.constants1, truthcolumn.constants2);
				boolean[] evaluate2 = truthcolumn.and(evaluate1, truthcolumn.constants3);
				for (int j = 0; j < evaluate2.length; j++) {
					sentence2 = sentence2 + "\n" + truthcolumn.constants1[j] + " " + truthcolumn.constants2[j] + " " + truthcolumn.constants3[j] + " " + evaluate2[j];
				}
				return sentence2;
			}
			//if p|q|r
			else if ((i + 5 <= sentence1.length()) && (sentence1.substring(i + 1, i + 2).equals("|")) && (sentence1.substring(i + 3, i + 4).equals("|"))) {
				//p|(q|r)
				String sentence2 = sentence1.charAt(i) + "|(" + sentence1.charAt(i + 2) + "|" + sentence1.charAt(i + 4) + ")";
				TruthColumn truthcolumn = new TruthColumn(3);
				boolean[] evaluate1 = truthcolumn.or(truthcolumn.constants1, truthcolumn.constants2);
				boolean[] evaluate2 = truthcolumn.or(evaluate1, truthcolumn.constants3);
				for (int j = 0; j < evaluate2.length; j++) {
					sentence2 = sentence2 + "\n" + truthcolumn.constants1[j] + " " + truthcolumn.constants2[j] + " " + truthcolumn.constants3[j] + " " + evaluate2[j];
				}
				return sentence2;
			}
			//if p&q&r
			else if ((i + 5 <= sentence1.length()) && (sentence1.substring(i + 1, i + 2).equals("&")) && (sentence1.substring(i + 3, i + 4).equals("&"))) {
				//p&(q&r)
				String sentence2 = sentence1.charAt(i) + "&(" + sentence1.charAt(i + 2) + "&" + sentence1.charAt(i + 4) + ")";
				TruthColumn truthcolumn = new TruthColumn(3);
				boolean[] evaluate1 = truthcolumn.and(truthcolumn.constants1, truthcolumn.constants2);
				boolean[] evaluate2 = truthcolumn.and(evaluate1, truthcolumn.constants3);
				for (int j = 0; j < evaluate2.length; j++) {
					sentence2 = sentence2 + "\n" + truthcolumn.constants1[j] + " " + truthcolumn.constants2[j] + " " + truthcolumn.constants3[j] + " " + evaluate2[j];
				}
				return sentence2;
			}
		}
		return sentence1;
	}
}
