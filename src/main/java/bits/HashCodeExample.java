package bits;

public class HashCodeExample {
    private int number;
    private String name;
    private boolean exist;
    private float fl;

    public HashCodeExample(int number, String name, boolean exist, float fl) {
        this.number = number;
        this.name = name;
        this.exist = exist;
        this.fl = fl;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + number;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (exist ? 13 : 17);
        int c = Float.floatToIntBits(fl);
        result = 31 * result + c;
        return result;
    }

    public static void main(String[] args) {
        HashCodeExample first = new HashCodeExample(2, "asd", true, 13);
        HashCodeExample second = new HashCodeExample(1, "adasd", true, 13);
        System.out.println(first.hashCode());
        System.out.println(first.hashCode());
        System.out.println(second.hashCode());

    }
}
