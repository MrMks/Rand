
public class SameCroup extends Group{

    SameCroup(int type, double a, double b){
        super(type,a,b,1);
    }

    private NumberResult number;
    @Override
    public String getResult() {
        number = number == null ? getNumberResult() : number;
        return number.toString();
    }

    @Override
    public void action() {
        number = null;
    }
}
