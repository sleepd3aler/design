package ru.ood.lsp;

public class Transaction {
    public int transferMoney(User first, User second, int amount) {
        first.setAmount(first.getAmount() - amount);
        second.setAmount(second.getAmount() + amount);
        int secondUserTotalAmount = second.getAmount();
        if (secondUserTotalAmount >= 999_999_999) {
            throw new IllegalArgumentException("The tax authorities are already knocking on your door");
        }
        return secondUserTotalAmount;

    }

    static class UsdTransaction extends Transaction {

        @Override
        public int transferMoney(User first, User second, int amount) {
            int secondUserTotalAmount = second.getAmount();
            if (first.getAmount() <= 0) {
                throw new IllegalArgumentException("Not enough money to transfer");
            }
            first.setAmount(first.getAmount() - amount);
            second.setAmount(second.getAmount() + amount);
            return secondUserTotalAmount;
        }
    }

    static class RubTransaction extends Transaction {
        @Override
        public int transferMoney(User first, User second, int amount) {
            first.setAmount(first.getAmount() - amount);
            second.setAmount(second.getAmount() + amount);
            return second.getAmount();
        }
    }

    static class User {

        public User() {
        }

        public User(int id, String name, int amount) {
            this.id = id;
            if (amount >= 500_000_000) {
                throw new IllegalArgumentException("The tax authorities are already knocking on your door");
            }
            this.name = name;
            this.amount = amount;
        }

        int id;

        String name;

        int amount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

    static class GovernmentEmployee extends User {

        public GovernmentEmployee(int id, String name, int amount) {
            this.id = id;
            this.name = name;
            this.amount = amount;
        }
    }
}
