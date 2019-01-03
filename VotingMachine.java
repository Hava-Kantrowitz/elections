/**
 * Creates a class for a voter to cast their vote
 * @author Victoria Bowen and Hava Kantrowitz 
 */

import java.util.Scanner;

public class VotingMachine {
	
	private ElectionData voteData = new ElectionData();
	private Scanner keyboard = new Scanner(System.in);
	
	/**
	 * instantiates the election machine interface
	 * @param voteData : the data for the election
	 */
	public VotingMachine(ElectionData voteData) {
		this.voteData = voteData;
	}
	
	/**
	 * Displays the current ballot
	 */
	public void printBallot() {
		    System.out.println("The candidates are ");
		    for (String s : voteData.getBallot()) {
		      System.out.println(s);
		    }
		  }
		  
	/**
	 * Displays the voting screen
	 */
	public void screen() {
		this.printBallot();
		System.out.println("Who do you want to vote for as your first choice?");
		String candidate1 = keyboard.next();
		System.out.println("Who do you want to vote for as your second choice?");
		String candidate2 = keyboard.next();
		System.out.println("Who do you want to vote for as your third choice?");
		String candidate3 = keyboard.next();
		try {
			voteData.processVote(candidate1, candidate2, candidate3);
		} catch (UnknownCandidateException e) {
			System.out.println(e.getUnknownName() + " is not on the ballot.");
			System.out.println("Would you like to add " + e.getUnknownName() + " to the ballot?");
			String determine = keyboard.next();
			if (determine.equals("y") || determine.equals("Y")) {
				addWriteIn(e.getUnknownName());
			}
			else if (!determine.equals("y") || !determine.equals("Y")) {
				System.out.println("That is not a valid input. Please try again.");
			}
			System.out.println("Please vote again.");
			screen();
		} catch (DuplicateVotesException e) {
			System.out.println(e.getDuplicateName() + " has already been voted for.");
			System.out.println("You may not vote for the same candidate twice. Please try again.");
			screen();
		}
		System.out.println("You voted for " + candidate1 + " as your first choice, " + candidate2 + " as your second choice, and " + candidate3 + "as your third choice.");
	}
	
	/**
	 * Adds a new candidate to the ballot
	 * @param name : the name of the new candidate
	 */
	public void addWriteIn(String name) {
		try {
			voteData.addCandidate(name);
			System.out.println(name + " was successfully added to the ballot.");
		} catch (CandidateExistsException e) {
			System.out.println(e.getCandidateName() + " is already on the ballot.");
		}
	}

}
