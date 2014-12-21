package models;

public class Request {

	private int requestId;
	private String requester;
	private String approver;
	private long requestTime;
	private long approveTime;
	private String isApproved;
	
	
	public Request() {
		super();
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getRequester() {
		return requester;
	}
	public void setRequester(String requester) {
		this.requester = requester;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public long getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(long requestTime) {
		this.requestTime = requestTime;
	}
	public long getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(long approveTime) {
		this.approveTime = approveTime;
	}
	public String isApproved() {
		return isApproved;
	}
	public void setApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	
	
}
