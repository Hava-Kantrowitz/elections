/**
 * Class to receive the exception when more than one vote cast been cast by the same person
 * for the same candidate
 * @author Victoria Bowen and Hava Kantrowitz
 *
 */
public class DuplicateVotesException extends Exception{
	
	private String candidateName;
	
	/**
	 * Creates an instance of the exception
	 * @param name : name of candidate who has received multiple votes
	 */
	public DuplicateVotesException(String name) {
		this.candidateName = name;
	}
	
	/**
	 * gets the name of the candidate
	 * @return the candidate name 
	 */
	public String getDuplicateName() {
		return candidateName;
	}

}
