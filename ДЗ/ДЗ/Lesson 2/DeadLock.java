package ru.philosophyit;

public class DeadLock {

    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s ���������� ����!\n", this.name, bower.getName());
            System.out.format("%s: ������� � �����!\n", this.name);
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s ���������� ����!\n", this.name, bower.getName());
        }
    }

    /**
     * ����� ����� � ���������
     *
     * @param args ��������� ��������� ������
     */
    public static void main(String[] args) {
        Friend alphonse = new Friend("Alphonse");
        Friend gaston = new Friend("Gaston");

        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}

