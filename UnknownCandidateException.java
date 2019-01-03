/**
 * Class to receive the exception when a candidate is voted for but isn't on the ballot
 * @author Victoria Bowen and Hava Kantrowitz 
 *
 */
public class UnknownCandidateException extends Exception{
	
	private String candidateName;
	
	/**
	 * Creates an instance of the unknown candidate exception
	 * @param name : the name of the unknown candidate
	 */
	public UnknownCandidateException(String name) {
		this.candidateName = name;
	}
	
	/**
	 * Gets the name of the unknown candidate
	 * @return the name of the candidate
	 */
	public String getUnknownName() {
		return candidateName;
	}

}
