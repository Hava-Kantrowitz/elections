import static org.junit.Assert.*;

import org.junit.Test;

public class Examples {

	ElectionData Setup () {

	    ElectionData ED = new ElectionData();

	    try {
			ED.addCandidate("husky");
		    ED.addCandidate("ziggy");
		    ED.addCandidate("gompei");
		} catch (CandidateExistsException e1) {
			return (ED);
		}

	    try {

	      ED.processVote("gompei", "husky", "ziggy");
	      ED.processVote("gompei", "ziggy", "husky");
	      ED.processVote("husky", "gompei", "ziggy");

	    } catch (Exception e) {}

	    return(ED);

	  }
	
	ElectionData Setup2 () {

	    ElectionData ED = new ElectionData();

	    try {
			ED.addCandidate("gompei");
			ED.addCandidate("husky");
		    ED.addCandidate("ziggy");
		} catch (CandidateExistsException e1) {
			return (ED);
		}

	    try {

	      ED.processVote("gompei", "husky", "ziggy");
	      ED.processVote("husky", "ziggy", "gompei");
	      ED.processVote("ziggy", "gompei", "husky");

	    } catch (Exception e) {}

	    return(ED);

	  }

	  // test first winner
	  @Test
	  public void testMostFirstWinner () {
	    assertEquals ("gompei", Setup().findWinnerMostFirstVotes());
	  }
	  
	  // test most points winner
	  @Test
	  public void testMostPointsWinner() {
		  assertEquals("gompei", Setup().findWinnerMostPoints());
	  }
	  
	  // tests most points with a tie
	  @Test
	  public void testMostPointsTie() {
		  assertEquals("gompei", Setup2().findWinnerMostPoints());
	  }
	  
	  // tests first winner with no candidate meeting the threshold
	  @Test
	  public void testMostFirstNoFifty() {
		  assertEquals("Runoff required", Setup2().findWinnerMostFirstVotes());
	  }
	  
	  // test Candidate Exists Exception 
	  @Test(expected = CandidateExistsException.class)
	  public void testCandidateExistsException() throws CandidateExistsException{
		  Setup().addCandidate("husky");
	  }
	  
	// test Duplicate Votes Exception first and second 
	  @Test(expected = DuplicateVotesException.class)
	  public void testDuplicateVotesFirstSecond() throws UnknownCandidateException, DuplicateVotesException{
		  Setup().processVote("gompei", "gompei", "husky");
	  }
	  
	  // test duplicate votes second and third
	  @Test(expected = DuplicateVotesException.class)
	  public void testDuplicateVotesSecondThird() throws UnknownCandidateException, DuplicateVotesException{
		  Setup().processVote("gompei", "husky", "husky");
	  }
	  
	  // test duplicate votes first and third
	  @Test(expected = DuplicateVotesException.class)
	  public void testDuplicateVotesFirstThird() throws UnknownCandidateException, DuplicateVotesException{
		  Setup().processVote("gompei", "husky", "gompei");
	  }
	  
	  // test Unknown Candidate Exception first
	  
	  @Test(expected = UnknownCandidateException.class)
		public void testUnknownCandidateWrongFirst() throws UnknownCandidateException, DuplicateVotesException{
			Setup().processVote("cat", "gompei", "husky");
		}
	  
	  // test unknown candidate second
	  @Test(expected = UnknownCandidateException.class)
		public void testUnknownCandidateWrongSecond() throws UnknownCandidateException, DuplicateVotesException{
			Setup().processVote("gompei", "cat", "husky");
		}
	  
	  // test unknown candidate third 
	  @Test(expected = UnknownCandidateException.class)
		public void testUnknownCandidateWrongThird() throws UnknownCandidateException, DuplicateVotesException{
			Setup().processVote("husky", "gompei", "cat");
		}
	  
	  // test multiple unknown candidates
	  @Test(expected = UnknownCandidateException.class)
	  public void testUnknownCandidateMultiple() throws UnknownCandidateException, DuplicateVotesException{
		  Setup().processVote("cat", "dog", "bird");
	  }
	  
	  // test both unknown and duplicate exceptions
	  @Test(expected = UnknownCandidateException.class)
	  public void testUnknownCandidateDuplicate() throws UnknownCandidateException, DuplicateVotesException{
		  Setup().processVote("cat", "husky", "cat");
	  }
	  
	  // we are ensuring processVote and addCandidate work via our setup of our elections, so we don't need a 
	  // specific test for either 
	
}
