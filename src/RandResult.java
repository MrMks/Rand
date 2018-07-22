
public class RandResult {
	private boolean m;
    private Object r;

    RandResult(boolean m, Object r) {
        this.m = m;
        this.r = r;
    }

    boolean find() {
        return m;
    }

    String asString() {
        return (String) r;
    }

    String[] asArray() {
        return (String[]) r;
    }
}
