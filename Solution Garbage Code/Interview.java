package application;

import java.io.Serializable;

public class Interview implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Candidate candidate;
	protected Interviewer interviewer;
	protected String time;
	protected String log;
	protected String outcome;
	protected String location = "On Site";

	public Interview(Candidate candidate, Interviewer interviewer, String time) {
		this.candidate = candidate;
		this.interviewer = interviewer;
		this.time = time;
	}

	public Interview(Candidate candidate, Interviewer interviewer, String log, String outcome,  String time) {
		this.candidate = candidate;
		this.interviewer = interviewer;
		this.log = log;
		this.outcome = outcome;
		this.time = time;

	}
	@Override
	public String toString() {
		return "Interviewer: " + interviewer.name + "\nCandidate: " + candidate.name;
	}
	public String toString2() {
		return "Interviewer "+interviewer.toString() + "\n\nCandidate " + candidate.toString() + "\n\nInterviewTime: "+ time +"\n\nInterview Log: " + (log == null ? "Not Interviewed yet" : log);
	}
}
