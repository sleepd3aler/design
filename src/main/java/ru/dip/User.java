package ru.dip;

public class User {
    /** 1)
     *  Нарушение DIP выражено тем, что Юзер зависит от конкретной реализации -> EmailMessenger(Нужно
     *  сделать абстрактный мессенджер и зависеть от абстракции */
    private EmailMessenger messenger;

    public User(EmailMessenger messenger) {
        this.messenger = messenger;
    }

    void sendMsg(User user, String msg) {
        messenger.sendMsg(user, msg);
    }

    class EmailMessenger {
        void sendMsg(User user, String msg) {
            /** sending message to user **/
        }
    }
}
