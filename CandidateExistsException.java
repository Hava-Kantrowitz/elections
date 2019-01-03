/**
 * Class to receive the exception when a candidate already exists
 * @author Victoria Bowen and Hava Kantrowitz
 *
 */

public class CandidateExistsException extends Exception {
	
	private String candidateName;
	
	/**
	 * Creates a candidate exists exception 
	 * @param name : name of candidate
	 */
	public CandidateExistsException(String name) {
		this.candidateName = name;
	}
	
	/**
	 * Gets name of candidate
	 * @return name of candidate
	 */
	public String getCandidateName() {
		return candidateName;
	}

}
