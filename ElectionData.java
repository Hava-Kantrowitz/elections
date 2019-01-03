/**
 * Creates a class to store election data
 * @author Victoria Bowen and Hava Kantrowitz
 */

import java.util.HashMap;
import java.util.LinkedList;

public class ElectionData {
  private LinkedList<String> ballot = new LinkedList<String>();
  private HashMap<String, Integer> firstChoice = new HashMap<String, Integer>();
  private HashMap<String, Integer> secondChoice = new HashMap<String, Integer>();
  private HashMap<String, Integer> thirdChoice = new HashMap<String, Integer>();
  
  
  /**
   * Creates the ballot for an election, instantiates an election data object
   */
  public ElectionData() {
    for (String n : ballot) {
    	firstChoice.put(n, 0);
    	secondChoice.put(n, 0);
    	thirdChoice.put(n, 0);
    }
  }
  
  /**
   * Gets the ballot 
   * @return LinkedList of all names on the ballot
   */
  public LinkedList<String> getBallot(){
	  return this.ballot;
  }
  
  /**
   * Processes the vote cast by a voter 
   * @param firstCandidate : the first-choice candidate of the voter
   * @param secondCandidate : the second-choice candidate of the voter
   * @param thirdCandidate : the third-choice candidate of the voter
   * @throws UnknownCandidateException : thrown when one of the candidates given by the voter isn't on the ballot
   * @throws DuplicateVotesException : thrown when the voter casts a vote for the same candidate more than once
   */
  public void processVote(String firstCandidate, String secondCandidate, String thirdCandidate) throws UnknownCandidateException, DuplicateVotesException{
	  
	  if (!firstChoice.containsKey(firstCandidate)){
		  throw new UnknownCandidateException(firstCandidate);
	  }
	  
	  else if (!firstChoice.containsKey(secondCandidate)) {
		  throw new UnknownCandidateException(secondCandidate);
	  }
	  
	  else if (!firstChoice.containsKey(thirdCandidate)) {
		  throw new UnknownCandidateException(thirdCandidate);
	  }
	  
	  else if (firstCandidate.equals(secondCandidate)) {
		  throw new DuplicateVotesException(secondCandidate);
	  }
	  
	  else if (secondCandidate.equals(thirdCandidate)) {
		  throw new DuplicateVotesException(thirdCandidate);
	  }
	  
	  else if (firstCandidate.equals(thirdCandidate)) {
		  throw new DuplicateVotesException(thirdCandidate);
	  }
	  
	  else if (firstChoice.containsKey(firstCandidate) && secondChoice.containsKey(secondCandidate) && thirdChoice.containsKey(thirdCandidate)) {
		  firstChoice.put(firstCandidate, firstChoice.getOrDefault(firstCandidate, 0) + 1);
		  secondChoice.put(secondCandidate, secondChoice.getOrDefault(secondCandidate, 0) + 1);
		  thirdChoice.put(thirdCandidate, thirdChoice.getOrDefault(thirdCandidate, 0) + 1);
	  }
	  
  }
  
  /**
   * Adds a candidate to a ballot
   * @param candidateName : the name of the candidate
   * @throws CandidateExistsException : throws when the candidate is already on the ballot
   */
  public void addCandidate(String candidateName) throws CandidateExistsException{
	  if (ballot.contains(candidateName)) {
		 throw new CandidateExistsException(candidateName);
	  }
	  
	  else {
		  ballot.add(candidateName);
		  firstChoice.put(candidateName, 0);
		  secondChoice.put(candidateName, 0);
		  thirdChoice.put(candidateName, 0);
	  }
  }
  
  /**
   * Determines the winner of the election based on the amount of first-choice votes candidates receive
   * @return the winning candidate's name, if no candidate met the victory threshold returns "runoff required"
   */
  public String findWinnerMostFirstVotes() {
	  int totalVote = 0;
	  for (String name : ballot) {
		  totalVote = totalVote + firstChoice.get(name);
	  }
	  
	  double halfOfVotes = (50*totalVote)/100;
	  
	  for (String name : ballot) {
		  if (firstChoice.get(name) > halfOfVotes) {
			  return name;
		  }
	  }
	  
	  return "Runoff required";
  }
  
  /**
   * Determines the winner of the election based on who had the most total points, 
   * weighted for first/second/third choices
   * @return the winning candidate's name 
   */
  public String findWinnerMostPoints() {
	  int totalVote = 0;
	  int interimTotalVote = 0;
	  String candidate = "";
	  for (String name : ballot) {
		  interimTotalVote = firstChoice.get(name)*3 + secondChoice.get(name)*2 + thirdChoice.get(name);
		  if (interimTotalVote > totalVote) {
			  totalVote = interimTotalVote;
			  candidate = name;
		  }
	  }
	  
	  return candidate;
  }
}
  
  