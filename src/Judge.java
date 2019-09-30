/**
 * Created by Jiatong Hao, Xiankang Wu and Lijun Chen on 9/27/2019.
 *
 * A generic class that checks winning and initiate winning/losing actions in a card game
 */

public abstract class Judge<P, D> {

    public abstract int checkWinner(P playerTypeA, D playerTypeB);
}
