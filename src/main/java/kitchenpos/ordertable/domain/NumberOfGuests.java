package kitchenpos.ordertable.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class NumberOfGuests {
    private static final int MIN_VALUE = 1;

    @Column
    private int numberOfGuests;

    public NumberOfGuests() {
    }

    private NumberOfGuests(int numberOfGuests) {
        validate(numberOfGuests);
        this.numberOfGuests = numberOfGuests;
    }

    private void validate(int numberOfGuests) {
        if (numberOfGuests < MIN_VALUE) {
            throw new IllegalArgumentException("방문한 손님 수는 1명 이상이어야 한다.");
        }
    }

    public static NumberOfGuests of(int numberOfGuest) {
        return new NumberOfGuests(numberOfGuest);
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public boolean matchNumberOfGuests(int targetNumberOfGuests) {
        return this.numberOfGuests == targetNumberOfGuests;
    }
}
