package ru.ood.lsp;

public class Subscriber {

    protected PhoneNumber phoneNumber;

    public Subscriber(PhoneNumber phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    protected void validate(PhoneNumber phoneNumber) {
        if (phoneNumber.getCountryCode() < 1 || phoneNumber.getCountryCode() > 999) {
            throw new IllegalArgumentException("Invalid country code!");
        }
        if (phoneNumber.getCityCode() < 1 || phoneNumber.getCityCode() > 999) {
            throw new IllegalArgumentException("Invalid city code!");
        }
        if (phoneNumber.getNumber() < 1 || phoneNumber.getNumber() > 999_999_999) {
            throw new IllegalArgumentException("Invalid number!");
        }
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    static class SomeOperatorSubscriber extends Subscriber {

        public SomeOperatorSubscriber(PhoneNumber phoneNumber) {
            super(phoneNumber);
        }

        @Override
        public void setPhoneNumber(PhoneNumber phoneNumber) {
            /* some specific logic; */
            /* Забыли сделать проверку. Возможно не валидное состояние */
            this.phoneNumber = phoneNumber;
        }
    }

    public static class ThirdRule {
        public static void main(String[] args) {
            Subscriber subscriber = new SomeOperatorSubscriber(
                    new PhoneNumber(+1, 111, 111_111_111)
            );
            subscriber.setPhoneNumber(
                    new PhoneNumber(-1, 111, 111_111_111)
            );
        }
    }
}
