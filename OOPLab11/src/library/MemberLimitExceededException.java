package library;

/** Checked exception: the member already holds the maximum number of loans. */
public class MemberLimitExceededException extends Exception {
    private final String memberId;
    private final int limit;

    public MemberLimitExceededException(String memberId, int limit) {
        super("Member " + memberId + " has reached the limit of " + limit + " loans");
        this.memberId = memberId;
        this.limit = limit;
    }

    public String getMemberId() { return memberId; }
    public int getLimit()       { return limit; }
}
