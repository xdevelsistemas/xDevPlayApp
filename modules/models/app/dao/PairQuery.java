package dao;

/**
 * Created by claytonsantosdasilva on 02/08/14.
 */
public class PairQuery<A> {
    public String field;
    public A value;

    public PairQuery(String yfield, A yvalue){
        this.field = yfield;
        this.value = yvalue;
    }


}
