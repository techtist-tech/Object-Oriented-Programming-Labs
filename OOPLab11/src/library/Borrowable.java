package library;

/** Capability of something that can be lent to a member and handed back. */
public interface Borrowable {

    /**
     * Marks this item as lent to the member.
     *
     * @throws ItemNotAvailableException     if it is already on loan
     * @throws MemberLimitExceededException  if the member already holds the maximum number of loans
     */
    void borrow(Member member) throws ItemNotAvailableException, MemberLimitExceededException;

    /** Marks this item as back on the shelf. */
    void giveBack();
}
